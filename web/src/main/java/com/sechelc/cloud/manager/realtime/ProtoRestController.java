package com.sechelc.cloud.manager.realtime;

import com.sechelc.cloud.manager.model.LogEntryProtocol;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by sechelc on 01.02.2015.
 */
@RestController
public class ProtoRestController {

    @Resource
    private LogsRepository logsRepository;

    @RequestMapping(value = "/addLog", method = RequestMethod.POST, consumes = "application/x-protobuf")
    @ResponseStatus(value = HttpStatus.OK)
    public void greeting(@RequestParam(value="log") LogEntryProtocol.LogEntry logEntryProtocol) {
        LogEntry logEntry = new LogEntry();
        logEntry.setAddedWater(logEntryProtocol.getAddedWater());
        logEntry.setAngle(logEntryProtocol.getAngle());
        logEntry.setBatteryVoltage(logEntryProtocol.getBatteryVoltage());
        logEntry.setCalcVolume(logEntryProtocol.getCalcVolume());
        logEntry.setChargerVoltage(logEntryProtocol.getChargerVoltage());
        logEntry.setCompany(logEntryProtocol.getCompany());
        logEntry.setDrumState(logEntryProtocol.getDrumState());
        logEntry.setLatitude(logEntryProtocol.getLatitude());
        logEntry.setLogQty(logEntryProtocol.getLogQty());
        logEntry.setLongitude(logEntryProtocol.getLongitude());
        logEntry.setMeasurementIndex(logEntryProtocol.getMeasurementIndex());
        logEntry.setMeasVolume(logEntryProtocol.getMeasVolume());
        logEntry.setPairLinkQuality(logEntryProtocol.getPairLinkQuality());
        logEntry.setPressure(logEntryProtocol.getPressure());
        logEntry.setRatio(logEntryProtocol.getRatio());
        logEntry.setSlump(logEntryProtocol.getSlump());
        logEntry.setSpeed(logEntryProtocol.getSpeed());
        logEntry.setStatus(logEntryProtocol.getStatus());
        logEntry.setSupplyVoltage(logEntryProtocol.getSupplyVoltage());
        logEntry.setTempAir(logEntryProtocol.getTempAir());
        logEntry.setTempProbe(logEntryProtocol.getTempProbe());
        logEntry.setTempReceiver(logEntryProtocol.getTempReceiver());
        logEntry.setTimestamp(logEntryProtocol.getTimestamp());
        logEntry.setTruckActivity(logEntryProtocol.getTruckActivity());
        logEntry.setTruckNo(logEntryProtocol.getTruckNo());
        logEntry.setTurnCountNeg(logEntryProtocol.getTurnCountNeg());
        logEntry.setTurnCountPos(logEntryProtocol.getTurnCountPos());
        logEntry.setTurnNumber(logEntryProtocol.getTurnNumber());
        logEntry.setViscosity(logEntryProtocol.getViscosity());
        logEntry.setVolume(logEntryProtocol.getVolume());
        logEntry.setWaterTemperature(logEntryProtocol.getWaterTemperature());
        logEntry.setYield(logEntryProtocol.getYield());
        logEntry.setzAxis(logEntryProtocol.getZAxis());
        logsRepository.save(logEntry);
    }
}
