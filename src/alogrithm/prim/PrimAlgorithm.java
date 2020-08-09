package alogrithm.prim;

import java.util.Arrays;

/**
 * @author Xuxx3309
 * @Description 普里姆算法  -- 求最小生成树
 * @create 2020-08-02 18:22
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexs = data.length;
        //10000表示不连通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};
        MGraph mGraph = new MGraph(vertexs);

        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, vertexs, data, weight);
        System.out.println("图的邻接矩阵:");
        minTree.showGraph(mGraph);

        System.out.println("从A(0)开始寻找最小生成树:");
        minTree.prim(mGraph, 0);
        System.out.println("从B(1)开始寻找最小生成树:");
        minTree.prim(mGraph, 1);
    }
}

//创建最小生成树
class MinTree {

    /**
     * 编写prim算法，得到最小生成树
     *
     * @param graph  图
     * @param vertex 开始生成的顶点的下标，例：0 -> ’A‘
     */
    public void prim(MGraph graph, int vertex) {
        //标记顶点是否被访问过，0:没访问过; 1:访问过
        int[] visited = new int[graph.vertexs];
        //把当前这个结点标记为已访问
        visited[vertex] = 1;

        //h1和h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;

        //因为有graph.vertexs顶点，普利姆算法结束后，有 graph.vertexs-1 条边
        for (int k = 1; k < graph.vertexs; k++) {

            //双层for循环:确定每一次生成的子图中，此次遍历的顶点和哪个顶点的距离最近
            //i结点表示被访问过的结点
            for (int i = 0; i < graph.vertexs; i++) {
                //j结点表示还没有访问过的结点
                for (int j = 0; j < graph.vertexs; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        //替换minWeight(寻找已经访问过的结点和未访问过的结点间权值最小的边)
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            //退出双层for循环时，就找到了一条权值最小的边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
            //将当前这个未访问的结点标记为已访问
            visited[h2] = 1;
            //重置minWeight
            minWeight = 10000;

        }

    }

    /**
     * 创建图的邻接矩阵
     *
     * @param graph   图
     * @param vertexs 图的顶点数
     * @param data    图的各顶点的值
     * @param weight  图的邻接矩阵
     */
    public void createGraph(MGraph graph, int vertexs, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < vertexs; i++) {
            //为图的各顶点赋值
            graph.data[i] = data[i];
            for (j = 0; j < vertexs; j++) {
                //为图的邻接矩阵赋值
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }
}

class MGraph {
    //顶点个数
    int vertexs;
    //存放顶点数据
    char[] data;
    //邻接矩阵
    int[][] weight;

    public MGraph(int vertexs) {
        this.vertexs = vertexs;
        data = new char[vertexs];
        weight = new int[vertexs][vertexs];
    }
}