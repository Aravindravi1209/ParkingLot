package services;

import models.*;
import repositories.ParkingLotRepository;

import java.util.ArrayList;
import java.util.List;

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

    public ParkingSpot addParkingSpot(Long id, int floorNumber, SpotType spotType)
    {
        ParkingLot currentParkingLot = parkingLotRepository.getParkingLotById(id);
        ParkingFloor parkingFloor = currentParkingLot.getParkingFloors().get(floorNumber);
        List<ParkingSpot> currentSpots = parkingFloor.getParkingSpots();
        if(currentSpots==null)
        {
            currentSpots = new ArrayList<>();
        }
        ParkingSpot newSpot = new ParkingSpot();
        newSpot.setSpotNumber(currentSpots.size());
        newSpot.setSpotType(spotType);
        newSpot.setParkingSpotStatus(ParkingSpotStatus.AVAILABLE);
        newSpot.setParkingFloor(parkingFloor);
        currentSpots.add(newSpot);
        parkingFloor.setParkingSpots(currentSpots);
        currentParkingLot.getParkingFloors().set(floorNumber,parkingFloor);
        ParkingLot savedParkingLot = addParkingLot(currentParkingLot);
        return savedParkingLot.getParkingFloors().get(floorNumber).getParkingSpots().get(currentSpots.size()-1);
    }
}
