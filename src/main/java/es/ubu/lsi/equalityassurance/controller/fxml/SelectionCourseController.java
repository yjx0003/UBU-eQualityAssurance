package es.ubu.lsi.equalityassurance.controller.fxml;

import java.io.File;
import java.util.Comparator;
import java.util.List;

import es.ubu.lsi.equalityassurance.controller.Controller;
import es.ubu.lsi.equalityassurance.model.Course;
import es.ubu.lsi.equalityassurance.util.UtilString;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SelectionCourseController {

	@FXML
	private ListView<Course> listViewCourses;

	@FXML
	private TextField textFieldSearch;

	public void init(MainController mainController, List<Course> courses) {
		FilteredList<Course> filteredList = listViewCourses(courses);
		onSelectionItem(mainController);
		textFieldSearch(filteredList);

		setCellFactory();
	}
	
	public Course getSelectedCourse() {
		return listViewCourses.getSelectionModel().getSelectedItem();
	}

	private FilteredList<Course> listViewCourses(List<Course> courses) {

		

		FilteredList<Course> filteredList = new FilteredList<>(FXCollections.observableArrayList(courses));
		filteredList.sorted(Comparator.comparing(Course::getFullName));
		listViewCourses.setItems(filteredList);
		return filteredList;
	}

	private void onSelectionItem(MainController mainController) {
		listViewCourses.getSelectionModel()
				.selectedItemProperty()
				.addListener((ov, old, newValue) -> {
					if (newValue != null) {
						File courseDir = Controller.getInstance()
								.getHostUserDir()
								.resolve(UtilString.removeReservedChar(newValue.getFullName()) + " - "
										+ newValue.getId())
								.toFile();
						mainController.getSelectionCacheController()
								.updateListView(courseDir);
					}
				});
	}

	private void textFieldSearch(FilteredList<Course> filteredList) {
		textFieldSearch.textProperty()
				.addListener(((observable, oldValue, newValue) -> {
					filteredList.setPredicate(course -> {
						if (newValue == null || newValue.isEmpty()) {
							return true;
						}
						String lowerCaseSearch = newValue.toLowerCase();
						return course.toString()
								.contains(lowerCaseSearch);
					});
				}));
	}

	private void setCellFactory() {
		listViewCourses.setCellFactory(param -> new ListCell<Course>() {
			@Override
			protected void updateItem(Course item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setGraphic(null);
					setText(null);
					// other stuff to do...

				} else {

					// set the width's
					setMinWidth(param.getWidth());
					setMaxWidth(param.getWidth());
					setPrefWidth(param.getWidth());

					// allow wrapping
					setWrapText(true);

					setText(item.toString());

				}
			}
		});
	}

}
