package baek

//스타트와 링크 (백트래킹)
class Baek14889 {

    fun main() {
        val n = readLine()!!.toInt()
        val s = Array(n) { IntArray(n) }
        for (i in 0 until n) {
            readLine()!!.split(" ").forEachIndexed { index, str -> s[i][index] = str.toInt() }
        }
        var answer = Int.MAX_VALUE

        fun getScore(
            arr: IntArray,
            temp: IntArray = IntArray(2),
            current: Int = 0,
            visited: BooleanArray = BooleanArray(arr.size),
        ): Int {
            if (2 == current) {
                return s[temp[0]][temp[1]]
            } else {
                var score = 0
                for (i in arr.indices) {
                    if (!visited[i]) {
                        visited[i] = true
                        temp[current] = arr[i]
                        score += getScore(arr, temp, current + 1, visited)
                        visited[i] = false
                    }
                }
                return score
            }
        }

        fun combination(
            arr: IntArray,
            r: Int,
            start: Int = 0,
            n: Int = arr.size,
            visited: BooleanArray = BooleanArray(arr.size),
        ): Boolean {
            if (r == 0) {
                val r = arrayListOf<Int>()
                val rInverse = arrayListOf<Int>()
                visited.forEachIndexed { index, b ->
                    if (b)
                        r.add(arr[index])
                    else
                        rInverse.add(arr[index])
                }
                val diff = Math.abs(getScore(r.toIntArray()) - getScore(rInverse.toIntArray()))
                if (answer > diff)
                    answer = diff
                if (answer == 0) return true
                return false
            }
            for (i in start until n) {
                visited[i] = true
                if (combination(arr, r - 1, i + 1, n, visited)) break
                visited[i] = false
            }
            return false
        }

        val nArray = IntArray(n).apply {
            for (i in 0 until n)
                this[i] = i
        }
        combination(nArray, n / 2)
        println(answer)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Baek14889().main()
        }
    }
}
// 걸린 시간(분):55