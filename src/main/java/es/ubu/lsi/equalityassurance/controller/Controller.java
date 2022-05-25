package es.ubu.lsi.equalityassurance.controller;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import es.ubu.lsi.equalityassurance.AppInfo;
import es.ubu.lsi.equalityassurance.controller.configuration.ConfigHelper;
import es.ubu.lsi.equalityassurance.controller.load.Login;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.util.I18n;
import es.ubu.lsi.equalityassurance.util.Language;
import es.ubu.lsi.equalityassurance.util.UtilString;
import es.ubu.lsi.moodlerestapi.webservice.webservices.WebService;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class Controller {
	
	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
			.ofLocalizedDateTime(FormatStyle.SHORT);

	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
	
	private DataBase dataBase;
	private Login login;
	private Language selectedLanguage;
	private Stage stage;
	private String username;
	private String password;
	private URL urlHost;
	private Path hostUserArchivedDir;
	private Path hostUserDir;
	private Path configuration;
	
	private static Controller singleton;

	private Controller() {

	}

	public static Controller getInstance() {
		if (singleton == null) {
			singleton = new Controller();
		}
		return singleton;
	}

	public void initialize() {

		ConfigHelper.initialize(AppInfo.PROPERTIES_PATH);

		// Si no existe el recurso de idioma especificado cargamos el Ingles
		Language lang = Language.getLanguageByTag(ConfigHelper.getProperty("language", Locale.getDefault().toLanguageTag()));
		try {

			if (lang == null) {
				log.info("Cargando idioma:{} ", Locale.getDefault());
				setSelectedLanguage(Language.ENGLISH_UK);
			} else {
				
				setSelectedLanguage(lang);
			}

		} catch (NullPointerException | MissingResourceException e) {
			log.error("No se ha podido encontrar el recurso de idioma, cargando idioma " + lang + ": {}", e);
			setSelectedLanguage(Language.ENGLISH_UK);
		}

	}
	
	public void setSelectedLanguage(Language selectedLanguage) {
		this.selectedLanguage = selectedLanguage;
		Locale a = selectedLanguage.getLocale();
		Locale.setDefault(a);
		I18n.setResourceBundle(ResourceBundle.getBundle(AppInfo.RESOURCE_BUNDLE_FILE_NAME));
		ConfigHelper.setProperty("language", a.toLanguageTag());
	}
	/**
	 * @return the webService
	 */
	public WebService getWebService() {
		return login.getWebService();
	}
	
	public void tryLogin(String host, String username, String password) throws IOException {

		login = new Login(host, username, password);
		String validHost = login.checkUrlServer(host);
		login.setHost(validHost);
		login.tryLogin();
		this.urlHost = new URL(validHost);
	}
	
	public void setDirectory() {

		String hostName = UtilString.removeReservedChar(this.getUrlHost()
				.getHost());
		String userName = UtilString.removeReservedChar(this.getUsername());
		this.hostUserArchivedDir = Paths.get(AppInfo.CACHE_DIR, hostName, userName, AppInfo.ARCHIVED_DIR);
		this.hostUserDir = Paths.get(AppInfo.CACHE_DIR, hostName, userName);
		this.configuration = Paths.get(AppInfo.CONFIGURATION_DIR, hostName, userName);


	}
}
