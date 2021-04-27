package programmers.practice

//https://programmers.co.kr/learn/courses/30/lessons/77485
//행렬 테두리 회전하기
//level2
class P77485 {

    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        var answer = intArrayOf()
        val arr = Array(rows) {i -> IntArray(columns) {j -> (i*columns) + j+1} }
        queries.forEach {
            val r1 = it[0] - 1
            val c1 = it[1] - 1
            val r2 = it[2] - 1
            val c2 = it[3] - 1
            val a1 = IntArray(c2 - c1) {c -> arr[r1][c1+c]}
            val a2 = IntArray(r2 - r1) {r -> arr[r1+r][c2]}
            val a3 = IntArray(c2 - c1) {c -> arr[r2][c1+c+1]}
            val a4 = IntArray(r2 - r1) {r -> arr[r1+r+1][c1]}
            var min = rows*columns
            a1.forEachIndexed { c, i ->
                arr[r1][c+c1+1] = i
                min = Math.min(min, i)
            }
            a2.forEachIndexed { r, i ->
                arr[r1+r+1][c2] = i
                min = Math.min(min, i)
            }
            a3.forEachIndexed { c, i ->
                arr[r2][c+c1] = i
                min = Math.min(min, i)
            }
            a4.forEachIndexed { r, i ->
                arr[r1+r][c1] = i
                min = Math.min(min, i)
            }
            answer += min
        }
        return answer
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = P77485()
            println(s.solution(6,6, arrayOf(intArrayOf(2,2,5,4),intArrayOf(3,3,6,6),intArrayOf(5,1,6,3))).contentToString())
            println(s.solution(3,3, arrayOf(intArrayOf(1,1,2,2),intArrayOf(1,2,2,3),intArrayOf(2,1,3,2),intArrayOf(2,2,3,3))).contentToString())
            println(s.solution(100,97, arrayOf(intArrayOf(1,1,100,97))).contentToString())

        }
    }
}
// 걸린 시간(분): 66