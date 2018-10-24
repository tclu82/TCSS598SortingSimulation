import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    // Global data size
    private static final int DATA_SIZE = 20;

    /**
     * Main method
     *
     * @param theArgs
     */
    public static void main(String... theArgs) {
        List<Integer> randomList = generateRandomNumber(DATA_SIZE);
        List<List<Integer>> subLists = generate4DataSet(randomList);

        for (List<Integer> list: subLists) {
            // Put your sorting method here

            int sortedness = calculateSortednessByInsertionIndex(list);

            StringBuilder sb = new StringBuilder("Sortedness(Insertion index) of ");
            sb.append(list);
            sb.append(" is " + sortedness);
            System.out.println(sb.toString());

//            list = mergeSort(list);
//            System.out.println(list);
            quickSort(list);
        }
//        System.out.println(subLists);
    }

    /**
     * Merge sort
     *
     * @param list input list
     * @return A sorted list
     */
    private static List<Integer> mergeSort(List<Integer> list) {
        return mergeSort(list, 0, list.size()-1);
    }

    /**
     * Merge sort helper
     *
     * @param list input list
     * @param start start index
     * @param end end index
     * @return A sorted list
     */
    private static List<Integer> mergeSort(List<Integer> list, int start, int end) {
        // Out of range
        if (start > end) {
            return null;
        }
        // Only 1 element, base case
        if (start == end) {
            List<Integer> result = new ArrayList<>();
            result.add(list.get(start));
            return result;
        }
        // Divide into 2 list
        int mid = (start + end) / 2;
        List<Integer> leftList = mergeSort(list, start, mid);
        List<Integer> rightList = mergeSort(list, mid + 1, end);
        // Merge 2 lists into a sorted 1 list
        return merge(leftList, rightList);
    }

    /**
     * Merge 2 lists into a sorted 1
     *
     * @param list1 input list1
     * @param list2 input list2
     * @return A sorted list
     */
    private static List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        // Check valid input
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        // List will contain sorted numbers for return
        List<Integer> result = new ArrayList<>();
        int index1 = 0, index2 = 0;

        while (index1 < list1.size() && index2 < list2.size()) {

            if (list1.get(index1) < list2.get(index2)) {
                result.add(list1.get(index1));
                index1++;

            } else {
                result.add(list2.get(index2));
                index2++;
            }
        }
        while (index1 < list1.size()) {
            result.add(list1.get(index1));
            index1++;
        }
        while (index2 < list2.size()) {
            result.add(list2.get(index2));
            index2++;
        }
        return result;
    }

    /**
     * Quick sort
     *
     * @param list input list
     */
    private static void quickSort(List<Integer> list) {
        quickSort(list, 0, list.size() -1);
    }

    /**
     * Quick sort helper method
     *
     * @param list input list
     * @param start start index
     * @param end end index
     */
    private static void quickSort(List<Integer> list, int start, int end) {
        if (start >= end) {
            return;
        }
        int index = quickSelect(list, start, end);
        quickSort(list, start, index -1);
        quickSort(list, index + 1, end);
    }

    /**
     * Find index of 1 element with sorted position
     *
     * @param list input list
     * @param start start index
     * @param end end index
     * @return index of sorted element position
     */
    private static int quickSelect(List<Integer> list, int start, int end) {
        int pivot = list.get(start), index = start;
        // Put the compare value to the end
        swap(list, start, end);
        while (index < end) {
            // If current value is smaller than pivot, swap with start index
            if (list.get(index) < pivot) {
                swap(list, index, start++);
            }
            index++;
        }
        // Put the swap value back
        swap(list, start, end);
        return start;
    }

    /**
     * Generate a list contains 1 to dataSize with random order
     *
     * @param dataSize
     * @return List<Integer> result
     */
    private static List<Integer> generateRandomNumber(int dataSize) {
        Random rand = new Random();
        // Generate a list from 1 to dataSize
        List<Integer> list = new ArrayList<>();
        for (int i=1; i<=dataSize; i++) {
            list.add(i);
        }
        List<Integer> result = new ArrayList<>();
        // Consume list and use it to create a random order list
        for (int i=1; i<=dataSize; i++) {
            int index = rand.nextInt(list.size());
            int number = list.remove(index);
            result.add(number);
        }
        return result;
    }

    /**
     * Generate sub-list from input list from start (included) to end (excluded)
     *
     * @param list input list
     * @param start start index
     * @param end end index
     * @return sub-list
     */
    private static List<Integer> generateSubList(List<Integer> list, int start, int end) {
        List<Integer> subList = list.subList(start, end);
        return subList;
    }

    /**
     * Generate 4 data set from input list
     *
     * @param list input list
     * @return List of List Integer
     */
    private static List<List<Integer>> generate4DataSet(List<Integer> list) {
        List<List<Integer>> subLists = new ArrayList<>();
        try {
            for (int i = 0; i < DATA_SIZE; i += DATA_SIZE / 4) {
                List<Integer> subList = generateSubList(list, i, i + DATA_SIZE / 4);
                subLists.add(subList);
            }
        // If the DATA_SIZE can't be divided by 4, ask user to modify.
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please adjust DATA_SIZE can be divided by 4");
            return null;
        }
        return subLists;
    }

    /**
     * Swap 2 list value
     *
     * @param list input list
     * @param index1
     * @param index2
     */
    private static void swap(List<Integer> list, int index1, int index2) {
        int temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    /**
     * Calculate sortedness with longest increasing subsequence
     *
     * @param list input list
     * @return sortedness
     */
    private static int calculateSortednessByInsertionIndex(List<Integer> list) {
        int longestIncreasingSubsequence = findLlongestIncreasingSubsequence(list);
        return list.size() - longestIncreasingSubsequence;
    }

    /**
     * Find longest increasing subsequence
     *
     * @param list
     * @return length of longest increasing subsequence
     */
    private static int findLlongestIncreasingSubsequence(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }
        int size = list.size();
        int[] dp = new int[size];
        int maxLen = 0;

        for (int i=0; i<size; i++) {
            dp[i] = 1;

            for (int j=i-1; j>=0; j--) {
                // Ascending
                if (list.get(j) < list.get(i)) {
                    // At least d[j] + 1
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
