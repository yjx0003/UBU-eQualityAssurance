package es.ubu.lsi.moodlerestapi.webservice.util;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import es.ubu.lsi.moodlerestapi.webservice.webservices.WSFunctionAbstract;
import es.ubu.lsi.moodlerestapi.webservice.webservices.WebService;
import okhttp3.Response;

public class UtilResponse {
	
	public static JSONArray getJSONArrayResponse(WebService webService, WSFunctionAbstract webServiceFunction)
			throws IOException {
		try (Response response = webService.getResponse(webServiceFunction)) {
			String string = response.body()
					.string();

			if (string.startsWith("{")) {
				JSONObject jsonObject = new JSONObject(string);
				throw new IllegalStateException(webServiceFunction + "\n" + jsonObject.optString("exception") + "\n"
						+ jsonObject.optString("message"));
			}

			return new JSONArray(string);

		}

	}

	public static JSONObject getJSONObjectResponse(WebService webService, WSFunctionAbstract webServiceFunction)
			throws IOException {
		try (Response response = webService.getResponse(webServiceFunction)) {
			JSONObject jsonObject = new JSONObject(new JSONTokener(response.body()
					.byteStream()));
			if (jsonObject.has("exception")) {
				throw new IllegalStateException(webServiceFunction + "\n" + jsonObject.optString("exception") + "\n"
						+ jsonObject.optString("message"));
			}
			return jsonObject;
		}

	}
	
	private UtilResponse() {
		// utility class, private constructor
	}
	
}
