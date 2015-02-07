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
            DeliverySite batchingPlant = BatchingPlant(i);
            deliverySiteRepository.save(batchingPlant);
        }
    }

    private DeliverySite BatchingPlant(int x) {
        DeliverySite batchingPlant = new DeliverySite();
        batchingPlant.setCompany("test");
        batchingPlant.setName("x");
        batchingPlant.setLatitude(Double.valueOf(43.775980 + (Math.random() * 6 * x)).toString());
        batchingPlant.setLongitude(Double.valueOf(25.598660 + (Math.random() * 15 * x)).toString());
        return batchingPlant;
    }
}
