class SinglyLinkedList {
    private Node head;

    public void insertFirst(String id, String name, double gpa) {
        Node newNode = new Node(id, name, gpa);
        newNode.next = head;
        head = newNode;
    }

    public void insertLast(String id, String name, double gpa) {
        Node newNode = new Node(id, name, gpa);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public Node search(String id) {
        Node current = head;
        while (current != null) {
            if (current.studentId.equals(id)) return current;
            current = current.next;
        }
        return null;
    }

    public boolean delete(String id) {
        if (head == null) return false;
        if (head.studentId.equals(id)) {
            head = head.next;
            return true;
        }
        Node current = head;
        while (current.next != null && !current.next.studentId.equals(id)) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
            return true;
        }
        return false;
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print("[" + current.studentId + " | " + current.name + " | GPA: " + current.gpa + "] -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Operation เฉพาะ: ค้นหานักศึกษาที่มี GPA สูงที่สุด
    public Node findMaxGPA() {
        if (head == null) return null;
        Node maxNode = head;
        Node current = head.next;
        while (current != null) {
            if (current.gpa > maxNode.gpa) {
                maxNode = current;
            }
            current = current.next;
        }
        return maxNode;
    }
}