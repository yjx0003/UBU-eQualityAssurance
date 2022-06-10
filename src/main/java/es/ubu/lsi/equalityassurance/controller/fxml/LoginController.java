package es.ubu.lsi.equalityassurance.controller.fxml;

import java.io.IOException;
import java.net.URL;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.controlsfx.control.textfield.TextFields;
import org.json.JSONException;

import es.ubu.lsi.equalityassurance.AppInfo;
import es.ubu.lsi.equalityassurance.controller.Controller;
import es.ubu.lsi.equalityassurance.controller.configuration.ConfigHelper;
import es.ubu.lsi.equalityassurance.controller.load.Login;
import es.ubu.lsi.equalityassurance.controller.load.PopulateCourse;
import es.ubu.lsi.equalityassurance.controller.load.PopulateCourseCategories;
import es.ubu.lsi.equalityassurance.controller.load.PopulateSiteInfo;
import es.ubu.lsi.equalityassurance.model.Course;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.SiteInfo;
import es.ubu.lsi.equalityassurance.util.I18n;
import es.ubu.lsi.equalityassurance.util.Language;
import es.ubu.lsi.equalityassurance.util.UtilAlert;
import es.ubu.lsi.equalityassurance.util.UtilFXML;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase controlador de la ventana de Login
 * 
 *
 */
@Slf4j
public class LoginController implements Initializable {

	private static final String HOSTS = "hosts";

	private static final String USERNAMES = "usernames";
	private Controller controller = Controller.getInstance();

	@FXML
	private VBox vBox;

	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private TextField txtHost;

	@FXML
	private ComboBox<Language> languageSelector;

	/**
	 * Crea el selector de idioma.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initializeProperties();

		Platform.runLater(() -> {
			if (!Optional.ofNullable(txtUsername.getText())
					.orElse("")
					.isEmpty()) {

				txtPassword.requestFocus(); // si hay texto cargado del usuario cambiamos el focus al texto de
											// password
			}
		});

		initLanguagesList();
		controller.setDataBase(new DataBase());

	}

	/**
	 * Initialize languages list choice box from Languages enum class.
	 */
	private void initLanguagesList() {

		Callback<ListView<Language>, ListCell<Language>> listCell = callback -> new ListCell<Language>() {
			@Override
			protected void updateItem(Language item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty) {
					setGraphic(null);
					setText(null);
				} else {
					setText(item.getDisplayLanguage());
					try {
						Image countryImage = new Image(AppInfo.IMG_FLAGS + item.getFlag() + ".png");
						ImageView imageView = new ImageView(countryImage);
						setGraphic(imageView);
					} catch (Exception e) {
						log.warn("No disponible la foto de bandera para: {}", item.getFlag());
						setGraphic(null);
					}

				}
			}
		};
		languageSelector.setCellFactory(listCell);
		languageSelector.setButtonCell(listCell.call(null));

		ObservableList<Language> languages = FXCollections.observableArrayList(Language.values());
		languages.sort(Comparator.comparing(Language::getDisplayLanguage, Collator.getInstance()));
		languageSelector.setItems(languages);
		languageSelector.setValue(controller.getSelectedLanguage());

