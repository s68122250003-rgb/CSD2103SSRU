import java.util.HashSet;

public class DuplicateChecker {

    // วิธีที่ 1: ใช้ลูปซ้อนกัน (Brute Force)
    public static boolean hasDuplicateBruteForce(int[] scores) {
        if (scores == null || scores.length <= 1) {
            return false;
        }

        int n = scores.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (scores[i] == scores[j]) {
                    return true; // พบข้อมูลซ้ำ
                }
            }
        }
        return false; // ไม่พบข้อมูลซ้ำ
    }

    // วิธีที่ 2: ใช้ HashSet<Integer>
    public static boolean hasDuplicateHashSet(int[] scores) {
        if (scores == null || scores.length <= 1) {
            return false;
        }

        HashSet<Integer> seen = new HashSet<>();
        for (int score : scores) {
            // หากมีคะแนนนี้ใน HashSet อยู่แล้ว แสดงว่าซ้ำ
            if (seen.contains(score)) {
                return true;
            }
            seen.add(score);
        }
        return false; // ไม่พบข้อมูลซ้ำ
    }

    public static void main(String[] args) {
        int[] scores = {78, 85, 62, 90, 78, 74};

        System.out.println("Brute Force Result: " + hasDuplicateBruteForce(scores));
        System.out.println("HashSet Result: " + hasDuplicateHashSet(scores));
    }
}