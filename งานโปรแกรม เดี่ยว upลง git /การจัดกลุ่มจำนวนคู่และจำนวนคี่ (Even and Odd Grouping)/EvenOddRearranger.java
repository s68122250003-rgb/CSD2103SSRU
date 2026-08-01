import java.util.Arrays;

public class EvenOddRearranger {

    /**
     * อัลกอริทึมที่ 1: Recursive Two-Pointer
     * สลับตำแหน่งจำนวนคู่และจำนวนคี่แบบเวียนเกิด (In-place)
     */
    public static void rearrangeRecursive(int[] a, int left, int right) {
        if (a == null || left >= right) {
            return; // Base Case: เมื่อตัวชี้ชนกันหรือสวนทางกัน
        }

        if (a[left] % 2 == 0) {
            // หากด้านซ้ายเป็นจำนวนคู่ เลื่อน left ไปทางขวา
            rearrangeRecursive(a, left + 1, right);
        } else if (a[right] % 2 != 0) {
            // หากด้านขวาเป็นจำนวนคี่ เลื่อน right ไปทางซ้าย
            rearrangeRecursive(a, left, right - 1);
        } else {
            // หากด้านซ้ายเป็นคี่ และด้านขวาเป็นคู่ ให้สลับค่า
            int temp = a[left];
            a[left] = a[right];
            a[right] = temp;
            rearrangeRecursive(a, left + 1, right - 1);
        }
    }

    /**
     * อัลกอริทึมที่ 2: Iterative Two-Pointer
     * สลับตำแหน่งจำนวนคู่และจำนวนคี่โดยใช้ลูป (In-place)
     */
    public static void rearrangeTwoPointer(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int left = 0;
        int right = a.length - 1;

        while (left < right) {
            // ขยับ left ไปทางขวาจนกว่าจะเจอจำนวนคี่
            while (left < right && a[left] % 2 == 0) {
                left++;
            }
            // ขยับ right ไปทางซ้ายจนกว่าจะเจอจำนวนคู่
            while (left < right && a[right] % 2 != 0) {
                right--;
            }
            // สลับค่า
            if (left < right) {
                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;
                left++;
                right--;
            }
        }
    }

    /**
     * อัลกอริทึมที่ 3: Extra Array
     * สร้างอาร์เรย์ใหม่ นำจำนวนคู่ใส่ก่อนแล้วจึงนำจำนวนคี่ใส่ตามหลัง (Stable)
     */
    public static int[] rearrangeExtraArray(int[] a) {
        if (a == null) {
            return null;
        }

        int n = a.length;
        int[] result = new int[n];
        int index = 0;

        // รอบที่ 1: คัดลอกจำนวนคู่
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                result[index++] = a[i];
            }
        }

        // รอบที่ 2: คัดลอกจำนวนคี่
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 != 0) {
                result[index++] = a[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // ทดสอบกรณีปกติ (General Case)
        int[] input1 = {7, 2, 9, 4, 1, 6, 3, 8};
        System.out.println("=== 1. Test General Case: " + Arrays.toString(input1) + " ===");
        
        int[] arrRec = input1.clone();
        rearrangeRecursive(arrRec, 0, arrRec.length - 1);
        System.out.println("1. Recursive Two-Pointer : " + Arrays.toString(arrRec));

        int[] arrIter = input1.clone();
        rearrangeTwoPointer(arrIter);
        System.out.println("2. Iterative Two-Pointer : " + Arrays.toString(arrIter));

        int[] arrExtra = rearrangeExtraArray(input1);
        System.out.println("3. Extra Array           : " + Arrays.toString(arrExtra));

        System.out.println("\n=== 2. Test Stability Verification ===");
        // ทดสอบความเสถียร (Stability Test Case)
        int[] stableInput = {5, 2, 7, 4, 9, 6};
        System.out.println("Original Input          : " + Arrays.toString(stableInput));
        System.out.println("Expected Stable Output  : [2, 4, 6, 5, 7, 9]");

        int[] stRec = stableInput.clone();
        rearrangeRecursive(stRec, 0, stRec.length - 1);
        System.out.println("1. Recursive Two-Pointer : " + Arrays.toString(stRec) + " (Unstable)");

        int[] stIter = stableInput.clone();
        rearrangeTwoPointer(stIter);
        System.out.println("2. Iterative Two-Pointer : " + Arrays.toString(stIter) + " (Unstable)");

        int[] stExtra = rearrangeExtraArray(stableInput);
        System.out.println("3. Extra Array           : " + Arrays.toString(stExtra) + " (Stable!)");
    }
}