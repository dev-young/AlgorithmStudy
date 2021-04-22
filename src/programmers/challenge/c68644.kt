package programmers.challenge


//https://programmers.co.kr/learn/courses/30/lessons/68644
//level1
class c68644 {
    fun solution(arr: IntArray): IntArray {
        var answer = arrayOf<Int>()
        val result = hashSetOf<Int>()
        val n = arr.size
        val visited = BooleanArray(n) { false }
        fun comb(r:Int, start:Int){
            if(r==n) {
                var sum = 0
                visited.forEachIndexed { i, it ->
                    if(it) {
                        sum += arr[i]
                    }
                }
                result.add(sum)
                return
            }

            for (i in start until n) {
                visited[i] = true
                comb(r, i+1)
                visited[i] = false
            }
        }

        comb(2, 0)
        return result.toList().sorted().toIntArray()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = c68644()
//            println(s.solution(arrayOf(intArrayOf(1, 1, 1, 1, 1, 1, 1, 1), intArrayOf(0, 1, 1, 1, 1, 1, 1, 1), intArrayOf(0, 0, 0, 0, 1, 1, 1, 1), intArrayOf(0, 1, 0, 0, 1, 1, 1, 1), intArrayOf(0, 0, 0, 0, 0, 0, 1, 1), intArrayOf(0, 0, 0, 0, 0, 0, 0, 1), intArrayOf(0, 0, 0, 0, 1, 0, 0, 1), intArrayOf(0, 0, 0, 0, 1, 1, 1, 1))).contentToString())
//            println(s.solution(arrayOf(intArrayOf(1,1,0,0),intArrayOf(1,0,0,0),intArrayOf(1,0,0,1),intArrayOf(1,1,1,1))).contentToString())

        }
    }
}
// 걸린 시간: 16분
