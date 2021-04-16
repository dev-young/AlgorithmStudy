package kakao.blind2021

class Blind0912_2 {
    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        var answer = arrayListOf<String>()
        val menu = hashSetOf<Char>().apply {
            orders.forEach {
                it.forEach {
                    add(it)
                }
            }
        }.toList().sorted().joinToString("")

        println(menu)
        println(orders.reduce { acc, s -> acc+s }.toCharArray().distinct().sorted().joinToString(""))

        fun combination(
                arr: String,
                r: Int,
                result: ArrayList<String>,
                start: Int = 0,
                n: Int = arr.length,
                visited: BooleanArray = BooleanArray(arr.length)
        ) {
            if (r == 0) {
                var temp = ""
                visited.forEachIndexed { index, b ->
                    if (b) {
                        temp += arr[index]
                    }
                }
                result.add(temp)
                return
            }
            for (i in start until n) {
                visited[i] = true
                combination(arr, r - 1, result, i + 1, n, visited)
                visited[i] = false
            }
        }

        course.forEach {
            val result = arrayListOf<String>()
            combination(menu, it, result)
            val maxList = arrayListOf<String>()
            var maxCount = 2
            for (r in result) {
                var n = 0
                out@ for (order in orders) {
                    if (order.length >= r.length) {
                        for (c in r) {
                            if (!order.contains(c)) {
                                continue@out
                            }
                        }
                        n++
                    }
                }
                if (n >= maxCount) {
                    if (n > maxCount) maxList.clear()
                    maxList.add(r)
                    maxCount = n
                }
            }
            answer.addAll(maxList)
        }

        return answer.sorted().toTypedArray()
    }

    //다르게 풀어봄
    fun solution2(orders: Array<String>, course: IntArray): Array<String> {
        var answer = arrayListOf<String>()
        val menu = hashSetOf<Char>().apply {
            orders.forEach {
                it.forEach {
                    add(it)
                }
            }
        }.toList().sorted().joinToString("")

        fun combination(
                arr: String,
                r: Int,
                result: ArrayList<String>,
                start: Int = 0,
                n: Int = arr.length,
                visited: BooleanArray = BooleanArray(arr.length)
        ) {
            if (r == 0) {
                var temp = ""
                visited.forEachIndexed { index, b ->
                    if (b) {
                        temp += arr[index]
                    }
                }
                result.add(temp)
                return
            }
            for (i in start until n) {
                visited[i] = true
                combination(arr, r - 1, result, i + 1, n, visited)
                visited[i] = false
            }
        }



        course.forEach {
            val result = arrayListOf<String>()
            combination(menu, it, result)
            val maxList = arrayListOf<String>()
            var maxCount = 2
            for (r in result) {
                var n = 0
                out@ for (order in orders) {
                    if (order.length >= r.length) {
                        for (c in r) {
                            if (!order.contains(c)) {
                                continue@out
                            }
                        }
                        n++
                    }
                }
                if (n >= maxCount) {
                    if (n > maxCount) maxList.clear()
                    maxList.add(r)
                    maxCount = n
                }
            }
            answer.addAll(maxList)
        }

        return answer.sorted().toTypedArray()
    }

}

fun main() {
    val s = Blind0912_2()
//    val r = s.solution(arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"), intArrayOf(2, 3,4))
//    val r = s.solution(arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"), intArrayOf(2, 3,4))
    val r = s.solution(arrayOf("ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"), intArrayOf(2, 3, 5))
//    val r = s.solution(arrayOf("ABC", "AC", "ACD", "ACD"), intArrayOf(2,3))
    println(r.contentDeepToString())
}
// 소요시간(분): 48