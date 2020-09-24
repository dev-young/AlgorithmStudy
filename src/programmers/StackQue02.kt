package programmers

import java.util.*

class StackQue02 {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var answer = 0
        val truckQue = arrayListOf<Int>()    // 인덱스를 저장
        val bridgeQue = arrayListOf<Int>()
        val truckPosition = IntArray(truck_weights.size) {0}
        truck_weights.forEachIndexed { index, i ->  truckQue.add(index) }

        var currentWeight = 0
        var finishCount = 0
        while (finishCount < truck_weights.size) {
            answer ++

            //다리위 트럭 한칸씩 전진
            bridgeQue.forEach { truckIndex ->
                truckPosition[truckIndex] += 1
            }

            //끝에 도달한 차가 있으면 다리 무게 줄이고 완료된 수 ++
            bridgeQue.firstOrNull()?.let { truck ->
                if(truckPosition[truck] > bridge_length) {
                    bridgeQue.removeAt(0)
                    currentWeight -= truck_weights[truck]
                    finishCount++
                }
            }

            //남은 차를 다리위에 올릴 수 있는지 판단후 가능하면 다리 위에 추가
            truckQue.firstOrNull()?.let {truck ->
                if(truck_weights[truck] + currentWeight <= weight) {
                    bridgeQue.add(truckQue.removeAt(0))
                    truckPosition[truck]++
                    currentWeight += truck_weights[truck]
                }
            }
        }
        return answer
    }

    fun solution2(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var answer = 0
        val truckQue : Queue<Int> = LinkedList()    // 인덱스를 저장
        val bridgeQue : Queue<Int> = LinkedList()
        val truckPosition = IntArray(truck_weights.size) {0}
        truck_weights.forEachIndexed { index, i ->  truckQue.add(index) }

        var currentWeight = 0
        var finishCount = 0
        while (finishCount < truck_weights.size) {
            answer ++

            //다리위 트럭 한칸씩 전진
            bridgeQue.forEach { truckIndex ->
                truckPosition[truckIndex] += 1
            }

            //끝에 도달한 차가 있으면 다리 무게 줄이고 완료된 수 ++
            bridgeQue.peek()?.let { truck ->
                if(truckPosition[truck] > bridge_length) {
                    bridgeQue.poll()
                    currentWeight -= truck_weights[truck]
                    finishCount++
                }
            }

            //남은 차를 다리위에 올릴 수 있는지 판단후 가능하면 다리 위에 추가
            truckQue.peek()?.let {truck ->
                if(truck_weights[truck] + currentWeight <= weight) {
                    bridgeQue.add(truckQue.poll())
                    truckPosition[truck]++
                }

            }

        }

        return answer
    }







    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = StackQue02()
            val r = s.solution(2, 10, intArrayOf(7,4,5,6))
            println(r)
        }
    }
}
// 걸린 시간: 0분
