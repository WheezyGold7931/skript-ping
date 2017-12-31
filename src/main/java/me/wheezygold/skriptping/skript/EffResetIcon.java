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

@Name("Reset Server Icon")
@Description("Reset's the server's icon. (This does not delete your default icon, this just stops skript-ping from touching it.")
@Examples({"reset the server-icon"})
@Since("0.1")
@SKU.data(
        name = "Reset Server Icon",
        desc = "Reset's the server's icon. (This does not delete your default icon, this just stops skript-ping from touching it.",
        example = "reset the server-icon"
)
public class EffResetIcon extends Effect {

    static {
        Skript.registerEffect(EffResetIcon.class, "reset [the] server(-| )icon");
    }

    @Override
    protected void execute(Event e) {
        SkriptPing.getInstance().resetIcon();
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
