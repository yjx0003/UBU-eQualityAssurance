package es.ubu.lsi.equalityassurance.util;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UtilString {
	/**
	 * Escapa las comillas simples de un texto añadiendo un \
	 * 
	 * @param input texto
	 * @return texto escapado
	 */
	public static String escapeJavaScriptText(String input) {

		return input.replace("'", "\\\'")
				.replaceAll("\\R", "");

	}

	/**
	 * Convierte una lista de elementos en string separados por comas
	 * 
	 * @param datasets
	 * @return
	 */
	public static <E> String join(List<E> datasets) {

		return join(datasets, ",");
	}

	public static <E> String join(Collection<E> collection, String joinCharacter) {
		if (collection == null || collection.isEmpty() || joinCharacter == null) {
			return "";
		}
		return collection.stream()
				.map(E::toString)
				.collect(Collectors.joining(joinCharacter));
	}

	/**
	 * Convierte una lista en string con los elementos entre comillas y separado por
	 * comas.
	 * 
	 * @param list
	 * @return
	 */
	public static <T> String joinWithQuotes(List<T> list) {
		// https://stackoverflow.com/a/18229122
		return list.stream()
				.map(s -> "'" + escapeJavaScriptText(s.toString()) + "'")
				.collect(Collectors.joining(","));
	}

	/**
	 * Removes reserved character from Windows filename.
	 * 
	 * @param stringToRemove need to remove reserved character
	 * @return without reserved character
	 */
	public static String removeReservedChar(String stringToRemove) {
		String newString = stringToRemove.replaceAll(":|\\\\|/|\\?|\\*|\\|", "");
		return newString.trim();
		
	}
}
