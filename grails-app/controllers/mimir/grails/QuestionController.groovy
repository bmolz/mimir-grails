package mimir.grails

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class QuestionController {

    QuestionService questionService
    def springSecurityService

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        println 'Question Index'
        //forward(action: "show", id: question.id)
    }

    def show() {
//        def question = Question.findByUser(springSecurityService.currentUser)
        def question = Question.findByUser(springSecurityService.currentUser)
        if(!question){
            new Question(
                user: springSecurityService.currentUser,
                type: 'NORMAL'
            ).save(flush:true, failOnError: true)
            question = Question.findByUser(springSecurityService.currentUser)
        }
        println question

        respond question
    }

    def save(Question question) {
        if (question == null) {
            render status: NOT_FOUND
            return
        }

        try {
            questionService.save(question)
        } catch (ValidationException e) {
            respond question.errors, view:'create'
            return
        }

        respond question, [status: CREATED, view:"show"]
    }

    @Secured(['ROLE_ADMIN'])
    def update(Question question) {
        if (question == null) {
            render status: NOT_FOUND
            return
        }

        try {
            questionService.save(question)
        } catch (ValidationException e) {
            respond question.errors, view:'edit'
            return
        }

        respond question, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        questionService.delete(id)

        render status: NO_CONTENT
    }
}
