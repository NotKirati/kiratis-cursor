package me.kirati.interfaces;

import me.kirati.enums.CursorStates;
import java.util.EnumSet;

public interface CursorStateInterface {
    public EnumSet<CursorStates> cursorStates = EnumSet.noneOf(CursorStates.class);

    public void addState(CursorStates state);
    public void removeState(CursorStates state);
}
