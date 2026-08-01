# แบบฝึกหัดการออกแบบอัลกอริทึมแบบเวียนเกิดและการวิเคราะห์ Big-O
## ข้อ 3: การเปรียบเทียบจำนวนสระและพยัญชนะ (Vowels vs Consonants Comparison)

เอกสารฉบับนี้จัดทำขึ้นเพื่อนำเสนอคำตอบและการวิเคราะห์เชิงลึกสำหรับ **ข้อ 3 การเปรียบเทียบจำนวนสระและพยัญชนะ** ตามข้อกำหนดของแบบฝึกหัดวิชาการออกแบบและวิเคราะห์อัลกอริทึม

---

## 1. คำอธิบายแนวคิดของอัลกอริทึม (Conceptual Explanation)

### 1.1 เงื่อนไขและกฎการคัดกรองตัวอักษร (Character Processing Rules)
* **สระ (Vowels)**: ตัวอักษร `a, e, i, o, u` (ไม่แยกตัวพิมพ์เล็กและตัวพิมพ์ใหญ่)
* **พยัญชนะ (Consonants)**: ตัวอักษรภาษาอังกฤษ (`isLetter`) ที่ไม่ใช่สระ
* **ตัวอักษรที่ถูกละเว้น**: ตัวเลข, ช่องว่าง, และเครื่องหมายพิเศษทุกชนิด จะไม่ถูกนับรวมในการคำนวณ

### 1.2 อัลกอริทึมที่ 1: Recursive Counting (`hasMoreVowelsRecursive`)
* **แนวคิดการทำงาน**: ใช้หลักการคำนวณผลต่างสะสมแบบเวียนเกิด (Recursive Net Difference) โดยตรวจสอบตัวอักษรทีละตัวผ่านดัชนีชี้ตำแหน่ง (`index`)
  * หากเป็นสระ ให้ค่า $+1$
  * หากเป็นพยัญชนะ ให้ค่า $-1$
  * หากเป็นตัวเลข/ช่องว่าง/เครื่องหมายพิเศษ ให้ค่า $0$
* **Base Case (กรณีฐาน)**: เมื่อดัชนีชี้ตำแหน่งขยับจนสุดความยาวของสตริง (`index >= s.length()`) ให้ คืนค่าเป็น $0$
* **Recursive Case (กรณีเวียนเกิด)**: คืนค่าผลรวมของค่าตัวอักษร ณ ตำแหน่งปัจจุบัน บวกกับผลลัพธ์จากการเรียกเมธอดเวียนเกิดกับตำแหน่งถัดไป (`index + 1`)
* **การสรุปผล**: หากผลรวมสุทธิจากการเรียกเวียนเกิดมากกว่า $0$ แสดงว่าสระมากกว่าพยัญชนะ (`true`)

### 1.3 อัลกอริทึมที่ 2: Iterative Counting (`hasMoreVowelsIterative`)
* **แนวคิดการทำงาน**: อ่านข้อความทีละตัวอักษรผ่านการวนลูป (`for loop`) ตั้งแต่ตำแหน่งแรกจนถึงตำแหน่งสุดท้าย โดยสร้างตัวแปรนับจำนวน 2 ตัว ได้แก่ `vowels` และ `consonants`
* **กระบวนการ**:
  * หากพบสระ ให้เพิ่มค่า `vowels++`
  * หากพบพยัญชนะ ให้เพิ่มค่า `consonants++`
  * ตัวอักษรอื่นๆ จะถูกละเว้น
* **การสรุปผล**: คืนค่าผลการเปรียบเทียบ `vowels > consonants`

---

## 2. Pseudocode (ผังขั้นตอนการทำงาน)

### 2.1 Pseudocode สำหรับ Helper Functions
```text
Function isVowel(ch):
    ch = toLowerCase(ch)
    Return ch == 'a' OR ch == 'e' OR ch == 'i' OR ch == 'o' OR ch == 'u'

Function isConsonant(ch):
    ch = toLowerCase(ch)
    Return isLetter(ch) AND NOT isVowel(ch)
```

### 2.2 Pseudocode สำหรับ Algorithm 1: Recursive Counting
```text
Algorithm hasMoreVowelsRecursive(s):
    Input: String s
    Output: Boolean (true if vowels > consonants, false otherwise)

    If s is null Then Return false
    Return countDiffRecursive(s, 0) > 0

Algorithm countDiffRecursive(s, index):
    // Base Case: Reached the end of string
    If index >= length(s) Then
        Return 0
    End If

    ch = charAt(s, index)
    val = 0
    If isVowel(ch) Then
        val = 1
    Else If isConsonant(ch) Then
        val = -1
    End If

    // Recursive Case: Accumulate with next index
    Return val + countDiffRecursive(s, index + 1)
```

