package repositories;

import controllers.ParkingLotController;
import models.ParkingLot;

import java.util.HashMap;

public class ParkingLotRepository {
    private HashMap<Long, ParkingLot> parkingLots = new HashMap<>();
    private Long lastCount = 0L;

    public ParkingLot save(ParkingLot parkingLot)
    {
        lastCount+=1;
        parkingLot.setId(lastCount);
        parkingLots.put(lastCount, parkingLot);
        return parkingLot;
    }
    public ParkingLot update(Long id, ParkingLot parkingLot)
    {
        parkingLots.put(id,parkingLot);
        return parkingLots.get(id);
    }
    public ParkingLot getParkingLotById(Long id)
    {
        return parkingLots.get(id);
    }
}
