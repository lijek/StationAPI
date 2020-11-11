package net.modificationstation.stationloader.impl.server.gui;

import net.minecraft.class_633;
import net.minecraft.container.ContainerBase;
import net.minecraft.entity.player.PlayerBase;
import net.modificationstation.stationloader.api.common.packet.CustomData;
import net.modificationstation.stationloader.mixin.server.accessor.ServerPlayerAccessor;

public class GuiHelper extends net.modificationstation.stationloader.impl.common.gui.GuiHelper {

    @Override
    protected void sideDependentPacket(PlayerBase player, CustomData packet) {
        packet.objects()[0] = null;
        ((ServerPlayerAccessor) player).invokeMethod_314();
        packet.set(new int[] {
                ((ServerPlayerAccessor) player).getField_260()
        });
    }

    @Override
    protected void afterPacketSent(PlayerBase player, ContainerBase container) {
        player.container = container;
        container.currentContainerId = ((ServerPlayerAccessor) player).getField_260();
        container.method_2076((class_633) player);
    }
}