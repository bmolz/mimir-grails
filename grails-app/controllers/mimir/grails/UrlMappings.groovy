package mimir.grails

class UrlMappings {

    static mappings = {

        "/profile"(resources:'profile') {
            "/social"(resources:'socialLink')
            "/picture"(resources:'picture')
        }

        "/question"(resource:'question') {
            "/choices"(resources:'choices')
        }

        "/statistics"(resource:'statistics')
        "/import"(resource:'importProfilesURL')

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
