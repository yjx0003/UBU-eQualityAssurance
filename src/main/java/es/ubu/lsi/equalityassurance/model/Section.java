package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Secciones de contenido de un curso donde se guardan los modulos del curso. De
 * momento no se usa está clase.
 * 
 * @author Yi Peng Ji
 *
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Section implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String EMPTY_NAME_PLACEHOLDER = "<N/A>";

	/**
	 * Section ID
	 */
	@EqualsAndHashCode.Include
	private int id;

	/**
	 * Section name
	 */
	private String name;

	/**
	 * Optional. is the section visible
	 */
	private boolean visible;

	/**
	 * Section description
	 */
	private String summary;

	/**
	 * summary format (1 = HTML, 0 = MOODLE, 2 = PLAIN or 4 = MARKDOWN)
	 */
	private DescriptionFormat summaryformat;

	/**
	 * Optional. Section number inside the course
	 */
	private int sectionNumber;

	/**
	 * Optional. Whether is a section hidden in the course format
	 */
	private int hiddenbynumsections;
	/**
	 * Optional. Is the section visible for the user?
	 */
	private boolean uservisible;

	/**
	 * Optional. Availability information.
	 */
	private String availabilityinfo;

	public Section(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		if (name != null && name.trim()
				.isEmpty()) {
			this.name = EMPTY_NAME_PLACEHOLDER;
		} else {
			this.name = name;
		}

	}

	@Override
	public String toString() {
		return name;
	}

}