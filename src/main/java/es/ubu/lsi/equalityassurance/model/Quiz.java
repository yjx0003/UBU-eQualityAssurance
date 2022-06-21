package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Quiz implements Serializable {

	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	private int id;
	private Course course;
	private CourseModule courseModule;
	private String name;
	private String intro;
	private List<String> introfiles;
	private Instant timeopen;
	private Instant timeclose;
	private int timelimit;
	private String preferredbehaviour;
	private int attempts;
	private int grademethod;
	private int decimalpoints;
	private int questiondecimalpoints;
	private boolean shuffleanswers;
	private boolean sumgrades;
	private double grade;
	private Instant timecreated;
	private Instant timemodified;
	private String password;
	private String subnet;
	private boolean hasfeedback;
	private boolean visible;
	private boolean groupmode;
	private int groupingid;
	
	public Quiz(int id) {
		this.id = id;
	}
	
}
