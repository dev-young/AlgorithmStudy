package algorithm

class Numbers {

    //최대 공약수 (유클리드 알고리즘)
    fun gcd(a_: Int, b_: Int): Int {
        var a = Math.max(a_, b_)
        var b = Math.min(a_, b_)
        while (b != 0) {
            (a % b).let {
                a = b
                b = it
            }
        }
        return a
    }

    //최속 공배수
    fun lcm(a: Int, b: Int) = (a * b) / gcd(a, b)

    //소수판별 ( 참고: https://myjamong.tistory.com/139?category=898047 )
    fun isPrime(n: Int): Boolean {
        var i = 2
        while (i * i <= n) {
            if (n % i == 0) return false
            i++
        }
        return true
    }


    //테스트
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nums = Numbers()
            intArrayOf(123).sortedDescending()
            println(nums.isPrime(997))
            intArrayOf(1,2,3,4).sortedWith(compareBy({it}, {it}, {it*2}))

        }
    }
}