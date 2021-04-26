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


    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val powerSet = PowerSet()
            val arr = intArrayOf(1, 2, 3)
            powerSet.powerSet(arr).forEach { println(it.contentToString()) }
            println()
            powerSet.powerSet(4).forEach { println(it.contentToString()) }
            println()
            powerSet.withBit(arr).forEach { println(it.contentToString()) }


        }
    }
}