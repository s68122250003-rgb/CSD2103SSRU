import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TwoStackBrowser browser = new TwoStackBrowser();
        int pageCounter = 1;

        while (true) {
            System.out.println("\n=== Two-Stack Browser Menu ===");
            System.out.println("[1] Visit Website (พิมพ์ URL)");
            System.out.println("[2] Back");
            System.out.println("[3] Forward");
            System.out.println("[4] Exit");
            System.out.print("เลือกเมนู: ");
            
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                System.out.print("กรอก URL เว็บไซต์ (เช่น google.com): ");
                String url = scanner.nextLine().trim();
                if (!url.startsWith("http")) {
                    url = "https://" + url;
                }
                String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                Page page = new Page("P" + pageCounter++, url, url, time);
                
                browser.visit(page);
            } else if (choice.equals("2")) {
                browser.back();
            } else if (choice.equals("3")) {
                browser.forward();
            } else if (choice.equals("4")) {
                System.out.println("จบการทำงาน");
                break;
            } else {
                System.out.println("คำสั่งไม่ถูกต้อง กรุณาลองใหม่");
                continue;
            }

            System.out.println("\n[ผลลัพธ์การทำงาน]");
            browser.current();
            browser.displayHistory();
        }
        scanner.close();
    }
}