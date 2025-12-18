package com.leave;

import java.util.ArrayList;
import java.util.List;

public class LeaveApp {

    public static void main(String[] args) {

        Employee emp = new Employee(101, "Anita");

        List<LeaveRequest> requests = new ArrayList<>();

        requests.add(new SickLeaveRequest(1, emp,
                "2025-01-01", "2025-01-03", false));

        requests.add(new VacationLeaveRequest(2, emp,
                "2025-02-01", "2025-02-05"));

        requests.add(new MaternityLeaveRequest(3, emp,
                "2025-03-01", "2025-08-28"));

        // ===== POLYMORPHISM =====
        for (LeaveRequest lr : requests) {
            lr.processRequest();
            lr.approve("HR Manager");
            lr.printStatusHistory();
            System.out.println("------------");
        }
    }
}
