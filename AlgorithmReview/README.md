### 1. ArrayCaseStudy (ระบบวิเคราะห์คะแนน)
ใช้ Array ในการเก็บและประมวลผลคะแนนสอบ 10 คน
***ฟังก์ชันการทำงาน:** คำนวณคะแนนรวม, คะแนนเฉลี่ย, หาค่าสูงสุด-ต่ำสุด, นับจำนวนผู้ที่สอบผ่าน (>= 7), และระบุรายชื่อนักศึกษาที่ต้องทบทวนเพิ่มเติม (< 5)
### ผลลัพธ์จาก ArrayCaseStudy
```
Total Score: 62
Average: 6.2
Max: 10, Min: 2
Students >= 7: 5
Needs Review: Student 3 (Score: 4) Student 8 (Score: 3) Student 10 (Score: 2)
```

### 2. StackCaseStudy (ระบบ Undo)
ใช้หลักการ **LIFO (Last-In, First-Out)** ซึ่งข้อมูลที่เข้ามาล่าสุดจะถูกนำออกก่อน
***ฟังก์ชันการทำงาน:** จำลองการบันทึกคำสั่งงาน และการทำ Undo คำสั่งล่าสุด 2 ครั้ง โดยมีการตรวจสอบสถานะของ Stack ก่อนดำเนินการเสมอ
### ผลลัพธ์จาก StackCaseStudy
```
Current Stack: [Type Data, Type Structure, Delete Structure, Type Algorithm, Type Java]
Undo: Type Java
Undo: Type Algorithm
Stack after Undo: [Type Data, Type Structure, Delete Structure]
```

### 3. QueueCaseStudy (ระบบคิวผู้ป่วย)
ใช้หลักการ **FIFO (First-In, First-Out)** ซึ่งข้อมูลที่เข้ามาถึงก่อนจะได้รับการบริการก่อน
***ฟังก์ชันการทำงาน:** จำลองการเข้าคิวผู้ป่วย P001-P005, การเรียกใช้บริการ 2 ลำดับแรก, การเพิ่มผู้ป่วยใหม่ P006-P007, และการตรวจสอบสถานะคิวคงเหลือ
### ผลลัพธ์จาก QueueCaseStudy
```
Service provided to: P001
Service provided to: P002
Next patient (peek): P003
Patients waiting (size): 5
Queue status: [P003, P004, P005, P006, P007]
```

---

## วิธีการรันโปรแกรม
ให้นักศึกษาเปิด Terminal ในโฟลเดอร์หลัก (`AlgorithmReview/`) แล้วใช้คำสั่งดังนี้:

1. **คอมไพล์:**
   ```bash
   javac src/ชื่อคลาส.java