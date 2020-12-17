package net.modificationstation.stationloader.api.client.event.keyboard;

import net.minecraft.client.options.KeyBinding;
import net.modificationstation.stationloader.api.client.event.option.KeyBindingRegister;
import net.modificationstation.stationloader.api.common.event.GameEvent;
import org.lwjgl.input.Keyboard;

import java.util.function.Consumer;

/**
 * Used to handle keypresses.
 * Implement this in the class you plan to use to handle your keypresses.
 * All events need to be registered in your mod's preInit method using KeyPressed.EVENT.register(yourInstantiatedClass).
 * <p>
 * You want to check for your key with {@link Keyboard#getEventKey()} against {@link KeyBinding#key myKeybind.key} before executing any related code.
 *
 * @author mine_diver
 * @see KeyBindingRegister
 */
public interface KeyPressed {

    GameEvent<KeyPressed> EVENT = new GameEvent<>(KeyPressed.class,
            (listeners) ->
                    () -> {
                        for (KeyPressed listener : listeners)
                            listener.keyPressed();
                    },
            (Consumer<GameEvent<KeyPressed>>) keyPressed ->
                    keyPressed.register(() -> GameEvent.EVENT_BUS.post(new Data()))
    );

    void keyPressed();

    final class Data extends GameEvent.Data<KeyPressed> {

        private Data() {
            super(EVENT);
        }
    }
}
