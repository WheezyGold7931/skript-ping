package me.wheezygold.skriptping.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.wheezygold.skriptping.SKU;
import me.wheezygold.skriptping.SkriptPing;
import org.bukkit.event.Event;

@Name("Reset Player Hover List")
@Description("Resets the player count hover message.")
@Examples({"reset the server hover list"})
@Since("0.1")
@SKU.data(
        name = "Reset Player Hover List",
        desc = "Resets the player count hover message.",
        example = "reset the server hover list"
)
public class EffResetHoverLines extends Effect {

    static {
        Skript.registerEffect(EffResetHoverLines.class, "reset [the] (player|server) hover [list]");
    }

    @Override
    protected void execute(Event e) {
        SkriptPing.getInstance().getHoverListStrings().clear();
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
