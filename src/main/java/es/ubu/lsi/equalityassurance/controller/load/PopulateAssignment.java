package es.ubu.lsi.equalityassurance.controller.load;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import es.ubu.lsi.equalityassurance.model.Assignment;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.Submission;
import es.ubu.lsi.moodlerestapi.webservice.api.mod.assign.ModAssignGetAssigments;
import es.ubu.lsi.moodlerestapi.webservice.api.mod.assign.ModAssignGetSubmissions;
import es.ubu.lsi.moodlerestapi.webservice.util.UtilResponse;
import es.ubu.lsi.moodlerestapi.webservice.webservices.WebService;

public class PopulateAssignment {

	private DataBase dataBase;
	private WebService webService;

	public PopulateAssignment(DataBase dataBase, WebService webService) {
		this.dataBase = dataBase;
		this.webService = webService;
	}

	public List<Assignment> populateAssignment(int courseid) {
		try {
			ModAssignGetAssigments modAssignGetAssigments = new ModAssignGetAssigments();
			modAssignGetAssigments.setCourseid(courseid);
			JSONObject jsonObject = UtilResponse.getJSONObjectResponse(webService, modAssignGetAssigments);
			return populateAssignment(jsonObject);

		} catch (IOException e) {
			return Collections.emptyList();
		}
	}

	public List<Assignment> populateAssignment(JSONObject jsonObject) {
		List<Assignment> assignments = new ArrayList<>();
		JSONArray courses = jsonObject.getJSONArray("courses");
		JSONObject course = courses.getJSONObject(0);
		JSONArray assignmentsArray = course.getJSONArray("assignments");
		for (int i = 0; i < assignmentsArray.length(); i++) {
			JSONObject assign = assignmentsArray.getJSONObject(i);
			Assignment assignment = dataBase.getAssignments()
					.getById(assign.getInt("id"));
			assignments.add(assignment);
			assignment.setCourseModule(dataBase.getModules()
					.getById(assign.getInt("cmid")));
			assignment.setName(assign.getString("name"));
			assignment.setNosubmissions(assign.getInt("nosubmissions"));
			assignment.setSubmissiondrafts(assign.getInt("submissiondrafts"));
			assignment.setSendnotifications(assign.getInt("sendnotifications") == 1);
			assignment.setSendlatenotifications(assign.getInt("sendlatenotifications") == 1);
			assignment.setSendstudentnotifications(assign.getInt("sendstudentnotifications") == 1);
			assignment.setDuedate(Instant.ofEpochSecond(assign.getLong("duedate")));
			assignment.setAllowsubmissionsfromdate(Instant.ofEpochSecond(assign.getLong("allowsubmissionsfromdate")));
			assignment.setGrade(assign.getDouble("grade"));
			assignment.setTimemodified(Instant.ofEpochSecond(assign.getLong("timemodified")));
			assignment.setCompletionsubmit(assign.getInt("completionsubmit") == 1);
			assignment.setCutoffdate(Instant.ofEpochSecond(assign.getLong("cutoffdate")));
			assignment.setGradingduedate(Instant.ofEpochSecond(assign.getLong("gradingduedate")));
			assignment.setTeamsubmission(assign.getInt("teamsubmission") == 1);

		}

		return assignments;
	}
	
	public List<Submission> populateSubmissions(Collection<Integer> assignmentids){
		try {
			ModAssignGetSubmissions modAssignGetSubmissions = new ModAssignGetSubmissions();
			modAssignGetSubmissions.setAssignmentIds(assignmentids);
			JSONObject jsonObject = UtilResponse.getJSONObjectResponse(webService, modAssignGetSubmissions);
			return populateSubmission(jsonObject);

		} catch (IOException e) {
			return Collections.emptyList();
		}
	}

	public List<Submission> populateSubmission(JSONObject jsonObject) {
		List<Submission> list = new ArrayList<>();
		JSONArray assignments = jsonObject.getJSONArray("assignments");
		for(int i = 0; i< assignments.length(); i++) {
			JSONObject assignmentObject = assignments.getJSONObject(i);
			Assignment assignment = dataBase.getAssignments().getById(assignmentObject.getInt("assignmentid"));
			JSONArray submissions = assignmentObject.getJSONArray("submissions");
			for(int j = 0; j<submissions.length(); ++j) {
				JSONObject submissionJSON =  submissions.getJSONObject(j);
				Submission submission = dataBase.getSubmissions().getById(submissionJSON.getInt("id"));
				list.add(submission);
				submission.setAssignment(assignment);
				submission.setUser(dataBase.getUsers().getById(submissionJSON.getInt("userid")));
				submission.setTimecreated(Instant.ofEpochSecond(submissionJSON.getLong("timecreated")));
				submission.setTimemodified(Instant.ofEpochSecond(submissionJSON.getLong("timemodified")));
				submission.setStatus(submissionJSON.getString("status"));
				submission.setGroupid(submissionJSON.getInt("groupid"));
				submission.setGradingstatus(submissionJSON.getString("gradingstatus"));
			
			}
		}
		return list;
	}
}
