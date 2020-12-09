package kakao.blind2020

//https://programmers.co.kr/learn/courses/30/lessons/60062?language=kotlin
class Blind_6 {

    fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
        dist.sortDescending()

        val weakLists = arrayListOf<List<Int>>()
        for (i in weak.indices) {
            val list = ArrayList<Int>()
            for (i in i until i + weak.size) {
                if (i < weak.size) list.add(weak[i])
                else list.add(weak[i % weak.size] + n)
            }
            weakLists.add(list)
        }

        dist.fold(arrayListOf<Int>(), { acc, i ->
            acc.add(i)

            val result = arrayListOf<IntArray>()
            permutation(acc, acc.size, result)

            for (currentDist in result) {
                for (weakList in weakLists) {
                    val deletedWeak = hashSetOf<Int>()
                    var startWeak = 0
                    var endWeak = 0
                    for (distance in currentDist) {
                        while (endWeak < weakList.size) {
                            val d = weakList[endWeak] - weakList[startWeak]
                            if (d <= distance) {
                                deletedWeak.add(endWeak)
                                endWeak++
                            } else {
                                startWeak = endWeak
                                break
                            }
                        }
                    }
                    if (deletedWeak.size == weak.size) {
                        return currentDist.size
                    }

                }

            }
            acc
        })

        return -1
    }

    fun permutation(
        arr: List<Int>,
        r: Int,
        result: ArrayList<IntArray>,
        temp: IntArray = IntArray(r),
        current: Int = 0,
        visited: BooleanArray = BooleanArray(arr.size),
    ) {
        if (r == current) {
            result.add(temp.clone())
        } else {
            for (i in arr.indices) {
                if (!visited[i]) {
                    visited[i] = true
                    temp[current] = arr[i]
                    permutation(arr, r, result, temp, current + 1, visited)
                    visited[i] = false
                }
            }
        }
    }

    fun solution_fail(n: Int, weak: IntArray, dist: IntArray): Int {
        var answer = 0
        val weakList = weak.toMutableList()

        dist.sortDescending()
        for (maxDistance in dist) {
            var minLeftDistance = n //남은 취약지점들의 거리가 가장 짧을때의 수치
            var deletedWeakPoint: List<Int>? = null
            answer++
            for (idx in weakList.indices) {
                val weakPoint = weakList[idx]

                val del = mutableListOf<Int>()
                for (i in weakList.indices) {
                    val target = weakList[(idx + i) % weakList.size]
                    val d = (target - weakPoint).let { if (it < 0) it + n else it }
                    if (d > maxDistance)
                        break
                    del.add(target)
                }
                val leftWeak = LinkedHashSet(weakList)
                del.reversed().forEach {
                    leftWeak.remove(it)
                }

                if (leftWeak.isEmpty())
                    return answer

                println(leftWeak)
                //min 구하기
                var min = if (leftWeak.size > 1) {
                    (leftWeak.first() - leftWeak.last()).let {
                        if (it < 0)
                            -it
                        else n - it
                    }
                } else 0
                leftWeak.toList().let {
                    for (i in 0 until it.size - 2) {
                        val d = n + it[i + 1] - it[i]
                        if (d < min)
                            min = d
                    }
                }

                if (min < minLeftDistance) {
                    minLeftDistance = min
                    deletedWeakPoint = del
                }
            }

            deletedWeakPoint?.forEach {
                weakList.remove(it)
            }
        }

        return if (weakList.isNotEmpty()) -1 else answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Blind_6()
//            println(s.solutionTest(12, intArrayOf(1, 5, 6, 10), intArrayOf(1, 2, 3, 4)))
            println(s.solution(100, intArrayOf(1, 5, 9, 12, 13, 20, 24, 28, 92), intArrayOf(1, 1, 8, 13)))
//            println(s.solution(200, intArrayOf(0, 10, 50, 80, 120, 160), intArrayOf(1, 10, 5, 40, 30)))
//            println(s.solution(30, intArrayOf(0,3,11,21), intArrayOf(10,4)))
//            println(s.solution(12, intArrayOf(1, 3, 4, 9, 10), intArrayOf(1, 1, 1)))

        }
    }

}