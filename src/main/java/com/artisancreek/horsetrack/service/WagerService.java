package com.artisancreek.horsetrack.service;


import com.artisancreek.horsetrack.model.Inventory;
import com.artisancreek.horsetrack.model.Wager;
import com.artisancreek.horsetrack.repository.HorseRepository;
import com.artisancreek.horsetrack.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WagerService {

  final int ONE = 1;
  final int FIVE = 5;
  final int TEN = 10;
  final int TWENTY = 20;
  final int HUNDRED = 100;

  @Autowired
  private HorseRepository horseRepository;

  @Autowired
  private InventoryRepository inventoryRepository;

  @Autowired
  private InventoryService inventoryService;

  public int calculateAmountWon(int wager, int odds) {
    return wager * odds;
  }

  public List<Wager> dispenseWinnings(int winnings) {

    inventoryService.decrementInventory(HUNDRED, winnings/HUNDRED);
    Wager hundred = new Wager(HUNDRED, winnings/HUNDRED);
    winnings = winnings % HUNDRED;

    inventoryService.decrementInventory(TWENTY, winnings/TWENTY);
    Wager twenty = new Wager(TWENTY, winnings/TWENTY);
    winnings = winnings % TWENTY;

    inventoryService.decrementInventory(TEN, winnings/TEN);
    Wager ten = new Wager(TEN, winnings/TEN);
    winnings = winnings % TEN;

    inventoryService.decrementInventory(FIVE, winnings/FIVE);
    Wager five = new Wager(FIVE, winnings/FIVE);
    winnings = winnings % FIVE;

    inventoryService.decrementInventory(ONE, winnings);
    Wager one = new Wager(ONE, winnings);

    List<Wager> denominations = new ArrayList<>();
    denominations.add(one);
    denominations.add(five);
    denominations.add(ten);
    denominations.add(twenty);
    denominations.add(hundred);

    return denominations;

  }

}
