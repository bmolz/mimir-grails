package mimir.grails

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

import spock.lang.Ignore

@Ignore
@Integration
@Rollback
class StatisticsServiceSpec extends Specification {

    StatisticsService statisticsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Statistics(...).save(flush: true, failOnError: true)
        //new Statistics(...).save(flush: true, failOnError: true)
        //Statistics statistics = new Statistics(...).save(flush: true, failOnError: true)
        //new Statistics(...).save(flush: true, failOnError: true)
        //new Statistics(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //statistics.id
    }

    void "test get"() {
        setupData()

        expect:
        statisticsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Statistics> statisticsList = statisticsService.list(max: 2, offset: 2)

        then:
        statisticsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        statisticsService.count() == 5
    }

    void "test delete"() {
        Long statisticsId = setupData()

        expect:
        statisticsService.count() == 5

        when:
        statisticsService.delete(statisticsId)
        sessionFactory.currentSession.flush()

        then:
        statisticsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Statistics statistics = new Statistics()
        statisticsService.save(statistics)

        then:
        statistics.id != null
    }
}
