import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RedPacket {

    public static final double min = 1;
    public static final double max = 0.3;

    public static void main(String[] args) {
        List<Integer> res = redPacket(100, 10);
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i) + " ");
        }
    }

    public static List<Integer> redPacket(double money, int people) {
        List<Integer> res = new ArrayList<>();
        double minMoney = min;
        double maxMoney = money * max;

        int shareMoney = 0;
        int sum = 0;

        for (int i = 0; i < people; i++) {
            double max = money - maxMoney * (people - i - 1);
            double min = money - minMoney * (people - i - 1);
            minMoney = minMoney > max ? minMoney : max;
            maxMoney = maxMoney < min ? maxMoney : min;

            shareMoney = (int) Math.floor((maxMoney - minMoney) * Math.random() + minMoney);
            money = money - shareMoney;
            sum += shareMoney;

            res.add(shareMoney);
        }

        System.out.println("Total money: " + sum);
        return res;
    }
}
