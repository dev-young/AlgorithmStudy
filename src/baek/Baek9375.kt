package baek

import java.util.*
import kotlin.collections.ArrayList

/** 패션왕 신해빈
문제
해빈이는 패션에 매우 민감해서 한번 입었던 옷들의 조합을 절대 다시 입지 않는다. 예를 들어 오늘 해빈이가 안경, 코트, 상의, 신발을 입었다면, 다음날은 바지를 추가로 입거나 안경대신 렌즈를 착용하거나 해야한다. 해빈이가 가진 의상들이 주어졌을때 과연 해빈이는 알몸이 아닌 상태로 며칠동안 밖에 돌아다닐 수 있을까?

입력
첫째 줄에 테스트 케이스가 주어진다. 테스트 케이스는 최대 100이다.

각 테스트 케이스의 첫째 줄에는 해빈이가 가진 의상의 수 n(0 ≤ n ≤ 30)이 주어진다.
다음 n개에는 해빈이가 가진 의상의 이름과 의상의 종류가 공백으로 구분되어 주어진다. 같은 종류의 의상은 하나만 입을 수 있다.
모든 문자열은 1이상 20이하의 알파벳 소문자로 이루어져있으며 같은 이름을 가진 의상은 존재하지 않는다.

출력
각 테스트 케이스에 대해 해빈이가 알몸이 아닌 상태로 의상을 입을 수 있는 경우를 출력하시오.
 */
fun main(){
    val testCaseList = ArrayList<HashMap<String, Int>>()

    val sc = Scanner(System.`in`)
    val testCaseCount = sc.nextLine().toInt()

    for (t in 0 until testCaseCount){
        val total = sc.nextLine().toInt()
        val dressMap : HashMap<String, Int> = hashMapOf()
        for (i in 0 until total){
            val s = sc.nextLine().split(" ")
//            val dressName = s[0]
            val category = s[1]
            if (dressMap[category] == null) {
                dressMap[category] = 1
            } else {
                dressMap[category] = dressMap[category]!!.plus(1)
            }

        }
        testCaseList.add(dressMap)
    }



    testCaseList.forEach { hashMap: HashMap<String, Int> ->
        println(getResult(hashMap.values))
    }


}

fun getResult(list: Collection<Int>): Int {
    var result = 1;

    for (i in list) {
        result *= i+1
    }

    return result-1
}

fun makeTestCase(testCaseList: java.util.ArrayList<HashMap<String, java.util.ArrayList<String>>>) {
    var dressMap : HashMap<String, ArrayList<String>> = hashMapOf()
    var stringList : ArrayList<String> = arrayListOf()

    stringList.add("hat")
    stringList.add("turban")
    dressMap["headgear"] = stringList
    stringList = arrayListOf()
    stringList.add("sunglasses")
    dressMap["eyewear"] = stringList
    testCaseList.add(dressMap)

    dressMap = hashMapOf()
    stringList = arrayListOf()
    stringList.add("mask")
    stringList.add("sunglasses")
    stringList.add("makeup")
    dressMap["face"] = stringList
    testCaseList.add(dressMap)

}
