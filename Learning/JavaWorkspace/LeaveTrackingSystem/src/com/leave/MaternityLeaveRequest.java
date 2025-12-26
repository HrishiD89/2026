package com.leave;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class MaternityLeaveRequest extends LeaveRequest {

	public MaternityLeaveRequest(int requestId, Employee employee, String startDate, String endDate) {
		super(requestId, employee, startDate, endDate, "Maternity Leave");
	}

	@Override
	public boolean processRequest() {
		System.out.println("Processing maternity leave request...");
        // Add specific checks if needed, e.g., duration limits
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
				"Maternity leave request " + getRequestId() + " approved by " + approverName + " for " + getEmployee().getName());
		return true;
	}

	@Override
	public boolean deny(String approverName, String reason) {
		changeStatus("Denied", approverName);
		System.out.println("Maternity leave request " + getRequestId() + " denied by " + approverName + " for "
				+ getEmployee().getName() + ". Reason: " + reason);
		return true;
	}

}
