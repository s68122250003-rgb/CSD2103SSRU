# แบบฝึกหัดการออกแบบอัลกอริทึมแบบเวียนเกิดและการวิเคราะห์ Big-O
## ข้อ 2: การตรวจสอบ Palindrome (Palindrome Verification)

เอกสารฉบับนี้จัดทำขึ้นเพื่อนำเสนอคำตอบและการวิเคราะห์เชิงลึกสำหรับ **ข้อ 2 การตรวจสอบ Palindrome** ตามข้อกำหนดของแบบฝึกหัดวิชาการออกแบบและวิเคราะห์อัลกอริทึม

---

## 1. คำอธิบายแนวคิดของอัลกอริทึม (Conceptual Explanation)

Palindrome คือสตริงที่เมื่ออ่านจากซ้ายไปขวาและจากขวาไปซ้ายแล้วได้ข้อความเดียวกัน การตรวจสอบจำเป็นต้องมีขั้นตอนการเตรียมข้อมูล (Preprocessing) ร่วมกับอัลกอริทึมหลักดังนี้

### 1.1 ขั้นตอนการเตรียมข้อมูล (String Preprocessing)
* **เงื่อนไขเพิ่มเติม**: ต้องสามารถละเว้น ตัวพิมพ์เล็ก-ใหญ่, ช่องว่าง, และเครื่องหมายวรรคตอน
* **แนวคิด**: วนลูปอ่านตัวอักษรทีละตัว กรองเก็บเฉพาะตัวอักษรและตัวเลข (`Character.isLetterOrDigit`) แล้วแปลงให้เป็นตัวพิมพ์เล็กทั้งหมด (`Character.toLowerCase`) ด้วย `StringBuilder` ก่อนส่งเข้ากระบวนการตรวจสอบ

### 1.2 อัลกอริทึมที่ 1: Reverse and Compare (`isPalindromeByReverse`)
* **แนวคิดการทำงาน**: สร้างสตริงย้อนกลับ (Reversed String) จากสตริงที่ผ่านการทำความสะอาดแล้ว จากนั้นนำสตริงย้อนกลับมาเปรียบเทียบกับสตริงเดิมด้วยเมธอด `.equals()`
* **กระบวนการ**: หากสตริงเดิมและสตริงย้อนกลับเหมือนกันทุกตัวอักษร จะคืนค่า `true` หากต่างกันแม้เพียงตำแหน่งเดียวจะคืนค่า `false`

### 1.3 อัลกอริทึมที่ 2: Recursive Two-Pointer (`isPalindromeRecursive`)
* **แนวคิดการทำงาน**: ใช้ตัวชี้สองตำแหน่ง ได้แก่ ตำแหน่งซ้ายสุด (`left`) และตำแหน่งขวาสุด (`right`) แล้วเปรียบเทียบตัวอักษร ณ ตำแหน่งทั้งสองแบบเวียนเกิด
* **Base Case (กรณีฐาน)**: `left >= right` (ตัวชี้ซ้ายชนหรือสวนทางกับตัวชี้ขวาที่จุดศูนย์กลาง) หมายถึง ได้ทำการเปรียบเทียบตัวอักษรครบทุกคู่แล้ว และตรงกันทั้งหมด จึงคืนค่า `true`
* **Early Exit Case (กรณีเลิกทำงานก่อนกำหนด)**: `s.charAt(left) != s.charAt(right)` หากพบว่าตัวอักษร ณ ตำแหน่งซ้ายและขวาไม่ตรงกัน ให้หยุดทำงานและคืนค่า `false` ทันที
* **Recursive Case (กรณีเวียนเกิด)**: `isPalindromeRecursive(s, left + 1, right - 1)` ขยับตัวชี้ซ้ายไปทางขวา 1 ตำแหน่ง และขยับตัวชี้ขวาไปทางซ้าย 1 ตำแหน่ง แล้วเรียกตัวเองเวียนเกิด

---

## 2. Pseudocode (ผังขั้นตอนการทำงาน)

### 2.1 Pseudocode สำหรับ Preprocessing
```text
Algorithm preprocess(s):
    Input: String s
    Output: Cleaned lowercase alphanumeric String

    If s is null Then Return null

    sb = new StringBuilder
    For i from 0 to length(s) - 1 Do
        ch = charAt(s, i)
        If isLetterOrDigit(ch) Then
            Append toLowerCase(ch) to sb
        End If
    End For

    Return sb.toString()
```

