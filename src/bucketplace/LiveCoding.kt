package bucketplace

class LiveCoding {

    fun solution(s:String): Int {
        var max = 0
        var a = 0
        var b = 1

        while (a< b) {
            val sub = s.substring(a, b)
            if (sub.length > max) {
                max = sub.length

            } else {

            }
            b++
        }

        return max
    }

    fun solution2(ints: IntArray): IntArray {
        val ans = IntArray(2)

        val total = (ints.size+2) * (ints.size+3) / 2
        val sum = ints.sum()
        val a = (total - sum) / 2
        ans[0] = a
        ans[1] = a + 1

        return ans
    }
}

fun main() {
//    println(BucketPlace().solution("abcabcbb"))
//    println(BucketPlace().solution("pwwkew"))
    println(LiveCoding().solution2(intArrayOf(1,5,2,6)).contentToString())
    println(LiveCoding().solution2(intArrayOf(1,4,5)).contentToString())
}