package xyz.carnage.itemmgmt.items;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class JuggernautItem extends SwordItem {

    public JuggernautItem(ToolMaterial toolMaterial, AttributeModifiersComponent settings) {
        super(toolMaterial, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient && attacker instanceof PlayerEntity player) {
            // Play the sound effect
            World world = attacker.getWorld();
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ENTITY_TURTLE_EGG_CRACK, SoundCategory.PLAYERS, // custom sound effect!
                    0.5f, 1.0f);
        }

        return super.postHit(stack, target, attacker);
    }


}