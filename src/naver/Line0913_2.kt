package naver

class Line0913_2 {
    fun solution(ball: IntArray, order: IntArray): IntArray {
        var answer = arrayListOf<Int>()

        val tempSet = hashSetOf<Int>()
        val ballList = ball.toMutableList()

        fun removeBall(index:Int){
            answer.add(ballList.removeAt(index))
        }

        order.forEach {
            val first = ballList.first()
            val last = ballList.last()

            if(it == first) {
                removeBall(0)
                while (ballList.isNotEmpty() && tempSet.contains(ballList.first())){
                    removeBall(0)
                }
            } else if (it == last) {
                removeBall(ballList.lastIndex)
                while (ballList.isNotEmpty() && tempSet.contains(ballList.last())){
                    removeBall(ballList.lastIndex)
                }
            } else {
                tempSet.add(it)
            }
        }


        return answer.toIntArray()
    }
}

fun main() {
    val s = Line0913_2()
    val r = s.solution(intArrayOf(1, 2, 3, 4, 5, 6), intArrayOf(6, 2, 5, 1, 4, 3))
    println(r.contentToString())
}