package sort;

import java.util.Arrays;

/**
 * @author Xuxx3309
 * @Description 选择排序
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 * 选择排序的思想其实和冒泡排序有点类似，都是在一次排序后把最小的元素放到最前面，或者将最大值放在最后面。但是过程不同，冒泡排序是通过相邻的比较和交换。而选择排序是通过对整体的选择，每一趟从前往后查找出无序区最小值，将最小值交换至无序区最前面的位置
 * @create 2020-07-23 10:35
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {10, 6, 8, 5, 9};
        selectionSort(arr);
    }

    public static void selectionSort(int[] arr) {
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