package com.leave;

public class MaternityLeaveRequest extends LeaveRequest {

    public MaternityLeaveRequest(int requestId, Employee employee,
                                 String startDate, String endDate) {
        super(requestId, employee, startDate, endDate, "MATERNITY");
    }

    @Override
    public boolean processRequest() {
        System.out.println("Maternity leave processed");
        return true;
    }

    @Override
    public int calculateLeaveDays() {
        return 180;
    }
}
