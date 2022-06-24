package es.ubu.lsi.equalityassurance.controller.rules.ubucev.assignment;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.Assignment;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class AssignmentTimeRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		Collection<Assignment> assignments = dataBase.getAssignments()
				.getValues();
		Instant start = dataBase.getActualCourse()
				.getStartDate();
		Instant end = dataBase.getActualCourse()
				.getEndDate();
		for (Assignment assignment : assignments) {
			Instant allowSubmissions = assignment.getAllowsubmissionsfromdate();
			Instant cutoffdate = assignment.getCutoffdate();
			if (allowSubmissions.getEpochSecond() != 0L && allowSubmissions.isBefore(start)
					|| allowSubmissions.isAfter(end)
					|| allowSubmissions.getEpochSecond() != 0L && cutoffdate.isBefore(start)
					|| cutoffdate.isAfter(end)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public List<Object> reasonFailPopup(DataBase dataBase) {
		List<Object> fails = new ArrayList<>();
		Collection<Assignment> assignments = dataBase.getAssignments()
				.getValues();
		Instant start = dataBase.getActualCourse()
				.getStartDate();
		Instant end = dataBase.getActualCourse()
				.getEndDate();
		for (Assignment assignment : assignments) {
			Instant allowSubmissions = assignment.getAllowsubmissionsfromdate();
			Instant cutoffdate = assignment.getCutoffdate();
			if (allowSubmissions.getEpochSecond() != 0L && allowSubmissions.isBefore(start)
					|| allowSubmissions.isAfter(end)
					|| allowSubmissions.getEpochSecond() != 0L && cutoffdate.isBefore(start)
					|| cutoffdate.isAfter(end)) {
				fails.add(assignment.getName());
			}
		}
		return fails;
	}

}
