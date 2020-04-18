import java.util.HashMap;
import java.util.LinkedList;


class Solution {
    public int findCircleNum(int[][] M) {
        LinkedList<Integer> queue = new LinkedList<>();
        int n = M.length;
        boolean[] visited = new boolean[n];
        int cnt=0;

        for (int i=0;i<n;i++) {
            if (!visited[i]) {
                queue.addLast(i);
                visited[i]=true;
                cnt+=1;
            }
            while (!queue.isEmpty()) {
                int node = queue.removeFirst();
                for (int j=0;j<n;j++) {
                    if ((M[node][j]==1) && (!visited[j])) {
                        queue.addLast(j);
                        visited[j]=true;
                    }
                }
            }
        }
        return cnt;
    }
}
