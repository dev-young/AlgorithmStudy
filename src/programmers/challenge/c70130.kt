package programmers.challenge


//https://programmers.co.kr/learn/courses/30/lessons/70130
//level3
class c70130 {

    /** 공통원소로 만들 수 있는 최대 길이 리턴 */
    fun check (arr: IntArray, common: Int) : Int{
        var i = 0
        val last = arr.size -1
        var length = 0
        while (i < last) {
            if(arr[i] != arr[i+1]) {
                if(arr[i] == common || arr[i+1] == common) {
                    length++
                    i++
                }
            }
            i++
        }
        return length
    }

    fun solution(a: IntArray): Int {
        var answer = 0
        if (a.size < 2) return answer

        val map = linkedMapOf<Int, Int>()
        a.forEach {
            map[it] = map.computeIfAbsent(it) { 0 } + 1
        }
        val sortedPair = map.map { Pair(it.key, it.value) }.sortedByDescending { it.second }

        for (pair in sortedPair) {
            if (pair.second > answer) {
                val r = check(a, pair.first)
                if (r > answer) answer = r
            }
        }

        return answer * 2
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = c70130()
//            println(s.solution(intArrayOf(5, 2, 3, 3, 5, 3)))
//            println(s.solution(intArrayOf(0, 3, 3, 0, 7, 2, 0, 2, 2, 0)))
            println(s.solution(intArrayOf(0,2,0,3,1,1,1,1,1,1,1,1,1,0,4)))

        }
    }
}
// 걸린 시간: 110분 간 풀고 포기하여 해설보고 다시품 40분간 다시 품
