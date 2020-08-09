package structure.tree.avl;

/**
 * @author Xuxx3309
 * @Description 平衡二叉搜索树  AVL树
 * @create 2020-07-31 1:39
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4, 3, 6, 5, 7, 8};//需要左旋转
        //int[] arr = {10, 12, 8, 9, 7, 6};//需要右旋转
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTreee avlTreee = new AVLTreee();
        for (int i = 0; i < arr.length; i++) {
            avlTreee.add(new Node(arr[i]));
        }
        avlTreee.infixOrder();

        System.out.println("树的高度:" + avlTreee.root.height());
        System.out.println("左子树的高度:" + avlTreee.root.leftHeihgt());
        System.out.println("右子树的高度:" + avlTreee.root.rightHeihgt());
        System.out.println("根结点:" + avlTreee.root);
    }
}

class AVLTreee {
    Node root;

    //查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找要删除结点的父结点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 删除以node为根结点的二叉排序树的最小结点
     *
     * @param node
     * @return 返回的以node为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左子结点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        //删除最小结点
        delNode(target.value);
        return target.value;
    }

    //删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1.需求先去找到要删除的结点 targetNode
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            Node parentNode = searchParent(value);
            //如果要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父结点的左子结点，还是右子结点
                if (parentNode.left != null && parentNode.left.value == value) {
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == value) {
                    parentNode.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { //有两颗子树的结点
                int minValue = delRightTreeMin(targetNode.right);
                targetNode.value = minValue;
            } else {//有一颗子树的结点
                //如果要删除的结点有左子结点
                if (targetNode.left != null) {
                    if (parentNode != null) {
                        //如果targetNode是parentNode的左子结点
                        if (parentNode.left.value == value) {
                            parentNode.left = targetNode.left;
                        } else {
                            parentNode.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parentNode != null) {
                        if (parentNode.left.value == value) {
                            parentNode.left = targetNode.right;
                        } else {
                            parentNode.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }

        }
    }

    //添加结点
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("空树，无法遍历");
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    //左旋转
    public void leftRotate() {
        //1.以当前根结点的值创建新的结点
        Node newNode = new Node(this.value);
        //2把新的结点的左子结点设置成当前结点的左子结点
        newNode.left = this.left;
        //3.把新的结点的右子结点设置为当前结点的右子结点的左子结点
        newNode.right = this.right.left;
        //4.把当前结点的值替换为当前结点的右子结点的值
        this.value = this.right.value;
        //5.把当前结点的右子结点设置成当前结点的右子结点的右子结点
        this.right = this.right.right;
        //6.把当前结点的左子结点设置成新的结点
        this.left = newNode;
    }

    //右旋转
    public void rightRotate() {
        //1.以当前根结点的值创建新的结点
        Node newNode = new Node(this.value);
        //2把新的结点的右子结点设置成当前结点的右子结点
        newNode.right = this.right;
        //3.把新的结点的左子结点设置为当前结点的左子结点的右子结点
        newNode.left = this.left.right;
        //4.把当前结点的值替换为当前结点的左子结点的值
        this.value = this.left.value;
        //5.把当前结点的左子结点设置成当前结点的左子结点的左子结点
        this.left = this.left.left;
        //6.把当前结点的右子结点设置成新的结点
        this.right = newNode;
    }

    //返回左子树的高度
    public int leftHeihgt() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeihgt() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //返回以该结点为根结点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 查找要删除的结点
     *
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left != null) {
                return this.left.search(value);
            } else {
                return null;
            }
        } else {
            if (this.right != null) {
                return this.right.search(value);
            } else {
                return null;
            }
        }
    }

    /**
     * 查找要删除结点的父结点
     *
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点的父结点，否则返回null
     */
    public Node searchParent(int value) {
        //如果当前结点就是要删除的结点的父结点，就返回当前结点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前结点的值，并且当前结点的左子结点不为空
            if (value < this.value && this.left != null) {
                //向左子树递归查找
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                //向右子树递归查找
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    //添加结点的方法
    //递归的形式添加结点,注意需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断传入的结点的值，和当前子树的根结点的值关系
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        //当添加完一个结点后, 如果: (右子树的高度-左子树的高度) > 1，要进行左旋转
        if (rightHeihgt() - leftHeihgt() > 1) {
            //如果当前结点的右子树的左子树的高度大于当前结点的右子树的右子树的高度
            if (this.right != null && this.right.leftHeihgt() > this.right.rightHeihgt()) {
                //当前结点的右节点需要先进行右旋转
                this.right.rightRotate();
                //然后再将当前结点进行左旋转
                this.leftRotate();
            } else {
                //直接进行左旋转即可
                this.leftRotate();
            }
            //防止再次加入后面的旋转
            return;
        }
        //当添加完一个结点后, 如果: (左子树的高度-右子树的高度) > 1，要进行右旋转
        if (leftHeihgt() - rightHeihgt() > 1) {
            //如果当前结点的左子树的右子树的高度大于当前结点的左子树的左子树的高度
            if (this.left != null && this.left.rightHeihgt() > this.left.leftHeihgt()) {
                //当前结点的左节点需要先进行左旋转
                this.left.leftRotate();
                //然后再将当前结点进行右旋转
                this.rightRotate();
            } else {
                //直接进行右旋转即可
                this.rightRotate();
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}