### 2.2 Pseudocode สำหรับ Algorithm 1: Reverse and Compare
```text
Algorithm isPalindromeByReverse(s):
    Input: String s
    Output: Boolean (true if Palindrome, false otherwise)

    If s is null Then Return false
    cleanStr = preprocess(s)
    
    // Reverse the cleaned string
    reversedStr = reverse(cleanStr)
    
    Return cleanStr equals reversedStr
```

### 2.3 Pseudocode สำหรับ Algorithm 2: Recursive Two-Pointer
```text
Algorithm isPalindromeRecursive(s, left, right):
    Input: Cleaned String s, Integer left pointer, Integer right pointer
    Output: Boolean

    // Base Case 1: All pairs checked successfully
    If left >= right Then
        Return true
    End If

    // Early Exit Case: Mismatch found
    If charAt(s, left) != charAt(s, right) Then
        Return false
    End If

    // Recursive Case: Move inward
    Return isPalindromeRecursive(s, left + 1, right - 1)
```

---

## 3. โปรแกรมภาษา Java (Java Source Code)

ซอร์สโค้ดสมบูรณ์ในไฟล์ `PalindromeChecker.java`:

```java
/**
 * คลาสสำหรับทดสอบและวิเคราะห์ประสิทธิภาพการตรวจสอบ Palindrome
 * แบบฝึกหัดข้อที่ 2: Palindrome Verification (Reverse vs Recursive Two-Pointer)
 */
public class PalindromeChecker {

    /**
     * เมธอดเตรียมข้อมูล: กรองเอาเฉพาะตัวอักษร/ตัวเลข และแปลงเป็นตัวพิมพ์เล็ก
     * @param s สตริงนำเข้า
     * @return สตริงที่ทำความสะอาดแล้ว
     */
    public static String preprocess(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sb.append(Character.toLowerCase(ch));
            }
        }
        return sb.toString();
    }

    /**
     * อัลกอริทึมที่ 1: Reverse and Compare
     * @param s สตริงนำเข้า
     * @return true หากเป็น Palindrome, false หากไม่เป็น
     */
    public static boolean isPalindromeByReverse(String s) {
        if (s == null) {
            return false;
        }
        String cleanStr = preprocess(s);
        String reversedStr = new StringBuilder(cleanStr).reverse().toString();
        return cleanStr.equals(reversedStr);
    }

    /**
     * อัลกอริทึมที่ 2: Recursive Two-Pointer
     * @param s สตริงที่ผ่านการ preprocess แล้ว
     * @param left ดัชนีตัวชี้ฝั่งซ้าย
     * @param right ดัชนีตัวชี้ฝั่งขวา
     * @return true หากเป็น Palindrome, false หากไม่เป็น
     */
    public static boolean isPalindromeRecursive(String s, int left, int right) {
        // Base Case: ตัวชี้ชนกันหรือสวนทางกัน (ตรวจสอบครบทุกตัวอักษรแล้ว)
        if (left >= right) {
            return true;
        }

        // Early Exit: หยุดทันทีเมื่อพบตัวอักษรคู่ที่ไม่ตรงกัน
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }

        // Recursive Case: ขยับตัวชี้เข้าหากัน
        return isPalindromeRecursive(s, left + 1, right - 1);
    }

    /**
     * Helper Method สำหรับเรียกใช้วิธี Recursive Two-Pointer ได้สะดวกขึ้น
     */
    public static boolean isPalindromeRecursiveWrapper(String s) {
        if (s == null) {
            return false;
        }
        String cleanStr = preprocess(s);
        if (cleanStr.isEmpty()) {
            return true;
        }
        return isPalindromeRecursive(cleanStr, 0, cleanStr.length() - 1);
    }

    public static void main(String[] args) {
        // ชุดข้อมูลทดสอบตามโจทย์กำหนดและ Edge Cases
        String[] testInputs = {
            "racecar",
            "level",
            "algorithm",
            "gohangasalamiimalasagnahog",
            "A man, a plan, a canal: Panama",
            "No 'x' in Nixon",
            "A",
            "",
            null
        };

        System.out.println("=== 1. Sample and Edge Cases Verification ===");
        System.out.printf("%-35s | %-12s | %-12s
", "Input String", "By Reverse", "Recursive");
        System.out.println("-------------------------------------------------------------------");

        for (String input : testInputs) {
            boolean res1 = isPalindromeByReverse(input);
            boolean res2 = isPalindromeRecursiveWrapper(input);
            
            String displayInput = (input == null) ? "null" : """ + input + """;
            System.out.printf("%-35s | %-12b | %-12b
", displayInput, res1, res2);
        }
    }
}
```

