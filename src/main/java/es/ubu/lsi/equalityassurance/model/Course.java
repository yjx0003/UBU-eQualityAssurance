package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	private int id;
	private String shortName;
	private String fullName;
	private String idNumber;
	private String summary;
	private DescriptionFormat summaryformat;
	private Instant startDate;
	private Instant endDate;
	private boolean isFavorite;
	private boolean enablecompletion;
	private CourseCategory courseCategory;
	private boolean courseAccess;
	private boolean reportAccess;
	private boolean gradeItemAccess;
	private boolean hasActivityCompletion;
	
	private Set<EnrolledUser> users;
	
	public Course(int id) {
		
		this.id = id;
		this.users = new HashSet<>();
	}

	@Override
	public String toString() {
		if (this.courseCategory == null) {
			return this.fullName;
		}
		return this.fullName + " (" + this.courseCategory + ")";
	}
}
