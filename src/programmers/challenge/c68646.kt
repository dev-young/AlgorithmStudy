package programmers.challenge

//https://programmers.co.kr/learn/courses/30/lessons/68646
//level3
class c68646 {

    fun solution(a: IntArray): Int {
        var answer: Int = 0
        /**왼쪽 혹은 오른쪽에 자신보다 작은 숫자가 1개 이하여야 최후까지 살아남을 수 있다.*/

        var minl = a[0] //왼쪽 최솟값
        val sortedSet = LinkedHashSet(a.sorted())   //정렬된 오른쪽 값
        a.forEachIndexed { index, i ->
            sortedSet.remove(i)
            if(index == 0 || index == a.lastIndex) {
                answer++
            } else {
                if(i < minl) {
                    answer++
                    minl = i
                } else {
                    val minr = sortedSet.first()
                    if(i < minr) {
                        answer++
                    }
                }
            }
        }

        return answer
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = c68646()
            val r = s.solution(intArrayOf(9,-1,-5))
            println(r)

        }
    }
}
// 걸린 시간: 40분
