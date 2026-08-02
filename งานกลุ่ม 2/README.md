# กรณีศึกษาที่ 2: ระบบตรวจสอบคะแนนซ้ำ (Duplicate Score Checker System)

โปรเจกต์นี้เป็นการเปรียบเทียบประสิทธิภาพของอัลกอริทึมในการตรวจสอบข้อมูลคะแนนสอบที่ซ้ำกันในรายการข้อมูล (Array) ภาษา Java โดยเปรียบเทียบระหว่างวิธี **Brute Force (Nested Loop)** และวิธี **HashSet**

---

## 📋 สารบัญ (Table of Contents)
- [รายละเอียดโจทย์ (Problem Overview)](#รายละเอียดโจทย์-problem-overview)
- [โครงสร้างโปรเจกต์ (Project Structure)](#โครงสร้างโปรเจกต์-project-structure)
- [วิธีการติดตั้งและการใช้งาน (How to Run)](#วิธีการติดตั้งและการใช้งาน-how-to-run)
- [การทำงานของเมธอด (Implementation)](#การทำงานของเมธอด-implementation)
- [บทวิเคราะห์ประสิทธิภาพ (Algorithm Analysis)](#บทวิเคราะห์ประสิทธิภาพ-algorithm-analysis)
  - [1. กำหนดค่า $n$](#1-กำหนดค่า-n)
  - [2. วิเคราะห์ Best Case และ Worst Case](#2-วิเคราะห์-best-case-และ-worst-case)
  - [3. Time Complexity](#3-time-complexity)
  - [4. Auxiliary Space Complexity](#4-auxiliary-space-complexity)
  - [5. ตารางเปรียบเทียบข้อดีและข้อจำกัด](#5-ตารางเปรียบเทียบข้อดีและข้อจำกัด)
  - [6. อธิบาย Time–Space Trade-off](#6-อธิบาย-timespace-trade-off)
  - [7. ข้อเสนอแนะกรณีข้อมูลขนาดใหญ่ ($n = 1,000,000$)](#7-ข้อเสนอแนะกรณีข้อมูลขนาดใหญ่-n--1000000)

---

## 📌 รายละเอียดโจทย์ (Problem Overview)

อาจารย์มีรายการคะแนนสอบของนักศึกษา และต้องการตรวจสอบว่ามีคะแนนใดซ้ำกันหรือไม่

**ตัวอย่างชุดข้อมูล:**
```java
int[] scores = {78, 85, 62, 90, 78, 74};
```

**งานที่ได้รับมอบหมาย:**
1. พัฒนาเมธอด `hasDuplicateBruteForce(int[] scores)` โดยใช้ลูปซ้อนกันเพื่อเปรียบเทียบคะแนนทุกคู่
2. พัฒนาเมธอด `hasDuplicateHashSet(int[] scores)` โดยใช้ `HashSet<Integer>` เพื่อบันทึกและตรวจสอบคะแนนที่เคยพบแล้ว
3. วิเคราะห์ความซับซ้อนของเวลา (Time Complexity) และหน่วยความจำ (Space Complexity)

---

## 📁 โครงสร้างโปรเจกต์ (Project Structure)

```text
.
├── DuplicateChecker.java   # ไฟล์ซอร์สโค้ดหลักภาษา Java
└── README.md               # เอกสารอธิบายโปรเจกต์และการวิเคราะห์
```

---

## 🚀 วิธีการติดตั้งและการใช้งาน (How to Run)

### สิ่งที่ต้องมีก่อน (Prerequisites)
- Java Development Kit (JDK) เวอร์ชัน 8 ขึ้นไป

### ขั้นตอนการรันโปรแกรม
1. คอมไพล์ไฟล์ Java:
   ```bash
   javac DuplicateChecker.java
   ```
2. รันโปรแกรม:
   ```bash
   java DuplicateChecker
   ```

---

## 💻 การทำงานของเมธอด (Implementation)

```java
import java.util.HashSet;

public class DuplicateChecker {

    /**
     * วิธีที่ 1: ตรวจสอบคะแนนซ้ำด้วย Brute Force (Nested Loop)
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static boolean hasDuplicateBruteForce(int[] scores) {
        if (scores == null || scores.length <= 1) {
            return false;
        }

        int n = scores.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (scores[i] == scores[j]) {
                    return true; // พบข้อมูลซ้ำ
                }
            }
        }
        return false; // ไม่พบข้อมูลซ้ำ
    }

    /**
     * วิธีที่ 2: ตรวจสอบคะแนนซ้ำด้วย HashSet
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static boolean hasDuplicateHashSet(int[] scores) {
        if (scores == null || scores.length <= 1) {
            return false;
        }

        HashSet<Integer> seen = new HashSet<>();
        for (int score : scores) {
            // หากมีคะแนนนี้ใน HashSet อยู่แล้ว แสดงว่าซ้ำ
            if (seen.contains(score)) {
                return true;
            }
            seen.add(score);
        }
        return false; // ไม่พบข้อมูลซ้ำ
    }

    public static void main(String[] args) {
        int[] scores = {78, 85, 62, 90, 78, 74};

        System.out.println("Brute Force Result : " + hasDuplicateBruteForce(scores));
        System.out.println("HashSet Result     : " + hasDuplicateHashSet(scores));
    }
}
```

---

## 📊 บทวิเคราะห์ประสิทธิภาพ (Algorithm Analysis)

### 1. กำหนดค่า $n$
* **$n$** คือ จำนวนสมาชิกทั้งหมดในอาร์เรย์ `scores` (หรือ `scores.length`)

---

### 2. วิเคราะห์ Best Case และ Worst Case

* **วิธีที่ 1: Brute Force (Nested Loop)**
  * **Best Case:** พบข้อมูลซ้ำคู่แรกที่ตำแหน่ง Index 0 และ Index 1 ทันที เปรียบเทียบเพียง 1 ครั้ง $\rightarrow O(1)$
  * **Worst Case:** ไม่พบข้อมูลซ้ำเลย หรือข้อมูลซ้ำอยู่ที่คู่สุดท้าย ต้องเปรียบเทียบข้อมูลทุกคู่ รวมทั้งหมด $\frac{n(n - 1)}{2}$ ครั้ง $\rightarrow O(n^2)$

* **วิธีที่ 2: HashSet**
  * **Best Case:** พบข้อมูลซ้ำตั้งแต่ตัวที่ 2 ของอาร์เรย์ อ่านและเช็กเพียง 2 ครั้ง $\rightarrow O(1)$
  * **Worst Case:** ไม่พบข้อมูลซ้ำเลย ต้องอ่านข้อมูลและเพิ่มเข้า `HashSet` จนครบทุกตัวทั้ง $n$ ตัว $\rightarrow O(n)$

---

### 3. Time Complexity

| เมธอด | Big-O Notation | คำอธิบาย |
| :--- | :---: | :--- |
| **`hasDuplicateBruteForce`** | $O(n^2)$ | เกิดจากการใช้ Nested Loop โดยลูปนอกวน $n$ รอบ และลูปในวนสูงสุด $n$ รอบ |
| **`hasDuplicateHashSet`** | $O(n)$ | วนลูปอ่านข้อมูลเพียงรอบเดียว โดย operation `contains()` และ `add()` ของ HashSet มีเฉลี่ย $O(1)$ |

---

### 4. Auxiliary Space Complexity

| เมธอด | Big-O Notation | คำอธิบาย |
| :--- | :---: | :--- |
| **`hasDuplicateBruteForce`** | $O(1)$ | ใช้เพียงตัวแปรดัชนี (`i`, `j`) ไม่มีการสร้างโครงสร้างข้อมูลเพิ่มเติม |
| **`hasDuplicateHashSet`** | $O(n)$ | กรณี Worst Case จะต้องเก็บข้อมูลทั้ง $n$ ตัวไว้ใน `HashSet` |

---

### 5. ตารางเปรียบเทียบข้อดีและข้อจำกัด

| หัวข้อเปรียบเทียบ | วิธีที่ 1: Brute Force | วิธีที่ 2: HashSet |
| :--- | :--- | :--- |
| **ข้อดี** | • โค้ดเข้าใจง่าย ไม่ซับซ้อน<br>• ไม่ประมวลผลเพิ่มในส่วนของหน่วยความจำ ($O(1)$ Space) | • ทำงานรวดเร็วมากแม้ข้อมูลจะมีขนาดใหญ่มาก ($O(n)$ Time) |
| **ข้อจำกัด** | • ทำงานช้ามากเมื่อ $n$ มีขนาดใหญ่ ($O(n^2)$ Time) | • ต้องใช้หน่วยความจำเพิ่มสำหรับเก็บ `HashSet` ($O(n)$ Space)<br>• มี Overhead เรื่องการทำ Auto-boxing (`int` $\rightarrow$ `Integer`) |

---

### 6. อธิบาย Time–Space Trade-off

**Time–Space Trade-off** ในกรณีศึกษานี้ คือการ **"สละหน่วยความจำเพิ่มเติม (Space) เพื่อให้ได้ความเร็วในการประมวลผล (Time) ที่เพิ่มขึ้นอย่างมหาศาล"**
* **วิธีที่ 1** เน้นประหยัดหน่วยความจำ ($O(1)$ Space) แต่องค์ความซับซ้อนของเวลากลายเป็น $O(n^2)$
* **วิธีที่ 2** ใช้หน่วยความจำเพิ่ม ($O(n)$ Space) เพื่อแลกกับการลดทอนเวลาในการค้นหาลงมาเหลือ $O(n)$

---

### 7. ข้อเสนอแนะกรณีข้อมูลขนาดใหญ่ ($n = 1,000,000$)

**สรุปคำตอบ:** ควรเลือก **วิธีที่ 2 (HashSet)**

**เหตุผลสนับสนุน:**
1. **เวลาในการประมวลผลแตกต่างกันมหาศาล:**
   * **Brute Force ($O(n^2)$):** ต้องเปรียบเทียบข้อมูลสูงถึง $\frac{(10^6)^2}{2} = 500,000,000,000$ ครั้ง (5 แสนล้านครั้ง) ซึ่งอาจใช้เวลาประมวลผล **หลายชั่วโมง หรือเป็นวัน**
   * **HashSet ($O(n)$):** ทำการค้นหาและบันทึกเพียง $1,000,000$ ครั้ง ซึ่งใช้เวลาประมวลผลเพียง **ไม่กี่มิลลิวินาที** (เสี้ยววินาที)
2. **การใช้งานหน่วยความจำสมเหตุสมผล:**
   * การเก็บ `Integer` จำนวน 1,000,000 ค่าใน `HashSet` จะใช้ RAM ประมาณ **30–50 MB** ซึ่งถือว่าน้อยมากสำหรับระบบคอมพิวเตอร์ในปัจจุบัน
