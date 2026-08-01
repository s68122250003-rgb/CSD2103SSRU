import java.util.Arrays;

public class ArrayPartitionByK {

    // 1. Recursive Partition
    public static void partitionRecursive(int[] a, int k, int left, int right) {
        // Base Case
        if (a == null || left >= right) {
            return;
        }

        // Recursive Case
        if (a[left] <= k) {
            partitionRecursive(a, k, left + 1, right);
        } else if (a[right] > k) {
            partitionRecursive(a, k, left, right - 1);
        } else {
            // สลับค่าเมื่อด้านซ้าย > k และด้านขวา <= k
            int temp = a[left];
            a[left] = a[right];
            a[right] = temp;
            partitionRecursive(a, k, left + 1, right - 1);
        }
    }

    // 2. Iterative Partition
    public static void partitionIterative(int[] a, int k) {
        if (a == null || a.length <= 1) {
            return;
        }

        int left = 0;
        int right = a.length - 1;

        while (left < right) {
            while (left < right && a[left] <= k) {
                left++;
            }
            while (left < right && a[right] > k) {
                right--;
            }
            if (left < right) {
                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;
                left++;
                right--;
            }
        }
    }

    // 3. Sorting-Based Algorithm
    public static void partitionBySorting(int[] a, int k) {
        if (a == null || a.length <= 1) {
            return;
        }
        Arrays.sort(a);
    }

    public static void main(String[] args) {
        int[] input = {12, 4, 7, 15, 3, 10, 8};
        int k = 8;

        System.out.println("Original Array: " + Arrays.toString(input) + ", k = " + k);

        int[] arr1 = input.clone();
        partitionRecursive(arr1, k, 0, arr1.length - 1);
        System.out.println("1. Recursive Partition : " + Arrays.toString(arr1));

        int[] arr2 = input.clone();
        partitionIterative(arr2, k);
        System.out.println("2. Iterative Partition : " + Arrays.toString(arr2));

        int[] arr3 = input.clone();
        partitionBySorting(arr3, k);
        System.out.println("3. Sorting-Based       : " + Arrays.toString(arr3));
    }
}