---

## 4. ตัวอย่างข้อมูลนำเข้าและผลลัพธ์ (Sample Input & Output)

| ลำดับ | Input String | Preprocessed String | Output (`ByReverse`) | Output (`Recursive`) | สถานะ |
| :---: | :--- | :--- | :---: | :---: | :---: |
| 1 | `"racecar"` | `"racecar"` | `true` | `true` | ผ่าน |
| 2 | `"level"` | `"level"` | `true` | `true` | ผ่าน |
| 3 | `"algorithm"` | `"algorithm"` | `false` | `false` | ผ่าน |
| 4 | `"gohangasalamiimalasagnahog"` | `"gohangasalamiimalasagnahog"` | `true` | `true` | ผ่าน |
| 5 | `"A man, a plan, a canal: Panama"` | `"amanaplanacanalpanama"` | `true` | `true` | ผ่าน |
| 6 | `"No 'x' in Nixon"` | `"noxinnixon"` | `true` | `true` | ผ่าน |
| 7 | `"A"` | `"a"` | `true` | `true` | ผ่าน |
| 8 | `""` | `""` | `true` | `true` | ผ่าน |
| 9 | `null` | `null` | `false` | `false` | ผ่าน |

---

## 5. การวิเคราะห์เชิงลึก (In-Depth Algorithm Analysis)

กำหนดให้ $n$ คือความยาวของสตริงหลังผ่านการ Preprocess

### 5.1 การวิเคราะห์ตามกรณีของสตริง (String Case Scenarios)
1. **กรณีที่สตริงเป็น Palindrome**:
   * **Reverse and Compare**: ทำ Preprocess ($n$ ครั้ง), กลับลำดับสตริง ($n$ ครั้ง), และเปรียบเทียบ ($n$ ครั้ง)
   * **Recursive Two-Pointer**: ทำ Preprocess ($n$ ครั้ง) และทำการเปรียบเทียบเวียนเกิด $n/2$ คู่ จนตัวชี้มาชนกันที่ศูนย์กลาง
2. **กรณีที่ตัวอักษรคู่แรกไม่ตรงกัน (First Character Pair Mismatch)**:
   * **Reverse and Compare**: จะต้องกลับลำดับสตริงให้เสร็จสิ้นครบ $n$ ตัวก่อน แล้วจึงเริ่มเปรียบเทียบและพบความต่างที่ตำแหน่งแรก
   * **Recursive Two-Pointer**: สามารถคืนค่า `false` ได้ตั้งแต่การเปรียบเทียบครั้งแรกในการเรียกเวียนเกิดชั้นแรกทันที (Early Exit)

### 5.2 Best-Case Time Complexity Analysis
* **Reverse and Compare**: $O(n)$
  * แม้ว่าตัวอักษรคู่แรกจะไม่ตรงกันแต่อัลกอริทึมต้องเสียเวลาในการทำ Preprocess และสร้างสตริงย้อนกลับขนาด $n$ ให้เสร็จสิ้นก่อนเสมอ
* **Recursive Two-Pointer**: $O(n)$ (รวม Preprocessing) หรือ $O(1)$ (หากพิจารณาเฉพาะขั้นตอนการเปรียบเทียบ)
  * หากไม่รวมเวลา Preprocess เมื่อตัวอักษรคู่แรกไม่ตรงกัน เมธอดจะเปรียบเทียบเพียงครั้งเดียวและเลิกทำงานทันที

