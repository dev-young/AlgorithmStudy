package kakao.blind2020

class Blind_5 {
    fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
        var answer = hashMapOf<String, IntArray>()
        val PILLAR = 0  //기둥
        val TENT = 1    //보
        val BUILD = 1   //설치

        fun getKey(x: Int, y: Int, type:Int) = "${x},${y},$type"
        fun getKey(array: IntArray) = getKey(array[0], array[1], array[2])

        fun updateAnswer(array: IntArray) {
            if(array[3] == BUILD)
                answer[getKey(array)] = intArrayOf(array[0], array[1], array[2])
            else
                answer.remove(getKey(array))
        }

        //해당 위치에 존재할 수 있는지 여부 반환
        fun isAvailable(x: Int, y: Int, type: Int): Boolean {
            //설치하는 경우
            if (type == PILLAR) {   //기둥
                if (y == 0
                    || answer[getKey(x, y - 1, PILLAR)] != null
                    || answer[getKey(x - 1, y, TENT)] != null
                    || answer[getKey(x, y, TENT)] != null
                ) {
                    return true
                }
            } else {    //보
                val keyBottom = getKey(x, y - 1, PILLAR)    //아래 좌표 키
                val keyBottomRight = getKey(x + 1, y - 1, PILLAR)    //오른쪽 아래 좌표 키
                val keyLeft = getKey(x - 1, y, TENT)    //왼쪽 좌표 키
                val keyRight = getKey(x + 1, y, TENT)    //오른쪽 좌표 키
                if (answer[keyBottom] != null
                    || answer[keyBottomRight] != null
                    || ( answer[keyLeft] != null && answer[keyRight] != null )
                ) {
                    return true
                }
            }
            return false
        }

        fun isAvailable(build: IntArray): Boolean {
            val x = build[0]
            val y = build[1]
            val type = build[2]

            if (build[3] == BUILD) {
                return isAvailable(x, y, type)
            } else {
                //철거하는 경우 일단 제거후 연결된 각 구조물마다 존재 가능 여부를 확인한다.
                updateAnswer(build)

                answer.values.forEach {
                    if(!isAvailable(it[0], it[1], it[2])){
                        build[3] = BUILD
                        updateAnswer(build)
                        return false
                    }
                }
                return true
            }
        }



        build_frame.forEach {
            if(isAvailable(it)){
                updateAnswer(it)
            }

        }

//        println(answer.values.toTypedArray().contentDeepToString())
        return answer.values.sortedWith(Comparator { o1, o2 ->
            o1[0].compareTo(o2[0]).let { if (it == 0) o1[1].compareTo(o2[1]).let { if (it == 0) o1[2].compareTo(o2[2]) else it } else it }
        }).toTypedArray()
    }

}

fun main() {
    val s = Blind_5()
    val ints = arrayOf(
        intArrayOf(0, 0, 0, 1),
        intArrayOf(2, 0, 0, 1),
        intArrayOf(4, 0, 0, 1),
        intArrayOf(0, 1, 1, 1),
        intArrayOf(1, 1, 1, 1),
        intArrayOf(2, 1, 1, 1),
        intArrayOf(3, 1, 1, 1),
        intArrayOf(2, 0, 0, 0),
        intArrayOf(1, 1, 1, 0),
        intArrayOf(2, 2, 0, 1)
    )
//    println(s.solution(
//        5, arrayOf(
//            intArrayOf(1, 0, 0, 1),
//            intArrayOf(1, 1, 1, 1),
//            intArrayOf(2, 1, 0, 1),
//            intArrayOf(2, 2, 1, 1),
//            intArrayOf(5, 0, 0, 1),
//            intArrayOf(5, 1, 0, 1),
//            intArrayOf(4, 2, 1, 1),
//            intArrayOf(3, 2, 1, 1)
//        )
//    ).contentDeepToString())

    println(s.solution(5, ints).contentDeepToString())
}