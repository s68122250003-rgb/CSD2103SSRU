import java.util.ArrayDeque;
import java.util.Deque;

public class TwoStackBrowser {

    // ใช้ Deque / ArrayDeque ตามข้อแนะนำในเอกสารกลุ่ม 2
    private Deque<Page> backStack = new ArrayDeque<>();
    private Deque<Page> forwardStack = new ArrayDeque<>();
    private Page currentPage;

    // VISIT
    public void visit(Page page) {
        if (page == null) {
            System.out.println("ข้อมูลหน้าเว็บไม่ถูกต้อง");
            return;
        }

        if (currentPage != null) {
            backStack.push(currentPage);
        }

        currentPage = page;

        // เปิดหน้าใหม่ ต้องล้าง Forward History
        forwardStack.clear();
    }

    // BACK
    public void back() {
        if (backStack.isEmpty()) {
            System.out.println("ไม่มีหน้าก่อนหน้า");
            return;
        }

        forwardStack.push(currentPage);
        currentPage = backStack.pop();
    }

    // FORWARD
    public void forward() {
        if (forwardStack.isEmpty()) {
            System.out.println("ไม่มีหน้าถัดไป");
            return;
        }

        backStack.push(currentPage);
        currentPage = forwardStack.pop();
    }

    // CURRENT
    public void current() {
        if (currentPage == null) {
            System.out.println("ยังไม่มีหน้าเว็บ");
            return;
        }
        System.out.println("หน้าปัจจุบัน: " + currentPage);
    }

    // DISPLAY HISTORY (แสดงครบทั้ง Current, Back, Forward)
    public void displayHistory() {
        System.out.println("Current Page: " + (currentPage != null ? currentPage : "ไม่มี"));
        System.out.println("Back Stack  : " + backStack);
        System.out.println("Forward Stack: " + forwardStack);
    }
}