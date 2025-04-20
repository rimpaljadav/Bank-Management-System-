package BankManagment;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManagement {
    /*private → This variable can only be accessed within the BankManagement class.

static → Shared among all instances of the class. No need to create an object to use it.

final → The value cannot be changed once assigned (it's a constant).

String FILE_NAME → Stores the file name "accounts.dat" as a string.*/
    private static final String FILE_NAME = "Bank_account.txt";
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static ArrayList<String> history=new ArrayList<>();

    public static void loadAccounts() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            accounts = (ArrayList<BankAccount>) input.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No existing account found.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveAccounts() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            output.writeObject(accounts);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteAccounts(Scanner sc) {
            System.out.println("Enter Account number to delete: ");
            String accNo = sc.next();

            for (BankAccount acc : accounts) {
                if (acc.getAccount_number().equals(accNo)) {
                    accounts.remove(acc);
                    saveAccounts();
                    System.out.println("Account deleted successfully.");
                    return;
                }
            }
            System.out.println("Account not found.");

    }

    public static void createAccount(Scanner sc) {
        sc.nextLine();
        System.out.println("Enter Account Number: ");
        String accNo = sc.nextLine();
        for (BankAccount acc : accounts) {
            if (acc.getAccount_number().equals(accNo)) {
                System.out.println("Already Existing...!");
                return;
            }
        }
        System.out.println("Enter Account Holder Name: ");
        String accHolder = sc.nextLine().strip();
        System.out.println("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        accounts.add(new BankAccount(accHolder, accNo, balance));
        System.out.println("Account created Successfully.");
        saveAccounts();

    }

    public static void depositMoney(Scanner sc) {
        System.out.println("Enter Account number:");
        String accNo = sc.next();

        for (BankAccount acc : accounts) {
            if (acc.getAccount_number().equals(accNo)) {
                System.out.println("Enter amount to deposit");
                double amount = sc.nextDouble();
                acc.deposit(amount);
                if(acc.getAccount_number().equals(accNo)) {
                    history.add(amount + " Deposited to " + acc.getAccount_holder());
                }
                saveAccounts();
                return;
            }
        }
        System.out.println("Account Not Found!");
    }

    public static void withdrawMoney(Scanner sc) {
        System.out.println("Enter Account number: ");
        String accNo = sc.next();
        for (BankAccount acc : accounts) {
            if (acc.getAccount_number().equals(accNo)) {
                System.out.println("Enter Withdraw amount: ");
                double amount = sc.nextDouble();
                acc.withdraw(amount);
                    history.add(amount+" Withdraw from "+acc.getAccount_holder());
                    saveAccounts();
                return;
            }
        }
        System.out.println("Account Not Found!");
    }

    public static void checkBalance(Scanner sc) {
        System.out.println("Enter Account number: ");
        String accNo = sc.next();
        for (BankAccount acc : accounts) {
            if (acc.getAccount_number().equals(accNo)) {
                System.out.println("Current Balance is: " + acc.getBalance());
                saveAccounts();
                return;
            }
        }
        System.out.println("Account Not Found.");
    }

    public static void displayAllAccounts(Scanner sc) {
        if (accounts.isEmpty()) {
            System.out.println("Account Not created yet!");
        }
        for (BankAccount acc : accounts) {
            acc.display_info();
            System.out.println("--------------------------");
        }
    }


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        loadAccounts();

        while(true){

            System.out.println("***Bank Management System***");
            System.out.println("1.Create Account");
            System.out.println("2.Deposit Money");
            System.out.println("3.Withdraw Money");
            System.out.println("4.Check Balance");
            System.out.println("5.Display All Account");
            System.out.println("6.Delete Account");
            System.out.println("7.Exit");
            System.out.println("Enter an option:");

            try{
                int option=sc.nextInt();
                switch (option){
                    case 1:
                        createAccount(sc);
                        break;
                    case 2:
                        depositMoney(sc);
                        break;
                    case 3:
                        withdrawMoney(sc);
                        break;
                    case 4:
                        checkBalance(sc);
                        break;
                    case 5:
                        displayAllAccounts(sc);
                        break;
                    case 6:
                        deleteAccounts(sc);
                        break;
                    case 7:
                        System.out.println("Existing system");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid option!");
                }
            }catch (Exception e){
                System.out.println("Invalid Input,Please enter a number:");
                sc.next();
            }
        }
    }


}

