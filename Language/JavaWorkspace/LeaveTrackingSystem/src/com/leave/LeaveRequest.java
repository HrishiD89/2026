package com.leave;

import java.util.ArrayList;
import java.util.List;

public abstract class LeaveRequest implements Approvable {

    protected int requestId;
    protected Employee employee;
    protected String startDate;
    protected String endDate;
    protected String status;

    private List<StatusChange> statusHistory = new ArrayList<>();

    public LeaveRequest(int requestId, Employee employee,
                        String startDate, String endDate, String status) {
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // ===== POLYMORPHIC METHOD =====
    public boolean processRequest() {
        System.out.println("Processing generic leave request...");
        return true;
    }

    // ===== ABSTRACT METHOD =====
    public abstract int calculateLeaveDays();

    // ===== INTERFACE IMPLEMENTATION =====
    @Override
    public boolean approve(String approverName) {
        changeStatus("APPROVED", approverName);
        return true;
    }

    @Override
    public boolean deny(String approverName, String reason) {
        changeStatus("DENIED (" + reason + ")", approverName);
        return true;
    }

    // ===== INNER CLASS =====
    public class StatusChange {
        private String oldStatus;
        private String newStatus;
        private String changedBy;

        public StatusChange(String oldStatus, String newStatus, String changedBy) {
            this.oldStatus = oldStatus;
            this.newStatus = newStatus;
            this.changedBy = changedBy;
        }

        @Override
        public String toString() {
            return oldStatus + " -> " + newStatus + " by " + changedBy;
        }
    }

    protected void changeStatus(String newStatus, String changedBy) {
        StatusChange change =
                new StatusChange(this.status, newStatus, changedBy);
        statusHistory.add(change);
        this.status = newStatus;
    }

    public void printStatusHistory() {
        for (StatusChange sc : statusHistory) {
            System.out.println(sc);
        }
    }
}
