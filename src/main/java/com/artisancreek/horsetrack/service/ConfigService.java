package com.artisancreek.horsetrack.service;

import com.artisancreek.horsetrack.RaceStatus;
import com.artisancreek.horsetrack.controller.*;
import com.artisancreek.horsetrack.model.Horse;
import com.artisancreek.horsetrack.model.Inventory;
import com.artisancreek.horsetrack.repository.HorseRepository;
import com.artisancreek.horsetrack.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

  @Autowired
  private HorseRepository horseRepository;

  @Autowired
  private InventoryRepository inventoryRepository;

  public ConfigService() {
  }

  public void loadHorses() {

    horseRepository.save(new Horse(1, "That Darn Gray Cat", 5, RaceStatus.WON));
    horseRepository.save(new Horse(2, "Fort Utopia", 10, RaceStatus.LOST));
    horseRepository.save(new Horse(3, "Count Sheep", 9, RaceStatus.LOST));
    horseRepository.save(new Horse(4, "Ms Traitour", 4, RaceStatus.LOST));
    horseRepository.save(new Horse(5, "Real Princess", 3, RaceStatus.LOST));
    horseRepository.save(new Horse(6, "Pa Kettle", 5, RaceStatus.LOST));
    horseRepository.save(new Horse(7, "Gin Stinger", 6, RaceStatus.LOST));

  }

  public void loadInventory() {
    inventoryRepository.save(new Inventory(1,10));
    inventoryRepository.save(new Inventory(5,10));
    inventoryRepository.save(new Inventory(10,10));
    inventoryRepository.save(new Inventory(20,10));
    inventoryRepository.save(new Inventory(100,10));
  }

  public void setWinner() {

//    CommandAction commandAction = new CommandActionWinner(new KioskMode(), 1);
//
//    CommandActionExecutor commandActionExecutor = new CommandActionExecutor();
//    commandActionExecutor.executeCommandAction(commandAction);

  }

  public void startup() {

    loadHorses();
    loadInventory();
//    setWinner();

  }



}
