import java.util.Arrays;

public class Question6TwoSum {

    // =========================================================================
    // อัลกอริทึมที่ 1: Brute Force (ตรวจสอบทุกคู่)
    // Time Complexity: O(n^2) | Space Complexity: O(1)
    // =========================================================================
    public static boolean findPairBruteForce(int[] a, int k) {
        if (a == null || a.length < 2) return false;

        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] + a[j] == k) {
                    System.out.println("[Brute Force] พบคู่คำตอบ: " + a[i] + " + " + a[j] + " = " + k);
                    return true;
                }
            }
        }
        return false;
    }

    // =========================================================================
    // อัลกอริทึมที่ 2: Recursive Two-Pointer (ขยับตัวชี้ซ้าย-ขวาแบบเวียนเกิด)
    // Time Complexity: O(n) | Space Complexity: O(n) จาก Call Stack
    // =========================================================================
    public static boolean findPairRecursive(int[] a, int k, int left, int right) {
        // Base Case 1: หาไม่พบ (ตัวชี้เดินมาชนหรือสวนทางกัน)
        if (a == null || left >= right) return false;

        int sum = a[left] + a[right];

        // Base Case 2: พบคู่คำตอบ
        if (sum == k) {
            System.out.println("[Recursive Two-Pointer] พบคู่คำตอบ: " + a[left] + " + " + a[right] + " = " + k);
            return true;
        } 
        // Recursive Case 1: ผลรวมน้อยไป ให้เลื่อนฝั่งซ้ายไปทางขวา
        else if (sum < k) {
            return findPairRecursive(a, k, left + 1, right);
        } 
        // Recursive Case 2: ผลรวมมากไป ให้เลื่อนฝั่งขวาไปทางซ้าย
        else {
            return findPairRecursive(a, k, left, right - 1);
        }
    }

    // Wrapper Method สำหรับ Recursive Two-Pointer ให้เรียกใช้ง่ายขึ้น
    public static boolean findPairRecursive(int[] a, int k) {
        if (a == null || a.length < 2) return false;
        return findPairRecursive(a, k, 0, a.length - 1);
    }

    // =========================================================================
    // อัลกอริทึมที่ 3: Binary Search (ค้นหาคู่สม k - A[i])
    // Time Complexity: O(n log n) | Space Complexity: O(1)
    // =========================================================================
    public static boolean findPairBinarySearch(int[] a, int k) {
        if (a == null || a.length < 2) return false;

        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int target = k - a[i]; // ค่าคู่สมที่ต้องการ
            
            // ค้นหา target ในช่วงดรรชนี i + 1 ถึง n - 1
            int foundIndex = binarySearch(a, i + 1, n - 1, target);
            if (foundIndex != -1) {
                System.out.println("[Binary Search] พบคู่คำตอบ: " + a[i] + " + " + a[foundIndex] + " = " + k);
                return true;
            }
        }
        return false;
    }

    // Helper Method: Binary Search แบบ Iterative
    private static int binarySearch(int[] a, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == target) {
                return mid;
            } else if (a[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // =========================================================================
    // Main Method สำหรับทดสอบการทำงาน
    // =========================================================================
    public static void main(String[] args) {
        // ข้อมูลตัวอย่าง (อาร์เรย์ต้องเรียงลำดับแล้ว)
        int[] a = {2, 4, 7, 11, 15, 20};
        int k = 18;

        System.out.println("=================================================");
        System.out.println("ข้อมูลนำเข้า (Input Array): " + Arrays.toString(a));
        System.out.println("เป้าหมาย (Target k): " + k);
        System.out.println("=================================================");

        // ทดสอบทั้ง 3 วิธี
        System.out.println("--- ผลการทำงาน ---");
        boolean res1 = findPairBruteForce(a, k);
        boolean res2 = findPairRecursive(a, k);
        boolean res3 = findPairBinarySearch(a, k);

        System.out.println("-------------------------------------------------");
        System.out.println("ทุกอัลกอริทึมให้ผลลัพธ์ตรงกัน: " + (res1 && res2 && res3));
    }
}