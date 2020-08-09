package alogrithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Xuxx3309
 * @Description 贪婪算法(贪心算法)
 * 假设存在下面需要付费的广播台，以及广播台信号可以覆盖的地区。如何选择最少的广播台，让所有的地区都可以接收到信号
 * 广播台-覆盖地区： k1-“北京","上海",天津” ; k2-"广州",“北京",深圳" ; k3-"成都","上海",“杭州” ; k4-上海",“天津” ; k5-杭州",“大连"
 * @create 2020-08-02 16:24
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播台，放入Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();

        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcasts.put("k1", hashSet1);
        broadcasts.put("k2", hashSet2);
        broadcasts.put("k3", hashSet3);
        broadcasts.put("k4", hashSet4);
        broadcasts.put("k5", hashSet5);

        //存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //存放选择的电台
        ArrayList<String> selectes = new ArrayList<>();

        //存放遍历过程中的电台覆盖地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        //保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        //如果maxKey不为null,则会加入到selects
        String maxKey = null;

        //如果allAreas不为空,则表示还没有覆盖到所有的地区
        while (allAreas.size() != 0) {
            maxKey = null;

            for (String key : broadcasts.keySet()) {
                tempSet.clear();

                //当前电台 key,可以覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);

                //求交集，交集会赋给 tempSet
                tempSet.retainAll(allAreas);

                //如果交集中包含的地区的数量，比maxKey指向的集合的地区还多，就需要重置maxKey
                //贪心算法体现：tempSet.size() > broadcasts.get(maxKey).size()
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selectes.add(maxKey);

                //将maxKey指向的广播电台覆盖的地区，从allAreas去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("得到的选择结果：" + selectes);
    }
}
