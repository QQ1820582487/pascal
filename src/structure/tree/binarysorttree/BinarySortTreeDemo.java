package structure.tree.binarysorttree;

/**
 * @author Xuxx3309
 * @Description 二叉排序树
 * @create 2020-07-30 1:09
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree sortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            sortTree.add(new Node(arr[i]));
        }
        sortTree.infixOrder();

        /*System.out.println("删除叶子节点：");
        sortTree.delNode(2);*/
        /*System.out.println("删除只有一个子树的节点：");
        sortTree.delNode(1);*/
        /*System.out.println("删除有两个子树的节点：");
        sortTree.delNode(7);*/
        sortTree.infixOrder();
    }
}

class BinarySortTree {
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
        //循环的查找左子节点，就会找到最小值
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
            } else if (targetNode.left != null && targetNode.right != null) { //有两颗子树的节点
                int minValue = delRightTreeMin(targetNode.right);
                targetNode.value = minValue;

            } else {//有一颗子树的节点
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


    //添加节点
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
     * @return 如果找到返回该结点的父节点，否则返回null
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
