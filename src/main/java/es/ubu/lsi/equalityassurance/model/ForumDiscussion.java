package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;
import java.time.Instant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ForumDiscussion implements Serializable {

	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	private int id;
	private CourseModule forum;
	private String name;
	private Instant timemodified;
	private EnrolledUser usermodified;
	private Instant timestart;
	private Instant timeend;
	private EnrolledUser user;
	private Instant created;
	private Instant modified;
	private int numreplies;
	private boolean pinned;
	private boolean locked;
	private boolean starred;
	private boolean canreply;
	private boolean canlock;
	private boolean canfavourite;

	public ForumDiscussion(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
