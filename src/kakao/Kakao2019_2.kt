package kakao

import java.util.*

fun main() {
    println(solution200507_1("{{2,1},{2},{2,1,3},{2,1,3,4}}"))
}

//43분 걸림
fun solution200507_1(s: String): IntArray {
    var answer : IntArray

    val arrList = arrayListOf<ArrayList<Int>>()

    val strings = s.removeSurrounding("{{", "}}").split("},{")

    for (str in strings) {
        val list = str.split(',').map { s -> s.toInt() }
        arrList.add(ArrayList(list))
    }
    arrList.sortBy { list -> list.size }
    println(arrList)
    answer = IntArray(arrList.size)

    for (i in 0 until arrList.size){
        val list = arrList[i]

        answer.forEach {
            list.remove(it)
        }

        answer[i] = list[0]

    }

    return answer
}