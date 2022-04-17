package bucketplace

class Solution1 {
    val directionMap = hashMapOf(Pair('N', 1), Pair('E', 2), Pair('S', 3), Pair('W', 4))
    fun lr(prev: Char, next: Char): String {
        val d = directionMap[next]!! - directionMap[prev]!!
        return if (d == 1 || d == -3) "right"
        else "left"
    }

    fun solution(path: String): Array<String> {
        val answer = arrayListOf<String>()
        var i = 0
        var prevDirection = path[0]
        while (i < path.length) {
            var cnt = 0 //직진 거리
            var nextDirection: Char? = null
            for (j in i..path.lastIndex) {
                if (path[i] != path[j]) {
                    nextDirection = path[j]
                    break
                }
                cnt++
            }
            nextDirection?.let {
                if (cnt < 6) {
                    answer.add("Time $i: Go straight ${cnt}00m and turn ${lr(prevDirection, it)}")
                } else {
                    answer.add("Time ${i + cnt - 5}: Go straight 500m and turn ${lr(prevDirection, it)}")
                }
                i += cnt
                prevDirection = nextDirection
            } ?: break

        }

        return answer.toTypedArray()
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution1()
            val r = s.solution("SSSSSSWWWNNNNNN")
            r.forEach {
                println(it)
            }
        }
    }
}
// 걸린 시간(분):