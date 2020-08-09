package alogrithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Xuxx3309
 * @Description 骑士周游(马踏棋盘)问题 + 贪心算飞
 * @create 2020-08-04 2:25
 */
public class TraversalChessboardUpgrade {
    //棋盘的列数
    private static int X;
    //棋盘的行数
    private static int Y;
    //记录棋盘的各个位置是否被访问过
    private static boolean[] visited;
    //记录是否棋盘的所有位置都被访问了
    private static boolean finished;

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        //设置当前位置，第一行，第一列
        int row = 1;
        int column = 1;

        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];

        traversalChessboard(chessboard, row - 1, column - 1, 1);

        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 骑士周游算法
     *
     * @param chessboard 棋盘
     * @param row        当前的位置的行，从0开始
     * @param column     儿当前的位置的列，从0开始
     * @param step       第几步，初始位置就是第1步，即：走第一步时，step=2
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        //标记该位置(当前的位置)已经访问
        visited[row * X + column] = true;
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));

        //对ps进行排序,排序的规则就是对ps的所有的Point对象的下一步的位置的数目，进行非递减排序
        //使用了贪心算法(选择的是下一步的位置的数目最大的)
        sort(ps);

        while (!ps.isEmpty()) {
            //取出下一个可以走的位置(从前开始取的)
            Point p = ps.remove(0);
            //判断该点是否已经访问过
            if (!visited[p.y * X + p.x]) {
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }

        //判断是否完成了任务，使用 step 和应该走的步数比较，
        //如果没有达到数量，则表示没有完成任务，将整个棋盘置0
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 根据当前位置(Point对象),计算马儿还能走哪些位置(Point),并放入到一个集合中(ArrayList),最多有8个位置
     *
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> points = new ArrayList<>();
        Point p1 = new Point();
        //表示马儿可以走5(向左2行，向上1行)这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            points.add(new Point(p1));
        }
        //表示马儿可以走6(向左1行，向上2行)这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            points.add(new Point(p1));
        }
        //表示马儿可以走7(向右1行，向上2行)这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            points.add(new Point(p1));
        }
        //表示马儿可以走0(向右2行，向上1行)这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            points.add(new Point(p1));
        }
        //表示马儿可以走1(向右2行，向下1行)这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            points.add(new Point(p1));
        }
        //表示马儿可以走2(向右1行，向下2行)这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            points.add(new Point(p1));
        }
        //表示马儿可以走3(向左1行，向下2行)这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            points.add(new Point(p1));
        }
        //表示马儿可以走4(向左2行，向下1行)这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            points.add(new Point(p1));
        }
        return points;
    }

    //根据当前位置的所有可行的下一步的选择位置,进行非递减排序
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取到o1的下一步的所有位置个数
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

}
