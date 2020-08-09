package sort;

import java.util.Arrays;

/**
 * @author Xuxx3309
 * @Description 插入排序
 * 插入排序的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * 插入排序操作类似于摸牌并将其从大到小排列。
 * @create 2020-07-23 10:34
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {5, 1, 7, 3, 1, 6, 9, 4};
        insertSort(arr);
    }

    public static void insertSort(int[] arr) {
        int i, j, index;
        for (i = 0; i < arr.length; i++) {
            index = i;
            for (j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
        System.out.println("排序后:" + Arrays.toString(arr));
    }
}
