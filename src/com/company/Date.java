package com.company;

/*Write a fully documented class named Date that contains that contains the day, month, and year of
        a point in time. Also implement the compare() method, which should allow you to compare two
        dates with regards to chronological order using Java’s comparison operators.*/
public class Date {
    private int day;
    private int month;
    private int year;

    //constructor no parameters
    public Date(){
        day = 0;
        month = 0;
        year = 0;
    }
    //contructor with parameters
    public Date(int day, int month,int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }
    //setter getters above
    //This basically checks if the book is late or not
    public int compare(Date returndate,Date duedate){
        if( returndate.getYear() > duedate.getYear()){
            return -1;//late
        }
        else if(returndate.getYear() < duedate.getYear()) {
            return 1;//not late
        }else {
            if (returndate.getMonth() > duedate.getMonth()) {
                return -1;//ifyear the same
            }
            else if(returndate.getMonth() < duedate.getMonth()){
                return 1;
            }
            else{
                if (returndate.getDay() > duedate.getDay()) {
                    return -1;//if month the same
                }
                else{
                    return 1;
                }
            }
        }
    }
    public String toString(){
        return month + "/" + day +"/"+ year;
    }
}
