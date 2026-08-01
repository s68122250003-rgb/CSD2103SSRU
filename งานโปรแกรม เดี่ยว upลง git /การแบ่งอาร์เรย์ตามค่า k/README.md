# ข้อ 5: การแบ่งอาร์เรย์ตามค่า k (Array Partitioning by Value k)

เอกสารนี้จัดทำขึ้นเพื่ออธิบายและนำเสนอโซลูชันสำหรับการแก้ปัญหา **การแบ่งอาร์เรย์ตามค่า k** ตามข้อกำหนดในแบบฝึกหัดการออกแบบอัลกอริทึมแบบเวียนเกิดและการวิเคราะห์ Big-O

---

## 📋 1. ภาพรวมของโจทย์ (Problem Overview)

กำหนดอาร์เรย์จำนวนเต็มที่ยังไม่เรียงลำดับ $A$ และจำนวนเต็ม $k$ ให้นักศึกษาเขียนโปรแกรมจัดตำแหน่งสมาชิกใหม่ โดยมีเงื่อนไขดังนี้:
* สมาชิกที่มีค่าน้อยกว่าหรือเท่ากับ $k$ ($\le k$) อยู่ด้านหน้า
* สมาชิกที่มีค่ามากกว่า $k$ ($> k$) อยู่ด้านหลัง

> **ตัวอย่าง:**
> * **Input:** $A = [12, 4, 7, 15, 3, 10, 8]$, $k = 8$
> * **Possible Output:** $[8, 4, 7, 3, 15, 10, 12]$ *(ไม่จำเป็นต้องเรียงลำดับสมาชิกภายในแต่ละกลุ่ม)*

---

## 💡 2. คำอธิบายแนวคิดของอัลกอริทึมทั้ง 3 วิธี (Algorithm Concepts)

### อัลกอริทึมที่ 1: Recursive Partition (การแบ่งกลุ่มแบบเวียนเกิด)
* **ชื่อเมธอด:** `static void partitionRecursive(int[] a, int k, int left, int right)`
* **แนวคิด:** ใช้ตัวชี้สองตำแหน่ง คือ `left` (เริ่มที่ 0) และ `right` (เริ่มที่ $n-1$) 
  * **Base Case:** เมื่อ `left >= right` จะหยุดทำงานเนื่องจากตัวชี้เดินมาชนกันหรือสวนทางกัน
  * **Recursive Case:**
    * หาก $a[left] \le k$ ให้ขยับ `left` ไปทางขวา (`left + 1`) ด้วยการเรียกเวียนเกิด
    * หาก $a[right] > k$ ให้ขยับ `right` ไปทางซ้าย (`right - 1`) ด้วยการเรียกเวียนเกิด
    * หากพบว่า $a[left] > k$ และ $a[right] \le k$ ให้สลับค่า (Swap) สมาชิกคู่นี้ แล้วเรียกเวียนเกิดช่วงถัดไป (`left + 1`, `right - 1`)

### อัลกอริทึมที่ 2: Iterative Partition (การแบ่งกลุ่มแบบใช้ลูปวน)
* **ชื่อเมธอด:** `static void partitionIterative(int[] a, int k)`
* **แนวคิด:** ใช้หลักการ Two-Pointer เดียวกันกับวิธีแรก แต่เปลี่ยนจากการเรียกเวียนเกิดเป็นการใช้ลูปวน `while` เลื่อน `left` ไปทางขวาตราบใดที่ $a[left] \le k$ และเลื่อน `right` ไปทางซ้ายตราบใดที่ $a[right] > k$ เมื่อตัวชี้ทั้งสองหยุด ให้สลับค่า และทำซ้ำจนกว่า `left >= right`

