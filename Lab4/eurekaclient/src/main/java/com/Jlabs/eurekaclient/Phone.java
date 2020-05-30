package com.Jlabs.eurekaclient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String model;
    private String company;
    private String version;
    private Float price;
    private String purchaser;
    private Date purchaseDate;
    private boolean is_deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) { this.version = version; }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean isDeleted() { return is_deleted; }

    public void setDeleted(boolean deleted) { is_deleted = deleted; }

    @Override
    public String toString() {
        return String.format("Phone: id = %d model = %s purchaser = %s company = %s version = %d price = %f purchaseDate = %s",
                id, model, purchaser, company, version, price,  purchaseDate.toString());
    }
}