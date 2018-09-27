package mimir.grails

class Question {
    User user
    String type
    Integer attempts
    Profile answer
    Date dateCreated

    static hasMany = [
        choices: Profile
    ]

    static constraints = {
        type inList: ['NORMAL', 'REVERSE', 'MAT']
    }

    static mapping = {
        type defaultValue: "'NORMAL'"
        attempts defaultValue: '0'
        dateCreated defaultValue: 'CURRENT_TIMESTAMP'
    }
}
