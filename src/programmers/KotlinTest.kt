package programmers

class KotlinTest {


    data class Item(var value: Int)
    fun test(){

        val alist = arrayListOf(Item(1), Item(2), Item(3), Item(4))
        alist.filter { it.value>1 }.forEach {
            it.value = 0
        }
        println(alist.toString())
    }

}

fun main() {
    KotlinTest().test()
}