### 5.3 Worst-Case Time Complexity Analysis
* **Reverse and Compare**: $O(n)$
  * เกิดขึ้นเมื่อสตริงเป็น Palindrome ต้องทำ Preprocess, Reverse, และ Compare รวม $3n$ การทำงาน ซึ่งเป็น $O(n)$
* **Recursive Two-Pointer**: $O(n)$
  * เกิดขึ้นเมื่อสตริงเป็น Palindrome เกิดการเรียกเวียนเกิด $n/2$ ครั้ง ซึ่งแต่ละครั้งใช้เวลา $O(1)$ รวมเป็น $O(n)$

### 5.4 Space Complexity Analysis
* **Reverse and Compare**: $O(n)$
  * จองหน่วยความจำ Heap สำหรับสตริงที่ Preprocess แล้ว ($n$ ตัวอักษร) และสตริงย้อนกลับอีก ($n$ ตัวอักษร)
* **Recursive Two-Pointer**: $O(n)$
  * จองหน่วยความจำ Heap สำหรับสตริงที่ Preprocess แล้ว ($n$ ตัวอักษร)
  * เกิด Call Stack ความลึกสูงสุด $n/2$ ชั้นในการเวียนเกิดแต่ละชั้น ซึ่งใช้ Auxiliary Space $O(n)$

### 5.5 ความสามารถในการหยุดทำงานก่อนครบทุกตัวอักษร (Early Exit Capability)
* **Reverse and Compare**: **ไม่มีความสามารถนี้** เนื่องจากโครงสร้างการรันถูกบังคับให้กลับลำดับสตริงทั้งเส้นให้เสร็จสมบูรณ์ก่อนเปรียบเทียบ
* **Recursive Two-Pointer**: **มีความสามารถนี้เต็มรูปแบบ** ส่งผลให้ในสภาวะการใช้งานจริงกับข้อความทั่วไปที่ไม่ใช่ Palindrome อัลกอริทึมนี้จะทำงานได้เร็วกว่าวิธีแรกมากเพราะหยุดทำงานได้ทันทีตั้งแต่คู่แรกๆ ที่พบความแตกต่าง

---

## 6. เปรียบเทียบข้อดี ข้อจำกัด และข้อสรุปความเหมาะสม

| มิติการเปรียบเทียบ | Reverse and Compare (`isPalindromeByReverse`) | Recursive Two-Pointer (`isPalindromeRecursive`) |
| :--- | :--- | :--- |
| **ความเร็วกรณีทั่วไป** | ช้ากว่าเนื่องจากต้องกลับลำดับสตริงทั้งหมดก่อน | **เร็วกว่ามาก** เนื่องจากมี Early Exit ตอบ `false` ได้ทันที |
| **Best-Case Time** | $O(n)$ (บังคับทำทั้งกระบวนการ) | **$O(1)$** (เฉพาะขั้นตอนตรวจเปรียบเทียบ) |
| **Worst-Case Time** | $O(n)$ | $O(n)$ |
| **Space Complexity** | $O(n)$ ( Heap Memory 2 สตริง) | $O(n)$ ( Heap Memory + Call Stack $n/2$ ชั้น) |
| **ความเสี่ยง Stack Overflow** | **ไม่มีความเสี่ยง** | มีความเสี่ยงหากสตริงมีความยาวมากเกินไป |

### สรุปความเหมาะสมภายใต้เงื่อนไขต่างๆ
1. **สภาวะที่ควรเลือกใช้ `Recursive Two-Pointer`**:
   * เหมาะสำหรับ **กรณีใช้งานทั่วไป** ที่ข้อความส่วนใหญ่ในระบบ **ไม่ได้เป็น Palindrome** เนื่องจากระบบจะตรวจพบความต่างและเลิกทำงานได้อย่างรวดเร็ว (Early Exit)
   * เหมาะสำหรับข้อความที่มีความยาวปานกลาง (ไม่เกิน 10,000 ตัวอักษร)
2. **สภาวะที่ควรเลือกใช้ `Reverse and Compare`**:
   * เหมาะสำหรับระบบที่รับข้อความขนาดยาวมาก และต้องการป้องกันความเสี่ยงของการเกิด `StackOverflowError` จาก Call Stack
   * เหมาะสำหรับระบบที่ต้องการโค้ดที่อ่านเข้าใจง่าย ซ่อมบำรุงง่าย
