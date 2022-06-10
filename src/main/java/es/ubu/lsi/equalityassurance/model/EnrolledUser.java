package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/**
 * Clase usuario de moodle. Los atributos optional pueden aparecer o no aparecer
 * en la respuesta de moodle.
 * 
 * @author Yi Peng Ji
 *
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EnrolledUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID of the user
	 */
	@EqualsAndHashCode.Include
	private int id;
	/**
	 * Optional. Username policy is defined in Moodle security config
	 */
	private String userName;
	/**
	 * Optional. The first name(s) of the userjm
	 */
	private String firstname;
	/**
	 * Optional. The family name of the user
	 */
	private String lastname;

	/**
	 * . The fullname of the user
	 */
	private String fullName;
	/**
	 * Optional. An email address
	 */
	private String email;
	/**
	 * Optional. Postal address
	 */
	private String address;

	/**
	 * Optional. Phone 1
	 */
	private String phone1;
	/**
	 * Optional. Phone 2
	 */
	private String phone2;

	/**
	 * Optional. icq number
	 */
	private String icq;

	/**
	 * Optional. skype id
	 */
	private String skype;
	/**
	 * Optional. yahoo id
	 */
	private String yahoo;
	/**
	 * Optional. aim id
	 */
	private String aim;
	/**
	 * Optional. msn number
	 */
	private String msn;
	/**
	 * Optional. department
	 */
	private String department;

	/**
	 * Optional. institution
	 */
	private String institution;

	/**
	 * Optional. An arbitrary ID code number perhaps from the institution
	 */
	private String idnumber;

	/**
	 * Optional. user interests (separated by commas)
	 */
	private String interests;

	/**
	 * Optional. first access to the site (0 if never)
	 */
	private Instant firstaccess = Instant.EPOCH;
	/**
	 * Optional. last access to the site (0 if never)
	 */
	private Instant lastaccess = Instant.EPOCH;

	/**
	 * Optional. last access to the course (0 if never)
	 */
	private Instant lastcourseaccess = Instant.EPOCH;

	/**
	 * Optional. User profile description
	 */
	private String description;
	/**
	 * Optional. description format (1 = HTML, 0 = MOODLE, 2 = PLAIN or 4 =
	 * MARKDOWN)
	 */
	private DescriptionFormat descriptionformat;

	/**
	 * Optional. Home city of the user
	 */
	private String city;
	/**
	 * Optional. URL of the user
	 */
	private String url;
	/**
	 * Optional. Home country code of the user, such as AU or CZ
	 */
	private String country;
	/**
	 * Optional. User image profile URL - small version
	 */
	private String profileimageurlsmall;

	/**
	 * Optional. User image profile URL - big version
	 */
	private String profileimageurl;
	
	private List<Role> roles;
	
	private List<Group> groups;
	
	private List<Course> courses;

	public EnrolledUser(int id) {
		this.id = id;
		this.roles = new ArrayList<>();
		this.groups = new ArrayList<>();
		this.courses = new ArrayList<>();
		
	}
}
