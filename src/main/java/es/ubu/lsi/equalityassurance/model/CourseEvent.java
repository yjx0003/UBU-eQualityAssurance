package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;
import java.time.Instant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter 
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CourseEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	private int id;
	private String name;
	private String description;
	private DescriptionFormat format;
	private EnrolledUser user;
	private CourseModule courseModule;
	private String eventtype;
	private Instant timestart;
	private int timeduration;
	private boolean visible;
	private Instant timemodified;

	public CourseEvent(int id) {
		this.id = id;
	}

	

}
