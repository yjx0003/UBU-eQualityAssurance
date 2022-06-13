package es.ubu.lsi.equalityassurance.controller.fxml;



import java.util.HashMap;
import java.util.Map;

import es.ubu.lsi.equalityassurance.controller.rules.Rule;
import es.ubu.lsi.equalityassurance.controller.rules.ubucev.RootUbucevRule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.view.Table;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lombok.Getter;

@Getter
public class WebViewContentController {
	
	@FXML
	private TabPane tabPaneWebViews;
	
	@FXML
	private Tab tabUbucev;
	
	private Map<Tab, Rule> rootRules = new HashMap<>();
	
	private MainController mainController;
	
	public void init(MainController mainController) {
		this.mainController = mainController;
		initRules();
		
		for(Tab tab: tabPaneWebViews.getTabs()) {
			initWebView((WebView)tab.getContent());
		}
		tabPaneWebViews.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) ->{
			
		});
		
		
	}

	private void initRules() {
		rootRules.put(tabUbucev, new RootUbucevRule());
	}
	
	private void initWebView(WebView webView) {
		WebEngine webEngine = webView.getEngine();
		webEngine.load(getClass().getResource("/graphics/Charts.html")
				.toExternalForm());
	}
	
	public void update(DataBase dataBase) {
		Tab selectedTab = tabPaneWebViews.getSelectionModel().getSelectedItem();
		WebView webView = (WebView)selectedTab.getContent();
		Table table = new Table(dataBase);
		table.updateTable(webView.getEngine(), rootRules.get(selectedTab));
	}

	
}
