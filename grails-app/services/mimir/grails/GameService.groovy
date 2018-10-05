package mimir.grails

import grails.gorm.transactions.Transactional
import groovy.time.TimeCategory
import java.util.Random

@Transactional
class GameService {

    def generateQuestion(Question q, boolean test = false) {
        Random rand = new Random()
        if(test) rand = new Random(123456)
        def profiles = Profile.withCriteria(){
            if(q.type=='MAT') ilike('firstName', "%Mat%")
        }
        if(q?.choices) q.choices.clear()
        def rAns = rand.nextInt((int) profiles.size)
        q.answer = profiles.get(rAns)
        def rChoices = [rAns] as Set
        def n = 0
        while (rChoices.size() < 6 && n < 100) {
            rChoices << rand.nextInt((int) profiles.size)
            n++
        }
        rChoices.each{
            q.addToChoices(profiles.get(it))
        }
        q.save()
    }

    def win(Question q, User u){
        u.question=null
        u.save()
        q.delete()

        Statistics stats = getStatistics(u)
        stats.totalTimeSeconds += TimeCategory.minus(new Date(), q.dateCreated).seconds
        stats.correct ++
        stats.total ++
        stats.fractionCorrect = stats.correct / stats.total
        stats.averageTimeSeconds = stats.totalTimeSeconds / stats.total
        stats.save()
    }

    def lose(Question q, User u){
        q.attempts ++
        q.save()
        Statistics stats = getStatistics(u)
        stats.wrong ++
        stats.total ++
        stats.fractionCorrect = stats.correct / stats.total
        stats.save()
    }

    def getStatistics(User u){
        def stats = u?.statistics
        if(!stats) {
            stats = new Statistics(
                correct: 0,
                wrong: 0,
                total: 0,
                totalTimeSeconds: 0,
                averageTimeSeconds: 0,
                fractionCorrect: 0,
                user: u
            ).save()
        }
        return stats
    }
}
