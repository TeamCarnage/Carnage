package xyz.carnage.itemmgmt.items;


/* import net.minecraft.item.TridentItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class BrinebreakerItem extends TridentItem {
    public BrinebreakerItem(ToolMaterial material, Item.Settings settings) {
        super(settings);


        // code the item lol


    }
}*/

import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;
import xyz.carnage.itemmgmt.ModTridentItem;

public class BrinebreakerItem extends ModTridentItem {
    private final ToolMaterial material;

    public BrinebreakerItem(ToolMaterial material, AttributeModifiersComponent attributeModifiers) {
        super(
                new Item.Settings()
        );
        this.material = material;
    }
}


