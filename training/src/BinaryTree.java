import java.util.LinkedList;
import java.util.List;

public class BinaryTree {
    private LinkedList<Node> res;

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

    public void preOrder(Node root) {
        if (root == null) {
            return;
        }
        res.add(root);
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder(Node root) {
        if (root == null) {
            return;
        }
        preOrder(root.left);
        res.add(root);
        preOrder(root.right);
    }

    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        preOrder(root.left);
        preOrder(root.right);
        res.add(root);
    }

    public void preOrderStack(Node root) {
        res.clear();
        LinkedList<Node> stack = new LinkedList<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            Node node = stack.removeLast();
            res.add(node);
            if (node.right != null) {
                stack.addLast(node.right); //debug
            }
            if (node.left != null) {
                stack.addLast(node.left);
            }
        }
    }

    public void inOrderStack(Node root) {
        res.clear();
        LinkedList<Node> stack = new LinkedList<>();
        stack.addLast(root);
        Node node = root.left;
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

    public void postOrderStack(Node root) {
        res.clear();
        LinkedList<Node> stack = new LinkedList<>();
        stack.addLast(root);
        Node pre = null;
        while (!stack.isEmpty()) {
            Node node = stack.getLast();
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

    public void levelOrderStack(Node root) {
        res.clear();
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            Node node = queue.removeFirst();
            res.add(node);
            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
        }
    }

    public int countNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + 1 + countNodes(root.right);
    }

    public int countLeaves(Node root) {
        //int res = 0; //optimize
        if (root == null) {
            return 0;
        }
        if ((root.left == null) && (root.right == null)) {
            return 1;
        }
        return countLeaves(root.left) + countLeaves(root.right);
    }

    public int getDepth(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getDepth(root.left) + 1, getDepth(root.right) + 1);
    }

    public int getKLevel(Node root, int k) { //default: k is valid
        if (root == null) {
            return 0;
        }
        if (k == 1) {
            return 1;
        }
        return getKLevel(root.left, k - 1) + getKLevel(root.right, k - 1);
    }

    public boolean isSameTree(Node p, Node q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public Node invertTree(Node root) {
        if (root == null) {
            return null;
        }
        Node left = root.left;
        root.left = root.right;
        root.right = left;
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        return root;
    }

    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if (root == null) {
            return null;
        }
        if ((root == p) || (root == q)) {
            return root;
        }
        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);

        if ((left != null) && (right != null)) {
            return root;
        }
        if (left != null) {
            return left;
        }
        return right;
    }



}
