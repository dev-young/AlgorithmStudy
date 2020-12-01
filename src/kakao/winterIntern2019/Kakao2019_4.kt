package kakao.winterIntern2019

fun main() {

    val result = solution200508_1(
        100,
        longArrayOf(
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
            12,
            13,
            14,
            4,
            4,
            4,
            4,
            1
        )
    )
//    val result = kakao.solution(10, longArrayOf(1,3,4,1,3,1))
    println(result.contentToString())
}

fun solution200508_1(k: Long, room_number: LongArray): LongArray {
    var answer = LongArray(room_number.size)
    val hotelRooms = HotelRooms(k)

    room_number.forEachIndexed { index, l ->
        answer[index] = hotelRooms.assignments(l)
    }

    return answer
}

class HotelRooms (val maxNum:Long){
    val completeRooms = hashMapOf<Long, Long>()
    var loopCounter = 0

    /**@return 배정된 방 번호*/
    fun assignments(roomNum:Long): Long {
        var currentNum = roomNum
        var prevNum = roomNum -1
        val updateTarget = arrayListOf<Long>()
        while (completeRooms.containsKey(currentNum)){
            updateTarget.add(currentNum)
            currentNum = completeRooms[currentNum]!!
            if(completeRooms.containsKey(prevNum)){
                if(currentNum < completeRooms[prevNum]!!){
                    currentNum = completeRooms[prevNum]!!
                } else {
                    prevNum--
                }
            }
            loopCounter++
        }
        completeRooms[currentNum] = currentNum+1
        updateTarget.forEach {
            completeRooms[it] = currentNum+1
        }

        return currentNum
    }

}


