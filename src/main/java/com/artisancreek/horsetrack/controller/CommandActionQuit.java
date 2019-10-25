package com.artisancreek.horsetrack.controller;

public class CommandActionQuit implements CommandAction {

  private final AccessorMode accessorMode;

  public CommandActionQuit(AccessorMode accessorMode) {
    this.accessorMode = accessorMode;
  }

  @Override
  public void execute() {
    accessorMode.quit();
  }
}
