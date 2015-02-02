package com.sechelc.cloud.manager.realtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by sechelc on 02.02.2015.
 */
@Service
public class LogEntryService {
    @Autowired
    private LogsRepository logsRepository;

    @PostConstruct
    public void initialize(){
        LogEntry logEntry = getLogEntry();
        LogEntry logEntry1 = getLogEntry();
        logEntry1.setTimestamp(1000);
        LogEntry logEntry2 = getLogEntry();
        logEntry2.setTruckNo("2");
        LogEntry logEntry3 = getLogEntry();
        logEntry3.setTruckNo("2");
        logEntry3.setTimestamp(2000);
        logsRepository.save(logEntry);
        logsRepository.save(logEntry1);
        logsRepository.save(logEntry2);
        logsRepository.save(logEntry3);
    }

    private LogEntry getLogEntry() {
        LogEntry logEntry = new LogEntry();
        logEntry.setAddedWater("12");
        logEntry.setAngle("12");
        logEntry.setBatteryVoltage("12");
        logEntry.setCalcVolume("12");
        logEntry.setChargerVoltage("12");
        logEntry.setCompany("test");
        logEntry.setDrumState("12");
        logEntry.setLatitude("46.775980");
        logEntry.setLogQty("10");
        logEntry.setLongitude("23.598660");
        logEntry.setMeasurementIndex("10");
        logEntry.setMeasVolume("10");
        logEntry.setPairLinkQuality("10");
        logEntry.setPressure("10");
        logEntry.setRatio("10");
        logEntry.setSlump("20");
        logEntry.setSpeed("3");
        logEntry.setStatus("10");
        logEntry.setSupplyVoltage("10");
        logEntry.setTempAir("23");
        logEntry.setTempProbe("28");
        logEntry.setTempReceiver("30");
        logEntry.setTimestamp(200);
        logEntry.setTruckActivity("10");
        logEntry.setTruckNo("1");
        logEntry.setTurnCountNeg("10");
        logEntry.setTurnCountPos("10");
        logEntry.setTurnNumber("10");
        logEntry.setViscosity("15");
        logEntry.setVolume("50");
        logEntry.setWaterTemperature("0");
        logEntry.setYield("10");
        logEntry.setzAxis("1");
        return logEntry;
    }
}
