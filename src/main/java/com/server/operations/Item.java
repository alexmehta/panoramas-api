package com.server.operations;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "", uniqueConstraints = {})
public class Item {
    //character stores the current character
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    @Column
    private String name;
    @Column
    private String description;


    @Column
    private String website;
    @ElementCollection
    @CollectionTable(name = "bucket")
    private List<String> bucket = new ArrayList<>();


    public Item(String name, String description, String website, List<String> bucket) {
        this.name = name;
        this.description = description;
        this.website = website;
        this.bucket = bucket;
    }


    public Item() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBucket() {
        return bucket;
    }

    public void setBucket(List<String> tags) {
        this.bucket = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}