class DoubleNode {
    String studentId;
    String name;
    double gpa;
    DoubleNode next;
    DoubleNode prev;

    public DoubleNode(String studentId, String name, double gpa) {
        this.studentId = studentId;
        this.name = name;
        this.gpa = gpa;
        this.next = null;
        this.prev = null;
    }
}