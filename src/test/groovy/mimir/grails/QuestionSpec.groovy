package mimir.grails

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import spock.lang.Ignore

@Ignore
class QuestionSpec extends Specification implements DomainUnitTest<Question> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
