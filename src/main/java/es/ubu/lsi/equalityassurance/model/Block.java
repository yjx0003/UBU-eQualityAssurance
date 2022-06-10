package es.ubu.lsi.equalityassurance.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Block implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	private int instanceid;
	private String name;
	private String region;
	private boolean collapsible;
	private boolean dockable;
	private int weight;
	private boolean visible;
	
	public Block(int instanceid) {
		this.instanceid = instanceid;
	}
	
	
	
	
}