### อัลกอริทึมที่ 3: Sorting-Based Algorithm (การจัดกลุ่มด้วยการเรียงลำดับ)
* **ชื่อเมธอด:** `static void partitionBySorting(int[] a, int k)`
* **แนวคิด:** เรียงลำดับอาร์เรย์ทั้งหมดจากน้อยไปมาก (Ascending Order) เมื่ออาร์เรย์ถูกเรียงลำดับแล้ว สมาชิกทั้งหมดที่มีค่าน้อยกว่าหรือเท่ากับ $k$ จะถูกจัดมาอยู่ส่วนหน้า และสมาชิกที่มีค่ามากกว่า $k$ จะไปอยู่ส่วนหลังโดยอัตโนมัติ

---

## 📝 3. Pseudocode

### Pseudocode 1: Recursive Partition
```text
Algorithm partitionRecursive(a, k, left, right):
    Input: Array of integers a, Integer k, Integer left, Integer right
    
    // Base Case: ตัวชี้ชนกันหรือสวนทางกัน
    If left >= right Then
        Return
    End If
    
    If a[left] <= k Then
        partitionRecursive(a, k, left + 1, right)
    Else If a[right] > k Then
        partitionRecursive(a, k, left, right - 1)
    Else
        Swap a[left] and a[right]
        partitionRecursive(a, k, left + 1, right - 1)
    End If
```

### Pseudocode 2: Iterative Partition
```text
Algorithm partitionIterative(a, k):
    Input: Array of integers a, Integer k
    If a is null OR length(a) <= 1 Then Return

    left = 0
    right = length(a) - 1

    While left < right Do
        While left < right AND a[left] <= k Do
            left = left + 1
        End While
        
        While left < right AND a[right] > k Do
            right = right - 1
        End While
        
        If left < right Then
            Swap a[left] and a[right]
            left = left + 1
            right = right - 1
        End If
    End While
```

### Pseudocode 3: Sorting-Based Algorithm
```text
Algorithm partitionBySorting(a, k):
    Input: Array of integers a, Integer k
    If a is null OR length(a) <= 1 Then Return

    // เรียงลำดับสมาชิกทั้งหมดจากน้อยไปมาก
    Sort(a)
```

---

## 💻 4. โปรแกรมภาษา Java ที่สามารถทำงานได้จริง

