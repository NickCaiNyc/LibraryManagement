package com.company;

import java.util.EmptyStackException;



/*Write a fully documented class named ReturnStack that allows us to take in returns (push) and
check in the last returned book (pop). ReturnStack should implement the toString() method, which
should print all of the ReturnStack!s data in tabular form
 */
public class ReturnStack implements Cloneable{

    private ReturnLog topLog;

    public ReturnLog getTopLog() {
        return topLog;
    }

    public void setTopLog(ReturnLog topLog) {
        this.topLog = topLog;
    }

    public ReturnStack() {
        topLog =  null;
    }

    //push checkout into stack if these work/ if the other function is true
    public void pushLog(long returnISBN, long returnUserID, Date returnDate, BookRepository bookRepoRef)throws InvalidISBNException, InvalidReturnDateException,BookNotCheckOutException,BookCheckedOutBySomeoneElseException,InvalidUserIdException{//push to stack
        String a = Long.toString(returnISBN);
        char b = a.charAt(0);
        int c = Character.getNumericValue(b);
        Book cursor = bookRepoRef.shelf[c].getHeadBook();
        for(int i = 0; i<bookRepoRef.shelf[c].getLength(); i++){
            if(cursor == null){
                break;
            }

            if((cursor.getISBN() == returnISBN) && (cursor.isCheckedout()) && (cursor.getCheckoutID() == returnUserID)){
                if(returnDate.compare(returnDate, cursor.getDueDate()) == -1){
                    System.out.println(cursor.getName() + " has been returned  LATE! Checking everything in...");
                    ReturnLog newNode = new ReturnLog(returnISBN, returnUserID, returnDate);
                    newNode.setNextLog(topLog);
                    topLog = newNode;
                    while(topLog != null) {
                        long z = topLog.getISBN();
                        String d = Long.toString(z);
                        char e = d.charAt(0);
                        int ff = Character.getNumericValue(e);

                        cursor = bookRepoRef.shelf[ff].getHeadBook();
                        for(int j = 0; j<bookRepoRef.shelf[ff].getLength(); j++) {
                            if (cursor.getISBN() == topLog.getISBN()) {
                                cursor.setCheckoutID(0);
                                cursor.setDueDate(null);
                                cursor.setCheckedout(false);
                                break;
                            }
                            cursor = cursor.getNextBook();
                        }
                        topLog = topLog.getNextLog();
                    }
                    return;
                }
                break;
            }
            else if(cursor.getISBN() != returnISBN && cursor == bookRepoRef.shelf[c].getTailBook()){
                throw new InvalidISBNException();
            }
            else if(cursor.getCheckoutID() != returnUserID&& cursor == bookRepoRef.shelf[c].getTailBook()){
                throw new InvalidUserIdException();
            }
            else if(!cursor.isCheckedout()&& cursor == bookRepoRef.shelf[c].getTailBook()){
                throw new BookNotCheckOutException();
            }
            else if(cursor.isCheckedout() && cursor.getCheckoutID() != returnUserID&& cursor == bookRepoRef.shelf[c].getTailBook()){
                throw new BookCheckedOutBySomeoneElseException();
            }

            cursor = cursor.getNextBook();
        }
        ReturnLog newNode = new ReturnLog(returnISBN, returnUserID, returnDate);
        newNode.setNextLog(topLog);
        topLog = newNode;
        if (cursor != null) {
            System.out.println(cursor.getName() + " has been returned to the return stack");
        }
    }
    public ReturnLog popLog()throws EmptyStackException { //pop top of stack
        ReturnLog answer = topLog;
        if(topLog == null){
            System.out.println("No book to check in!");
            return null;
        }if(topLog.getNextLog() == null){
            answer = topLog;
            topLog = null;
        }
        else if (topLog.getNextLog() != null) {
            topLog = topLog.getNextLog();
        }
        return answer;
    }

    public ReturnLog top()throws EmptyStackException{

        if(topLog == null){
            return null;

        };
        return topLog;
    }
    public ReturnLog pop(){
        ReturnLog a;
        a = topLog;
        if(topLog == null){
            return null;
        }
        topLog = topLog.getNextLog();
        return a;
    }
    public void push(long isbn, long id, Date duedate){
        ReturnLog newNode = new ReturnLog(isbn, id, duedate);
        newNode.setNextLog(topLog);
        topLog = newNode;
    }
    public String DisplayStack(ReturnStack givenstack) throws CloneNotSupportedException {

        System.out.format("%15s%15s%15s\n","ISBN", "Checkout UserID", "Due Date");
        String a = null;
        long isbn;
        long id;
        Date date;
        ReturnStack newgivenstack = (ReturnStack) givenstack.clone();
        ReturnStack newstack = new ReturnStack();
        while(newgivenstack != null){
            if(newgivenstack.getTopLog() == null){
                break;
            }
            isbn = newgivenstack.top().getISBN();
            id = newgivenstack.top().getUserID();
            date = newgivenstack.top().getReturnDate();
            newstack.push(isbn,id,date);
            a += System.out.format("%15s%15s%15s\n", newgivenstack.topLog.getISBN(), newgivenstack.topLog.getUserID(),newgivenstack.topLog.getReturnDate());
            newgivenstack.pop();
        }
        return a;
    }

    @Override
    public ReturnStack clone() {
        try {
            ReturnStack clone = (ReturnStack) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    public class InvalidISBNException extends Exception {
    }

    public class InvalidReturnDateException extends Exception {
    }

    public class BookNotCheckOutException extends Exception {
    }

    public class BookCheckedOutBySomeoneElseException extends Exception {
    }

    public class InvalidUserException extends Exception {
    }

    public class InvalidUserIdException extends Exception {
    }
}
