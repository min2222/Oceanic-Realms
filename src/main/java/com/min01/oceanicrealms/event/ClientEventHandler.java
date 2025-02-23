package com.min01.oceanicrealms.event;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.OceanicEntities;
import com.min01.oceanicrealms.entity.model.ModelBullShark;
import com.min01.oceanicrealms.entity.model.ModelCrab;
import com.min01.oceanicrealms.entity.model.ModelDolphinfish;
import com.min01.oceanicrealms.entity.model.ModelGreatWhiteShark;
import com.min01.oceanicrealms.entity.model.ModelHammerheadShark;
import com.min01.oceanicrealms.entity.model.ModelTuna;
import com.min01.oceanicrealms.entity.renderer.BullSharkRenderer;
import com.min01.oceanicrealms.entity.renderer.CrabRenderer;
import com.min01.oceanicrealms.entity.renderer.DolphinfishRenderer;
import com.min01.oceanicrealms.entity.renderer.GreatWhiteSharkRenderer;
import com.min01.oceanicrealms.entity.renderer.HammerheadSharkRenderer;
import com.min01.oceanicrealms.entity.renderer.TunaRenderer;

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
		/*try
		{
		    AESUtil.encryptFiles(".png");
		}
		catch (InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | NoSuchPaddingException | InvalidKeySpecException | IOException e) 
		{
		    e.printStackTrace();
		}*/
	}
	
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
    	event.registerLayerDefinition(ModelGreatWhiteShark.LAYER_LOCATION, ModelGreatWhiteShark::createBodyLayer);
    	event.registerLayerDefinition(ModelCrab.LAYER_LOCATION, ModelCrab::createBodyLayer);
    	event.registerLayerDefinition(ModelBullShark.LAYER_LOCATION, ModelBullShark::createBodyLayer);
    	event.registerLayerDefinition(ModelTuna.LAYER_LOCATION, ModelTuna::createBodyLayer);
    	event.registerLayerDefinition(ModelDolphinfish.LAYER_LOCATION, ModelDolphinfish::createBodyLayer);
    	event.registerLayerDefinition(ModelHammerheadShark.LAYER_LOCATION, ModelHammerheadShark::createBodyLayer);
    }
    
    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
    	event.registerEntityRenderer(OceanicEntities.GREAT_WHITE_SHARK.get(), GreatWhiteSharkRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.CRAB.get(), CrabRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.BULL_SHARK.get(), BullSharkRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.TUNA.get(), TunaRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.DOLPHINFISH.get(), DolphinfishRenderer::new);
    	event.registerEntityRenderer(OceanicEntities.HAMMERHEAD_SHARK.get(), HammerheadSharkRenderer::new);
    }
}
