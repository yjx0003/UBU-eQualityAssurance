package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Forum implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	private int id;
	private String type;
	private String name;
	private boolean forcesubscribe;
	private String intro;

	private CourseModule courseModule;
	
	public Forum(int id) {
		this.id = id;
	}
}
