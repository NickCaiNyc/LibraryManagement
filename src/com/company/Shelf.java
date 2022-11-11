package com.company;/*Write a fully documented class named Shelf that contains books in a particular shelf of
        BookRepository. The Shelf class should contain a Book reference to the start of your shelf and its
        length. Also implement the toString() method, which should print all of the shelve!s data in tabular
        form.
*/
//BOOK IN SHELF, shelf has a set length so need to check if there is space left
import java.lang.*;
public class Shelf {
    private ShelfSortCriteria sorti;

    private Book HeadBook;
    private Book TailBook;
    private int length;

    public Shelf(int length){//constructor
        this.sorti = ShelfSortCriteria.By_isbn;
        this.length = length;
        HeadBook = null;
        TailBook = null;
    }

    public Shelf sortingof(ShelfSortCriteria sortCriteria){
        switch(sortCriteria){
            case By_isbn ->{
                Shelf newShelf = new Shelf(100);
                long min = 0;
                Book cursor;
                Book cursor2;
                Book newbook = null;
                String s = "";
                while(HeadBook != null) {//basically while there is a linkedlist of shelf[i] still there
                    cursor = HeadBook;
                    min = 10000000000000L;

                    while (cursor != null) {

                        if (min > cursor.getISBN()) {
                            min = cursor.getISBN();//min value of the current linked list
                            String condition = String.valueOf(cursor.getCond());
                            newbook = new Book(cursor.getName(), cursor.getAuthor(), cursor.getGenre(), cursor.getISBN() , cursor.getYearPublished(), condition, cursor.isCheckedout());
                        }
                        if(cursor == TailBook){
                            break;
                        }
                        cursor = cursor.getNextBook();
                    }

                    cursor = HeadBook;
                    for(int i = 0; i < length; i++) {//removes the smallest value of linkedlist
                        if((HeadBook == TailBook) && (min == cursor.getISBN())){
                            HeadBook = TailBook = null;
                            break;
                        }

                        //For if there are only two items in the thing
                        //if head is equal
                        if(HeadBook.getNextBook() == TailBook && min == cursor.getISBN()){
                            cursor = HeadBook = TailBook;
                            break;
                        }
                        //if tail is equal
                        //if next to cursor is tail and tail has same ISBN then set tail to null and set tail to cursor
                        if(HeadBook.getNextBook() == TailBook && min == cursor.getNextBook().getISBN()){
                            TailBook = HeadBook;
                            break;
                        }

                        //At head given more then two in the list

                        if(HeadBook.getNextBook() != null && HeadBook.getISBN() == min){
                            cursor = cursor.getNextBook();
                            HeadBook = cursor;
                            break;
                        }
                        // one before tail but given more then 2 in list and tail has the isbn number
                        if (cursor.getNextBook() == TailBook && min == cursor.getNextBook().getISBN()){
                            TailBook = cursor;
                            break;
                        }


                        if (cursor.getNextBook().getNextBook() != null && cursor.getNextBook().getISBN() == min) {
                            //route cursor to the next next node
                            cursor.setNextBook(cursor.getNextBook().getNextBook());
                            //basically if by skipping a node it is at tail then set that cursor to tail
                            if (cursor.getNextBook() == null) {
                                TailBook = cursor;
                            }
                            break;
                        }

                        cursor = cursor.getNextBook();
                        if(cursor == null){
                            break;
                        }

                    }

                    cursor2 = newShelf.getHeadBook();
                    for(int i = 0; i < length; i++) {
                        if(cursor2 == null){
                            break;
                        }
                        if(cursor2.getNextBook() == null){
                            break;
                        }
                        cursor2 = cursor2.getNextBook();
                    }

                    if (cursor2 == null) {//cursor is null
                        newShelf.setHeadBook(newbook);
                        newShelf.setTailBook(newbook);

                    }
                    //if there are books there meaning head = something
                    else {
                        newbook.setNextBook(cursor2.getNextBook());
                        cursor2.setNextBook(newbook);
                        cursor2 = newbook;
                        if(cursor2.getNextBook() == null){
                            newShelf.setTailBook(cursor2);
                        }
                    }
                }
                return newShelf;
            }
            case By_name -> {
                Shelf newShelf = new Shelf(100);
                newShelf.setSorti(ShelfSortCriteria.By_name);
                char min = ' ';
                Book cursor;
                Book cursor2;
                Book newbook = null;
                while(HeadBook != null) {//basically while there is a linkedlist of shelf[i] still there
                    cursor = HeadBook;
                    min = 'x';

                    while (cursor != null) {
                        char a = cursor.getName().charAt(0);
                        if (min > a) {
                            min = cursor.getName().charAt(0);//min value of the current linked list
                            String condition = String.valueOf(cursor.getCond());
                            newbook = new Book(cursor.getName(), cursor.getAuthor(), cursor.getGenre(), cursor.getISBN() , cursor.getYearPublished(), condition, cursor.isCheckedout());
                        }
                        if(cursor == TailBook){
                            break;
                        }
                        cursor = cursor.getNextBook();
                    }

                    cursor = HeadBook;
                    for(int i = 0; i < length; i++) {//removes the smallest value of linkedlist
                        if((HeadBook == TailBook) && (min == cursor.getName().charAt(0))){
                            HeadBook = TailBook = null;
                            break;
                        }

                        //For if there are only two items in the thing
                        //if head is equal
                        if(HeadBook.getNextBook() == TailBook && min == cursor.getName().charAt(0)){

                            cursor = HeadBook = TailBook;
                            break;
                        }
                        //if tail is equal
                        //if next to cursor is tail and tail has same ISBN then set tail to null and set tail to cursor
                        if(HeadBook.getNextBook() == TailBook && min == cursor.getNextBook().getName().charAt(0)){

                            TailBook = HeadBook;
                            break;
                        }

                        //At head given more then two in the list

                        if(HeadBook.getNextBook() != null && HeadBook.getName().charAt(0) == min){

                            cursor = cursor.getNextBook();
                            HeadBook = cursor;
                            break;
                        }
                        // one before tail but given more then 2 in list and tail has the isbn number
                        if (cursor.getNextBook() == TailBook && min == cursor.getNextBook().getName().charAt(0)){

                            TailBook = cursor;
                            break;
                        }


                        if (cursor.getNextBook().getNextBook() != null && cursor.getNextBook().getName().charAt(0) == min) {

                            cursor.setNextBook(cursor.getNextBook().getNextBook());
                            //basically if by skipping a node it is at tail then set that cursor to tail
                            if (cursor.getNextBook() == null) {
                                TailBook = cursor;
                            }
                            break;
                        }

                        cursor = cursor.getNextBook();
                        if(cursor == null){
                            break;
                        }

                    }

                    cursor2 = newShelf.getHeadBook();
                    for(int i = 0; i < length; i++) {
                        if(cursor2 == null){
                            break;
                        }
                        if(cursor2.getNextBook() == null){
                            break;
                        }
                        cursor2 = cursor2.getNextBook();
                    }

                    if (cursor2 == null) {//cursor is null
                        newShelf.setHeadBook(newbook);
                        newShelf.setTailBook(newbook);

                    }
                    //if there are books there meaning head = something
                    else {
                        newbook.setNextBook(cursor2.getNextBook());
                        cursor2.setNextBook(newbook);
                        cursor2 = newbook;
                        if(cursor2.getNextBook() == null){
                            newShelf.setTailBook(cursor2);
                        }
                    }
                }
                newShelf.setSorti(ShelfSortCriteria.By_name);
                return newShelf;
            }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            case By_year -> {
                Shelf newShelf = new Shelf(100);
                int min = 0;
                Book cursor;
                Book cursor2;
                Book newbook = null;
                String s = "";
                while(HeadBook != null) {//basically while there is a linkedlist of shelf[i] still there
                    cursor = HeadBook;
                    min = 100000;//assuming the year us going to be 4 digits

                    while (cursor != null) {

                        if (min > cursor.getYearPublished()) {
                            min = cursor.getYearPublished();//min value of the current linked list
                            String condition = String.valueOf(cursor.getCond());
                            newbook = new Book(cursor.getName(), cursor.getAuthor(), cursor.getGenre(), cursor.getISBN() , cursor.getYearPublished(), condition, cursor.isCheckedout());
                        }
                        if(cursor == TailBook){
                            break;
                        }
                        cursor = cursor.getNextBook();
                    }

                    cursor = HeadBook;
                    for(int i = 0; i < length; i++) {//removes the smallest value of linkedlist
                        if((HeadBook == TailBook) && (min == cursor.getYearPublished())){
                            HeadBook = TailBook = null;
                            break;
                        }

                        //For if there are only two items in the thing
                        //if head is equal
                        if(HeadBook.getNextBook() == TailBook && min == cursor.getYearPublished()){
                            cursor = HeadBook = TailBook;
                            break;
                        }
                        //if tail is equal
                        //if next to cursor is tail and tail has same ISBN then set tail to null and set tail to cursor
                        if(HeadBook.getNextBook() == TailBook && min == cursor.getNextBook().getYearPublished()){
                            TailBook = HeadBook;
                            break;
                        }

                        //At head given more then two in the list

                        if(HeadBook.getNextBook() != null && HeadBook.getYearPublished() == min){
                            cursor = cursor.getNextBook();
                            HeadBook = cursor;
                            break;
                        }
                        // one before tail but given more then 2 in list and tail has the isbn number
                        if (cursor.getNextBook() == TailBook && min == cursor.getNextBook().getYearPublished()){
                            TailBook = cursor;
                            break;
                        }


                        if (cursor.getNextBook().getNextBook() != null && cursor.getNextBook().getYearPublished() == min) {
                            //route cursor to the next next node
                            cursor.setNextBook(cursor.getNextBook().getNextBook());
                            //basically if by skipping a node it is at tail then set that cursor to tail
                            if (cursor.getNextBook() == null) {
                                TailBook = cursor;
                            }
                            break;
                        }

                        cursor = cursor.getNextBook();
                        if(cursor == null){
                            break;
                        }

                    }

                    cursor2 = newShelf.getHeadBook();
                    for(int i = 0; i < length; i++) {
                        if(cursor2 == null){
                            break;
                        }
                        if(cursor2.getNextBook() == null){
                            break;
                        }
                        cursor2 = cursor2.getNextBook();
                    }

                    if (cursor2 == null) {//cursor is null
                        newShelf.setHeadBook(newbook);
                        newShelf.setTailBook(newbook);

                    }
                    //if there are books there meaning head = something
                    else {
                        newbook.setNextBook(cursor2.getNextBook());
                        cursor2.setNextBook(newbook);
                        cursor2 = newbook;
                        if(cursor2.getNextBook() == null){
                            newShelf.setTailBook(cursor2);
                        }
                    }
                }
                newShelf.setSorti(ShelfSortCriteria.By_year);
                return newShelf;
            }
            //////////////////////////////////////////////////////////////////////////////////////////////////////////
            case By_author -> {

                Shelf newShelf = new Shelf(100);
                char min = ' ';
                Book cursor;
                Book cursor2;
                Book newbook = null;
                while(HeadBook != null) {//basically while there is a linkedlist of shelf[i] still there
                    cursor = HeadBook;
                    min = 'x';

                    while (cursor != null) {
                        char a = cursor.getAuthor().charAt(0);
                        if (min > a) {
                            min = cursor.getAuthor().charAt(0);//min value of the current linked list
                            String condition = String.valueOf(cursor.getCond());
                            newbook = new Book(cursor.getName(), cursor.getAuthor(), cursor.getGenre(), cursor.getISBN() , cursor.getYearPublished(), condition, cursor.isCheckedout());
                        }
                        if(cursor == TailBook){
                            break;
                        }
                        cursor = cursor.getNextBook();
                    }

                    cursor = HeadBook;
                    for(int i = 0; i < length; i++) {//removes the smallest value of linkedlist
                        if((HeadBook == TailBook) && (min == cursor.getAuthor().charAt(0))){
                            HeadBook = TailBook = null;
                            break;
                        }

                        //For if there are only two items in the thing
                        //if head is equal
                        if(HeadBook.getNextBook() == TailBook && min == cursor.getAuthor().charAt(0)){

                            cursor = HeadBook = TailBook;
                            break;
                        }
                        //if tail is equal
                        //if next to cursor is tail and tail has same ISBN then set tail to null and set tail to cursor
                        if(HeadBook.getNextBook() == TailBook && min == cursor.getNextBook().getAuthor().charAt(0)){

                            TailBook = HeadBook;
                            break;
                        }

                        //At head given more then two in the list

                        if(HeadBook.getNextBook() != null && HeadBook.getAuthor().charAt(0) == min){

                            cursor = cursor.getNextBook();
                            HeadBook = cursor;
                            break;
                        }
                        // one before tail but given more then 2 in list and tail has the isbn number
                        if (cursor.getNextBook() == TailBook && min == cursor.getNextBook().getAuthor().charAt(0)){

                            TailBook = cursor;
                            break;
                        }


                        if (cursor.getNextBook().getNextBook() != null && cursor.getNextBook().getAuthor().charAt(0) == min) {

                            cursor.setNextBook(cursor.getNextBook().getNextBook());
                            //basically if by skipping a node it is at tail then set that cursor to tail
                            if (cursor.getNextBook() == null) {
                                TailBook = cursor;
                            }
                            break;
                        }

                        cursor = cursor.getNextBook();
                        if(cursor == null){
                            break;
                        }

                    }

                    cursor2 = newShelf.getHeadBook();
                    for(int i = 0; i < length; i++) {
                        if(cursor2 == null){
                            break;
                        }
                        if(cursor2.getNextBook() == null){
                            break;
                        }
                        cursor2 = cursor2.getNextBook();
                    }

                    if (cursor2 == null) {//cursor is null
                        newShelf.setHeadBook(newbook);
                        newShelf.setTailBook(newbook);

                    }
                    //if there are books there meaning head = something
                    else {
                        newbook.setNextBook(cursor2.getNextBook());
                        cursor2.setNextBook(newbook);
                        cursor2 = newbook;
                        if(cursor2.getNextBook() == null){
                            newShelf.setTailBook(cursor2);
                        }
                    }
                }
                newShelf.setSorti(ShelfSortCriteria.By_author);
                return newShelf;
            }
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            case By_condition -> {
                Shelf newShelf = new Shelf(100);
                char min = ' ';
                char a = ' ';
                Book cursor;
                Book cursor2;
                Book newbook = null;
                String s = "";
                while(HeadBook != null) {//basically while there is a linkedlist of shelf[i] still there
                    cursor = HeadBook;
                    min = 'x';

                    while (cursor != null) {
                        a = String.valueOf(cursor.getCond()).charAt(0);
                        if (min > a) {
                            min = String.valueOf(cursor.getCond()).charAt(0);//min value of the current linked list
                            String condition = String.valueOf(cursor.getCond());
                            newbook = new Book(cursor.getName(), cursor.getAuthor(), cursor.getGenre(), cursor.getISBN() , cursor.getYearPublished(), condition, cursor.isCheckedout());
                        }
                        if(cursor == TailBook){
                            break;
                        }
                        cursor = cursor.getNextBook();
                    }

                    cursor = HeadBook;
                    for(int i = 0; i < length; i++) {//removes the smallest value of linkedlist
                        if((HeadBook == TailBook) && (min == String.valueOf(cursor.getCond()).charAt(0))){
                            HeadBook = TailBook = null;
                            break;
                        }

                        //For if there are only two items in the thing
                        //if head is equal
                        if(HeadBook.getNextBook() == TailBook && min == String.valueOf(cursor.getCond()).charAt(0)){
                            System.out.println(cursor.getName() + " has been deleted from the book repository");
                            cursor = HeadBook = TailBook;
                            break;
                        }
                        //if tail is equal
                        //if next to cursor is tail and tail has same ISBN then set tail to null and set tail to cursor
                        if(HeadBook.getNextBook() == TailBook && min == String.valueOf(cursor.getNextBook().getCond()).charAt(0)){
                            System.out.println(TailBook.getName() + " has been deleted from the book repository");
                            TailBook = HeadBook;
                            break;
                        }

                        //At head given more then two in the list

                        if(HeadBook.getNextBook() != null && String.valueOf(HeadBook.getCond()).charAt(0) == min){
                            cursor = cursor.getNextBook();
                            HeadBook = cursor;
                            break;
                        }
                        // one before tail but given more then 2 in list and tail has the isbn number
                        if (cursor.getNextBook() == TailBook && min == String.valueOf(cursor.getNextBook().getCond()).charAt(0)){
                            TailBook = cursor;
                            break;
                        }


                        if (cursor.getNextBook().getNextBook() != null && String.valueOf(cursor.getNextBook().getCond()).charAt(0) == min) {
                            //route cursor to the next next node
                            cursor.setNextBook(cursor.getNextBook().getNextBook());
                            //basically if by skipping a node it is at tail then set that cursor to tail
                            if (cursor.getNextBook() == null) {
                                TailBook = cursor;
                            }
                            break;
                        }

                        cursor = cursor.getNextBook();
                        if(cursor == null){
                            break;
                        }

                    }

                    cursor2 = newShelf.getHeadBook();
                    for(int i = 0; i < length; i++) {
                        if(cursor2 == null){
                            break;
                        }
                        if(cursor2.getNextBook() == null){
                            break;
                        }
                        cursor2 = cursor2.getNextBook();
                    }

                    if (cursor2 == null) {//cursor is null
                        newShelf.setHeadBook(newbook);
                        newShelf.setTailBook(newbook);

                    }
                    //if there are books there meaning head = something
                    else {
                        newbook.setNextBook(cursor2.getNextBook());
                        cursor2.setNextBook(newbook);
                        cursor2 = newbook;
                        if(cursor2.getNextBook() == null){
                            newShelf.setTailBook(cursor2);
                        }
                    }
                }
                newShelf.setSorti(ShelfSortCriteria.By_condition);
                return newShelf;

            }
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            case By_genre -> {
                Shelf newShelf = new Shelf(100);
                char min = ' ';
                Book cursor;
                Book cursor2;
                Book newbook = null;
                while(HeadBook != null) {//basically while there is a linkedlist of shelf[i] still there
                    cursor = HeadBook;
                    min = 'x';

                    while (cursor != null) {
                        char a = cursor.getGenre().charAt(0);
                        if (min > a) {
                            min = cursor.getGenre().charAt(0);//min value of the current linked list
                            String condition = String.valueOf(cursor.getCond());
                            newbook = new Book(cursor.getName(), cursor.getAuthor(), cursor.getGenre(), cursor.getISBN() , cursor.getYearPublished(), condition, cursor.isCheckedout());
                        }
                        if(cursor == TailBook){
                            break;
                        }
                        cursor = cursor.getNextBook();
                    }

                    cursor = HeadBook;
                    for(int i = 0; i < length; i++) {//removes the smallest value of linkedlist
                        if((HeadBook == TailBook) && (min == cursor.getGenre().charAt(0))){
                            HeadBook = TailBook = null;
                            break;
                        }

                        //For if there are only two items in the thing
                        //if head is equal
                        if(HeadBook.getNextBook() == TailBook && min == cursor.getGenre().charAt(0)){

                            cursor = HeadBook = TailBook;
                            break;
                        }
                        //if tail is equal
                        //if next to cursor is tail and tail has same ISBN then set tail to null and set tail to cursor
                        if(HeadBook.getNextBook() == TailBook && min == cursor.getNextBook().getGenre().charAt(0)){

                            TailBook = HeadBook;
                            break;
                        }

                        //At head given more then two in the list

                        if(HeadBook.getNextBook() != null && HeadBook.getGenre().charAt(0) == min){

                            cursor = cursor.getNextBook();
                            HeadBook = cursor;
                            break;
                        }
                        // one before tail but given more then 2 in list and tail has the isbn number
                        if (cursor.getNextBook() == TailBook && min == cursor.getNextBook().getGenre().charAt(0)){

                            TailBook = cursor;
                            break;
                        }


                        if (cursor.getNextBook().getNextBook() != null && cursor.getNextBook().getGenre().charAt(0) == min) {

                            cursor.setNextBook(cursor.getNextBook().getNextBook());
                            //basically if by skipping a node it is at tail then set that cursor to tail
                            if (cursor.getNextBook() == null) {
                                TailBook = cursor;
                            }
                            break;
                        }

                        cursor = cursor.getNextBook();
                        if(cursor == null){
                            break;
                        }

                    }

                    cursor2 = newShelf.getHeadBook();
                    for(int i = 0; i < length; i++) {
                        if(cursor2 == null){
                            break;
                        }
                        if(cursor2.getNextBook() == null){
                            break;
                        }
                        cursor2 = cursor2.getNextBook();
                    }

                    if (cursor2 == null) {//cursor is null
                        newShelf.setHeadBook(newbook);
                        newShelf.setTailBook(newbook);

                    }
                    //if there are books there meaning head = something
                    else {
                        newbook.setNextBook(cursor2.getNextBook());
                        cursor2.setNextBook(newbook);
                        cursor2 = newbook;
                        if(cursor2.getNextBook() == null){
                            newShelf.setTailBook(cursor2);
                        }
                    }
                }
                newShelf.setSorti(ShelfSortCriteria.By_genre);
                return newShelf;

            }

        }
        return null;
    }

