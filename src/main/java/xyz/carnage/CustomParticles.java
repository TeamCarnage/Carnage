package xyz.carnage;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.BillboardParticle;
import net.minecraft.client.particle.CampfireSmokeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class CustomParticles {
    public static final SimpleParticleType HELLFIRE = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(
                Registries.PARTICLE_TYPE,
                Identifier.of("carnage", "hellfire"),
                HELLFIRE
                // For this example, we will use the end rod particle behaviour.

        );
        ParticleFactoryRegistry.getInstance().register(CustomParticles.HELLFIRE, CampfireSmokeParticle.CosySmokeFactory::new);

    }
}