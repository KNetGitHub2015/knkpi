package com.knowledgenet.knkpi

import grails.validation.Validateable

@Validateable
class CreateUserCommand {
    Long salesRepId
    String username
    String password
    String confirmPassword

    static constraints = {
        salesRepId(nullable: false)
        username(nullable: false, blank: false)
        password(nullable: false, blank: false)
        confirmPassword(nullable: false, blank: false, validator: { val, object ->
            if ((val != object.password)) {
                return 'passwordMismatch'
            }
        })
    }
}
