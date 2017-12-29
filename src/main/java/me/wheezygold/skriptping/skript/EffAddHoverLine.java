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
        name = "Add Server Hover Line",
        desc = "Adds a line to the player count hover message in the multi-player menu.",
        example = "add \"meme\" to the player hover list"
)
public class EffAddHoverLine extends Effect {

    static {
        Skript.registerEffect(EffAddHoverLine.class, "add %string% to [the] (player|server) hover [list]");
    }

    private Expression<String> entry;

    @Override
    protected void execute(Event e) {
        SkriptPing.getInstance().getHoverListStrings().add(entry.getSingle(e));
    }

    @Override
    public String toString(Event e, boolean debug) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        entry = (Expression<String>) exprs[0];
        return true;
    }
}
