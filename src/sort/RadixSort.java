package sort;

import java.util.Arrays;

/**
 * @author Xuxx3309
 * @Description 基数排序
 * 原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * 基数排序的方式可以采用LSD（Least significant digital）或MSD（Most significant digital），LSD的排序方式由键值的最右边开始，而MSD则相反，由键值的最左边开始。
 * 实现逻辑:
 * ① 将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。
 * ② 从最低位开始，依次进行一次排序。
 * ③ 这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
 * @create 2020-07-23 22:05
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
        int[] bucketElementCounts = new int[10];

        //得到数组中最大的数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //将最大值变为String,求其长度就得到了最大值的位数
        int maxLength = (max + "").length();

        //使用循环
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //针对每个元素的对应位进行处理，第一次：个位;第二次：十位。。。
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应位的值
                int digitOfElement = (arr[j] / n) % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照桶的顺序（一维数组的下标）依次取出数据，放入原来的数组
            int index = 0;
            //遍历每一个桶，并将桶中的数据放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据才遍历
                if (bucketElementCounts[k] != 0) {
                    //循环第k个桶，将其中的数据放入原数组
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                //每一轮处理后需要将每个bucketElementCounts[k]置为0
                bucketElementCounts[k] = 0;
            }
            System.out.println("第" + (i + 1) + "轮处理结果：" + Arrays.toString(arr));
        }

    }

    //推导过程
    /*public static void radixSort(int[] arr) {
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
        int[] bucketElementCounts = new int[10];

        //第一轮(针对元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个数
            int digitOfElement = (arr[j] / 1) % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照桶的顺序（一维数组的下标）依次取出数据，放入原来的数组
        int index = 0;
        //遍历每一个桶，并将桶中的数据放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据才遍历
            if (bucketElementCounts[k] != 0) {
                //循环第k个桶，将其中的数据放入原数组
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            //每一轮处理后需要将每个bucketElementCounts[k]置为0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第一轮处理结果：" + Arrays.toString(arr));

        //第二轮(针对元素的十位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个数
            int digitOfElement = (arr[j] / 10) % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照桶的顺序（一维数组的下标）依次取出数据，放入原来的数组
        index = 0;
        //遍历每一个桶，并将桶中的数据放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据才遍历
            if (bucketElementCounts[k] != 0) {
                //循环第k个桶，将其中的数据放入原数组
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            //每一轮处理后需要将每个bucketElementCounts[k]置为0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第二轮处理结果：" + Arrays.toString(arr));

        //第三轮(针对元素的百位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个数
            int digitOfElement = (arr[j] / 100) % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //按照桶的顺序（一维数组的下标）依次取出数据，放入原来的数组
        index = 0;
        //遍历每一个桶，并将桶中的数据放入到原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据才遍历
            if (bucketElementCounts[k] != 0) {
                //循环第k个桶，将其中的数据放入原数组
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index] = bucket[k][l];
                    index++;
                }
            }
            //每一轮处理后需要将每个bucketElementCounts[k]置为0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第三轮处理结果：" + Arrays.toString(arr));

    }*/
}
