import java.util.LinkedList;

/* 二叉树遍历框架 */
/*
void traverse(TreeNode root) {
        // 前序遍历
        traverse(root.left)
        // 中序遍历
        traverse(root.right)
        // 后序遍历
        }
*/

//根据题意，思考一个二叉树节点需要做什么，到底用什么遍历顺序就清楚了。

public class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;
    TreeNode next;

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public int getVal() {
        return val;
    }

    public TreeNode() {

    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode(int val) {
        setVal(val);
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        setVal(val);
        setLeft(left);
        setRight(right);
    }

    public TreeNode(TreeNode root) {
        setVal(root.val);
        setLeft(root.left);
        setRight(root.right);
    }

    public void traverse(TreeNode root) {

    }

    // [226. 翻转二叉树] easy
    // 前序遍历 后序遍历 pre-order in-order post-order traversal
    // 二叉树题目的一个难点就是，如何把题目的要求细化成每个节点需要做的事情。
    // 先搞清楚当前 root 节点该做什么，然后根据函数定义递归调用子节点
    // 对称递归
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        root.right = invertTree(root.right);
        root.left = invertTree(root.left);
        return root;
    }

    //[116. 填充每个节点的下一个右侧节点指针] medium
    //BFS or traversal TODO
    //层次遍历基于广度优先搜索，它与广度优先搜索的不同之处在于，广度优先搜索每次只会取出一个节点来拓展，而层次遍历会每次将队列中的所有元素都拿出来拓展
    //二叉树层次相关：BFS
    public TreeNode connect(TreeNode root) {
        if (root == null) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>(); //queue addLast+pop
        root.next = null;
        if (root.left == null) {
            return root;
        }
        queue.addLast(root.left); //第一个节点判断有点麻烦
        queue.addLast(root.right);
        queue.addLast(null);
        while (!queue.isEmpty()) {
            TreeNode node = queue.pop();
            if (node == null) { //debug：对于null的处理，root为null，root.left panic
                continue;
            }
            if (node.left != null) {
                queue.addLast(node.left);
                queue.addLast(node.right);
            }
            node.next = queue.peek(); //没有使用层次遍历的BFS，每次只弹出一个，而不是所有（一层）
            if (queue.peek() == null) {
                queue.addLast(null);
            }
        }
        return root;
    }

    // [114. 二叉树展开为链表] medium
    // 简单版 = pre-order traversal
    // 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？post-order traversal
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left == null) {
            return;
        }
        if (right != null) {
            while (left.right != null) { //暴力找到树叶，有时候直接考虑暴力方法
                left = left.right;
            }
            left.right = right;
        }

        root.right = root.left;
        root.left = null;
    }

    // [654. 最大二叉树] medium
    // 暴力找max值 + pre-order
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return null;
    }

    // [105. 从前序与中序遍历序列构造二叉树] medium
    // 把题目的要求细化，搞清楚根节点应该做什么，
    // 然后剩下的事情抛给前/中/后序的遍历框架就行了，千万不要跳进递归的细节里
    // [106. 从中序与后序遍历序列构造二叉树] medium
    // 同上
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreeRecur(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTreeRecur(int[] inorder, int inS, int inE, int[] postorder, int postS, int postE) {
        if (inE < inS) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postE]);
        int rootIdx = getRootIdx(inorder, postorder[postE]);
        int rightSize = inE - rootIdx;
        root.left = buildTreeRecur(inorder, inS, rootIdx - 1, postorder, postS, postE - rightSize - 1);
        root.right = buildTreeRecur(inorder, rootIdx + 1, inE, postorder, postE - rightSize, postE - 1);
        return root;
    }

    public int getRootIdx(int[] inorder, int val) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }

    // [652. 寻找重复的子树] medium
    // 用什么遍历方式
    // 先不要考虑复杂度，能解出来
    // 我如何才能知道以自己为根的二叉树长啥样？
    //其实看到这个问题，就可以判断本题要使用「后序遍历」框架来解决

    // [230. 二叉搜索树中第K小的元素]
    // BST的性质：inorder is sorted
    int index = 0;
    int res = 0;

    public int kthSmallest(TreeNode root, int k) {
        kthSmallestRecur(root, k);
        return res;
    }

    public void kthSmallestRecur(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        kthSmallestRecur(root.left, k);
        index++;
        if (index == k) {
            res = root.val;
            return;
        }
        kthSmallestRecur(root.right, k);
    }

}

