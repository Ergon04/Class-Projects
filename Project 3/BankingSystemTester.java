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
* Provides tests for BankAccount and BankingSystem methods 
*
*/
public class BankingSystemTester {

  /**
   * This calls all the allTests method and prints out an appropriate
   * message.
   * @param args unused.
   */
  public static void main(String[] args) {
    if (allTests()) {
      System.out.println("All tests passed.");
    } else {
      System.out.println("At least one test failed.");
    }
  }

  /**
   * calls all the testing methods returning true if all pass,
   * false otherwise.
   */
  public static boolean allTests() {
    boolean allPassed = true;

    // BankAccount constructor tests
    allPassed &= testBankAccountConstructorValid();
    allPassed &= testBankAccountConstructorInvalidAccountNumber();
    allPassed &= testBankAccountConstructorNullName();
    allPassed &= testBankAccountConstructorEmptyName();
    allPassed &= testBankAccountConstructorNegativeBalance();

    // BankAccount deposit tests
    allPassed &= testDepositValid();
    allPassed &= testDepositNegativeAmount();

    // BankAccount withdraw tests
    allPassed &= testWithdrawValid();
    allPassed &= testWithdrawNegativeAmount();
    allPassed &= testWithdrawInsufficientFunds();

    // BankingSystem createAccount tests
    allPassed &= testCreateAccountValid();
    allPassed &= testCreateAccountDuplicate();

    // BankingSystem findAccount tests
    allPassed &= testFindAccountValid();
    allPassed &= testFindAccountNotFound();

    // BankingSystem transferMoney tests
    allPassed &= testTransferMoneyValid();
    allPassed &= testTransferMoneyNegativeAmount();
    allPassed &= testTransferMoneySameAccount();
    allPassed &= testTransferMoneyFromAccountNotFound();
    allPassed &= testTransferMoneyToAccountNotFound();
    allPassed &= testTransferMoneyInsufficientFunds();

    // BankingSystem displayAccountInfo tests
    allPassed &= testDisplayAccountInfoValid();
    allPassed &= testDisplayAccountInfoNotFound();

    return allPassed;
  }

  // =================== BankAccount Constructor Tests ===================

