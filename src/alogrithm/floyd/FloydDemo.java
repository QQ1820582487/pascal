package alogrithm.floyd;

import java.util.Arrays;

/**
 * @author Xuxx3309
 * @Description 弗洛伊德(Floyd)算法 -- 求最短路径
 * 弗洛伊德算法 VS 迪杰斯特拉算法:
 * 迪杰斯特拉算法通过选定的被访问顶点，求出从出发访问顶点到其他顶点的最短路径;
 * 弗洛伊德算法中每一个顶点都是出发访问点，所以需要将每一个顶点看做被访问顶点，通过中间顶点，求出从每一个顶点到其他顶点的最短路径。
 * @create 2020-08-04 1:19
 */
public class FloydDemo {
    public static void main(String[] args) {
        final int N = 65535;
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertexs.length][vertexs.length];
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{0, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};
        Graph graph = new Graph(vertexs.length, matrix, vertexs);
        graph.show();

        System.out.println();
        System.out.println("弗洛伊德算法处理后：");
        graph.floyd();
        graph.show();
    }
}

//图
class Graph {
    //顶点数组
    private char[] vertexs;
    //从各个顶点出发到其它项点的距离
    private int[][] dis;
    //到达目标项点的前驱项点
    private int[][] pre;

    public void floyd() {
        //保存顶点间的距离
        int len = 0;

        //对中间顶点进行遍历，k: 中间顶点的下标
        for (int k = 0; k < dis.length; k++) {
            //对出发顶点进行遍历，k: 出发顶点的下标
            for (int i = 0; i < dis.length; i++) {
                //对终点进行遍历，k: 终点的下标
                for (int j = 0; j < dis.length; j++) {
                    //从 i 顶点出发，经过 k 中间顶点，到达 j 顶点的距离
                    len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        //更新距离
                        dis[i][j] = len;
                        //更新前驱顶点
                        pre[i][j] = pre[k][j];
                        //pre[j][i] = pre[i][j];
                    }
                }
            }
        }
    }

    /**
     * @param length  顶点数
     * @param matrix  邻接矩阵
     * @param vertexs 顶点数组
     */
    public Graph(int length, int[][] matrix, char[] vertexs) {
        this.vertexs = vertexs;
        this.dis = matrix;
        this.pre = new int[length][length];
        //对pre数组初始化，存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    //显示pre数组和dis数组
    public void show() {
        //为了便于查看
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        for (int k = 0; k < dis.length; k++) {
            for (int i = 0; i < dis.length; i++) {
//                System.out.print(pre[k][i] + "\t");
                System.out.print(vertexs[pre[k][i]] + "\t");
            }
            System.out.println();
            for (int i = 0; i < dis.length; i++) {
//                System.out.print(dis[k][i] + "\t");
                System.out.print("(" + vertexs[k] + "到" + vertexs[i] + "的最短路径是:" + dis[k][i] + ")\t");
            }
            System.out.println();
        }
    }
}