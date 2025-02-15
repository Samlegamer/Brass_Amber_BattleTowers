package com.BrassAmber.ba_bt.block.tileentity.client;

import com.BrassAmber.ba_bt.BrassAmberBattleTowers;

import net.minecraft.client.renderer.Atlases;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = BrassAmberBattleTowers.MOD_ID, bus = Bus.MOD)
public class BTChestTextures {
	public static final ResourceLocation[] GOLEM_CHEST_TEXTURES = locateChestTextures("golem");
	public static final ResourceLocation[] STONE_CHEST_TEXTURES = locateChestTextures("stone");

	/**
	 * We need to stitch the textures before we can use them. Otherwise they'll just appear as missing textures.
	 */
	@SubscribeEvent
	public static void textureStitch(TextureStitchEvent.Pre event) {
		if (event.getMap().location().equals(Atlases.CHEST_SHEET)) {
			stitchAll(event, GOLEM_CHEST_TEXTURES);
			stitchAll(event, STONE_CHEST_TEXTURES);
		}
	}

	/**
	 * Stitch all textures in the array
	 */
	private static void stitchAll(TextureStitchEvent.Pre event, ResourceLocation[] textureLocations) {
		for (ResourceLocation chestTexture : textureLocations) {
			event.addSprite(chestTexture);
		}
	}

	/**
	 * Helper method for new chest textures
	 */
	private static ResourceLocation[] locateChestTextures(String chestName) {
		return new ResourceLocation[] {
			BrassAmberBattleTowers.locate("entity/chest/" + chestName + "_chest/" + chestName),
			BrassAmberBattleTowers.locate("entity/chest/" + chestName + "_chest/" + chestName + "_left"),
			BrassAmberBattleTowers.locate("entity/chest/" + chestName + "_chest/" + chestName + "_right")
		};
	}
}
