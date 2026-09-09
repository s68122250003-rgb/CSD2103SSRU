public class queue {

    // =========================================
    // Algorithm A : FIFO Queue[cite: 3]
    // =========================================
    public static void FIFOqueue(List<FoodOrder> orders) {
        Queue<FoodOrder> queue = new ArrayDeque<>();
        for (FoodOrder order : orders) {
            queue.add(order);
        }

        System.out.println("\n===== Algorithm A : FIFO Queue =====");
        int currentTime = 0;
        int totalWaitingTime = 0; 
        int maxWaitingTime = 0;

        while (!queue.isEmpty()) {
            FoodOrder order = queue.poll();
            // คำนวณตามสูตรในรายงาน: waitingTime = currentTime - arrivalTime[cite: 3]
            int waitingTime = Math.max(0, currentTime - order.arrivalTime);
            totalWaitingTime += waitingTime;
            maxWaitingTime = Math.max(maxWaitingTime, waitingTime);

            System.out.println("Processing Order: " + order.orderId + " | Waiting time: " + waitingTime + " mins");
            
            if (currentTime < order.arrivalTime) {
                currentTime = order.arrivalTime;
            }
            currentTime += order.preparationTime;
        }

        double averageWaitingTime = orders.isEmpty() ? 0 : (double) totalWaitingTime / orders.size();
        System.out.println("--------------------------------"); 
        System.out.println("Average Waiting Time = " + averageWaitingTime + " mins");
        System.out.println("Maximum Waiting Time = " + maxWaitingTime + " mins"); 
    }  

    // =========================================
    // Algorithm B : Priority Queue[cite: 3]
    // =========================================
    public static void priorityQueue(List<FoodOrder> orders) {
        // จัดเรียงตาม Priority (น้อยไปมาก) และกรณีเท่ากันให้ดู arrivalTime (มาก่อนทำก่อน) ตามรายงาน[cite: 3]
        PriorityQueue<FoodOrder> queue = new PriorityQueue<>(
            Comparator.comparingInt((FoodOrder o) -> o.priority)
                      .thenComparingInt(o -> o.arrivalTime)
        );

        for (FoodOrder order : orders) {
            queue.add(order);
        }

        System.out.println("\n===== Algorithm B : Priority Queue =====");
        int currentTime = 0;
        int totalWaitingTime = 0; 
        int maxWaitingTime = 0;

        while (!queue.isEmpty()) {
            FoodOrder order = queue.poll();
            int waitingTime = Math.max(0, currentTime - order.arrivalTime);
            totalWaitingTime += waitingTime;
            maxWaitingTime = Math.max(maxWaitingTime, waitingTime);

            System.out.println("Processing Priority Order: " + order.orderId + " (Priority: " + order.priority + ") | Waiting time: " + waitingTime + " mins");
            
            if (currentTime < order.arrivalTime) {
                currentTime = order.arrivalTime;
            }
            currentTime += order.preparationTime;
        }

        double averageWaitingTime = orders.isEmpty() ? 0 : (double) totalWaitingTime / orders.size();
        System.out.println("--------------------------------"); 
        System.out.println("Average Waiting Time = " + averageWaitingTime + " mins");
        System.out.println("Maximum Waiting Time = " + maxWaitingTime + " mins"); 
    }

    // =========================================
    // Main : User Input 
    // =========================================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<FoodOrder> orders = new ArrayList<>();

        System.out.println("===== Food Delivery Order System =====");
        System.out.print("Enter the number of orders: ");
        int n = scanner.nextInt(); 
        scanner.nextLine();

        for (int i = 0; i < n; i++) { 
            System.out.println("\n--- Order " + (i + 1) + " ---");
            System.out.print("Enter Order ID: ");
            String orderId = scanner.nextLine(); 

            System.out.print("Enter Arrival Time (minutes): ");
            int arrivalTime = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Select Food Type:");
            System.out.println("1 = Express");
            System.out.println("2 = Normal");
            System.out.println("3 = Large");
            System.out.print("Choice (1-3): ");
            String typeChoice = scanner.nextLine();

            String foodType;
            int priority;

            // กำหนด Priority ตามเอกสารรายงาน: Express = 1, Normal = 2, Large = 3[cite: 3]
            if (typeChoice.equals("1")) {
                foodType = "Express";
                priority = 1;
            } else if (typeChoice.equals("2")) {
                foodType = "Normal";
                priority = 2;
            } else {
                foodType = "Large";
                priority = 3;
            }   

            System.out.print("Preparation Time (minutes): "); 
            int preparationTime = scanner.nextInt();
            scanner.nextLine();

            orders.add(new FoodOrder(orderId, arrivalTime, foodType, preparationTime, priority));
        }

        // ประมวลผลทั้งสองอัลกอริทึม[cite: 3]
        FIFOqueue(orders);
        priorityQueue(orders);

        scanner.close();
    }
}