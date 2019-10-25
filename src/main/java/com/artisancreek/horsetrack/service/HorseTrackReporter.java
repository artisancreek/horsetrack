package com.artisancreek.horsetrack.service;

import com.artisancreek.horsetrack.model.Horse;
import com.artisancreek.horsetrack.model.Inventory;
import com.artisancreek.horsetrack.repository.HorseRepository;
import com.artisancreek.horsetrack.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorseTrackReporter {

  public HorseTrackReporter() {
  }

  @Autowired
  private HorseRepository horseRepository;

  @Autowired
  private InventoryRepository inventoryRepository;

  public void printHorses() {

    Iterable<Horse> horses = horseRepository.findAll();
    System.out.println("Horses:");
    horses.forEach((horse) -> {
      System.out.println(horse.getHorseNumber()
          +","+horse.getHorseName()
          +","+horse.getOdds()
          +","+horse.getRaceStatus().toString().toLowerCase());
    });
  }

  public void printInventory() {

    List<Inventory> inventories = inventoryRepository.findAll();
    System.out.println("Inventory:");
    inventories.forEach((inventory) -> {
      System.out.println("$"
          +inventory.getDenomination()
          +","+inventory.getBillCount());
    });
  }

  public void startup() {
    printInventory();
    printHorses();
  }

}
