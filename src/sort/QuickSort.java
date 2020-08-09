package sort;

/**
 * @author Xuxx3309
 * @Description 快速排序，又称划分交换排序（partition-exchange sort），是由冒泡排序改进而得到的
 * 步骤：
 * 1.在待排序的N个记录中任取一个元素(通常取第一个记录)作为基准，称为基准记录；
 * 2.定义两个索引 left 和 right 分别表示“首索引” 和 “尾索引”，key 表示“基准值”；
 * 3.首先，尾索引向前扫描，直到找到比基准值小的记录(left != righ)，并替换首索引对应的值；
 * 4.然后，首索引向后扫描，直到找到比基准值大于的记录(left != righ)，并替换尾索引对应的值；
 * 5.若在扫描过程中首索引等于尾索引(left = right),则一趟排序结束；将基准值(key)替换首索引所对应的值；
 * 6.再进行下一趟排序时，待排序列被分成两个区:[0,left-1],[righ+1,end]
 * 7.对每一个分区重复步骤2~6，直到所有分区中的记录都有序，排序成功。
 * @create 2020-07-22 22:10
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {5, 1, 7, 3, 1, 6, 9, 4};
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i + "\t");
        }
    }

    /**
     * @param arr        待排序列
     * @param leftIndex  待排序列起始位置
     * @param rightIndex 待排序列结束位置
     */
    private static void quickSort(int[] arr, int leftIndex, int rightIndex) {
        //左下标
        int l = leftIndex;
        //右下标
        int r = rightIndex;
        //选择一个值作为参考值，此处选的是 中轴
        int pivot = arr[(leftIndex + rightIndex) / 2];
        //临时变量
        int temp = 0;
        //比pivot大的值放右边，比pivot小的值放左边
        while (l < r) {
            //在pivot的左边一直找，直到找到大于等于pivot的值
            while (arr[l] < pivot) {
                l++;
            }
            //在pivot的右边一直找，直到找到小于等于pivot的值
            while (arr[r] > pivot) {
                r--;
            }
            //说明pivot的左右的值已经排好了（左边小于pivot，右边大于pivot）
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换后，arr[l]==pivot,则r--，前移
            if (arr[l] == pivot) {
                r--;
            }
            //如果交换后，arr[r]==pivot,则l--，后移
            if (arr[r] == pivot) {
                l++;
            }

        }
        if (l == r) {
            l++;
            r--;
        }
        //向左递归
        if (leftIndex < r) {
            quickSort(arr, leftIndex, r - 1);
        }
        //向右递归
        if (rightIndex > l) {
            quickSort(arr, l, rightIndex);
        }

    }
}