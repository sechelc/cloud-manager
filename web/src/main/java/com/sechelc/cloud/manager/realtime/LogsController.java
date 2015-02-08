package com.sechelc.cloud.manager.realtime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sechelc on 07.02.2015.
 */
@RestController
public class LogsController {
    @Autowired
    private LogsRepository logsRepository;


    @RequestMapping(value = "logs/graph", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public GraphData getGraphData(@RequestParam String truckNo){
        String lastSpeed ="0";
        String lastVolume="0";
        String lastSlump="0";
        String lastTempProbe="0";
        GraphData graphData = new GraphData();
        List<LogEntry> byTruckNo = logsRepository.findByTruckNo(truckNo);
        for (LogEntry logEntry : byTruckNo) {
            lastSpeed = getBestValue(lastSpeed, logEntry.getSpeed());
            lastVolume = getBestValue(lastVolume, logEntry.getVolume());
            lastSlump = getBestValue(lastSlump, logEntry.getSlump());
            lastTempProbe = getBestValue(lastTempProbe, logEntry.getTempProbe());
            graphData.addDataPoint(logEntry, lastSpeed, lastVolume, lastSlump, lastTempProbe);
        }
        return graphData;
    }

    private String getBestValue(String lastSpeed, String speed) {
        if(!speed.contains("---")){
            return speed;
        }
        return lastSpeed;
    }

    @RequestMapping(value = "logs/graphGreaterThan", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public GraphData getGraphData(@RequestParam String truckNo, @RequestParam Long timestamp){
        String lastSpeed ="0";
        String lastVolume="0";
        String lastSlump="0";
        String lastTempProbe="0";
        GraphData graphData = new GraphData();
        List<LogEntry> byTruckNo = logsRepository.findByTruckNoAndTimestampGreaterThan(truckNo, timestamp);
        for (LogEntry logEntry : byTruckNo) {
            lastSpeed = getBestValue(lastSpeed, logEntry.getSpeed());
            lastVolume = getBestValue(lastVolume, logEntry.getVolume());
            lastSlump = getBestValue(lastSlump, logEntry.getSlump());
            lastTempProbe = getBestValue(lastTempProbe, logEntry.getTempProbe());
            graphData.addDataPoint(logEntry, lastSpeed, lastVolume, lastSlump, lastTempProbe);
        }
        return graphData;
    }

    @RequestMapping(value = "logs/add", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void addEntry(){
        LogEntry logEntry = LogEntryService.getLogEntry("100");
        logEntry.setTempProbe("28.5");
        logEntry.setPressure(String.valueOf(Math.tan(Math.floor(Math.random()*10) + 0.0d)*10));
        logEntry.setSlump(String.valueOf(Math.cos(Math.floor(Math.random()*10) + 0.0d)*10));
        logEntry.setSpeed(String.valueOf(Math.floor(Math.random()*10)));
        logEntry.setTimestamp(System.currentTimeMillis() + 360*30000);
        logsRepository.save(logEntry);
    }

}
