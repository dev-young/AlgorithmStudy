package naver

class Line0913_5 {
    fun solution(cards: IntArray): Int {
        var answer: Int = -1
        var playerCard = arrayListOf<Int>()
        var dealerCard =  arrayListOf<Int>()
        var visibleCard = 0

        for (i in cards.indices){
            if(cards[i] > 10){
                cards[i]  = 10
            }
        }

        playerCard.add(cards[0])
        dealerCard.add(cards[1])
        playerCard.add(cards[2])
        visibleCard = cards[3]

        if(playerCard.sum() == 21) {

        } else {
            for(i in 4..cards.lastIndex) {
                if(visibleCard == 1 || visibleCard >= 7) {
                    playerCard.add(cards[i])
                }

                if(playerCard.sum() >= 17) {
                    break
                }
                if(visibleCard == 4 || visibleCard == 5 || visibleCard == 6) {
                    break
                }

                if(visibleCard == 4 || visibleCard == 5 || visibleCard == 6) {
                    break
                }
            }
        }


        return answer
    }
}

fun main() {
    val s = Line0913_5()
    val r = s.solution(intArrayOf(12, 7, 11, 6, 2, 12))
    println(r)
}