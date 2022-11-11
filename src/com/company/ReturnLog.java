package com.company;

/*Write a fully documented class named ReturnLog that allows us to track which books have been
returned to the library.

 */
public class ReturnLog {
    private long ISBN;
    private long userID;
    private Date returnDate;
    private ReturnLog nextLog;


    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public long getISBN() {
        return ISBN;
    }

    public ReturnLog getNextLog() {
        return nextLog;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public long getUserID() {
        return userID;
    }

    public void setNextLog(ReturnLog nextLog) {
        this.nextLog = nextLog;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
    //constructor without parameters
    public ReturnLog(){
        userID = 0;
        ISBN = 0;
        nextLog = null;
        returnDate = null;
    }
    //constructor with parameters
    public ReturnLog(long ISBN, long userID, Date returnDate){
        this.ISBN = ISBN;
        this.userID = userID;
        nextLog = null;
        this.returnDate = returnDate;
    }
}
