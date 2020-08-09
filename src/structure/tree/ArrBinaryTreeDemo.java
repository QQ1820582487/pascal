package structure.tree;

/**
 * @author Xuxx3309
 * @Description 顺序存储二叉树的特点:
 * 1)顺序二叉树通常只考虑完全二叉树
 * 2)第n个元素的左子节点为 2*n+1
 * 3)第n个元素的右子节点为 2* n+ 2
 * 4)第n个元素的父节点为 (n-1)/ 2
 * 5)n:表示顺序存储二叉树中的第几个元素(按0开始编号)
 * @create 2020-07-27 15:47
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
        System.out.println();
        arrayBinaryTree.infixOrder();
        System.out.println();
        arrayBinaryTree.postOrder();
    }
}

//数组实现顺序存储二叉树
class ArrayBinaryTree {
    //存储数据节点
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载
    public void preOrder() {
        System.out.print("前序遍历: ");
        this.preOrder(0);
    }

    public void infixOrder() {
        System.out.print("中序遍历: ");
        this.infixOrder(0);
    }

    public void postOrder() {
        System.out.print("后序遍历: ");
        this.postOrder(0);
    }

    /**
     * 顺序存储二叉树的前序遍历
     *
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        //如果数组为空
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法进行前序遍历");
            return;
        }
        //输出当前节点
        System.out.printf(arr[index] + "\t");
        //递归遍历左子树
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }
        //递归遍历右子树
        if ((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

    //中序遍历
    public void infixOrder(int index) {
        //如果数组为空
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法进行前序遍历");
            return;
        }
        //递归遍历左子树
        if ((index * 2 + 1) < arr.length) {
            infixOrder(index * 2 + 1);
        }
        //输出当前节点
        System.out.printf(arr[index] + "\t");
        //递归遍历右子树
        if ((index * 2 + 2) < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }

    //后序遍历
    public void postOrder(int index) {
        //如果数组为空
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，无法进行前序遍历");
            return;
        }
        //递归遍历左子树
        if ((index * 2 + 1) < arr.length) {
            postOrder(index * 2 + 1);
        }
        //递归遍历右子树
        if ((index * 2 + 2) < arr.length) {
            postOrder(index * 2 + 2);
        }
        //输出当前节点
        System.out.printf(arr[index] + "\t");
    }
}