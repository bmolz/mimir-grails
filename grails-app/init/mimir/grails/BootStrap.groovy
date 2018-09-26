package mimir.grails

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def userRole = new Role(authority: 'ROLE_USER').save()

        def adminUser = new User(username: 'admin', password: 'admin').save()
        def playerUser = new User(username: 'player', password: 'player').save()

        UserRole.create adminUser, adminRole
        UserRole.create playerUser, userRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

    }
    def destroy = {
    }
}
