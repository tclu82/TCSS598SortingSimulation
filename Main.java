import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final int DATA_SIZE = 20;



    public static void main(String... theArgs) {
        List<Integer> randomList = generateRandomNumber(DATA_SIZE);
        System.out.println(randomList.size());
        System.out.println(randomList);
    }

    private static List<Integer> generateRandomNumber(int dataSize) {
        Random rand = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i=1; i<=dataSize; i++) {
            list.add(i);
        }
        List<Integer> result = new ArrayList<>();
        for (int i=1; i<=dataSize; i++) {
            int index = rand.nextInt(list.size());
            int number = list.remove(index);
            result.add(number);
        }
        return result;
    }

}
