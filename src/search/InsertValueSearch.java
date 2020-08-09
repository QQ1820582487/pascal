package search;

import java.util.Arrays;

/**
 * @author Xuxx3309
 * @Description 插值查找 元素必须是有序的，如果是无序的则要先进行排序操作。
 * 基于二分查找算法，将查找点的选择改进为自适应选择，可以提高查找效率。当然，差值查找也属于有序查找。
 * 将二分查找算法的 mid=(low+high)/2, 即mid=low+1/2*(high-low);
 * 通过类比，我们可以将查找的点改进为如下：
 * mid=low+(key-a[low])/(a[high]-a[low])*(high-low)
 * @create 2020-07-25 17:03
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 1; i <= 100; i++) {
            arr[i - 1] = i;
        }
        System.out.println(Arrays.toString(arr));
        int search = insertValueSearch(arr, 0, arr.length - 1, 1);
        System.out.println(search);
    }

    /**
     * @param arr   传入的数组
     * @param left  左索引
     * @param right 右索引
     * @param key   要查找的值
     * @return 要查找的值在数组中的索引
     */
    public static int insertValueSearch(int[] arr, int left, int right, int key) {
        //添加的条件{key < arr[0] || key > arr[arr.length - 1]}是为了防止 mid 越界
        if (left > right || key < arr[0] || key > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (key - arr[left]) / (arr[right] - arr[left]) * (right - left);
        int midValue = arr[mid];
        //向右递归
        if (key > midValue) {
            return insertValueSearch(arr, mid + 1, right, key);
        }
        //向左递归
        else if (key < midValue) {
            return insertValueSearch(arr, left, mid - 1, key);
        } else {
            return mid;
        }
    }
}
