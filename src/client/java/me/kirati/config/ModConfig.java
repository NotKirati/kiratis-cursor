package me.kirati.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.util.Identifier;

import static me.kirati.KiratisCursorClient.MOD_ID;

@Config(name = "kiratis-cursor")
public class ModConfig implements ConfigData {
//    boolean toggleA = true;
//    boolean toggleB = false;
//
//    @ConfigEntry.Gui.CollapsibleObject
//    InnerStuff stuff = new InnerStuff();
//
//    @ConfigEntry.Gui.Excluded
//    InnerStuff invisibleStuff = new InnerStuff();
//
//    static class InnerStuff {
//        int a = 0;
//        int b = 1;
//    }
    @ConfigEntry.Gui.TransitiveObject
    public Cursor cursor = new Cursor();

    public static class Cursor {
        public boolean enabled = true;
        public int size = 30;

        // Excluded fields
        @ConfigEntry.Gui.Excluded
        public float offsetX = 0;
        @ConfigEntry.Gui.Excluded
        public float offsetY = 0;
        @ConfigEntry.Gui.Excluded
        public Identifier asset = DEFAULT_CURSOR;
        @ConfigEntry.Gui.Excluded
        public final static Identifier DEFAULT_CURSOR = Identifier.of(MOD_ID,"cursor.png");
    }
}
