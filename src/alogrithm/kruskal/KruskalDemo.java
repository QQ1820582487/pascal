package alogrithm.kruskal;

import java.util.Arrays;

/**
 * @author Xuxx3309
 * @Description 克鲁斯卡尔算法  -- 求最小生成树
 * @create 2020-08-03 1:42
 */
public class KruskalDemo {

    //边的数量
    private int edgeNum;
    //顶点数组
    private char[] vertexs;
    //邻接矩阵
    private int[][] matrix;

    //使用 INF 表示两个顶点不能连通,0表示自身
//    private static final int INF = Integer.MAX_VALUE;
    private static final int INF = -1;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
        KruskalDemo kruskalDemo = new KruskalDemo(vertexs, matrix);
        kruskalDemo.print();

        /*EData[] edges = kruskalDemo.getEdges();
        //按权值升序排列
        kruskalDemo.sortEdges(edges);
        System.out.println(Arrays.toString(edges));*/

        kruskalDemo.kruskal();
    }

    public void kruskal() {
        //表示最后结果数组的索引
        int index = 0;
        //保存"已有最小生成树”中的每个顶点在最小生成树中的终点
        int[] ends = new int[edgeNum];

        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        //获取图中所有的边的集合
        EData[] edges = getEdges();
        System.out.println("图中所有的边的集合: \r\n" + Arrays.toString(edges) + "\t 共" + edges.length + "条");

        //按权值升序排列
        sortEdges(edges);

        //遍历，将边添加到最小生成树中，并判断准备加入的边是否形成了回路，如果没有形成回路就加入到最小生成树
        for (int i = 0; i < edgeNum; i++) {
            ///获取到第i条边的第一个顶点(起点)下标
            int p1 = getPosition(edges[i].start);
            ///获取到第i条边的第二个顶点(终点)下标
            int p2 = getPosition(edges[i].end);

            //获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            //获取p2这个顶点在已有最小生成树中的终点
            int n = getEnd(ends, p2);

            //判断准备加入的边是否形成了回路
            //不是同一个终点，没有形成回路
            if (m != n) {
                //设置m在"已有最小生成树"中的终点( ends 中下标为 m 的值设为 n -> 下标为 m 的顶点的终点的下标为 n )
                ends[m] = n;
                rets[index++] = edges[i];
            }
        }

        //统计并打印 "最小生成树"，rets
        System.out.println("最小生成树:");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }

    public KruskalDemo(char[] vertexs, int[][] matrix) {
        /*this.vertexs = vertexs;
        this.matrix = matrix;*/

        //初始化顶点(复制,)
        this.vertexs = new char[vertexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //初始化边(复制)
        this.matrix = new int[vertexs.length][vertexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    //打印邻接矩阵
    public void print() {
        System.out.print("邻接矩阵为：\n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 将所有边进行排序，冒泡
     *
     * @param edges 边集合
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }

        }
    }

    /**
     * @param ch 顶点的值,比如：'A','B'...
     * @return 返回ch顶点对应的下标，如果找不到，返回-1
     */
    public int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获职图中的边，放到EData[]数组中
     *
     * @return EData[]数组
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点
     *
     * @param ends 这个数组就是记录了各个顶点对应的终点是哪个,ends数组是在遍历过程中，逐步形成
     * @param i    表示传入的项点对应的下标
     * @return 返回的就是下标为i的这个项点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}

//边对象
class EData {
    //边的起点
    char start;
    //边的终点
    char end;
    //边的权值
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}