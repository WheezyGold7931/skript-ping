package me.wheezygold.skriptping.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.wheezygold.skriptping.SkriptPing;
import org.bukkit.event.Event;

public class ExprMOTD extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprMOTD.class, String.class, ExpressionType.SIMPLE, "[the] server [list] motd");
    }

    @Override
    protected String[] get(Event e) {
        return new String[]{SkriptPing.getInstance().getCustomMotd()};
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.RESET)
            return new Class[]{String.class};
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            if (delta[0] == null) {
                Skript.error("MOTD string must be a valid string!");
                return;
            }
            SkriptPing.getInstance().setCustomMotd((String) delta[0]);
        } else {
            SkriptPing.getInstance().setCustomMotd(null);
        }
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
        return "server list motd";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
