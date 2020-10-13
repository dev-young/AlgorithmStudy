package programmers

import java.util.*

//https://programmers.co.kr/learn/courses/30/lessons/42628?language=kotlin
class Heap03 {
    fun solution(operations: Array<String>): IntArray {
        val que = PriorityQueue(kotlin.Comparator<Pair<Int, Int>> { o1, o2 ->
            -o1.second.compareTo(o2.second)
        })
        val queInv = PriorityQueue(kotlin.Comparator<Pair<Int, Int>> { o1, o2 ->
            o1.second.compareTo(o2.second)
        })

        val removed = hashSetOf<Int>()

        operations.forEachIndexed { idx, operation ->
            operation.split(" ").let {
                if (it[0] == "I") {
                    it[1].toInt().let {
                        que.add(Pair(idx, it))
                        queInv.add(Pair(idx, it))
                    }
                } else {
                    if (it[1] == "1") {
                        //최대값 삭제
                        while (que.poll().let {
                                when {
                                    it == null -> false
                                    removed.contains(it.first) -> true    //이미 삭제된경우
                                    else -> {
                                        removed.add(it.first)
                                        false   //삭제 안된 경우
                                    }
                                }
                            }) {

                        }
                    } else {
                        //최솟값 삭제
                        while (queInv.poll().let {
                                when {
                                    it == null -> false
                                    removed.contains(it.first) -> true
                                    else -> {
                                        removed.add(it.first)
                                        false
                                    }
                                }
                            }) {

                        }
                    }
                }
            }
        }

        if (que.isEmpty() || queInv.isEmpty()) return intArrayOf(0, 0)

        var max = 0
        while (que.poll().let {
                if (it == null) false
                else if (removed.contains(it.first))
                    true
                else {
                    max = it.second
                    false
                }
            }) {
        }

        var min = 0
        while (queInv.poll().let {
                if (it == null) false
                else if (removed.contains(it.first))
                    true
                else {
                    min = it.second
                    false
                }
            }) {
        }


        return intArrayOf(max, min)
    }

    /**이진탐색을 이용해 항상 정렬된 리스트 유지하는 방법*/
    fun solution2(operations: Array<String>): IntArray {
        fun findTargetIndex(arr: List<Int>, search: Int): Int {
            var low = 0
            var mid = 0
            var high = arr.size

            if(search > arr.last())
                return arr.lastIndex + 1

            while (low <= high) {
                mid = (low + high) / 2
                val target = arr[mid]
                when {
                    target < search -> {
                        low = mid + 1
                    }
                    target > search -> {
                        high = mid - 1
                    }
                    else -> {
                        return mid
                    }
                }
            }
            return mid
        }

        val sortedList = mutableListOf<Int>()
        fun addToSortedList(n: Int){
            if(sortedList.isEmpty()) sortedList.add(n)
            else
                sortedList.add(findTargetIndex(sortedList, n), n)
        }

        operations.forEachIndexed { idx, operation ->
            operation.split(" ").let {
                if (it[0] == "I") {
                    it[1].toInt().let {
                        addToSortedList(it)
                    }
                } else {
                    if (it[1] == "1") {
                        //최대값 삭제
                        if(sortedList.isNotEmpty()) sortedList.removeAt(sortedList.lastIndex)
                    } else {
                        //최솟값 삭제
                        if(sortedList.isNotEmpty()) sortedList.removeAt(0)
                    }
                }
            }
            println(sortedList)
        }

        println(sortedList)

        return if(sortedList.isEmpty()) intArrayOf(0, 0)
        else intArrayOf(sortedList.last(), sortedList.first())

    }


    /**깔끔한 방식*/
    fun solution3(operations: Array<String>): IntArray {
        val pq1 = PriorityQueue<Int>()                              //작은거
        val pq2 = PriorityQueue<Int>(Collections.reverseOrder())    //큰거

        operations.asSequence().map { it.split(" ") }.forEach {
            if (it[0] == "I")
                it[1].toInt().let { value ->
                    pq1.add(value)
                    pq2.add(value)
                }
            else
                if (pq1.isNotEmpty()) {
                    it[1].toInt().let { value ->
                        if (value == 1) pq1.remove(pq2.poll())
                        else pq2.remove(pq1.poll())
                    }
                }
        }

        return if (pq1.isNotEmpty()) {
            intArrayOf(pq2.poll(), pq1.poll())
        } else {
            intArrayOf(0, 0)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Heap03()
//            val r = s.solution(arrayOf("I 7", "I -5", "I 5", "D -1"))
            val r = s.solution(arrayOf("I 16", "I -5643", "D -1", "D 1", "D 1", "I 7", "D -1"))
            val r2 = s.solution2(arrayOf("I 16", "I 5643","I 8", "I 16", "I 5643", "D -1", "D 1", "D 1", "I 7", "D -1"))
//            println(r.contentToString())
            println(r2.contentToString())
        }
    }
}

// 걸린 시간: 120분