package kakao.blind2021

class Blind0912_7 {

    /**내가 푼건 시간과 공간복잡도가 너무 크다... */
    fun solution(sales: IntArray, links: Array<IntArray>): Int {
        val parentSet = hashSetOf<Int>()
        val pMap = hashMapOf<Int, Int>()
        links.forEach {
            parentSet.add(it[0])
            pMap[it[1]] = it[0]
        }

        var minSum = parentSet.sumBy { sales[it - 1] }.toLong()

        val visited = hashSetOf<Int>()

        fun powerSet(n: Int, idx: Int, sum: Long = 0) {
            if (sum >= minSum) return
            if (parentSet.size < visited.size)
                return

            if (idx == n) {
                val temp = mutableListOf<Int>()
                visited.forEach {
                    temp.add(it + 1)
                }
                print(temp)
                println(" $sum")
                val tSet = hashSetOf<Int>()
                for (i in temp) {
                    pMap[i]?.let { tSet.add(it) }
                    if (parentSet.contains(i)) tSet.add(i)
                    if (tSet.size == parentSet.size) {
                        minSum = sum
                        break
                    }
                }
                return
            }
            visited.add(idx)
            powerSet(n, idx + 1, sum + sales[idx])
            visited.remove(idx)
            powerSet(n, idx + 1, sum)
        }

        powerSet(sales.size, 0)

        return minSum.toInt()
    }
}

fun main() {
    val s = Blind0912_7()
//    val r = s.solution(intArrayOf(5, 6, 5, 3, 4),
//            arrayOf(intArrayOf(2, 3), intArrayOf(1, 4), intArrayOf(2, 5), intArrayOf(1, 2))
//    )
    val r = s.solution(intArrayOf(14, 17, 15, 18, 19, 14, 13, 16, 28, 17),
            arrayOf(intArrayOf(10, 8), intArrayOf(1, 9), intArrayOf(9, 7), intArrayOf(5, 4), intArrayOf(1, 5), intArrayOf(5, 10), intArrayOf(10, 6), intArrayOf(1, 3), intArrayOf(10, 2))
    )
    println(r)
}