import java.util.Stack;

public class StackCaseStudy {
    private Stack<String> history = new Stack<>();

    public void processUndo() {
        String[] actions = {"Type Data", "Type Structure", "Delete Structure", "Type Algorithm", "Type Java"};
        for (String action : actions) history.push(action);

        System.out.println("Current Stack: " + history);

        for (int i = 0; i < 2; i++) {
            if (!history.isEmpty()) {
                System.out.println("Undo: " + history.pop());
            }
        }
        System.out.println("Stack after Undo: " + history);
    }
    public static void main(String[] args) {
        StackCaseStudy a1 = new StackCaseStudy();
        a1.processUndo();
    }
}