package kakao.winterIntern2019

import kotlin.random.Random

class Kakao2019_4 {

    /**전에 내가 푼 풀이 보고 다시 풀어본 버전*/
    fun solution3(k: Long, room_number: LongArray): LongArray {
        var answer = LongArray(room_number.size)

        val map = hashMapOf<Long,Long>()
        room_number.forEachIndexed { index, want ->
            var roomNum = want
            var prev = map[roomNum-1]
            while (map.containsKey(roomNum)) {
                roomNum = map[roomNum]!!
                prev?.let {
                    if (roomNum < it) {
                        roomNum = it
                    } else prev = map[it -1]
                }
            }
            prev?.let { map[it] = roomNum+1 }
            map[roomNum] = roomNum+1
            map[want] = roomNum+1
            answer[index] = roomNum
        }
        return answer
    }

    class ReservatedRoomRange(var start: Long) {
        var end: Long = start
    }

    /**3번 풀이보다 이게 더 빠를줄 알았는데 실패했다... */
    fun solution2(k: Long, room_number: LongArray): LongArray {
        var answer = longArrayOf()

        val reservated = hashSetOf<Long>()
        val rangeList = arrayListOf<ReservatedRoomRange>()
        room_number.forEach {
            if (reservated.contains(it)) {
                var rnum = it
                var idx = 0
                for (i in rangeList.indices) {
                    val range = rangeList[i]
                    if (rnum in range.start..range.end) {
                        rnum = range.end + 1
                        idx = i
                        break
                    }
                }
                reservated.add(rnum)
                answer += rnum
                rangeList[idx].end = rnum
                if (idx + 1 < rangeList.size) {
                    if (rangeList[idx + 1].start == rnum + 1) {
                        rangeList[idx].end = rangeList[idx + 1].end
                        rangeList.removeAt(idx + 1)
                    }
                }
            } else {
                reservated.add(it)
                answer += it

                var leftRange: ReservatedRoomRange? = null
                var rightRange: ReservatedRoomRange? = null
                val rnum = it
                var idx = 0
                for (i in rangeList.indices) {
                    idx = i
                    val range = rangeList[i]
                    if (range.end == rnum - 1) {
                        leftRange = range
                    } else if (range.start == rnum + 1) {
                        rightRange = range
                        break
                    } else if (range.start > rnum) {
                        break
                    }
                }

                if(leftRange == null && rightRange == null) {
                    if(rangeList.isNotEmpty() && rangeList[idx].end < rnum) idx++
                    rangeList.add(idx, ReservatedRoomRange(rnum))
                } else {
                    leftRange?.let { it.end = rnum }
                    rightRange?.let {
                        it.start =  rnum
                        leftRange?.let { lr ->
                            lr.end = it.end
                            rangeList.removeAt(idx)
                        }
                    }
                }
            }

        }

        return answer
    }

    fun solution(k: Long, room_number: LongArray): LongArray {
        var answer = LongArray(room_number.size)
        val hotelRooms = HotelRooms(k)

        for (i in room_number.indices) {
            val completedRoomNum:Long = hotelRooms.assignments(room_number[i])
            answer[i] = completedRoomNum
        }


        return answer
    }

    class HotelRooms (val maxNum:Long){
        val completeRooms = HashMap<Long, CompletedRoom>()
        var loopCounter = 0
        /**@return 배정된 방 번호*/
        fun assignments(roomNum:Long): Long {
            var currentNum = roomNum
            var prevRoom = completeRooms[currentNum-1]
            var currentRoom = completeRooms[currentNum]
            while (currentRoom != null){
                currentNum = currentRoom.parentNum
                if(prevRoom != null){
                    if(currentNum < prevRoom.parentNum){
                        currentNum = prevRoom.parentNum
                    } else {
                        prevRoom = completeRooms[prevRoom.num -1]
                    }
                }
                currentRoom = completeRooms[currentNum]
                loopCounter++
            }
            completeRooms[currentNum] = CompletedRoom(currentNum, currentNum+1)
            completeRooms[roomNum]!!.parentNum = currentNum+1

            return currentNum
        }

        data class CompletedRoom (val num : Long, var parentNum : Long)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Kakao2019_4()
//            val r = s.solution(10, longArrayOf(1, 3, 4, 1, 3, 1))
//            val r = s.solution(1111, longArrayOf(2, 2, 2,7,7,7,3 ,3,3)) //2,3,4,7,8,9,5,6,10
            var l = longArrayOf(1,1,1,1,5,5,5,5,3)   //2,3,4,7,8,9,5,6,10
            var r = s.solution2(12313, l)
            var r2 = s.solution(12313, l)
            val random = Random(1234)
            while (r.contentEquals(r2)) {
                val n = random.nextLong(10)+4
                l = LongArray(n.toInt()) {random.nextLong(n*10)+1}
                r = s.solution3(12313, l)
                r2 = s.solution(12313, l)
            }
            println(r.contentToString())
            println(r2.contentToString())
            println(r.contentEquals(r2))
        }
    }
}
// 걸린 시간(분): 119


