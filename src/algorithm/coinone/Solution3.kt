package algorithm.coinone

import toArrayOfStringArray

class Solution3 {
    fun solution(queries: Array<Array<String>>): IntArray {
        val answer = IntArray(queries.size)

        fun rotate(charArray: CharArray, direction: Int) {
            //direction: 1(아래로), 2(오른쪽)
        }

        queries.forEachIndexed { index, strings ->
            var result = 0

            val a = strings[0].toCharArray()
            val b = strings[1].toCharArray()

            val set1 = setOf(0,1,2,3)
            val position = b.let {
                var i = -1
                it.forEachIndexed { index, c ->
                    if(c == a[0]) i = index
                }
                i
            }
            while (a[0] != b[0]) {

                if (set1.contains(position)) {

                }
            }
            for (i in 1..4) {

                if (a.toString() == b.toString()) result = 1
            }

            answer[index] = result
        }
        return answer
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution3()
            val r = s.solution("""[["YOWRGB", "WOYRBG"], ["YOWRGB", "WOYRGB"], ["BGORWY", "BGORWY"]]""".toArrayOfStringArray())
            println(r)
        }
    }
}
// 걸린 시간(분):