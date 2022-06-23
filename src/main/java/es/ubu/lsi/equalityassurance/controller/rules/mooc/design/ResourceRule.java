package es.ubu.lsi.equalityassurance.controller.rules.mooc.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.DataBase;
import es.ubu.lsi.equalityassurance.model.ModuleType;

public class ResourceRule extends BasicRule {

	private static final Set<ModuleType> RESOURCES = new HashSet<>(Arrays.asList(ModuleType.BOOK, ModuleType.FILE,
			ModuleType.FOLDER, ModuleType.LABEL, ModuleType.IMS_PACKAGE, ModuleType.PAGE, ModuleType.URL));

	@Override
	public boolean apply(DataBase dataBase) {
		for (ModuleType moduleType : RESOURCES) {
			boolean value = dataBase.getModules()
					.getValues()
					.stream()
					.anyMatch(m -> m.getModuleType() == moduleType);
			if (!value) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public List<Object> reasonFailPopup(DataBase dataBase) {
		List<Object> list = new ArrayList<>();
		for (ModuleType moduleType : RESOURCES) {
			boolean value = dataBase.getModules()
					.getValues()
					.stream()
					.anyMatch(m -> m.getModuleType() == moduleType);
			if (!value) {
				list.add(moduleType.getModName());
			}
		}
		
		return list;
	}
}
