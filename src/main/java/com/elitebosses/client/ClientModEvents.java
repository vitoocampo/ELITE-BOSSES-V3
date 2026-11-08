package com.elitebosses.client;

import com.elitebosses.EliteBosses;
import com.elitebosses.registry.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EliteBosses.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {
    private ClientModEvents() {
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.CHOCOLATE.get(), ChocolateRenderer::new);
        event.registerEntityRenderer(ModEntities.HOUNDMASTER.get(), HoundmasterRenderer::new);
        event.registerEntityRenderer(ModEntities.STALKER.get(), StalkerRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HoundmasterModel.LAYER, HoundmasterModel::createBodyLayer);
        event.registerLayerDefinition(StalkerModel.LAYER, StalkerModel::createBodyLayer);
    }
}
