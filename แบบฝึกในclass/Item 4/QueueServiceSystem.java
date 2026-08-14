public class QueueServiceSystem {
    public static void main(String[] args) {
        Student s1 = new Student("65001", "Password Reset");
        Student s2 = new Student("65002", "WiFi Problem");
        Student s3 = new Student("65003", "Software Installation");
        Student s4 = new Student("65004", "Account Locked");
        Student s5 = new Student("65005", "Printer Problem");

        System.out.println("=== Testing Linked List-based Queue ===");
        LinkedListQueue q = new LinkedListQueue();

        System.out.println("Step 1: 65001 เข้าคิว");
        q.enqueue(s1);
        q.displayQueue();

        System.out.println("\nStep 2: 65002 เข้าคิว");
        q.enqueue(s2);
        q.displayQueue();

        System.out.println("\nStep 3: 65003 เข้าคิว");
        q.enqueue(s3);
        q.displayQueue();

        System.out.println("\nStep 4: ให้บริการ 1 คน");
        Student served1 = q.dequeue();
        System.out.println("Serve: " + served1.id);
        q.displayQueue();

        System.out.println("\nStep 5: 65004 เข้าคิว");
        q.enqueue(s4);
        q.displayQueue();

        System.out.println("\nStep 6: ให้บริการ 1 คน");
        Student served2 = q.dequeue();
        System.out.println("Serve: " + served2.id);
        q.displayQueue();

        System.out.println("\nStep 7: 65005 เข้าคิว");
        q.enqueue(s5);
        q.displayQueue();
    }
}