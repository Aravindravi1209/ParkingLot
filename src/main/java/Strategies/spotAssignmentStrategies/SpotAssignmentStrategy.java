package Strategies.spotAssignmentStrategies;

import models.EntryGate;
import models.ParkingLot;
import models.ParkingSpot;
import models.SpotType;

public interface SpotAssignmentStrategy {
    public ParkingSpot assignSpot(ParkingLot parkingLot, SpotType spotType, EntryGate entryGate);
}
