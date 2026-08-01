public class VowelConsonantCounter {

    /**
     * Helper Method: ตรวจสอบว่าเป็นสระหรือไม่ (a, e, i, o, u)
     */
    private static boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    /**
     * Helper Method: ตรวจสอบว่าเป็นพยัญชนะภาษาอังกฤษหรือไม่
     */
    private static boolean isConsonant(char ch) {
        ch = Character.toLowerCase(ch);
        return Character.isLetter(ch) && !isVowel(ch);
    }

    /**
     * อัลกอริทึมที่ 1: Recursive Counting
     * ตรวจสอบว่าสระมากกว่าพยัญชนะหรือไม่ด้วยการคำนวณเวียนเกิด
     */
    public static boolean hasMoreVowelsRecursive(String s) {
        if (s == null) {
            return false;
        }
        return countDiffRecursive(s, 0) > 0;
    }

    /**
     * Helper Recursive Method คำนวณผลต่างสะสมระหว่างสระและพยัญชนะ
     */
    private static int countDiffRecursive(String s, int index) {
        // Base Case: วิ่งจนสุดความยาวของสตริง
        if (index >= s.length()) {
            return 0;
        }

        char ch = s.charAt(index);
        int currentVal = 0;
        if (isVowel(ch)) {
            currentVal = 1;   // สระได้ +1
        } else if (isConsonant(ch)) {
            currentVal = -1;  // พยัญชนะได้ -1
        }

        // Recursive Case: คืนค่าผลรวมปัจจุบัน + ตำแหน่งถัดไป
        return currentVal + countDiffRecursive(s, index + 1);
    }

    /**
     * อัลกอริทึมที่ 2: Iterative Counting
     * วนลูปอ่านตัวอักษรเพื่อเพิ่มนับจำนวนสระและพยัญชนะ
     */
    public static boolean hasMoreVowelsIterative(String s) {
        if (s == null) {
            return false;
        }

        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (isVowel(ch)) {
                vowels++;
            } else if (isConsonant(ch)) {
                consonants++;
            }
        }

        return vowels > consonants;
    }

    public static void main(String[] args) {
        // ชุดข้อมูลทดสอบ
        String[] testInputs = {
            "education",              // Vowels: 5, Consonants: 4 -> true
            "Hello World!",           // Vowels: 3, Consonants: 7 -> false
            "AEIOU 12345",            // Vowels: 5, Consonants: 0 -> true
            "bcdfg 99!!",             // Vowels: 0, Consonants: 5 -> false
            "a e i o u b c d f g",    // Vowels: 5, Consonants: 5 -> false (เท่ากัน)
            "",                       // Empty string -> false
            null                      // Null -> false
        };

        System.out.println("=== Vowel vs Consonant Comparison Verification ===");
        System.out.printf("%-25s | %-12s | %-12s\n", "Input String", "Recursive", "Iterative");
        System.out.println("---------------------------------------------------------");

        for (String input : testInputs) {
            boolean res1 = hasMoreVowelsRecursive(input);
            boolean res2 = hasMoreVowelsIterative(input);

            String display = (input == null) ? "null" : "\"" + input + "\"";
            System.out.printf("%-25s | %-12b | %-12b\n", display, res1, res2);
        }
    }
}