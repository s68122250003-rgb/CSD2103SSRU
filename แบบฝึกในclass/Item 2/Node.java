class Node {
    String studentId;
    String name;
    double gpa;
    Node next;

    public Node(String studentId, String name, double gpa) {
        this.studentId = studentId;
        this.name = name;
        this.gpa = gpa;
        this.next = null;
    }
}