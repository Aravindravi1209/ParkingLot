package dtos;

import models.ParkingFloor;
import models.ParkingLot;
import models.SpotType;

public class CreateParkingSpotRequestDto {
    private SpotType spotType;
    private ParkingFloor parkingFloor;
    private Long parkingLotId;

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public void setParkingFloor(ParkingFloor parkingFloor) {
        this.parkingFloor = parkingFloor;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}
