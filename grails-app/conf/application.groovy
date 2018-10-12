

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'mimir.grails.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'mimir.grails.UserRole'
grails.plugin.springsecurity.authority.className = 'mimir.grails.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
    [pattern: '/**',               access: ['permitAll']],
]

grails.plugin.springsecurity.filterChain.chainMap = [
    [pattern: '/**',             filters: 'none']
]
