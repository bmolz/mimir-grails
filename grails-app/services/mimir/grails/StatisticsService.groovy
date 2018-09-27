package mimir.grails

import grails.gorm.services.Service

@Service(Statistics)
interface StatisticsService {

    Statistics get(Serializable id)

    List<Statistics> list(Map args)

    Long count()

    void delete(Serializable id)

    Statistics save(Statistics statistics)

}