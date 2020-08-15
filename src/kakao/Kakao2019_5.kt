package kakao

import java.util.*
import kotlin.system.measureTimeMillis

/*
1.다음 자리에 돌이 있고 건널 수 있는 자리면 건넌다.
2.다음 자리에 돌이 없으면 그 다음 자리를 본다.
3.


*/
class Kakao2019_5{
    var emptyCount = 0  // 숫자가 0인 돌의 수
    val stonesMap = hashMapOf<Int, Int>()   // <돌idx, 다음 돌까지의 빈 돌의 수>

    /**이게 더 효율성 점수가 낮다... 왜일까..? */
    fun solution(stones: IntArray, k: Int): Int {
        stonesMap.clear()
        var answer = 0
        var mins : IntArray = intArrayOf()
        val time = measureTimeMillis {
            mins = HashSet(stones.asList()).toIntArray()
            mins.sort()
        }
        println(time)
        stonesMap[-1] = 0
        out@for (m in mins) {
            var min = m - answer
            answer += min

            var index = stonesMap[-1]!!
            emptyCount = index
            while(index < stones.size){
                val i = index

                if(stones[i] != 0) stones[i] = stones[i] - min

                if(stones[i] == 0){
                    val count = stonesMap.getOrDefault(i, 0)
                    index += count
                    emptyCount += count + 1
                    stonesMap[i-1] = count + 1
                } else {
                    emptyCount = 0
                }

                if(emptyCount >= k){
                    //더이상 못 건널때
//                    println(stones.contentToString())
                    break@out
                }
                index++
            }

//            println(stones.contentToString())
        }

        return answer
    }

    /**정확성 100%*/
    fun solution2(stones: IntArray, k: Int): Int {
        var answer = 0
        val mins = HashSet(stones.asList()).toIntArray()
        mins.sort()

        out@for (m in mins) {
            var min = m - answer
            answer += min
            emptyCount = 0

            var index = 0
            while(index < stones.size){
                val i = index

                if(stones[i] != 0) stones[i] = stones[i] - min

                if(stones[i] == 0){
                    emptyCount++
                } else {
                    emptyCount = 0
                }

                if(emptyCount >= k){
                    //더이상 못 건널때
//                    println(stones.contentToString())
                    break@out
                }
                index++
            }
//            println(stones.contentToString())
        }

        return answer
    }


    fun solution_notUse(stones: IntArray, k: Int): Int {
        var answer = 0
        var position = stones.size

        out@while (true){
            for (i in stones.indices){
                val stone = stones[i]
                if (stone > 0){
                    if(emptyCount<k){
                        //건넌다
                        stones[i]--
                        position = i
                        emptyCount = 0
                    } else break@out

                } else {
                    emptyCount++
                }
            }

            if(position + k < stones.size){
                //실패
                break
            } else {
                //성공
                answer++
                position = -1   // 위치 초기화
//                println(stones.contentToString())
            }
        }



//        println(stones.contentToString())
        return answer
    }
}

fun main() {
    val s = Kakao2019_5()
//    println(s.solution2(intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 5, 3, 2, 1, 4, 4, 4, 4, 4, 4, 4, 2, 5, 1), 6))
//    println(s.kakao.solution(intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 5, 3, 2, 1, 4, 4, 4, 4, 4, 4, 4, 2, 5, 1), 6))


//    println(s.solution2(intArrayOf(3, 2, 2, 8, 1), 4))
//    println(s.kakao.solution(intArrayOf(3, 2, 2, 8, 1), 4))


//    println(s.solution2(intArrayOf(6, 1, 9, 6, 5), 1))
//    println(s.kakao.solution(intArrayOf(6, 1, 9, 6, 5), 1))

//    println(s.solution2(intArrayOf(9,2,1,8,3),2))
//    println(s.kakao.solution(intArrayOf(9,2,1,8,3),2))

    for(i in 1 .. 1){
        val random = Random()
        val size = 200000
        val intArray = IntArray(size)
        for(i in 0 until size){
            intArray[i] = random.nextInt(200000000)+1
        }

        val r1 = s.solution(intArray.clone(), size)
//        for(k in 1 .. size){
//            println()
//
//            println(intArray.contentToString())
//            val r1 = s.kakao.solution(intArray.clone(), k)
//            println()
//            println(intArray.contentToString())
//            val r2 = s.solution2(intArray.clone(), k)
//            println("$r1 , $r2")
//            if(r1 != r2){
//                println(intArray.contentToString())
//                if(r1 < 3 || r2 <3)
//                    println(k)
//            }
//        }
    }


//    println(kakao.Kakao2019_5().solution2(intArrayOf(2, 4, 5, 3, 2), 2))
//    println(kakao.Kakao2019_5().kakao.solution(intArrayOf(2, 4, 5, 3, 2), 2))
//    println(kakao.Kakao2019_5().solution2(intArrayOf(5, 8, 5, 2, 6, 2), 3))
//    println(kakao.Kakao2019_5().kakao.solution(intArrayOf(5, 8, 5, 2, 6, 2), 3))
}





