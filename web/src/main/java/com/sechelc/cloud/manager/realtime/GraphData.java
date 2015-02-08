package com.sechelc.cloud.manager.realtime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sechelc on 07.02.2015.
 */
public class GraphData {
    private List<BigDecimal[]> graphData = new ArrayList<>();

    public void addDataPoint(LogEntry logEntry){
        BigDecimal[] dataPoints = new BigDecimal[5];
        dataPoints[0] = BigDecimal.valueOf(logEntry.getTimestamp()).setScale(0, BigDecimal.ROUND_HALF_UP);
        dataPoints[1] = new BigDecimal(logEntry.getSpeed()).setScale(2, BigDecimal.ROUND_HALF_UP);
        dataPoints[2] = new BigDecimal(logEntry.getTempProbe()).setScale(2, BigDecimal.ROUND_HALF_UP);
        dataPoints[3] = new BigDecimal(logEntry.getSlump()).setScale(2, BigDecimal.ROUND_HALF_UP);
        dataPoints[4] =new BigDecimal(logEntry.getVolume()).setScale(2, BigDecimal.ROUND_HALF_UP);
        graphData.add(dataPoints);
    }

    public List<BigDecimal[]> getGraphData() {
        return graphData;
    }

    public void setGraphData(List<BigDecimal[]> graphData) {
        this.graphData = graphData;
    }
}
