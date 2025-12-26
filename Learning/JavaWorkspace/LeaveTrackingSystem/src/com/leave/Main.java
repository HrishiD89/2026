package com.leave;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		// Create Employee
        Employee emp1 = new Employee(101, "Alice Smith", "IT", "alice@example.com");
        Employee emp2 = new Employee(102, "Bob Jones", "HR", "bob@example.com");

        // Create Leave Requests
        SickLeaveRequest sickLeave = new SickLeaveRequest(1, emp1, "2025-05-01", "2025-05-03", false);
        MaternityLeaveRequest maternityLeave = new MaternityLeaveRequest(2, emp1, "2025-06-01", "2025-09-01");
        VacationLeaveRequest vacationLeave = new VacationLeaveRequest(3, emp2, "2025-07-01", "2025-07-10", true);

        // Polymorphism: List of LeaveRequest
        List<LeaveRequest> requests = new ArrayList<>();
        requests.add(sickLeave);
        requests.add(maternityLeave);
        requests.add(vacationLeave);

        System.out.println("--- Processing Requests (Polymorphism) ---");
        for (LeaveRequest request : requests) {
            System.out.println("\nRequest ID: " + request.getRequestId() + " (" + request.getClass().getSimpleName() + ")");
            System.out.println("Employee: " + request.getEmployee().getName());
            System.out.println("Days: " + request.calculateLeaveDays());
            
            // Polymorphic call
            boolean processed = request.processRequest();
            
            if (processed) {
                // Simulate approval process
                if (request instanceof SickLeaveRequest && request.calculateLeaveDays() > 2 && !((SickLeaveRequest)request).isMedicalCertificateProvided()) {
                     request.deny("System", "Medical certificate missing for > 2 days");
                } else {
                     request.approve("Manager");
                }
            }
        }

        System.out.println("\n--- Status History Tracking (Inner Class) ---");
        // Demonstrate history on the Sick Leave request which was denied/approved
        // Let's manually change status to show history
        sickLeave.changeStatus("Pending Appeal", "Alice");
        sickLeave.approve("Director"); // Approve after appeal

        sickLeave.displayStatusHistory();
	}
}