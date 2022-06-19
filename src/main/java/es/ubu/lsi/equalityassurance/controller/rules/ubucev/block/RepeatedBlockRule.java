package es.ubu.lsi.equalityassurance.controller.rules.ubucev.block;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import es.ubu.lsi.equalityassurance.controller.rules.BasicRule;
import es.ubu.lsi.equalityassurance.model.Block;
import es.ubu.lsi.equalityassurance.model.DataBase;

public class RepeatedBlockRule extends BasicRule{

	@Override
	public boolean apply(DataBase dataBase) {
		List<Integer> blocks = dataBase.getBlocks().getValues().stream().map(Block::getInstanceid).collect(Collectors.toList());
		return blocks.size() == new HashSet<Integer>(blocks).size();
	}

}
