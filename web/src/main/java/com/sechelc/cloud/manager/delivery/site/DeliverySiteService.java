package com.sechelc.cloud.manager.delivery.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by sechelc on 02.02.2015.
 */
@Service
public class DeliverySiteService {

    @Autowired
    private DeliverySiteRepository deliverySiteRepository;

    @PostConstruct
    public void initialize() {
        for (int i = 0; i <= 5; i++) {
            DeliverySite batchingPlant = getDeliverySite(i);
            deliverySiteRepository.save(batchingPlant);
        }
    }

    private DeliverySite getDeliverySite(int x) {
        DeliverySite batchingPlant = new DeliverySite();
        batchingPlant.setCompany("test");
        batchingPlant.setName("x" + x);
        batchingPlant.setLatitude(Double.valueOf(48.775980 + (Math.random() * 2 * (x%3))).toString());
        batchingPlant.setLongitude(Double.valueOf(30.598660 + (Math.random() * 2 * (x%3))).toString());
        return batchingPlant;
    }
}
