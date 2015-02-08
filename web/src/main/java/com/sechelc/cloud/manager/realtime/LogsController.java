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
        GraphData graphData = new GraphData();
        List<LogEntry> byTruckNo = logsRepository.findByTruckNo(truckNo);
        for (LogEntry logEntry : byTruckNo) {
            graphData.addDataPoint(logEntry);
        }
        return graphData;
    }

    @RequestMapping(value = "logs/graphGreaterThan", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public GraphData getGraphData(@RequestParam String truckNo, @RequestParam Long timestamp){
        GraphData graphData = new GraphData();
        List<LogEntry> byTruckNo = logsRepository.findByTruckNoAndTimestampGreaterThan(truckNo, timestamp);
        for (LogEntry logEntry : byTruckNo) {
            graphData.addDataPoint(logEntry);
        }
        return graphData;
    }

}
