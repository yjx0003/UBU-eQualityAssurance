package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;
import java.time.Instant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Assignment implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	private int id;
	private CourseModule courseModule;
	private String name;
	private int nosubmissions;
	private int submissiondrafts;
	private boolean sendnotifications;
	private boolean sendlatenotifications;
	private boolean sendstudentnotifications;
	private Instant duedate;
	private Instant allowsubmissionsfromdate;
	private double grade;
	private Instant timemodified;
	private boolean completionsubmit;
	private Instant cutoffdate;
	private Instant gradingduedate;
	private String intro;
	private boolean teamsubmission;
	
	public Assignment(int id) {
		this.id = id;
	}
	
	
}
