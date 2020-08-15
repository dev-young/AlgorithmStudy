package kakao

class Kakao2020_3 {
    fun solution(gems: Array<String>): IntArray {
        var answer = intArrayOf(1, gems.size)
        var max = hashSetOf<String>().apply { gems.forEach { add(it) } }.size // 보석 종류 수
        val kindSet = hashSetOf<String>()
        outer@for(i in gems.indices){
            kindSet.clear()
            if(gems.size - 1 - i <= max)
                break
            inner@for(j in i until  gems.size){
                val gem = gems[j]
                kindSet.add(gem)

                if(kindSet.size == max){
                    val count1 = answer[1] - answer[0]
                    val count2 = j - i
                    if(count1 > count2){
                        answer[0] = i + 1
                        answer[1] = j + 1
                        break@inner
                    }
                }
//                if(kindSet.size == max && j == (gems.size -1)){
//                    break@outer
//                }

            }
        }

        return answer
    }

}

fun main() {
    val s = Kakao2020_3()
//    var r = s.kakao.solution(arrayOf("AA", "AB", "AC", "AA", "AC"))
    println(s.solution(arrayOf("DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA")).contentToString())
    println(s.solution(arrayOf("AA", "AB", "AC", "AA", "AC")).contentToString())
    println(s.solution(arrayOf("XYZ", "XYZ", "XYZ")).contentToString())
    println(s.solution(arrayOf("ZZZ", "YYY", "NNNN", "YYY", "BBB")).contentToString())
}