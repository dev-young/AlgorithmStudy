package kakao.winterIntern2019

//https://programmers.co.kr/learn/courses/30/lessons/64062?language=kotlin
class Kakao2019_5 {

    fun solution(stones: IntArray, k: Int): Int {
        fun isAvailable(n: Int): Boolean {
            val d = n - 1
            var term = 0
            for (s in stones) {
                val stone = s - d

                if (stone > 0) term = 0
                else term++

                if (term == k)
                    return false
            }
            return true
        }

        var low = 1
        var high = 200000000
        var mid = 0
        var lastR = false
        while (low < high) {
            mid = (low + high) / 2
            lastR = isAvailable(mid)
            if (lastR) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }

        if(lastR) {
            while (isAvailable(mid+1)){
                mid++
            }
        } else {
            while (!isAvailable(--mid)){}
        }
        return if(mid<1) 1
        else mid
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
// 걸린 시간: 122분