package kakao

import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {

    println(
        solution200507_2(
            arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc"),
            arrayOf("c****", "**a**", "******")
        )
    )
//    println(kakao.solution(arrayOf("1", "6", "2", "3", "4", "5", "7", "8", "9"), arrayOf("c****","**a**")))
}

fun solution200507_2(userIds: Array<String>, bannedIds: Array<String>): Int {
    val backtrackHashes = mutableSetOf<Int>()
    val matchResults = Array(userIds.size) { BooleanArray(bannedIds.size) }

    fun backtrack(level: Int, success: Int, visitFlag: Int): Int {
        if (level >= bannedIds.size) {
            return if (success == bannedIds.size && visitFlag !in backtrackHashes) {
                backtrackHashes += visitFlag
                1
            } else {
                0
            }
        }

        tailrec fun loop(index: Int, acc: Int): Int {
            if (index >= userIds.size) {
                return acc
            }
            val mask = 1 shl index
            val child = if (visitFlag and mask == 0 && matchResults[index][level]) {
                backtrack(level + 1, success + 1, visitFlag or mask)
            } else {
                0
            }
            return loop(index + 1, acc + child)
        }

        return loop(0, 0)
    }

    for ((rowIndex, userId) in userIds.withIndex()) {
        for ((colIndex, bannedId) in bannedIds.withIndex()) {
            matchResults[rowIndex][colIndex] = matches(userId, bannedId)
        }
    }

    return backtrack(0, 0, 0)
}


fun matches(userId: String, bannedId: String): Boolean {
    if (userId.length != bannedId.length) {
        return false
    }

    tailrec fun matchesImpl(index: Int): Boolean {
        if (index >= userId.length) {
            return index >= bannedId.length
        }
        if (bannedId[index] != '*' && userId[index] != bannedId[index]) {
            return false
        }
        return matchesImpl(index + 1)
    }

    return matchesImpl(0)
}
/**------------------------------------------------------------------*/
fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
    var answer = 0

    val availableSet = HashSet<String>()

    val combList = doComb(user_id, banned_id.size)
    combList.forEach { println(it.toString()) }

    for (arrayList in combList) {
        if(isPosible(arrayList, banned_id)){
            answer++
        }

    }

//    for (banned in banned_id) {
//
//        val set = hashSetOf<String>()
//        for (user in user_id) {
//            if (kakao.isPosible(user, banned)){
//                set.add(user)
//                availableSet.add(user)
//            }
//        }
//
////        answer *= count
//    }
//
//
//    val result = kakao.doComb(availableSet.toTypedArray(), banned_id.size)
//    result.forEach { println(it.toString()) }




    return answer
}

fun doComb(arr: Array<String>, r: Int): ArrayList<ArrayList<String>> {
    val result = ArrayList<ArrayList<String>>()
    comb(arr, BooleanArray(arr.size), result, 0, arr.size, r)

    return result
}

fun comb(arr: Array<String>, visited: BooleanArray, result:ArrayList<ArrayList<String>>, depth: Int, n: Int, r: Int) {
    if (r == 0) {
        result.add(getResult(arr, visited, n))
        return
    }
    if (depth == n) {
        return
    } else {
        visited[depth] = true
        comb(arr, visited, result, depth + 1, n, r - 1)
        visited[depth] = false
        comb(arr, visited, result, depth + 1, n, r)
    }
}

fun getResult(arr: Array<String>, visited: BooleanArray, n: Int): ArrayList<String> {
    val r = ArrayList<String>()
//    val r = StringArray()
    for(i in 0 until n){
        if(visited[i])
            r.add(arr[i])
    }
    return r
//    println(r.toString())
}

fun isPosible(user : String, banned : String) : Boolean{
    if(user.length == banned.length){
        for (i in user.indices){
            if(banned[i] != '*' && user[i] != banned[i])
                return false
        }
        return true
    }
    return false
}

fun isPosible(users : ArrayList<String>, banneds : Array<String>) : Boolean{
    val set = HashSet<String>(users)
    var success = 0
    for (banned in banneds) {

        for (s in set) {
            if(isPosible(s, banned)){
                success++
                set.remove(s)
                break
            }
        }
        if(success == banneds.size){
            return true
        }
    }
    return false
}

fun isPosible(users : Array<String>, banneds : Array<String>) : Boolean{
    val set = HashSet<String>(users.asList())
    var success = 0
    for (banned in banneds) {

        for (s in set) {
            if(isPosible(s, banned)){
                success++
                set.remove(s)
                break
            }
        }
        if(success == banneds.size){
            return true
        }
    }
    return false
}