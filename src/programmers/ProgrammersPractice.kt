package programmers

import kotlin.system.measureNanoTime

class ProgrammersPractice {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        var answer = arrayListOf<Int>()

        val map = hashMapOf<String, Genre>()
        genres.forEachIndexed { index, s ->
            if(!map.containsKey(s))
                map[s] = Genre(s)
            map[s]!!.add(index, plays[index])
        }

        map.values.sortedByDescending { it.totalCount }.forEach {
            answer.addAll(it.getTopTow().toList())
        }

//        println(map.toString())
        return answer.toIntArray()
    }


    //개쩌는 정답이다...
    fun solution2(genres: Array<String>, plays: IntArray): IntArray{
        return genres.indices.groupBy { genres[it] }
            .toList()
            .sortedByDescending { it.second.sumBy { plays[it] } }
            .map { it.second.sortedByDescending { plays[it] }.take(2) }
            .flatten()
            .toIntArray()
    }

    data class Genre(val genre:String){
        val playList = hashMapOf<Int, Int>()
        var totalCount = 0

        fun add(index:Int, playCount:Int){
            playList[index] = playCount
            totalCount += playCount
        }

        fun getTopTow() : IntArray{
            var first = -1
            var second = -1

            playList.forEach { (idx, count) ->
                val firstCount = playList.getOrDefault(first, 0)
                val secondCount = playList.getOrDefault(second, 0)
                if(firstCount < count) {
                    second = first
                    first = idx
                } else if(firstCount == count){
                    if(idx < first){
                        second = first
                        first = idx
                    } else if(secondCount <= count){
                        second = idx
                    }
                } else if(secondCount < count){
                    second = idx
                } else if (secondCount == count){
                    if(idx < second){
                        second = idx
                    }
                }
            }

            return if(second == -1) intArrayOf(first)
            else intArrayOf(first, second)
        }

        override fun toString(): String {
            return "Genre(genre='$genre', playList=$playList)"
        }

    }

}


fun main() {
    println(measureNanoTime {

        ProgrammersPractice()
            .solution(arrayOf("classic", "pop", "classic", "pop", "classic", "classic"), intArrayOf(400, 600, 150, 2500, 500, 500))
    })

    println(measureNanoTime {
        ProgrammersPractice()
            .solution2(arrayOf("classic", "pop", "classic", "pop", "classic", "classic"), intArrayOf(400, 600, 150, 2500, 500, 500))
    })
//    val r = programmers.ProgrammersPractice().kakao.solution(arrayOf("classic", "pop", "classic", "pop", "classic", "classic"), intArrayOf(400, 600, 150, 2500, 500, 500))
//    println(r.contentToString())
}