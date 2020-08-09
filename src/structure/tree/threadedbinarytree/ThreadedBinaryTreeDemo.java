package structure.tree.threadedbinarytree;

/**
 * @author Xuxx3309
 * @Description 线索化二叉树
 * @create 2020-07-27 16:40
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "test1");
        HeroNode node2 = new HeroNode(3, "test3");
        HeroNode node3 = new HeroNode(6, "test6");
        HeroNode node4 = new HeroNode(8, "test8");
        HeroNode node5 = new HeroNode(10, "test10");
        HeroNode node6 = new HeroNode(14, "test14");

        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(node1);
        tree.threadedNodes();

        //测试:以node5节点测试
        /*HeroNode node5Left = node5.getLeft();
        int node5LeftType = node5.getLeftType();
        System.out.println("node5Left:" + node5Left + "  node5LeftType:" + node5LeftType);
        HeroNode node5Right = node5.getRight();
        int node5RightType = node5.getRightType();
        System.out.println("node5Right:" + node5Right + "  node5RightType:" + node5RightType);*/

        //当线索化二叉树后，不能在使用原来的遍历方法,会陷入死循环
        //tree.infixOrder();

        System.out.println("使用线索化的方式进行前序遍历");
        tree.threadedPreList();//1 3 8 10 6 14
        System.out.println("使用线索化的方式进行中序遍历");
        tree.threadedInfixList();//8 3 10 1 14 6
        System.out.println("使用线索化的方式进行后序遍历");
        tree.threadedPostList();//8 10 3 14 6 1

    }

}

//线索化二叉树
class ThreadedBinaryTree {
    private HeroNode root;
    //指向当前结点的前驱节点（在进行递归线索化时,pre 总是指当前结点的前一个结点）
    private HeroNode preNode;

    //重载
    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //遍历线索化二叉树的方法  前序
    public void threadedPreList() {
        HeroNode node = root;
        while (node != null) {
            //输出当前节点
            System.out.println(node);

            //循环至leftType=1的节点，说明改节点时按照线索化处理后的节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
                System.out.println(node);
            }

            //如果当前结点的右指针指向的是后继结点，就一直输出
            while (node.getRightType() == 1) {
                //获取到当前结点的后继结点
                node = node.getRight();
            }
            node = node.getRight();
        }
    }

    //遍历线索化二叉树的方法  中序
    public void threadedInfixList() {
        HeroNode node = root;
        while (node != null) {
            //循环至leftType=1的节点，说明改节点时按照线索化处理后的节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //输出当前节点
            System.out.println(node);

            //如果当前结点的右指针指向的是后继结点，就一直输出
            while (node.getRightType() == 1) {
                //获取到当前结点的后继结点
                node = node.getRight();
            }
            //输出当前节点
            System.out.println(node);

            node = node.getRight();
        }
    }

    //遍历线索化二叉树的方法  后序
    public void threadedPostList() {
        //后续遍历为什么没写？因为后续遍历线索二叉树时还需要一个指针指向parent，逻辑相对复杂。
    }

    // 前序线索化二叉树
    public void preThreadedTree(HeroNode node) {
        // 如果node == null,此时该节点无法线索化
        if (node == null) {
            return;
        }
        // 先序列化当前节点
        if (node.getLeft() == null) {
            node.setLeft(preNode);
            node.setLeftType(1);
        }
        if (preNode != null && preNode.getRight() == null) {
            preNode.setRight(node);
            preNode.setRightType(1);
        }
        preNode = node;

        if (node.getLeftType() == 0) {
            preThreadedTree(node.getLeft());
        }
        if (node.getRightType() == 0) {
            preThreadedTree(node.getRight());
        }
    }

    //对二叉树进行 中序线索化
    public void threadedNodes(HeroNode node) {
        if (node == null) {
//            System.out.println("节点为空，无法线索化");
            return;
        }
        //(一)先线索化左子树
        threadedNodes(node.getLeft());
        //(二)线索化当前结点τ
        //处理当前结点的前驱结点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(preNode);
            node.setLeftType(1);
        }
        //处理当前结点的后继结点
        if (preNode != null && preNode.getRight() == null) {
            //让当前节点的左指针指向前驱节点
            preNode.setRight(node);
            preNode.setRightType(1);
        }
        //每处理一个结点后，把当前结点赋值为下一个结点的前驱结点
        preNode = node;

        //(三)在线索化右子树
        threadedNodes(node.getRight());
    }

    //　后序线索化二叉树
    public void postThreadedTree(HeroNode node) {
        if (node == null) {
            return;
        }

        postThreadedTree(node.getLeft());
        postThreadedTree(node.getRight());

        if (node.getLeft() == null) {
            node.setLeft(preNode);
            node.setLeftType(1);
        }
        if (preNode != null && preNode.getRight() == null) {
            preNode.setRight(node);
            preNode.setRightType(1);
        }
        preNode = node;
    }

    //删除节点
    public void delNode(int no) {
        if (root != null) {
            //如果只有一个root节点
            if (root.getNo() == no) {
                root = null;
            } else {
                //有多个节点时，递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树，无法删除");
        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空...");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空...");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空...");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public HeroNode getPreNode() {
        return preNode;
    }

    public void setPreNode(HeroNode preNode) {
        this.preNode = preNode;
    }
}

