package kakao.blind2021

class Blind0912_2 {
    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        var answer = arrayListOf<String>()
        val ordersSet = hashSetOf<String>().apply {
            orders.forEach {
                it.forEach { add(it.toString()) }
            }
        }

//        println(ordersSet.toString())
        val menuSet = powerSet(ordersSet.toTypedArray()).let {
            val list = arrayListOf<String>()
            val courseSet = course.toHashSet()
            it.forEach {
                if (courseSet.contains(it.length)) {
                    list.add(it)
                }
            }
            list
        }
//        println(menuSet.toString())

        val courseMap = hashMapOf<String, Int>()
        menuSet.forEach { menus ->
            orders.forEach { order ->
                var isContain = true
                kotlin.run {
                    menus.forEach { menu ->
                        if (!order.contains(menu)) {
                            isContain = false
                            return@run
                        }
                    }
                }

                if (isContain) {
                    courseMap[menus] = courseMap[menus]?.plus(1) ?: 1
                }

            }

        }
//        println(courseMap.toString())
        val pairList = courseMap.let {
            val pairs = arrayListOf<Pair<String, Int>>()
            it.forEach { (t, u) ->
                if(u > 1)
                    pairs.add(Pair(t, u))
            }
            pairs.sortWith(Comparator { o1, o2 -> -o1.second.compareTo(o2.second) })

            pairs
        }

        //Map<코스메뉴수, Pair<주문수, List<메뉴>>>
        val map = hashMapOf<Int, Pair<Int, ArrayList<String>>>()
        pairList.forEach { pair /**메뉴, 주문수*/ ->
            map[pair.first.length]?.apply {
                if(first == pair.second) {
                    //주문 수가 같은 경우 목록에 추가
                    second.add(pair.first)
                } else if(first < pair.second) {
                    //새로운 메뉴의 주문수가 더 큰 경우
                    map[pair.first.length] = Pair(pair.second, arrayListOf(pair.first))
                }
            }

            if (map[pair.first.length] == null) {
                map[pair.first.length] = Pair(pair.second, arrayListOf(pair.first))
            }
        }

//        println(pairList)
//        println(map.toString())

        map.values.forEach {
            answer.addAll(it.second)
        }
        answer.sort()
        return answer.toTypedArray()
    }

    fun powerSet(arr: Array<String>, n: Int = arr.size, idx: Int = 0): ArrayList<String> {
        val visited: BooleanArray = BooleanArray(arr.size)
        val result = arrayListOf<String>()
        fun temp(idx: Int) {
            if (idx == n) {
                var r = ""
                visited.forEachIndexed { index, b ->
                    if (b) {
                        r += arr[index]
                    }
                }
                result.add(r)

                return
            }
            visited[idx] = false
            temp(idx + 1)
            visited[idx] = true
            temp(idx + 1)
        }

        temp(idx)

        return result

    }

}

fun main() {
    val s = Blind0912_2()
//    val r = s.solution(arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"), intArrayOf(2, 3,4))
//    val r = s.solution(arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"), intArrayOf(2, 3,4))
    val r = s.solution(arrayOf("ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"), intArrayOf(2, 3,5))
//    val r = s.solution(arrayOf("ABC", "AC", "ACD", "ACD"), intArrayOf(2,3))
    println(r.contentDeepToString())
}