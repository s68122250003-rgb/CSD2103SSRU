public class StringReverser {

    /**
     * อัลกอริทึมที่ 1: Recursive Algorithm
     * กลับลำดับสตริงด้วยการเรียกเมธอดเวียนเกิด
     */
    public static String reverseRecursive(String s) {
        // Handling special & edge cases
        if (s == null) {
            return null;
        }
        
        // Base Case: สตริงว่างหรือเหลือเพียง 1 ตัวอักษร ไม่ต้องวนต่อ
        if (s.length() <= 1) {
            return s;
        }

        // Recursive Case: ตัวอักษรสุดท้าย + ผลการวนกลับสตริงที่เหลือด้านหน้า
        char lastChar = s.charAt(s.length() - 1);
        String remaining = s.substring(0, s.length() - 1);
        
        return lastChar + reverseRecursive(remaining);
    }

    /**
     * อัลกอริทึมที่ 2: Iterative Algorithm
     * กลับลำดับสตริงด้วยการใช้วนนิ่งลูปถอยหลังคู่กับ StringBuilder
     */
    public static String reverseIterative(String s) {
        // Handling special & edge cases
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder(s.length());
        
        // อ่านย้อนกลับจากตำแหน่งสุดท้ายลงมาถึงตำแหน่งแรก
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    // เมธอดสำหรับสร้างสตริงทดสอบตามขนาดที่ต้องการ
    private static String generateTestString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // 1. ทดสอบฟังก์ชันด้วยข้อมูลตัวอย่างจากโจทย์
        String inputSample = "pots&pans";
        System.out.println("=== Sample Test ===");
        System.out.println("Input:  " + inputSample);
        System.out.println("Recursive Output: " + reverseRecursive(inputSample));
        System.out.println("Iterative Output: " + reverseIterative(inputSample));
        System.out.println();

        // 2. ทดสอบกรณีพิเศษ (Edge Cases)
        System.out.println("=== Edge Cases Test ===");
        System.out.println("Null input: " + reverseRecursive(null));
        System.out.println("Empty input: \"" + reverseRecursive("") + "\"");
        System.out.println("Single char: " + reverseRecursive("A"));
        System.out.println();

        // 3. ทดสอบเปรียบเทียบประสิทธิภาพขนาดข้อมูล 10, 100, 1000, 10000 ตัวอักษร
        int[] sizes = {10, 100, 1000, 10000};
        System.out.println("=== Performance Benchmark ===");
        
        for (int size : sizes) {
            String testStr = generateTestString(size);

            // ทดสอบ Iterative
            long startIter = System.nanoTime();
            reverseIterative(testStr);
            long endIter = System.nanoTime();

            // ทดสอบ Recursive
            long startRec = System.nanoTime();
            try {
                reverseRecursive(testStr);
            } catch (StackOverflowError e) {
                System.out.println("Size " + size + " -> Recursive StackOverflowError!");
            }
            long endRec = System.nanoTime();

            System.out.printf("Size %5d | Iterative: %8d ns | Recursive: %8d ns\n",
                    size, (endIter - startIter), (endRec - startRec));
        }
    }
}