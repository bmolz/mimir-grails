package mimir.grails

import grails.gorm.services.Service

@Service(Picture)
interface PictureService {

    Picture get(Serializable id)

    List<Picture> list(Map args)

    Long count()

    void delete(Serializable id)

    Picture save(Picture picture)

}