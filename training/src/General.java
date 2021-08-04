import java.util.*;

public class General {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int i = 0;
        int j = matrix[0].length - 1;
        while ((i < matrix.length) && (j >= 0)) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    public String replaceSpace(String s) {
        return s.replaceAll(" ", "%20");
    }

    public int[] reversePrint(ListNode head) {
        LinkedList<Integer> stack = new LinkedList<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node.val);
            node = node.next;
        }
        int[] res = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            res[i] = stack.pop();
            i++;
        }
        return res;
    }

    public int numWays(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % (1000000007);
        }
        return dp[n];
    }

    public boolean isMatch(String s, String p) {

        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) { //length index
            for (int j = 0; j <= s.length(); j++) {
                if (p.charAt(i - 1) == '.') {
                    if ((j >= 1) && (dp[i - 1][j - 1])) {
                        dp[i][j] = true;
                    }
                } else if (p.charAt(i - 1) == '*') {
                    if ((j >= 1) && (p.charAt(i - 2) == s.charAt(j - 1) || p.charAt(i - 2) == '.') && (dp[i][j - 1])) {
                        dp[i][j] = true;
                    } else if ((dp[i - 2][j])) {
                        dp[i][j] = true;
                    }
                } else {
                    if ((j >= 1) && p.charAt(i - 1) == s.charAt(j - 1) && dp[i - 1][j - 1]) {
                        dp[i][j] = true;
                    }
                }
            }
        }
        return dp[p.length()][s.length()];
    }

    public String reverseNum(int nums) {
        String res = "";
        int n = nums;
        if (nums < 0) {
            res = res.concat("-");
            n = -nums;
        }
        return recurReverseNum(n, res);


    }

    public String recurReverseNum(int nums, String res) {
        String out = res.concat(String.valueOf(nums % 10));
        if (Math.abs(nums) < 10) {
            return out;
        }

        return recurReverseNum(nums / 10, out);

    }

    public String removeKdigits(String num, int k) {
        StringBuilder res = new StringBuilder();
        int head = 0;
        while ((k > 0) && (head < num.length())) {
            int idx = smamllestDigits(num.substring(head, Math.min(head + k + 1, num.length())));
            res.append(num.charAt(idx + head));
            head += idx + 1;
            k -= idx;
        }
        res.append(num.substring(head));
        while (res.length() > 0 && res.charAt(0) == '0') {
            res.deleteCharAt(0);
        }
        String out = res.substring(0, Math.max(0, res.length() - k));
        if (out.length() == 0) {
            out = "0";
        }
        return out;
    }

    public int smamllestDigits(String num) {
        int resIdx = 0;
        char ch = num.charAt(0);
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) < ch) {
                ch = num.charAt(i);
                resIdx = i;
            }
        }
        return resIdx;
    }

//    public boolean isValid(String str) {
//        // Write your code here
//        LinkedList<String> stack = new LinkedList<>();
//        int index = 0;
//        while (index < str.length()) {
//            if (str.charAt(index) == '<') { //label
//                int end = getLabelEnd(str, index + 1);
//                if (end < 0) {
//                    return false;
//                }
//                String label = str.substring(index + 1, end);
//                index = end + 1;
//                if (label.equals("") || label.equals("/")) {
//                    return false;
//                }
//                if (label.charAt(0) == '/') { //tail label
//                    if (stack.isEmpty()) {
//                        return false;
//                    }
//                    String headLabel = stack.pop();
//                    if (!headLabel.equals(label.substring(1))) {
//                        return false;
//                    }
//                } else { //head label
//                    stack.push(label);
//                }
//            } else if (str.charAt(index) == '>') {
//                return false;
//            } else {
//                index++;
//            }
//        }
//        if (!stack.isEmpty()) {
//            return false;
//        }
//        return true;
//    }
//
//    public int getLabelEnd(String str, int begin) {
//        // Write your code here
//        for (int i = begin; i < str.length(); i++) {
//            if (str.charAt(i) == '>') {
//                return i;
//            } else if (str.charAt(i) == '<') {
//                return -1;
//            }
//        }
//        return str.length();
//    }

    public boolean isValid(String str) {

        if (str == null || str.isEmpty()) {
            return true;
        }
        LinkedList<String> stack = new LinkedList<>();
        int i = 0;
        while (i < str.length()) {
            if (str.charAt(i) == '<') {
                int tagEnd = str.indexOf('>', i);
                if (tagEnd < 0) {
                    return false;
                }
                String tag = str.substring(i + 1, tagEnd);
                if (tag.equals("") || tag.equals("/")) {
                    return false;
                }
                if (tag.startsWith("/")) {//tail
                    if (stack.isEmpty() || !tag.substring(1).equals(stack.pop())) {
                        return false;
                    }
                } else {//head
                    stack.push(tag);
                }
                i = tagEnd + 1;
            } else if (str.charAt(i) == '>') {
                return false;
            } else {
                i++;
            }
        }
        return stack.isEmpty();
    }

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        if (numerator < 0 ^ denominator < 0) {
            res.append("-");
        }
        long num = Math.abs(Long.valueOf(numerator));
        long denom = Math.abs(Long.valueOf(denominator));
        res.append(num / denom); //integer
        long remain = (num % denom) * 10;
        if (remain == 0) {
            return res.toString();
        }
        res.append(".");
        StringBuilder raw = new StringBuilder();
        HashMap<Long, Integer> cycle = new HashMap<>();
        while (remain != 0) {
            long bit = remain / denom;
            if (cycle.containsKey(remain)) {
                res.append(raw.substring(0, cycle.get(remain)));
                res.append("(");
                res.append(raw.substring(cycle.get(remain)));
                res.append(")");
                return res.toString();
            }
            cycle.put(remain, raw.length());
            raw.append(remain / denom);
            remain = (remain % denom) * 10;
        }
        res.append(raw);
        return res.toString();

    }

    // 路径：记录在 track 中
