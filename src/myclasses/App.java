/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Author;
import entity.Book;
import entity.History;
import entity.Reader;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class App {
    private Scanner scanner = new Scanner(System.in);
    private Book[] books = new Book[10];
    private Reader[] readers = new Reader[10];
    private History[] histories = new History[10];
    public void run(){
       String repeat = "yes";
       do{
           System.out.println("Выберите номер задачи: ");
           System.out.println("0: Закрыть программу");
           System.out.println("1: Добавить читателя");
           System.out.println("2: Добавить книгу");
           System.out.println("3: Список книг");
           System.out.println("4: Выдать книгу читателю");
           System.out.println("5: Вернуть книгу в библиотеку");
           System.out.println("6: Список читателей");
           System.out.println("7: Список выданных книг");
           int task = scanner.nextInt(); scanner.nextLine();
           switch (task) {
               case 0:
                   repeat = "no";
                   break;
               case 1:
                   addReader();
                   break;
               case 2:
                   addBook();
                   break;
               case 3:
                   printListBooks();
                   break;
               case 4:
                   givenBook();
                   break;
               case 5:
                   returnBook();
                   break;
               case 6:
                   printListReaders();
                   
                   break;
               case 7:
                   printListGivenBooks();
                   break;
               default:
                   System.out.println("Выберите номер из списка!");
           }
           
       }while("yes".equals(repeat));
       System.out.println("Пока! :)");
    }
    private boolean printListGivenBooks(){
        System.out.println("Список выданных книг: ");
                   int n = 0;
                   for (int i = 0; i < histories.length; i++) {
                       if(histories[i] != null && histories[i].getReturnBook() == null){
                           System.out.printf("%d. Книгу \"%s\" читает %s %s%n"
                                   ,i+1
                                   ,histories[i].getBook().getBookName()
                                   ,histories[i].getReader().getFirstname()
                                   ,histories[i].getReader().getLastname()
                           );
                           n++;
                       }
                   }
                   if(n < 1) {
                       System.out.println("Нет выданных книг");
                       return false;
                   }
                   return true;
    }

    private void addReader() {
        Reader reader = new Reader();
        System.out.print("Введите имя читателя: ");
        reader.setFirstname(scanner.nextLine());
        System.out.print("Введите фамилию читателя: ");
        reader.setLastname(scanner.nextLine());
        System.out.print("Введите телефон читателя: ");
        reader.setPhone(scanner.nextLine());
        System.out.println("Читатель инициирован: "+reader.toString());
        for (int i = 0; i < readers.length; i++) {
            if(readers[i] == null){
               readers[i] = reader;
               break;
            }
       }   
    }

    private void addBook() {
       Book book = new Book();
       System.out.print("Введите название книги: ");
       book.setBookName(scanner.nextLine());
       System.out.print("Введите количество авторов книги: ");
       int countAuthors = (scanner.nextInt());scanner.nextLine();
       Author[] authors = new Author[countAuthors];
       for (int i = 0; i < countAuthors; i++) {
           Author author = new Author();
           System.out.print("Введите имя автора "+(i+1)+": ");
           author.setFirstName(scanner.nextLine());
           System.out.print("Введите фамилию автора: ");
           author.setLastName(scanner.nextLine());
           System.out.print("Введите год рождения автора: ");
           author.setBirthYear(scanner.nextInt());scanner.nextLine();
           authors[i] = author;
       }
       book.setAuthors(authors);
       for (int i = 0; i < books.length; i++) {
           if(books[i] == null){
               books[i] = book;
               break;
           }
       }
       System.out.print("Введите год издания книги: ");
       book.setReleaseYear(scanner.nextInt());scanner.nextLine();
       System.out.println("Книга инициирована: "+book.toString());    
    }

    private void printListBooks() {
        System.out.println("Список книг: ");
        for (int i = 0; i < books.length; i++) {
            if(books[i] != null){
                System.out.printf("%d. %s. %s. %d.%n"
                       ,i+1
                       ,books[i].getBookName()
                       ,Arrays.toString(books[i].getAuthors())
                       ,books[i].getReleaseYear()
               );
           }

       }
    }

    private void givenBook() {
        System.out.println("---- Выдать книгу ----");
       System.out.println("Список книг: ");
       for (int i = 0; i < books.length; i++) {
          if(books[i] != null){
               System.out.printf("%d. %s. %s. %d.%n"
                       ,i+1
                       ,books[i].getBookName()
                       ,Arrays.toString(books[i].getAuthors())
                       ,books[i].getReleaseYear()
               );
          }
       }
       System.out.println("Выберите номер книги: ");
       int numberBook = scanner.nextInt(); scanner.nextLine();

       System.out.println("Список читателей: ");
       for (int i = 0; i < readers.length; i++) {
          if(readers[i] != null){
               System.out.printf("%d. %s. %s. Телефон: %s.%n"
                       ,i+1
                       ,readers[i].getFirstname()
                       ,readers[i].getLastname()
                       ,readers[i].getPhone()
               );
          }
       }
       System.out.println("Выберите номер читателя: ");
       int numberReader = scanner.nextInt(); scanner.nextLine();

       History history = new History();
       history.setBook(books[numberBook - 1]);
       history.setReader(readers[numberReader - 1]);
       Calendar c = new GregorianCalendar();
       history.setGivenBook(c.getTime());
       for (int i = 0; i < histories.length; i++) {
           if(histories[i] == null){
               histories[i] = history;
               break;
           }
       }
       System.out.println("--------------------");
    }

    private void returnBook() {
        System.out.println("---- Возврат книги ----");
        if(!printListGivenBooks()){
           return;
        }
        System.out.print("Выберите номер возвращаемой книги: ");
        int numberHistory = scanner.nextInt(); scanner.nextLine();
        Calendar c = new GregorianCalendar();
        histories[numberHistory - 1].setReturnBook(c.getTime());
    }

    private void printListReaders() {
        System.out.println("---- Список читателей -----");
        for (int i = 0; i < readers.length; i++) {
            if(readers[i] != null){
                System.out.printf("%d. %s %s. тел.: %s%n"
                       ,i+1
                       ,readers[i].getFirstname()
                       ,readers[i].getLastname()
                       ,readers[i].getPhone()
                );
            }
        }
    }
}
