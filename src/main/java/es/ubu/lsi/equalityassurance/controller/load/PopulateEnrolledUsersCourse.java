package es.ubu.lsi.equalityassurance.controller.load;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ubu.lsi.equalityassurance.model.Course;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.DescriptionFormat;
import es.ubu.lsi.equalityassurance.model.EnrolledUser;
import es.ubu.lsi.equalityassurance.model.Group;
import es.ubu.lsi.equalityassurance.model.Role;
import es.ubu.lsi.equalityassurance.util.Constants;
import es.ubu.lsi.moodlerestapi.webservice.api.core.enrol.CoreEnrolGetEnrolledUsers;
import es.ubu.lsi.moodlerestapi.webservice.api.core.users.CoreUserGetUsersByField;
import es.ubu.lsi.moodlerestapi.webservice.util.UtilResponse;
import es.ubu.lsi.moodlerestapi.webservice.webservices.WebService;

public class PopulateEnrolledUsersCourse {

	private static final Logger LOGGER = LoggerFactory.getLogger(PopulateEnrolledUsersCourse.class);
	private DataBase dataBase;
	private WebService webService;

	public PopulateEnrolledUsersCourse(DataBase dataBase, WebService webService) {
		this.dataBase = dataBase;
		this.webService = webService;
	}
	
	
	public List<EnrolledUser> createEnrolledUsers(int courseid){
		try {
			JSONArray jsonArray = UtilResponse.getJSONArrayResponse(webService, new CoreEnrolGetEnrolledUsers(courseid));
			return createEnrolledUsers(jsonArray);
		} catch (Exception e) {
			LOGGER.error("Error when get the users in the course: ", e );
			return Collections.emptyList();
		}
		
	}

	public List<EnrolledUser> createEnrolledUsers(JSONArray jsonArray) {
		List<EnrolledUser> enrolledUsers = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); ++i) {
			enrolledUsers.add(createEnrolledUser(jsonArray.getJSONObject(i)));
		}
		return enrolledUsers;
	}

	/**
	 * Crea el usuario matriculado a partir del json parcial de la respuesta de
	 * moodle
	 * 
	 * @param user json parcial del usuario
	 *             {@link webservice.WSFunctions#CORE_ENROL_GET_ENROLLED_USERS}
	 * @return usuario matriculado
	 * @throws IOException si hay un problema de conexion con moodle
	 */
	private EnrolledUser createEnrolledUser(JSONObject user) {

		EnrolledUser enrolledUser = userAttributes(user);

		List<Course> courses = createCourses(user.optJSONArray(Constants.ENROLLEDCOURSES));
		courses.forEach(course -> course.getUsers().add(enrolledUser));

		List<Role> roles = createRoles(user.optJSONArray(Constants.ROLES));
		roles.forEach(role -> role.getEnrolledUsers().add(enrolledUser));
		
		List<Group> groups = createGroups(user.optJSONArray(Constants.GROUPS));
		groups.forEach(group -> group.getEnrolledUsers().add(enrolledUser));
		
		return enrolledUser;

	}

	public EnrolledUser userAttributes(JSONObject user) {

		EnrolledUser enrolledUser = dataBase.getUsers()
				.getById(user.getInt(Constants.ID));

		enrolledUser.setFirstname(user.optString(Constants.FIRSTNAME));
		enrolledUser.setLastname(user.optString(Constants.LASTNAME));
		enrolledUser.setFullName(user.optString(Constants.FULLNAME));
		enrolledUser.setFirstaccess(Instant.ofEpochSecond(user.optLong(Constants.FIRSTACCESS, -1))); // -1 si no esta
																										// disponible
		enrolledUser.setLastaccess(Instant.ofEpochSecond(user.optLong(Constants.LASTACCESS, -1)));// -1 si no esta
																									// disponible
		enrolledUser.setLastcourseaccess(Instant.ofEpochSecond(user.optLong(Constants.LASTCOURSEACCESS, -1)));// disponible
																												// en
		// moodle 3.7
		enrolledUser.setDescription(user.optString(Constants.DESCRIPTION));
		enrolledUser.setDescriptionformat(DescriptionFormat.get(user.optInt(Constants.DESCRIPTIONFORMAT)));
		enrolledUser.setCity(user.optString(Constants.CITY));
		enrolledUser.setCountry(user.optString(Constants.COUNTRY));
		enrolledUser.setProfileimageurl(user.optString(Constants.PROFILEIMAGEURL));
		enrolledUser.setProfileimageurlsmall(user.optString(Constants.PROFILEIMAGEURLSMALL));
		enrolledUser.setEmail(user.optString(Constants.EMAIL));
		
		
		return enrolledUser;
	}

	private List<Group> createGroups(JSONArray jsonArray) {
		
		if(jsonArray== null) {
			return Collections.emptyList();
		}
		
		
		List<Group> groups = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); ++i) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			Group group = dataBase.getGroups()
					.getById(jsonObject.getInt(Constants.ID));

			group.setGroupName(jsonObject.optString(Constants.NAME));
			group.setDescription(jsonObject.optString(Constants.DESCRIPTION));
			group.setDescriptionFormat(DescriptionFormat.get(jsonObject.optInt(Constants.DESCRIPTIONFORMAT)));
			groups.add(group);
		}

		return groups;
	}

	private List<Role> createRoles(JSONArray jsonArray) {
		if(jsonArray== null) {
			return Collections.emptyList();
		}
		
		List<Role> roles = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); ++i) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			Role role = dataBase.getRoles()
					.getById(jsonObject.getInt(Constants.ROLEID));

			role.setRoleName(jsonObject.optString(Constants.NAME));
			role.setRoleShortName(jsonObject.optString(Constants.SHORTNAME));
			roles.add(role);
		}

		return roles;
	}

	private List<Course> createCourses(JSONArray jsonArray) {
		List<Course> courses = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); ++i) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Course course = dataBase.getCourses()
					.getById(jsonObject.getInt(Constants.ID));
			course.setFullName(jsonObject.optString(Constants.FULLNAME));
			course.setShortName(jsonObject.optString(Constants.SHORTNAME));
			courses.add(course);
		}
		return courses;
	}
	
	
	
	public List<EnrolledUser> searchUser(Collection<Integer> ids) throws IOException {
		try {
			CoreUserGetUsersByField coreUserGetUsersByField = new CoreUserGetUsersByField();
			coreUserGetUsersByField.setIds(ids);
			JSONArray array = UtilResponse.getJSONArrayResponse(webService, coreUserGetUsersByField);
			List<EnrolledUser> users = new ArrayList<>();

			for (int i = 0; i < array.length(); ++i) {
				users.add(userAttributes(array.getJSONObject(i)));
			}
			return users;

		}catch(Exception e) {
			return Collections.emptyList();
		}
		
	}

}