### 2.3 Pseudocode สำหรับ Algorithm 2: Iterative Counting
```text
Algorithm hasMoreVowelsIterative(s):
    Input: String s
    Output: Boolean

    If s is null Then Return false

    vowels = 0
    consonants = 0

    For i from 0 to length(s) - 1 Do
        ch = charAt(s, i)
        If isVowel(ch) Then
            vowels = vowels + 1
        Else If isConsonant(ch) Then
            consonants = consonants + 1
        End If
    End For

    Return vowels > consonants
```

---

## 3. โปรแกรมภาษา Java (Java Source Code)

ซอร์สโค้ดสมบูรณ์ในไฟล์ `VowelConsonantCounter.java`:

```java
/**
 * คลาสสำหรับทดสอบและวิเคราะห์ประสิทธิภาพการเปรียบเทียบจำนวนสระและพยัญชนะ
 * แบบฝึกหัดข้อที่ 3: Vowels vs Consonants (Recursive vs Iterative)
 */
public class VowelConsonantCounter {

    /**
     * Helper Method: ตรวจสอบว่าเป็นสระหรือไม่ (a, e, i, o, u)
     */
    private static boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    /**
     * Helper Method: ตรวจสอบว่าเป็นพยัญชนะภาษาอังกฤษหรือไม่
     */
    private static boolean isConsonant(char ch) {
        ch = Character.toLowerCase(ch);
        return Character.isLetter(ch) && !isVowel(ch);
    }

    /**
     * อัลกอริทึมที่ 1: Recursive Counting
     * @param s สตริงภาษาอังกฤษ
     * @return true หากสระมากกว่าพยัญชนะ, false หากน้อยกว่าหรือเท่ากัน
     */
    public static boolean hasMoreVowelsRecursive(String s) {
        if (s == null) {
            return false;
        }
        return countDiffRecursive(s, 0) > 0;
    }

    /**
     * Helper Recursive Method คำนวณผลต่างสะสมระหว่างสระและพยัญชนะ
     */
    private static int countDiffRecursive(String s, int index) {
        // Base Case: วิ่งจนสุดความยาวของสตริง
        if (index >= s.length()) {
            return 0;
        }

        char ch = s.charAt(index);
        int currentVal = 0;
        if (isVowel(ch)) {
            currentVal = 1;   // สระได้ +1
        } else if (isConsonant(ch)) {
            currentVal = -1;  // พยัญชนะได้ -1
        }

        // Recursive Case: คืนค่าผลรวมปัจจุบัน + ตำแหน่งถัดไป
        return currentVal + countDiffRecursive(s, index + 1);
    }

    /**
     * อัลกอริทึมที่ 2: Iterative Counting
     * @param s สตริงภาษาอังกฤษ
     * @return true หากสระมากกว่าพยัญชนะ, false หากน้อยกว่าหรือเท่ากัน
     */
    public static boolean hasMoreVowelsIterative(String s) {
        if (s == null) {
            return false;
        }

        int vowels = 0;
        int consonants = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (isVowel(ch)) {
                vowels++;
            } else if (isConsonant(ch)) {
                consonants++;
            }
        }

        return vowels > consonants;
    }

    public static void main(String[] args) {
        // ชุดข้อมูลทดสอบ
        String[] testInputs = {
            "education",              // Vowels: 5, Consonants: 4 -> true
            "Hello World!",           // Vowels: 3, Consonants: 7 -> false
            "AEIOU 12345",            // Vowels: 5, Consonants: 0 -> true
            "bcdfg 99!!",             // Vowels: 0, Consonants: 5 -> false
            "a e i o u b c d f g",    // Vowels: 5, Consonants: 5 -> false (เท่ากัน)
            "",                       // Empty string -> false
            null                      // Null -> false
        };

        System.out.println("=== 1. Sample and Edge Cases Verification ===");
        System.out.printf("%-25s | %-12s | %-12s
", "Input String", "Recursive", "Iterative");
        System.out.println("---------------------------------------------------------");

        for (String input : testInputs) {
            boolean res1 = hasMoreVowelsRecursive(input);
            boolean res2 = hasMoreVowelsIterative(input);

            String display = (input == null) ? "null" : """ + input + """;
            System.out.printf("%-25s | %-12b | %-12b
", display, res1, res2);
        }
    }
}
```

---

## 4. ตัวอย่างข้อมูลนำเข้าและผลลัพธ์ (Sample Input & Output)

| ลำดับ | Input String | Vowels | Consonants | Output (`Recursive`) | Output (`Iterative`) | หมายเหตุ / สถานะ |
| :---: | :--- | :---: | :---: | :---: | :---: | :--- |
| 1 | `"education"` | 5 | 4 | `true` | `true` | สระมากกว่า (5 > 4) |
| 2 | `"Hello World!"` | 3 | 7 | `false` | `false` | พยัญชนะมากกว่า (3 < 7) |
| 3 | `"AEIOU 12345"` | 5 | 0 | `true` | `true` | ละเว้นตัวเลขและช่องว่าง |
| 4 | `"bcdfg 99!!"` | 0 | 5 | `false` | `false` | ละเว้นเครื่องหมายพิเศษ |
| 5 | `"a e i o u b c d f g"` | 5 | 5 | `false` | `false` | จำนวนเท่ากัน (5 > 5 เป็น false) |
| 6 | `""` | 0 | 0 | `false` | `false` | ข้อมูลว่าง ไม่พบสระ |
| 7 | `null` | 0 | 0 | `false` | `false` | กรณีข้อมูลเป็น Null |

