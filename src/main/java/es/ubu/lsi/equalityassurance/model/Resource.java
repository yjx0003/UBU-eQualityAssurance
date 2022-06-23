package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;
import java.time.Instant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Resource implements Serializable{
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	private int id;
	private CourseModule courseModule;
	private String name;
	private String intro;
	private Instant timemodified;
	private boolean visible;
	
	public Resource(int id) {
		this.id = id;
	}

}
