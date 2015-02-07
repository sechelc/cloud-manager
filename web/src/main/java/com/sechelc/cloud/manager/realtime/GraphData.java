package com.sechelc.cloud.manager.realtime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sechelc on 07.02.2015.
 */
public class GraphData {
    private List<String[]> graphData = new ArrayList<>();

    public void addDataPoint(LogEntry logEntry){
        String[] dataPoints = new String[5];
        dataPoints[0] = String.valueOf(logEntry.getTimestamp());
        dataPoints[1] = logEntry.getSpeed();
        dataPoints[2] = logEntry.getTempProbe();
        dataPoints[3] = logEntry.getSlump();
        dataPoints[4] = logEntry.getVolume();
        graphData.add(dataPoints);
    }

    public List<String[]> getGraphData() {
        return graphData;
    }

    public void setGraphData(List<String[]> graphData) {
        this.graphData = graphData;
    }
}
