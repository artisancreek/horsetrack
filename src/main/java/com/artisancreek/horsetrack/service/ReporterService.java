package com.artisancreek.horsetrack.service;

import com.artisancreek.horsetrack.model.Horse;
import com.artisancreek.horsetrack.model.Inventory;
import com.artisancreek.horsetrack.repository.HorseRepository;
import com.artisancreek.horsetrack.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporterService {

  public ReporterService() {
  }

  @Autowired
  private HorseRepository horseRepository;

  @Autowired
  private InventoryRepository inventoryRepository;

  @Value("${currency.symbol}")
  private String currencySymbol;

  // Messages
  @Value("${message.inventory}")
  private String messageInventory;
  @Value("${message.horses}")
  private String messageHorses;
  @Value("${message.payout}")
  private String messagePayout;
  @Value("${message.dispensing}")
  private String messageDispensing;

  // Error Messages
  @Value("${error.message.insufficient.funds}")
  private String errorMessageInsufficientFunds;
  @Value("${error.message.invalid.bet}")
  private String errorMessageInvalidBet;
  @Value("${error.message.invalid.command}")
  private String errorMessageInvalidCommand;
  @Value("${error.message.invalid.horse.number}")
  private String errorMessageInvalidHorseNumber;
  @Value("${error.message.no.payout}")
  private String errorMessageNoPayout;

  public void printHorses() {

    Iterable<Horse> horses = horseRepository.findAll();
    System.out.println(messageHorses);
    horses.forEach((horse) -> {
      System.out.println(horse.getHorseNumber()
          +","+horse.getHorseName()
          +","+horse.getOdds()
          +","+horse.getRaceStatus().toString().toLowerCase());
    });
  }

  public void printInventory() {

    Iterable<Inventory> inventories = inventoryRepository.findAll();
    System.out.println(messageInventory);
    inventories.forEach((inventory) -> {
      System.out.println(currencySymbol
          +inventory.getDenomination()
          +","+inventory.getBillCount());
    });
  }

  public void printInvalidCommand(String command) {
    System.out.println(errorMessageInvalidCommand + " " + command);
  }

  public void printErrorMessage(String message) {
    System.out.println(message);
  }
  public void startup() {
    printInventory();
    printHorses();
  }

}
