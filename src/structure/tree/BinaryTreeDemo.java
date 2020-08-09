package structure.tree;

/**
 * @author Xuxx3309
 * @Description 二叉树
 * @create 2020-07-27 2:35
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先创建二叉树
        BinaryTree tree = new BinaryTree();
        //创建节点
        HeroNode node1 = new HeroNode(1, "test1");
        HeroNode node2 = new HeroNode(2, "test2");
        HeroNode node3 = new HeroNode(3, "test3");
        HeroNode node4 = new HeroNode(4, "test4");
        HeroNode node5 = new HeroNode(5, "test5");

        //先手动创建该二叉树
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        tree.setRoot(node1);

        //测试
        /*System.out.println("前序遍历");
        tree.preOrder();
        System.out.println("中序遍历");
        tree.infixOrder();
        System.out.println("后序遍历");
        tree.postOrder();*/

        /*System.out.println("前序遍历查找");
        HeroNode node = tree.preOrderSearch(1);
        System.out.println("前序遍历查找结果:" + node);
        System.out.println("中序遍历查找");
        node = tree.infixOrderSearch(2);
        System.out.println("前序遍历查找结果:" + node);
        System.out.println("后序遍历查找");
        node = tree.postOrderSearch(3);
        System.out.println("前序遍历查找结果:" + node);*/

        System.out.println("删除前，前序遍历");
        tree.preOrder();
        tree.delNode(3);
        System.out.println("删除后，前序遍历");
        tree.preOrder();

    }
}

//二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
}

//二叉树节点类
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

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

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}