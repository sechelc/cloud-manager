package com.sechelc.cloud.manager.realtime;

import javax.persistence.*;

@Entity
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "log_id")
    private long id;

    public String truckNo;
    public String pressure;
    public String speed;
    public String volume;
    public String slump;
    public String viscosity;
    public String yield;
    public String tempReceiver;
    public String tempProbe;
    private long timestamp;
    private String status;
    private String measVolume;
    private String calcVolume;
    private String angle;
    private String ratio;
    private String turnNumber;
    private String pairLinkQuality;
    private String turnCountPos;
    private String turnCountNeg;
    private String tempAir;
    private String waterTemperature;
    private String batteryVoltage;
    private String supplyVoltage;
    private String chargerVoltage;
    private String zAxis;
    private String drumState;
    private String truckActivity;
    private String measurementIndex;
    private String logQty;
    private String addedWater;
    private String latitude;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    private String company;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTruckNo() {
        return truckNo;
    }

    public void setTruckNo(String truckNo) {
        this.truckNo = truckNo;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getSlump() {
        return slump;
    }

    public void setSlump(String slump) {
        this.slump = slump;
    }

    public String getViscosity() {
        return viscosity;
    }

    public void setViscosity(String viscosity) {
        this.viscosity = viscosity;
    }

    public String getYield() {
        return yield;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

    public String getTempReceiver() {
        return tempReceiver;
    }

    public void setTempReceiver(String tempReceiver) {
        this.tempReceiver = tempReceiver;
    }

    public String getTempProbe() {
        return tempProbe;
    }

    public void setTempProbe(String tempProbe) {
        this.tempProbe = tempProbe;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMeasVolume() {
        return measVolume;
    }

    public void setMeasVolume(String measVolume) {
        this.measVolume = measVolume;
    }

    public String getCalcVolume() {
        return calcVolume;
    }

    public void setCalcVolume(String calcVolume) {
        this.calcVolume = calcVolume;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getTurnNumber() {
        return turnNumber;
    }

    public void setTurnNumber(String turnNumber) {
        this.turnNumber = turnNumber;
    }

    public String getPairLinkQuality() {
        return pairLinkQuality;
    }

    public void setPairLinkQuality(String pairLinkQuality) {
        this.pairLinkQuality = pairLinkQuality;
    }

    public String getTurnCountPos() {
        return turnCountPos;
    }

    public void setTurnCountPos(String turnCountPos) {
        this.turnCountPos = turnCountPos;
    }

    public String getTurnCountNeg() {
        return turnCountNeg;
    }

    public void setTurnCountNeg(String turnCountNeg) {
        this.turnCountNeg = turnCountNeg;
    }

    public String getTempAir() {
        return tempAir;
    }

    public void setTempAir(String tempAir) {
        this.tempAir = tempAir;
    }

    public String getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(String waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public String getBatteryVoltage() {
        return batteryVoltage;
    }

    public void setBatteryVoltage(String batteryVoltage) {
        this.batteryVoltage = batteryVoltage;
    }

    public String getSupplyVoltage() {
        return supplyVoltage;
    }

    public void setSupplyVoltage(String supplyVoltage) {
        this.supplyVoltage = supplyVoltage;
    }

    public String getChargerVoltage() {
        return chargerVoltage;
    }

    public void setChargerVoltage(String chargerVoltage) {
        this.chargerVoltage = chargerVoltage;
    }

    public String getzAxis() {
        return zAxis;
    }

    public void setzAxis(String zAxis) {
        this.zAxis = zAxis;
    }

    public String getDrumState() {
        return drumState;
    }

    public void setDrumState(String drumState) {
        this.drumState = drumState;
    }

    public String getTruckActivity() {
        return truckActivity;
    }

    public void setTruckActivity(String truckActivity) {
        this.truckActivity = truckActivity;
    }

    public String getMeasurementIndex() {
        return measurementIndex;
    }

    public void setMeasurementIndex(String measurementIndex) {
        this.measurementIndex = measurementIndex;
    }

    public String getLogQty() {
        return logQty;
    }

    public void setLogQty(String logQty) {
        this.logQty = logQty;
    }

    public String getAddedWater() {
        return addedWater;
    }

    public void setAddedWater(String addedWater) {
        this.addedWater = addedWater;
    }
}