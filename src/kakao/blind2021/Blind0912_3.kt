package kakao.blind2021

class Blind0912_3 {
    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val answer: IntArray = IntArray(query.size)
        val infoArr: Array<Array<String>> = Array(info.size) { arrayOf("", "", "", "", "") }
        val queryArr: Array<Array<String>> = Array(query.size) { arrayOf("", "", "", "", "") }
        info.forEachIndexed { index, s ->
            infoArr[index] = s.split(' ').toTypedArray()
        }

        query.forEachIndexed { index, s ->
            val sp = s.split(" and ")
            val last = sp[3].split(' ')
            queryArr[index] = arrayOf(sp[0], sp[1], sp[2], last[0], last[1])
        }




        infoArr.sortWith(Comparator { o1, o2 ->
            o1[4]!!.toInt().compareTo(o2[4]!!.toInt())
        })

        val keyMap = hashMapOf<String, HashSet<Int>>()
        infoArr.forEachIndexed { index, arr ->
            arr.forEach {
                if(keyMap[it] == null) {
                    keyMap[it] = hashSetOf(index)
                } else {
                    keyMap[it]?.add(index)
                }
            }

        }
        infoArr.forEach { println(it.contentToString()) }
        queryArr.forEach { println(it.contentToString()) }
        keyMap.forEach { t, u ->
            println("$t, $u")
        }

//        println(binarySearch(infoArr, 0) )
//        println(binarySearch(infoArr, 80) )
//        println(binarySearch(infoArr, 100) )
//        println(binarySearch(infoArr, 270) )


        queryArr.forEachIndexed { index, conditions ->
            var result = 0

            val score = conditions[4].toInt()
            val startIdx = binarySearch(infoArr, score)

            //특정 점수 이상 애들 가져와서 수행
            loop@for(i in startIdx..infoArr.lastIndex) {
                val infos = infoArr[i]
                var isOk = true
                for(j in 0 .. 3) {
                    val condition = conditions[j]
                    if(condition == "-") {
                        continue
                    } else if(keyMap[condition] == null || !keyMap[condition]!!.contains(i)) {
                        isOk = false
                        break
                    }
                }
                if(isOk)
                    result++

            }


            answer[index] = result
        }



        return answer
    }

    fun binarySearch(arr: Array<Array<String>>, search: Int): Int {
        var low = 0
        var mid = 0
        var high = arr.size

        while (low <= high) {
            mid = (low + high) / 2
            val target = arr[mid][4].toInt()
            if (target < search) {
                low = mid + 1
            } else if (target > search) {
                high = mid - 1
            } else {
                while (arr[mid-1][4] == arr[mid][4]){
                    mid--
                }
                return mid
            }
        }
        return mid
    }

}

fun main() {
    val s = Blind0912_3()
    val r = s.solution(
        arrayOf(
            "java backend junior pizza 150",
            "python frontend senior chicken 210",
            "python frontend senior chicken 150",
            "cpp backend senior pizza 260",
            "java backend junior chicken 80",
            "python backend senior chicken 50"
        ),
        arrayOf(
            "- and backend and senior and - 150",
            "- and - and - and chicken 100",
            "- and - and - and - 150"
        )
    )
    println(r.contentToString())
}