  /**
  * Tests whether BankAccount can create instances
  * 
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testBankAccountConstructorValid() {
    System.out.print("testBankAccountConstructorValid ");
    try {
      BankAccount account = new BankAccount("12345678", "John Doe", 100.0);
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
  * Checks that an invalid number throws an InvalidAccountException
  * 
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testBankAccountConstructorInvalidAccountNumber() {
    System.out.print("testBankAccountConstructorInvalidAccountNumber ");
    try {
      BankAccount account = new BankAccount("AJIODJWI", "Eric Eric", 100.0);
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false; 
    }
  }

  /**
  * Checks that an IllegalArgumentException is thrown if a 
  * null name is submitted
  * 
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testBankAccountConstructorNullName() {
    System.out.print("testBankAccountConstructorNullName ");
    try {
      BankAccount account = new BankAccount("12345678", null, 100.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
  * Checks that an IllegalArgumentException is thrown if an 
  * empty name is submitted
  * 
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testBankAccountConstructorEmptyName() {
    System.out.print("testBankAccountConstructorEmptyName ");
    try {
      BankAccount account = new BankAccount("12345678", "", 100.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
  * Checks that an IllegalArgumentException is thrown if a 
  * negative number is submitted
  * 
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testBankAccountConstructorNegativeBalance() {
    System.out.print("testBankAccountConstructorNegativeBalance ");
    try {
      BankAccount account = new BankAccount("12345678", "Eric Eric", -100.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  // =================== BankAccount Deposit Tests ===================

  /**
  * Tests whether BankAccount can successfully deposit
  * into a regular account
  * 
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testDepositValid() {
    System.out.print("testDepositValid ");
    try {
      BankAccount account = new BankAccount("12345678", "John Doe", 100.0);
      double originalBalance = account.getBalance();
      account.deposit(50.0);
      if (account.getBalance() == originalBalance + 50.0) {
        System.out.println("PASS");
        return true;
      } else {
        System.out.println("FAIL");
        return false;
      }
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
  * Checks that depositing a negative number yields an 
  * IllegalArgumentException
  * 
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testDepositNegativeAmount() {
    System.out.print("testDepositNegativeAmount ");
    try {
      BankAccount account = new BankAccount("12345678", "Eric Eric", 100.0);
      account.deposit(-50.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  // =================== BankAccount Withdraw Tests ===================
 
  /**
  * Tests whether BankAccount can successfully withdraw
  * from a regular account
  * 
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testWithdrawValid() {
    System.out.print("testWithdrawValid ");
    try {
      BankAccount account = new BankAccount("12345678", "John Doe", 100.0);
      double originalBalance = account.getBalance();
      account.withdraw(30.0);
      if (account.getBalance() == originalBalance - 30.0) {
        System.out.println("PASS");
        return true;
      } else {
        System.out.println("FAIL");
        return false;
      }
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
  * Checks that withdrawing a negative number yields an 
  * IllegalArgumentException
  * 
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testWithdrawNegativeAmount() {
    System.out.print("testWithdrawNegativeAmount ");
    BankAccount account = new BankAccount("12345678", "Eric Eric", 100.0);
    try {
      account.withdraw(-100.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
  * Checks that depositing a greater number than what 
  * the balance is yields an InsufficientFundsException
  * 
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testWithdrawInsufficientFunds() {
    System.out.print("testWithdrawNegativeAmount ");
    BankAccount account = new BankAccount("12345678", "Eric Eric", 100.0);
    try {
      account.withdraw(200.0);
      System.out.println("FAIL");
      return false;
    } catch (InsufficientFundsException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  // =================== BankingSystem CreateAccount Tests ===================

  /**
  * Checks that BankingSystem can successfully create an
  * ArrayList of BankAccounts
  *
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testCreateAccountValid() {
    System.out.print("testCreateAccountValid ");
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Jane Smith", 200.0);
      BankAccount account = system.findAccount("12345678");
      if (account != null && account.getBalance() == 200.0) {
        System.out.println("PASS");
        return true;
      } else {
        System.out.println("FAIL");
        return false;
      }
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
  * Checks that creating a duplicate account throws an 
  * InvalidAccountException
  *
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testCreateAccountDuplicate() {
    System.out.print("testCreateAccountDuplicate ");
    BankingSystem sys = new BankingSystem();
    try {
      sys.createAccount("12345678", "Jane Smith", 200.0);
      sys.createAccount("12345678", "Eric Eric", 100);
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
    
  }

  // =================== BankingSystem FindAccount Tests ===================

  /**
  * Checks that BankingSystem can find account based on
  * account number
  *
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testFindAccountValid() {
    System.out.print("testFindAccountValid ");
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Jane Smith", 200.0);
      BankAccount account = system.findAccount("12345678");
      if (account != null && account.getAccountNumber().equals("12345678")) {
        System.out.println("PASS");
        return true;
      } else {
        System.out.println("FAIL");
        return false;
      }
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
  * Tests that the findAccount method throws an
  * InvalidAccountException if an account is not found
  *
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testFindAccountNotFound() {
    System.out.print("testFindAccountNotFound ");
    try {
      BankingSystem sys = new BankingSystem();
      sys.createAccount("12345678", "Jane Smith", 200.0);
      sys.findAccount("02345678");
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false; 
    }
  }

  // =================== BankingSystem TransferMoney Tests ===================

  /**
  * Tests that BankingSystem can transfer money from 
  * one account to another 
  *
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testTransferMoneyValid() {
    System.out.print("testTransferMoneyValid ");
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Alice", 300.0);
      system.createAccount("87654321", "Bob", 100.0);

      system.transferMoney("12345678", "87654321", 50.0);

      BankAccount fromAccount = system.findAccount("12345678");
      BankAccount toAccount = system.findAccount("87654321");

      if (fromAccount.getBalance() == 250.0 && toAccount.getBalance() == 150.0) {
        System.out.println("PASS");
        return true;
      } else {
        System.out.println("FAIL");
        return false;
      }
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
  * Tests that the transfer method throws an IllegalArgumentException
  * if the amount listed is negative 
  *
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testTransferMoneyNegativeAmount() {
    System.out.print("testTransferMoneyNegativeAmount ");
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Alice", 300.0);
      system.createAccount("87654321", "Bob", 100.0);

      system.transferMoney("12345678", "87654321", -50.0);

      BankAccount fromAccount = system.findAccount("12345678");
      BankAccount toAccount = system.findAccount("87654321");
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
    
  }

  /**
  * Tests that the transfer method throws an IllegalArgumentException
  * if the account numbers are the same 
  *
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testTransferMoneySameAccount() {
    System.out.print("testTransferMoneySameAccount ");
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Alice", 300.0);

      system.transferMoney("12345678", "12345678", 50.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
  * Tests that the transfer method throws an InvalidAccountException
  * if the FROM amount number is not found
  *
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testTransferMoneyFromAccountNotFound() {
    System.out.print("testTransferMoneyFromAccountNotFound ");
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("87654321", "Bob", 100.0);

      system.transferMoney("99999999", "87654321", 50.0);
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
  * Tests that the transfer method throws an InvalidAccountException
  * if the TO amount number is not found
  *
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testTransferMoneyToAccountNotFound() {
    System.out.print("testTransferMoneyToAccountNotFound ");
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Alice", 300.0);

      system.transferMoney("12345678", "99999999", 50.0);
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
  * Tests that the transfer method throws an InsufficientFundsException
  * if there is not enough money for the transaction to take place
  *
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testTransferMoneyInsufficientFunds() {
    System.out.print("testTransferMoneyInsufficientFunds ");
    try { 
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Alice", 300.0);
      system.createAccount("02345678", "Alice", 300.0);

      system.transferMoney("12345678", "02345678", 1000.0);
      System.out.println("FAIL");
      return false;
    } catch (InsufficientFundsException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }

  }

  // =================== BankingSystem DisplayAccountInfo Tests
  // ===================

  /**
  * Checks that the displayAccount method functions 
  * like it is supposed to 
  *
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testDisplayAccountInfoValid() {
    System.out.print("testDisplayAccountInfoValid ");
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Test User", 500.0);
      system.displayAccountInfo("12345678"); // Should not throw exception
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
  * Checks that the displayAccount method throws 
  * InvalidAccountException if the account number is not
  * found
  *
  * @returns true or false depending on whether the test was
  * passed
  */
  public static boolean testDisplayAccountInfoNotFound() {
    System.out.print("testDisplayAccountInfoNotFound ");
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Test User", 500.0);
      system.displayAccountInfo("88888888");
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }
}