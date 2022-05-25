package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String sitename;
	private String username;
	private String firstname;
	private String lastname;
	private String fullname;
	private String lang;
	private int userid;
	private String siteurl;
	
	private List<Course> coursesEnrolled;

	public SiteInfo() {
		this.coursesEnrolled = new ArrayList<>();
	}
	
}
