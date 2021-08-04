import java.util.HashMap;
import java.util.LinkedList;

public class BinaryTree {
    private LinkedList<TreeNode> res;

    public BinaryTree() {

        res = new LinkedList<>();
    }

    public LinkedList<Integer> getRes() {
        LinkedList<Integer> vals = new LinkedList<>();
        for (int i = 0; i < res.size(); i++) {
            vals.add(res.get(i).val);
        }
        return vals;
    }

    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root);
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        preOrder(root.left);
        res.add(root);
        preOrder(root.right);
    }

    public void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        preOrder(root.left);
        preOrder(root.right);
        res.add(root);
    }

    public void preOrderStack(TreeNode root) {
        res.clear();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.removeLast();
            res.add(node);
            if (node.right != null) {
                stack.addLast(node.right); //debug
            }
            if (node.left != null) {
                stack.addLast(node.left);

            }
        }
    }

    public void inOrderStack(TreeNode root) {
        res.clear();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        TreeNode node = root.left;
        while (!stack.isEmpty() || node != null) { //debug
            while (node != null) {
                stack.addLast(node);
                node = node.left;
            }
            node = stack.removeLast();
            res.add(node);
            node = node.right; //debug
        }
    }

    public void postOrderStack(TreeNode root) {
        res.clear();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.addLast(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode node = stack.getLast();
            if (((node.left == null) && (node.right == null)) || ((pre != null) && ((pre == node.left) || (pre == node.right)))) {
                res.add(node);
                pre = node;
                stack.removeLast();
                continue;
            }
            if (node.right != null) {
                stack.addLast(node.right);
            }
            if (node.left != null) {
                stack.addLast(node.left);
            }

        }
    }

    public void levelOrderStack(TreeNode root) {
        res.clear();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();
            res.add(node);
            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
        }
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + 1 + countNodes(root.right);
    }

    public int countLeaves(TreeNode root) {
        //int res = 0; //optimize
        if (root == null) {
            return 0;
        }
        if ((root.left == null) && (root.right == null)) {
            return 1;
        }
        return countLeaves(root.left) + countLeaves(root.right);
    }

    public int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getDepth(root.left) + 1, getDepth(root.right) + 1);
    }

    public int getKLevel(TreeNode root, int k) { //default: k is valid
        if (root == null) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        return getKLevel(root.left, k - 1) + getKLevel(root.right, k - 1);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        return root;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if ((root == p) || (root == q)) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if ((left != null) && (right != null)) {
            return root;
        }
        if (left != null) {
            return left;
        }
        return right;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        HashMap<Integer, Integer> dictPre = new HashMap<>(); //val,index
        HashMap<Integer, Integer> dictIn = new HashMap<>();
        for (int i = 0; i < preorder.length; i++) {
            dictPre.put(preorder[i], i);
            dictIn.put(inorder[i], i);
        }
        return recur(preorder,inorder,dictPre,dictIn,root,0,preorder.length-1);
    }

    public TreeNode recur(int[] preorder, int[] inorder,
                      HashMap<Integer, Integer> dictPre, HashMap<Integer, Integer> dictIn,
                      TreeNode root, int left, int right) {
        int midIndex = dictIn.get(root.val);
        int leftCnt = midIndex - left;
        int rightCnt = right - midIndex;
        if (leftCnt > 0) {
            root.left = new TreeNode(preorder[dictPre.get(root.val) + 1]);
            root.left=recur(preorder,inorder,dictPre,dictIn,root.left, left, midIndex-1);
        }
        if (rightCnt > 0) {
            root.right = new TreeNode(preorder[dictPre.get(root.val) + leftCnt + 1]);
            root.right=recur(preorder,inorder,dictPre,dictIn,root.right, midIndex+1, right);
        }
        return root;
    }

    public int numWays(int n) {
        int[] dp = new int[(n+1)];
        dp[0]=0;
        dp[1]=1;
        for (int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }


}
