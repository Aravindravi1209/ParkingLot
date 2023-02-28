import Strategies.spotAssignmentStrategies.RandomSpotAssignmentStrategy;
import Strategies.spotAssignmentStrategies.SpotAssignmentStrategy;
import controllers.ParkingLotController;
import controllers.TicketController;
import dtos.*;
import models.*;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;
import services.ParkingLotService;
import services.TicketService;

public class Client {
    public static void main(String[] args) {

        ObjectRegistry.put("parkingLotRepository",new ParkingLotRepository());

        ObjectRegistry.put("parkingLotService", new ParkingLotService(
                (ParkingLotRepository)ObjectRegistry.get("parkingLotRepository")));

        ObjectRegistry.put("parkingLotController",new ParkingLotController(
                (ParkingLotService)ObjectRegistry.get("parkingLotService")
        ));

        ObjectRegistry.put("ticketRepository",new TicketRepository());

        ObjectRegistry.put("spotAssignmentStrategy", new RandomSpotAssignmentStrategy());

        ObjectRegistry.put("ticketService", new TicketService(
                (TicketRepository) ObjectRegistry.get("ticketRepository"),
                (SpotAssignmentStrategy) ObjectRegistry.get("spotAssignmentStrategy"),
                (ParkingLotRepository) ObjectRegistry.get("parkingLotRepository")
        ));

        ObjectRegistry.put("ticketController", new TicketController((TicketService)ObjectRegistry.get("ticketService")));

        ParkingLotController parkingLotController = (ParkingLotController)ObjectRegistry.get("parkingLotController");
        CreateParkingLotRequestDto createParkingLotRequestDto = new CreateParkingLotRequestDto();
        createParkingLotRequestDto.setAddress("Chennai");
        createParkingLotRequestDto.setNumberOfFloors(12);
        CreateParkingLotResponseDto createParkingLotResponseDto = parkingLotController.createParkingLot(createParkingLotRequestDto);
        ParkingLot parkingLot;
        if(createParkingLotResponseDto.getResponseStatusDto().equals(ResponseStatusDto.FAILURE))
        {
            parkingLot=null;
            System.out.println("Something is wrong!");
        }
        else {
            parkingLot=createParkingLotResponseDto.getParkingLot();
            System.out.println(parkingLot);
        }
        UpdateParkingLotRequestDto updateParkingLotRequestDto = new UpdateParkingLotRequestDto();
        updateParkingLotRequestDto.setParkingLotId(1L);
        updateParkingLotRequestDto.setAddress("Arav's House");
        ParkingLot updatedParkingLot = parkingLotController.updateAddress(updateParkingLotRequestDto).getParkingLot();
        System.out.println("Address updated!");

        CreateParkingSpotRequestDto createParkingSpotRequestDto = new CreateParkingSpotRequestDto();
        createParkingSpotRequestDto.setParkingLotId(1L);
        createParkingSpotRequestDto.setParkingFloor(parkingLot.getParkingFloors().get(1));
        createParkingSpotRequestDto.setSpotType(SpotType.MEDIUM);

        ParkingSpot newParkingSpot = parkingLotController.createParkingSpot(createParkingSpotRequestDto).getParkingSpot();
        System.out.println("Parking Spot Created");
        TicketController ticketController = (TicketController)ObjectRegistry.get("ticketController");

        GenerateTicketRequestDto generateTicketRequestDto = new GenerateTicketRequestDto();
        generateTicketRequestDto.setParkingLotId(1L);
        generateTicketRequestDto.setSpotType(SpotType.MEDIUM);
        generateTicketRequestDto.setVehicle(new Vehicle("TN12AR1209", VehicleType.MEDIUM));
        generateTicketRequestDto.setEntryGate(new EntryGate());

        GenerateTicketResponseDto generateTicketResponseDto = ticketController.generateTicket(generateTicketRequestDto);
        if(generateTicketResponseDto.getResponseStatusDto().equals(ResponseStatusDto.SUCCESS))
        {
            System.out.println(generateTicketResponseDto.getTicket());
        }
        else {
            System.out.println("Ticket is invalid.");
        }

    }
}
