package com.artisancreek.horsetrack;

import com.artisancreek.horsetrack.controller.*;
import com.artisancreek.horsetrack.repository.HorseRepository;
import com.artisancreek.horsetrack.repository.InventoryRepository;
import com.artisancreek.horsetrack.service.ConfigService;
import com.artisancreek.horsetrack.service.HorseTrackReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HorsetrackApplication implements CommandLineRunner {

  @Autowired
  HorseRepository horseRepository;

  @Autowired
  InventoryRepository inventoryRepository;

  @Autowired
  ConfigService configService;

  @Autowired
  HorseTrackReporter horseTrackReporter;

  public static void main(String[] args) {
    SpringApplication.run(HorsetrackApplication.class, args);
  }

  @Override
  public void run(String... strings) throws Exception {

    configService.startup();

    horseTrackReporter.startup();

    boolean isQuit = false;
    while (!(isQuit)) {

      // Read from the command line
      CommandAction commandAction = new CommandActionQuit(new KioskMode());

      // validate the input

      // execute the command
      CommandActionExecutor commandActionExecutor = new CommandActionExecutor();
      commandActionExecutor.executeCommandAction(commandAction);

    }


  }
}
