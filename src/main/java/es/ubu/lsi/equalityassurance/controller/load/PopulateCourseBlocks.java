package es.ubu.lsi.equalityassurance.controller.load;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import es.ubu.lsi.equalityassurance.model.Block;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.moodlerestapi.webservice.api.core.block.CoreBlockGetCourseBlocks;
import es.ubu.lsi.moodlerestapi.webservice.util.UtilResponse;
import es.ubu.lsi.moodlerestapi.webservice.webservices.WebService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PopulateCourseBlocks {

	private WebService webService;
	private DataBase dataBase;

	public PopulateCourseBlocks(DataBase dataBase, WebService webService) {
		this.webService = webService;
		this.dataBase = dataBase;
	}

	public List<Block> populateCourseBlocks(int courseid) {
		try {
			CoreBlockGetCourseBlocks coreBlockGetCourseBlocks = new CoreBlockGetCourseBlocks(courseid);

			JSONObject jsonObject = UtilResponse.getJSONObjectResponse(webService, coreBlockGetCourseBlocks);
			return populateCourseBlocks(jsonObject);
		} catch (Exception e) {
			log.warn("Error to get block information");
			return Collections.emptyList();
		}
	}

	private List<Block> populateCourseBlocks(JSONObject jsonObject) {
		JSONArray blocks = jsonObject.getJSONArray("blocks");
		List<Block> blockList = new ArrayList<>();
		for (int i = 0; i < blocks.length(); i++) {
			JSONObject blockObject = blocks.getJSONObject(i);
			Block block = dataBase.getBlocks().getById(blockObject.getInt("instanceid"));
			block.setName(blockObject.getString("name"));
			block.setRegion(blockObject.optString("region"));
			block.setCollapsible(blockObject.optBoolean("collapsible"));
			block.setDockable(blockObject.optBoolean("dockable"));
			block.setWeight(jsonObject.optInt("weight"));
			block.setVisible(blockObject.optBoolean("visible"));
			blockList.add(block);
			
		}
		return blockList;
	}

}
