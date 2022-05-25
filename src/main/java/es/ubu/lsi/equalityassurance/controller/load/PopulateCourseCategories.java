package es.ubu.lsi.equalityassurance.controller.load;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ubu.lsi.equalityassurance.model.CourseCategory;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.DescriptionFormat;
import es.ubu.lsi.equalityassurance.util.Constants;
import es.ubu.lsi.moodlerestapi.webservice.api.core.course.CoreCourseGetCategories;
import es.ubu.lsi.moodlerestapi.webservice.util.UtilResponse;
import es.ubu.lsi.moodlerestapi.webservice.webservices.WSFunctionEnum;
import es.ubu.lsi.moodlerestapi.webservice.webservices.WebService;

public class PopulateCourseCategories {
	private static final Logger LOGGER = LoggerFactory.getLogger(PopulateCourseCategories.class);
	private DataBase dataBase;
	private WebService webService;

	public PopulateCourseCategories(DataBase dataBase, WebService webService) {
		this.dataBase = dataBase;
		this.webService = webService;
	}

	public List<CourseCategory> populateCourseCategories(Collection<Integer> categoryids) {

		try {
			CoreCourseGetCategories coreCourseGetCategories = new CoreCourseGetCategories();
			coreCourseGetCategories.appendIds(categoryids);
			coreCourseGetCategories.setAddsubcategories(false);
			JSONArray jsonArray = UtilResponse.getJSONArrayResponse(webService, coreCourseGetCategories);
			return populateCourseCategories(jsonArray);
		} catch (Exception e) {
			LOGGER.warn("Problem to get course categories", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Populate attributes in every course category from
	 * {@link WSFunctionEnum#CORE_COURSE_GET_CATEGORIES}
	 * 
	 * @param dataBase
	 * @param jsonArray
	 */
	public List<CourseCategory> populateCourseCategories(JSONArray jsonArray) {
		List<CourseCategory> courseCategories = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			int id = jsonObject.getInt(Constants.ID);
			CourseCategory courseCategory = dataBase.getCourseCategories()
					.getById(id);
			courseCategory.setName(jsonObject.getString(Constants.NAME));
			courseCategory.setDescription(jsonObject.getString(Constants.DESCRIPTION));
			courseCategory.setDescriptionFormat(DescriptionFormat.get(jsonObject.getInt(Constants.DESCRIPTIONFORMAT)));
			courseCategory.setCoursecount(jsonObject.getInt(Constants.COURSECOUNT));
			courseCategory.setDepth(jsonObject.getInt(Constants.DEPTH));
			courseCategory.setPath(jsonObject.getString(Constants.PATH));
			courseCategories.add(courseCategory);
		}
		return courseCategories;
	}

}
