package arrays;

import java.util.ArrayList;
import java.util.List;

/*
现在有两个列表，每个列表是一个增序的版本号的序列，现在请实现一个算法能够将这两个列表合并成一个增序的版本号的序列。
输入:
列表1：[1.0.0, 1.0.2, 2.0.0, 3.1.5, 4.2.1]
列表2：[0.0.1, 2.0.0, 3.1.6]
输出：
[0.0.1, 1.0.0, 1.0.2, 2.0.0, 3.1.5, 3.1.6, 4.2.1]
*/
public class MergeSortVersions {

    public static List<String> sort(List<String> v1, List<String> v2) {
        int i1 = 0;
        int i2 = 0;
        List<String> v3 = new ArrayList<String>();
        while (i1 < v1.size() && i2 < v2.size()) {
            if (lt(v1.get(i1), v2.get(i2))) {
                v3.add(v1.get(i1));
                ++i1;
            } else {
                v3.add(v2.get(i2));
                ++i2;
            }
        }
        for (; i1 < v1.size(); ++i1) {
            v3.add(v1.get(i1));
        }
        for (; i2 < v2.size(); ++i2) {
            v3.add(v2.get(i2));
        }
        return v3;
    }

    private static boolean lt(String v1, String v2) {
        String[] v1segs = v1.split("\\.");
        String[] v2segs = v2.split("\\.");
        int i = 0;
        for (; i < Math.min(v1segs.length, v2segs.length); ++i) {
            int v1i = Integer.valueOf(v1segs[i]);
            int v2i = Integer.valueOf(v2segs[i]);
            if (v1i < v2i) {
                return true;
            } else if (v1i > v2i) {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<String> v1 = new ArrayList<String>() {
            {
                add("1.0.0");
                add("1.0.2");
                add("2.0.0");
                add("3.1.5");
                add("4.2.1");
            }
        };

        List<String> v2 = new ArrayList<String>() {
            {
                add("0.0.1");
                add("2.0.0");
                add("3.1.6");
            }
        };

        System.out.println(sort(v1, v2));
    }
}
