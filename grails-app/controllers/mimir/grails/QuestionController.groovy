package mimir.grails

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON


@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class QuestionController {

    QuestionService questionService
    GameService gameService
    def springSecurityService

    static allowedMethods = [show: "GET", save: "POST", answer: "POST"]

    // GET question
    def show() {
        def question = springSecurityService.currentUser?.question
        if (!question) {
            forward(action: 'save')
        } else {
            respond question, [status: OK]
        }
    }

    // POST new question
    def save() {
        def type = request.JSON?.type ?: 'NORMAL'
        springSecurityService.currentUser?.question?.delete()
        new Question(
            user: springSecurityService.currentUser,
            type: type,
            attempts: 0
        ).save(flush: true, failOnError: true)
        Question question = springSecurityService.currentUser?.question
        gameService.generateQuestion(question)
        respond question, [status: CREATED, view: "show"]

    }

    // POST answer
    def answer() {
        Question question = springSecurityService.currentUser?.question
        if(question?.answer?.id?.toString() == params?.id ) {
            gameService.win(question, springSecurityService.currentUser)
            def res = [message: true]
            render res as JSON
        } else if(question?.choices*.id.toString().contains(params?.id)){
            gameService.lose(question, springSecurityService.currentUser)
            def res = [message: false]
            render res as JSON
        } else {
            render status: NOT_FOUND
        }
    }
}
