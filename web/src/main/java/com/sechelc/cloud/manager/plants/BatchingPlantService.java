package com.sechelc.cloud.manager.plants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by sechelc on 02.02.2015.
 */
@Service
public class BatchingPlantService {

    @Autowired
    private BatchingPlantRepository batchingPlantRepository;

    @PostConstruct
    public void initialize() {
        for (int i = 0; i <= 2; i++) {
            BatchingPlant batchingPlant = getbatchingPlant(i);
            batchingPlantRepository.save(batchingPlant);
        }
    }

    private BatchingPlant getbatchingPlant(int x) {
        BatchingPlant batchingPlant = new BatchingPlant();
        batchingPlant.setCompany("test");
        batchingPlant.setName("x" + x);
        batchingPlant.setLatitude(Double.valueOf(45.775980 + (Math.random() * 2 * (x%3)/10)).toString());
        batchingPlant.setLongitude(Double.valueOf(24.598660 + (Math.random() * 2 * (x%3)/10)).toString());
        return batchingPlant;
    }
}
