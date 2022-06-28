package es.ubu.lsi.equalityassurance.controller.load;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		
		} catch (Exception e) {
			log.warn("Fallo en populate Quiz", e);
			return Collections.emptyList();
		}
	}

	public List<Quiz> poppulateQuiz(JSONObject jsonObject) {
		List<Quiz> list = new ArrayList<>();
		JSONArray quizzes = jsonObject.getJSONArray("quizzes");
		for(int i = 0; i<quizzes.length(); i++) {
			JSONObject quizJSONObject = quizzes.getJSONObject(i);
			Quiz quiz = dataBase.getQuizzes().getById(quizJSONObject.optInt("id"));
			list.add(quiz);
			quiz.setCourse(dataBase.getCourses().getById(quizJSONObject.optInt("course")));
			quiz.setCourseModule(dataBase.getModules().getById(quizJSONObject.optInt("coursemodule")));
			quiz.setName(quizJSONObject.optString("name"));
			quiz.setIntro(quizJSONObject.optString("intro"));
			List<String> introfiles = new ArrayList<>();
			quiz.setIntrofiles(introfiles);
			JSONArray introfilesArray = quizJSONObject.getJSONArray("introfiles");
			for(int j = 0; j<introfilesArray.length(); j++) {
				JSONObject introfile = introfilesArray.getJSONObject(j);
				introfiles.add(introfile.optString("filename"));
			}
			quiz.setTimeopen(Instant.ofEpochSecond(quizJSONObject.optLong("timeopen")));
			quiz.setTimeclose(Instant.ofEpochSecond(quizJSONObject.optLong("timeclose")));
			quiz.setTimelimit(quizJSONObject.optInt("timelimit"));
			quiz.setPreferredbehaviour(quizJSONObject.optString("preferredbehaviour"));
			quiz.setAttempts(quizJSONObject.optInt("attempts"));
			quiz.setGrademethod(quizJSONObject.optInt("grademethod"));
			quiz.setDecimalpoints(quizJSONObject.optInt("decimalpoints"));
			quiz.setQuestiondecimalpoints(quizJSONObject.optInt("questiondecimalpoints"));
			quiz.setShuffleanswers(quizJSONObject.optInt("shuffleanswers") == 1);
			quiz.setSumgrades(quizJSONObject.optInt("sumgrades") == 1);
			quiz.setGrade(quizJSONObject.optDouble("grade"));
			quiz.setTimecreated(Instant.ofEpochSecond(quizJSONObject.optLong("timecreated")));
			quiz.setTimemodified(Instant.ofEpochSecond(quizJSONObject.optLong("timemodified")));
			quiz.setPassword(quizJSONObject.optString("password"));
			quiz.setSubnet(quizJSONObject.optString("subnet"));
			quiz.setHasfeedback(quizJSONObject.optInt("hasfeedback") == 1);
			quiz.setVisible(quizJSONObject.optInt("visible") == 1);
			quiz.setGroupmode(quizJSONObject.optInt("groupmode") == 1);
			quiz.setGroupingid(quizJSONObject.optInt("groupingid"));
			
			
			
		}
		return list;
	}
}
