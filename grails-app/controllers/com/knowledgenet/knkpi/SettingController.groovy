package com.knowledgenet.knkpi



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SettingController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Setting.list(params), model: [settingInstanceCount: Setting.count()]
    }

    def show(Setting settingInstance) {
        respond settingInstance
    }

    def create() {
        respond new Setting(params)
    }

    @Transactional
    def save(Setting settingInstance) {
        if (settingInstance == null) {
            notFound()
            return
        }

        if (settingInstance.hasErrors()) {
            respond settingInstance.errors, view: 'create'
            return
        }

        settingInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'setting.label', default: 'Setting'), settingInstance.id])
                redirect settingInstance
            }
            '*' { respond settingInstance, [status: CREATED] }
        }
    }

    def edit(Setting settingInstance) {
        respond settingInstance
    }

    @Transactional
    def update(Setting settingInstance) {
        if (settingInstance == null) {
            notFound()
            return
        }

        if (settingInstance.hasErrors()) {
            respond settingInstance.errors, view: 'edit'
            return
        }

        settingInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Setting.label', default: 'Setting'), settingInstance.id])
                redirect settingInstance
            }
            '*' { respond settingInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Setting settingInstance) {

        if (settingInstance == null) {
            notFound()
            return
        }

        settingInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Setting.label', default: 'Setting'), settingInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'setting.label', default: 'Setting'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
