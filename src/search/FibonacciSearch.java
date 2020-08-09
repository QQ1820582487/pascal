package search;

import java.util.Arrays;

/**
 * @author Xuxx3309
 * @Description 斐波那契查找
 * 基本思想：也是二分查找的一种提升算法，通过运用黄金比例的概念在数列中选择查找点进行查找，提高查找效率。
 * 同样地，斐波那契查找也属于一种有序查找算法。
 * @create 2020-07-26 16:11
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = fibSearch(arr, 1000);
        System.out.println(index);
    }

    //因为 mid=low+F(k-1)-1 ,需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
    //非递归方法得到一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契查找算法（此处是非递归）
     *
     * @param arr 数组
     * @param key 需要查找的值
     * @return 返回对应下标，否则返回 -1
     */
    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        //用来表示斐波那契分割数值的下标
        int k = 0;
        int mid = 0;
        //获取到斐波那契数列
        int[] f = fib();
        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k]值可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向 temp[]
        //不足的部分会使用0填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        //实际上需求使用arr数组最后的数填充temp
        for (int i = high + 1; i < temp.length - 1; i++) {
            temp[i] = arr[high];
        }
        //使用while来循环处理，找到 key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            //向左移
            if (key < temp[mid]) {
                high = mid - 1;
                //为什是k--
                //1.全部元素=前面的元素+后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //因为前面有f[k-1]个元素,所以可以继续拆分f[k-1] = f[k-2] + f[k-3]
                //即在f[k-1]的前面继续查找k--
                //即下次循环mid = f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) {//向右移
                low = mid + 1;
                //为什么是k -=2
                //1.全部元素=前面的元素+后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //3.因为后面我们有f[k-2]所以可以继续拆分f[k-1] = f[k-3] + f[k-4]
                //4.即在f[k-2] 的前面进行查找 k-=2
                //5.即下次循环mid = f[k - 1 - 2]-1
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
