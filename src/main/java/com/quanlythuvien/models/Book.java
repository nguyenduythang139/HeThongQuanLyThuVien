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
    private StringProperty category;
    private ObjectProperty<Date> publicDate;
    private IntegerProperty quantity;
    private StringProperty language;
    private StringProperty state;
    private StringProperty location;

    public Book(int id, String name, String author, String category, Date publicDate, int quantity, String language, String state, String location) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.author = new SimpleStringProperty(author);
        this.category = new SimpleStringProperty(category);
        this.publicDate = new SimpleObjectProperty<>(publicDate);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.language = new SimpleStringProperty(language);
        this.state = new SimpleStringProperty(state);
        this.location = new SimpleStringProperty(location);
    }
    
    public Book(int id, String name){
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public int getId() { return id.get(); }
    public IntegerProperty idProperty() { return id; }

    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }

    public String getAuthor() { return author.get(); }
    public StringProperty authorProperty() { return author; }

    public String getCategory() { return category.get(); }
    public StringProperty categoryProperty() { return category; }

    public Date getPublicDate() { return publicDate.get(); }
    public ObjectProperty<Date> publicDateProperty() { return publicDate; }

    public int getQuantity() { return quantity.get(); }
    public IntegerProperty quantityProperty() { return quantity; }

    public String getLanguage() { return language.get(); }
    public StringProperty languageProperty() { return language; }

    public String getState() { return state.get(); }
    public StringProperty stateProperty() { return state; }

    public String getLocation() { return location.get(); }
    public StringProperty locationProperty() { return location; }
}