package search;

/**
 * @author Xuxx3309
 * @Description 线性查找
 * 顺序查找也称为线形查找，属于无序查找算法。
 * 从数据结构线形表的一端开始，顺序扫描，依次将扫描到的结点关键字与给定值k相比较，若相等则表示查找成功；
 * 若扫描结束仍没有找到关键字等于k的结点，表示查找失败。
 * @create 2020-07-24 18:18
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int rs = seqSearch(arr, -1);
        System.out.println(rs);
    }

    /**
     * 此处是找到一个满足的值就返回，如果要查找所有的满足的值可以进行遍历，将满足的值放入数组
     *
     * @param arr
     * @param value
     * @return
     */
    public static int seqSearch(int[] arr, int value) {
        //逐一比对
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
