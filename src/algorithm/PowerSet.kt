package algorithm

class PowerSet {

    /**부분집합 구하기*/
    fun powerSet(arr: IntArray): MutableList<IntArray> {
        val result = mutableListOf<IntArray>()
        fun powerSet(arr: IntArray, visited: BooleanArray, n: Int, idx: Int) {
            if (idx == n) {
//            print(arr, visited, n)
                val temp = mutableListOf<Int>()
                visited.forEachIndexed { index, b ->
                    if(b) temp.add(arr[index])
                }
                result.add(temp.toIntArray())
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

    /**부분집합 구하기
     * @param range ex) 2,3,5개의 원소 구성된 부분집합 -> range = setOf(2,3,5)
     * */
    fun <T>powerSet(arr: Array<T>, range:Set<Int>? = null): MutableList<List<T>> {
        val result = mutableListOf<List<T>>()
        fun powerSet(arr: Array<T>, visited: BooleanArray, n: Int, idx: Int) {
            if (idx == n) {
//            print(arr, visited, n)
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

    fun powerSet(size:Int): MutableList<IntArray> {
        val result = mutableListOf<IntArray>()
        fun powerSet(visited: BooleanArray, n: Int, idx: Int) {
            if (idx == n) {
//            print(arr, visited, n)
                val temp = mutableListOf<Int>()
                visited.forEachIndexed { index, b ->
                    if(b) temp.add(index)
                }
                result.add(temp.toIntArray())
                return
            }
            visited[idx] = false
            powerSet(visited, n, idx + 1)
            visited[idx] = true
            powerSet(visited, n, idx + 1)
        }

        powerSet(BooleanArray(size), size, 0)
        return result
    }

    /**비트연산을 통해 부분집합 구하기*/
    fun withBit(arr:IntArray) : ArrayList<IntArray>{
        val n = arr.size
        val res = arrayListOf<IntArray>()
        for (i in 0 until (1 shl n)) {
            val temp = arrayListOf<Int>()
            for (j in 0 until n) {
                if (i and (1 shl j) != 0) {
                    temp.add(arr[j])
                }
            }
            res.add(temp.toIntArray())
        }
        return res
    }

    /**비트연산을 통해 부분집합 구하기
     * @param range 뽑힌 갯수 set   ex) 2,3,5개의 원소 구성된 조합 -> range = setOf(2,3,5)
     * */
    fun <T>withBit(arr:Array<T>, range:Set<Int>? = null) : ArrayList<List<T>>{
        val n = arr.size
        val res = arrayListOf<List<T>>()
        for (i in 0 until (1 shl n)) {
            val temp = arrayListOf<T>()
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


    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val powerSet = PowerSet()
            val arr = intArrayOf(1, 2, 3, 4,5,6,7,8,9,10)
//            arr.reverse()
//            println(powerSet.withBit(arr).size)
//            powerSet.powerSet(arr).reversed().forEach {
//                print("${it.sum()} ")
//                println(it.contentToString())
//            }
//            println()
//            powerSet.powerSet(4).forEach { println(it.contentToString()) }
//            println()
            powerSet.withBit(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).apply { reverse() }).map { Pair(it.sum(), it) }.sortedBy { it.first }.reversed().forEach { (sum, arr) ->
                print("${sum} ")
                println(arr.contentToString())
            }


        }
    }
}