package alogrithm.kmp;

/**
 * @author Xuxx3309
 * @Description 暴力匹配法
 * @create 2020-08-01 17:40
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "Abcdabdabc";
        String str2 = "abc";
        int i = violenceMatch(str1, str2);
        System.out.println(i);
    }

    //暴力匹配算法实现
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        //i索引指向s1
        int i = 0;
        //j索引指向s2
        int j = 0;

        while (i < s1Len && j < s2Len) {
            //匹配成功
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        //判断是否匹配成功
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
