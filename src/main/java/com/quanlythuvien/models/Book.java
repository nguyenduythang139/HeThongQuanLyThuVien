/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlythuvien.models;
import javafx.beans.property.*;
import java.util.Date;
/**
 *
 * @author admin
 */
public class Book {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty author;
    private StringProperty publisher;
    private StringProperty category;
    private ObjectProperty<Date> publicDate;
    private IntegerProperty quantity;
    private StringProperty language;
    private StringProperty state;
    private StringProperty location;
    private BooleanProperty selected;

    public Book(int id, String name, String author, String publisher,String category, Date publicDate, int quantity, String language, String state, String location) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);
        this.publisher = new SimpleStringProperty(publisher);
        this.category = new SimpleStringProperty(category);
        this.publicDate = new SimpleObjectProperty<>(publicDate);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.language = new SimpleStringProperty(language);
        this.state = new SimpleStringProperty(state);
        this.location = new SimpleStringProperty(location);
    }
    
    public Book(int id, String name) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.selected = new SimpleBooleanProperty(false);
    }
     
    public int getId() { return this.id.get(); }
    public void setId(int id) { this.id.set(id); }

    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }

    public String getAuthor() { return author.get(); }
    public void setAuthor(String author) { this.author.set(author); }
    
    public String getPublisher() {return publisher.get();}
    public void setPublisher(String publisher) {this.publisher.set(publisher);}

    public String getCategory() { return category.get(); } 
    public void setCategory(String category) { this.category.set(category); }
    
    public Date getPublicDate() { return publicDate.get(); }
    public void setPublicDate(Date publicDate) { this.publicDate.set(publicDate); }

    public int getQuantity() { return quantity.get(); }
    public void setQuantity(int quantity) { this.quantity.set(quantity); }

    public String getLanguage() { return language.get(); }
    public void setLanguage(String language) { this.language.set(language); }

    public String getState() { return state.get(); }
    public void setState(String state) { this.state.set(state); }
    
    public String getLocation() { return location.get(); }
    public void setLocation(String location) { this.location.set(location); }
    
    public boolean isSelected() { return selected.get(); }
    public void setSelected(boolean selected) { this.selected.set(selected); }
}