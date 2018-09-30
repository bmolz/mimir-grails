package mimir.grails

class UrlMappings {

    static mappings = {

        "/$controller/$action?/$id?(.$format)?"{}

        "/api/profile"(resources:'profile') {
            "/social"(resources:'socialLink')
            "/picture"(resources:'picture')
        }

        "/api/question"(resource:'question') {
//            "/choices"(resources:'choices')
        }

        "/api/statistics"(resource:'statistics')
        "/api/import"(resource:'importProfilesURL')
        "/login/auth/$id"(controller:'profile', action:'login', id:"$id")

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
