package es.ubu.lsi.equalityassurance.controller.load;

import java.io.IOException;

import org.json.JSONObject;

import es.ubu.lsi.equalityassurance.model.SiteInfo;
import es.ubu.lsi.moodlerestapi.webservice.api.core.webservice.CoreWebserviceGetSiteInfo;
import es.ubu.lsi.moodlerestapi.webservice.util.UtilResponse;
import es.ubu.lsi.moodlerestapi.webservice.webservices.WebService;

public class PopulateSiteInfo {

	public SiteInfo populateSiteInfo(WebService webService) throws IOException {
		
		return populateSiteInfo(UtilResponse.getJSONObjectResponse(webService, new CoreWebserviceGetSiteInfo()));
		
	}
	
	public SiteInfo populateSiteInfo(JSONObject jsonObject) {
		SiteInfo siteInfo = new SiteInfo();
		
		siteInfo.setSitename(jsonObject.getString("sitename"));
		siteInfo.setUsername(jsonObject.getString("username"));
		siteInfo.setFirstname(jsonObject.getString("firstname"));
		siteInfo.setLastname(jsonObject.getString("lastname"));
		siteInfo.setFullname(jsonObject.getString("fullname"));
		siteInfo.setLang(jsonObject.getString("lang"));
		siteInfo.setSiteurl(jsonObject.getString("siteurl"));
		siteInfo.setUserid(jsonObject.getInt("userid"));
		return siteInfo;
		
		
		
	}
	
}
