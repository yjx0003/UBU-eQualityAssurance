package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;
import java.time.Instant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DiscussionPost implements Serializable{

	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	private int id;
	private ForumDiscussion discussion;
	private DiscussionPost parent;
	private EnrolledUser user;
	private Instant created;
	private Instant modified;
	private boolean mailed;
	private String subject;
	private String message;
	private DescriptionFormat messageformat;
	private boolean messagetrust;
	private String attachment;
	private int totalscore;
	private boolean mailnow;
	private boolean canreply;
	private boolean deleted;
	private boolean isprivatereply;
	
	public DiscussionPost(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return subject;
	}
	
}
