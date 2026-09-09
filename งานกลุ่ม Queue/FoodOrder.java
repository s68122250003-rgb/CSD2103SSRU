import java.util.*;

class FoodOrder {
    String orderId;
    int arrivalTime; // เวลาที่ Order เข้าสู่ระบบ (นาที)
    String foodType;
    int preparationTime;
    int priority; // 1: Express, 2: Normal, 3: Large (เลขน้อย = สำคัญมาก)[cite: 3]

    public FoodOrder(String orderId, int arrivalTime, String foodType, int preparationTime, int priority) {
        this.orderId = orderId;
        this.arrivalTime = arrivalTime;
        this.foodType = foodType;
        this.preparationTime = preparationTime;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return orderId + " | Arrival: " + arrivalTime + "m | " + foodType + " | Prep: " + preparationTime + "m | Priority: " + priority;
    }
}