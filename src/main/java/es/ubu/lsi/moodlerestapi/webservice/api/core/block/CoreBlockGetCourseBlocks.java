package es.ubu.lsi.moodlerestapi.webservice.api.core.block;

import es.ubu.lsi.moodlerestapi.webservice.webservices.WSFunctionAbstract;
import es.ubu.lsi.moodlerestapi.webservice.webservices.WSFunctionEnum;

public class CoreBlockGetCourseBlocks extends WSFunctionAbstract {

	public CoreBlockGetCourseBlocks(int courseid) {
		super(WSFunctionEnum.CORE_BLOCK_GET_COURSE_BLOCS);
		setCourseid(courseid);
	}

	/**
	 * Course id (required).
	 * 
	 * @param courseid course id
	 */
	public void setCourseid(int courseid) {
		parameters.put("courseid", courseid);

	}

	public void setReturncontents(boolean returncontents) {
		parameters.put("returncontents", returncontents ? 1 : 0);
	}

}
