package mimir.grails

import grails.testing.mixin.integration.Integration
import grails.transaction.Transactional

import static grails.web.http.HttpHeaders.*
import static org.springframework.http.HttpStatus.*
import spock.lang.*
import geb.spock.*
import grails.plugins.rest.client.RestBuilder

@Integration
@Transactional
class QuestionFunctionalSpec extends GebSpec {

    RestBuilder rest = new RestBuilder()

    void "Tests for Question types"() {
        when:"Get question"
        def response = rest.get("${baseUrl}/api/question")

        then:"Expect 6 choices"
        println response.json
        response.json.choices.size() == 6

        when:"Change question type to reverse"
        def responseReverse = rest.get("${baseUrl}/api/question?type=REVERSE")

        then:"Verify reverse type response is different with 6 choices"
        println responseReverse.json
        responseReverse.json.choices.size() == 6
        response.json != responseReverse.json
    }

    void "Tests verify answers to question with seed"() {
        when:"Request new question"
        def response = rest.post("${baseUrl}/api/question?test=true")

        then:"Check question name"
        println response.json
        response.json.name.contains('Ryan') == true
        response.json.name.contains('Bob') == false

        when:"Answer with wrong id"
        response = rest.post("${baseUrl}/api/question/answer/129")

        then:"Message value is wrong"
        println response.json
        response.json.message.value.toString() == 'wrong'

        when:"Answer with invalid id"
        response = rest.post("${baseUrl}/api/question/answer/100")

        then:"Message value is invalid"
        println response.json
        response.json.message.value.toString() == 'invalid'

        when:"Answer with correct id"
        response = rest.post("${baseUrl}/api/question/answer/37")

        then:"Message value is correct"
        println response.json
        response.json.message.value.toString() == 'correct'
    }

    void "Test Matt mode are all matt's"() {
        when:"Request new question"
        def response = rest.post("${baseUrl}/api/question?type=MAT")

        then:"Choices are all matt's"
        println Profile.getAll(response.json.choices*.id)*.firstName
        Profile.getAll(response.json.choices*.id)*.firstName.each{
            assert it.toLowerCase().contains('matt')
        }
    }

    void "Tests Statistics are updated correctly"() {
        when:"Answer with wrong id"

        def response = rest.post("${baseUrl}/api/question?test=true")
        response = rest.get("${baseUrl}/api/leaderboard/1")
        println response.json
        def wrongCount = response.json.wrong
        def correctCount = response.json.correct
        rest.post("${baseUrl}/api/question/answer/129")
        response = rest.get("${baseUrl}/api/leaderboard/1")

        then:"Wrong count +1"
        println response.json
        response.json.wrong == wrongCount + 1

        when:"Answer with wrong id"
        rest.post("${baseUrl}/api/question/answer/74")
        response = rest.get("${baseUrl}/api/leaderboard/1")

        then:"Wrong count +2"
        println response.json
        response.json.wrong == wrongCount + 2

        when:"Answer with correct id"
        rest.post("${baseUrl}/api/question/answer/37")
        response = rest.get("${baseUrl}/api/leaderboard/1")

        then:"Correct count +1"
        println response.json
        response.json.correct == correctCount + 1
    }

    void "Test question choices are unique"() {
        when:"Get question"
        def responses = []
        100.times {
            responses << rest.post("${baseUrl}/api/question").json.choices*.id
        }

        then:"6 unique choices"
        responses.each{
            assert it.unique().size() == 6
        }
        responses.unique().size() >= 95
        println responses
    }
}
