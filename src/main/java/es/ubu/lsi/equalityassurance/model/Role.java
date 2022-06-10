package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	private int roleId;
	private String roleName;
	private String roleShortName;


	public Role(int id) {
		setRoleId(id);
	}

	@Override
	public String toString() {
		if(roleName!= null && roleName.trim().isEmpty()) {
			return roleShortName;
		}else {
			return roleName;
		}
		
	}






}
