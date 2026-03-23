/*
 * Author: Eric Gonzalez-Montijo
 * Email: gonzlezmonti@wisc.edu
 * Course: Computer Science 300, Spring 2026
 * Assignment:  3.7 Project 3 - Among Us
 * Citations: No LLM used, Canvas Style Guide, 
 * Collaboration with Gavin Garrison, Arrays and Methods JavaDoc, 
 * Types of Exceptions JavaDoc
 */


import java.util.ArrayList;


/**
  * Represents a system that handles multiple accounts at one through an arraylist
  * 
  * This class allows user to create, find, and transfer money into accounts. 
  * This class also calculates the total balance across various accounts. 
  */
public class BankingSystem {
  // ArrayList where the BankAccount instances are stored
  private ArrayList<BankAccount> accounts;


  /**
  * BankingSystem constructor 
  */
  public BankingSystem() {
    accounts = new ArrayList<BankAccount>();
  }

  /**
  * Creates a new BankAccount instance and adds it into the ArrayList
  * 
  * @param accountNumber ID as an 8 digit String
  * @param name the name ascribed to the account
  * @param initialDeposit the inital balance of the account
  * @throws InvalidAccountException when account numbers match or is invalid 
  * @throws IllegalArgumentException if the deposit being 
  * attempted is negative or the name is invalid
  */
  public void createAccount(String accountNumber, String name, double initialDeposit) {
    for (BankAccount account : accounts) {
      if (account.getAccountNumber().equals(accountNumber)) {
        throw new InvalidAccountException("Duplicate");
      }
    }

    try {
      BankAccount account = new BankAccount(accountNumber, name, initialDeposit);
      accounts.add(account);
    } catch (InvalidAccountException | IllegalArgumentException e) {
      System.out.println(e.getMessage());
      throw e;
    }
  }

  /**
  * finds an account using its ID number
  * 
  * @param accountNumber ID as an 8 digit String
  * @return the account object instance 
  * @throws IllegalArgumentException when the amount is negative or when attmepting to transfer
  * to the same account
  * @throws InvalidAccountException if the account does not exist
  * @throws InsufficientFundsException if the balance is not enough
  */
  public BankAccount findAccount(String accountNumber) {
    for (BankAccount account : accounts) {
      if (account.getAccountNumber().equals(accountNumber)) {
        return account;
      }
    }
    
    throw new InvalidAccountException("Account not found"); 
  }

  /**
  * Transfers money from one account to another 
  * 
  * @param fromAccountNum the account the money is being taken from
  * @param toAccountNum the account the money is being sent to 
  * @param amount the amount of money being sent from one account to another
  * @throws IllegalArgumentException 
  */
  public void transferMoney(
      String fromAccountNum, String toAccountNum, double amount) throws InsufficientFundsException {

    if (amount <= 0) {
      throw new IllegalArgumentException("Must be positive");
    }

    if (fromAccountNum.equals(toAccountNum)) {
      throw new IllegalArgumentException("No transfer between the same accounts");
    }

    BankAccount fromAccount = findAccount(fromAccountNum);
    BankAccount toAccount = findAccount(toAccountNum);

    if (fromAccount.getBalance() < amount) {
      throw new InsufficientFundsException("Amount can not be greater than balance");
    }

    fromAccount.withdraw(amount);
    toAccount.deposit(amount);
  }

  /**
  * Shows user the information of an account
  * 
  * @param accountNumber the ID number of the account
  * @throws InvalidAccountException when account does not exist 
  */
  public void displayAccountInfo(String accountNumber) {
    try {
      BankAccount account = findAccount(accountNumber);
      System.out.println(account.toString());
    } catch (InvalidAccountException e) {
      System.out.println(e.getMessage());
      throw e;
    }
    
  }

  /**
  * Calculates the total balance of all accounts
  * 
  * @return the total amount 
  */
  public double getTotalBankBalance() {
    double total = 0;
    for (BankAccount account : accounts) {
      total += account.getBalance();
    }
    return total;
  }
}