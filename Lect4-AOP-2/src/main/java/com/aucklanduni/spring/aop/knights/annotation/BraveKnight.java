package com.aucklanduni.spring.aop.knights.annotation;

public class BraveKnight implements Knight {
  private Quest _quest;
  
  public BraveKnight(Quest quest) {
    _quest = quest;
  }
  
  public void embarkOnQuest() throws QuestException {
    _quest.embark();
  }
}
