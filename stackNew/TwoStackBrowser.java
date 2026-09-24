import java.util.Stack;

public class TwoStackBrowser {
    private Stack<Page> backStack = new Stack<>();
    private Stack<Page> forwardStack = new Stack<>();

    public void visit(Page page) {
        if (page != null) {
            backStack.push(page);
            forwardStack.clear(); // ล้างประวัติ Forward เมื่อมีการเข้าเว็บใหม่
        }
    }

    public void back() {
        if (backStack.size() <= 1) {
            System.out.println("❌ ไม่สามารถ Back ได้ (ไม่มีหน้าก่อนหน้า)");
            return;
        }
        forwardStack.push(backStack.pop());
    }

    public void forward() {
        if (forwardStack.isEmpty()) {
            System.out.println("❌ ไม่สามารถ Forward ได้ (ไม่มีหน้าถัดไป)");
            return;
        }
        backStack.push(forwardStack.pop());
    }

    public void current() {
        if (backStack.isEmpty()) {
            System.out.println("📌 หน้าปัจจุบัน: ไม่มี (ยังไม่ได้เปิดเว็บ)");
        } else {
            System.out.println("📌 หน้าปัจจุบัน: " + backStack.peek());
        }
    }

    public void displayHistory() {
        System.out.println("--- สถานะ Stack ---");
        System.out.println("Back Stack    : " + backStack);
        System.out.println("Forward Stack : " + forwardStack);
        System.out.println("--------------------");
    }
}