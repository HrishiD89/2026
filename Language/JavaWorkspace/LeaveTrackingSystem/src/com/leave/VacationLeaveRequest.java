package com.leave;

public class VacationLeaveRequest extends LeaveRequest {

    public VacationLeaveRequest(int requestId, Employee employee,
                                String startDate, String endDate) {
        super(requestId, employee, startDate, endDate, "VACATION");
    }

    @Override
    public boolean processRequest() {
        System.out.println("Vacation leave processed");
        return true;
    }

    @Override
    public int calculateLeaveDays() {
        return 5;
    }
}
