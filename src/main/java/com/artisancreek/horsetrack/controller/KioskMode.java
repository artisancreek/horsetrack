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
    if ((commandService.parseCommand(commandLine).equalsIgnoreCase("invalid"))) {
      reporterService.printInvalidCommand(commandLine);
    } else {
      commandFactory(commandLine);
    }

  }

  private void commandFactory(String commandLine) {
    String command = commandService.parseCommand(commandLine);

    switch (command) {
      case "quit":
        quit = true;
        break;
      case "restock":
        restock();
        break;
      case "winner":
        winner(commandService.getWinningHorseNumber());
        break;
      case "wager":
        wager(commandService.getBetHorseNumber(), commandService.getWagerAmount());
        break;
      case "error":
        reporterService.printErrorMessage(commandService.getErrorMessage());
        break;
      default:
        // Do nothing
    }

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
  public void wager(int horseNumber, int wagerAmount) {
    System.out.println("wager: "+ horseNumber + "," + wagerAmount);
  }

  @Override
  public void winner(int horseNumber) {
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
