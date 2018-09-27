package mimir.grails

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class StatisticsController {

    StatisticsService statisticsService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond statisticsService.list(params), model:[statisticsCount: statisticsService.count()]
    }

    def show(Long id) {
        respond statisticsService.get(id)
    }

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

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        statisticsService.delete(id)

        render status: NO_CONTENT
    }
}
