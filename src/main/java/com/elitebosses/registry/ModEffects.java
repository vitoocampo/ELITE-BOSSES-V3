package com.elitebosses.registry;

import com.elitebosses.EliteBosses;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EliteBosses.MODID);

    /** Obsesion: marca al jugador elegido por el Stalker (5 minutos). */
    public static final RegistryObject<MobEffect> OBSESSION = EFFECTS.register("obsession",
            () -> new SimpleEffect(MobEffectCategory.HARMFUL, 0x7A1018));

    /** Salmuera: se acumula (cada carga sube el nivel). Solo la leche, la muerte o la muerte de la Krasue la quitan. */
    public static final RegistryObject<MobEffect> BRINE = EFFECTS.register("brine",
            () -> new SimpleEffect(MobEffectCategory.HARMFUL, 0x3FB8A0));

    private ModEffects() {
    }

    private static final class SimpleEffect extends MobEffect {
        SimpleEffect(MobEffectCategory category, int color) {
            super(category, color);
        }
    }
}
