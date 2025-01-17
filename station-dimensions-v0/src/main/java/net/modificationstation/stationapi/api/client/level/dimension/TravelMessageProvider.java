package net.modificationstation.stationapi.api.client.level.dimension;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface TravelMessageProvider {

    String getEnteringTranslationKey();

    String getLeavingTranslationKey();
}
