package es.ubu.lsi.equalityassurance;

/**
 * Clase de utilidad que devuelve la información de la aplicación.
 * 
 * @author Yi Peng Ji
 *
 */
public class AppInfo {


	public static final String VERSION = "1.0";
	
	public static final String APPLICATION_VERSION = "v" + VERSION;


	public static final String APPLICATION_NAME = "UBU eQuality Assurance";

	public static final String APPLICATION_NAME_WITH_VERSION = APPLICATION_NAME + " " + APPLICATION_VERSION;


	public static final String RESOURCE_BUNDLE_FILE_NAME = "messages/Messages";

	public static final String LOGGER_FILE_APPENDER = "log/" + APPLICATION_NAME_WITH_VERSION + ".log";

	public static final String CACHE_DIR = "cache";
	
	public static final String CONFIGURATION_DIR = "configuration";
	
	public static final String IMG_DIR = "/img/";

	public static final String IMG_FLAGS = IMG_DIR + "countries_flags/";

	public static final String PROPERTIES_PATH = "configuration.json";
	
	public static final String ARCHIVED_DIR = "Archived Courses";


	// Info del usuario

	public static final String JAVA_CLASS_PATH = System.getProperty("java.class.path");

	public static final String JAVA_HOME = System.getProperty("java.home");

	public static final String JAVA_VENDOR = System.getProperty("java.vendor");

	public static final String JAVA_VERSION = System.getProperty("java.version");

	public static final String OS_ARCH = System.getProperty("os.arch");

	public static final String OS_NAME = System.getProperty("os.name");

	public static final String OS_VERSION = System.getProperty("os.version");

	public static final String USER_DIR = System.getProperty("user.dir");

	public static final String USER_HOME = System.getProperty("user.home");

	public static final String USER_NAME = System.getProperty("user.name");

	public static final String JAVA_FX_VERSION = System.getProperty("javafx.runtime.version");
	

	private AppInfo() {
		throw new UnsupportedOperationException();
	}
}
