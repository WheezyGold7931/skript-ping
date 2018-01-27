package me.wheezygold.skriptping.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.wheezygold.skriptping.SkriptPing;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;

public class ExprOnlinePlayers extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprOnlinePlayers.class, Number.class, ExpressionType.SIMPLE, "best players");
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    protected Number[] get(Event e) {
        return new Number[]{Bukkit.getOnlinePlayers().size()};
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.RESET)
            return new Class[]{Number.class};
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET) {
            SkriptPing.getInstance().log(String.valueOf(((Number) delta[0]).intValue()));
        } else {
            //TODO Add RESET changer
        }
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "best players";
    }

}
