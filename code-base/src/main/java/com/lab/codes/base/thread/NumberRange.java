package com.lab.codes.base.thread;

//@NotThreadSafe
public class NumberRange {

  private int lower, upper;

  public NumberRange(int lower, int upper) {
    this.lower = lower;
    this.upper = upper;
  }

  public int getLower() {
    return lower;
  }

  public int getUpper() {
    return upper;
  }

  public void setLower(int value) {
    if (value > upper) {
      throw new IllegalArgumentException("");
    }
    lower = value;
  }

  public void setUpper(int value) {
    if (value < lower) {
      throw new IllegalArgumentException("");
    }
    upper = value;
  }
}
