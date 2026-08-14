class LinkedListQueue {
    private Node front;
    private Node rear;

    public LinkedListQueue() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(Student s) {
        Node newNode = new Node(s);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    public Student dequeue() {
        if (front == null) {
            System.out.println("Queue Empty!");
            return null;
        }
        Student removed = front.student;
        front = front.next;
        if (front == null) rear = null;
        return removed;
    }

    public Student peek() {
        if (front == null) return null;
        return front.student;
    }

    public void displayQueue() {
        if (front == null) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Front -> ");
        Node current = front;
        while (current != null) {
            System.out.print(current.student.id + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }
}