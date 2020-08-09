package structure.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Xuxx3309
 * @Description
 * @create 2020-07-28 3:12
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    //前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树，无法遍历");
        }

    }

    /**
     * 创建赫夫曼树
     *
     * @param arr 数组
     * @return 创建好后的赫夫曼树的root节点
     */
    public static Node createHuffmanTree(int[] arr) {
        //为了操作方便，将arr数组的值转为Node并存入ArrayList
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            //对ArrayList中的节点排序(从小到大)
            Collections.sort(nodes);

            //(1)取出权值最小的结点(二叉树)
            Node leftNode = nodes.get(0);
            //(2)取出权值第二小的结点(二叉树)
            Node rightNode = nodes.get(1);
            //(3)构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //(4)从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //(5)将parent加入到ArrayList
            nodes.add(parent);
        }

        return nodes.get(0);
    }
}

//创建节点
class Node implements Comparable<Node> {
    //权值
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.value - o.value;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
