package mimir.grails

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class StatisticsController {

    StatisticsService statisticsService
    SpringSecurityService springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        def max = Math.min(params?.max ?: 10, 100)
        def offset = params?.offset ?: 0
        def sortBy = ['correct', 'wrong', 'total', 'averageTimeSeconds', 'totalTimeSeconds', 'fractionCorrect'].contains(params?.sortBy) ? params.sortBy : 'correct'
        def orderBy = ['desc', 'asc'].contains(params?.orderBy) ? params.orderBy : 'desc'
        def leaderBoard = Statistics.withCriteria(max: max, offset: offset){
            order(sortBy, orderBy)
        }
        respond leaderBoard
    }

    def show() {
        respond springSecurityService.currentUser.statistics
    }

    @Secured(['ROLE_ADMIN'])
    def save(Statistics statistics) {
        if (statistics == null) {
            render status: NOT_FOUND
            return
        }

        try {
            statisticsService.save(statistics)
        } catch (ValidationException e) {
            respond statistics.errors, view:'create'
            return
        }

        respond statistics, [status: CREATED, view:"show"]
    }

    @Secured(['ROLE_ADMIN'])
    def update(Statistics statistics) {
        if (statistics == null) {
            render status: NOT_FOUND
            return
        }

        try {
            statisticsService.save(statistics)
        } catch (ValidationException e) {
            respond statistics.errors, view:'edit'
            return
        }

        respond statistics, [status: OK, view:"show"]
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        statisticsService.delete(id)

        render status: NO_CONTENT
    }
}
