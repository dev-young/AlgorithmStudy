package bucketplace

class Solution2 {
    fun solution(call: String): String {
        val map = hashMapOf<String, Int>()

        for (i in call.indices) {
            for (j in i+1..call.length) {
                val k = call.substring(i, j).lowercase()
                map[k] = (map[k] ?: 0) + 1
            }
        }
        val target = arrayListOf<String>()
        var max = 0
        map.forEach { t, u ->
            if (max < u) {
                max = u
                target.clear()
                target.add(t)
            } else if (max == u) {
                target.add(t)
            }
        }

        var answer = call
        target.forEach {
            answer = answer.replace(it, "", true)
        }

        return answer
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution2()
            val r = s.solution("abxdeydeabz")
            println(r)
        }
    }
}
// 걸린 시간(분): 풀이 한번 보고 품