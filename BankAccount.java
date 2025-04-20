package BankManagment;

import java.io.Serializable;
import java.util.ArrayList;

public class BankAccount implements Serializable {
    private static final long serialVersionUID=1L;
    private String account_holder;
    private String account_number;
    private double balance;


    //constructor
    public BankAccount(String account_holder,String account_number,double balance){
        this.account_holder=account_holder;
        this.account_number=account_number;
        this.balance=balance;

    }

    //getter method
    public String getAccount_holder(){
        return account_holder;
    }
    public String getAccount_number(){
        return  account_number;
    }
    public double getBalance(){
        return balance;
    }




    //deposit method
    public void deposit(double amount){
        if(amount > 0){
            balance+=amount;
            System.out.println(amount+" deposited successfully.");
        }
        else{
            System.out.println("Invalid amount to deposit");
        }
    }

    //withdraw method
    public void withdraw(double amount){
        if(amount > balance){
            System.out.println("Insufficient balance");
        }
        else{
            balance-=amount;
            System.out.println(amount+" Withdraw successfully.");
        }
    }

    public void display_info(){
        System.out.println("Account Holder is: "+account_holder);
        System.out.println("Account number is: "+account_number);
        System.out.println("Balance is: "+balance);
    }


}


