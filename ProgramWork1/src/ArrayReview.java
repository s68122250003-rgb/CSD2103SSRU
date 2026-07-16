import java.util.Scanner; 

  

public class ArrayReview { 

    public static void main(String[] args) { 

        Scanner scanner = new Scanner(System.in); 

         

        int[] scores = new int[5]; 

        int totalScore = 0; 

         

        for (int i = 0; i < scores.length; i++) { 

            System.out.print("Enter score " + (i + 1) + ": "); 

            scores[i] = scanner.nextInt(); 

             

            totalScore += scores[i]; 

        } 

         

        double averageScore = totalScore / 5.0; 

         

        System.out.println("Total score = " + totalScore); 

        System.out.println("Average score = " + averageScore); 

         

        scanner.close(); 

    } 

} 

 