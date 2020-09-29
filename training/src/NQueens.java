import java.util.LinkedList;
import java.util.List;

public class NQueens {
    List<List<Integer>> res = new LinkedList<>();

    public List<List<String>> solveNQueens(int n) {
        int[] nums = new int[n];
        for (int i = 1; i < n; i++) {
            nums[i - 1] = i;
        }
        backtrack(nums, new LinkedList<>());
        return outFormat(n, res);
    }

    public List<List<String>> outFormat(int size, List<List<Integer>> data) {
        List<List<String>> res = new LinkedList<>();
        String format = "";
        for (int i = 0; i < size - 1; i++) {
            format = format.concat(".");
        }
        for (int i = 0; i < data.size(); i++) {
            List<String> solutions = new LinkedList<>();
            for (int j = 0; j < data.get(i).size(); j++) {
                solutions.add(format.substring(0, data.get(i).get(j)) + "Q" + format.substring(data.get(i).get(j)));
            }
            res.add(new LinkedList<>(solutions));
        }
        return res;
    }

    public void backtrack(int[] nums, LinkedList<Integer> path) {
        if (path.size() == nums.length) {
            res.add(new LinkedList<Integer>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if ((path.contains(nums[i])) || ((path.size() > 0) && (Math.abs(nums[i] - path.getLast()) <= 1))) {

                continue;
            }
            path.add(nums[i]);
            backtrack(nums, path);
            path.removeLast();
        }
    }
}
