package kakao.blind2019

//https://programmers.co.kr/learn/courses/30/lessons/42889
class KakaoB19_2 {

    fun solution(N: Int, stages: IntArray): IntArray {
        val arr = Array(N) { intArrayOf(0,1, it+1)}
        var prev = -1
        stages.sort()
        stages.forEachIndexed { i, stage ->
            if(stage <= arr.size){
                arr[stage-1][0]++
                if(prev != stage) {
                    arr[stage-1][1] = stages.size - i
                }
            }
            prev = stage
        }
        val r = arr.sortedWith(compareBy ({ -(it[0].toDouble()/it[1].toDouble()) }, {it[2]}))
        return r.map { it[2] }.toIntArray()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = KakaoB19_2()
//            val r = s.solution(4, intArrayOf(4, 4, 4, 4, 4))
            val r = s.solution(5, intArrayOf(2, 1, 2, 6, 2, 4, 3, 3))
            println(r.contentToString())
        }
    }
}
// 걸린 시간: 78