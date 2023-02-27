package services;

import models.ParkingLot;
import repositories.ParkingLotRepository;

public class ParkingLotService {
    private ParkingLotRepository parkingLotRepository;
    public ParkingLotService(ParkingLotRepository parkingLotRepository)
    {
        this.parkingLotRepository=parkingLotRepository;
    }
    public ParkingLot addParkingLot(ParkingLot parkingLot)
    {
        return parkingLotRepository.save(parkingLot);
    }

    public ParkingLot updateAddress(Long id, String newAddress)
    {
        ParkingLot currentParkingLot = parkingLotRepository.getParkingLotById(id);
        currentParkingLot.setAddress(newAddress);
        ParkingLot updatedParkingLot = parkingLotRepository.update(id, currentParkingLot);
        return updatedParkingLot;
    }
}
