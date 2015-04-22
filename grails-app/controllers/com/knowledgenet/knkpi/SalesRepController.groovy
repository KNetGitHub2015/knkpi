package com.knowledgenet.knkpi

import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured([Role.ADMIN])
class SalesRepController {
    def netSuiteAccessorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond SalesRep.list(params), model: [salesRepInstanceCount: SalesRep.count()]
    }

    def show(SalesRep salesRepInstance) {
        respond salesRepInstance
    }

//    def create() {
//        respond new SalesRep(params)
//    }

    @Transactional
    def save(SalesRep salesRepInstance) {
        if (salesRepInstance == null) {
            notFound()
            return
        }

        if (salesRepInstance.hasErrors()) {
            respond salesRepInstance.errors, view: 'create'
            return
        }

        salesRepInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'salesRep.label', default: 'SalesRep'), salesRepInstance.id])
                redirect salesRepInstance
            }
            '*' { respond salesRepInstance, [status: CREATED] }
        }
    }

    @Transactional
    def saveUser(CreateUserCommand createUserCommandInstance) {
        if (createUserCommandInstance?.hasErrors()) {
            respond createUserCommandInstance.errors, view: 'createUser'
            return
        }

        User user = new User(username: createUserCommandInstance.username, password: createUserCommandInstance.password)
        user.save(flush: true)

        SalesRep salesRepInstance = SalesRep.get(createUserCommandInstance.salesRepId)
        salesRepInstance.user = user
        salesRepInstance.save(flush: true)

        UserRole.create(user, Role.findByAuthority(Role.USER), true)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect salesRepInstance
            }
            '*' { respond salesRepInstance, [status: CREATED] }
        }
    }

    def getEmployees() {
        def employees = netSuiteAccessorService.getEmployees()

        employees.each {
            String repId = it.id
            if (!SalesRep.findByRepId(repId)) {
                SalesRep rep = new SalesRep() //TODO: This is wrong, it is forcing me to set everything to nullable or set default values.
                rep.repId = repId
                rep.repName = it.columns["entityid"]
                rep.title = it.columns["title"]
                rep.managerName = it.columns["supervisor"].name
                rep.managerId = it.columns["supervisor"].internalid
                rep.startDate = it.columns["hiredate"]

                def birthDate = null
                String birthDateString = it.columns["birthdate"]

                if (birthDateString) {
                    birthDate = Date.parse("M/dd/yyyy", birthDateString).format("M/d")
                }

                rep.birthDay = birthDate

                rep.save(failOnError: true)
            }
        }

        redirect(action: "index", params: params)
    }

    def edit(SalesRep salesRepInstance) {
        respond salesRepInstance
    }

    @Transactional
    def update(SalesRep salesRepInstance) {
        if (salesRepInstance == null) {
            notFound()
            return
        }

        if (salesRepInstance.hasErrors()) {
            respond salesRepInstance.errors, view: 'edit'
            return
        }

        salesRepInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'SalesRep.label', default: 'SalesRep'), salesRepInstance.id])
                redirect salesRepInstance
            }
            '*' { respond salesRepInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(SalesRep salesRepInstance) {

        if (salesRepInstance == null) {
            notFound()
            return
        }

        salesRepInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'SalesRep.label', default: 'SalesRep'), salesRepInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    def createUser(SalesRep salesRep) {
        String username = salesRep.repName.toLowerCase().replace(' ', '.')
        CreateUserCommand createUserCommand = new CreateUserCommand(username: username, salesRepId: salesRep.id)

        [createUserCommandInstance: createUserCommand]
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesRep.label', default: 'SalesRep'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
