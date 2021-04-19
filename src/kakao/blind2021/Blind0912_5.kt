package kakao.blind2021

class Blind0912_5 {

    /**풀이법 (https://www.youtube.com/watch?v=Xx5bk_EP8tQ)
     * 전체 플레이시간을 초단위로 바꾸고 각 초마다 시청자 수를 계산한 뒤 광고시간동안의 그 수의 합이 가장 큰 시간대를 찾으면 끝!*/
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        fun toSec(time: String): Int {
            return time.split(":").let {
                it[0].toInt() * 3600 + it[1].toInt() * 60 + it[2].toInt()
            }
        }

        fun toTime(sec: Int): String {
            return String.format("%02d:%02d:%02d",  sec / 3600, sec / 60 % 60, sec % 60)
        }

        val playSec = toSec(play_time)
        val advSec = toSec(adv_time)
        val arr = IntArray(playSec)

        logs.forEach {
            it.split("-").let {
                for (i in toSec(it[0]) until toSec(it[1])) {
                    arr[i] += 1
                }
            }
        }
        var maxStart = 0
        var maxSum = 0L
        for (i in 0 until advSec) maxSum += arr[i]
        var prevSum = maxSum
        for (i in 0 until  playSec-advSec) {
            prevSum = prevSum - arr[i] + arr[i+advSec]
            if(maxSum < prevSum) {
                maxSum = prevSum
                maxStart = i+1
            }
        }

        return toTime(maxStart)
    }

    /**9,15,20,26,27 실패함 / 일부 통과 일부 시간초과*/
    fun solution1(play_time: String, adv_time: String, logs: Array<String>): String {
        fun toSec(time: String): Int {
            return time.split(":").let {
                it[0].toInt() * 3600 + it[1].toInt() * 60 + it[2].toInt()
            }
        }

        fun toTime(sec: Int): String {
            val ss = (sec % 60).let { if (it < 10) "0$it" else "$it" }
            val m = sec / 60
            val hh = (m / 60).let { if (it < 10) "0$it" else "$it" }
            val mm = (m % 60).let { if (it < 10) "0$it" else "$it" }
            return "$hh:$mm:$ss"
        }

        val playSec = toSec(play_time)
        val advSec = toSec(adv_time)
        if (advSec >= playSec) return "00:00:00"

        val logs = logs.map {
            it.split("-").let {
                Pair(toSec(it[0]), toSec(it[1]))
            }
        }.sortedWith(Comparator { o1, o2 ->
            o1.first.compareTo(o2.first).let { if (it == 0) o1.second.compareTo(o2.second) else it }
        })


        var minStart = 0
        var maxSum = 0L

        //시작시간으로부터 광고를 재생했을 때 총 합을 계산하여 반환
        fun getSum(start: Int): Long {
            val end = start + advSec
            var sum = 0L
            for (log in logs) {
                if (end < log.first) break
                val s = if (start < log.first) log.first else start
                val e = if (end > log.second) log.second else end
                if (s < e) sum += e - s
            }
            return sum
        }

        for (log in logs) {
            var s1 = log.first  // 시청기록의 시작을 기준으로 광고를 시작할때
            val s2 = log.second - advSec  // 시청기록의 종료를 기준으로 광고를 종료할때의 시작시간
            var sum1 = 0L
            var sum2 = 0L
            val e = log.second
            if (s1 + advSec <= playSec) {
                sum1 = getSum(s1)
            }
//            else if (s2 < 0) break

            if (s2 >= 0) {
                sum2 = getSum(s2)
            }

            if (sum1 < sum2) {
                s1 = s2
                sum1 = sum2
            }
            if (maxSum < sum1) {
                minStart = s1
                maxSum = sum1
            }
        }

        return toTime(minStart)
    }
}

fun main() {
    val s = Blind0912_5()
//    val r = s.solution("02:03:55", "00:14:15", arrayOf("01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"))
    val r = s.solution("99:59:59", "25:00:00", arrayOf("69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"))
    println(r)
}
// 소요시간(분):