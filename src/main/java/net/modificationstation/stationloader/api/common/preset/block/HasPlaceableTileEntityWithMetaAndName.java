package net.modificationstation.stationloader.api.common.preset.block;

import net.minecraft.item.PlaceableTileEntity;
import net.modificationstation.stationloader.api.common.block.BlockItemProvider;
import net.modificationstation.stationloader.api.common.factory.GeneralFactory;
import net.modificationstation.stationloader.api.common.preset.item.PlaceableTileEntityWithMetaAndName;

public interface HasPlaceableTileEntityWithMetaAndName extends BlockItemProvider {

    @Override
    default PlaceableTileEntity getBlockItem(int shiftedID) {
        return (PlaceableTileEntity) GeneralFactory.INSTANCE.newInst(PlaceableTileEntityWithMetaAndName.class, shiftedID);
    }
}