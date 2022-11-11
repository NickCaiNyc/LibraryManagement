package com.company;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.*;
public class LibraryManager {
    public static void main(String[] args){
        Scanner inp = new Scanner(System.in);
        char input;
        char input2;
        char input3;
        char input4;
        long ISBN;
        long ID;
        int row;
        int year;
        int day;
        int month;
        int dueyear;
        boolean checkout;
        String condition;
        String name;
        String author;
        String genre;
        BookRepository a = new BookRepository();
        java.util.Date d = new java.util.Date();
        Book b = new Book();
        ReturnStack stacker = new ReturnStack();
        ReturnStack copystack = new ReturnStack();
        do {
            System.out.println("Menu\n" +
                    "For spaces use underscore _\n"  +
                    "o (B) – Manage Book Repository\n" +
                    "           o (C) – Checkout Book\n" +//menu
                    "           o (N) – Add New Book\n" +
                    "           o (R) – Remove Book\n" +//menu
                    "           o (P) – Print Repository\n" +
                    "           o (S) – Sort Shelf\n" +//menu
                    "                   o (I) – ISBN Number\n" +
                    "                   o (N) – Name\n" +//menu
                    "                   o (A) – Author\n" +
                    "                   o (G) – Genre\n" +//menu
                    "                   o (Y) – Year\n" +
                    "                   o (C) – Condition\n" +//menu
                    "o (R) – Manage Return Stack\n" +
                    "           o (R) – Return Book\n" +//menu
                    "           o (L) – See Last Return\n" +
                    "           o (C) – Check In Last Return\n" +//menu
                    "           o (P) – Print Return Stack\n" +
                    "o (Q) – Checkout Book\n");


            input = inp.next().charAt(0);
            System.out.println("Please select what to manage");
            switch (input) {// switch case
                case 'B' -> {
                    System.out.println("Please select an option");
                    input2 = inp.next().charAt(0);
                    switch (input2){
                        case 'C' -> {
                            //checkoutbook
                            System.out.println("Enter ISBN");
                            ISBN = inp.nextLong();
                            System.out.println("Enter library ID");
                            ID = inp.nextLong();
                            String longtoString;
                            int lengthofID;
                            longtoString = String.valueOf(ISBN);
                            lengthofID = longtoString.length();
                            if(lengthofID < 11) {
                                System.out.println("Provide month (mm)");
                                month = inp.nextInt();
                                System.out.println("Provide day (dd)");
                                day = inp.nextInt();
                                System.out.println("Provide year (yyyy)");
                                dueyear = inp.nextInt();
                                com.company.Date newdate = new com.company.Date(day, month, dueyear);
                                try {
                                    a.checkOutBook(ISBN, ID, newdate);
                                } catch (BookRepository.InvalidISBNException | BookRepository.InvalidUserIdException | BookRepository.BookAlreadyCheckOutException e) {
                                    e.printStackTrace();

                                }
                            }
                            else{
                                try {
                                    throw new BookRepository.InvalidUserIdException();
                                } catch (BookRepository.InvalidUserIdException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        case 'N' -> {
                            //Add book

                            System.out.println("Please provide an ISBN number: ");
                            String longtoString;
                            int lengthofISBN;
                            condition = "s";
                            ISBN = inp.nextLong();
                            checkout = false;
                            longtoString = String.valueOf(ISBN);
                            lengthofISBN = longtoString.length();
                            if(lengthofISBN > 13){
                                try {
                                    throw new BookRepository.InvalidISBNException();
                                } catch (BookRepository.InvalidISBNException e) {
                                    e.printStackTrace();
                                }
                            }
                            else {
                                System.out.println("Please provide a name: ");
                                name = inp.next();
                                System.out.println("Please provide an author: ");
                                author = inp.next();
                                System.out.println("Please provide a genre: ");
                                genre = inp.next();
                                System.out.println("Please provide a publishing year (YYYY) : ");
                                year = inp.nextInt();
                                System.out.println("Please provide a condition (if wrong output, the default is GOOD): ");
                                condition = inp.next();
                                try {
                                    a.addBook(name, author, genre, ISBN, year, condition, checkout);
                                } catch (BookRepository.InvalidISBNException | BookRepository.BookAlreadyExistsException e) {
                                    e.printStackTrace();
                                }
                            }


                        }
                        case 'R' -> { //do something about when there isn't a book in shelf
                            //remove book
                            System.out.println("Enter ISBN of book to be removed");
                            ISBN = inp.nextLong();
                            try {
                                a.removeBook(ISBN);
                            } catch (BookRepository.InvalidISBNException e) {
                                e.printStackTrace();
                            }

                        }
                        case 'P' -> {
                            //print repository//genre is wrong, condition is wrong
                            System.out.println("Please select a shelf");
                            row = inp.nextInt();
                            a.print(row);
                        }
                        case 'S' -> {
                            System.out.println("Please select a shelf between 0 - 9");
                            row = inp.nextInt();
                            System.out.println("Please select a sorting criteria");
                            input3 = inp.next().charAt(0);
                            switch (input3){
                                case 'I' -> {
                                    //ISBN
                                    try {
                                        a.sortShelf(row,"By_isbn");
                                    } catch (BookRepository.InvalidSortCriteraException e) {
                                        e.printStackTrace();
                                    }
                                }
                                case 'N' -> {
                                    //Name
                                    try {
                                        a.sortShelf(row,"By_name");
                                    } catch (BookRepository.InvalidSortCriteraException e) {
                                        e.printStackTrace();
                                    }
                                }
                                case 'A' -> {
                                    //Author
                                    try {
                                        a.sortShelf(row,"By_author");
                                    } catch (BookRepository.InvalidSortCriteraException e) {
                                        e.printStackTrace();
                                    }
                                }
                                case 'G' -> {
                                    //Genre
                                    try {
                                        a.sortShelf(row,"By_genre");
                                    } catch (BookRepository.InvalidSortCriteraException e) {
                                        e.printStackTrace();
                                    }
                                }
                                case 'Y' -> {
                                    //Year
                                    try {
                                        a.sortShelf(row,"By_year");
                                    } catch (BookRepository.InvalidSortCriteraException e) {
                                        e.printStackTrace();
                                    }
                                }
                                case 'C' -> {
                                    //Condition
                                    try {
                                        a.sortShelf(row,"By_condition");
                                    } catch (BookRepository.InvalidSortCriteraException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
                case 'R' -> {
                    Book returnbook;
                    ReturnLog z;
                    System.out.println("Please select an option");
                    input4 = inp.next().charAt(0);
                    switch (input4){
                        case 'R' -> {
                            //return book

                            System.out.println("Enter ISBN of the book you are returning");
                            ISBN = inp.nextLong();
                            System.out.println("Enter your library id that goes with the book");
                            ID = inp.nextLong();
                            System.out.println("Please your current date");
                            System.out.println("Month: ");
                            month = inp.nextInt();
                            System.out.println("Day: ");
                            day =  inp.nextInt();
                            System.out.println("Year: ");
                            year = inp.nextInt();
                            //returnbook = a.returnbook(ISBN, ID);
                            com.company.Date currentdate = new com.company.Date(day,month,year);

                            try {
                                stacker.pushLog(ISBN, ID, currentdate , a);
                            } catch (ReturnStack.InvalidISBNException | ReturnStack.InvalidReturnDateException | ReturnStack.BookNotCheckOutException | ReturnStack.BookCheckedOutBySomeoneElseException | ReturnStack.InvalidUserIdException e) {
                                e.printStackTrace();
                            }
                        }
                        case 'L' -> {
                            //peek the top of the stack
                            z = stacker.top();
                            if(z != null) {
                                ISBN = z.getISBN();
                                ID = z.getUserID();
                                returnbook = a.returnbook(ISBN, ID);
                                System.out.println(returnbook.getName() + " is the next book to be checked in");
                            }
                            else
                            {
                                System.out.println("There are no books in the return pile\n");
                            }
                        }
                        case 'C' -> {
                            //pop the top of the stack

                            z = stacker.popLog();
                            if(z != null) {
                            ISBN = z.getISBN();
                            ID = z.getUserID();
                            returnbook = a.returnbook(ISBN, ID);
                            returnbook.setCheckedout(false);
                            returnbook.setDueDate(null);
                            returnbook.setCheckoutID(0);
                            System.out.println(returnbook.getName() + "has been checked in");
                            }
                        }
                        case 'P' -> {
                            //print return stack
                            copystack = stacker;
                            try {
                                stacker.DisplayStack(copystack);
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                case 'Q' -> System.out.println("Thank you for using this interphase, Have a " +
                        "nice day");
            }
        }while(input != 'Q');//if value of input is Q which is quit then it will exit out of the menu
    }
}
