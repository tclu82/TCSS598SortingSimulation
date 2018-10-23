import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final int DATA_SIZE = 40;



    public static void main(String... theArgs) {
        List<Integer> randomList = generateRandomNumber(DATA_SIZE);
//        System.out.println(randomList);


        try {
            List<List<Integer>> subLists = new ArrayList<>();
            for (int i = 0; i < DATA_SIZE; i += DATA_SIZE / 4) {
                List<Integer> subList = generateSubList(randomList, i, i + DATA_SIZE / 4);
                subLists.add(subList);
            }
            System.out.println(subLists);






        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please adjust DATA_SIZE can be divided by 4");
        }
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

    private static List<Integer> generateSubList(List<Integer> list, int start, int end) {
        List<Integer> subList = list.subList(start, end);
        return subList;
    }

}
