package me.kirati;

import com.mojang.blaze3d.systems.RenderSystem;
import me.kirati.config.ModConfig;
import me.kirati.enums.CursorStates;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import static me.kirati.KiratisCursorClient.*;
import static me.kirati.interfaces.CursorStateInterface.cursorStates;

public class CursorRenderer {

    public static void render(DrawContext context, int mouseX, int mouseY, float delta) {
        var cursor = AutoConfig.getConfigHolder(ModConfig.class).getConfig().cursor;
        if(cursor.enabled){
            var scale = client.getWindow().getScaleFactor();
            RenderSystem.depthFunc(GL11.GL_ALWAYS);
            changeCursor();
            context.drawTexture(
                    // Image to use
                    cursor.asset,
                    // Horizontal and Vertical Offset
                    (int) Math.round(mouseX-cursor.size*cursor.offsetX/scale),
                    (int) Math.round(mouseY-cursor.size*cursor.offsetY/scale),
                    (float) 0, (float) 0,
                    // Width & Height
                    (int) (cursor.size/scale), (int) (cursor.size/scale),
                    // Texture Width & Height
                    (int) (cursor.size/scale), (int) (cursor.size/scale));
            RenderSystem.depthFunc(GL11.GL_LEQUAL);

            GLFW.glfwSetInputMode(client.getWindow().getHandle(),GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
        }
        else {
            GLFW.glfwSetInputMode(client.getWindow().getHandle(),GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
        }
    }

    public static void changeCursor() {
        var cursor = AutoConfig.getConfigHolder(ModConfig.class).getConfig().cursor;
        if (cursorStates.contains(CursorStates.GRABBING)) {
            cursor.offsetX = 0.35f;
            cursor.asset = Identifier.of(MOD_ID, "cursor_grab.png");
        } else if (cursorStates.contains(CursorStates.HOVERING)) {
            cursor.offsetX = 0.35f;
            cursor.asset = Identifier.of(MOD_ID, "cursor_hover.png");
        } else {
            cursor.offsetX = 0;
            cursor.asset = Identifier.of(MOD_ID, "cursor.png");
        };
    }
}
