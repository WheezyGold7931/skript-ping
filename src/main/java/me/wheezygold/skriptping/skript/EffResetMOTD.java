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
        name = "Reset Server MOTD",
        desc = "Reset's the server's MOTD",
        example = "reset the server list motd"
)
public class EffResetMOTD extends Effect {

    static {
        Skript.registerEffect(EffResetMOTD.class, "reset [the] server [list] motd");
    }

    @Override
    protected void execute(Event e) {
        SkriptPing.getInstance().setCustomMotd("");
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
