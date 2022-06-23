package es.ubu.lsi.moodlerestapi.webservice.api.mod.assign;

import java.util.Collection;

import es.ubu.lsi.moodlerestapi.webservice.webservices.WSFunctionAbstract;
import es.ubu.lsi.moodlerestapi.webservice.webservices.WSFunctionEnum;

public class ModAssignGetSubmissions extends WSFunctionAbstract{

	public ModAssignGetSubmissions() {
		super(WSFunctionEnum.MOD_ASSIGN_GET_SUBMISSIONS);
	}
	
	public void setAssignmentIds(Collection<Integer> assignmentids) {
		parameters.put("assignmentids", assignmentids);
	}

}
