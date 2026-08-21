public class MainA {

    public static void main(String[] args) {

        TwoStackBrowser browser = new TwoStackBrowser();

        // ต้องสร้างออบเจกต์ Page แทนการใส่ String เพียวๆ
        Page page1 = new Page("P1", "Google", "https://google.com", "10:00");
        Page page2 = new Page("P2", "YouTube", "https://youtube.com", "10:01");
        Page page3 = new Page("P3", "Facebook", "https://facebook.com", "10:02");
        Page page4 = new Page("P4", "GitHub", "https://github.com", "10:03");

        System.out.println("=== VISIT A B C ===");
        browser.visit(page1);
        browser.visit(page2);
        browser.visit(page3);
        browser.current();
        browser.displayHistory();

        System.out.println("\n=== BACK ===");
        browser.back();
        browser.current();
        browser.displayHistory();

        System.out.println("\n=== BACK ===");
        browser.back();
        browser.current();
        browser.displayHistory();

        System.out.println("\n=== FORWARD ===");
        browser.forward();
        browser.current();
        browser.displayHistory();

        System.out.println("\n=== VISIT GitHub ===");
        browser.visit(page4);
        browser.current();
        browser.displayHistory();
    }
}