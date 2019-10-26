package com.artisancreek.horsetrack.controller;

import com.artisancreek.horsetrack.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KioskMode implements AccessorMode {

  private boolean quit = false;

  public KioskMode() {
  }

  @Autowired
  HorseService horseService;

  @Autowired
  InventoryService inventoryService;

  @Autowired
  ConfigService configService;

  @Autowired
  CommandService commandService;

  @Autowired
  ReporterService reporterService;

  @Override
  public void execute(String commandLine) {
    System.out.println("Command issued: "+commandLine);
    restock();
    quit = true;
  }

  @Override
  public boolean quit() {
    System.out.println("quit");
    return quit;
  }

  @Override
  public void restock() {
    System.out.println("restock");
  }

  @Override
  public void wager() {
    System.out.println("wager");
  }

  @Override
  public void winner(int horseNumber) {
    System.out.println("winner: " + horseNumber);
    horseService.setRaceWinner(horseNumber);

  }

  @Override
  public void printStartupMessages() {
    reporterService.startup();
  }

  @Override
  public void initialize() {
    configService.startup();
  }

}
