package kakao.winterIntern2019

//https://programmers.co.kr/learn/courses/30/lessons/64062?language=kotlin
class Kakao2019_5 {

    fun solution(stones: IntArray, k: Int): Int {
        fun check(n:Int):Boolean {
            var empty = 0
            for (i in stones.indices) {
                if(stones[i]-n+1 > 0)
                    empty = 0
                else
                    empty++
                if(empty == k)
                    return false
            }
            return true
        }

        var low = 1
        var high = 200000000
        var mid = (low + high) / 2

        while (low < high) {
            if (!check(mid)) {
                high = mid - 1
            } else {
                if(!check(mid+1))
                    break
                low = mid + 1
            }
            mid = (low + high) / 2
        }
        return mid
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2019_5()
            val r = s.solution(intArrayOf(2, 4, 5, 3, 2, 1, 4, 2, 5, 1), 3)
//            val r = s.solution(intArrayOf(1), 1)
            println(r)
        }
    }
}
// 걸린 시간(분): 29