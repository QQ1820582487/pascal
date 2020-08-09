package alogrithm.divide_and_rule;

/**
 * @author Xuxx3309
 * @Description 使用 分治算法 解决 汉诺塔问题
 * 分治算法在每一层递归上都有三个步骤:
 * 1.分解:将原问题分解为若干个规模较小，相互独立，与原问题形式相同的子问题
 * 2.解决:若子问题规模较小而容易被解决则直接解，否则递归地解各个子问题
 * 3.合并:将各个子问题的解合并为原问题的解。
 * @create 2020-07-31 18:05
 */
public class Hanoi {
    public static void main(String[] args) {
        hanoi(5, 'A', 'B', 'C');
    }

    /**
     * 汉诺塔的移动的方法 -- 使用分治算法
     *
     * @param num 盘数
     * @param A   塔A
     * @param B   塔B
     * @param C   塔C
     */
    public static void hanoi(int num, char A, char B, char C) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从 " + A + "->" + C);
        } else if (num >= 2) {
            //如果盘数 >= 2,可以看做是两个盘  1.最下边的一个盘  2.上面的所有盘
            //1.把上面的所有盘 A->B,移动过程会使用到C
            hanoi(num - 1, A, C, B);
            //2.把最下边的盘 A->C
            System.out.println("第" + num + "个盘从 " + A + "->" + C);
            //3.把B塔的所有盘从B->C,移动过程会使用到A
            hanoi(num - 1, B, A, C);
        }
    }
}