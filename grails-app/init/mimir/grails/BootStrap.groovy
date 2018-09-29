package mimir.grails

class BootStrap {

    def importService

    def init = { servletContext ->
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save()
        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save()

        def adminUser = User.findByUsername('user') ?: new User(username: 'admin', password: 'admin').save()
        def playerUser = User.findByUsername('player') ?: new User(username: 'player', password: 'player').save()

        UserRole.create adminUser, adminRole
        UserRole.create playerUser, userRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        if(!ImportProfilesURL.get(1)) {
            println 'Importing Profiles...'
            new ImportProfilesURL(url: "https://tinyurl.com/ybss7rrb", count: importService.importProfiles("https://tinyurl.com/ybss7rrb".toURL().text)).save()
        }
    }
    def destroy = {
    }
}
