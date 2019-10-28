package com.artisancreek.horsetrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CommandService {

  @Value("${error.message.invalid.bet}")
  private String errorMessageInvalidBet;
  @Value("${error.message.invalid.horse.number}")
  private String errorMessageInvalidHorseNumber;

  private int betHorseNumber;
  private String currentCommand;
  private int wagerAmount;
  private int winningHorseNumber;

  private String errorMessage;

  @Autowired
  ReporterService reporterService;

  @Autowired
  HorseService horseService;

  @Autowired
  InventoryService inventoryService;

  public CommandService() {
  }

  public void execute(String[] command) {
  }

//  public boolean isValidCommand(String commandLine) {
//    String winnerCommand = "[W,w] [1-"+maxHorses+"]";
//
//    // Split the command line into potential parts
//    if (commandLine.equalsIgnoreCase("q")) {
//      return true;
//    } else if (commandLine.equalsIgnoreCase("r")) {
//      return true;
//    } else if (commandLine.matches(winnerCommand)) {
//      return true;
//    } else if (commandLine.matches("[0-9]+ [0-9]+.?[0-9]*")) {
//      return true;
//    } else {
//      return false;
//    }
//  }

  public String parseCommand(String commandLine) {

    // Split the command line into its component parts
    String[] commandComponents = Arrays.stream(commandLine.split(" "))
        .map(String::trim)
        .toArray(String[]::new);

    if (commandComponents[0].equalsIgnoreCase("q")) {
      currentCommand = "quit";
      return currentCommand;
    } else if (commandComponents[0].equalsIgnoreCase("r")) {
      currentCommand = "restock";
      return currentCommand;
    } else if (commandLine.matches("[W,w] [1-9]")) {
      winningHorseNumber = Integer.parseInt(commandComponents[1]);
      if (horseService.isValidHorseNumber(winningHorseNumber)) {
        currentCommand = "winner";
        return currentCommand;
      } else {
        winningHorseNumber = 0;
        errorMessage = errorMessageInvalidHorseNumber + " " + commandComponents[1];
        currentCommand = "error";
        return currentCommand;
      }
    } else if (commandLine.matches("[0-9]+ [0-9]+.?[0-9]*")) {
      betHorseNumber = Integer.parseInt(commandComponents[0]);
      if (!(horseService.isValidHorseNumber(betHorseNumber))) {
        betHorseNumber = 0;
        errorMessage = errorMessageInvalidHorseNumber + " " + commandComponents[0];
        currentCommand = "error";
        return currentCommand;
      }
      try {
        wagerAmount = Integer.parseInt(commandComponents[1]);
      } catch (NumberFormatException e) {
        errorMessage = errorMessageInvalidBet + " " + commandComponents[1];
        currentCommand = "error";
        return currentCommand;
      }
      currentCommand = "wager";
      return currentCommand;
    } else {
      currentCommand = "invalid";
      return currentCommand;
    }
  }

  public String getCurrentCommand() {
    return currentCommand;
  }

  public int getBetHorseNumber() {
    return betHorseNumber;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public int getWagerAmount() {
    return wagerAmount;
  }

  public int getWinningHorseNumber() {
    return winningHorseNumber;
  }

}
