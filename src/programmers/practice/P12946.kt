package programmers.practice


//https://programmers.co.kr/learn/courses/30/lessons/12946
//하노이의 탑
//level3
class P12946 {
    fun solution(n: Int): Array<IntArray> {
        var answer = arrayOf<IntArray>()
        fun h(n:Int, cur:Int, empty:Int, des:Int){
            if(n == 1) {
                answer += intArrayOf(cur, des)
                return
            }
            h(n-1, cur, des, empty)
            answer += intArrayOf(cur, des)
            h(n-1, empty, cur, des)
        }
        h(n, 1, 2, 3)
        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = P12946()
            println(s.solution(3).contentDeepToString())
//            println(s.solution(3).contentDeepToString())

        }
    }
}
// 걸린 시간(분): 39
