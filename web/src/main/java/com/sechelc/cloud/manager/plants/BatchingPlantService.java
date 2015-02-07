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
        for (int i = 0; i <= 5; i++) {
            BatchingPlant batchingPlant = getbatchingPlant(i);
            batchingPlantRepository.save(batchingPlant);
        }
    }

    private BatchingPlant getbatchingPlant(int x) {
        BatchingPlant batchingPlant = new BatchingPlant();
        batchingPlant.setCompany("test");
        batchingPlant.setName("x" + x);
        batchingPlant.setLatitude(Double.valueOf(30.775980 + (Math.random() * 5 * x)).toString());
        batchingPlant.setLongitude(Double.valueOf(23.598660 + (Math.random() * 7 * x)).toString());
        return batchingPlant;
    }
}
