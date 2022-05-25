package es.ubu.lsi.moodlerestapi.webservice.webservices;


import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class WebService {

	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
	private String moodleWSRestFormat = "json";
	private String hostWithWS;
	private String hostWithAjax;
	private String sesskey;

	private String token;
	private String privateToken;

	public WebService() {
	}

	/**
	 * Intenta conectarse al servicio de moodle para recuperar el token del usuario
	 * para la API.
	 * 
	 * @param host     host de moodle
	 * @param userName username de moodle
	 * @param password contraseña de moodle
	 * @throws IOException si no se ha podido conectar al host
	 */
	public WebService(String host, String username, String password) throws IOException {

		String url = host + "/login/token.php";
		RequestBody formBody = new FormBody.Builder().add("username", username)
				.add("password", password)
				.add("service", WSFunctionEnum.MOODLE_MOBILE_APP.toString())
				.build();
		try (Response response = Connection.getResponse(new Request.Builder().url(url)
				.post(formBody)
				.build())) {
			JSONObject jsonObject = new JSONObject(new JSONTokener(response.body()
					.byteStream()));

			if (jsonObject.has("error")) {
				throw new IllegalAccessError(jsonObject.getString("error"));
			}

			setData(host, jsonObject.getString("token"), jsonObject.optString("privatetoken"));
		}

	}

	public String getPrivateToken() {
		return privateToken;
	}

	public void setPrivateToken(String privateToken) {
		this.privateToken = privateToken;

	}

	public void setData(String host, String token, String privateToken) {
		this.token = token;
		this.privateToken = privateToken;

		this.hostWithWS = host + "/webservice/rest/server.php";
		this.hostWithAjax = host + "/lib/ajax/service.php";
	}

	public void setSesskey(String sesskey) {
		this.sesskey = sesskey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getmoodleWSRestFormat() {
		return moodleWSRestFormat;
	}

	public void setmoodleWSRestFormat(String moodleWSRestFormat) {
		this.moodleWSRestFormat = moodleWSRestFormat;
	}

	public Response getResponse(WSFunction wsFunction) throws IOException {

		
		wsFunction.addToMapParemeters();

		Builder formBody = new FormBody.Builder();

		formBody.add("wsfunction", wsFunction.getWSFunction()
				.toString());
		formBody.add("moodlewsrestformat", moodleWSRestFormat);
		formBody.add("wstoken", token);
		
		for (String key : wsFunction.getParameters()
				.keySet()) {
			jsonToQueryParam(formBody, new StringBuilder(key), wsFunction.getParameters()
					.get(key));
		}

		Request.Builder builder = new Request.Builder().url(hostWithWS);

		builder.post(formBody.build());

		return Connection.getResponse(builder.build());

	}

	private void jsonToQueryParam(Builder formBodyBuilder, StringBuilder stringBuilder, Object param) {

		
		if (param instanceof JSONObject) {
			JSONObject jsonObject = (JSONObject) param;
			for (String key : jsonObject.keySet()) {
				StringBuilder newStringBuilder = new StringBuilder(stringBuilder);
				newStringBuilder.append("[");
				newStringBuilder.append(key);
				newStringBuilder.append("]");
				jsonToQueryParam(formBodyBuilder, newStringBuilder, jsonObject.get(key));
			}
		} else if (param instanceof Map) {
			Map<?, ?> map = (Map<?, ?>) param;
			for (Map.Entry<?, ?> entry : map.entrySet()) {
				StringBuilder newStringBuilder = new StringBuilder(stringBuilder);
				newStringBuilder.append("[");
				newStringBuilder.append(entry.getKey());
				newStringBuilder.append("]");
				jsonToQueryParam(formBodyBuilder, newStringBuilder, entry.getValue());
			}
		} else if (param instanceof Iterable) {
			Iterable<?> iterable = (Iterable<?>) param;
			Iterator<?> iterator = iterable.iterator();
			for (int i = 0; iterator.hasNext(); ++i) {
				StringBuilder newStringBuilder = new StringBuilder(stringBuilder);
				newStringBuilder.append("[");
				newStringBuilder.append(i);
				newStringBuilder.append("]");
				jsonToQueryParam(formBodyBuilder, newStringBuilder, iterator.next());
			}

		} else {
			formBodyBuilder.add(stringBuilder.toString(), param.toString());
		}
	}

	public Response getAjaxResponse(WSFunction... wsFunctions) throws IOException {
		return WebService.getAjaxResponse(hostWithAjax + "?sesskey=" + sesskey, wsFunctions);
	}

	public static Response getAjaxResponse(String host, WSFunction... wsFunctions) throws IOException {
		String hosturl = host + "/lib/ajax/service.php";
		return ajaxResponse(hosturl, wsFunctions);
	}

	public static Response getAjaxResponse(String host, String sesskey, WSFunction... wsFunctions) throws IOException {
		return ajaxResponse(host + "/lib/ajax/service.php?sesskey=" + sesskey, wsFunctions);
	}

	private static Response ajaxResponse(String host, WSFunction... wsFunctions) throws IOException {
		JSONArray jsonArray = new JSONArray();

		for (int i = 0; i < wsFunctions.length; ++i) {
			JSONObject methods = new JSONObject();
			methods.put("index", i);
			methods.put("methodname", wsFunctions[i].getWSFunction()
					.toString());
			methods.put("args", wsFunctions[i].getParameters());
			jsonArray.put(methods);
		}

		RequestBody body = RequestBody.create(jsonArray.toString(), JSON);
		return Connection.getResponse(new Request.Builder().url(host)
				.post(body)
				.build());
	}

}
