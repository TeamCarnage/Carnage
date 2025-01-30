package xyz.carnage.manager.item;

import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public class CoolDownManager {
    public static void setItemCooldown(PlayerEntity player, Item item, int duration) {
        player.getItemCooldownManager().set(item, duration);
    }
}
