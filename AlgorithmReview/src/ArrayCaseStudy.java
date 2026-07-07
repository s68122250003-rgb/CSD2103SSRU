public class ArrayCaseStudy {
    private int[] scores = {6, 8, 4, 9, 7, 5, 10, 3, 8, 2};

    public void analyzeScores() {
        int sum = 0, max = scores[0], min = scores[0], countPass = 0;
        String needsReview = "";

        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];
            if (scores[i] > max) max = scores[i];
            if (scores[i] < min) min = scores[i];
            if (scores[i] >= 7) countPass++;
            if (scores[i] < 5) needsReview += "Student " + (i + 1) + " (Score: " + scores[i] + ") ";
        }

        System.out.println("Total Score: " + sum);
        System.out.println("Average: " + (double) sum / scores.length);
        System.out.println("Max: " + max + ", Min: " + min);
        System.out.println("Students >= 7: " + countPass);
        System.out.println("Needs Review: " + needsReview);
    }
    public static void main(String[] args) {
        ArrayCaseStudy program = new ArrayCaseStudy();
        program.analyzeScores();
    }
}