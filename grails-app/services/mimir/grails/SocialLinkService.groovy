package mimir.grails

import grails.gorm.services.Service

@Service(SocialLink)
interface SocialLinkService {

    SocialLink get(Serializable id)

    List<SocialLink> list(Map args)

    Long count()

    void delete(Serializable id)

    SocialLink save(SocialLink socialLink)

}