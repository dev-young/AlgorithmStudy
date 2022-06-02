package algorithm.korbit

class Solution1 {

    fun solution(arr: IntArray, n: Int): Boolean {
        val set = arr.toSet()
        arr.forEach {
            val other = n - it
            if (set.contains(other) && it != other) return true
        }
        return false
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution1()
            val r = s.solution(intArrayOf(5, 3, 9, 13), 7)
            println(r)
        }
    }
}
// 걸린 시간(분): 18