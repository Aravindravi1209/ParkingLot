package dtos;

import controllers.ParkingLotController;
import models.ParkingLot;

public class CreateParkingLotResponseDto extends ResponseDto{
    private ParkingLot parkingLot;

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
