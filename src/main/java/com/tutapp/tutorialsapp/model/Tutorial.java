package com.tutapp.tutorialsapp.model;

import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Data model class corresponds to entity and table 'tutorials'
// @Entity indicates that the class is a persistent Java class. "tutorials" is the table that maps to this entity
@Entity 
@Table(name = "tutorials")
public class Tutorial {
    
    @Id // indicate primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // define generation strategy for primary key
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "title")
    private String description;

    @Column(name = "published")
    private boolean published;
}
