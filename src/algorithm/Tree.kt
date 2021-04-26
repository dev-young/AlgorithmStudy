package algorithm

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class Tree {

    /**@param v 노드 번호 (정점)
     * @param parent 부모노드 번호*/
    inner class Node(val v: Int, val parent: Int) {
        val child = arrayListOf<Int>()
    }


    /**@param edges 간선 정보 (부모/자식 개념 없이 모든 간선 포함)
     * ex) edges[1] = {2,3,4} -> 1번 정점에 연결된 정점은 2,3,4번
     *
     * @param root 루트로 정할 노드
     *
     * @param parent 현재 탐색할 노드의 부모노드 (-1인 경우 최상위 노드)
     *
     * @param tree 완성되었을때 반횐될 트리
     *
     * @return Node 담겨전 트리 반환
     * */
    fun makeTree(edges: Array<HashSet<Int>>, root: Int, parent: Int = -1, tree: HashMap<Int, Node> = hashMapOf()): HashMap<Int, Node> {
        val node = Node(root, parent)
        tree[root] = node
        for (child in edges[root]) {
            if(child != parent) {
                node.child.add(child)
                makeTree(edges, child, root, tree)
            }
        }
        return tree
    }

    /**@return 위 함수와 비슷하게 동작하며 Node 로 이루어진 트리가 아니라 "map[자식] = 부모" 형태의 결과 반환*/
    fun makeChildParentTree(edges: Array<HashSet<Int>>, root: Int, parent: Int = -1, tree: HashMap<Int, Int> = hashMapOf()): HashMap<Int, Int> {
        tree[root] = parent
        for (child in edges[root]) {
            if(child != parent) {
                makeChildParentTree(edges, child, root, tree)
            }
        }
        return tree
    }

    /**정점이 너무 많아 재귀호출에 제한이 걸릴 경우 스택을 사용하여 재귀호출 하듯이 탐색할 수 있다.
     * @param edges 간선 정보 (부모/자식 개념 없이 모든 간선 포함)
     * ex) edges[1] = {2,3,4} -> 1번 정점에 연결된 정점은 2,3,4번
     *
     * @param root 루트로 정할 노드
     *
     * @param tree 완성되었을때 반횐될 트리
     *
     * @return Node 담겨진 트리 반환 (최상위 노드는 자신의 번호와 부모의 번호가 같다.)
     * */
    fun makeTreeWithStack(edges: Array<HashSet<Int>>, root: Int, tree: HashMap<Int, Node> = hashMapOf()): HashMap<Int, Node> {
        val visited = hashSetOf<Int>()
        val stack = Stack<Pair<Int, Int>>()
        stack.push(Pair(root, root))
        while (stack.isNotEmpty()) {
            val (node, parent) = stack.pop()
            if(visited.contains(node)) {
                tree[parent]?.child?.add(node)
                /**리프노드*/
                println(node)
                continue
            }
            visited.add(node)
            tree[node] = Node(node, parent)
            stack.push(Pair(node, parent))
            for (e in edges[node]) {
                if(visited.contains(e)) continue
                stack.push(Pair(e, node))
            }
        }

        return tree
    }

    /**start 노드로부터 가장 먼 거리의 노드와 그 거리를 반환한다.
     * @param edges n번 노드와 연결된 노드들의 집합, 연결된 노드는 Pair<노드번호, 가중치> 형태로 저장*/
    fun getMaxLength(edges: Array<HashSet<Pair<Int,Int>>>, start: Int): Pair<Int, Int> {
        var maxSum = 0
        var maxNode = start
        val visited = BooleanArray(edges.size+1) { false }

        fun dfs(node: Int, sum :Int = 0) {
            visited[node] = true
            if(maxSum < sum) {
                maxSum = sum
                maxNode = node
            }
            for (pair in edges[node]) {
                if (visited[pair.first]) continue
                dfs(pair.first, sum+pair.second)
            }
        }
        dfs(start)
        return Pair(maxNode, maxSum)
    }

    /**start 노드로부터 각 정점간의 거리를 배열의 형태로 반환한다. (bfs 사용)  ex) dist[3] -> start노드에서 3번노드까지의 거리(가중치합)
     * @param edges n번 노드와 연결된 노드들의 집합, 연결된 노드는 Pair<노드번호, 가중치> 형태로 저장*/
    fun getMaxLength2(edges: Array<HashSet<Pair<Int,Int>>>, start: Int): IntArray {
        val visited = BooleanArray(edges.size+1) { false }
        val dist = IntArray(edges.size+1)
        val que : Queue<Int> = LinkedList()
        que.offer(0)
        while (que.isNotEmpty()) {
            val node = que.poll()
            visited[node] = true
            for (pair in edges[node]) {
                if(visited[pair.first] || node == pair.first) continue
                que.offer(pair.first)
                dist[pair.first] = dist[node] + pair.second
            }
        }

        var maxDist = 0 //최대거리
        var maxNode = start //최대거리인 노드 번호
        dist.forEachIndexed { index, i ->
            if (maxDist < i ) {
                maxDist = i
                maxNode = index
            }
        }

        return dist
    }


    /**간선정보가 주어졌을 때 해당 그래프에서 트리의 갯수를 반환한다.
     * @param edges 간선 정보 (부모/자식 개념 없이 모든 간선 포함)
     * ex) edges[1] = {2,3,4} -> 1번 정점에 연결된 정점은 2,3,4번 */
    fun getTreeCount(edges:Array<HashSet<Int>>): Int {
        var treeCount = 0

        val visited = hashSetOf<Int>()
        val unVisited = (1 until edges.size).toHashSet()
        while (unVisited.isNotEmpty()) {
            val target = unVisited.iterator().next()
            unVisited.remove(target)

            var isCycle = false
            /**dfs를 사용해 특정 노드를 기준으로 순회를 하고 순회중 이미 방문한곳을 한번 더 방문하게 되면 싸이클이 발생된다는 사실을 통해 해당 노드를 포함하는 그래프에 싸이클이 있는지 여부를 판단*/
            fun dfs(current :Int, before:Int) {
                visited.add(current)
                unVisited.remove(current)
                for (n in edges[current]) {
                    if(n == before) continue
                    if(visited.contains(n) || current == before) {
                        isCycle = true
                        continue    // 싸이클을 확인했지만 break 하지 않고 continue를 하는 이유는 싸이클이 발상한 현재 그래프와 연결된 모든 정점들을 찾아 unVisited에서 제외시키기 위함이다.
                    }
                    dfs(n, current)
                }
            }
            dfs(target, 0)
            if(!isCycle){
                treeCount++
            }
        }
        return treeCount
    }
}