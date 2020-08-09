package alogrithm.dynamic;

/**
 * @author Xuxx3309
 * @Description 动态规划实践 -- 背包问题    PS:没太懂
 * 难点在于公式推导
 * 将动态规划拆分成三个子目标：
 * 1.建立状态转移方程
 * 这一步是最难的，大部分人都被卡在这里。这一步没太多的规律可说，只需抓住一个思维：当做已经知道​[公式]~​[公式]的值，然后想办法利用它们求得[公式]​。
 * 2.缓存并复用以往结果
 * 这一步不难，但是很重要。如果没有合适地处理，很有可能就是指数和线性时间复杂度的区别。
 * 3.按顺序从小往大算
 * 这里的“小”和“大”对应的是问题的规模，在这里也就是我们要从 [公式] ​, [公式] ​, ... 到​ [公式] 依次顺序计算。
 * <p>
 * 参考：如何理解动态规划？ https://www.zhihu.com/question/39948290/answer/883302989
 * @create 2020-08-01 1:29
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        //物品重量
        int[] w = {1, 4, 3};
        //物品价值
        int[] val = {1500, 3000, 2000};
        //背包容量
        int m = 4;
        //物品个数
        int n = val.length;
        //v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];

        //为了记录放入商品的情况，再定一个二维数组
        int[][] path = new int[n + 1][m + 1];

        //初始化 v 第一行和第一列
        for (int i = 0; i < v.length; i++) {
            //将第一列设置为0
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            //将第一行设置为0
            v[0][i] = 0;
        }

        //(根据前面得到公式来)动态规划处理
        //不处理第一行
        for (int i = 1; i < v.length; i++) {
            //不处理第一列
            for (int j = 1; j < v[0].length; j++) {
                //公式
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    //因为i是从1开始的，所以公式有调整
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //为了记录商品存放到背包的情况,就不能直接的使用↑上面的公式,改为使用if-else来处理
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //记录
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        System.out.println("动态规划表格:");
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + "\t");
            }
            System.out.println();
        }

        //这样输出会把所有的放入情况都得到，其实只需要最后的放入
        /*for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                if (path[i][j] == 1) {
                    System.out.printf("第%d个商品放入到背包\n", i);
                }
            }
            System.out.println();
        }*/

        System.out.println("动态规划path:");
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                System.out.print(path[i][j] + "\t");
            }
            System.out.println();
        }
        //行最大下标
        int i = path.length - 1;
        //列最大下标
        int j = path[0].length - 1;
        //从path的最后开始找
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                //调整背包剩余容量
                j -= w[i - 1];
            }
            i--;
        }
    }
}