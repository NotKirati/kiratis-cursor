package me.kirati.mixin.client;

import me.kirati.enums.CursorStates;
import me.kirati.interfaces.CursorStateInterface;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.kirati.KiratisCursorClient.LOGGER;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin<T extends ScreenHandler> implements CursorStateInterface {

    @Shadow public abstract T getScreenHandler();
    @Shadow @Nullable protected Slot focusedSlot;

    @Override
    public void addState(CursorStates state) {
        cursorStates.add(state);
    }

    @Override
    public void removeState(CursorStates state) {
        cursorStates.remove(state);
    }

    // When a HandledGUI is rending(ie. Inventory), correctly set the cursor.
    @Inject(method = "render", at = @At("HEAD"))
    void changeCursor(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        T handler = getScreenHandler();

        // If Cursor is holding item.
        if (handler.getCursorStack().isEmpty()) {
            removeState(CursorStates.GRABBING);
        } else {
            addState(CursorStates.GRABBING);
        };

        // If cursor is hovering over item.
        if (focusedSlot != null && focusedSlot.hasStack()) {
            addState(CursorStates.HOVERING);
        } else {
            removeState(CursorStates.HOVERING);
        };
    }
}
