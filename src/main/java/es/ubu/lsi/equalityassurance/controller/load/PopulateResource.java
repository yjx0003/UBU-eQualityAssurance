package es.ubu.lsi.equalityassurance.controller.load;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.Resource;
import es.ubu.lsi.moodlerestapi.webservice.api.mod.resource.ModResourceGetResourcesByCourses;
import es.ubu.lsi.moodlerestapi.webservice.util.UtilResponse;
import es.ubu.lsi.moodlerestapi.webservice.webservices.WebService;

public class PopulateResource {
	private DataBase dataBase;
	private WebService webService;

	public PopulateResource(DataBase dataBase, WebService webService) {
		this.dataBase = dataBase;
		this.webService = webService;
	}

	public List<Resource> populateResource(int courseid) {
		try {
			ModResourceGetResourcesByCourses modResourceGetResourcesByCourses = new ModResourceGetResourcesByCourses();
			modResourceGetResourcesByCourses.setCourseid(courseid);
			JSONObject jsonObject = UtilResponse.getJSONObjectResponse(webService, modResourceGetResourcesByCourses);
			return populateResource(jsonObject);

		} catch (IOException e) {
			return Collections.emptyList();
		}
	}

	public List<Resource> populateResource(JSONObject jsonObject) {
		List<Resource> list = new ArrayList<>();
		JSONArray resources = jsonObject.getJSONArray("resources");
		for (int i = 0; i < resources.length(); ++i) {
			JSONObject r = resources.getJSONObject(i);
			Resource resource = dataBase.getResources().getById(r.getInt("id"));
			resource.setCourseModule(dataBase.getModules().getById(r.getInt("coursemodule")));
			resource.setName(r.getString("name"));
			resource.setIntro(r.getString("intro"));
			resource.setTimemodified(Instant.ofEpochSecond(r.getLong("timemodified")));
			resource.setVisible(r.getInt("visible") == 1);
			list.add(resource);
			
			
		}
		return list;
	}
}
