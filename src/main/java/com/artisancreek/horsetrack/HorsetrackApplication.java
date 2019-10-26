package com.artisancreek.horsetrack;

import com.artisancreek.horsetrack.controller.KioskMode;
import com.artisancreek.horsetrack.repository.HorseRepository;
import com.artisancreek.horsetrack.repository.InventoryRepository;
import com.artisancreek.horsetrack.service.HorseService;
import com.artisancreek.horsetrack.service.ReporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class HorsetrackApplication implements CommandLineRunner {

  @Autowired
  private ConfigurableApplicationContext context;

  @Autowired
  HorseRepository horseRepository;

  @Autowired
  InventoryRepository inventoryRepository;

  @Autowired
  HorseService horseService;

  @Autowired
  ReporterService reporterService;

  @Autowired
  KioskMode kioskMode;

  public static void main(String[] args) {
    SpringApplication.run(HorsetrackApplication.class, args);
  }

  @Override
  public void run(String... strings) throws Exception {

    kioskMode.initialize();

    kioskMode.printStartupMessages();

    Scanner commandString = new Scanner(System.in);
    while (!(kioskMode.quit())) {

      // Read from the command line
      // validate the input
      kioskMode.execute(commandString.nextLine());

//      reporterService.printHorses();
//      kioskMode.winner(3);
    }

    System.exit(SpringApplication.exit(context));

  }
}
