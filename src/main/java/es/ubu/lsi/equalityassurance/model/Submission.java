package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;
import java.time.Instant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Submission implements Serializable{

	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	private int id;
	private Assignment assignment;
	private EnrolledUser user;
	private int attemptnumber;
	private Instant timecreated;
	private Instant timemodified;
	private String status;
	private int groupid;
	private String gradingstatus;
	
	public Submission(int id) {
		this.id = id;
	}
	
}
