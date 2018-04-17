package me.wheezygold.skriptping.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.wheezygold.skriptping.SKU;
import me.wheezygold.skriptping.SkriptPing;
import org.bukkit.event.Event;

@Name("Server Version String")
@Description("The name of the server version.")
@Examples({"set the version string to \"SkriptCord\""})
@Since("0.1")
@SKU.data(
        name = "Server Version String",
        desc = "The name of the server version.",
        example = "set the version string to \"SkriptCord\""
)
public class ExprVersionString extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprVersionString.class, String.class, ExpressionType.SIMPLE, "[the] version (string|name)");
    }

    @Override
    protected String[] get(Event e) {
        return new String[]{SkriptPing.getInstance().getCustomVersionName()};
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.RESET)
            return new Class[]{String.class};
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        String versionName = mode == Changer.ChangeMode.SET ? (String) delta[0] : null;
        SkriptPing.getInstance().setCustomVersionName(versionName);
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "version string";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
