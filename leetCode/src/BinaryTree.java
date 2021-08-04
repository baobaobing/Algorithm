import java.util.LinkedList;

public class BinaryTree {

    private LinkedList<TreeNode> res;

    public BinaryTree() {
        res = new LinkedList<>();
    }

    public LinkedList<TreeNode> getRes() {
        return res;
    }

    public void setRes(LinkedList<TreeNode> res) {
        this.res = res;
    }

    public void preOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }
}
