package me.wheezygold.skriptping.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.wheezygold.skriptping.SKU;
import me.wheezygold.skriptping.SkriptPing;
import org.bukkit.event.Event;

@SKU.data(
        name = "Reset Server Version String",
        desc = "Reset's the version string.",
        example = "reset the version string"
)
public class EffResetVersion extends Effect {

    static {
        Skript.registerEffect(EffResetVersion.class, "reset [the] version string");
    }

    @Override
    protected void execute(Event e) {
        SkriptPing.getInstance().setCustomVersionName("");
    }

    @Override
    public String toString(Event e, boolean debug) {
        return null;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
