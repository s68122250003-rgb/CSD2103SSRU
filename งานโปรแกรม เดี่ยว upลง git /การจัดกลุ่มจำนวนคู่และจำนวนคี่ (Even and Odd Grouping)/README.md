# ข้อ 4: การจัดกลุ่มจำนวนคู่และจำนวนคี่ (Even and Odd Grouping)

โปรเจกต์นี้จัดทำขึ้นสำหรับการวิเคราะห์และเปรียบเทียบอัลกอริทึมการจัดกลุ่มข้อมูลในอาร์เรย์ โดยกำหนดให้ **จำนวนคู่ทั้งหมดอยู่ด้านหน้า** และ **จำนวนคี่ทั้งหมดอยู่ด้านหลัง** ผ่านการเปรียบเทียบ 3 แนวทาง ได้แก่ Recursive, Iterative (Two-Pointer) และ Extra Array

---

## 1. โจทย์และแนวคิดของอัลกอริทึม (Concept & Approaches)

กำหนดอาร์เรย์ของจำนวนเต็ม $A$ ขนาด $n$ ต้องการจัดกลุ่มข้อมูลแบ่งฝั่งคู่-คี่

### 1.1 Recursive Two-Pointer (`rearrangeRecursive`)
* **แนวคิด**: กำหนดตัวชี้ 2 ตำแหน่ง คือ `left` (เริ่มที่ index 0) และ `right` (เริ่มที่ index `n - 1`) แล้วทำการตรวจสอบและสลับตำแหน่งแบบเวียนเกิด
* **Base Case**: เมื่อ `left >= right` (ตัวชี้ชนกันหรือสวนทางกัน) ให้หยุดการทำงาน
* **Recursive Case**:
  * หาก `a[left]` เป็นคู่ -> เลื่อนขยับ `left + 1`
  * หาก `a[right]` เป็นคี่ -> เลื่อนขยับ `right - 1`
  * หาก `a[left]` เป็นคี่ และ `a[right]` เป็นคู่ -> สลับค่า (Swap) แล้วขยับ `left + 1` และ `right - 1` เข้าหากัน

### 1.2 Iterative Two-Pointer (`rearrangeTwoPointer`)
* **แนวคิด**: ใช้ตัวชี้ `left` และ `right` ทำงานในลักษณะเดียวกับวิธีแรก แต่เปลี่ยนกระบวนการเวียนเกิดเป็นการวนลูป `while` เพื่อลดการใช้หน่วยความจำ
* **กระบวนการ**: เลื่อน `left` ไปทางขวาตราบเท่าที่เป็นคู่ และเลื่อน `right` ไปทางซ้ายตราบเท่าที่เป็นคี่ หากพบตำแหน่งที่ไม่ถูกต้อง ให้สลับค่าและขยับตัวชี้เข้าหากัน

### 1.3 Extra Array (`rearrangeExtraArray`)
* **แนวคิด**: สร้างอาร์เรย์ใหม่ขนาด $n$ เพื่อคัดเลือกและวางข้อมูลทีละกลุ่มโดยไม่ดัดแปลงอาร์เรย์เดิม
* **กระบวนการ**: 
  1. วนลูปคัดลอกเฉพาะ **จำนวนคู่** ลงอาร์เรย์ใหม่จากซ้ายไปขวา
  2. วนลูปคัดลอก **จำนวนคี่** ใส่ต่อท้ายกลุ่มจำนวนคู่
* **ความเสถียร (Stability)**: เป็น **Stable Algorithm** เนื่องจากลำดับสัมพัทธ์เดิมของสมาชิกทุกตัวถูกรักษาไว้อย่างสมบูรณ์

---

## 2. ผังขั้นตอนการทำงาน (Pseudocode)

### 2.1 Pseudocode: Recursive Two-Pointer
```text
Algorithm rearrangeRecursive(a, left, right):
    Input: Array a, Integer left, Integer right

    If left >= right Then
        Return
    End If

    If a[left] % 2 == 0 Then
        rearrangeRecursive(a, left + 1, right)
    Else If a[right] % 2 != 0 Then
        rearrangeRecursive(a, left, right - 1)
    Else
        Swap a[left] and a[right]
        rearrangeRecursive(a, left + 1, right - 1)
    End If