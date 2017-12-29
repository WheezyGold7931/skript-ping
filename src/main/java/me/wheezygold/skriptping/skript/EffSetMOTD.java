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
        name = "Set Server MOTD",
        desc = "Set's the server's MOTD",
        example = "update the server list motd to \"oh hai\""
)
public class EffSetMOTD extends Effect {

    static {
        Skript.registerEffect(EffSetMOTD.class, "update [the] server [list] motd to %string%");
    }

    private Expression<String> target;

    @Override
    protected void execute(Event e) {
        SkriptPing.getInstance().setCustomMotd(target.getSingle(e));
    }

    @Override
    public String toString(Event e, boolean debug) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        target = (Expression<String>) exprs[0];
        return true;
    }
}
