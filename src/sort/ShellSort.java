package sort;

import java.util.Arrays;

/**
 * @author Xuxx3309
 * @Description 希尔排序，由插入排序改进而来
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
 * @create 2020-07-23 10:35
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {5, 1, 7, 3, 1, 6, 9, 4};
        shellSort(arr);
        System.out.println("排序后:" + Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {
        //step:步长，每次减为原来的一半
        for (int step = arr.length / 2; step > 0; step /= 2) {
            //对一个步长区间进行比较 [step,arr.length)
            for (int i = step; i < arr.length; i++) {
                int value = arr[i];
                int j;
                //对步长区间中具体的元素进行比较
                for (j = i - step; j >= 0 && arr[j] > value; j -= step) {
                    //j为左区间的取值，j+step为右区间与左区间的对应值。
                    arr[j + step] = arr[j];
                }
                //此时step为一个负数，[j + step]为左区间上的初始交换值
                arr[j + step] = value;
            }
        }
    }
}
