package com.thenathe.toolpouchcompat.mixin;

import net.minecraft.server.network.ServerConfigurationPacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Both shims need the same Fabric channel query; only the Tool Pouch shim schedules it. */
@Pseudo
@Mixin(targets = "com.thenathe.mapstitchcompat.ClientCapabilities", remap = false)
public abstract class MapstitchSchedulingBridgeMixin {
    @Inject(method = "beforeRegistrySync", at = @At("HEAD"), cancellable = true)
    private static void toolpouchcompat$singleChannelQuery(
            ServerConfigurationPacketListenerImpl listener, Runnable configure, CallbackInfo ci) {
        configure.run();
        ci.cancel();
    }
}
