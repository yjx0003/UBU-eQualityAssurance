package es.ubu.lsi.equalityassurance.controller.fxml;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import es.ubu.lsi.equalityassurance.controller.Controller;
import es.ubu.lsi.equalityassurance.model.Course;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.Getter;

@Getter
public class MainController implements Initializable{

	@FXML
	private SelectionCacheController selectionCacheController;
	
	@FXML
	private SelectionCourseController selectionCourseController;
	
	@FXML
	private UpperElementsController upperElementsController;
	
	@FXML
	private WebViewContentController webViewContentController;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Course> courses = Controller.getInstance().getDataBase().getSiteInfo().getCoursesEnrolled();
		selectionCourseController.init(this, courses);
		upperElementsController.init(this);
		selectionCacheController.init(this);
		webViewContentController.init(this);
	}
	
}
