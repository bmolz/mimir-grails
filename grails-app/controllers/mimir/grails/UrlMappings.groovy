package mimir.grails

class UrlMappings {

    static mappings = {

        "/$controller/$action?/$id?(.$format)?"{}

        "/api/profile(.$format)?"(resources:'profile') {
            "/social"(resources:'socialLink')
            "/picture"(resources:'picture')
        }

        "/api/question(.$format)?"(controller:'question', action:'show', method:'GET')
        "/api/question(.$format)?"(controller:'question', action:'save', method:'POST')
        "/api/question/answer/$id"(controller:'question', action:'answer', id:"$id", method:'POST')

        "/api/leaderboard(.$format)?"(resources:'statistics')
        "/api/import"(resource:'importProfilesURL')
//        "/login/auth/$id"(controller:'profile', action:'login', id:"$id")

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
