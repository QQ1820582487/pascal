package alogrithm.binarysearch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xuxx3309
 * @Description 二分查找  元素必须是有序的，如果是无序的则要先进行排序操作。
 * 基本思想：也称为是折半查找，属于有序查找算法。
 * 用给定值k先与中间结点的关键字比较，中间结点把线形表分成两个子表，若相等则查找成功；
 * 若不相等，再根据k与该中间结点关键字的比较结果确定下一步查找哪个子表，这样递归进行，直到查找到或查找结束发现表中没有这样的结点。
 * @create 2020-07-24 18:36
 */
public class BinarySearch {
    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 4, 5, 89};
//        int rs = binarySearch(arr, 0, arr.length - 1, 10);
//        System.out.println(rs);

        int[] arr1 = {1, 1, 5, 5, 5, 5};
        int i = binSearch0(arr1, 3);
        System.out.println(i);
        List<Integer> list = binarySearch1(arr1, 0, arr1.length - 1, 5);
        System.out.println(list);
    }

    //二分查找 普通循环实现
    public static int binSearch0(int[] arr, int key) {
        int mid = arr.length / 2;
        if (key == arr[mid]) {
            return mid;
        }
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (key == arr[mid]) {
                return mid;
            } else if (key > arr[mid]) {
                //向左找
                start = mid + 1;
            } else {
                //向右找
                end = mid - 1;
            }
        }
        return -1;
    }


    /**
     * 二分查找，此时假设数组是按从小到大排列的
     * 此处是查找到一个就返回
     *
     * @param arr   数组
     * @param left  左索引
     * @param right 右索引
     * @param key   要查找的值
     * @return 找到就返回下标，没找到就返回 -1
     */
    public static int binarySearch(int[] arr, int left, int right, int key) {
        int mid = (left + right) / 2;
        int midValue = arr[mid];

        //当left==right时，数组已经遍历完成
        if (left == right) {
            return -1;
        }
        if (key > midValue) {
            //向右递归
            return binarySearch(arr, mid + 1, right, key);
        } else if (key < midValue) {
            //向左递归
            return binarySearch(arr, left, mid - 1, key);
        } else {
            return mid;
        }
    }

    /**
     * 二分查找，此时假设数组是按从小到大排列的
     * 此处是查找到所有的再返回
     *
     * @param arr
     * @param left
     * @param right
     * @param key
     * @return 存有所有满足条件的元素的下标的list, 如果没找到则返回空list
     */
    public static List<Integer> binarySearch1(int[] arr, int left, int right, int key) {
        int mid = (left + right) / 2;
        int midValue = arr[mid];

        //当left>right时，数组已经遍历完成
        if (left > right) {
            return new ArrayList<Integer>();
        }
        if (key > midValue) {
            //向右递归
            return binarySearch1(arr, mid + 1, right, key);
        } else if (key < midValue) {
            //向左递归
            return binarySearch1(arr, left, mid - 1, key);
        } else {
            List<Integer> list = new ArrayList<>();
            //向mid左边继续扫描
            int temp = mid - 1;
            while (true) {
                //查找到最左边或者找不到，该退出了
                if (temp < 0 || arr[temp] != key) {
                    break;
                }
                list.add(temp);
                temp -= 1;
            }
            //将mid存入list
            list.add(mid);
            //向mid右边继续扫描
            temp = mid + 1;
            while (true) {
                //查找到最右边或者找不到，该退出了
                if (temp > arr.length - 1 || arr[temp] != key) {
                    break;
                }
                list.add(temp);
                temp += 1;
            }
            return list;
        }
    }
}