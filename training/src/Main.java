import java.util.*;

public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String myString = scanner.next();
//        int myInt = scanner.nextInt();
//        scanner.close();

//        Map<Integer, TreeNode> nodes = new HashMap<>();
//        for (int i = 1; i < 8; i++) {
//            nodes.put(i, new TreeNode(i));
//        }
//        nodes.get(1).left = nodes.get(2);
//        nodes.get(1).right = nodes.get(3);
//        nodes.get(2).left = nodes.get(4);
//        nodes.get(3).left = nodes.get(5);
//        nodes.get(4).right = nodes.get(6);
//        nodes.get(6).right = nodes.get(7);
//        BinaryTree sol = new BinaryTree();
        //sol.levelOrderStack(nodes.get(1));
        //System.out.println(sol.lowestCommonAncestor(nodes.get(1),nodes.get(3),nodes.get(7)));
        int[] preorder = new int[]{30909, -4466, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        BinaryTree sol = new BinaryTree();
        General gen = new General();
        General.Combination com = new General.Combination();
        int[][] x = new int[1][1];
        x[0][0] = 1;
        Graph graph = new Graph();
        int[][] matrix = {{
                1, 2, 3,4},
                { 5, 6,7,8},
                {7, 8, 9}
        };
        int[][] edges = {
                {2, 1},
                {3, 1},
                {4, 2},
                {5, 1},
                {6, 2},
                {7, 5},
                {8, 6},
                {9, 8},
                {10, 5},
        };
        List<String> li = new LinkedList<String>();
        li.add("BCG");
        li.add("CDE");
        li.add("GEA");
        li.add("FFF");
        //System.out.println(graph.findCheapestPrice(5,new int[][]{{4,1,1},{1,2,3},{0,3,2 },{0,4,10},{3,1,1},{1,4,3}},2,1,1));
        System.out.println(gen.numDecodings("12"));
        //"(1+(4+5+2)-3)+(6+8)"

    }
}
