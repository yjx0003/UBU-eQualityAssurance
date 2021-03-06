package es.ubu.lsi.equalityassurance.controller.fxml;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import es.ubu.lsi.equalityassurance.controller.Controller;
import es.ubu.lsi.equalityassurance.controller.load.PopulateAssignment;
import es.ubu.lsi.equalityassurance.controller.load.PopulateCourseBlocks;
import es.ubu.lsi.equalityassurance.controller.load.PopulateCourseContent;
import es.ubu.lsi.equalityassurance.controller.load.PopulateEnrolledUsersCourse;
import es.ubu.lsi.equalityassurance.controller.load.PopulateForum;
import es.ubu.lsi.equalityassurance.controller.load.PopulateGradeItem;
import es.ubu.lsi.equalityassurance.controller.load.PopulateQuiz;
import es.ubu.lsi.equalityassurance.controller.load.PopulateResource;
import es.ubu.lsi.equalityassurance.model.Assignment;
import es.ubu.lsi.equalityassurance.model.Course;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.ForumDiscussion;
import es.ubu.lsi.equalityassurance.model.ModuleType;
import es.ubu.lsi.equalityassurance.util.I18n;
import es.ubu.lsi.equalityassurance.util.Serialization;
import es.ubu.lsi.equalityassurance.util.UtilAlert;
import es.ubu.lsi.equalityassurance.util.UtilString;
import es.ubu.lsi.moodlerestapi.webservice.webservices.WebService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Cursor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpperElementsController {

	private MainController mainController;

	public void init(MainController mainController) {
		this.mainController = mainController;
	}

	public void execute() {
		Course actualCourse = mainController.getSelectionCourseController()
				.getSelectedCourse();
		Service<DataBase> service = new Service<DataBase>() {
			@Override
			protected Task<DataBase> createTask() {
				return new Task<DataBase>() {
					@Override
					protected DataBase call() throws Exception {
						return loadData();
					}
				};
			}
		};
		service.setOnSucceeded(v -> {
			DataBase dataBase = service.getValue();

			Controller controller = Controller.getInstance();
			Path courseDir = controller.getHostUserDir()
					.resolve(UtilString.removeReservedChar(actualCourse.getFullName()) + " - " + actualCourse.getId());
			String route = courseDir.resolve(UtilString.removeReservedChar(LocalDateTime.now()
					.toString()))
					.toString();

			if (!courseDir.toFile()
					.isDirectory()) {
				courseDir.toFile()
						.mkdirs();
			}

			Serialization.encrypt(controller.getPassword(), route, dataBase);
			mainController.getSelectionCacheController()
					.updateListView(courseDir.toFile());
			Controller.getInstance().getStage().getScene().setCursor(Cursor.DEFAULT);
		});
		service.setOnFailed(v -> {
			UtilAlert.warningWindow(v.getSource()
					.getException()
					.getMessage());
			Controller.getInstance().getStage().getScene().setCursor(Cursor.DEFAULT);
		});
		service.start();
		Controller.getInstance().getStage().getScene().setCursor(Cursor.WAIT);
	}

	private DataBase loadData() {
		Course actualCourse = mainController.getSelectionCourseController()
				.getSelectedCourse();

		if (actualCourse == null) {
			throw new IllegalArgumentException(I18n.get("warn.selectCourse"));
		}
		DataBase dataBase = populate(actualCourse);

		return dataBase;

	}

	private DataBase populate(Course actualCourse) {
		DataBase dataBase = new DataBase();

		dataBase.setActualCourse(actualCourse);

		Controller controller = Controller.getInstance();
		WebService webService = controller.getWebService();

		PopulateEnrolledUsersCourse populateEnrolledUsersCourse = new PopulateEnrolledUsersCourse(dataBase, webService);
		populateEnrolledUsersCourse.createEnrolledUsers(actualCourse.getId());
//		PopulateActivityCompletion populateActivityCompletion = new PopulateActivityCompletion(dataBase,
//				webService);
//		populateActivityCompletion.creeateActivitiesCompletionStatus(actualCourse.getId(),
//				enrolledUsers);
		PopulateGradeItem populateGradeItem = new PopulateGradeItem(dataBase, webService);
		try {
			populateGradeItem.createGradeItems(actualCourse.getId(), controller.getDataBase().getSiteInfo().getUserid());
		} catch (IOException e) {
			log.warn("Fallo al cargar los items de calificacion");
		}
		PopulateCourseBlocks populateCourseBlocks = new PopulateCourseBlocks(dataBase, webService);
		populateCourseBlocks.populateCourseBlocks(actualCourse.getId());
		PopulateCourseContent populateCourseContent = new PopulateCourseContent(webService, dataBase);
		populateCourseContent.populateCourseContent(actualCourse.getId());
		PopulateQuiz populateQuiz = new PopulateQuiz(dataBase, webService);
		populateQuiz.populateQuiz(actualCourse.getId());
		PopulateAssignment populateAssignment = new PopulateAssignment(dataBase, webService);
		List<Assignment> assignments = populateAssignment.populateAssignment(actualCourse.getId());
		populateAssignment.populateSubmissions(assignments.stream()
				.map(Assignment::getId)
				.collect(Collectors.toList()));
		PopulateResource populateResource = new PopulateResource(dataBase, webService);
		populateResource.populateResource(actualCourse.getId());
		PopulateForum populateForum = new PopulateForum(dataBase, webService);
		populateForum.populateForum(actualCourse.getId());
		List<ForumDiscussion> forumDiscussions = populateForum.populateForumDiscussions(dataBase.getModules()
				.getValues()
				.stream()
				.filter(cm -> cm.getModuleType() == ModuleType.FORUM)
				.collect(Collectors.toList()));

		populateForum.populateDiscussionPosts(forumDiscussions.stream()
				.map(ForumDiscussion::getId)
				.collect(Collectors.toList()));
		return dataBase;
	}
}
