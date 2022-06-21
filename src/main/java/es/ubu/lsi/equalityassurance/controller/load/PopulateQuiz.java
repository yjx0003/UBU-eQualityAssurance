package es.ubu.lsi.equalityassurance.controller.load;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.Quiz;
import es.ubu.lsi.moodlerestapi.webservice.api.mod.quiz.ModQuizGetQuizzesByCourses;
import es.ubu.lsi.moodlerestapi.webservice.util.UtilResponse;
import es.ubu.lsi.moodlerestapi.webservice.webservices.WebService;

public class PopulateQuiz {

	private DataBase dataBase;
	private WebService webService;

	public PopulateQuiz(DataBase dataBase, WebService webService) {
		this.dataBase = dataBase;
		this.webService = webService;
	}

	public List<Quiz> populateQuiz(int courseid) {
		try {
			
			JSONObject jsonObject = UtilResponse.getJSONObjectResponse(webService, new ModQuizGetQuizzesByCourses(courseid));
			return poppulateQuiz(jsonObject);
		
		} catch (IOException e) {
			return Collections.emptyList();
		}
	}

	public List<Quiz> poppulateQuiz(JSONObject jsonObject) {
		List<Quiz> list = new ArrayList<>();
		JSONArray quizzes = jsonObject.getJSONArray("quizzes");
		for(int i = 0; i<quizzes.length(); i++) {
			JSONObject quizJSONObject = quizzes.getJSONObject(i);
			Quiz quiz = dataBase.getQuizzes().getById(quizJSONObject.getInt("id"));
			list.add(quiz);
			quiz.setCourse(dataBase.getCourses().getById(quizJSONObject.getInt("course")));
			quiz.setCourseModule(dataBase.getModules().getById(quizJSONObject.getInt("coursemodule")));
			quiz.setName(quizJSONObject.getString("name"));
			quiz.setIntro(quizJSONObject.getString("intro"));
			List<String> introfiles = new ArrayList<>();
			quiz.setIntrofiles(introfiles);
			JSONArray introfilesArray = quizJSONObject.getJSONArray("introfiles");
			for(int j = 0; j<introfilesArray.length(); j++) {
				JSONObject introfile = introfilesArray.getJSONObject(j);
				introfiles.add(introfile.getString("filename"));
			}
			quiz.setTimeopen(Instant.ofEpochSecond(quizJSONObject.getLong("timeopen")));
			quiz.setTimeclose(Instant.ofEpochSecond(quizJSONObject.getLong("timeclose")));
			quiz.setTimelimit(quizJSONObject.getInt("timelimit"));
			quiz.setPreferredbehaviour(quizJSONObject.getString("preferredbehaviour"));
			quiz.setAttempts(quizJSONObject.getInt("attempts"));
			quiz.setGrademethod(quizJSONObject.getInt("grademethod"));
			quiz.setDecimalpoints(quizJSONObject.getInt("decimalpoints"));
			quiz.setQuestiondecimalpoints(quizJSONObject.getInt("questiondecimalpoints"));
			quiz.setShuffleanswers(quizJSONObject.getInt("shuffleanswers") == 1);
			quiz.setSumgrades(quizJSONObject.getInt("sumgrades") == 1);
			quiz.setGrade(quizJSONObject.getDouble("grade"));
			quiz.setTimecreated(Instant.ofEpochSecond(quizJSONObject.getLong("timecreated")));
			quiz.setTimemodified(Instant.ofEpochSecond(quizJSONObject.getLong("timemodified")));
			quiz.setPassword(quizJSONObject.getString("password"));
			quiz.setSubnet(quizJSONObject.getString("subnet"));
			quiz.setHasfeedback(quizJSONObject.getInt("hasfeedback") == 1);
			quiz.setVisible(quizJSONObject.getInt("visible") == 1);
			quiz.setGroupmode(quizJSONObject.getInt("groupmode") == 1);
			quiz.setGroupingid(quizJSONObject.getInt("groupingid"));
			
			
			
		}
		return list;
	}
}
