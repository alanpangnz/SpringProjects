package com.aucklanduni.spring.aop.knights;

public class JoustingQuest implements Quest {

	@Override
	public void embark() throws QuestException {
		throw new QuestException("Aghh! Knocked off the horse!");
		
	}
	
}
