package alogrithm.dijkstra;

import java.util.Arrays;

/**
 * @author Xuxx3309
 * @Description 迪杰斯特拉算法 -- 求最短路径
 * @create 2020-08-03 16:27
 */
public class DijkstraDemo {
    public static void main(String[] args) {
        //N代表不可直接连接
        final int N = 65535;

        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertexs.length][vertexs.length];
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        Graph graph = new Graph(vertexs, matrix);
        graph.showGraph();

        graph.dijkstra(6);
        graph.showDijkstra();
    }
}


class Graph {
    //顶点数组
    private char[] vertexs;
    //邻接矩阵
    private int[][] matrix;
    //已访问顶点集合
    private VisitedVertex visitedVertex;

    public Graph(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
    }

    /**
     * 迪杰斯特拉算法
     *
     * @param index 出发顶点的下标
     */
    public void dijkstra(int index) {
        visitedVertex = new VisitedVertex(vertexs.length, index);
        //更新index项点到周围顶点的距离和前驱项点
        update(index);

        for (int j = 1; j < vertexs.length; j++) {
            //选择并返回新的访问项点
            index = visitedVertex.updateArr();
            //更新index项点到周围顶点的距离和前驱项点
            update(index);
        }

    }

    //更新index项点到周围顶点的距离和前驱项点
    private void update(int index) {
        int len = 0;
        //遍历对应顶点的行的邻接矩阵，更新 already_arr[],pre_visited[],dis[]
        for (int j = 0; j < matrix[index].length; j++) {
            //len:出发顶点到index顶点的距离 + index顶点到j顶点的距离的和
            len = visitedVertex.getDis(index) + matrix[index][j];
            //如果j顶点没有被访问过，并且len小于出发顶点到j顶点的距离，就需要更新
            if (!visitedVertex.in(j) && len < visitedVertex.getDis(j)) {
                //更新j顶点的前驱为index顶点
                visitedVertex.updatePre(j, index);
                //更新出发顶点到j项点的距离
                visitedVertex.updateDis(j, len);
            }
        }
    }

    //显示图
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void showDijkstra() {
        visitedVertex.show();
    }
}

//已访问顶点集合
class VisitedVertex {
    //记录各个顶点是否访问过1表示访问过, 0未访问,会动态更新
    public int[] already_arr;
    //每个下标对应的值为前一个顶点下标，会动态更新
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离,比如G为出发顶点，就会记录G到其它顶点的距离，会动态更新，求的最短距离就会存放到dis
    public int[] dis;

    /**
     * @param length 表示顶点个数
     * @param index  出发点的下标
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis数组
        Arrays.fill(dis, 65535);
        //将出发顶点设置为被访问过，1
        this.already_arr[index] = 1;
        //设置出发顶点的访问距离为0
        dis[index] = 0;
    }

    //显示最后的结果
    public void show() {
        System.out.println("结果：");
        System.out.println("already_arr:" + Arrays.toString(already_arr));
        System.out.println("pre_visited:" + Arrays.toString(pre_visited));
        System.out.println("dis:" + Arrays.toString(dis));
    }

    //继续选择并返回新的访问顶点，比如这里的G完后,就是A点作为新的访问顶点(注意不是出发顶点)
    public int updateArr() {
        int min = 65535;
        int index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }

    /**
     * 判断index顶点是否被访问过
     *
     * @param index 顶点的下标
     * @return 如果访问过，就返回true,否则访问false
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     *
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新pre顶点的前驱顶点为index顶点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * @param index
     * @return 返回出发顶点到index顶点的距离
     */
    public int getDis(int index) {
        return dis[index];
    }
}