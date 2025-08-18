package com.min01.oceanicrealms.event;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.block.OceanicBlocks;
import com.min01.oceanicrealms.block.model.ModelClam;
import com.min01.oceanicrealms.block.model.ModelSeaAnemone;
import com.min01.oceanicrealms.block.model.ModelStarfish;
import com.min01.oceanicrealms.blockentity.renderer.AnimatableBlockRenderer;
import com.min01.oceanicrealms.blockentity.renderer.NoRotationLimitRenderer;
import com.min01.oceanicrealms.entity.OceanicEntities;
import com.min01.oceanicrealms.entity.model.ModelCrab;
import com.min01.oceanicrealms.entity.model.ModelDolphinfish;
import com.min01.oceanicrealms.entity.model.ModelGreatHammerheadShark;
import com.min01.oceanicrealms.entity.model.ModelGreatWhiteShark;
import com.min01.oceanicrealms.entity.model.ModelHammerheadShark;
import com.min01.oceanicrealms.entity.model.ModelLionfish;
import com.min01.oceanicrealms.entity.model.ModelMackerelFish;
import com.min01.oceanicrealms.entity.model.ModelOarfishBody;
import com.min01.oceanicrealms.entity.model.ModelOarfishHead;
import com.min01.oceanicrealms.entity.model.ModelOarfishTail;
import com.min01.oceanicrealms.entity.model.ModelPorbeagleShark;
import com.min01.oceanicrealms.entity.model.ModelSailfish;
import com.min01.oceanicrealms.entity.model.ModelSilverPomfretFish;
import com.min01.oceanicrealms.entity.model.ModelTuna;
import com.min01.oceanicrealms.entity.model.ModelWhaleshark;
import com.min01.oceanicrealms.entity.renderer.CrabRenderer;
import com.min01.oceanicrealms.entity.renderer.DolphinfishRenderer;
import com.min01.oceanicrealms.entity.renderer.GreatHammerheadSharkRenderer;
import com.min01.oceanicrealms.entity.renderer.GreatWhiteSharkRenderer;
import com.min01.oceanicrealms.entity.renderer.HammerheadSharkRenderer;
import com.min01.oceanicrealms.entity.renderer.LionfishRenderer;
import com.min01.oceanicrealms.entity.renderer.MackerelFishRenderer;
import com.min01.oceanicrealms.entity.renderer.OarfishBodyRenderer;
import com.min01.oceanicrealms.entity.renderer.OarfishHeadRenderer;
import com.min01.oceanicrealms.entity.renderer.OarfishTailRenderer;
import com.min01.oceanicrealms.entity.renderer.PorbeagleSharkRenderer;
import com.min01.oceanicrealms.entity.renderer.SailfishRenderer;
import com.min01.oceanicrealms.entity.renderer.SilverPomfretFishRenderer;
import com.min01.oceanicrealms.entity.renderer.TunaRenderer;
import com.min01.oceanicrealms.entity.renderer.WhalesharkRenderer;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = OceanicRealms.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler 
{
	@SubscribeEvent
	public static void onFMLClientSetup(FMLClientSetupEvent event)
	{
        BlockEntityRenderers.register(OceanicBlocks.NO_ROTATION_LIMIT_BLOCK_ENTITY.get(), NoRotationLimitRenderer::new);
        BlockEntityRenderers.register(OceanicBlocks.ANIMATABLE_BLOCK_ENTITY.get(), AnimatableBlockRenderer::new);
		/*try
		{
		    OceanicUtil.encryptFiles("leiojgwiojg80", "12h92h0twhg9o", ".png");
		}
		catch (Exception e) 
		{
		    e.printStackTrace();
		}*/
	}
	
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
    	event.registerLayerDefinition(ModelGreatWhiteShark.LAYER_LOCATION, ModelGreatWhiteShark::createBodyLayer);
    	event.registerLayerDefinition(ModelCrab.LAYER_LOCATION, ModelCrab::createBodyLayer);
    	event.registerLayerDefinition(ModelPorbeagleShark.LAYER_LOCATION, ModelPorbeagleShark::createBodyLayer);
    	event.registerLayerDefinition(ModelTuna.LAYER_LOCATION, ModelTuna::createBodyLayer);
    	event.registerLayerDefinition(ModelDolphinfish.LAYER_LOCATION, ModelDolphinfish::createBodyLayer);
    	event.registerLayerDefinition(ModelHammerheadShark.LAYER_LOCATION, ModelHammerheadShark::createBodyLayer);
    	event.registerLayerDefinition(ModelClam.LAYER_LOCATION, ModelClam::createBodyLayer);
    	event.registerLayerDefinition(ModelMackerelFish.LAYER_LOCATION, ModelMackerelFish::createBodyLayer);
    	event.registerLayerDefinition(ModelSilverPomfretFish.LAYER_LOCATION, ModelSilverPomfretFish::createBodyLayer);
    	event.registerLayerDefinition(ModelSeaAnemone.LAYER_LOCATION, ModelSeaAnemone::createBodyLayer);
    	event.registerLayerDefinition(ModelStarfish.LAYER_LOCATION, ModelStarfish::createBodyLayer);
    	event.registerLayerDefinition(ModelLionfish.LAYER_LOCATION, ModelLionfish::createBodyLayer);
    	event.registerLayerDefinition(ModelWhaleshark.LAYER_LOCATION, ModelWhaleshark::createBodyLayer);
    	event.registerLayerDefinition(ModelSailfish.LAYER_LOCATION, ModelSailfish::createBodyLayer);
    	event.registerLayerDefinition(ModelGreatHammerheadShark.LAYER_LOCATION, ModelGreatHammerheadShark::createBodyLayer);
    	event.registerLayerDefinition(ModelOarfishHead.LAYER_LOCATION, ModelOarfishHead::createBodyLayer);
    	event.registerLayerDefinition(ModelOarfishBody.LAYER_LOCATION, ModelOarfishBody::createBodyLayer);
    	event.registerLayerDefinition(ModelOarfishTail.LAYER_LOCATION, ModelOarfishTail::createBodyLayer);
    }
    
    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
    	event.registerEntityRenderer(OceanicEntities.GREAT_WHITE_SHARK.get(), GreatWhiteSharkRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.CRAB.get(), CrabRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.PORBEAGLE_SHARK.get(), PorbeagleSharkRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.TUNA.get(), TunaRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.DOLPHINFISH.get(), DolphinfishRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.HAMMERHEAD_SHARK.get(), HammerheadSharkRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.MACKEREL_FISH.get(), MackerelFishRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.SILVER_POMFRET_FISH.get(), SilverPomfretFishRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.LIONFISH.get(), LionfishRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.WHALESHARK.get(), WhalesharkRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.SAILFISH.get(), SailfishRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.GREAT_HAMMERHEAD_SHARK.get(), GreatHammerheadSharkRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.OARFISH_HEAD.get(), OarfishHeadRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.OARFISH_BODY.get(), OarfishBodyRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.OARFISH_TAIL.get(), OarfishTailRenderer::new);
    }
}