    public void setSorti(ShelfSortCriteria sorti) {
        this.sorti = sorti;
    }

    public ShelfSortCriteria getSorti() {
        return sorti;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public Book getHeadBook() {
        return HeadBook;
    }

    public Book getTailBook() {
        return TailBook;
    }

    public void setHeadBook(Book headBook) {
        HeadBook = headBook;
    }

    public void setTailBook(Book tailBook) {
        TailBook = tailBook;
    }


    //add book and then make shelf when it is full?
    public void AddBook(Book addedBook) throws BookAlreadyExistsException{
        //if head is null
        //not considered spacing
        //if the same ISBN exists or not
        Book cursor = new Book();
        cursor = HeadBook;
        for(int i = 0; i < length; i++) {
            if(cursor == null){
                break;
            }
            if(cursor.getNextBook() == null){
                break;
            }
            if(cursor.getISBN() == addedBook.getISBN()){
                throw new BookAlreadyExistsException();
            }//if ISBN exist

            cursor = cursor.getNextBook();
        }

        if (HeadBook == null) {//cursor is null
            HeadBook = TailBook = cursor = addedBook;
            System.out.println(addedBook.getName() + " has been added to the book repository");
        }
        //if there are books there meaning head = something
        else { //1 2 3 4 5 6C T      8
            addedBook.setNextBook(cursor.getNextBook());
            cursor.setNextBook(addedBook);
            cursor = addedBook;
            if(cursor.getNextBook() == null){
                TailBook = cursor;
            }

            System.out.println(addedBook.getName() + " has been added to the book repository");

        }

    }

    public void RemoveBook(String removedISBN) throws BookDoesNotExistException, InvalidISBNException {
        long num = Long.parseLong(removedISBN);
        //make string into number
        //have a cursor
        Book cursor = HeadBook;

        for(int i = 0; i < length;i++) {
            //length of shelf
            if (cursor == null) {
                throw new BookDoesNotExistException();
            } else {
                //only 1 item
                if((HeadBook == TailBook) && (num == cursor.getISBN())){
                    HeadBook = TailBook = cursor = null;
                    break;
                }

                //For if there are only two items in the thing
                //if head is equal
                if(HeadBook.getNextBook() == TailBook && num == cursor.getISBN()){
                    System.out.println(cursor.getName() + " has been deleted from the book repository");
                    cursor = HeadBook = TailBook;
                    break;
                }
                //if tail is equal
                //if next to cursor is tail and tail has same ISBN then set tail to null and set tail to cursor
                if(HeadBook.getNextBook() == TailBook && num == cursor.getNextBook().getISBN()){
                    System.out.println(TailBook.getName() + " has been deleted from the book repository");
                    TailBook = HeadBook;
                    break;
                }

                //At head given more then two in the list

                if(HeadBook.getNextBook() != null && HeadBook.getISBN() == num){
                    System.out.println(cursor.getName() + " has been deleted from the book repository");
                    cursor = cursor.getNextBook();
                    HeadBook = cursor;
                    break;
                }
                // one before tail but given more then 2 in list and tail has the isbn number
                if (cursor.getNextBook() == TailBook && num == cursor.getNextBook().getISBN()){
                    System.out.println(cursor.getName() + " has been deleted from the book repository");
                    TailBook = cursor;
                    break;
                }


                if (cursor.getNextBook().getNextBook() != null && cursor.getNextBook().getISBN() == num) {
                    System.out.println(cursor.getName() + " has been deleted from the book repository");
                    //route cursor to the next next node
                    cursor.setNextBook(cursor.getNextBook().getNextBook());
                    //basically if by skipping a node it is at tail then set that cursor to tail
                    if (cursor.getNextBook() == null) {
                        TailBook = cursor;
                    }
                    break;
                }
                if(TailBook.getISBN() != num){
                    throw new BookDoesNotExistException();
                }
            }

            cursor = cursor.getNextBook();
            if(cursor == null){
                throw new BookDoesNotExistException();
            }
        }
    }
    public Book searchtocheckin(long isbn, long id){
        Book cursor = HeadBook;
        for(int i = 0; i<length; i++){
            if(cursor.getISBN() == isbn && cursor.isCheckedout() && cursor.getCheckoutID() == id){
                return cursor;
            }
            if(cursor == TailBook && cursor.getISBN() != isbn){
                break;//we have been through the entire list and no isbn here
            }
            cursor = cursor.getNextBook();
        }
        System.out.println("There is no such book with this isbn");
        return null;
    }

    //made function which can search for book and finds it with condition of it is not checked out and that it is the correct isbn, from there it checks the book out basically
    public void searchbook(long isbn, Date duedate, Long id)throws BookCheckedOutBySomeoneElseException {
        Book cursor = HeadBook;
        for(int i = 0; i<length; i++){
            if(cursor.getISBN() == isbn && cursor.isCheckedout()){
                throw new BookCheckedOutBySomeoneElseException();
            }
            if(cursor.getISBN() == isbn && !cursor.isCheckedout()){
                cursor.setDueDate(duedate);
                cursor.setCheckedout(true);
                cursor.setCheckoutID(id);
                System.out.println(cursor.getName() + " has been checked out by id number " + cursor.getCheckoutID() + " due " + duedate);
                return;
            }
            if(cursor == TailBook && cursor.getISBN() != isbn){//if at end and isbn not equal means your input is wrong basically
                break;
            }
            cursor = cursor.getNextBook();
        }
        System.out.println("There is no such book with this isbn");
    }

    public String toString(){
        StringBuilder a = new StringBuilder();
        Book cursor = HeadBook;
        switch(getSorti()){
            case By_isbn -> {//fix this part and look through the sorts
                System.out.format("%15s%15s%20s%20s\n","ISBN", "Checked Out", "Due Date", "Checkout UserID");}
            case By_genre -> {
                System.out.format("%15s%15s%20s%20s\n","Genre", "Checked Out", "Due Date", "Checkout UserID");}
            case By_year -> {
                System.out.format("%15s%15s%20s%20s\n", "Year","Checked Out", "Due Date", "Checkout UserID");}
            case By_name -> {
                System.out.format("%15s%15s%20s%20s\n","Name", "Checked Out", "Due Date", "Checkout UserID");}
            case By_author -> {
                System.out.format("%15s%15s%20s%20s\n","Author", "Checked Out", "Due Date", "Checkout UserID");}
            case By_condition -> {
                System.out.format("%15s%15s%20s%20s\n","Condition","Checked Out", "Due Date", "Checkout UserID");}
        }
        for(int i = 0; i < length; i++){

            switch(getSorti()){
                case By_isbn -> {//fix this part and look through the sorts
                    a.append(System.out.format("%15s%15s%20s%20s\n", cursor.getISBN(), cursor.isCheckedout(), cursor.getDueDate(), cursor.getCheckoutID()));}
                case By_genre -> {
                    a.append(System.out.format("%15s%15s%20s%20s\n", cursor.getGenre(), cursor.isCheckedout(), cursor.getDueDate(), cursor.getCheckoutID()));}
                case By_year -> {
                    a.append(System.out.format("%15s%15s%20s%20s\n", cursor.getYearPublished(), cursor.isCheckedout(), cursor.getDueDate(), cursor.getCheckoutID()));}
                case By_name -> {
                    a.append(System.out.format("%15s%15s%20s%20s\n", cursor.getName(), cursor.isCheckedout(), cursor.getDueDate(), cursor.getCheckoutID()));}
                case By_author -> {
                    a.append(System.out.format("%15s%15s%20s%20s\n", cursor.getAuthor(), cursor.isCheckedout(), cursor.getDueDate(), cursor.getCheckoutID()));}
                case By_condition -> {
                    a.append(System.out.format("%15s%15s%20s%20s\n", cursor.getCond(), cursor.isCheckedout(), cursor.getDueDate(), cursor.getCheckoutID()));}
            }
            if(cursor == TailBook){
                break;
            }
            if(HeadBook == TailBook){
                break;
            }
            cursor = cursor.getNextBook();
        }
        String Stringbuilderconverter = a.toString();
        return Stringbuilderconverter;
    }

    public class BookAlreadyExistsException extends Exception {
    }

    public class BookDoesNotExistException extends Exception {
    }

    public class BookCheckedOutBySomeoneElseException extends Exception {
    }

    public class InvalidISBNException extends Exception {
    }
}
