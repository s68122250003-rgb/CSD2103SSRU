public class Main {
    public static void main(String[] args) {
        // ข้อมูลตัวอย่างนักศึกษา 6 คน
        String[][] students = {
                {"65001", "Somchai", "3.50"},
                {"65002", "Somsri", "3.85"},
                {"65003", "Somsak", "2.75"},
                {"65004", "Somyot", "3.95"},
                {"65005", "Somporn", "3.20"},
                {"65006", "Somjai", "3.60"}
        };

        System.out.println("=== 1. Singly Linked List Test ===");
        SinglyLinkedList sll = new SinglyLinkedList();
        for (String[] s : students) {
            sll.insertLast(s[0], s[1], Double.parseDouble(s[2]));
        }
        sll.display();
        Node maxGpaStudent = sll.findMaxGPA();
        if (maxGpaStudent != null) {
            System.out.println("Highest GPA Student: " + maxGpaStudent.name + " (" + maxGpaStudent.studentId + ") with GPA: " + maxGpaStudent.gpa);
        }

        System.out.println("\n=== 2. Doubly Linked List Test ===");
        DoublyLinkedList dll = new DoublyLinkedList();
        for (String[] s : students) {
            dll.insertLast(s[0], s[1], Double.parseDouble(s[2]));
        }
        dll.displayForward();
        dll.displayBackward();

        System.out.println("\n=== 3. Circular Linked List Test ===");
        CircularLinkedList cll = new CircularLinkedList();
        for (String[] s : students) {
            cll.insertLast(s[0], s[1], Double.parseDouble(s[2]));
        }
        cll.displayTwoRounds();
    }
}