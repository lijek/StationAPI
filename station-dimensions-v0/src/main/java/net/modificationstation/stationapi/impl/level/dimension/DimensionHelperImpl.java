package net.modificationstation.stationapi.impl.level.dimension;

import net.minecraft.class_467;
import net.minecraft.entity.player.PlayerBase;
import net.modificationstation.stationapi.api.registry.Identifier;

public abstract class DimensionHelperImpl {

    public abstract void switchDimension(PlayerBase player, Identifier destination, double scale, class_467 travelAgent);
}
