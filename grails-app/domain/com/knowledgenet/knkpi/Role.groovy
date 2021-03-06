package com.knowledgenet.knkpi

class Role {

    public static final String ADMIN = "ROLE_ADMIN"
    public static final String MANAGER = "ROLE_MANAGER"
    public static final String USER = "ROLE_USER"

    String authority

    static mapping = {
        cache true
    }

    static constraints = {
        authority blank: false, unique: true
    }
}
