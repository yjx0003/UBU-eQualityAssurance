package es.ubu.lsi.equalityassurance.util;

import java.io.IOException;
import java.net.URL;

import es.ubu.lsi.equalityassurance.AppInfo;
import es.ubu.lsi.equalityassurance.controller.configuration.ConfigHelper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class UtilFXML {
	public static void changeScene(URL url, Stage stage) {
		changeScene(url, stage, null, true);
	}

	public static void changeScene(URL url, Stage stage, Object fxmlController) {
		changeScene(url, stage, fxmlController, true);
	}

	public static void changeScene(URL url, Stage stage, boolean showStage) {
		changeScene(url, stage, null, showStage);
	}

	/**
	 * Permite cambiar la ventana actual.
	 * 
	 * @param sceneFXML La ventanan a la que se quiere cambiar.
	 *
	 * @throws IOException
	 */
	public static void changeScene(URL sceneFXML, Stage stage, Object fxmlController, boolean showStage) {

		// Accedemos a la siguiente ventana
		FXMLLoader loader = new FXMLLoader(sceneFXML, I18n.getResourceBundle());
		if (fxmlController != null) {
			loader.setController(fxmlController);
		}

		try {

			Parent root = loader.load();
			stage.close();

			stage.setScene(new Scene(root));

			if (showStage) {
				stage.show();
			}
		} catch (IOException e) {
			UtilAlert.errorWindow("error loading fxml: " + sceneFXML, e);
			throw new IllegalArgumentException("Invalid fxml", e);
		}

	}
	
	public static Stage createDialog(FXMLLoader loader, Stage ownerStage, Modality modality) {

		Scene newScene;
		try {
			newScene = new Scene(loader.load());
		} catch (IOException ex) {
			UtilAlert.errorWindow("FXML file corrupted", ex);
			return null;
		}
		Style.addStyle(ConfigHelper.getProperty("style"), newScene.getStylesheets());

		Stage stage = createStage(ownerStage, modality);
		stage.setScene(newScene);
		stage.setResizable(false);

		stage.show();
		return stage;
	}

	public static void createDialog(FXMLLoader loader, Stage ownerStage) {

		createDialog(loader, ownerStage, Modality.WINDOW_MODAL);
	}
	
	public static Stage createStage(Window owner, Modality modality) {
		Stage stage = new Stage();

		stage.initOwner(owner);
		stage.initModality(modality);

		stage.getIcons()
				.add(new Image("/img/logo_min.png"));
		stage.setTitle(AppInfo.APPLICATION_NAME_WITH_VERSION);
		return stage;
	}
}
