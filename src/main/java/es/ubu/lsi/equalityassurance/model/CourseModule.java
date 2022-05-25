package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa las actividades o recursos presentes en un curso.
 * 
 * @author Yi Peng Ji
 *
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CourseModule implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * course module id
	 */
	@EqualsAndHashCode.Include
	private int cmid;

	/**
	 * activity url Optional.
	 * 
	 */
	private String url;

	/**
	 * activity module name
	 */
	private String moduleName;

	/**
	 * instance id
	 */
	private int instance;

	/**
	 * Optional. activity description
	 */
	private String description;

	/**
	 * Optional. is the module visible
	 */
	private boolean visible;

	/**
	 * Optional. Is the module visible for the user?
	 */
	private boolean uservisible;
	/**
	 * Optional. Availability information.
	 */
	private String availabilityinfo;

	/**
	 * Optional. is the module visible on course page
	 */
	private boolean visibleoncoursepage;
	/**
	 * activity icon url
	 */
	private String modicon;

	/**
	 * activity module type
	 */
	private ModuleType moduleType;

	/**
	 * activity module plural name
	 */
	private String modplural;

	/**
	 * Optional. module availability settings
	 */
	private String availability;

	/**
	 * number of identation in the site
	 */
	private int indent;

	private Section section;

	private Map<EnrolledUser, ActivityCompletion> activitiesCompletion;

	public CourseModule(int id) {
		this.cmid = id;
		activitiesCompletion = new HashMap<>();
	}
}
