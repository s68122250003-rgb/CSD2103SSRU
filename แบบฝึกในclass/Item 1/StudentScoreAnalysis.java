public class StudentScoreAnalysis {

    public static void main(String[] args) {
        // ข้อมูลคะแนนนักศึกษา 5 คน x 4 รายการ (Quiz, Assignment, Midterm, Final)
        int[][] scores = {
                {8, 18, 25, 40},
                {7, 15, 20, 35},
                {10, 20, 28, 42},
                {6, 16, 22, 30},
                {9, 19, 27, 45}
        };

        int numStudents = scores.length;       // n = 5
        int numCategories = scores[0].length;  // m = 4

        // 1. คำนวณคะแนนรวมของนักศึกษาแต่ละคน และเก็บไว้ใน Array 1 มิติ
        int[] totals = new int[numStudents];
        int grandTotal = 0;

        for (int i = 0; i < numStudents; i++) {
            int sum = 0;
            for (int j = 0; j < numCategories; j++) {
                sum += scores[i][j];
            }
            totals[i] = sum;
            grandTotal += sum;
            System.out.println("Student " + (i + 1) + " Total = " + totals[i]);
        }

        // 2 & 3. หาคะแนนรวมสูงสุด, ต่ำสุด และค่าเฉลี่ยของทั้งห้อง
        int highest = totals[0];
        int lowest = totals[0];

        for (int i = 1; i < numStudents; i++) {
            if (totals[i] > highest) highest = totals[i];
            if (totals[i] < lowest) lowest = totals[i];
        }

        double average = (double) grandTotal / numStudents;

        System.out.println("\nAverage = " + average);
        System.out.println("Highest = " + highest);
        System.out.println("Lowest = " + lowest);

        // 4. แสดงหมายเลขนักศึกษาที่มีคะแนนรวมสูงกว่าค่าเฉลี่ย
        System.out.println("\nStudents above average:");
        for (int i = 0; i < numStudents; i++) {
            if (totals[i] > average) {
                System.out.println("Student " + (i + 1));
            }
        }

        // 5. หาค่าเฉลี่ยของคะแนนแต่ละประเภท (Quiz, Assignment, Midterm, Final)
        String[] categoryNames = {"Quiz", "Assignment", "Midterm", "Final"};
        System.out.println("\nAverage per category:");
        for (int j = 0; j < numCategories; j++) {
            int categorySum = 0;
            for (int i = 0; i < numStudents; i++) {
                categorySum += scores[i][j];
            }
            double catAvg = (double) categorySum / numStudents;
            System.out.println(categoryNames[j] + " Average = " + catAvg);
        }
    }
}