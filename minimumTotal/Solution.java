import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {

        List<Integer> dp = new LinkedList<>();
        dp.add(triangle.get(0).get(0));
        //init
        for (int i=1;i<triangle.size();i++) {
            int j;
            dp.add(dp.get(dp.size()-1)+triangle.get(i).get(dp.size())); //spec tail
            for (j=dp.size()-2;j>0;j--) { //debug: reverse override
                dp.set(j,Math.min(dp.get(j-1),dp.get(j))+triangle.get(i).get(j));
            }
            dp.set(0,dp.get(0)+triangle.get(i).get(0)); //spec head
        }
        Collections.sort(dp);
        return dp.get(0);
    }
}