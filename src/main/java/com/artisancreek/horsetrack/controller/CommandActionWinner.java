package com.artisancreek.horsetrack.controller;

public class CommandActionWinner implements CommandAction {

  private final AccessorMode accessorMode;
  private final int winner;

  public CommandActionWinner(AccessorMode accessorMode, int winner) {
    this.accessorMode = accessorMode;
    this.winner = winner;
  }

  @Override
  public Void execute() {
    accessorMode.winner(winner);
    return null;
  }
}
