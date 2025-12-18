package com.leave;

public class SickLeaveRequest extends LeaveRequest {

    private boolean medicalCertificateProvided;

    public SickLeaveRequest(int requestId, Employee employee,
                            String startDate, String endDate,
                            boolean medicalCertificateProvided) {
        super(requestId, employee, startDate, endDate, "SICK");
        this.medicalCertificateProvided = medicalCertificateProvided;
    }

    @Override
    public boolean processRequest() {
        if (!medicalCertificateProvided && calculateLeaveDays() > 2) {
            System.out.println("Medical certificate required for sick leave > 2 days");
            return false;
        }
        System.out.println("Sick leave processed");
        return true;
    }

    @Override
    public int calculateLeaveDays() {
        return 3; // mocked for simplicity
    }
}