```java
import java.util.Arrays;

public class ArrayPartitioning {

    // ---------------------------------------------------------
    // Algorithm 1: Recursive Partition
    // ---------------------------------------------------------
    /**
     * แบ่งกลุ่มอาร์เรย์แบบเวียนเกิด (Recursive Two-Pointer)
     * @param a อาร์เรย์จำนวนเต็ม
     * @param k ค่าหลักเกณฑ์ในการแบ่งกลุ่ม (Pivot Value)
     * @param left ดรรชนีฝั่งซ้าย
     * @param right ดรรชนีฝั่งขวา
     */
    public static void partitionRecursive(int[] a, int k, int left, int right) {
        // Base Case: ป้องกันข้อมูลว่าง หรือเมื่อตัวชี้เดินมาชนกัน/สวนทางกัน
        if (a == null || left >= right) {
            return;
        }

        if (a[left] <= k) {
            // สมาชิกซ้ายถูกต้องแล้ว ขยับ left ไปทางขวา
            partitionRecursive(a, k, left + 1, right);
        } else if (a[right] > k) {
            // สมาชิกขวาถูกต้องแล้ว ขยับ right ไปทางซ้าย
            partitionRecursive(a, k, left, right - 1);
        } else {
            // ซ้าย > k และ ขวา <= k ให้สลับค่า แล้วขยับทั้งสองฝั่ง
            swap(a, left, right);
            partitionRecursive(a, k, left + 1, right - 1);
        }
    }

    // ---------------------------------------------------------
    // Algorithm 2: Iterative Partition
    // ---------------------------------------------------------
    /**
     * แบ่งกลุ่มอาร์เรย์แบบวนลูป (Iterative Two-Pointer)
     * @param a อาร์เรย์จำนวนเต็ม
     * @param k ค่าหลักเกณฑ์ในการแบ่งกลุ่ม
     */
    public static void partitionIterative(int[] a, int k) {
        if (a == null || a.length <= 1) {
            return;
        }

        int left = 0;
        int right = a.length - 1;

        while (left < right) {
            // เลื่อน left ไปทางขวาจนกว่าจะเจอตัวที่ > k
            while (left < right && a[left] <= k) {
                left++;
            }
            // เลื่อน right ไปทางซ้ายจนกว่าจะเจอตัวที่ <= k
            while (left < right && a[right] > k) {
                right--;
            }
            // สลับสมาชิกที่อยู่ผิดฝั่ง
            if (left < right) {
                swap(a, left, right);
                left++;
                right--;
            }
        }
    }

    // ---------------------------------------------------------
    // Algorithm 3: Sorting-Based Algorithm
    // ---------------------------------------------------------
    /**
     * แบ่งกลุ่มอาร์เรย์ด้วยการเรียงลำดับข้อมูล
     * @param a อาร์เรย์จำนวนเต็ม
     * @param k ค่าหลักเกณฑ์ในการแบ่งกลุ่ม
     */
    public static void partitionBySorting(int[] a, int k) {
        if (a == null || a.length <= 1) {
            return;
        }
        // ใน Java ใช้ Dual-Pivot Quicksort สำหรับ Primitive Types
        Arrays.sort(a);
    }

    // เมธอดช่วยสำหรับสลับสมาชิกในอาร์เรย์
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // เมธอดหลักสำหรับทดสอบการทำงาน
    public static void main(String[] args) {
        int[] original = {12, 4, 7, 15, 3, 10, 8};
        int k = 8;

        System.out.println("Input Array: " + Arrays.toString(original) + ", k = " + k);
        System.out.println("---------------------------------------------------------");

        // ทดสอบ Algorithm 1
        int[] test1 = original.clone();
        partitionRecursive(test1, k, 0, test1.length - 1);
        System.out.println("1. Recursive Partition Output : " + Arrays.toString(test1));

        // ทดสอบ Algorithm 2
        int[] test2 = original.clone();
        partitionIterative(test2, k);
        System.out.println("2. Iterative Partition Output : " + Arrays.toString(test2));

        // ทดสอบ Algorithm 3
        int[] test3 = original.clone();
        partitionBySorting(test3, k);
        System.out.println("3. Sorting-Based Output       : " + Arrays.toString(test3));
    }
}
```

---

## 📊 5. ตัวอย่างข้อมูลนำเข้าและผลลัพธ์ (Sample Input / Output)

### ข้อมูลนำเข้า (Input):
* **Array $A$:** `[12, 4, 7, 15, 3, 10, 8]`
* **$k$:** `8`

### ผลลัพธ์จากการรันโปรแกรม (Console Output):
```text
Input Array: [12, 4, 7, 15, 3, 10, 8], k = 8
---------------------------------------------------------
1. Recursive Partition Output : [8, 4, 7, 3, 15, 10, 12]
2. Iterative Partition Output : [8, 4, 7, 3, 15, 10, 12]
3. Sorting-Based Output       : [3, 4, 7, 8, 10, 12, 15]
```

* **คำอธิบายผลลัพธ์:**
  * สำหรับวิธีที่ 1 และ 2: สมาชิกกลุ่มแรกคือ `[8, 4, 7, 3]` ซึ่งทุกตัว $\le 8$ และกลุ่มหลังคือ `[15, 10, 12]` ซึ่งทุกตัว $> 8$ (สลับตามตำแหน่งที่เจอ ไม่จำเป็นต้องเรียงลำดับ)
  * สำหรับวิธีที่ 3: สมาชิกถูกจัดกลุ่มถูกต้องเช่นกัน และสมาชิกภายในจัดเรียงจากน้อยไปมากสมบูรณ์

---

## ⏱️ 6. การวิเคราะห์ Time Complexity

1. **Recursive Partition: $O(n)$**
   * **เหตุผล:** ในการเรียกเวียนเกิดแต่ละรอบ ตัวชี้ `left` จะขยับไปทางขวา 1 ตำแหน่ง หรือ `right` จะขยับไปทางซ้าย 1 ตำแหน่ง หรือขยับทั้งคู่หลังจากสลับค่า ทำให้สมาชิกทั้ง $n$ ตัวถูกประมวลผลเพียงครั้งเดียว จำนวนรอบทำงานรวมเป็นเชิงเส้น $O(n)$

