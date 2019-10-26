package com.artisancreek.horsetrack.controller;

public class CommandActionExecutor {

  public <T> T executeCommandAction(CommandAction commandAction) {

   return commandAction.execute();

  }

}
