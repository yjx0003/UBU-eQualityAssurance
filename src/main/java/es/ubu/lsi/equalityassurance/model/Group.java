package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase Group para distinguir los grupos que hay en un curso, así como los
 * grupos en los que se encuentra un usuario.
 * 
 * @author Yi Peng Ji
 * 
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Group implements Serializable {

	private static final long serialVersionUID = 1L;
	@EqualsAndHashCode.Include
	private int groupId;
	private String groupName;
	private String description;
	private DescriptionFormat descriptionFormat;
	

	public Group(int id) {
		setGroupId(id);
	}


	@Override
	public String toString() {
		return groupName;
	}


}
