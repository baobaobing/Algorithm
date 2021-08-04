import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Integer, TreeNode> nodes = new HashMap<>();
        for (int i = 1; i < 8; i++) {
            nodes.put(i, new TreeNode(i));
        }
        nodes.get(1).left = nodes.get(2);
        nodes.get(1).right = nodes.get(3);
        nodes.get(2).left = nodes.get(4);
        nodes.get(2).right = nodes.get(5);
        nodes.get(3).left = nodes.get(6);
        nodes.get(3).right = nodes.get(7);
        nodes.get(1).flatten(nodes.get(1));
        System.out.println();
    }

}
