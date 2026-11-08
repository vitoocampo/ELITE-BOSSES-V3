package com.elitebosses.client;

import com.elitebosses.EliteBosses;
import com.elitebosses.entity.StalkerEntity;
import com.elitebosses.registry.ModEffects;
import com.elitebosses.registry.ModSounds;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Secuencia de vision de la Obsesion (ultimos 30 s): pantalla negra 1 s y despues parpadeos
 * entre negro / vista normal / vision de la Krasue (imagen translucida) con sonidos de carne.
 * Se calcula con el tiempo restante del efecto, que el servidor ya sincroniza.
 */
@Mod.EventBusSubscriber(modid = EliteBosses.MODID, value = Dist.CLIENT)
public final class ObsessionClient {
    private static final ResourceLocation VISION =
            new ResourceLocation(EliteBosses.MODID, "textures/gui/krasue_vision.png");
    private static final int VISION_TICKS = 600;   // ultimos 30 s
    private static final int BLACK_TICKS = 20;     // 1 s negro al inicio

    private ObsessionClient() {
    }

    /** Ticks transcurridos de la secuencia, o -1 si no esta activa. */
    private static int sequenceTick() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            return -1;
        }
        MobEffectInstance inst = mc.player.getEffect(ModEffects.OBSESSION.get());
        if (inst == null || inst.isInfiniteDuration()) {
            return -1;
        }
        int remaining = inst.getDuration();
        if (remaining > VISION_TICKS) {
            return -1;
        }
        return VISION_TICKS - remaining;
    }

    /** 0 = negro, 1 = vista normal, 2 = vision. */
    private static int flickerState(int tick) {
        if (tick < BLACK_TICKS) {
            return 0;
        }
        int slot = (tick - BLACK_TICKS) / 3;
        int h = slot * 0x9E3779B1;
        h ^= (h >>> 15);
        h *= 0x2C1B3C6D;
        h ^= (h >>> 12);
        int r = Math.floorMod(h, 10);
        // el parpadeo se vuelve mas violento hacia el final
        int progress = (tick - BLACK_TICKS) * 10 / (VISION_TICKS - BLACK_TICKS);
        if (r < 3 + progress / 3) {
            return 0;
        }
        return r < 6 + progress / 3 ? 2 : 1;
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        int t = sequenceTick();
        Minecraft mc = Minecraft.getInstance();
        if (t < 0 || mc.player == null) {
            return;
        }
        if (t == 0 || (t > BLACK_TICKS && t % 40 == 0)) {
            float pitch = 0.75F + mc.player.getRandom().nextFloat() * 0.5F;
            mc.getSoundManager().play(SimpleSoundInstance.forUI(ModSounds.FLESH_FORM.get(), pitch, 1.0F));
        }
    }

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        int t = sequenceTick();
        if (t < 0) {
            return;
        }
        GuiGraphics gg = event.getGuiGraphics();
        int w = gg.guiWidth(), h = gg.guiHeight();
        int state = flickerState(t);
        if (state == 0) {
            gg.fill(0, 0, w, h, 0xFF000000);
        } else if (state == 2) {
            gg.fill(0, 0, w, h, 0xCC000000); // como la ceguera
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.55F);
            int iw = h * 512 / 768;
            gg.blit(VISION, (w - iw) / 2, 0, iw, h, 0.0F, 0.0F, 512, 768, 512, 768);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableBlend();
        }
    }
}
