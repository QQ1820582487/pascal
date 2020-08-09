package alogrithm.binarysearch;

/**
 * @author Xuxx3309
 * @Description 二分查找（非递归方式）
 * @create 2020-07-31 17:39
 */
public class BinarySearchNoRecur {

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int rs = binarySearch(arr, 100);
        System.out.println(rs);
    }

    /**
     * 二分查找的非递归实现
     *
     * @param arr    数组（此时认为数组是升序排列的）
     * @param target 目标值
     * @return 找到则返回目标值下标，没找到则返回-1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                //向左找
                right = mid - 1;
            } else {
                //向右找
                left = mid + 1;
            }
        }
        return -1;
    }
}

