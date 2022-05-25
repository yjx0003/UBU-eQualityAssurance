package es.ubu.lsi.equalityassurance.util;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ubu.lsi.equalityassurance.model.ModuleType;

/**
 * Clase encargada de traducir los elementos del resource bundle.
 * 
 * @author Yi Peng Ji
 *
 */
public class I18n {

	private static final Logger LOGGER = LoggerFactory.getLogger(I18n.class);
	private static ResourceBundle rb;

	/**
	 * Modifica el resource bundle
	 * 
	 * @param rb
	 *            el nuevo resource bundle
	 */
	public static void setResourceBundle(ResourceBundle rb) {
		I18n.rb = rb;
	}

	/**
	 * Devuelve el resource bundle.
	 * 
	 * @return el resource bundle actual
	 */
	public static ResourceBundle getResourceBundle() {
		return rb;
	}

	/**
	 * Devuelve la traducir a partir de la key
	 * 
	 * @param key
	 *            key
	 * @return el valor asociado a esa key, o la propia key si no existe el valor
	 */
	public static String get(String key) {

		return getOrDefault(key, key);
	}


	/**
	 * Traduce el tipo de modulo
	 * 
	 * @param moduleType
	 *            tipo de modulo
	 * @return la traduccion si existe, o el {@link ModuleType#toString()} si no
	 *         existe.
	 */
	public static String get(ModuleType moduleType) {
		return getOrDefault("module." + moduleType.getModName(), moduleType.getModName());
	}

	/**
	 * Devuelve el valor de la key o el de defecto si no existe en el resource
	 * bundle.
	 * 
	 * @param key
	 *            key
	 * @param defaultValue
	 *            valor por defecto
	 * @return el valor de la key o el valor por defecto
	 */
	private static String getOrDefault(String key, String defaultValue) {
		if (key !=null && rb.containsKey(key)) {
			return rb.getString(key);
		}
		LOGGER.warn("No existe entrada en el resource bundle la key: {}", key);
		return defaultValue;

	}

	private I18n() {
		throw new UnsupportedOperationException();
	}


}
