package Graph.kruskal;

import java.util.Arrays;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        Integer[] test = new Integer[]{1,2,3,4,5};
        Arrays.sort(test, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                Integer num1 = (Integer) o1;
                Integer num2 = (Integer) o2;

                if (num1 - num2 < 0) {
                    return 1;
                }
                return -1;
            }
        });

        for (int num : test) {
            System.out.println(num);
        }
    }
}
