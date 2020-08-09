package alogrithm.kmp;

import java.util.Arrays;

/**
 * @author Xuxx3309
 * @Description KMP算法
 * @create 2020-08-02 1:38
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext(str2);
        System.out.println("next=> " + Arrays.toString(next));
        int i = kmpSearch(str1, str2, next);
        System.out.println("index=" + i);
    }

    /**
     * kmp搜索算法
     *
     * @param str1 原字符串
     * @param str2 子串
     * @param next 子串对应的部分匹配值表
     * @return 匹配成功时返回子串在原字符串的第一个索引位置，匹配失败则返回 -1
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {

            //匹配失败则让 j 回退到前一个值,直到匹配成功 或者 j <= 0
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            //匹配成功则 j++
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }


    //获取一个字符串(子串)的部分匹配值表
    private static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        //如果字符串是长度为1,部分匹配值一定是0
        next[0] = 0;

        for (int i = 1, j = 0; i < dest.length(); i++) {

            //匹配失败则让 j 回退到前一个值,直到匹配成功
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            //匹配成功则 j++
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
