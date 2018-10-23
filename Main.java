import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final int DATA_SIZE = 40;



    public static void main(String... theArgs) {
        List<Integer> randomList = generateRandomNumber(DATA_SIZE);
//        System.out.println(randomList);
        List<List<Integer>> subLists = generate4DataSet(randomList);
//        System.out.println(subLists);

        for (List<Integer> list: subLists) {

            quickSort(list);

        }
        System.out.println(subLists);
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

    private static List<List<Integer>> generate4DataSet(List<Integer> list) {
        List<List<Integer>> subLists = new ArrayList<>();
        try {

            for (int i = 0; i < DATA_SIZE; i += DATA_SIZE / 4) {
                List<Integer> subList = generateSubList(list, i, i + DATA_SIZE / 4);
                subLists.add(subList);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please adjust DATA_SIZE can be divided by 4");
            return null;
        }
        return subLists;
    }

    private static void quickSort(List<Integer> list) {
        quickSort(list, 0, list.size() -1);
    }

    private static void quickSort(List<Integer> list, int start, int end) {
        if (start >= end) {
            return;
        }
        int index = quickSelect(list, start, end);
        quickSort(list, start, index -1);
        quickSort(list, index + 1, end);
    }

    private static int quickSelect(List<Integer> list, int start, int end) {
        int pivot = list.get(start), index = start;
        swap(list, start, end);
        while (index < end) {
            if (list.get(index) < pivot) {
                swap(list, index, start++);
            }
            index++;
        }
        swap(list, start, end);
        return start;
    }

    private static void swap(List<Integer> list, int index1, int index2) {
        int temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}
