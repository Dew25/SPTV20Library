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
import facade.AuthorFacade;
import facade.BookFacade;
import facade.HistoryFacade;
import facade.ReaderFacade;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import tools.Singleton;

/**
 *
 * @author user
 */
public class App {
    public static boolean toFile = false;
    
    private Scanner scanner = new Scanner(System.in);
    private ReaderFacade readerFacade;
    private BookFacade bookFacade;
    private AuthorFacade authorFacade;
    private HistoryFacade historyFacade;
    private Singleton singleton;
//    private List<Book> books = new ArrayList<>();
//    private List<Author> authors = new ArrayList<>();
//    private List<Reader> readers = new ArrayList<>();
//    private List<History> histories = new ArrayList<>();
//    private Keeping keeper;
    
    public App(){
//        if(toFile){
//            keeper = new SaverToFiles();
//        }else{
//            keeper = new SaverToBase();
//        }
//        books = keeper.loadBooks();
//        authors = keeper.loadAuthors();
//        readers = keeper.loadReaders();
//        histories = keeper.loadHistories();
        readerFacade = new ReaderFacade(Reader.class);
        bookFacade = new BookFacade(Book.class);
        authorFacade = new AuthorFacade(Author.class);
        historyFacade = new HistoryFacade(History.class);
        singleton = Singleton.getInstance();
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
           System.out.println("8: Список авторов");
           System.out.println("9: Добавить автора");
           System.out.println("10: Изменение данных книги");
           System.out.println("11: Изменение данных читателя");
           System.out.println("12: Изменение данных автора");
           int task = getNumber();
           
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
               case 8:
                   printListAuthors();
                   break;
               case 9:
                   addAuthor();
                   break;
               case 10:
                   changeBook();
                   break;
               case 11:
                   changeReader();
                   break;
               case 12:
                   changeAuthor();
                   break;
               default:
                   System.out.println("Попробуй еще раз: ");
           }
           
       }while("yes".equals(repeat));
       System.out.println("Пока! :)");
    }
    
    private Set<Integer> printListGivenBooks(){
        Set<Integer> setNumbersGivenBooks = new HashSet<>();
        System.out.println("Список выданных книг: ");
        List<History> histories = historyFacade.findListGiverBooks();
        for (int i = 0; i < histories.size(); i++) {
                System.out.printf("%d. Книгу \"%s\" читает %s %s. Срок возврата: %s%n"
                       ,histories.get(i).getId()
                       ,histories.get(i).getBook().getBookName()
                       ,histories.get(i).getReader().getFirstname()
                       ,histories.get(i).getReader().getLastname()
                       ,showReturnDateBook(histories.get(i).getBook())
                );
                setNumbersGivenBooks.add(histories.get(i).getId().intValue());
        }
      
        if(setNumbersGivenBooks.isEmpty()){
            System.out.println("Список выданных книг пуст.");
        }
       return setNumbersGivenBooks;
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
        readerFacade.create(reader);
//        readers.add(reader);
//        keeper.saveReaders(readers);
    }

    private void addBook() {
       Book book = new Book();
       Set<Integer> setNumbersAuthors = printListAuthors();
       if(setNumbersAuthors.isEmpty()){
           System.out.println("Нет авторов, добавьте");
           return;
       }
       System.out.println("Если в списке есть автор для книги введите 1: ");
       if(getNumber() != 1){
           System.out.println("Добавьте автора");
           return;
       }
       System.out.print("Введите количество авторов книги: ");
       int countAuthors = getNumber();
       List<Author> authorsBook = new ArrayList<>(countAuthors);
       for (int i = 0; i < countAuthors; i++) {
           Author author = new Author();
           System.out.println("Введите номер автора "+(i+1)+" из списка");
           authorsBook.add(authorFacade.find((long)insertNumber(setNumbersAuthors)));
       }
       book.setAuthors(authorsBook);
       System.out.print("Введите название книги: ");
       book.setBookName(scanner.nextLine());
       System.out.print("Введите год издания книги: ");
       book.setReleaseYear(getNumber());
       System.out.print("Введите количество экземпляров книги: ");
       book.setQuantity(getNumber());
       book.setCount(book.getQuantity());
       System.out.println("Книга инициирована: "+book.toString());    
       bookFacade.create(book);
    }
    /**
     * Метод выводит список книг библиотеки 
     * и количество экземпляров в наличии
     * или дату возврата читаемой книги
     * @return setNumbersBooks - набор номеров книг, экземпляры которых есть в наличии
     */
    private Set<Integer> printListBooks() {
        Set<Integer> setNumbersBooks = new HashSet<>();
        List<Book> books = bookFacade.findAll();
        System.out.println("Список книг: ");
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i) != null 
                    && books.get(i).getCount() > 0
                    && books.get(i).getCount() < books.get(i).getQuantity() + 1){
                System.out.printf("%d. %s. %s. %d. В наличии: %d%n"
                       ,books.get(i).getId()
                       ,books.get(i).getBookName()
                       ,Arrays.toString(books.get(i).getAuthors().toArray())
                       ,books.get(i).getReleaseYear()
                       ,books.get(i).getCount()
                );
                setNumbersBooks.add(books.get(i).getId().intValue());
           }else if(books.get(i) != null){
                System.out.printf("%d. %s. %s. %d. - все экземпляры на руках до: %s%n"
                       ,books.get(i).getId()
                       ,books.get(i).getBookName()
                       ,Arrays.toString(books.get(i).getAuthors().toArray())
                       ,books.get(i).getReleaseYear()
                       ,showReturnDateBook(books.get(i))//"5.10.2021"
               );
           }

       }
        return setNumbersBooks;
    }
    private String showReturnDateBook(Book book){
        LocalDate givenDate = null;
        History history = historyFacade.findHistoryByBook(book);
        if(history == null){
            return "";
        }
        givenDate= history.getGivenBook().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate returnDateBook = givenDate.plusDays(14);
        return  returnDateBook.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    private void givenBook() {
       System.out.println("---- Выдать книгу ----");
       Set<Integer> setNumbersBooks = printListBooks();
       if(setNumbersBooks.isEmpty()){
           System.out.println("Нет книг для выдачи");
          return;
       }
       System.out.println("Выберите номер книги: ");
       int numberBook= insertNumber(setNumbersBooks);
       
       Set<Integer> setNumbersReaders = printListReaders();
       System.out.println("Выберите номер читателя: ");
       if(setNumbersReaders.isEmpty()){
           System.out.println("Нет читателей");
          return;
       }
       int numberReader= insertNumber(setNumbersReaders);
       History history = new History();
       Book book = bookFacade.find((long)numberBook);
       history.setBook(book);
       history.setReader(readerFacade.find((long)numberReader));
       Calendar c = new GregorianCalendar();
       history.setGivenBook(c.getTime());
       book.setCount(book.getCount() - 1);
       bookFacade.edit(book);
       historyFacade.edit(history);
       System.out.println("--------------------");
    }

    private void returnBook() {
        System.out.println("---- Возврат книги ----");
        Set<Integer> setNumbersHistories = printListGivenBooks();
        if(setNumbersHistories.isEmpty()){
            System.out.println("Нет выданных книг.");
           return;
        }
        System.out.print("Выберите номер возвращаемой книги: ");
        int numberHistory=insertNumber(setNumbersHistories);
        History history = historyFacade.find((long)numberHistory);
        Calendar c = new GregorianCalendar();
        history.setReturnBook(c.getTime());
        Book book = bookFacade.find(history.getBook().getId());
        book.setCount(book.getCount() + 1);
        bookFacade.edit(book);
        historyFacade.edit(history);
    }

    private Set<Integer> printListReaders() {
        System.out.println("----- Список читателей -----");
        Set<Integer> setNumbersReaders = new HashSet<>();
        List<Reader> readers = readerFacade.findAll();
        for (int i = 0; i < readers.size(); i++) {
            if(readers.get(i) != null){
                System.out.printf("%d. %s %s. тел.: %s%n"
                       ,readers.get(i).getId()
                       ,readers.get(i).getFirstname()
                       ,readers.get(i).getLastname()
                       ,readers.get(i).getPhone()
                );
                setNumbersReaders.add(readers.get(i).getId().intValue());
            }
        }
        if(setNumbersReaders.isEmpty()){
            System.out.println("Список читателей пуст.");
        }
        return setNumbersReaders;
    }

    private int getNumber() {
        int number;
        do{
           String strNumber = scanner.nextLine();
           try {
               number = Integer.parseInt(strNumber);
               return number;
           } catch (NumberFormatException e) {
               System.out.println("Введено \""+strNumber+"\". Выбирайте номер ");
           }
       }while(true);
    }

    private int insertNumber(Set<Integer> setNumbers) {
        int number=0;
        do{
           number = getNumber();
           if(setNumbers.contains(number)){
               break;
           }
           System.out.println("Попробуй еще раз: ");
       }while(true);
       return number; 
    }

    private Set<Integer> printListAuthors() {
        System.out.println("----- Список авторов -----");
        Set<Integer> setNumbersAuthors = new HashSet<>();
        List<Author> authors = authorFacade.findAll();
        for (int i = 0; i < authors.size(); i++) {
            if(authors.get(i) != null){
                System.out.printf("%d. %s %s. %d%n"
                       ,authors.get(i).getId()
                       ,authors.get(i).getFirstName()
                       ,authors.get(i).getLastName()
                       ,authors.get(i).getBirthYear()
                );
                setNumbersAuthors.add(authors.get(i).getId().intValue());
            }
        }
        if(setNumbersAuthors.isEmpty()){
            System.out.println("Список авторов пуст");
        }
        return setNumbersAuthors;
        
    }

    private void addAuthor() {
        Author author = new Author();
        System.out.print("Введите имя автора: ");
        author.setFirstName(scanner.nextLine());
        System.out.print("Введите фамилию автора: ");
        author.setLastName(scanner.nextLine());
        System.out.print("Введите год рождени автора: ");
        author.setBirthYear(getNumber());
        System.out.println("Автор инициирован: "+author.toString());
        authorFacade.create(author);
    }

    private void changeBook() {
        Set<Integer> changeNumber = new HashSet<>();
        changeNumber.add(1);
        changeNumber.add(2);
        Set<Integer> setNumbersBooks = printListBooks();
        if(setNumbersBooks.isEmpty()){
            System.out.println("В базе нет книг");
            return;
        }
        System.out.println("Выберите номер книги: ");
        int numberBook = insertNumber(setNumbersBooks);
        Book book = bookFacade.find((long) numberBook);
        System.out.println("Название книги: "+book.getBookName());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        int change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новое название книги: ");
            book.setBookName(scanner.nextLine());
        }
        System.out.println("Год издания книги: "+book.getReleaseYear());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новый год издания книги: ");
            book.setReleaseYear(getNumber());
        }
        System.out.println("Количество экземпляров книги: "+book.getQuantity());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новое количество экземпляров книги: ");
            int newQuantity = getNumber();
            int oldQuantity = book.getQuantity();
            int oldCount = book.getCount();
            int newCount = oldCount + (newQuantity - oldQuantity);
            book.setQuantity(newQuantity);
            book.setCount(newCount);
        }
        Set<Integer> setNumbersAuthors = printListAuthors();
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите количество авторов: ");
            int countAuthors = getNumber();
            book.getAuthors().clear();
            for (int i = 0; i < countAuthors; i++) {
                System.out.println("Введите номер "+(i+1)+" автора из списка: ");
                int numberAuthor = insertNumber(setNumbersAuthors);
                book.getAuthors().add(authorFacade.find((long)numberAuthor));
            }
        }
        bookFacade.edit(book);
    }

    private void changeReader() {
        Set<Integer> changeNumber = new HashSet<>();
        changeNumber.add(1);
        changeNumber.add(2);
        Set<Integer> setNumbersReaders = printListReaders();
        if(setNumbersReaders.isEmpty()){
            return;
        }
        System.out.println("Выберите номер читателя: ");
        int numberReader = insertNumber(setNumbersReaders);
        Reader reader = readerFacade.find((long)numberReader);
        System.out.println("Имя читателя: "+reader.getFirstname());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        int change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новое имя читателя: ");
            reader.setFirstname(scanner.nextLine());
        }
        System.out.println("Фамилия читателя: "+reader.getLastname());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новую фамилию читателя: ");
            reader.setLastname(scanner.nextLine());
        }
        System.out.println("Телефон читателя: "+reader.getPhone());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новый телефон читателя: ");
            reader.setPhone(scanner.nextLine());
        }
        readerFacade.edit(reader);
    }

    private void changeAuthor() {
        Set<Integer> changeNumber = new HashSet<>();
        changeNumber.add(1);
        changeNumber.add(2);
        Set<Integer> setNumbersAuthors = printListAuthors();
        if(setNumbersAuthors.isEmpty()){
            return;
        }
        System.out.println("Выберите номер автора: ");
        int numberAuthor = insertNumber(setNumbersAuthors);
        Author author = authorFacade.find((long) numberAuthor);
        System.out.println("Имя автора: "+author.getFirstName());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        int change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новое имя автора: ");
            author.setFirstName(scanner.nextLine());
        }
        System.out.println("Фамилия автора: "+author.getLastName());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новую фамилию читателя: ");
            author.setLastName(scanner.nextLine());
        }
        System.out.println("Год рождения автора: "+author.getBirthYear());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новый год рождения автора: ");
            author.setBirthYear(getNumber());
        }
        authorFacade.edit(author);
    }
}
