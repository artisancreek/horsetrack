package com.artisancreek.horsetrack.controller;

public class CommandActionRestock implements CommandAction {

  private final AccessorMode accessorMode;

  public CommandActionRestock(AccessorMode accessorMode) {
    this.accessorMode = accessorMode;
  }

  @Override
  public void execute() {
    accessorMode.restock();
  }
}
