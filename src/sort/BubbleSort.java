package sort;

import java.util.Arrays;

/**
 * @author Xuxx3309
 * @Description 冒泡排序
 * 冒泡排序是一种交换排序，核心是冒泡，把数组中最小的那个往上冒，冒的过程就是和他相邻的元素交换。
 * @create 2020-07-23 10:33
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9};
        bubbleSort(arr);
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("排序后:" + Arrays.toString(arr));
    }
}
