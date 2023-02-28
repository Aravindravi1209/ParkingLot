package dtos;

import models.ParkingSpot;

public class CreateParkingSpotResponseDto extends ResponseDto {
    private ParkingSpot parkingSpot;

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}
