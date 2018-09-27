package mimir.grails

class Statistics {
    User user
    Integer correct
    Integer wrong
    Integer total
    Integer totalTimeSeconds
    Integer averageTimeSeconds
    Double fractionCorrect

    static mapping = {
        correct defaultValue: '0'
        wrong defaultValue: '0'
        total defaultValue: '0'
        totalTimeSeconds defaultValue: '0'
        averageTimeSeconds defaultValue: '0'
        fractionCorrect defaultValue: '0'
    }
}
