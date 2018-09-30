package mimir.grails

class Question {
    String type
    Integer attempts
    Profile answer
    Date dateCreated

    static hasMany = [
        choices: Profile
    ]

    static belongsTo = [
        user:User
    ]

    static constraints = {
        type nullable: true, inList: ['NORMAL', 'REVERSE', 'MAT']
        attempts nullable: true
        answer nullable: true
    }

    static mapping = {
        type defaultValue: "'NORMAL'"
        attempts defaultValue: '0'
        dateCreated defaultValue: 'CURRENT_TIMESTAMP'
    }
}
