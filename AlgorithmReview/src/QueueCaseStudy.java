import java.util.LinkedList;
import java.util.Queue;

public class QueueCaseStudy {
    private Queue<String> patientQueue = new LinkedList<>();

    public void processQueue() {
        String[] initialPatients = {"P001", "P002", "P003", "P004", "P005"};
        for (String p : initialPatients) {
            patientQueue.add(p);
        }

        for (int i = 0; i < 2; i++) {
            if (!patientQueue.isEmpty()) {
                System.out.println("Service provided to: " + patientQueue.remove());
            }
        }

        patientQueue.add("P006");
        patientQueue.add("P007");

        System.out.println("Next patient (peek): " + patientQueue.peek()); //
        System.out.println("Patients waiting (size): " + patientQueue.size()); //[cite: 1]
        System.out.println("Queue status: " + patientQueue); //[cite: 1]
    }

    public static void main(String[] args) {
        QueueCaseStudy clinic = new QueueCaseStudy();
        clinic.processQueue();
    }
}