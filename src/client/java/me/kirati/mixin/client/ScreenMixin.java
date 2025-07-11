package me.kirati.mixin.client;

import me.kirati.CursorRenderer;
import me.kirati.enums.CursorStates;
import me.kirati.interfaces.CursorStateInterface;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public abstract class ScreenMixin implements CursorStateInterface {

    // Render the cursor when visible.
    @Inject(method = "renderWithTooltip", at = @At("TAIL"))
    void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        CursorRenderer.render(context, mouseX, mouseY, delta);
    }

    // Reset cursor once when closing current screen.
    @Inject(method = "close", at = @At("HEAD"))
    void changeCursor(CallbackInfo ci) {
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
