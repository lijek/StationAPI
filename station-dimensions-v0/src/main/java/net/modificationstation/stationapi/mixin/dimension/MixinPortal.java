package net.modificationstation.stationapi.mixin.dimension;

import net.minecraft.block.Portal;
import net.minecraft.entity.EntityBase;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.entity.HasTeleportationManager;
import net.modificationstation.stationapi.api.level.dimension.TeleportationManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Portal.class)
public class MixinPortal implements TeleportationManager {

    @Inject(
            method = "onEntityCollision(Lnet/minecraft/level/Level;IIILnet/minecraft/entity/EntityBase;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/EntityBase;method_1388()V"
            )
    )
    private void onEntityCollision(Level level, int x, int y, int z, EntityBase entityBase, CallbackInfo ci) {
        if (entityBase instanceof HasTeleportationManager manager)
            manager.setTeleportationManager(this);
    }
}
