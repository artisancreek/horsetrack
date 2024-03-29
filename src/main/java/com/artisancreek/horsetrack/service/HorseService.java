package com.artisancreek.horsetrack.service;

import com.artisancreek.horsetrack.RaceStatus;
import com.artisancreek.horsetrack.model.Horse;
import com.artisancreek.horsetrack.repository.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorseService {

  @Autowired
  private HorseRepository horseRepository;

  public String getHorseName(int horseNumber) {
    Horse horse = horseRepository.findByHorseNumberEquals(horseNumber);

    return horse.getHorseName();
  }

  public int getHorseOdds(int horseNumber) {
    Horse horse = horseRepository.findByHorseNumberEquals(horseNumber);

    return horse.getOdds();
  }

  public boolean isHorseWinner(int horseNumber) {
    Horse horse = horseRepository.findByHorseNumberEquals(horseNumber);
    if (horse.getRaceStatus() == RaceStatus.WON) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isValidHorseNumber(int horseNumber) {
    if (horseRepository.findByHorseNumberEquals(horseNumber) == null) {
      return false;
    } else {
      return true;
    }
  }

  public void setRaceWinner(int horseNumber) {

    List<Horse> horses = horseRepository.findAll();

    horses.stream().filter((horse)-> horse.getRaceStatus() == RaceStatus.WON)
      .forEach(losingHorse-> {
        losingHorse.setRaceStatus(RaceStatus.LOST);
        horseRepository.save(losingHorse);
      });

    horses.stream().filter((horse)-> horse.getHorseNumber() == horseNumber)
      .forEach(winningHorse-> {
        winningHorse.setRaceStatus(RaceStatus.WON);
        horseRepository.save(winningHorse);
      });

  }
}
