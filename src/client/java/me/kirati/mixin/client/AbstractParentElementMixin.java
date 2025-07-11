package me.kirati.mixin.client;

import me.kirati.enums.CursorStates;
import me.kirati.interfaces.CursorStateInterface;
import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.gui.Element;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractParentElement.class)
public class AbstractParentElementMixin implements CursorStateInterface {

    // Reset cursor when changing screens.
    @Inject(method = "setFocused", at = @At("HEAD"))
    void changeCursor(Element focused, CallbackInfo ci) {
        removeState(CursorStates.HOVERING);
        removeState(CursorStates.GRABBING);
    }

    @Override
    public void addState(CursorStates state) {
        cursorStates.add(state);
    }

    @Override
    public void removeState(CursorStates state) {
        cursorStates.remove(state);
    }
}
