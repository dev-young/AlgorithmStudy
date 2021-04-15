package programmers.challenge


//https://programmers.co.kr/learn/courses/30/lessons/68936
//level2
class c68936 {
    fun solution(arr: Array<IntArray>): IntArray {
        var c0 = 0
        var c1 = 0

        arr.forEach {
            it.forEach {
                if (it == 0) c0++
                else c1++
            }
        }

        var s = 1
        while (s < arr.size){
            println(s)
            var i = 0
            var j = 0

            while (i+s < arr.size && j+s < arr.size) {
                if(arr[i][j] != 2 && arr[i][j] == arr[i][j+s] && arr[i][j] == arr[i+s][j] && arr[i][j] == arr[i+s][j+s]){
                    if(arr[i][j] == 0) c0 -= 3
                    else c1 -=3
                } else {
                    arr[i][j] = 2
                }
                j += s + s
                if(j == arr.size) {
                    j = 0
                    i += s + s
                }
            }
            s *= 2
        }
        return intArrayOf(c0, c1)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = c68936()
            println(s.solution(arrayOf(intArrayOf(1, 1, 1, 1, 1, 1, 1, 1), intArrayOf(0, 1, 1, 1, 1, 1, 1, 1), intArrayOf(0, 0, 0, 0, 1, 1, 1, 1), intArrayOf(0, 1, 0, 0, 1, 1, 1, 1), intArrayOf(0, 0, 0, 0, 0, 0, 1, 1), intArrayOf(0, 0, 0, 0, 0, 0, 0, 1), intArrayOf(0, 0, 0, 0, 1, 0, 0, 1), intArrayOf(0, 0, 0, 0, 1, 1, 1, 1))).contentToString())
//            println(s.solution(arrayOf(intArrayOf(1,1,0,0),intArrayOf(1,0,0,0),intArrayOf(1,0,0,1),intArrayOf(1,1,1,1))).contentToString())

        }
    }
}
// 걸린 시간: 60분
