package xyz.carnage.manager.entity.entityCombos;

import xyz.carnage.Carnage;
import xyz.carnage.manager.entity.brinebreaker.BrinebreakerEntity;

public class BrinebreakerEntityCombos {

    public static void checkShields(BrinebreakerEntity entity) {
        int shields = entity.getBrineBreakerShields();
        if (shields == 1) {
            Carnage.LOGGER.info("Brinebreaker Shields activated");
        }
    }


    public static void initialise() {
        Carnage.LOGGER.info("Brinebreaker Combo effect Initialized");
    }
}