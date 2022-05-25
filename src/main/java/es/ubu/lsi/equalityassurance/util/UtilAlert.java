package es.ubu.lsi.equalityassurance.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import es.ubu.lsi.equalityassurance.AppInfo;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UtilAlert {
	/**
	 * Muestra una ventana de error.
	 * 
	 * @param mensaje El mensaje que se quiere mostrar.
	 * @return
	 */
	public static ButtonType errorWindow(String contentText) {
		return dialogWindow(AlertType.ERROR, I18n.get("error"), contentText);
	}

	/**
	 * Muestra una ventana de información.
	 * 
	 * @param message El mensaje que se quiere mostrar.
	 * @param exit    Indica si se quiere mostar el boton de salir o no.
	 * @return
	 */
	public static ButtonType infoWindow(String contentText) {

		return dialogWindow(AlertType.INFORMATION, I18n.get("information"), contentText);
	}

	public static ButtonType confirmationWindow(String contentText) {
		return dialogWindow(AlertType.CONFIRMATION, I18n.get("confirmation"), contentText);
	}

	public static ButtonType warningWindow(String contentText) {
		return dialogWindow(AlertType.WARNING, I18n.get("warning"), contentText);
	}

	public static void errorWindow(String contentText, Throwable ex) {

		Alert alert = createAlert(AlertType.ERROR);

		Text text = new Text(contentText);

		TextFlow flow = new TextFlow(text);
		flow.setPrefWidth(600);

		alert.getDialogPane()
				.setContent(flow);

	

		alert.getButtonTypes()
				.setAll(ButtonType.OK);

		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);

		Label label = new Label(I18n.get("exceptionTrace"));

		TextArea textArea = new TextArea(sw.toString());
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane()
				.setExpandableContent(expContent);
		

		alert.showAndWait();

	}

	public static ButtonType dialogWindow(AlertType alertType, String headerText, String contentText) {
		Alert alert = createAlert(alertType, headerText, contentText);
		alert.showAndWait();
		return alert.getResult();
	}

	public static Alert createAlert(AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(AppInfo.APPLICATION_NAME_WITH_VERSION);
		alert.initModality(Modality.APPLICATION_MODAL);
		Stage stageAlert = (Stage) alert.getDialogPane()
				.getScene()
				.getWindow();
		stageAlert.getIcons()
				.add(new Image("/img/logo_min.png"));
		return alert;
	}
	public static Alert createAlert(AlertType alertType, String headerText, String contentText) {
		Alert alert = createAlert(alertType);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		return alert;
	}
}