		// Carga la interfaz con el idioma seleccionado
		languageSelector.getSelectionModel()
				.selectedItemProperty()
				.addListener((ov, value, newValue) -> {

					controller.setSelectedLanguage(newValue);
					log.info("Idioma de la aplicación: {}", newValue);
					log.info("Idioma cargado del resource bundle: {}", I18n.getResourceBundle()
							.getLocale());
					log.info("[Bienvenido a " + AppInfo.APPLICATION_NAME_WITH_VERSION + "]");
					UtilFXML.changeScene(getClass().getResource("/view/Login.fxml"), controller.getStage());
				});
		log.debug("Selector de idiomas configurado");
	}

	/**
	 * Inicializa el fichero properties con el nombre de usuario y host
	 * 
	 * @throws IOException
	 */
	private void initializeProperties() {

		txtHost.setText(ConfigHelper.getProperty("host", ""));

		txtUsername.setText(ConfigHelper.getProperty("username", ""));
		txtPassword.setText(System.getProperty("app.password", ""));

		TextFields.bindAutoCompletion(txtUsername, ConfigHelper.getArray(USERNAMES)
				.toList())
				.setDelay(0);
		TextFields.bindAutoCompletion(txtHost, ConfigHelper.getArray(HOSTS)
				.toList())
				.setDelay(0);

	}

	/**
	 * Guarda las opciones del usuario en el fichero properties
	 */
	private void saveProperties() {

		String username = txtUsername.getText();
		ConfigHelper.setProperty("username", username);

		String host = txtHost.getText();
		ConfigHelper.setProperty("host", host);

		List<Object> arrayUsername = ConfigHelper.getArray(USERNAMES)
				.toList();
		if (!arrayUsername.contains(username)) {

			ConfigHelper.appendArray(USERNAMES, username);
		}

		List<Object> arrayHosts = ConfigHelper.getArray(HOSTS)
				.toList();
		if (!arrayHosts.contains(host)) {

			ConfigHelper.appendArray(HOSTS, host);
		}

	}

	public void login() {
		if (txtHost.getText()
				.trim()
				.isEmpty()
				|| txtPassword.getText()
						.trim()
						.isEmpty()
				|| txtUsername.getText()
						.trim()
						.isEmpty()) {
			UtilAlert.infoWindow(I18n.get("error.fields"));
			return;
		}
		controller.getStage()
				.getScene()
				.setCursor(Cursor.WAIT);

		onlineMode();

	}

	private void onlineMode() {
		Service<Void> service = new Service<Void>() {

			@Override
			protected Task<Void> createTask() {
				return getUserDataWorker();
			}
		};

		service.setOnSucceeded(s -> {
			onSuccessLogin();
			
			UtilFXML.changeScene(getClass().getResource("/view/Main.fxml"), controller.getStage());
			controller.getStage().setMaximized(true);
		});

		service.setOnFailed(e -> {
			log.info("Failed task", e.getSource()
					.getException());
			controller.getStage()
					.getScene()
					.setCursor(Cursor.DEFAULT);

			UtilAlert.infoWindow(e.getSource()
					.getException()
					.getMessage());
		});
		vBox.disableProperty()
				.bind(service.runningProperty());
		service.start();
	}

	private void onSuccessLogin() {
		log.info("Login success");
		saveProperties();
		controller.getStage()
				.getScene()
				.setCursor(Cursor.DEFAULT);
		controller.setDirectory();

	}

	/**
	 * Realiza las tareas mientras carga la barra de progreso
	 * 
	 * @return tarea
	 */
	private Task<Void> getUserDataWorker() {
		return new Task<Void>() {
			@Override
			protected Void call() {
				try {

					controller.tryLogin(txtHost.getText(), txtUsername.getText(), txtPassword.getText());
					txtHost.setText(controller.getUrlHost()
							.toString());
					Login login = controller.getLogin();

					txtUsername.setText(login.getUsername());
					txtPassword.setText(login.getPassword());
					controller.setUsername(login.getUsername());
					controller.setPassword(login.getPassword());

				} catch (IOException e) {
					log.error("No se ha podido conectar con el host.", e);
					throw new IllegalArgumentException(I18n.get("error.host"));
				} catch (JSONException e) {
					log.error("Usuario y/o contraseña incorrectos", e);
					throw new IllegalArgumentException(I18n.get("error.login"));

				}

				try {
					log.info("Recogiendo info del usuario");

					PopulateSiteInfo populateSiteInfo = new PopulateSiteInfo();
					SiteInfo siteInfo = populateSiteInfo.populateSiteInfo(controller.getWebService());

					controller.getDataBase()
							.setSiteInfo(siteInfo);
					PopulateCourse populateCourse = new PopulateCourse(controller.getDataBase(),
							controller.getWebService());
					List<Course> userCourses = populateCourse.createCourses(siteInfo.getUserid());
					siteInfo.setCoursesEnrolled(userCourses);
					PopulateCourseCategories populateCourseCategories = new PopulateCourseCategories(
							controller.getDataBase(), controller.getWebService());
					populateCourseCategories.populateCourseCategories(siteInfo.getCoursesEnrolled()
							.stream()
							.map(c -> c.getCourseCategory()
									.getId())
							.collect(Collectors.toList()));

				} catch (Exception e) {
					log.error("Error al recuperar los datos del usuario.", e);
					throw new IllegalStateException(I18n.get("error.user"));
				}

				return null;
			}
		};

	}

	

}
