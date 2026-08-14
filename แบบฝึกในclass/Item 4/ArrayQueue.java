class ArrayQueue {
    private Student[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new Student[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(Student s) {
        if (size == capacity) {
            System.out.println("Queue Full!");
            return;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = s;
        size++;
    }

    public Student dequeue() {
        if (size == 0) {
            System.out.println("Queue Empty!");
            return null;
        }
        Student removed = queue[front];
        front = (front + 1) % capacity;
        size--;
        return removed;
    }

    public Student peek() {
        if (size == 0) return null;
        return queue[front];
    }

    public void displayQueue() {
        if (size == 0) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Front -> ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.print(queue[index].id + (i < size - 1 ? " -> " : ""));
        }
        System.out.println();
    }
}