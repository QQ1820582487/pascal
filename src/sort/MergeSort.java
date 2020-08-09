package sort;

/**
 * @author Xuxx3309
 * @Description 归并排序 归并排序，是创建在归并操作上的一种有效的排序算法。
 * 算法是采用分治法（Divide and Conquer）的一个非常典型的应用，且各层分治递归可以同时进行。
 * 归并排序思路简单，速度仅次于快速排序，为稳定排序算法，一般用于对总体无序，但是各子项相对有序的数列。
 * <p>
 * 归并排序是用分治思想，分治模式在每一层递归上有三个步骤：
 * 分解（Divide）：将n个元素分成个含n/2个元素的子序列。
 * 解决（Conquer）：用合并排序法对两个子序列递归的排序。
 * 合并（Combine）：合并两个已排序的子序列已得到排序结果。
 * @create 2020-07-23 10:35
 */
public class MergeSort {
    static int c = 0;

    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        //新建一个临时数组
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }

    //分+合
    public static void mergeSort(int[] arr, int left, int right, int[] tmp) {
        if (left < right) {
            //中间索引
            int mid = (left + right) / 2;
            //对左边序列递归，进行排序
            mergeSort(arr, left, mid, tmp);
            //对右边序列递归，进行排序
            mergeSort(arr, mid + 1, right, tmp);
            //合并左右两个有序序列
            merge(arr, left, mid, right, tmp);
        }
    }

    /**
     * 合并的方法
     *
     * @param arr   要排序的数组
     * @param left  左边有序序列的初始最左边索引
     * @param mid   中间索引
     * @param right 右边有序序列的初始最右边索引
     * @param temp  临时数组，用于中转
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //初始化i,左边有序序列的初始索引
        int i = left;
        //初始化j,右边有序序列的初始索引
        int j = mid + 1;
        //指向temp[]的当前索引
        int t = 0;

        //1.先把左右两边(有序序列)的数据按照规则填充到temp数组，直到左右两边的有序序列，有一边处理完毕为止
        //左右两边的索引都还在其索引范围内
        while (i <= mid && j <= right) {
            //如果左边的有序序列的当前元素小于等于右边的有序序列的当前元素
            if (arr[i] <= arr[j]) {
                //就将左边的有序序列的当前元素填充到temp数组
                temp[t] = arr[i];
                //后移
                t++;
                i++;
            } else {
                //反之，就将右边的有序序列的当前元素填充到temp数组
                temp[t] = arr[j];
                //后移
                t++;
                j++;
            }

        }

        //2.把有剩余数据的一边的有序序列的数据依次全部填充到temp数组
        //如果左边的有序序列还有剩余的元素，就全部依次填充到temp数组
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        //如果右边的有序序列还有剩余的元素，就全部依次填充到temp数组
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        //3.将temp数组的元素拷贝回arr数组
        t = 0;
        int tempLeft = left;
        System.out.println("tempLeft=" + tempLeft + "  tempRight=" + right);
        while (tempLeft <= right) {
            //第一次合并：tempLeft=0,right=1;第二次合并：tempLeft=2,right=3;第三次合并：tempLeft=0,right=3;....
            //最后一次合并：tempLeft=0,right=arr.length - 1;
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }

}
