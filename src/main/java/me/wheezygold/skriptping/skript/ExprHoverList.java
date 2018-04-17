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

public class ExprHoverList extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprHoverList.class, String.class, ExpressionType.SIMPLE, "[the] (player|server) hover list");
    }

    @Override
    protected String[] get(Event e) {
        return SkriptPing.getInstance().getHoverListStrings().toArray(new String[0]);
    }

    @Override
    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.RESET || mode == Changer.ChangeMode.REMOVE)
            return new Class[]{String.class};
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        switch (mode) {
            case ADD:
                SkriptPing.getInstance().getHoverListStrings().add((String) delta[0]);
                break;

            case REMOVE:
                SkriptPing.getInstance().getHoverListStrings().add((String) delta[0]);
                break;

            case RESET:
                SkriptPing.getInstance().getHoverListStrings().clear();

        }
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "hover list";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
