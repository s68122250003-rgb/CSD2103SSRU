public class PalindromeChecker {

    /**
     * เมธอดสำหรับทำความสะอาดสตริง (ละเว้นตัวพิมพ์เล็ก-ใหญ่ ช่องว่าง และเครื่องหมายวรรคตอน)
     */
    public static String preprocess(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sb.append(Character.toLowerCase(ch));
            }
        }
        return sb.toString();
    }

    /**
     * อัลกอริทึมที่ 1: Reverse and Compare
     * สร้างสตริงย้อนกลับแล้วนำมาเปรียบเทียบกับสตริงเดิม
     */
    public static boolean isPalindromeByReverse(String s) {
        if (s == null) {
            return false;
        }
        String cleanStr = preprocess(s);
        String reversedStr = new StringBuilder(cleanStr).reverse().toString();
        return cleanStr.equals(reversedStr);
    }

    /**
     * อัลกอริทึมที่ 2: Recursive Two-Pointer
     * เปรียบเทียบตัวอักษรตำแหน่งซ้ายสุดและขวาสุดแบบเวียนเกิด
     */
    public static boolean isPalindromeRecursive(String s, int left, int right) {
        // Base Case: ตัวชี้ชนกันหรือสวนทางกัน (ตรวจสอบครบทุกตัวอักษรแล้ว)
        if (left >= right) {
            return true;
        }

        // Early Exit: หากพบตัวอักษรคู่ที่ไม่ตรงกัน ให้หยุดทำงานและตอบ false ทันที
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }

        // Recursive Case: เลื่อนตัวชี้ซ้ายไปทางขวา และตัวชี้ขวาไปทางซ้าย
        return isPalindromeRecursive(s, left + 1, right - 1);
    }

    /**
     * Helper Method สำหรับเรียกใช้ isPalindromeRecursive ได้สะดวกขึ้น
     */
    public static boolean isPalindromeRecursiveWrapper(String s) {
        if (s == null) {
            return false;
        }
        String cleanStr = preprocess(s);
        if (cleanStr.isEmpty()) {
            return true; // สตริงว่างถือว่าเป็น Palindrome
        }
        return isPalindromeRecursive(cleanStr, 0, cleanStr.length() - 1);
    }

    public static void main(String[] args) {
        // ชุดข้อมูลทดสอบตามโจทย์กำหนด
        String[] testInputs = {
            "racecar",
            "level",
            "algorithm",
            "gohangasalamiimalasagnahog",
            "A man, a plan, a canal: Panama",
            "",
            "A",
            null
        };

        System.out.println("=== Palindrome Check Verification ===");
        System.out.printf("%-35s | %-12s | %-12s\n", "Input String", "By Reverse", "Recursive");
        System.out.println("-------------------------------------------------------------------");

        for (String input : testInputs) {
            boolean res1 = isPalindromeByReverse(input);
            boolean res2 = isPalindromeRecursiveWrapper(input);
            
            String displayInput = (input == null) ? "null" : "\"" + input + "\"";
            System.out.printf("%-35s | %-12b | %-12b\n", displayInput, res1, res2);
        }
    }
}