package com.leave;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class SickLeaveRequest extends LeaveRequest {
	private boolean medicalCertificateProvided;

	public SickLeaveRequest(int requestId, Employee employee, String startDate, String endDate,
			boolean medicalCertificateProvided) {
		super(requestId, employee, startDate, endDate, "Sick Leave");
		this.medicalCertificateProvided = medicalCertificateProvided;
	}

	// Additional methods specific to sick leave
	public boolean isMedicalCertificateProvided() {
		return medicalCertificateProvided;
	}

	@Override
	public boolean processRequest() {
		if (!medicalCertificateProvided && calculateLeaveDays() > 2) {
			System.out.println("Sick leave longer than 2 days requires a medical certificate");
			return false;
		}
		System.out.println("Processing sick leave request...");
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
		return (int) ChronoUnit.DAYS.between(start, end) + 1; // Including both start and end date
	}

	@Override
	public boolean approve(String approverName) {
		changeStatus("Approved", approverName);
		System.out.println(
				"Leave request " + getRequestId() + " approved by " + approverName + " for " + getEmployee().getName());
		return true;
	}

	@Override
	public boolean deny(String approverName, String reason) {
		changeStatus("Denied", approverName);
		System.out.println("Leave request " + getRequestId() + " denied by " + approverName + " for "
				+ getEmployee().getName() + ". Reason: " + reason);
		return true;
	}

}
