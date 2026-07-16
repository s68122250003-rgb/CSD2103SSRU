import java.util.Scanner; 

  

public class ArraySearch { 

    public static void main(String[] args) { 

        Scanner scanner = new Scanner(System.in); 

         

        String[] names = {"Somchai", "Somsri", "Sompong", "Nadech", "Yaya"}; 

         

        System.out.print("Enter name to search: "); 

        String searchName = scanner.next(); 

         

        boolean isFound = false; 

         

        for (int i = 0; i < names.length; i++) { 

            if (names[i].equalsIgnoreCase(searchName)) { 

                isFound = true; 

                break;  

            } 

        } 

         

        if (isFound) { 

            System.out.println("Found"); 

        } else { 

            System.out.println("Not Found"); 

        } 

         

        scanner.close(); 

    } 

} 