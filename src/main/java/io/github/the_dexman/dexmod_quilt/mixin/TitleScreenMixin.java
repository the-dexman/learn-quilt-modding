package io.github.the_dexman.dexmod_quilt.mixin;

import io.github.the_dexman.dexmod_quilt.DexmodQuilt;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
	@Inject(method = "init", at = @At("TAIL"))
	public void onInit(CallbackInfo ci) {
		DexmodQuilt.LOGGER.info("This line is printed by an example mod mixin!");
	}
}
