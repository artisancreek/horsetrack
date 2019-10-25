package com.artisancreek.horsetrack.controller;

public class KioskMode implements AccessorMode {

  @Override
  public boolean quit() {
    return true;
  }

  @Override
  public void restock() {
    System.out.println("restock");
  }

  @Override
  public void wager() {

  }

  @Override
  public void winner(int horse) {
    System.out.println("winner: " + horse);
  }

}
