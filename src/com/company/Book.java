package com.company;
/*Write a fully documented class named Book that contains parameters of the information about
        itself. The Book class should contain variables for the book!s name, author, year of publication,
        genre, condition, and ISBN Number. In addition, it should also contain variables on whether it has
        been checked out, who checked it out, and its return date. Also implement the toString() method,
        which should print all of the book!s data in tabular form.
        */

public class Book {

    private bookCondition cond;
    private long ISBN;
    private long checkoutID;
    private int yearPublished;

    private boolean checkedout;

    private String genre;
    private String name;
    private String author;

    private Book nextBook;

    private Date dueDate;

    public Book(){
        name = "";
        author = "";
        genre = "";
        ISBN = 0;
        yearPublished = 0;
        checkedout = false;
        nextBook = null;
        cond = null;
    }
    public Book(String name, String author, String genre, long ISBN, int yearPublished, String condition, Boolean checkedout){//enum not added
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.ISBN=ISBN;
        this.yearPublished = yearPublished;
        nextBook = null;
        this.cond = convertenum(condition);
        this.checkedout = checkedout;
    }
    private bookCondition convertenum(String condition){
        switch(condition){
            case "NEW":
                return bookCondition.NEW;
            case "BAD":
                return bookCondition.BAD;
            case "REPLACE":
                return bookCondition.REPLACE;
            case "GOOD"://prompt user to state these options
                return bookCondition.GOOD;
        }
        return bookCondition.GOOD;
    }


    public bookCondition getCond() {
        return cond;
    }

    public void setCond(bookCondition cond) {
        this.cond = cond;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Book getNextBook() {
        return nextBook;
    }

    public boolean isCheckedout() {
        return checkedout;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public long getCheckoutID() {
        return checkoutID;
    }

    public long getISBN() {
        return ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCheckedout(boolean checkedout) {
        this.checkedout = checkedout;
    }

    public void setCheckoutID(long checkoutID) {
        this.checkoutID = checkoutID;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public void setNextBook(Book nextBook) {
        this.nextBook = nextBook;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

}
