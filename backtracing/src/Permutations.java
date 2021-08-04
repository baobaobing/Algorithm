import java.util.LinkedList;
import java.util.List;

public class Permutations {
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> path = new LinkedList<>();
        backtracking(nums,path);
        return res;
    }

    public void backtracking(int[] nums, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new LinkedList<>(path)); //shallow copy debug
            return;
        }
        for (int i=0;i<nums.length;i++){
            if (path.contains(nums[i])){
                continue;
            }
            path.add(nums[i]);
            backtracking(nums,path);
            path.removeLast();
        }
    }
}
