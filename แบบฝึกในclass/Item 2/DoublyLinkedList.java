class DoublyLinkedList {
    private DoubleNode head;
    private DoubleNode tail;

    public void insertFirst(String id, String name, double gpa) {
        DoubleNode newNode = new DoubleNode(id, name, gpa);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public void insertLast(String id, String name, double gpa) {
        DoubleNode newNode = new DoubleNode(id, name, gpa);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public DoubleNode search(String id) {
        DoubleNode current = head;
        while (current != null) {
            if (current.studentId.equals(id)) return current;
            current = current.next;
        }
        return null;
    }

    public boolean delete(String id) {
        DoubleNode current = search(id);
        if (current == null) return false;

        if (current == head) head = current.next;
        if (current == tail) tail = current.prev;

        if (current.prev != null) current.prev.next = current.next;
        if (current.next != null) current.next.prev = current.prev;

        return true;
    }

    public void display() {
        displayForward();
    }

    // Operation เฉพาะ: แสดงข้อมูลทิศทางเดินหน้า
    public void displayForward() {
        System.out.print("Forward: ");
        DoubleNode current = head;
        while (current != null) {
            System.out.print(current.studentId + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    // Operation เฉพาะ: แสดงข้อมูลทิศทางถอยหลัง
    public void displayBackward() {
        System.out.print("Backward: ");
        DoubleNode current = tail;
        while (current != null) {
            System.out.print(current.studentId + (current.prev != null ? " -> " : ""));
            current = current.prev;
        }
        System.out.println();
    }
}


