package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Represanta la categoría que pertenece el curso.
 * 
 * @author Yi Peng Ji
 *
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CourseCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	private int id;

	/**
	 * category name
	 */
	private String name;

	/**
	 * category description
	 */
	private String description;

	/**
	 * description format (1 = HTML, 0 = MOODLE, 2 = PLAIN or 4 = MARKDOWN)
	 */
	private DescriptionFormat descriptionFormat;

	/**
	 * parent category id
	 */
	private int parent;

	/**
	 * category sorting order
	 */
	private int sortorder;

	/**
	 * number of courses in this category
	 */
	private int coursecount;

	/**
	 * category depth
	 */
	private int depth;
	/**
	 * category path
	 */
	private String path;

	public CourseCategory(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return name;
	}

}
