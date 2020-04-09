fun main(){
    println("Hello Kotlin!")

    var arr = arrayOf("Hello Kotlin!", "String", "array")
    arr.iterator().forEach { s ->
        println(s)
    }
}