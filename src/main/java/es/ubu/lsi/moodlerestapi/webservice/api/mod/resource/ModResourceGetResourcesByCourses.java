package es.ubu.lsi.moodlerestapi.webservice.api.mod.resource;

import java.util.Collection;
import java.util.Collections;

import es.ubu.lsi.moodlerestapi.webservice.webservices.WSFunctionAbstract;
import es.ubu.lsi.moodlerestapi.webservice.webservices.WSFunctionEnum;

/**
 * Returns the courses and assignments for the users capability.
 * 
 * @author Yi Peng Ji
 *
 */
public class ModResourceGetResourcesByCourses extends WSFunctionAbstract {

	public ModResourceGetResourcesByCourses() {
		super(WSFunctionEnum.MOD_RESOURCE_GET_RESOURCES_BY_COURSES);
	}

	/**
	 * Only one course id
	 * 
	 * @param courseid course id
	 */
	public void setCourseid(int courseid) {
		setCourseids(Collections.singleton(courseid));
	}

	/**
	 * Course id, empty for retrieving all the courses.
	 * 
	 * @param courseids 0 or more course ids
	 */
	public void setCourseids(Collection<Integer> courseids) {
		parameters.put("courseids", courseids);
	}


}
