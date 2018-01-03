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

@Name("Set Server Version String")
@Description("Set's the version string. This displays on the ping bar of the user.")
@Examples({"set the version string to \"SkriptCord\""})
@Since("0.1")
@SKU.data(
        name = "Set Server Version String",
        desc = "Set's the version string. This displays on the ping bar of the user.",
        example = "set the version string to \"SkriptCord\""
)
public class EffSetVersion extends Effect {

    static {
        Skript.registerEffect(EffSetVersion.class, "set [the] version string to %string%");
    }

    private Expression<String> target;

    @Override
    protected void execute(Event e) {
        SkriptPing.getInstance().setCustomVersionName(target.getSingle(e));
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
