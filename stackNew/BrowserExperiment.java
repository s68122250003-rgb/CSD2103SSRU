import java.util.ArrayList;
import java.util.List;

public class BrowserExperiment {

    private static final int[] DATA_SIZES = {1000, 10000, 50000, 100000};
    private static final int TRIALS = 5;

    public static void main(String[] args) {
        System.out.println("=== ผลการทดลองเปรียบเทียบการล้าง Forward History ===");
        System.out.printf("%-10s | %-20s | %-20s%n", "Size (n)", "Two-Stack Avg (ns)", "ArrayList Avg (ns)");
        System.out.println("---------------------------------------------------------------");

        for (int n : DATA_SIZES) {
            long totalTwoStackTime = 0;
            long totalArrayListTime = 0;

            for (int t = 0; t < TRIALS; t++) {
                totalTwoStackTime += testTwoStack(n);
                totalArrayListTime += testArrayList(n);
            }

            long avgTwoStack = totalTwoStackTime / TRIALS;
            long avgArrayList = totalArrayListTime / TRIALS;

            System.out.printf("%-10d | %-20d | %-20d%n", n, avgTwoStack, avgArrayList);
        }
    }

    // ทดสอบ Two-Stack Algorithm
    private static long testTwoStack(int n) {
        TwoStackBrowser browser = new TwoStackBrowser();
        
        // 1. สร้างประวัติขนาด n หน้า
        for (int i = 0; i < n; i++) {
            browser.visit(new Page("P" + i, "Title " + i, "http://page" + i + ".com", "10:00"));
        }

        // 2. ย้อนกลับไปยังตำแหน่งกึ่งกลาง (n / 2 ครั้ง)
        for (int i = 0; i < n / 2; i++) {
            browser.back();
        }

        // 3. วัดเฉพาะเวลาในการเปิดหน้าใหม่ (ซึ่งรวมขั้นตอนล้าง Forward History)
        Page newPage = new Page("NEW", "New Page", "http://newpage.com", "10:05");
        
        long startTime = System.nanoTime();
        browser.visit(newPage);
        long endTime = System.nanoTime();

        return (endTime - startTime);
    }

    // ทดสอบ ArrayList Algorithm
    private static long testArrayList(int n) {
        ArrayListBrowser browser = new ArrayListBrowser();
        
        // 1. สร้างประวัติขนาด n หน้า
        for (int i = 0; i < n; i++) {
            browser.visit(new Page("P" + i, "Title " + i, "http://page" + i + ".com", "10:00"));
        }

        // 2. ย้อนกลับไปยังตำแหน่งกึ่งกลาง (n / 2 ครั้ง)
        for (int i = 0; i < n / 2; i++) {
            browser.back();
        }

        // 3. วัดเฉพาะเวลาในการเปิดหน้าใหม่ (ซึ่งรวมขั้นตอนล้าง Forward History)
        Page newPage = new Page("NEW", "New Page", "http://newpage.com", "10:05");

        long startTime = System.nanoTime();
        browser.visit(newPage);
        long endTime = System.nanoTime();

        return (endTime - startTime);
    }
}