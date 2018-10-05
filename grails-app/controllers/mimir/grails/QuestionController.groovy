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
        } else if(params?.type && question.type != params?.type) {
            forward(action: 'save')
        } else {
            respond question, [status: OK]
        }
    }

    // POST new question
    def save() {
        def type = params?.type ?: 'NORMAL'
        type = request.JSON?.type ?: type
        springSecurityService.currentUser?.question?.delete()
        new Question(
            user: springSecurityService.currentUser,
            type: type,
            attempts: 0
        ).save(flush: true, failOnError: true)
        Question question = springSecurityService.currentUser?.question
        if(params?.test == 'true') {
            gameService.generateQuestion(question, true)
        } else {
            gameService.generateQuestion(question)
        }
        respond question, [status: CREATED, view: "show"]

    }

    // POST answer
    def answer() {
        Question question = springSecurityService.currentUser?.question
        if(question?.answer?.id?.toString() == params?.id ) {
            gameService.win(question, springSecurityService.currentUser)
            def res = [message: "correct"]
            render res as JSON
        } else if(question?.choices*.id.toString().contains(params?.id)){
            gameService.lose(question, springSecurityService.currentUser)
            def res = [message: "wrong"]
            render res as JSON
        } else {
            def res = [message: "invalid"]
            render res as JSON
        }
    }
}
