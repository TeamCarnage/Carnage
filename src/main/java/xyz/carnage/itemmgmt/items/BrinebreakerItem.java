package xyz.carnage.itemmgmt.items;



/* import net.minecraft.item.TridentItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class BrinebreakerItem extends TridentItem {
    public BrinebreakerItem(ToolMaterial material, Item.Settings settings) {
        super(settings);


        // code the item lol
        // /\ on it lol - colonel xoxo


    }
}*/


import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import xyz.carnage.entity.BrineBreakerEntity;
import xyz.carnage.itemmgmt.ModToolMaterials;

// Create a new class for your custom trident
public class BrinebreakerItem extends TridentItem {

    public BrinebreakerItem(ModToolMaterials modToolMaterials, AttributeModifiersComponent attributeModifiers) {
        super(new Item.Settings());
    }

    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getDamage() >= itemStack.getMaxDamage() - 1) {
            return TypedActionResult.fail(itemStack);
        }
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            int i = this.getMaxUseTime(stack) - remainingUseTicks;
            if (i >= 10) {
                if (!world.isClient) {
                    // Create vanilla trident for reference
                    TridentEntity vanillaTrident = new TridentEntity(world, playerEntity, stack);

                    // Create our custom trident
                    BrineBreakerEntity tridentEntity = new BrineBreakerEntity(world, playerEntity, stack.copy());
                    tridentEntity.setPosition(
                            playerEntity.getX(),
                            playerEntity.getEyeY() - 0.1,
                            playerEntity.getZ()
                    );

                    // Copy vanilla trident properties
                    tridentEntity.setVelocity(
                            playerEntity,
                            playerEntity.getPitch(),
                            playerEntity.getYaw(),
                            0.0F,
                            2.5F,
                            1.0F
                    );

                    world.spawnEntity(tridentEntity);
                    world.playSoundFromEntity(
                            null,
                            tridentEntity,
                            SoundEvents.ITEM_TRIDENT_THROW.value(),
                            SoundCategory.PLAYERS,
                            1.0F,
                            1.0F
                    );
                }
            }
        }
    }
}