package es.ubu.lsi.equalityassurance.controller.rules.ubucev.themes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.CourseModule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.ModuleType;
import es.ubu.lsi.moodlerestapi.webservice.webservices.Connection;
import okhttp3.Response;

public class ResourcesAvailableRule extends BasicRule {

	@Override
	public boolean apply(DataBase dataBase) {
		List<String> urls = dataBase.getModules()
				.getValues()
				.stream()
				.filter(cm -> cm.getModuleType() == ModuleType.URL)
				.map(CourseModule::getUrl)
				.collect(Collectors.toList());
		
		for(String url:urls) {
			try(Response response = Connection.getResponse(url)){
				if(!response.isSuccessful()) {
					return false;
				}
			} catch (IOException e) {
				return false;
			}
		}
		return true;
	}
	@Override
	public List<Object> reasonFailPopup(DataBase dataBase) {
		List<CourseModule> courseModules = dataBase.getModules()
				.getValues()
				.stream()
				.filter(cm -> cm.getModuleType() == ModuleType.URL)
				.collect(Collectors.toList());
		List<Object> courseModuleNames = new ArrayList<>();
		for(CourseModule module:courseModules) {
			try(Response response = Connection.getResponse(module.getUrl())){
				if(!response.isSuccessful()) {
					courseModuleNames.add(module.getModuleName());
				}
			} catch (IOException e) {
				courseModuleNames.add(module.getModuleName());
			}
		}
		
		return courseModuleNames;
	}
}
