package net.modificationstation.stationapi.api.client.event.model;

import lombok.RequiredArgsConstructor;
import net.modificationstation.stationapi.api.client.model.BlockModelProvider;
import net.modificationstation.stationapi.api.common.event.Event;
import net.modificationstation.stationapi.api.common.event.GameEventOld;

import java.util.function.Consumer;

// TODO: Item and Entity model documentation.

/**
 * Used to set a custom model for your block.
 * Implement {@link BlockModelProvider} in your block class to use a custom model.
 *
 * @author mine_diver
 * @see BlockModelProvider
 */
@RequiredArgsConstructor
public class ModelRegister extends Event {

    public final Type type;

    public enum Type {
        BLOCKS,
        ITEMS,
        ENTITIES
    }
}
