import java.util.*;

public class Graph {
    private int[][] graph;
    private LinkedList<Integer> res;

    public Graph(int[][] graph) {
        res = new LinkedList<>();
        this.graph = graph;
    }

    public Graph() {
        res = new LinkedList<>();
    }

//    // 计算从起点 start 到终点 target 的最近距离
//    int BFS(Node start, Node target) {
//        Queue<Node> q; // 核心数据结构
//        Set<Node> visited; // 避免走回头路
//
//        q.offer(start); // 将起点加入队列
//        visited.add(start);
//        int step = 0; // 记录扩散的步数
//
//        while (q not empty) {
//            int sz = q.size();
//            /* 将当前队列中的所有节点向四周扩散 */
//            for (int i = 0; i < sz; i++) {
//                Node cur = q.poll();
//                /* 划重点：这里判断是否到达终点 */
//                if (cur is target)
//                return step;
//                /* 将 cur 的相邻节点加入队列 */
//                for (Node x : cur.adj())
//                    if (x not in visited) {
//                    q.offer(x);
//                    visited.add(x);
//                }
//            }
//            /* 划重点：更新步数在这里 */
//            step++;
//        }
//    }
//    public int[] dijk(int start) {
//    }

    public int[] dijkstra(int start) {

        int[] res = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        int node = start;
        visited[node] = true;
        for (int i = 0; i < graph.length; i++) {
            if (graph[node][i] > 0) {
                res[i] = graph[node][i];
            } else {
                res[i] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < graph.length - 1; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < graph.length; j++) { //find the nearest one
                if (res[j] < min && !visited[j]) {
                    node = j;
                    min = res[j];
                }
            }
            visited[node] = true;
            for (int j = 0; j < graph.length; j++) { //update dis
                if (graph[node][j] > 0 && res[node] + graph[node][j] < res[j]) {
                    res[j] = res[node] + graph[node][j];
                }
            }

        }
        return res;
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        //dp[v,k] =
        graph = new int[n][n];
        for (int[] flight : flights) {
            graph[flight[0]][flight[1]] = flight[2];
        } //create adj
        int[][] dp = new int[n][K + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i <= K; i++) {
            dp[src][i] = 0; //src2src
        }
        for (int i = 0; i < n; i++) {
            if (graph[src][i] != 0) {
                dp[i][0] = graph[src][i]; //src's neighbors
            }
        }
        for (int times = 1; times <= K; times++) {
            for (int node = 0; node < n; node++) {
                for (int neighbor = 0; neighbor < n; neighbor++) {
                    if (node != neighbor && graph[neighbor][node] != 0 &&
                            dp[neighbor][times - 1] != Integer.MAX_VALUE &&
                            graph[neighbor][node] + dp[neighbor][times - 1] < dp[node][times]) {
//                        System.out.println(graph[neighbor][node]);
//                        System.out.println(dp[neighbor][times - 1]);
                        dp[node][times] = graph[neighbor][node] + dp[neighbor][times - 1];
                    }
                }
            }
        }
        return dp[dst][K] == Integer.MAX_VALUE ? -1 : dp[dst][K];

    }

