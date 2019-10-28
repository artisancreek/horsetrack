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

  @Autowired
  WagerService wagerService;

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
    return quit;
  }

  @Override
  public void restock() {
    inventoryService.restock();
    reporterService.printInventory();
  }

  @Override
  public void wager(int horseNumber, int wagerAmount) {
    if (horseService.isValidHorseNumber(horseNumber)) {
      int amountWon = wagerService.calculateAmountWon(
                        wagerAmount,
                        horseService.getHorseOdds(horseNumber));
      if (inventoryService.sufficientFunds(amountWon)) {
        if (horseService.isHorseWinner(horseNumber)) {
          reporterService.printPayout(horseService.getHorseName(horseNumber), amountWon);
          reporterService.printDispense(wagerService.dispenseWinnings(amountWon));
        } else {
          reporterService.printNoPayout(horseService.getHorseName(horseNumber));
        }
        reporterService.printInventory();
        reporterService.printHorses();
      } else {
        reporterService.printInsufficientFunds(amountWon);
      }
    } else {
      reporterService.printInvalidHorse(horseNumber);
    }
  }

  @Override
  public void winner(int horseNumber) {
    if (horseService.isValidHorseNumber(horseNumber)) {
      horseService.setRaceWinner(horseNumber);
      reporterService.printInventory();
      reporterService.printHorses();
    } else {
      reporterService.printInvalidHorse(horseNumber);
    }
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