---

## 5. การวิเคราะห์เชิงลึก (In-Depth Algorithm Analysis)

กำหนดให้ $n$ คือความยาวของสตริง $s$

### 5.1 การวิเคราะห์ Time Complexity
* **Recursive Counting**: **$O(n)$**
  * เกิดการเรียกเมธอดเวียนเกิด $n + 1$ ครั้ง แต่ละครั้งเข้าถึงตัวอักษรด้วย `s.charAt(index)` ซึ่งใช้เวลา $O(1)$
* **Iterative Counting**: **$O(n)$**
  * วนลูปอ่านตัวอักษร $n$ รอบ แต่ละรอบตรวจสอบเงื่อนไข Helper Method ซึ่งใช้เวลา $O(1)$

### 5.2 การวิเคราะห์ Space Complexity
* **Recursive Counting**: **$O(n)$**
  * เกิด Call Stack ความลึก $n + 1$ ชั้น เพื่อเก็บ Local Variable และ Parameter ในกระบวนการเวียนเกิด
* **Iterative Counting**: **$O(1)$**
  * ใช้เฉพาะตัวแปรนับค่าพื้นฐาน (`vowels`, `consonants`, `i`) ซึ่งใช้หน่วยความจำคงที่ ไม่ขึ้นกับขนาดของข้อความ $n$

### 5.3 จำนวน Recursive Calls
* หากสตริงมีความยาว $n$ ตัวอักษร อัลกอริทึมจะสร้าง **Recursive Calls ทั้งหมด $n + 1$ ครั้ง** ($n$ ครั้งสำหรับอักขระแต่ละตัว และ 1 ครั้งสุดท้ายเมื่อตก Base Case `index == n`)

### 5.4 ความเสี่ยงของ StackOverflowError
* **Recursive Counting มีความเสี่ยงสูงมากต่อการเกิด `StackOverflowError`** เมื่อใช้กับข้อความขนาดยาว (เช่น $n \ge 10,000$) เนื่องจาก JVM มีการจำกัดขีดจำกัดหน่วยความจำชั่วคราวสำหรับ Call Stack
* **Iterative Counting ไม่มีความเสี่ยงเรื่อง Stack Overflow** เนื่องจากทำงานอยู่บน Stack Frame เดียวตลอดกระบวนการวนลูป

### 5.5 ขนาดข้อมูลที่เหมาะสมสำหรับแต่ละวิธี (Suitable Data Size)
* **Recursive Counting**: เหมาะสมสำหรับข้อความ **ขนาดเล็กถึงปานกลาง ($n < 1,000$)**
* **Iterative Counting**: เหมาะสมสำหรับข้อความ **ทุกขนาด (ตั้งแต่ $n = 1$ ไปจนถึง $n \ge 100,000+$)**

---

## 6. เปรียบเทียบข้อดี ข้อจำกัด และข้อสรุปความเหมาะสม

| มิติการเปรียบเทียบ | Recursive Counting (`hasMoreVowelsRecursive`) | Iterative Counting (`hasMoreVowelsIterative`) |
| :--- | :--- | :--- |
| **Time Complexity** | $O(n)$ | $O(n)$ |
| **Space Complexity** | **$O(n)$** ( Call Stack) | **$O(1)$** (ประหยัดหน่วยความจำสูงสุด) |
| **ความเสี่ยง Stack Overflow** | **สูง** (หาก $n \ge 10,000$) | **ไม่มีความเสี่ยง** |
| **การอ่านและเข้าใจโค้ด** | สั้นกระชับ ซ่อนการสะสมค่าไว้ใน Call Stack | ตรงไปตรงมา โครงสร้างอ่านเข้าใจง่าย |

### สรุปความเหมาะสมภายใต้เงื่อนไขต่างๆ
1. **สภาวะที่ควรเลือกใช้ `Iterative Counting`**:
   * เหมาะสมที่สุดสำหรับการพัฒนา **ระบบงานจริง (Production Systems)** ทุกกรณี
   * เหมาะสำหรับข้อความทุกขนาด โดยเฉพาะข้อความยาวๆ หรือข้อมูลที่ส่งมาจากภายนอก
   * ให้ประสิทธิภาพสูงสุด ปลอดภัยจาก Stack Overflow และกินหน่วยความจำคงที่ $O(1)$
2. **สภาวะที่ควรเลือกใช้ `Recursive Counting`**:
   * เหมาะสำหรับการศึกษาและฝึกฝนแนวคิดเรื่อง Recursive Accumulation / Reduction
   * เหมาะสำหรับข้อความขนาดเล็กสั้นๆ ที่เน้นความกระชับของรูปแบบโปรแกรม
