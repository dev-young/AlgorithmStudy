package programmers


//https://programmers.co.kr/learn/courses/30/lessons/42627?language=kotlin
class Heap02 {
    fun solution(jobs: Array<IntArray>): Int {
        jobs.sortWith(Comparator { o1, o2 ->
            val b = o1[0].compareTo(o2[0])
            if (b == 0)
                o1[1].compareTo(o2[1])
            else
                b
        })
//        jobs.forEach { println(it.contentToString()) }
        val jobList = jobs.toMutableList()

        var totalTime = 0
        var currentTime = 0
        while (jobList.isNotEmpty()) {
            var targetJob = jobList[0]
            if (targetJob[0] > currentTime) {
                //현재 시간보다 요청 시간이 더 뒤에 있는 경우
                currentTime = targetJob[0] + targetJob[1]
                jobList.removeAt(0)
                totalTime += targetJob[1]
                continue
            }

            var targetIdx = 0
            for (i in jobList.indices) {
                if (jobList[i][0] > currentTime) {
                    break
                }

                if (jobList[i][1] < targetJob[1]) {
                    targetIdx = i
                    targetJob = jobList[i]
                }
            }

            currentTime += targetJob[1]
            totalTime += currentTime - targetJob[0]
            jobList.removeAt(targetIdx)

        }

        return totalTime / jobs.size
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s = Heap02()
//            val r = s.solution(arrayOf(intArrayOf(0, 3), intArrayOf(1, 9), intArrayOf(2, 6)))
            val r = s.solution(arrayOf(intArrayOf(1, 4), intArrayOf(0, 4), intArrayOf(0, 3), intArrayOf(0, 2)))
            println(r)
        }
    }
}

// 걸린 시간: 120분