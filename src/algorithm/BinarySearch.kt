package algorithm

class BinarySearch {

    /**해당 값 혹은 해당 값 다음으로 큰 값을 가지는 인덱스 반환
     * 해당 값이 여러개인 경우 가장 마지막 인덱스 반환
     * 다음 값이 여러개인 경우 가장 처음 인덱스 반환
     * 그냥 쓰지 말자*/
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

    /**경계의 왼쪽지점을 반환
     * 만약 입력받은 arr를 check를 통해 변환해서 [F,F,F,F,F,T,T,T,T,T]와 같은 모양인 경우 마지막 F의 인덱스 반환
     * 만약 첫번째 원소의 check 값이 동일하면 -1 반환*/
    fun findBoundary(arr: IntArray, check: (n: Int) -> Boolean): Int {
        var s = 0
        var e = arr.lastIndex
        if (check(arr.first())) return -1
        if (!check(arr.last())) return -1
        while (check(arr[s]) == check(arr[s+1])) {
            val mid = (s + e) / 2
            if (check(arr[mid])) {
                e = mid
            } else s = mid
        }
        return s
    }

    /**위 로직의 List 버전*/
    fun <T>List<T>.findBoundary(check: (n: T) -> Boolean) : Int {
        var s = 0
        var e = lastIndex
        if (check(first())) return -1
        if (!check(last())) return -1
        while (check(this[s]) == check(this[s+1])) {
            val mid = (s + e) / 2
            if (check(this[mid])) {
                e = mid
            } else
                s = mid
        }
        return s
    }

    fun searchTest() {
        val list = arrayListOf(5, 5, 5, 5, 5, 5, 5, 7, 7, 7, 8, 9)
        println(list.findBoundary { it >= 7 })
        println(list.findBoundary { it > 17 })
        println(list.findBoundary { it > 4 })
//        println(findFirst(list, 7))
//        println(findFirst(list, 10))
    }

    /**해당 값 혹은 해당 값 다음으로 작은 값을 가지는 최초의 인덱스 반환
     * 해당 값보다 작은 값이 없으면 -1 반환 */
    fun findFirst(arr: List<Int>, search: Int): Int {
        var low = 0
        var mid = 0
        var high = arr.size
        if (arr.first() > search) return -1
        if (arr.first() == search) return 0
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

    /**오름차순으로 정렬된 배열에서 최초로 search 이상의 값이 나오는 인덱스 반환
     * 배열 내에 이상인 값이 없으면 -1 반환 */
    fun findFirstBig(sortedList: List<Int>, search: Int): Int {
        var low = 0
        var high = sortedList.size
        var mid = (low + high) / 2

        if (sortedList.isEmpty() || search < sortedList.first()) return 0

        while (low < high) {
            if (sortedList[mid] < search) {
                low = mid + 1
            } else {
                if (mid > 0 && sortedList[mid - 1] < search)
                    break
                high = mid - 1
            }
            mid = (low + high) / 2
        }
        if (mid == sortedList.size) return -1
        return mid
    }

    /**오름차순으로 정렬된 배열에서 search 이하의 값들 중 제일 마지막 인덱스 반환
     * 배열 내에 이하인 값이 없으면 -1 반환 */
    fun findLastSmall(sortedList: List<Int>, search: Int): Int {
        var low = 0
        var high = sortedList.size
        var mid = (low + high) / 2

        if (search >= sortedList.last()) return sortedList.lastIndex
        if (search < sortedList.first()) return -1

        while (low < high) {
            if (sortedList[mid] > search) {
                high = mid - 1
            } else {
                if (sortedList[mid + 1] > search)
                    break
                low = mid + 1
            }
            mid = (low + high) / 2
        }
//        if (mid == sortedList.size) return -1
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
//                val list = arrayListOf(10, 40, 50, 60, 65)
//                val list = arrayListOf(10,30,30,30,40,40,40,50)
//                val list = arrayListOf(50)
//                println(findFirstBig(list, 30))
//                println(findFirstBig(list, 20))
//                println(findFirstBig(list, 10))
//                println(findFirstBig(list, 0))
//                println(findFirstBig(list, 35))
//                println(findFirstBig(list, 40))
//                println(findFirstBig(list, 60))
            }
            test.searchTest()
//            println()
//            test.addToTest()

        }
    }
}