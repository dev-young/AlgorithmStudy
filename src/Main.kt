import java.util.*

fun main(){
    val sc = Scanner(System.`in`)
    val num = sc.nextInt()
    print(factorial(num))

}

fun factorial(number:Int) : Int{
    if(number > 1){
        return number * factorial(number-1)
    }

    return  1
}