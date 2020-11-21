package kakao.blind2019

//https://programmers.co.kr/learn/courses/30/lessons/42889
class KakaoB19_2 {

    fun solution(N: Int, stages: IntArray): IntArray {
        var answer = intArrayOf()
        val stageMap = hashMapOf<Int, Int>() //<스테이지번호, 해당 스테이지에 남아있는 사람 수>
        val clearMap = hashMapOf<Int, Int>() //<스테이지번호, 해당 스테이지에 도달한 사람 수>
        for (i in 1..N) {
            stageMap[i] = 0
        }
        stages.sort()

        stages.forEachIndexed { idx, it ->
            stageMap[it] = stageMap[it]?.plus(1) ?: 1
            if (!clearMap.contains(it))
                clearMap[it] = stages.size - idx
        }

        val failList = arrayListOf<Pair<Int, Double>>()

        for (i in 1..N) {
            val f = if (stageMap[i] == 0) 0.0
            else if (clearMap[i] == null || clearMap[i] == 0) 0.0
            else
                (stageMap[i]!!.toDouble() / clearMap[i]!!)
            failList.add(Pair(i, f))
        }

        return failList.sortedWith(Comparator { o1, o2 ->
            val r = -o1.second.compareTo(o2.second)
            if (r == 0) o1.first.compareTo(o2.first)
            else r
        }).map {
            it.first
        }.toIntArray()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = KakaoB19_2()
            val r = s.solution(4, intArrayOf(4, 4, 4, 4, 4))
            println(r.contentToString())
        }
    }
}
// 걸린 시간: 56분