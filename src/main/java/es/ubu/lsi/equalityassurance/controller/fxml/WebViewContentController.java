package es.ubu.lsi.equalityassurance.controller.fxml;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;

@Getter
@Slf4j
public class WebViewContentController {

	@FXML
	private WebView webView;
	private JavaConnector javaConnector;

	public void init() {
		javaConnector = new JavaConnector();
		WebEngine webEngine = webView.getEngine();
		webEngine.getLoadWorker()
				.stateProperty()
				.addListener((ov, oldState, newState) -> {
					if (Worker.State.SUCCEEDED != newState)
						return;
					log.debug("Cargado la pagina html");
					JSObject window = (JSObject) webEngine.executeScript("window");
					window.setMember("javaConnector", javaConnector);

				});
		webEngine.load(getClass().getResource("/graphics/Charts.html")
				.toExternalForm());
	}

}
