package kakao.blind2022

class Solution4 {
    fun solution(n: Int, info: IntArray): IntArray {
        var answer: IntArray? = null
        var maxDiff = 0
        val infoScore = info.foldIndexed(0) { index, acc, i -> if (i > 0) acc + 10 - index else acc }

        withBit(intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)).forEach { arr ->
            var mScore = 0  //내 점수
            var eScore = infoScore //상대 점수
            var enable = true   //전략 사용 가능여부
            var remain = n  //남은 화살수
            val mInfo = IntArray(11)    //내가 맞춘 과녁 정보
            for (idx in arr) {
                val eHitCount = info[idx]
                val mHitCount = eHitCount + 1
                remain -= mHitCount
                if (remain < 0) {
                    //남은 화살수가 부족한 경우 사용할 수 없는 전략
                    enable = false
                    break
                }
                mInfo[idx] = mHitCount
                val s = 10 - idx
                mScore += s
                if (eHitCount > 0)
                    eScore -= s
            }
            if (remain > 0) {
                mInfo[10] = remain
            }
            if (enable) {
                val diff = mScore - eScore
                if (diff > 0) {
                    if (diff > maxDiff) {
                        maxDiff = diff
                        answer = mInfo
                    } else if (diff == maxDiff) {
                        answer?.let {
                            //기존의 answer 과 비교하여 더 나은 answer 으로 교체
                            var mInfoBetter = false
                            for (i in 10 downTo 0) {
                                if (mInfo[i] > it[i]) {
                                    mInfoBetter = true
                                    break
                                }
                            }
                            if (mInfoBetter) {
                                answer = mInfo
                            }
                        } ?: kotlin.run { answer = mInfo }
                    }
                }
            }

        }

        return answer ?: intArrayOf(-1)
    }

    fun withBit(arr: IntArray): ArrayList<IntArray> {
        val n = arr.size
        val res = arrayListOf<IntArray>()
        for (i in 0 until (1 shl n)) {
            val temp = arrayListOf<Int>()
            for (j in 0 until n) {
                if (i and (1 shl j) != 0) {
                    temp.add(arr[j])
                }
            }
            res.add(temp.toIntArray())
        }
        return res

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Solution4()
            val r = s.solution(
                5,
                intArrayOf(2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0)
            )
            println(r.toList())
        }
    }
}
// 걸린 시간(분): 풀이 한번 보고 품