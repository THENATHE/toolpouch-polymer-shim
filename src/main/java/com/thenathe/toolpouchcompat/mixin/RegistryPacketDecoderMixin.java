package com.thenathe.toolpouchcompat.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.thenathe.toolpouchcompat.WireRegistries;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.PacketDecoder;
import org.spongepowered.asm.mixin.Mixin;
import java.util.List;

@Mixin(PacketDecoder.class)
public abstract class RegistryPacketDecoderMixin {
    @WrapMethod(method = "decode")
    private void toolpouchcompat$decode(ChannelHandlerContext channel, ByteBuf input, List<Object> output, Operation<Void> original) {
        WireRegistries.decode(() -> original.call(channel, input, output));
    }
}
