package webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.ubu.lsi.moodlerestapi.webservice.api.core.webservice.CoreWebserviceGetSiteInfo;
import es.ubu.lsi.moodlerestapi.webservice.util.UtilResponse;
import es.ubu.lsi.moodlerestapi.webservice.webservices.WebService;

public class WebServiceTest {
	
	private static WebService webService;
	private static String username = "teacher";
	private static String password = "moodle";
	private static String host = "https://school.moodledemo.net/";
	
	@BeforeAll
	public static void login() throws IOException {
		webService = new WebService(host, username, password);
		
	}
	
	
	@Test
	public void coreSiteInfoTest() throws IOException{
		JSONObject jsonObject = UtilResponse.getJSONObjectResponse(webService, new CoreWebserviceGetSiteInfo());
		
			
		
	}
	
}
