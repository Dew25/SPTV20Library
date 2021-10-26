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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import tools.SaverToFiles;

/**
 *
 * @author user
 */
public class App {
    private Scanner scanner = new Scanner(System.in);
    private List<Book> books = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    private SaverToFiles saverToFiles = new SaverToFiles();
    
    public App(){
        books = saverToFiles.loadBooks();
        readers = saverToFiles.loadReaders();
        histories = saverToFiles.loadHistories();
    }
    
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
                   for (int i = 0; i < histories.size(); i++) {
                       //печатем книгу если она !null, выдана и 
                       if(histories.get(i) != null 
                               && histories.get(i).getReturnBook() == null
                               && (
                                     histories.get(i).getBook().getCount() 
                                    < histories.get(i).getBook().getQuantity()+1
                                  )
                               ){
                           System.out.printf("%d. Книгу \"%s\" читает %s %s%n"
                                   ,i+1
                                   ,histories.get(i).getBook().getBookName()
                                   ,histories.get(i).getReader().getFirstname()
                                   ,histories.get(i).getReader().getLastname()
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
        readers.add(reader);
        saverToFiles.saveReaders(readers);
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
       System.out.print("Введите год издания книги: ");
       book.setReleaseYear(scanner.nextInt());scanner.nextLine();
       System.out.print("Введите количество экземпляров книги: ");
       book.setQuantity(scanner.nextInt());scanner.nextLine();
       book.setCount(book.getQuantity());
       System.out.println("Книга инициирована: "+book.toString());    
       books.add(book);
       saverToFiles.saveBooks(books);
    }

    private void printListBooks() {
        System.out.println("Список книг: ");
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i) != null 
                    && books.get(i).getCount() > 0
                    && books.get(i).getCount() < books.get(i).getQuantity() + 1){
                System.out.printf("%d. %s. %s. %d.%n"
                       ,i+1
                       ,books.get(i).getBookName()
                       ,Arrays.toString(books.get(i).getAuthors())
                       ,books.get(i).getReleaseYear()
               );
           }

       }
    }

    private void givenBook() {
        System.out.println("---- Выдать книгу ----");
       System.out.println("Список книг: ");
       int n = 0;
       for (int i = 0; i < books.size(); i++) {
          if(books.get(i) != null && books.get(i).getCount() > 0){
               System.out.printf("%d. %s. %s. %d.%n"
                       ,i+1
                       ,books.get(i).getBookName()
                       ,Arrays.toString(books.get(i).getAuthors())
                       ,books.get(i).getReleaseYear()
               );
               n++;
          }
       }
       if(n < 1){
           System.out.println("Нет книг для чтения");
           return;
       }
       System.out.println("Выберите номер книги: ");
       int numberBook = scanner.nextInt(); scanner.nextLine();

       System.out.println("Список читателей: ");
       for (int i = 0; i < readers.size(); i++) {
          if(readers.get(i) != null){
               System.out.printf("%d. %s. %s. Телефон: %s.%n"
                       ,i+1
                       ,readers.get(i).getFirstname()
                       ,readers.get(i).getLastname()
                       ,readers.get(i).getPhone()
               );
          }
       }
       System.out.println("Выберите номер читателя: ");
       int numberReader = scanner.nextInt(); scanner.nextLine();

       History history = new History();
       history.setBook(books.get(numberBook - 1));
       history.setReader(readers.get(numberReader - 1));
       Calendar c = new GregorianCalendar();
       history.setGivenBook(c.getTime());
       histories.add(history);
       history.getBook().setCount(history.getBook().getCount() - 1);
       saverToFiles.saveBooks(books);
       saverToFiles.saveHistories(histories);
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
        histories.get(numberHistory - 1).setReturnBook(c.getTime());
        histories.get(numberHistory - 1).getBook().setCount(
           histories.get(numberHistory - 1)
                    .getBook()
                    .getCount() + 1
        );
        saverToFiles.saveBooks(books);
        saverToFiles.saveHistories(histories);
    }

    private void printListReaders() {
        System.out.println("---- Список читателей -----");
        for (int i = 0; i < readers.size(); i++) {
            if(readers.get(i) != null){
                System.out.printf("%d. %s %s. тел.: %s%n"
                       ,i+1
                       ,readers.get(i).getFirstname()
                       ,readers.get(i).getLastname()
                       ,readers.get(i).getPhone()
                );
            }
        }
    }
}
