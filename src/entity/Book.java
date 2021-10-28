/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author user
 */
public class Book implements Serializable{
    private String bookName;
    private Author[] authors;
    private int releaseYear;
    private int quantity;
    private int count;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Book{" 
                + "bookName=" + bookName 
                + ", authors=" + Arrays.toString(authors) 
                + ", releaseYear=" + releaseYear 
                + ", quantity=" + quantity 
                + ", count=" + count 
                + '}';
    }

    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.bookName);
        hash = 13 * hash + Arrays.deepHashCode(this.authors);
        hash = 13 * hash + this.releaseYear;
        hash = 13 * hash + this.quantity;
        hash = 13 * hash + this.count;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.releaseYear != other.releaseYear) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.count != other.count) {
            return false;
        }
        if (!Objects.equals(this.bookName, other.bookName)) {
            return false;
        }
        if (!Arrays.deepEquals(this.authors, other.authors)) {
            return false;
        }
        return true;
    }
    
}
