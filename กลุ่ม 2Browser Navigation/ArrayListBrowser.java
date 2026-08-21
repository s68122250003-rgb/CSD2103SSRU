import java.util.ArrayList;
import java.util.List;

public class ArrayListBrowser {
    private List<Page> historyList = new ArrayList<>();
    private int currentIndex = -1;

    public void visit(Page page) {
        if (page == null) {
            System.out.println("ข้อมูลหน้าเว็บไม่ถูกต้อง");
            return;
        }

        // ล้าง Forward History โดยใช้ subList
        if (currentIndex < historyList.size() - 1) {
            historyList.subList(currentIndex + 1, historyList.size()).clear();
        }

        historyList.add(page);
        currentIndex = historyList.size() - 1;
    }

    public void back() {
        if (currentIndex <= 0) {
            System.out.println("ไม่มีหน้าก่อนหน้า");
            return;
        }
        currentIndex--;
    }

    public void forward() {
        if (currentIndex >= historyList.size() - 1) {
            System.out.println("ไม่มีหน้าถัดไป");
            return;
        }
        currentIndex++;
    }

    public void current() {
        if (currentIndex == -1) {
            System.out.println("ยังไม่มีหน้าเว็บ");
            return;
        }
        System.out.println("หน้าปัจจุบัน: " + historyList.get(currentIndex));
    }

    public void displayHistory() {
        System.out.println("History: " + historyList);
        System.out.println("Current Index: " + currentIndex);
    }
}