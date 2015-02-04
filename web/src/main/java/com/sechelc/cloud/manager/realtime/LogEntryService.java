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
    public void initialize() {
        for (int i = 0; i <= 30; i++) {
            LogEntry logEntry = getLogEntry(String.valueOf(i));
            if (i % 2 == 0) {
                logEntry.setCompany("altacompanie");
            }
            logEntry.setTimestamp(i * 100);
            logsRepository.save(logEntry);
        }

        for (int i = 0; i <= 360; i++) {
            LogEntry logEntry = getLogEntry("100");
            logEntry.setTempProbe(String.valueOf(Math.sin(i + 0.0d)));
            logEntry.setPressure(String.valueOf(Math.tan(i + 0.0d)));
            logEntry.setSlump(String.valueOf(Math.cos(i + 0.0d)));
            logEntry.setSpeed(String.valueOf(i));
            if(i<100) {
                logEntry.setVolume(String.valueOf(i));
            } else if (i<300){
                logEntry.setVolume("100");
            } else{
                logEntry.setVolume(String.valueOf(100 - (300 - i )));
            }
            logEntry.setTimestamp(i * 100);
            logsRepository.save(logEntry);
        }
    }

    private LogEntry getLogEntry(String x) {
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
        logEntry.setTruckNo(x);
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
