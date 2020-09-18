package programmers


//https://programmers.co.kr/learn/courses/30/lessons/42746?language=kotlin
class Sort02 {
    fun solution(numbers: IntArray): String {
        return numbers.sortedWith(Comparator { o1:Int, o2:Int ->
            -"$o1$o2".compareTo("$o2$o1") }).joinToString("").let { if (it.startsWith('0')) "0" else it }
    }

}


fun main() {
    println(Sort02().solution(intArrayOf(6, 10, 2)))
    println(Sort02().solution(intArrayOf(3, 30, 34, 5, 9)))
}