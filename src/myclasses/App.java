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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class App {
    public void run(){
       String repeat = "yes";
       Scanner scanner = new Scanner(System.in);
       do{
           System.out.println("Выберите номер задачи: ");
           System.out.println("0: Закрыть программу");
           System.out.println("1: Добавить читателя");
           int task = scanner.nextInt(); scanner.nextLine();
           switch (task) {
               case 0:
                   repeat = "no";
                   break;
               case 1:
                   Reader reader = new Reader();
                   System.out.print("Введите имя читателя: ");
                   reader.setFirstname(scanner.nextLine());
                   System.out.print("Введите фамилию читателя: ");
                   reader.setLastname(scanner.nextLine());
                   System.out.print("Введите телефон читателя: ");
                   reader.setPhone(scanner.nextLine());
                   System.out.println("Читатель инициирован: "+reader.toString());
                   break;
               default:
                   System.out.println("Выберите номер из списка!");
           }
           
       }while("yes".equals(repeat));
       System.out.println("Пока! :)");
    }
}
