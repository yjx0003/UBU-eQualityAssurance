package es.ubu.lsi.equalityassurance.controller.fxml;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ubu.lsi.equalityassurance.AppInfo;
import es.ubu.lsi.equalityassurance.controller.Controller;
import es.ubu.lsi.equalityassurance.controller.configuration.ConfigHelper;
import es.ubu.lsi.equalityassurance.util.Style;
import es.ubu.lsi.equalityassurance.util.UtilFXML;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Clase Loader. Inicializa la ventana de login
 * 
 *
 */
public class Loader extends Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Loader.class);
	private Controller controller = Controller.getInstance();

	@Override
	public void start(Stage primaryStage) {

		try {
			Thread.currentThread().setUncaughtExceptionHandler((thread, throwable) -> {
				LOGGER.error("Error no catcheado: {}", thread, throwable);
	        });			
			controller.initialize();
			
			LOGGER.info("[Bienvenido a {}]", AppInfo.APPLICATION_NAME_WITH_VERSION);
			
			hackTooltipStartTiming();
			
			primaryStage.getIcons()
					.add(new Image("/img/logo_min.png"));
			primaryStage.setTitle(AppInfo.APPLICATION_NAME_WITH_VERSION);
			
			UtilFXML.changeScene(getClass().getResource("/view/Login.fxml"), primaryStage);
			Style.addStyle(ConfigHelper.getProperty("style"), primaryStage.getScene()
					.getStylesheets());
			controller.setStage(primaryStage);

		} catch (Exception e) {
			LOGGER.error("Error al iniciar controller: {}", e);
		}
	}

	

	@Override
	public void stop() {

		ConfigHelper.save();
	}
	
	/**
	 * https://stackoverflow.com/a/27739605
	 */
	private static void hackTooltipStartTiming() {
	    try {
	        Field fieldBehavior = Tooltip.class.getDeclaredField("BEHAVIOR");
	        fieldBehavior.setAccessible(true);
	        Object objBehavior = fieldBehavior.get(new Tooltip());

	        Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
	        fieldTimer.setAccessible(true);
	        Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

	        objTimer.getKeyFrames().clear();
	        objTimer.getKeyFrames().add(new KeyFrame(new Duration(250)));
	    } catch (Exception e) {
	       LOGGER.error("Cannot set tooltip delay", e);
	    }
	}
	

	public static void initialize() {
		launch();
	}
}