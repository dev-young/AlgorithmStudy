import java.util.*

/*
1.다음 자리에 돌이 있고 건널 수 있는 자리면 건넌다.
2.다음 자리에 돌이 없으면 그 다음 자리를 본다.
3.


*/
class Kakao2019_5{
    var emptyCount = 0  // 숫자가 0인 돌의 수
    val stonesMap = hashMapOf<Int, Int>()   // <돌idx, 다음 돌까지의 빈 돌의 수>

    fun solution(stones: IntArray, k: Int): Int {
        var answer = 0
        val mins = HashSet(stones.asList()).toIntArray()
        mins.sort()

        stonesMap[-1] = 0
        out@for (m in mins) {
            var min = m - answer
            answer += min
            emptyCount = 0

            var index = stonesMap[-1]!!
            emptyCount = index
            while(index < stones.size){
                val i = index

                if(stones[i] != 0) stones[i] = stones[i] - min


                if(stones[i] == 0){
                    emptyCount++
                    stonesMap[i-1] = stonesMap.getOrDefault(i, 0) + 1
                } else {
                    emptyCount = 0
                }

                if(emptyCount >= k){
                    //더이상 못 건널때
                    println(stones.contentToString())
                    break@out
                }

                if(stonesMap.containsKey(i)){
                    index += stonesMap[i]!!
                    emptyCount += stonesMap[i]!!
                }
                index++
            }

            println(stones.contentToString())
        }

        return answer
    }

    /**정확성 100%*/
    fun solution2(stones: IntArray, k: Int): Int {
        var answer = 0
        val mins = HashSet(stones.asList()).toIntArray()
        mins.sort()

        stonesMap[-1] = 0
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
                    println(stones.contentToString())
                    break@out
                }
                index++
            }

            println(stones.contentToString())
        }

        return answer
    }


    fun solution3(stones: IntArray, k: Int): Int {
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
                println(stones.contentToString())
            }
        }



        println(stones.contentToString())
        return answer
    }
}

fun main() {
//    val result = Kakao2019_5().solution2(intArrayOf(2, 4, 5, 3, 2, 1, 4, 2, 5, 1), 3)
//    val result = Kakao2019_5().solution(intArrayOf(2, 4, 5, 3, 2, 1, 4, 2, 5, 1), 3)
    val result = Kakao2019_5().solution(intArrayOf(5, 8, 5, 2, 6, 2), 3)
    println(result)
}





