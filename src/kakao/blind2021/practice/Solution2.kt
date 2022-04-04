package kakao.blind2021.practice

class Solution2 {
    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val answer = arrayListOf<String>()
        val result = hashMapOf<String, Int>()
        val rangeSet = course.toSet()
        orders.forEach {
            val comb = powerSet(it.toList().toTypedArray(), rangeSet).map { it.sorted().joinToString("") }
            comb.forEach { k ->
                result[k] = (result[k]?:0) + 1
            }
        }

        result.keys.groupBy { it.length }.values.forEach { keys ->
            val set = hashSetOf<String>()
            var max = 2
            for (key in keys) {
                val count = result[key]!!
                if (count < max) continue
                if (count > max) {
                    set.clear()
                    max = count
                }
                set.add(key)
            }
            answer.addAll(set)
        }

        return answer.sorted().toTypedArray()
    }

    /**부분집합 구하기*/
    fun <T>powerSet(arr: Array<T>, range:Set<Int>? = null): MutableList<List<T>> {
        val result = mutableListOf<List<T>>()
        fun powerSet(arr: Array<T>, visited: BooleanArray, n: Int, idx: Int) {
            if (idx == n) {
                val temp = mutableListOf<T>()
                visited.forEachIndexed { index, b ->
                    if(b) temp.add(arr[index])
                }
                if (range == null || range.contains(temp.size))
                    result.add(temp.toList())
                return
            }
            visited[idx] = false
            powerSet(arr, visited, n, idx + 1)
            visited[idx] = true
            powerSet(arr, visited, n, idx + 1)
        }

        powerSet(arr, BooleanArray(arr.size), arr.size, 0)
        return result
    }

    fun withBit(arr:CharArray, range:Set<Int>? = null) : ArrayList<List<Char>>{
        val n = arr.size
        val res = arrayListOf<List<Char>>()
        for (i in 0 until (1 shl n)) {
            val temp = arrayListOf<Char>()
            for (j in 0 until n) {
                if (i and (1 shl j) != 0) {
                    temp.add(arr[j])
                }
            }
            if (range == null || range.contains(temp.size))
                res.add(temp.toList())
        }
        return res
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution2()
            println(
                    s.solution(
                            arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"),
                            intArrayOf(2,3,4)
                    ).toList().toString()
            )

        }
    }
}
// 걸린 시간(분): 50