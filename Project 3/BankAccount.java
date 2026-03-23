/*
 * Author: Eric Gonzalez-Montijo
 * Email: gonzlezmonti@wisc.edu
 * Course: Computer Science 300, Spring 2026
 * Assignment:  3.7 Project 3 - Among Us
 * Citations: No LLM used, Canvas Style Guide, 
 * Collaboration with Gavin Garrison, Arrays and Methods JavaDoc, 
 * Types of Exceptions JavaDoc
 */


/**
  * Handles all of the changings of a bank account
  * Includes depositing, withdrawing, fetching information, etc. This program
  * also preforms exception handling and throwing to make sure that bugs do not
  * affect the accounts themselves.
  */
public class BankAccount {
  // 8-digit account number 
  private String accountNumber;

  // Name of the account holder ex: "John Smith", can not be null or empty
  private double balance;

  // The balance that the account starts with, can not be negative
  private String accountHolderName;

  /**
    * Constructor for BankAccount with account number, balance, and name 
    * 
    * @param accountNumber as an 8 digit String
    * @param accountHolderName is the name of the account holder
    * @param initialBalance is the double representing the balance when the account is created
    * @throws InvalidAccountException if the balance is not 8 digits
    * @throws IllegalArgumentException if the holder name is null/empty 
    * or if initialBalance is negative
    */
  public BankAccount (String accountNumber, String accountHolderName, double initialBalance) {
    try {
      int tempAccountNunmber = Integer.parseInt(accountNumber);
    } catch (NumberFormatException e) {
      throw new InvalidAccountException("Account Number must be eight digits.");
    }

    if (accountNumber.length() != 8) {
      throw new InvalidAccountException("Account number can not be greater than 8.");
    }

    if (accountHolderName == null || accountHolderName.trim().isEmpty()) {
      throw new IllegalArgumentException("Account name can not be null or empty.");
    }
    
    if (initialBalance < 0) {
      throw new IllegalArgumentException("Initial balance can not be negative.");
    }

    this.accountNumber = accountNumber;
    this.accountHolderName = accountHolderName;
    this.balance = initialBalance;
  }

  /**
  * Method for depositing money into a bank account
  * 
  * @param amount double representing the amount being put into an account
  * @throws IllegalArgumentException if the account is negative
  */
  public void deposit(double amount) {
    // TODO: Throw IllegalArgumentException for negative amounts
    if (amount < 0) {
      throw new IllegalArgumentException("Amount can not be negative");
    }

    balance += amount;
  }

  /**
  * Method for withdrawing money from a bank account
  * 
  * @param amount double representing the amount being taken out of an account
  * @throws IllegalArgumentException if the account is negative
  * @throws InsufficientFundsException if the amount is greater than the balance of the account
  */
  public void withdraw(double amount) throws InsufficientFundsException {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount can not be negative.");
    }

    if (amount > balance) {
      throw new InsufficientFundsException("Amount can not be greater than balance");
    }

    balance -= amount;
  }

  /**
  * Method for fetching the balance of an account
  * 
  * @return balance the amount of money in an account
  */
  public double getBalance() {
    return balance;
  }

  /**
  * Method for fetching the ID number of an account
  * 
  * @return accountNumber the the ID number of an account
  */
  public String getAccountNumber() {
    return accountNumber;
  }

  /**
  * Method for fetching the name for an account
  * 
  * @return accountHolderName the name ascribed to an account
  */
  public String getAccountHolderName() {
    return accountHolderName;
  }

  /**
  * Returns a String of infromation of an account
  * 
  * @return a String containing the acccount number, holder name, and balance of an account 
  */
  public String toString() {
    return String.format(
      "Account: %s, Holder: %s, Balance: $%.2f",
      accountNumber,
      accountHolderName,
      balance
    );
  }
}