import java.util.Stack;

public class StackApplications {

    // ==========================================
    // ส่วนที่ 1: ตรวจสอบวงเล็บ (Parentheses Matching)
    // ==========================================
    public static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            // ถ้าเจอเครื่องหมายเปิด ให้ push เข้า Stack
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            // ถ้าเจอเครื่องหมายปิด ให้ตรวจสอบและ pop ออกจาก Stack
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false; // เจอวงเล็บปิดแต่ไม่มีวงเล็บเปิดใน Stack
                }

                char top = stack.pop();
                if (!isMatchingPair(top, ch)) {
                    return false; // เครื่องหมายเปิด-ปิด ไม่สอดคล้องกัน
                }
            }
        }

        // ต้องไม่มีวงเล็บเปิดค้างอยู่ใน Stack
        return stack.isEmpty();
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }

    // ==========================================
    // ส่วนที่ 2: ระบบ Undo Operation
    // ==========================================
    public static void runUndoSimulation(String[] commands) {
        Stack<String> historyStack = new Stack<>();

        for (String cmd : commands) {
            System.out.println(cmd);

            if (cmd.startsWith("ADD ")) {
                String item = cmd.substring(4);
                historyStack.push(item);
                displayStack(historyStack);
            } else if (cmd.equals("UNDO")) {
                if (!historyStack.isEmpty()) {
                    String removedItem = historyStack.pop();
                    System.out.println("Remove " + removedItem);
                } else {
                    System.out.println("Nothing to UNDO");
                }
            }
        }
    }

    private static void displayStack(Stack<String> stack) {
        System.out.print("Stack: ");
        for (String item : stack) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // ==========================================
    // Main Method ทดสอบการทำงาน
    // ==========================================
    public static void main(String[] args) {
        System.out.println("=== 1. Checking Parentheses ===");
        String test1 = "{A + [B * (C + D)]}";
        String test2 = "{A + [B * (C + D)]"; // วงเล็บไม่ครบ

        System.out.println("Input:  " + test1);
        System.out.println("Output: " + (isBalanced(test1) ? "Balanced" : "Not Balanced"));

        System.out.println("\nInput:  " + test2);
        System.out.println("Output: " + (isBalanced(test2) ? "Balanced" : "Not Balanced"));

        System.out.println("\n=== 2. Undo Operations Simulation ===");
        String[] commands = {
                "ADD A",
                "ADD B",
                "ADD C",
                "UNDO",
                "ADD D",
                "UNDO"
        };
        runUndoSimulation(commands);
    }
}