2. **Iterative Partition: $O(n)$**
   * **เหตุผล:** ลูปวนทำงานโดยให้ `left` เดินจากซ้ายไปขวา และ `right` เดินจากขวาไปซ้าย รวมระยะทางเดินของตัวชี้ทั้งสองเท่ากับ $n$ สมาชิก อ่านและเปรียบเทียบข้อมูลสมาชิกละ 1 ครั้ง จึงใช้เวลาเป็น $O(n)$

3. **Sorting-Based Algorithm: $O(n \log n)$**
   * **เหตุผล:** เมธอด `Arrays.sort()` ในภาษา Java ใช้ Dual-Pivot Quicksort ซึ่งมีประสิทธิภาพเฉลี่ยในการเรียงลำดับเป็น $O(n \log n)$ เนื่องจากต้องทำการเปรียบเทียบและจัดเรียงสมาชิกทุกตัวอย่างสมบูรณ์

---

## 💾 7. การวิเคราะห์ Space Complexity

1. **Recursive Partition: $O(n)$ Auxiliary Space**
   * **เหตุผล:** แม้จะปรับเปลี่ยนข้อมูลในอาร์เรย์เดิมตรงๆ (In-place) แต่การเวียนเกิดจะสร้าง Call Stack ในหน่วยความจำ Stack ของ JVM สูงสุด $n$ ชั้นในกรณีเลื่อนทีละตำแหน่ง ทำให้ใช้พื้นที่ความจำชั่วคราวเป็น $O(n)$

2. **Iterative Partition: $O(1)$ Auxiliary Space**
   * **เหตุผล:** ทำงานแบบ In-place สมบูรณ์ โดยใช้เพียงตัวแปรดรรชนีคงที่ (`left`, `right`) ไม่มีการจองหน่วยความจำเพิ่มตามขนาดข้อมูล $n$

3. **Sorting-Based Algorithm: $O(\log n)$ Auxiliary Space**
   * **เหตุผล:** ใช้หน่วยความจำสำหรับ Call Stack ของอัลกอริทึม Quicksort ในการแบ่งย่อยช่วงข้อมูล ซึ่งใช้พื้นที่ประมาณ $O(\log n)$

---

## 🔍 8. ตอบคำถามเชิงวิเคราะห์เฉพาะของข้อ 5

### 8.1 เหตุผลที่การเรียงลำดับ (Sorting-Based) อาจทำให้โปรแกรมช้ากว่าที่จำเป็น
* **ข้อกำหนดของโจทย์:** ต้องการเพียงแค่จัดให้สมาชิก $\le k$ อยู่ด้านหน้า และ $> k$ อยู่ด้านหลัง โดย**ไม่จำเป็นต้องเรียงลำดับสมาชิกภายในแต่ละกลุ่ม**
* **การทำงานส่วนเกิน (Overhead):** อัลกอริทึมการแบ่งกลุ่ม (Partition) ใช้เวลาเพียง $O(n)$ ในขณะที่การเรียงลำดับต้องใช้เวลา $O(n \log n)$ การเรียงลำดับจึงเป็นการสิ้นเปลืองเวลาในการประมวลผลกับงานที่โจทย์ไม่ได้ต้องการ
* **เปรียบเทียบความแตกต่างเมื่อข้อมูลมีขนาดใหญ่:**
  * หาก $n = 1,000,000$:
    * วิธี **Partition ($O(n)$)** ประมวลผลประมาณ $1,000,000$ รอบ
    * วิธี **Sorting ($O(n \log n)$)** ประมวลผลประมาณ $1,000,000 	imes \log_2(1,000,000) pprox 20,000,000$ รอบ (ช้ากว่าประมาณ 20 เท่า)

