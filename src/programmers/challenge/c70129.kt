package programmers.challenge


//https://programmers.co.kr/learn/courses/30/lessons/70129
//level1
class c70129 {
    fun solution(str: String): IntArray {

        var zeroC = 0
        fun toBinary(s:String): String {
            var ss = ""
            s.forEach {
                if (it == '0') {
                    zeroC++
                } else ss += it
            }
            return ss.length.toString(2)
        }
        var s = str
        var tryCount = 0
        while (s != "1") {
            s = toBinary(s)
            tryCount++
        }
        return intArrayOf(tryCount, zeroC)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = c70129()
//            println(s.solution(arrayOf(intArrayOf(1, 1, 1, 1, 1, 1, 1, 1), intArrayOf(0, 1, 1, 1, 1, 1, 1, 1), intArrayOf(0, 0, 0, 0, 1, 1, 1, 1), intArrayOf(0, 1, 0, 0, 1, 1, 1, 1), intArrayOf(0, 0, 0, 0, 0, 0, 1, 1), intArrayOf(0, 0, 0, 0, 0, 0, 0, 1), intArrayOf(0, 0, 0, 0, 1, 0, 0, 1), intArrayOf(0, 0, 0, 0, 1, 1, 1, 1))).contentToString())
//            println(s.solution(arrayOf(intArrayOf(1,1,0,0),intArrayOf(1,0,0,0),intArrayOf(1,0,0,1),intArrayOf(1,1,1,1))).contentToString())

        }
    }
}
// 걸린 시간: 12분
