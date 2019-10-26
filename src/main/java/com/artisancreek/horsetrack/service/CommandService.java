package com.artisancreek.horsetrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

  @Autowired
  ReporterService reporterService;

  public CommandService() {
  }

  public void execute(String[] command) {

  }
}
