package kakao.intern2021

class Kakao2021_2 {
    fun solution(places: Array<Array<String>>): IntArray {
        var answer: IntArray = intArrayOf()
        places.forEach { println(it) }
        return answer
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2021_2()
            println(s.solution(arrayOf(arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"), arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"), arrayOf("PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"), arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"), arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"))))
        }
    }
}
// 걸린 시간(분):