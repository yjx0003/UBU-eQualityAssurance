package es.ubu.lsi.equalityassurance.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Lenguajes disponibles, añadir una entrada nueva de enumeracion cada vez que
 * se añada un fichero de idiomas en la carpeta resource
 * 
 * @author Yi Peng Ji
 *
 */
public enum Language {

	//CATALAN_SPAIN("ca-ES","ES-CT"),
	//GERMAN_GERMANY("de-DE"),
	SPANISH_SPAIN("es-ES"),
	//FRENCH_FRANCE("fr-FR"),
	//ITALIAN_ITALY("it-IT"),
	//JAPANESE("ja"),
	//PORTUGUESE_PORTUGAL("pt-PT"),
	// RUSSIAN("ru"),
	// CHINESE_SIMPLIFIED("zh-CN"),
	ENGLISH_UK("en-GB");

	private Locale locale;
	private String flag;

	private static Map<Locale, Language> localeMap;

	static {

		localeMap = new HashMap<>();
		for (Language language : Language.values()) {
			localeMap.put(language.locale, language);
		}
	}

	/**
	 * Contructor de la enumeración
	 * 
	 * @param languageTag
	 *            codigo de lenguaje según IETF BCP 47
	 */
	private Language(String languageTag) {
		this.locale = Locale.forLanguageTag(languageTag);
		this.flag = locale.getCountry();
	}


	/**
	 * Devuelve un lenguaje a partor del codigo de lengua, por ejemplo el Español
	 * seria "es".
	 * 
	 * @param code
	 *            codigo de la lengua
	 * @return elemento de la enumeracion asociada o el ingles si no existe.
	 */
	public static Language getLanguageByTag(String code) {
		return localeMap.getOrDefault(Locale.forLanguageTag(code), Language.ENGLISH_UK);
	}

	/**
	 * Devuelve el elemento de la enumeracion a partir del Locale.
	 * 
	 * @param locale
	 *            locale
	 * @return elmento de la enum, si no existe busca a partir del lenguage de
	 *         locale
	 */
	public static Language getLanguageByLocale(Locale locale) {
		// si no existe el locale con el lenguaje y pais, se devuelve el un locale solo
		// con el lenguaje
		return localeMap.getOrDefault(locale, getLanguageByTag(locale.getLanguage()));
	}

	/**
	 * Devuelve el locale
	 * 
	 * @return locale
	 */
	public Locale getLocale() {
		return locale;

	}

	public String getDisplayLanguage() {
		return locale.getDisplayLanguage(locale);
	}

	public String getCountry() {
		return locale.getCountry();
	}
	
	public String getFlag() {
		return flag;
	}

	@Override
	public String toString() {
		String language = locale.getDisplayLanguage(locale);
		String country = locale.getDisplayCountry(locale);

		return country.isEmpty() ? language : language + " - " + country;
	}

}
