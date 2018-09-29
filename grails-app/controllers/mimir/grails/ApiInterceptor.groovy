package mimir.grails


class ApiInterceptor {

    def springSecurityService

    ApiInterceptor() {
        matchAll()
    }

    boolean before() {
        if(!springSecurityService.currentUser) springSecurityService.reauthenticate('player', 'player')
        println springSecurityService.currentUser
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