// 选择列表：nums 中不存在于 track 的那些元素
// 结束条件：nums 中的元素全都在 track 中出现
    void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if (track.size() == nums.length) {
            //res.add(new LinkedList(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (track.contains(nums[i]))
                continue;
            // 做选择
            track.add(nums[i]);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }


    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        int b = matrix.length - 1;
        int r = matrix[0].length - 1;
        int l = 0;
        int t = 0;
        int cnt = (b + 1) * (r + 1);
        while (cnt > 0) {
            for (int i = l; i <= r && cnt > 0; i++) {
                res.add(matrix[t][i]);
                cnt--;
            }
            t++;
            for (int i = t; i <= b && cnt > 0; i++) {
                res.add(matrix[i][r]);
                cnt--;
            }
            r--;
            for (int i = r; i >= l && cnt > 0; i--) {
                res.add(matrix[b][i]);
                cnt--;
            }
            b--;
            for (int i = b; i >= t && cnt > 0; i--) {
                res.add(matrix[i][l]);
                cnt--;
            }
            l++;
        }
        return res;
    }

    public int XORTree(int n, int[][] edges, int[] original, int[] target) {
        cnt = 0;
        LinkedList<Integer>[] adj = new LinkedList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int[] edge : edges) { //create tree
            adj[edge[1]].add(edge[0]);
            adj[edge[0]].add(edge[1]); //to perform dfs
        }
        XORTreeDFS(adj, original, target, 1, 1, 0, 0);
        return cnt;
    }

    private int cnt;

    public void XORTreeDFS(LinkedList<Integer>[] adj, int[] original, int[] target, int node, int father, int thisLayerFlipCnt, int nextLayerFlipCnt) {
        int newNext = thisLayerFlipCnt;
        if ((original[node] ^ thisLayerFlipCnt) != target[node]) {
            newNext ^= 1; //flipcnt++;
            cnt++;
        }
        for (Integer neighbor : adj[node]) {
            if (neighbor != father) {
                XORTreeDFS(adj, original, target, neighbor, node, nextLayerFlipCnt, newNext);
            }
        }
    }

    LinkedList<Integer> pourWater(LinkedList<Integer> heights, int V, int K) {
        while (V > 0) {
            int l = K;
            while (l > 0 && heights.get(l - 1) <= heights.get(l)) {
                l--;
            }
            while (l < K && heights.get(l).equals(heights.get(l + 1))) {
                l++;
            }
            if (l != K) { //fill in left
                heights.set(l, heights.get(l) + 1);
                V--;
                continue;
            }
            int r = K;
            while (r < heights.size() - 1 && heights.get(r + 1) <= heights.get(r)) {
                r++;
            }
            while (r > K && heights.get(r - 1).equals(heights.get(r))) {
                r--;
            }
            heights.set(r, heights.get(r) + 1);
            V--;
        }
        return heights;
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new LinkedList<>();
        HashMap<String, Integer> word2idx = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            word2idx.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                String prefix = words[i].substring(0, j);
                String suffix = words[i].substring(j);
                String prefixReverse = new StringBuilder(prefix).reverse().toString();
                String suffixReverse = new StringBuilder(suffix).reverse().toString();
                if (isPalindrome(suffix) && word2idx.containsKey(prefixReverse) && word2idx.get(prefixReverse) != i) {
                    List<Integer> resPair = new LinkedList<>();
                    resPair.add(i);
                    resPair.add(word2idx.get(prefixReverse));
                    res.add(resPair);
                }

                if (isPalindrome(prefix) && word2idx.containsKey(suffixReverse) && word2idx.get(suffixReverse) != i) {
                    List<Integer> resPair = new LinkedList<>();
                    resPair.add(word2idx.get(suffixReverse));
                    resPair.add(i);
                    res.add(resPair);
                }
            }
        }
        return res;
    }

    public boolean isPalindrome(String str) {
        int head = 0;
        int tail = str.length() - 1;
        while (head < tail) {
            if (str.charAt(head) != str.charAt(tail)) {
                return false;
            }
            head++;
            tail--;
        }
        return true;
    }

    PriorityQueue<Integer> smallerHalf = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    PriorityQueue<Integer> biggerHalf = new PriorityQueue<>();

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */
    public void addNum(int num) {
        smallerHalf.add(num);
        biggerHalf.add(smallerHalf.poll());

        if (smallerHalf.size() < biggerHalf.size()) {
            smallerHalf.add(biggerHalf.poll());
        }

    }

    public double findMedian() {
        if (smallerHalf.size() > biggerHalf.size()) {
            return smallerHalf.peek();
        } else if (smallerHalf.size() == 0) {
            return 0;
        } else {
            return (double) (smallerHalf.peek() + biggerHalf.peek()) / 2;
        }

    }

    static class Combination {
        List<List<Integer>> res = new LinkedList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            res.clear();

            //Arrays.sort(candidates,Collections.reverseOrder());
            Arrays.sort(candidates);
            combinationSumDFS(candidates, 0, target, new LinkedList<Integer>());

            return res;

        }

        public void combinationSumDFS(int[] candidates, int idx, int remain, List<Integer> path) {
            if (remain == 0) {
                res.add(path);
                return;
            }
            if (idx >= candidates.length || remain < 0) {
                return;
            }
            //drop
            combinationSumDFS(candidates, idx + 1, remain, new LinkedList<Integer>(path));
            //add
            path.add(candidates[idx]);
            combinationSumDFS(candidates, idx, remain - candidates[idx], new LinkedList<Integer>(path));
        }

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            res.clear();

            ArrayList<int[]> nums = new ArrayList<>();
            Arrays.sort(candidates);
            for (int i = 0; i < candidates.length; i++) {
                if (i > 0 && candidates[i] == candidates[i - 1]) {
                    nums.get(nums.size() - 1)[1]++;
                } else {
                    nums.add(new int[]{candidates[i], 1});
                }

            }

            combinationSum2DFS(nums, 0, target, new LinkedList<Integer>(), nums.get(0)[1]);

            return res;

        }

        public void combinationSum2DFS(ArrayList<int[]> candidates, int idx, int remain, List<Integer> path, int times) {
            if (remain == 0) {
                res.add(path);
                return;
            }
            if (idx >= candidates.size() || remain < 0) {
                return;
            }
            //drop
            int nexttimes = 0;
            if (idx + 1 >= candidates.size()) {
                nexttimes = 0;
            } else {
                nexttimes = candidates.get(idx + 1)[1];
            }
            combinationSum2DFS(candidates, idx + 1, remain, new LinkedList<Integer>(path), nexttimes);
            //add
            path.add(candidates.get(idx)[0]);
            times--;
            if (times > 0) {
                combinationSum2DFS(candidates, idx, remain - candidates.get(idx)[0], new LinkedList<Integer>(path), times);

            } else {
                combinationSum2DFS(candidates, idx + 1, remain - candidates.get(idx)[0], new LinkedList<Integer>(path), nexttimes);

            }
        }
    }

    public String removeDuplicateLetters(String s) {
        HashMap<Character, Integer> keyCnt = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (keyCnt.containsKey(ch)) {
                keyCnt.put(ch, keyCnt.get(ch) + 1);
            } else {
                keyCnt.put(ch, 1);
            }
        }
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (!stack.contains(ch)) { //debug
                //System.out.println(keyCnt.get(stack.peek()));
                while (!stack.isEmpty() && keyCnt.get(stack.peek()) > 0 && stack.peek() > ch) {

                    stack.pop();
                }
                stack.push(ch);
            }
            keyCnt.put(ch, keyCnt.get(ch) - 1);//debug
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.insert(0, stack.pop());
        }

        return res.toString();
    }

    public int splitArray(int[] nums, int m) {
        int head = nums[0]; //max of array
        int tail = 0; //sum of array
        for (int num : nums) {
            tail += num;
            if (num > head) {
                head = num;
            }
        }
        while (head < tail) {
            int mid = (head + tail) / 2;
            if (split(nums, m, mid)) { ////latter half
                head = mid + 1;
            } else {
                tail = mid;
            }
        }
        return head;
    }

    public boolean split(int[] nums, int cnt, int threshold) {
        int i = 0;
        while (i < nums.length) {
            int sum = 0;
            while (i < nums.length) {
                if (sum + nums[i] > threshold) {
                    break; //debug
                }
                sum += nums[i];
                i++;
            }
            cnt--;
            if (cnt < 0) {
                return true; //latter half
            }
        }
        return false;//former half
    }


    public void printSierpinskiFractal(int n) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int myInt = scanner.nextInt();
            if (myInt == 0) {
                break;
            }
            char[][] shape = new char[(int) Math.pow(2, myInt)][(int) Math.pow(2, myInt + 1)];
            printSierpinskiFractalRecur(shape, myInt, 0, (int) (Math.pow(2, myInt) - 1));
        }
        scanner.close();
    }

    public void printSierpinskiFractalRecur(char[][] shape, int n, int x, int y) {
        if (n == 1) {
            shape[x][y] = '/';
            shape[x][y + 1] = '\\';
            shape[x - 1][y + 1] = '/';
            shape[x + 1][y + 2] = '\\';
            shape[x + 1][y] = '_';
            shape[x + 1][y + 1] = '_';
            return;
        }
        printSierpinskiFractalRecur(shape, n - 1, x, y);
        printSierpinskiFractalRecur(shape, n - 1, (int) (x + Math.pow(2, n - 1)), (int) (y - Math.pow(2, n - 2)));
        printSierpinskiFractalRecur(shape, n - 1, (int) (x + Math.pow(2, n - 1)), (int) (y + Math.pow(2, n - 2)));
    }

    public int calculate(String s) { //TODO
        LinkedList<Integer> numStack = new LinkedList<>();
        LinkedList<Integer> opStack = new LinkedList<>(); //+-:+-1,(:0
        if (s.charAt(0) == '-') {
            numStack.push(0);
        }
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                continue;
            } else if (ch == '(') {
                opStack.push(0);
            } else if (ch == ')') {
                int val = 0;
                opStack.pop(); //pop 0
                if (!opStack.isEmpty() && opStack.peek() != 0) {
                    int newVal = numStack.pop() * opStack.pop();
                    numStack.push(newVal);
                }

            } else if (ch == '+') {
                opStack.push(1);
            } else if (ch == '-') {
                opStack.push(-1);
            } else { //digit
                int num = ch - '0';
                i++;
                while (i < s.length() && s.charAt(i) < '9' && s.charAt(i) > '0') {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                if (!opStack.isEmpty() && opStack.peek() != 0) {
                    num = numStack.pop() + num * opStack.pop();
                }
                numStack.push(num);
            }
        }
        int val = 0;
        while (!opStack.isEmpty()) {
            val += numStack.pop() * opStack.pop();
        }
        val += numStack.pop();
        return val;
    }

//    private int findSteps(int num) {
//
//    }
//    public int findLongestSteps(int num) {
//
//    }

    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        if (!s.isEmpty() && numDecodingIsValid(s.substring(0, 1))) {
            dp[1] = 1;
        }
        for (int i = 1; i < s.length(); i++) {
            dp[i + 1] = 0;
            if (numDecodingIsValid(s.substring(i, i + 1))) {
                dp[i + 1] += dp[i];
            }
            if (numDecodingIsValid(s.substring(i - 1, i + 1))) {
                dp[i + 1] += dp[i - 1];
            }
        }
        return dp[s.length()];
    }

    public boolean numDecodingIsValid(String s) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            num = num * 10 + s.charAt(i) - '0';
        }
        return !s.isEmpty() && s.charAt(0) - '0' > 0 && num <= 26 && num >= 1;
    }

}
























































