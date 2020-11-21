package kakao.blind2019

//https://programmers.co.kr/learn/courses/30/lessons/42890?language=kotlin
class KakaoB19_3 {

    fun solution(relation: Array<Array<String>>): Int {

        val answerList = arrayListOf<IntArray>()
        val set = arrayListOf<Int>().apply {
            for (i in relation[0].indices) add(i)
        }.toIntArray().powerSet()
        set.sortBy { it.size }
        set.removeAt(0)
        set.dropLast(1)

        set.forEach { cols ->
            val keySet = hashSetOf<String>()
            for (row in relation) {
                var key = ""
                cols.forEach { key += "${row[it]} " }
                if (keySet.contains(key))
                    break
                keySet.add(key)
            }

            if (keySet.size == relation.size) { //유일성 체크
                var minimality = true
                for (ans in answerList) {
                    if (checkPowerSet(cols, ans)){
                        minimality = false
                        break
                    }
                }

                //최소성을 충족하는 경우
                if (minimality)
                    answerList.add(cols)
            }
        }

        return answerList.size
    }

    fun checkPowerSet(parent: IntArray, child: IntArray): Boolean {
        for (c in child) {
            if(!parent.contains(c))
                return false
        }
        return true
    }


    fun IntArray.powerSet(): MutableList<IntArray> {
        val result = mutableListOf<IntArray>()
        tailrec fun ps(arr: IntArray, visited: BooleanArray, n: Int, idx: Int) {
            if (idx == n) {
//            print(arr, visited, n)
                val temp = mutableListOf<Int>()
                visited.forEachIndexed { index, b ->
                    if (b) temp.add(arr[index])
                }
                result.add(temp.toIntArray())
                return
            }
            visited[idx] = false
            ps(arr, visited, n, idx + 1)
            visited[idx] = true
            ps(arr, visited, n, idx + 1)
        }

        ps(this, BooleanArray(size), size, 0)
        return result
    }

    /**다른사람이 푼 깔끔한 풀이 (방법은 같으나 문법이 넘 깔끔하다. )*/
    fun solution2(relation: Array<Array<String>>): Int {

        fun <T> List<T>.powerSet(): List<List<T>> {
            var r = listOf(emptyList<T>())
            tailrec fun recursive(ll: List<T>) {
                if (ll.isEmpty())
                    return
                r = r.flatMap { listOf(it + ll.first(), it) }
                return recursive(ll.drop(1))
            }

            recursive(this)
            return r.toList()
        }

        val columnSize = relation.first().size
        val tupleSize = relation.size
        val powerSets = (1..columnSize).toList().powerSet().dropLast(1).sortedBy { it.size }

        val candidateKey = mutableListOf<List<Int>>()

        fun <T> List<T>.isPartOf(l: List<T>): Boolean = all { it in l }

        return powerSets.filter { set ->
            if (candidateKey.any { it.isPartOf(set) })
                false
            else {
                val keys = relation.map { tuple -> set.fold("") { acc, i -> acc + tuple[i - 1] } }

                if (keys.distinct().size == tupleSize) {
                    candidateKey.add(set)
                    true
                } else
                    false
            }
        }.size
    }



    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = KakaoB19_3()
            val r = s.solution(arrayOf(arrayOf("100", "ryan", "music", "2"),
                arrayOf("200", "apeach", "math", "2"),
                arrayOf("300", "tube", "computer", "3"),
                arrayOf("400", "con", "computer", "4"),
                arrayOf("500", "muzi", "music", "3"),
                arrayOf("600", "apeach", "music", "2")))
            println(r)
        }
    }
}
// 걸린 시간: 90분