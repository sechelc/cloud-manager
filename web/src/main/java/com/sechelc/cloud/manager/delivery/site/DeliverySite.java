package com.sechelc.cloud.manager.delivery.site;

import javax.persistence.*;

@Entity
public class DeliverySite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ds_id")
    private long id;

    private String company;
    private String name;
    private String latitude;
    private String longitude;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}