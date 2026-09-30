package com.thenathe.toolpouchcompat.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.fabricmc.fabric.api.networking.v1.context.PacketContext;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Map;

/** Keep MapStitch's native-entry restoration, but let this shim compact the combined map once. */
@Pseudo
@Mixin(targets = "com.thenathe.mapstitchcompat.WireRegistries", remap = false)
public abstract class MapstitchWireBridgeMixin {
    @Inject(method = "prepare", at = @At("HEAD"), cancellable = true)
    private static void toolpouchcompat$singleWireMap(
            Map<Identifier, Object2IntMap<Identifier>> original, PacketContext context,
            CallbackInfoReturnable<Map<Identifier, Object2IntMap<Identifier>>> cir) {
        // Its private wire context remains unset. Its pre-existing ID/tag hooks therefore
        // become identity operations while the Tool Pouch shim handles all four registries.
        cir.setReturnValue(original);
    }
}
