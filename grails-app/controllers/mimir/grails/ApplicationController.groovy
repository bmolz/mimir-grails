package mimir.grails

import grails.core.GrailsApplication
import grails.plugins.*

class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager
    def springSecurityService


    def index() {
        if(!springSecurityService.currentUser) springSecurityService.reauthenticate('player', 'player')
        println springSecurityService.currentUser
        [grailsApplication: grailsApplication, pluginManager: pluginManager]
    }
}
