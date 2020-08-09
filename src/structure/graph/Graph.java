package structure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Xuxx3309
 * @Description 图
 * @create 2020-07-31 3:49
 */
public class Graph {
    //顶点表
    private ArrayList<String> vertexs;
    //邻接矩阵
    private int[][] edges;
    //存储边的数目
    private int numOfEdges;
    //记录某个结点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        //边数
        int n = 5;
        String[] vertexvalues = {"A", "B", "C", "D", "E"};

        Graph graph = new Graph(n);
        for (String value : vertexvalues) {
            graph.insertVertex(value);
        }
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        System.out.println("结点的个数:" + graph.getNumOfVertex());
        System.out.println("边的数目:" + graph.getNumOfEdge());
        graph.showGraph();

        //System.out.println("深度优先遍历(DFS):");
        //graph.dfs();

        System.out.println("广度优先遍历(BFS):");
        graph.bfs();

    }

    //遍历所有的结点，都进行广度优先搜索
    private void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    //对一个结点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i) {
        //表示队列的头结点对应下标
        int u;
        //邻接结点w的对应下标
        int w;
        //队列，记录结点访问的顺序
        LinkedList<Object> queue = new LinkedList<>();


        System.out.print(getValueByIndex(i) + " -> ");
        //标记为以访问
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接结点的下标W
            w = getFirstNeighbor(u);
            while (w != -1) {
                //没访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + " -> ");
                    //标记为以访问
                    isVisited[w] = true;
                    //将结点加入队列
                    queue.addLast(w);
                }
                //访问过，以u为前驱点，找w后面的下一个邻结点
                w = getNextNeighbor(u, w);//此时体现了广度优先
            }

        }
    }


    //对dfs进行重载,遍历所有的结点，并进行dfs
    public void dfs() {
        //遍历所有的结点,并进行dfs
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //深度优先遍历算法
    private void dfs(boolean[] isVisited, int i) {
        //首先我们访问该结点,输出
        System.out.print(getValueByIndex(i) + " -> ");
        //将结点设置为已经访问
        isVisited[i] = true;
        //查找结点i的第一个邻接结点w
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w结点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    //得到第一个邻接结点的下标(如果存在就返回对应的下标，否则返回-1)
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexs.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexs.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }


    //图中常用方法
    //返回结点的个数
    public int getNumOfVertex() {
        return vertexs.size();
    }

    //得到边的数目
    public int getNumOfEdge() {
        return numOfEdges;
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //返回结点i(下标)对应的数据
    public String getValueByIndex(int index) {
        return vertexs.get(index);
    }

    //返回两顶点的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public Graph(int n) {
        //初始顶点表和邻接矩阵
        edges = new int[n][n];
        vertexs = new ArrayList<>(n);
        isVisited = new boolean[n];
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexs.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     表示点的下标
     * @param v2     表示点的下标
     * @param weight 两顶点的关系
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
