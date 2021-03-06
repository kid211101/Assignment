/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Image;
import java.sql.Date;

/**
 *
 * @author khanh doan
 */
public class Book {
    private int ID;
    private String Name;
    private Category category;
    private String author;
    private int number;
    private Date EntryDate;
    private int price;

    public Book() {
    }

    public Book(int ID, String Name, Category Category, String Author, int number, Date EntryDate, int price) {
        this.ID = ID;
        this.Name = Name;
        this.category = Category;
        this.author = Author;
        this.number = number;
        this.EntryDate = EntryDate;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(Date EntryDate) {
        this.EntryDate = EntryDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "ID=" + ID + ", Name=" + Name + ", category=" + category + ", author=" + author + ", number=" + number + ", EntryDate=" + EntryDate + ", price=" + price + '}';
    }
    
}
