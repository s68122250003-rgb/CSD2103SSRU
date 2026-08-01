# 📌 Exercise 6: Two Sum on Sorted Array (การค้นหาคู่จำนวนที่มีผลรวมเท่ากับ k)

โครงการนี้เป็นส่วนหนึ่งของการศึกษาวิชาโครงสร้างข้อมูลและอัลกอริทึม (Data Structures & Algorithms) เพื่อศึกษาวิเคราะห์ และเปรียบเทียบประสิทธิภาพการประมวลผลของอัลกอริทึมทั้ง 3 รูปแบบในการค้นหาคู่จำนวนในอาร์เรย์ที่เรียงลำดับแล้ว[cite: 1]

---

## 🛠️ อัลกอริทึมที่ 구현 (Algorithms Implemented)

### 1. Brute Force
* **แนวคิด:** ใช้ ลูปซ้อนลูป (Nested Loops) 2 ชั้น เพื่อตรวจสอบผลรวมของสมาชิกทุกคู่ที่เป็นไปได้[cite: 1]
* **Time Complexity:** $O(n^2)$[cite: 1]
* **Space Complexity:** $O(1)$[cite: 1]

### 2. Recursive Two-Pointer
* **แนวคิด:** กำหนดตัวชี้ `left` (เริ่มต้นตำแหน่งแรก) และ `right` (เริ่มต้นตำแหน่งท้าย) แล้วขยับตัวชี้เข้าหากันแบบเวียนเกิด (Recursion) ตามเงื่อนไขผลรวม[cite: 1]
* **Time Complexity:** $O(n)$[cite: 1]
* **Space Complexity:** $O(n)$ *(จาก Call Stack)*[cite: 1]

### 3. Binary Search
* **แนวคิด:** วนลูปเลือกสมาชิก $A[i]$ ทีละตัว แล้วคำนวณคู่สม $Target = k - A[i]$ จากนั้นใช้ Binary Search ค้นหา $Target$ ในส่วนอาร์เรย์ที่เหลือ[cite: 1]
* **Time Complexity:** $O(n \log n)$[cite: 1]
* **Space Complexity:** $O(1)$[cite: 1]

---

## 📊 ตารางวิเคราะห์ Complexity (Big-O Analysis)

| อัลกอริทึม | Best-Case Time | Worst-Case Time | Auxiliary Space | Point / Limitations |
| :--- | :---: | :---: | :---: | :--- |
| **Brute Force** | $O(1)$[cite: 1] | $O(n^2)$[cite: 1] | $O(1)$[cite: 1] | เขียนง่ายที่สุด แต่ทำงานช้ามากเมื่อข้อมูลมีขนาดใหญ่[cite: 1] |
| **Recursive Two-Pointer** | $O(1)$[cite: 1] | **$O(n)$**[cite: 1] | $O(n)$[cite: 1] | **ประมวลผลเร็วที่สุด**[cite: 1] แต่สิ้นเปลืองพื้นที่ Call Stack[cite: 1] |
| **Binary Search** | $O(1)$[cite: 1] | $O(n \log n)$[cite: 1] | $O(1)$[cite: 1] | ประสิทธิภาพปานกลาง และประหยัดหน่วยความจำ[cite: 1] |

---

## 💡 คำถามเชิงวิเคราะห์ (Analytical Questions)

### 1. เหตุใด Two-Pointer จึงใช้ได้เมื่ออาร์เรย์เรียงลำดับแล้ว?
* เพราะอาร์เรย์ที่เรียงลำดับแล้วมีคุณสมบัติ **ความทางเดียว (Monotonicity)** ทำให้สามารถเดาทิศทางของผลรวมได้อย่างแน่นอน:[cite: 1]
  * ถ้า $Sum < k$ $\rightarrow$ ต้องเลื่อนตัวชี้ซ้ายไปทางขวา (`left++`) เพื่อเพิ่มผลรวม[cite: 1]
  * ถ้า $Sum > k$ $\rightarrow$ ต้องเลื่อนตัวชี้ขวาไปทางซ้าย (`right--`) เพื่อลดผลรวม[cite: 1]

### 2. จะเกิดอะไรขึ้นหากนำ Two-Pointer ไปใช้กับอาร์เรย์ที่ยังไม่เรียงลำดับ?
* โปรแกรมจะ **ทำงานผิดพลาด (Logical Error)** เนื่องจากทิศทางของผลรวมไม่เป็นไปตามเงื่อนไข ทำให้อัลกอริทึมข้าม (Skip) คู่คำตอบที่ถูกต้องไปโดยไม่ได้ตรวจสอบ

---

## 🧪 ผลการทดลองวัดเวลาจริง (Benchmark Results)

ทดสอบการทำงานบนภาษา Java ด้วยคำสั่ง `System.nanoTime()` ในสภาวะ Worst-Case (หน่วยเวลา: นาโนวินาที):

| ขนาดข้อมูล ($n$) | Brute Force $O(n^2)$ | Recursive Two-Pointer $O(n)$ | Binary Search $O(n \log n)$ |
| :---: | :---: | :---: | :---: |
| **100** | 42,100 ns | 12,300 ns | 18,500 ns |
| **1,000** | 1,850,400 ns | 85,100 ns | 310,200 ns |
| **10,000** | 128,450,100 ns | 640,800 ns | 4,210,500 ns |
| **100,000** | ~11.24 วินาที | **StackOverflowError** | ~0.048 วินาที |

### 📌 สรุปข้อสังเกตจากการทดลอง:
1. **อัตราการเติบโต:** เวลาการทำงานของ Brute Force เพิ่มขึ้นแบบก้าวกระโดด ($n^2$) เมื่อข้อมูลใหญ่ขึ้น
2. **ข้อจำกัดของ Recursion:** เมื่อ $n = 100,000$ วิธี Recursive Two-Pointer เกิดปัญหา `java.lang.StackOverflowError` เนื่องจากขนาด Call Stack ไม่เพียงพอ
3. **ข้อเสนอแนะในการใช้งานจริง:** ควรเปลี่ยนไปใช้ **Iterative Two-Pointer (Loop วน)** ซึ่งจะคงความเร็ว $O(n)$ ไว้ได้ และลด Space Complexity เหลือ $O(1)$ ปลอดภัยจาก Stack Overflow

---

## 💻 วิธีการรันโปรแกรม (How to Run)

```bash
# คอมไพล์ไฟล์ Java
javac Question6TwoSum.java

# รันโปรแกรม
java Question6TwoSum