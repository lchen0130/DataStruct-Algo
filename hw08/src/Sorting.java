import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Leo Chen
 * @userid lchen396
 * @GTID 903218169
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement cocktail sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Passed in comparator or the "
                + "arr is null. Please check the input parameters are valid");
        }
        boolean modified = true;
        int front = 0;
        int back = arr.length;


        while (front < back && modified) {
            modified = false;
            int last = back;
            for (int i = front; i < back - 1; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    T temp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = temp;
                    modified = true;
                    last = i + 1;
                }

            }
            if (last < --back) {
                back = last;
            }
            if (!modified || back == 0) {
                return;
            }
            modified = false;
            int first = front;
            for (int i = back - 1; i > front; i--) {
                if (comparator.compare(arr[i], arr[i - 1]) < 0) {
                    T temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                    modified = true;
                    first = i - 1;
                }
            }
            if (first > front) {
                front = first;
            }

        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable).
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Passed in comparator or the "
                + "arr is null. Please check the input parameters are valid");
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j], arr[j - 1]) < 0) {
                T temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * Note that there may be duplicates in the array, but they may not
     * necessarily stay in the same relative order.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Passed in comparator or the "
                + "arr is null. Please check the input parameters are valid");
        }

        for (int i = 0; i < arr.length; i++) {
            int smallest = i;
            for (int j = i + 1; j < arr.length; i++) {
                if (comparator.compare(arr[j], arr[smallest]) < 0) {
                    smallest = j;
                }
            }
            if (smallest != i) {
                T temp = arr[smallest];
                arr[smallest] = arr[i]; //swap the smallest with the first elem
                arr[i] = temp;
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Passed in comparator or the "
                + "arr is null. Please check the input parameters are valid");
        }

        helpQuickSort(arr, comparator, 0, arr.length, rand);
    }

    /**
     * quick sort helper that uses pivots to generalize changes
     * @param arr to be passed in to change
     * @param comparator that is used to compare
     * @param left index of array
     * @param right index of array
     * @param rand seed that will be used to generate pivot
     * @param <T> type that is used
     */
    private static <T> void helpQuickSort(T[] arr, Comparator<T> comparator,
                                          int left, int right, Random rand) {
        if (left >= right) {
            return;
        }
        int pivotIndex = left + (rand.nextInt(right - left));
        T pivot = arr[pivotIndex];
        arr[pivotIndex] = arr[left];
        arr[left] = pivot;
        int leftIndex = left + 1;
        int rightIndex = right - 1;

        while ((leftIndex < rightIndex)) {
            while ((leftIndex < rightIndex) && comparator.compare(
                    arr[rightIndex], pivot) <= 0) {
                leftIndex++;
            }
            while ((leftIndex < rightIndex) && (comparator.compare(
                    arr[rightIndex], pivot) >= 0)) {
                rightIndex--;
            }
            if (leftIndex < rightIndex) {
                T temp = arr[rightIndex];
                arr[rightIndex] = arr[leftIndex];
                arr[leftIndex] = temp;
                rightIndex--;
                leftIndex++;
            }
        }
        T temp = arr[rightIndex];
        arr[rightIndex] = arr[left];
        arr[left] = temp;
        helpQuickSort(arr, comparator, left, rightIndex - 1, rand);
        helpQuickSort(arr, comparator, rightIndex + 1, right, rand);
    }



    /**
          * Implement merge sort.
          *
          * It should be:
          *  stable
          *
          * Have a worst case running time of:
          *  O(n log n)
          *
          * And a best case running time of:
          *  O(n log n)
          *
          * You can create more arrays to run mergesort, but at the end,
          * everything should be merged back into the original T[]
          * which was passed in.
          *
          * Any duplicates in the array should be in the same relative position
          *      after
          * sorting as they were before sorting.
          *
          * @throws IllegalArgumentException if the array or comparator is null
          * @param <T> data type to sort
          * @param arr the array to be sorted
          * @param comparator the Comparator used to compare the data in arr
          */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Passed in comparator or the "
                + "arr is null. Please check the input parameters are valid");
        }
        if (arr.length <= 1) {
            return;
        }
        int mid = arr.length / 2;
        T[] leftArr = (T[]) new Object[mid];
        T[] rightArr = (T[]) new Object[arr.length - mid];
        for (int i = 0; i < mid; i++) {
            leftArr[i] = arr[i];
        }
        int j = 0;
        for (int i = mid; i < arr.length; i++) {
            rightArr[j++] = arr[i];
        }
        mergeSort(leftArr, comparator);
        mergeSort(rightArr, comparator);
        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = 0;
        while (leftIndex < mid && rightIndex < arr.length - mid) {
            if (comparator.compare(leftArr[leftIndex], rightArr[rightIndex])
                    <= 0) {
                arr[currentIndex] = leftArr[leftIndex++];
            } else {
                arr[currentIndex] = rightArr[rightIndex++];
            }
            currentIndex++;
        }
        while (leftIndex < mid) {
            arr[currentIndex] = leftArr[leftIndex++];
            currentIndex++;
        }
        while (rightIndex < arr.length - mid) {
            arr[currentIndex] = rightArr[rightIndex++];
            currentIndex++;
        }

    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * Do NOT use {@code Math.pow()} in your sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("The array is null "
                + "please pass in a valid array parameter");
        }
        //num / 1 % 10
        //num / 10 % 10
        LinkedList<Integer>[] counter;
        counter = (LinkedList<Integer>[]) new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            counter[i] = new LinkedList<>();
        }
        int mod = 10;
        int dev = 1;
        boolean cont = true;
        while (cont) {
            cont = false;
            for (int number : arr) {
                int bucket = number / dev; //shift over
                if ((bucket / 10) != 0) {
                    cont = true;
                }
                counter[bucket % mod + 9].add(number);

            }
            int arrayIndex = 0;
            for (int i = 0; i < counter.length; i++) {
                for (int number: counter[i]) {
                    arr[arrayIndex++] = number;
                }
                counter[i].clear();
            }
            dev *= 10;
        }
    }
}
