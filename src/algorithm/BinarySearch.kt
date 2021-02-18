package algorithm

class BinarySearch {

    /**해당 값 혹은 해당 값 다음으로 큰 값을 가지는 인덱스 반환 */
    fun search(arr: List<Int>, search: Int): Int {
        var low = 0
        var mid = 0
        var high = arr.size

        if (search > arr.last()) return arr.lastIndex

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
        if(mid < low)
            return mid+1
        return mid
    }

    fun searchTest() {
        val list = arrayListOf(5, 5, 5, 5, 5, 5, 5, 7, 7, 7, 8, 9)
        println(search(list, 4))
        println(search(list, 5))
        println(search(list, 6))
        println(search(list, 7))
        println(search(list, 10))
    }

    /**해당 값 혹은 해당 값 다음으로 작은 값을 가지는 최초의 인덱스 반환 */
    fun findFirst(arr: List<Int>, search: Int): Int {
        var low = 0
        var mid = 0
        var high = arr.size

        if (search > arr.last()) return arr.lastIndex

        while (low <= high) {
            mid = (low + high) / 2
            val target = arr[mid]
            if (target < search) {
                low = mid + 1
            } else if (target > search) {
                high = mid - 1
            } else {
                while (mid >= 0 && arr[mid - 1] == arr[mid]) {
                    mid--
                }
                return mid
            }
        }
        return mid
    }

    fun findFirstTest() {
        val list = arrayListOf(0, 1, 2, 3, 4, 5, 5, 5, 5, 5, 5, 7, 8, 9)
        println(findFirst(list, -1))
        println(findFirst(list, 4))
        println(findFirst(list, 5))
        println(findFirst(list, 6))
        println(findFirst(list, 7))
        println(findFirst(list, 17))
    }

    /**오름차순으로 정렬된 리스트에 새로운 값을 추가할 경우 추가된 인덱스 반환*/
    fun targetAddIndex(arr: List<Int>, search: Int): Int {
        if (arr.isEmpty() || search <= arr.first())
            return 0
        if (search >= arr.last())
            return arr.size

        var low = 0
        var mid = 0
        var high = arr.size - 1

        while (low < high) {
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

        if (arr[mid] < search)
            mid++
        return mid
    }

    fun targetAddIndexTest() {
        val list = mutableListOf<Int>()
        val arr = intArrayOf(1, 54, 6, 3, 2, 4, 2, 1, 4, 56, 7, 8, 56, 4)
//        val arr = intArrayOf(5)
        arr.forEach {
            list.add(targetAddIndex(list, it), it)
            println("$list  (+$it)")
        }
    }

    /**오름차순으로 정렬된 리스트에 값 추가*/
    fun addToSortedList(arr: MutableList<Int>, value: Int) {
        if (arr.isEmpty() || value <= arr.first())
            return arr.add(0, value)
        if (value >= arr.last()) {
            arr.add(value)
            return
        }

        var low = 0
        var mid = 0
        var high = arr.size - 1

        while (low < high) {
            mid = (low + high) / 2
            val target = arr[mid]
            when {
                target < value -> {
                    low = mid + 1
                }
                target > value -> {
                    high = mid - 1
                }
                else -> {
                    return arr.add(mid, value)
                }
            }
        }

        if (arr[mid] < value)
            mid++
        return arr.add(mid, value)
    }

    fun addToTest() {
        val list = arrayListOf<Int>()
        val arr = intArrayOf(1, 54, 6, 3, 2, 4, 2, 1, 4, 56, 7, 8, 56, 4)
        arr.forEach {
            addToSortedList(list, it)
            println("$list  (+$it)")
        }
    }

    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val test = BinarySearch().apply {
                val list = arrayListOf(10, 20, 40, 50, 60, 65)
                println(search(list, 40))
            }
//            test.searchTest()
//            println()
//            test.addToTest()

        }
    }
}