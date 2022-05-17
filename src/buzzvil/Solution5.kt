package buzzvil

class Solution5 {


    fun solution(s: String): String {
        fun proc(a: CharArray): String {
            val b = StringBuilder()
            a.forEach {
                b.append(it)
                b.reverse()
            }
            return b.toString()
        }

        var answer = ""
        var max = ""
        val n = s.length
        val visited = hashSetOf<Int>()
        var flag = true
        val r = s.count { it == '1' }.let { if (it > n - it) it else {
                flag = false
                (n - it)
        } }
        val charArray = CharArray(s.length)
        fun perm(temp: IntArray = IntArray(r), current: Int = 0) {
            if (r == current) {
                val set = temp.toSet()
                for (i in 0 until n) charArray[i] = if (flag) if (set.contains(i)) '1' else '0'
                else { if (set.contains(i)) '0' else '1' }
                val b = proc(charArray)
                if (max == "") {
                    max = b
                    answer = charArray.joinToString("")
                } else {
                    val result = max.compareTo(b)
                    if (result == -1) {
                        max = b
                        answer = charArray.joinToString("")
                    }
                }

            } else {
                for (i in 0 until n) {
                    if (!visited.contains(i)) {
                        visited.add(i)
                        temp[current] = i
                        perm(temp, current + 1)
                        visited.remove(i)
                    }
                }
            }
        }
        perm()

        return answer
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution5()
            val r = s.solution("110")
            println(r)
        }
    }
}
// 걸린 시간(분):