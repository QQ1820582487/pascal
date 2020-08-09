package sort;

import java.util.Arrays;

/**
 * @author Xuxx3309
 * @Description 堆排序
 * 堆是具有以下性质的完全二叉树。每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆；每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆。
 * 基本思想是：将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，此时末尾就为最大值。然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了
 * @create 2020-07-28 1:21
 */
public class HeapSort {
    public static void main(String[] args) {
        //将数组进行升序排序
        int[] arr = {4, 6, 8, 5, 9};
        System.out.println("堆排序前 => " + Arrays.toString(arr));
        heapSort(arr);
        System.out.println("堆排序后 => " + Arrays.toString(arr));
    }

    //堆排序方法
    public static void heapSort(int[] arr) {
        int temp = 0;
        //手动分步完成
        /*adjust(arr, 1, arr.length);
        System.out.println("第一次调整：" + Arrays.toString(arr));
        adjust(arr, 0, arr.length);
        System.out.println("第二次调整：" + Arrays.toString(arr));*/

        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        //将堆项元素与数组末尾元素交换，将最大元素"沉"到数组末端;
        for (int j = arr.length - 1; j > 0; j--) {
            //将堆项元素与数组末尾元素交换
            temp = arr[j];
            //此时的 arr[j]就是数组末尾，arr[0] 就是堆顶
            arr[j] = arr[0];
            arr[0] = temp;
            //重新调整结构，使其满足堆定义,然后继续交换堆顶元素与当前数组末尾元素,反复执行调整+交换步骤，直到整个序列有序。
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 将数组（二叉树）变成大顶堆（或者小顶堆）
     * 将以 i 对应的非叶子结点的树调整成大顶堆（或者小顶堆）（从局部到整体进行调整）
     *
     * @param arr    待调整的数组
     * @param i      非叶子节点在数组中的索引
     * @param lenght 要调整的数组长度  (会逐渐减少)
     */
    public static void adjustHeap(int[] arr, int i, int lenght) {
        //取出当前元素
        int temp = arr[i];
        //开始调整
        // k=i*2+1,k是i节点的左子节点的索引，i*2+2 是右子节点的索引
        for (int k = i * 2 + 1; k < lenght; k = k * 2 + 1) {
            //如果左子节点小于右子节点
            if (k + 1 < lenght && arr[k] < arr[k + 1]) {
                //将k指向右子节点
                k++;
            }
            //如果子节点大于父节点
            if (arr[k] > temp) {
                //将子节点和父节点进行交换
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
            //将一开始取出的值放到调整后的地方
            arr[i] = temp;

            //当for循环结束后，就已经将以i为父结点的堆调整为了大顶堆
        }
    }
}