//二叉树节点类
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    //如果leftType == 0表示指向的是左子树，如果1则表示指向前驱结点
    private int leftType;
    //如果rightType == 0表示指向是右子树，如果1表示指向后继结点
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }


    //递归删除结点
    //1.如果删除的节点是叶子节点,则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no) {
        /*思路
        1.因为此时的二叉树是单向的，所以是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
        2.如果当前结点的左子结点不为空, 并且左子结点就是要删除结点, 就将this.left = null; 并且返回(结束递归删除)
        3.如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将this.right = nu1l; 并且返回(结束递归删除)
        4.如果第2和第3步没有删除结点，那么就需要向左子树进行递归删除
        5.如果第4步 也没有删除结点，则应当向右子树进行递归删除。*/
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        //向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    //前序遍历
    public void preOrder() {
        //输出父节点(当前节点)
        System.out.println(this);
        //递归左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        //递归左子树
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点(当前节点)
        System.out.println(this);
        //递归右子树
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        //递归左子树
        if (this.left != null) {
            this.left.postOrder();
        }
        //递归右子树
        if (this.right != null) {
            this.right.postOrder();
        }
        //输出父节点(当前节点)
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     *
     * @param no 要查找的节点no
     * @return 如果找到就返回该节点 , 如果没有找到返回null
     */
    public HeroNode preOrderSearch(int no) {
        //比较当前节点
        if (this.no == no) {
            return this;
        }

        //判断当前结点的左子节点是否为空,如果不为空,则递归前序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        //如果左递归前序查找，找到结点，则返回
        if (resNode != null) {
            return resNode;
        }

        //如果左递归前序查找没找到结点，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        //此时不用再判断，必须要返回了
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        //判断当前结点的左子节点是否为空,如果不为空,则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        //如果左递归中序查找，找到结点，则返回
        if (resNode != null) {
            return resNode;
        }

        //比较当前节点
        if (this.no == no) {
            return this;
        }

        //如果左递归中序查找没找到结点，则继续向右递归中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        //此时不用再判断，必须要返回了
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        //判断当前结点的左子节点是否为空,如果不为空,则递归后序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        //如果左递归后序查找，找到结点，则返回
        if (resNode != null) {
            return resNode;
        }

        //如果左递归后序查找没找到结点，则继续向右递归后序查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        //左右子树都没有找到，就比较当前结点
        if (this.no == no) {
            return this;
        }

        return resNode;
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}