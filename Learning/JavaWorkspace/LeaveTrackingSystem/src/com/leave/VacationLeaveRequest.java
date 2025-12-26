package com.leave;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class VacationLeaveRequest extends LeaveRequest {
	private boolean isPlanned;

	public VacationLeaveRequest(int requestId, Employee employee, String startDate, String endDate, boolean isPlanned) {
		super(requestId, employee, startDate, endDate, "Vacation Leave");
		this.isPlanned = isPlanned;
	}

	public boolean isPlanned() {
		return isPlanned;
	}
    
    @Override
	public boolean processRequest() {
		System.out.println("Processing vacation leave request...");
        if (!isPlanned && calculateLeaveDays() > 1) {
             System.out.println("Unplanned vacation leave > 1 day might require manager review.");
        }
		return true;
	}

	@Override
	public int calculateLeaveDays() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate start = LocalDate.parse(getStartDate(), formatter);
		LocalDate end = LocalDate.parse(getEndDate(), formatter);

		if (end.isBefore(start)) {
			throw new IllegalArgumentException("End date cannot be before start date");
		}
		return (int) ChronoUnit.DAYS.between(start, end) + 1;
	}

	@Override
	public boolean approve(String approverName) {
		changeStatus("Approved", approverName);
		System.out.println(
				"Vacation leave request " + getRequestId() + " approved by " + approverName + " for " + getEmployee().getName());
		return true;
	}

	@Override
	public boolean deny(String approverName, String reason) {
		changeStatus("Denied", approverName);
		System.out.println("Vacation leave request " + getRequestId() + " denied by " + approverName + " for "
				+ getEmployee().getName() + ". Reason: " + reason);
		return true;
	}
}
