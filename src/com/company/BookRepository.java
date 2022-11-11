package com.company;

import java.util.Objects;

/*Write a fully documented class named BookRepository that contains all shelves and is able to add,
        remove, check in, and check out Books. The BookRepository class should have a fixed-length array
        of 10 shelves.
        10 shelves based on the first number of the ISBN
*/
public class BookRepository {

    Shelf[] shelf = new Shelf[10];

    //For R section check userid with isbn
    public void checkInBook(long checkedInISBN, long checkInUserID){
        //check to and see the stack and see if checkedinISBN and checkinuserid matches
        String a = Long.toString(checkedInISBN);
        boolean bookexists = false;
        char b = a.charAt(0);
        int c = Character.getNumericValue(b);
        if(shelf[c] == null){
            System.out.println("No shelf with this book");
            return;
        }

    }
    //Check out
    public void checkOutBook(long checkedOutISBN, long checkOutUserID, Date dueDate)throws InvalidISBNException, InvalidUserIdException, BookAlreadyCheckOutException
    {
        //finds the book based on the isbn, if can find then push it into log
        String a = Long.toString(checkedOutISBN);

        char b = a.charAt(0);
        int c = Character.getNumericValue(b);

        if(shelf[c] == null){
            System.out.println("No shelf with this book");
            return;
        }
// function to see if the ISBN exists or not
        try {
            shelf[c].searchbook(checkedOutISBN, dueDate, checkOutUserID);
        } catch (Shelf.BookCheckedOutBySomeoneElseException e) {
            e.printStackTrace();
        }
    }

    //Add book looking at the ISBN, making a book and storing it into the book
    public void addBook(String addName, String addAuthor,String genre, long isbn,int yearpublished, String condition, Boolean checkedout) throws InvalidISBNException, BookAlreadyExistsException {
        //new book class
        String a = Long.toString(isbn);
        char b = a.charAt(0);
        int c = Character.getNumericValue(b);

        Book newbook = new Book(addName, addAuthor, genre, isbn, yearpublished,condition,checkedout);

        if(shelf[c] == null) {
            shelf[c] = new Shelf(100);
            shelf[c] = shelf[c].sortingof(ShelfSortCriteria.By_isbn);
        }

        try {
            shelf[c].AddBook(newbook);
        } catch (Shelf.BookAlreadyExistsException e) {
            e.printStackTrace();
        }
        shelf[c] = shelf[c].sortingof(shelf[c].getSorti());
    }
    public Book returnbook(long isbn, long id){
        String a = Long.toString(isbn);
        char b = a.charAt(0);
        int c = Character.getNumericValue(b);
        return shelf[c].searchtocheckin(isbn, id);
    }

    //remove based on ISBN
    //loop through the array of Shelf and delete if it can find ISBN
    public void removeBook(long removeISBN) throws InvalidISBNException{
        String s = String.valueOf(removeISBN);
        int lengthofISBN;
        lengthofISBN = s.length();
        if(lengthofISBN > 13) {
            for (int i = 0; i < 10; i++) {
                if (shelf[i] == null) {
                    System.out.println("Empty Shelf");
                } else {
                    try {
                        shelf[i].RemoveBook(s);
                    } catch (Shelf.BookDoesNotExistException | Shelf.InvalidISBNException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else{
            throw new InvalidISBNException();
        }
    }
    //sort the shelf of choice bsed on the sortCriteria
    public void sortShelf(int shelfInt, String sortCriteria)throws InvalidSortCriteraException{

        if(Objects.equals(sortCriteria, "By_isbn")){
            shelf[shelfInt].setSorti(ShelfSortCriteria.By_isbn);
            shelf[shelfInt] = shelf[shelfInt].sortingof(ShelfSortCriteria.By_isbn);
        }
        if(Objects.equals(sortCriteria, "By_name")){
            shelf[shelfInt].setSorti(ShelfSortCriteria.By_name);
            shelf[shelfInt] = shelf[shelfInt].sortingof(ShelfSortCriteria.By_name);
        }
        if(Objects.equals(sortCriteria, "By_author")){
            shelf[shelfInt].setSorti(ShelfSortCriteria.By_author);
            shelf[shelfInt] = shelf[shelfInt].sortingof(ShelfSortCriteria.By_author);
        }
        if(Objects.equals(sortCriteria, "By_year")){
            shelf[shelfInt].setSorti(ShelfSortCriteria.By_year);
            shelf[shelfInt] = shelf[shelfInt].sortingof(ShelfSortCriteria.By_year);
        }
        if(Objects.equals(sortCriteria, "By_condition")){
            shelf[shelfInt].setSorti(ShelfSortCriteria.By_condition);
            shelf[shelfInt] = shelf[shelfInt].sortingof(ShelfSortCriteria.By_condition);
        }
        if(Objects.equals(sortCriteria, "By_genre")){
            shelf[shelfInt].setSorti(ShelfSortCriteria.By_genre);
            shelf[shelfInt] = shelf[shelfInt].sortingof(ShelfSortCriteria.By_genre);
        }
    }

    public void print(int row){
        if(shelf[row] == null){
            System.out.println("There is no row to print");
        }
        else{
            System.out.println(shelf[row].toString());
        }
    }

    public static class InvalidISBNException extends Exception {
    }

    public static class InvalidUserIdException extends Exception {
    }

    public class BookAlreadyCheckOutException extends Exception {
    }

    public class BookAlreadyExistsException extends Exception {
    }

    public class InvalidSortCriteraException extends Exception {
    }
}
