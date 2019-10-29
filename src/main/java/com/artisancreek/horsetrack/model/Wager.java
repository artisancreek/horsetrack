package com.artisancreek.horsetrack.model;

public class Wager {

  private int denomination;
  private int billCount;

  public Wager(int denomination, int billCount) {
    this.denomination = denomination;
    this.billCount = billCount;
  }

  public int getDenomination() {
    return denomination;
  }

  public void setDenomination(int denomination) {
    this.denomination = denomination;
  }

  public int getBillCount() {
    return billCount;
  }

  public void setBillCount(int billCount) {
    this.billCount = billCount;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Wager{");
    sb.append("denomination=").append(denomination);
    sb.append(", billCount=").append(billCount);
    sb.append('}');
    return sb.toString();
  }
}