### 8.2 ความสัมพันธ์ของปัญหานี้กับขั้นตอน Partition ใน Quick Sort
* ปัญหานี้ใช้ **ตรรกะเดียวกันกับขั้นตอน Partition ใน Quick Sort** (เช่น Hoare's Partition Scheme) โดยตรง
* **ค่า $k$ ในโจทย์ทำหน้าที่เป็น Pivot (หลักเปรียบเทียบ):** เพื่อแบ่งข้อมูลออกเป็นสองฝั่ง
* **ความแตกต่าง:** โจทย์ข้อนี้ต้องการทำ Partition เพียง **1 ครั้ง** แล้วจบการทำงาน แต่อัลกอริทึม Quick Sort จะนำฝั่งซ้ายและฝั่งขวาไปทำ Partition แบบเวียนเกิดซ้ำๆ จนกว่าข้อมูลจะเรียงลำดับสมบูรณ์

### 8.3 การระบุความสามารถในการทำงานแบบ In-place
* **ทุกอัลกอริทึมเป็น In-place Algorithm** ในแง่ของโครงสร้างข้อมูล เนื่องจากปรับเปลี่ยนตำแหน่งสมาชิกภายในอาร์เรย์เดิมโดยตรง ไม่ได้สร้างอาร์เรย์ใหม่ขนาด $n$
* หากพิจารณา Auxiliary Space เพิ่มเติม: **Iterative Partition เป็นวิธีเดียวที่เป็น In-place สมบูรณ์ทั้งระดับข้อมูลและหน่วยความจำระบบ ($O(1)$ Space)**

---

## ⚖️ 9. การเปรียบเทียบข้อดีและข้อจำกัดของแต่ละอัลกอริทึม

| มิติการเปรียบเทียบ | 1. Recursive Partition | 2. Iterative Partition | 3. Sorting-Based |
| :--- | :--- | :--- | :--- |
| **Time Complexity** | $O(n)$ | $O(n)$ *(เร็วที่สุด)* | $O(n \log n)$ *(ช้าที่สุด)* |
| **Auxiliary Space** | $O(n)$ *(Call Stack)* | $O(1)$ *(ประหยัดที่สุด)* | $O(\log n)$ |
| **In-place capability** | เป็น In-place | เป็น In-place สมบูรณ์ | เป็น In-place |
| **ความเสี่ยง Stack Overflow** | **สูง** (เมื่อ $n \ge 10,000$) | **ไม่มี** | ไม่มี |
| **ความง่ายในการเขียนโค้ด** | ปานกลาง | ปานกลาง (ระวัง Index) | **ง่ายที่สุด** (ใช้ Library) |

---

## 🎯 10. สรุปความเหมาะสมภายใต้เงื่อนไขต่างๆ (Conclusion)

1. **สำหรับการใช้งานในระบบจริง (Production) หรือข้อมูลขนาดใหญ่ ($n \ge 10,000$):**
   * **อัลกอริทึมที่เหมาะสมที่สุด:** **Iterative Partition (วิธีที่ 2)**
   * **เหตุผล:** ให้ประสิทธิภาพสูงสุดทั้งด้านเวลา $O(n)$ และหน่วยความจำ $O(1)$ ไม่มีความเสี่ยงต่อปัญหา `StackOverflowError`

2. **สำหรับการศึกษาแนวคิดทางอัลกอริทึม หรือข้อมูลขนาดเล็ก ($n < 1,000$):**
   * **อัลกอริทึมที่เหมาะสมที่สุด:** **Recursive Partition (วิธีที่ 1)**
   * **เหตุผล:** โค้ดสะท้อนตรรกะแบบ Divide and Conquer และช่วยให้เข้าใจหลักการทำงานของ Quick Sort ได้ดีที่สุด

3. **สำหรับกรณีที่ต้องการให้ข้อมูลเรียงลำดับจากน้อยไปมากสมบูรณ์:**
   * **อัลกอริทึมที่เหมาะสมที่สุด:** **Sorting-Based Algorithm (วิธีที่ 3)**
   * **เหตุผล:** เหมาะเมื่อระบบปลายทางจำเป็นต้องใช้ข้อมูลที่เรียงลำดับอย่างเป็นระเบียบไปประมวลผลต่อ
