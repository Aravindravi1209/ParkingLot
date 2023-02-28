package controllers;

import dtos.*;
import models.ParkingFloor;
import models.ParkingLot;
import models.ParkingSpot;
import services.ParkingLotService;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotController {
    private ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService)
    {
        this.parkingLotService=parkingLotService;
    }
    public CreateParkingLotResponseDto createParkingLot(CreateParkingLotRequestDto request){

        if(request.getNumberOfFloors()<2)
        {
            CreateParkingLotResponseDto response = new CreateParkingLotResponseDto();
            response.setResponseStatusDto(ResponseStatusDto.FAILURE);
            return response;
        }
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setAddress(request.getAddress());
        List<ParkingFloor> parkingFloors = new ArrayList<>();
        for(int i=0;i<request.getNumberOfFloors();i++)
        {
            parkingFloors.add(new ParkingFloor());
        }
        parkingLot.setParkingFloors(parkingFloors);
        ParkingLot createdParkingLot = parkingLotService.addParkingLot(parkingLot);
        CreateParkingLotResponseDto response = new CreateParkingLotResponseDto();
        response.setParkingLot(createdParkingLot);
        response.setResponseStatusDto(ResponseStatusDto.SUCCESS);
        return response;
    }

    public UpdateParkingLotResponseDto updateAddress(UpdateParkingLotRequestDto request)
    {
        ParkingLot updatedParkingLot = parkingLotService.updateAddress(
                request.getParkingLotId(), request.getAddress()
        );
        UpdateParkingLotResponseDto response = new UpdateParkingLotResponseDto();
        response.setParkingLot(updatedParkingLot);
        response.setResponseStatusDto(ResponseStatusDto.SUCCESS);
        return response;
    }

    public CreateParkingSpotResponseDto createParkingSpot(CreateParkingSpotRequestDto request)
    {
        if(request.getParkingFloor().getFloorNumber()<0)
        {
            CreateParkingSpotResponseDto response = new CreateParkingSpotResponseDto();
            response.setResponseStatusDto(ResponseStatusDto.FAILURE);
            return response;
        }

        ParkingSpot parkingSpot = parkingLotService.addParkingSpot(request.getParkingLotId(),
                request.getParkingFloor().getFloorNumber(), request.getSpotType());

        CreateParkingSpotResponseDto response = new CreateParkingSpotResponseDto();
        response.setParkingSpot(parkingSpot);
        response.setResponseStatusDto(ResponseStatusDto.SUCCESS);
        return response;
    }

}
