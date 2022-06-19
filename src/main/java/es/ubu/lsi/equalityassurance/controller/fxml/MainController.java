package es.ubu.lsi.equalityassurance.controller.fxml;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.StatusBar;

import es.ubu.lsi.equalityassurance.controller.Controller;
import es.ubu.lsi.equalityassurance.model.Course;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import lombok.Getter;

@Getter
public class MainController implements Initializable {

	@FXML
	private SelectionCacheController selectionCacheController;

	@FXML
	private SelectionCourseController selectionCourseController;

	@FXML
	private UpperElementsController upperElementsController;

	@FXML
	private WebViewContentController webViewContentController;

	@FXML
	private StatusBar statusBar;

	private Hyperlink actualCourseHyperLink;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Course> courses = Controller.getInstance()
				.getDataBase()
				.getSiteInfo()
				.getCoursesEnrolled();
		selectionCourseController.init(this, courses);
		upperElementsController.init(this);
		selectionCacheController.init(this);
		webViewContentController.init(this);

		statusBarInit();
	}

	private void statusBarInit() {
		// Mostramos Host actual
		Controller controller = Controller.getInstance();
		actualCourseHyperLink = new Hyperlink();
		
		
		Label lblRelease = new Label("Moodle " + controller.getDataBase()
				.getSiteInfo()
				.getRelease());

		HBox left = new HBox();
		left.setAlignment(Pos.CENTER);
		left.setSpacing(5);
		left.getChildren()
				.addAll(actualCourseHyperLink, new Separator(Orientation.VERTICAL), lblRelease);

		statusBar.getLeftItems()
				.add(left);

	}

}
