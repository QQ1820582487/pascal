package structure.hashtab;

import java.util.Scanner;

/**
 * @author Xuxx3309
 * @Description
 * @create 2020-07-26 17:02
 */
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        //测试
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add：添加 ");
            System.out.println("list：显示 ");
            System.out.println("find：查找雇员 ");
            System.out.println("del：删除雇员 ");
            System.out.println("exit：退出 ");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id:");
                    int id = scanner.nextInt();
                    System.out.println("请输入name:");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "del":
                    System.out.println("请输入要删除的id");
                    id = scanner.nextInt();
                    hashTab.del(id);
                    break;
                default:
                    System.out.println("输入有误");
                    break;
            }
        }
    }

}

class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //不要忘了分别初始化每一个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //根据id查找员工
    public Emp findEmpById(int id) {
        //使用散列函数确定到哪条链表查找
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表中找到 雇员id = %d\n", (empLinkedListNo + 1), id);
            return emp;
        } else {
            System.out.println("未找到该雇员");
            return null;
        }
    }

    //添加员工（节点）
    public void add(Emp emp) {
        //根据员工的id,,得到该员工应当添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        //添加到对应的链表
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //删除员工（节点）
    public void del(int id) {
        //根据员工的id,,得到该员工应当添加到哪条链表
        int empLinkedListNo = hashFun(id);
        //在对应的链表中删除改雇员
        empLinkedListArray[empLinkedListNo].delEmpById(id);

        //如果有多个id相同（但是id不应该相同）
        /*Emp emp = findEmpById(id);
        if (emp != null) {
            this.del(emp.id);
        }*/
    }

    //编写散列函数，使用一个简单取模法
    public int hashFun(int id) {
        return id % size;
    }

    //遍历所有链表（哈希表）
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i + 1);
        }
    }
}

//员工类（链表节点）
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

//链表
class EmpLinkedList {
    private Emp head;

    //添加
    //1.假定，当添加雇员时, id是自增长，即id的分配总是从小到大
    //因此我们将该雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        //如果是添加第一个员工
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是添加第一个员工
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            //向后移
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //遍历链表
    public void list(int no) {
        if (head == null) {
            System.out.println("第【" + no + "】条链表为空");
            return;
        }
        System.out.print("第【" + no + "】条链表信息: ");
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            //向后移
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //根据id查找雇员.
    //如果查找到，就返回Emp,如果没有找到就返回null;
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;

            }
            if (curEmp.next == null) {
                curEmp = null;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    //根据ID删除员工
    public void delEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
        }
        Emp curEmp = head;
        while (true) {
            //要删除的雇员节点前面有雇员节点存在
            if (curEmp.next != null && curEmp.next.id == id) {
                //要删除的雇员节点后还有雇员节点存在
                if (curEmp.next.next != null) {
                    curEmp.next = curEmp.next.next;
                } else {
                    //要删除的雇员节点后没有雇员节点存在
                    curEmp.next = null;
                }
                System.out.printf("ID为【%d】的雇员以被删除\n", id);
                break;
            } else if (curEmp.next == null) {
                //要删除的雇员节点前面没有雇员节点存在
                head = null;
                System.out.printf("ID为【%d】的雇员以被删除\n", id);
                break;
            } else {
                //找不到要删除的雇员节点
                System.out.printf("未找到ID为【%d】的雇员\n", id);
                break;
            }
        }
    }
}