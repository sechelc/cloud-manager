package com.sechelc.cloud.manager.realtime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sechelc on 07.02.2015.
 */
public class GraphData {
    private List<BigDecimal[]> graphData = new ArrayList<>();

    public void addDataPoint(LogEntry logEntry, String lastSpeed, String lastVolume, String lastSlump, String lastTempProbe, String lastPressure){
        BigDecimal[] dataPoints = new BigDecimal[6];
        String speed = logEntry.getSpeed();
        String tempProbe = logEntry.getTempProbe();
        String slump = logEntry.getSlump();
        String volume = logEntry.getVolume();
        String pressure = logEntry.getPressure();
        speed= getBestValue(speed, lastSpeed);
        volume= getBestValue(volume, lastVolume);
        slump= getBestValue(slump, lastSlump);
        tempProbe= getBestValue(tempProbe, lastTempProbe);
        pressure= getBestValue(pressure, lastPressure);

        dataPoints[0] = BigDecimal.valueOf(logEntry.getTimestamp()).setScale(0, BigDecimal.ROUND_HALF_UP);
        dataPoints[1] = new BigDecimal(speed).setScale(2, BigDecimal.ROUND_HALF_UP);
        dataPoints[2] = new BigDecimal(slump).setScale(2, BigDecimal.ROUND_HALF_UP);
        dataPoints[3] = new BigDecimal(tempProbe).setScale(2, BigDecimal.ROUND_HALF_UP);
        dataPoints[4] =new BigDecimal(volume).setScale(2, BigDecimal.ROUND_HALF_UP);

        dataPoints[5] =new BigDecimal(pressure).setScale(2, BigDecimal.ROUND_HALF_UP);
        graphData.add(dataPoints);
    }

    private String getBestValue(String speed, String lastSpeed) {
        if(speed.contains("---")){
            if(!lastSpeed.contains("---")) {
                return lastSpeed;
            }
            else{
                return "0";
            }
        }
        return speed;
    }

    public List<BigDecimal[]> getGraphData() {
        return graphData;
    }

    public void setGraphData(List<BigDecimal[]> graphData) {
        this.graphData = graphData;
    }
}
