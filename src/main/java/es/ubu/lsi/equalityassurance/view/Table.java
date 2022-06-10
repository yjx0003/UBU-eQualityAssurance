package es.ubu.lsi.equalityassurance.view;

import java.util.List;

import es.ubu.lsi.equalityassurance.controller.rules.AllRules;
import es.ubu.lsi.equalityassurance.controller.rules.Rule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.util.I18n;
import es.ubu.lsi.equalityassurance.util.JSArray;
import es.ubu.lsi.equalityassurance.util.JSObject;
import javafx.scene.web.WebEngine;

public class Table {

	private DataBase dataBase;

	public Table(DataBase dataBase) {
		this.dataBase = dataBase;
	}

	public void updateTable(WebEngine webEngine) {
		Rule allRules = new AllRules();
		JSArray tabledata = new JSArray();
		leafRules(tabledata, allRules);
		JSObject data = new JSObject();
		data.put("columns", createColumns());
		data.put("tabledata", tabledata);
		webEngine.executeScript(String.format("updateTabulator(%s, %s)", data, getOptions()));

	}

	private JSArray createColumns() {
		JSObject jsObject = new JSObject();
		JSArray array = new JSArray();

		jsObject.putWithQuote("title", I18n.get("chartlabel.name"));
		jsObject.put("tooltip", true);
		jsObject.put("field", "'name'");
		array.add(jsObject);

		jsObject = new JSObject();
		jsObject.putWithQuote("title", I18n.get("chartlabel.progress"));
		jsObject.putWithQuote("field", "value");
		jsObject.putWithQuote("formatter", "progress");
		jsObject.put("formatterParams", getProgressParam());
		array.add(jsObject.toString());
		
		jsObject = new JSObject();
		jsObject.putWithQuote("title", I18n.get("chartlabel.reasonFail"));
		jsObject.put("tooltip", true);
		jsObject.put("field", "'reasonFail'");
		array.add(jsObject);
		return array;
	}

	private String getProgressParam() {
		JSObject jsObject = new JSObject();
		jsObject.put("min", 0);
		jsObject.put("max", 1);

		jsObject.put("legend", "function(value){return Math.round(value*100||0)+'%';}");

		jsObject.putWithQuote("hozAlign", "center");
		JSArray jsArray = new JSArray();

		jsArray.add("'rgba(247,136,128,1.0)'");
		jsArray.add("'rgba(244,227,174,1.0)'");
		jsArray.add("'rgba(255,240,51,1.0)'");
		jsArray.add("'rgba(181,255,51,1.0)'");
		jsObject.put("color", jsArray);
		return jsObject.toString();
	}

	private JSObject getOptions() {
		JSObject jsObject = new JSObject();
		jsObject.put("height", "'95%'");
		jsObject.put("dataTree", true);
		jsObject.put("dataTreeStartExpanded", true);
		
		return jsObject;

	}

	private void leafRules(JSArray jsArray, Rule rule) {
		JSObject jsObject = new JSObject();
		jsArray.add(jsObject);
		jsObject.putWithQuote("name", I18n.get(rule.getName()));
		List<Rule> leafRules = rule.getChildrenRules();

		jsObject.put("value", rule.getValue(dataBase));
		
		if (!leafRules.isEmpty()) {

			JSArray children = new JSArray();
			jsObject.put("_children", children);
			for (Rule leafRule : leafRules) {

				leafRules(children, leafRule);
			}
		} else if(!rule.apply(dataBase)) {
			jsObject.putWithQuote("reasonFail", rule.reasonFail(dataBase));
		}
	}
}
