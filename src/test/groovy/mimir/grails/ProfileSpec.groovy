package mimir.grails

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import grails.converters.JSON
import org.springframework.test.annotation.Rollback
import spock.lang.Ignore

@Ignore
@Rollback
class ProfileSpec extends Specification implements DomainUnitTest<Profile> {

    def setup() {
        new Profile(JSON.parse(
    '''{
                
            }'''
        )).save()
    }

    def cleanup() {
    }

    void "Read Profile Name"() {
        expect:"firstName"
                Profile.get(1).firstName == ''
                Profile.count() == 1
    }
}
