import Strategies.spotAssignmentStrategies.RandomSpotAssignmentStrategy;
import Strategies.spotAssignmentStrategies.SpotAssignmentStrategy;
import controllers.ParkingLotController;
import controllers.TicketController;
import dtos.*;
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
        if(createParkingLotResponseDto.getResponseStatusDto().equals(ResponseStatusDto.FAILURE))
        {
            System.out.println("Something is wrong!");
        }
        else {
            System.out.println(createParkingLotResponseDto.getParkingLot());
        }
        UpdateParkingLotRequestDto updateParkingLotRequestDto = new UpdateParkingLotRequestDto();
        updateParkingLotRequestDto.setParkingLotId(1L);
        updateParkingLotRequestDto.setAddress("Arav's House");
        System.out.println(parkingLotController.updateAddress(updateParkingLotRequestDto));
    }
}