    public boolean exist(char[][] board, String word) {
        if (word.length() == 0) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existDfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean existDfs(char[][] board, String word, int i, int j, int index) {
        if (!(isValidIndex(board, i, j))) {
            return false;
        }
        if (board[i][j] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        char tmp = board[i][j];
        board[i][j] = '/';
        boolean res = (existDfs(board, word, i + 1, j, index + 1) || existDfs(board, word, i - 1, j, index + 1) ||
                existDfs(board, word, i, j + 1, index + 1) || existDfs(board, word, i, j - 1, index + 1));
        board[i][j] = tmp;
        return res;
    }

    public boolean isValidIndex(char[][] board, int i, int j) {
        return (i >= 0) && (i < board.length) && (j >= 0) && (j < board[0].length);
    }

    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        return movingCountDfs(res, visited, 0, 0, k);
    }

    public int movingCountDfs(int res, boolean[][] visited, int m, int n, int k) {
        if (!isValidIndex(visited, m, n)) {
            return res;
        }
        if (visited[m][n] || ((BitsSum(m) + BitsSum(n)) > k)) {
            return res;
        }
        visited[m][n] = true;
        res += 1;
        res = movingCountDfs(res, visited, m + 1, n, k);
        res = movingCountDfs(res, visited, m - 1, n, k);
        res = movingCountDfs(res, visited, m, n + 1, k);
        res = movingCountDfs(res, visited, m, n - 1, k);
        return res;
    }

    public int BitsSum(int n) {
        int res = 0;
        while (n > 0) {
            res += n % 10;
            n = n / 10;
        }
        return res;
    }

    public boolean isValidIndex(boolean[][] board, int i, int j) {
        return (i >= 0) && (i < board.length) && (j >= 0) && (j < board[0].length);
    }

    //topological
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new LinkedList<>());
        }
        int[] inDegrees = new int[numCourses];
        for (int[] edge : prerequisites) {
            inDegrees[edge[0]]++;
            adj.get(edge[1]).add(edge[0]);
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.addLast(i);
            }
        }
        while (!queue.isEmpty()) {
            int node = queue.pop();
            numCourses--;
            for (int neighbor : adj.get(node)) {
                inDegrees[neighbor]--;
                if (inDegrees[neighbor] == 0) {
                    queue.addLast(neighbor);
                }
            }
        }
        if (numCourses == 0) {
            return true;
        }
        return false;
    }

    //dfs
    public boolean canFinishWDFSMethod(int numCourses, int[][] prerequisites) {
        //create adj
        List<List<Integer>> adj = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new LinkedList<>());
        }
        for (int[] edge : prerequisites) {
            adj.get(edge[1]).add(edge[0]);
        }
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (canFinishDFS(adj, visited, i)) {
                return false;
            }
        }
        return true;


    }

    public boolean canFinishDFS(List<List<Integer>> adj, int[] visited, int node) {
        if (visited[node] == -1) {
            return false;
        }
        if (visited[node] == 1) {
            return true;
        }
        visited[node] = 1;
        for (int neighbor : adj.get(node)) {
            if (canFinishDFS(adj, visited, neighbor)) {
                return true;
            }
        }
        visited[node] = -1;
        return false; //?
    }

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        HashMap<String, List<Character>> infer = new HashMap<>(); //construct rules
        for (String rule : allowed) {
            String children = rule.substring(0, 2);

            if (infer.containsKey(children)) {
                infer.get(children).add(rule.charAt(2));
            } else {
                List<Character> options = new LinkedList<>();
                options.add(rule.charAt(2));
                infer.put(children, options);
            }
        }
        return pyramidTransitionDFS(bottom, infer, "", 0);
    }

    public boolean pyramidTransitionDFS(String bottom, HashMap<String, List<Character>> infer, String current, int pos) {
        if (current.length() == bottom.length() - 1) {
            return current.length() == 1 || pyramidTransitionDFS(current, infer, "", 0);
        }
        String key = bottom.substring(pos, pos + 2);
        if (infer.containsKey(key)) {
            for (Character option : infer.get(key)) {
                if (pyramidTransitionDFS(bottom, infer, current + option, pos + 1)) {
                    return true;
                }
            }
        }
        return false;

    }
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new LinkedList<>();
        int m = board.length;
        int n =board[0].length;
        for (String word:words){
            boolean[][]visited = new boolean[m][n];
            for(int i=0;i<m;i++){
                for (int j=0;j<n;j++){
                    if (!visited[i][j] && board[i][j]==word.charAt(0)){
                        if (findWordsDFS(board,visited,i,j,""+word.charAt(0),word)){
                            res.add(word);
                            i=m; //debug
                            j=n;
                        }
                    }
                }
            }
        }
        return res;
    }
    public boolean findWordsDFS(char[][] board, boolean[][]visited, int i, int j, String path, String word){
        if (path.equals(word)){
            return true;
        }

        visited[i][j]=true;
        for (int[] neighbor:findNeighbors(visited,board.length,board[i].length,i,j)){
            int ii=neighbor[0];
            int jj=neighbor[1];
            //System.out.println(word.charAt(path.length()));
            if(board[ii][jj]==word.charAt(path.length()) && findWordsDFS(board,visited,ii,jj,path+board[ii][jj],word)){
                return true;
            }
        }
        visited[i][j]=false;
        return false;
    }

    public List<int[]> findNeighbors(boolean[][]visited,int m ,int n,int i,int j){

        List<int[]> res = new LinkedList<>();
        if (isValidIdx(m,n,i-1,j) && !visited[i-1][j]){
            res.add(new int[]{i-1,j});
        }
        if (isValidIdx(m,n,i+1,j) && !visited[i+1][j]){
            res.add(new int[]{i+1,j});
        }
        if (isValidIdx(m,n,i,j-1) && !visited[i][j-1]){
            res.add(new int[]{i,j-1});
        }
        if (isValidIdx(m,n,i,j+1) && !visited[i][j+1]){
            res.add(new int[]{i,j+1});
        }
        return res;
    }

    public boolean isValidIdx(int m,int n,int i,int j){
        return i<m&&i>=0&&j<n&&j>=0;
    }


}

































