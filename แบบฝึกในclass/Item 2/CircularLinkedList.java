class CircularLinkedList {
    private Node head;
    private Node tail;

    public void insertFirst(String id, String name, double gpa) {
        Node newNode = new Node(id, name, gpa);
        if (head == null) {
            head = tail = newNode;
            newNode.next = head;
        } else {
            newNode.next = head;
            head = newNode;
            tail.next = head;
        }
    }

    public void insertLast(String id, String name, double gpa) {
        Node newNode = new Node(id, name, gpa);
        if (head == null) {
            head = tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }
    }

    public Node search(String id) {
        if (head == null) return null;
        Node current = head;
        do {
            if (current.studentId.equals(id)) return current;
            current = current.next;
        } while (current != head);
        return null;
    }

    public boolean delete(String id) {
        if (head == null) return false;

        Node current = head;
        Node prev = tail;

        do {
            if (current.studentId.equals(id)) {
                if (head == tail) {
                    head = tail = null;
                } else {
                    prev.next = current.next;
                    if (current == head) head = current.next;
                    if (current == tail) tail = prev;
                }
                return true;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        return false;
    }

    public void display() {
        if (head == null) {
            System.out.println("Empty List");
            return;
        }
        Node current = head;
        do {
            System.out.print(current.studentId + " -> ");
            current = current.next;
        } while (current != head);
        System.out.println("(Head)");
    }

    // Operation เฉพาะ: แสดงข้อมูล 2 รอบเพื่อพิสูจน์การเชื่อมกลับไปยังโหนดแรก
    public void displayTwoRounds() {
        if (head == null) return;
        Node current = head;
        int roundCount = 0;
        System.out.print("Two Rounds Display: ");
        while (roundCount < 2) {
            System.out.print(current.studentId + " -> ");
            current = current.next;
            if (current == head) {
                roundCount++;
            }
        }
        System.out.println("...");
    }
}