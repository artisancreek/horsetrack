package com.artisancreek.horsetrack.controller;

public interface AccessorMode {

  public boolean quit();
  public void restock();
  public void wager();
  public void winner(int horse);

}
