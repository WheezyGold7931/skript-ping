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
import com.comphenix.protocol.wrappers.WrappedServerPing;
import me.wheezygold.skriptping.SKU;
import me.wheezygold.skriptping.SkriptPing;
import org.bukkit.event.Event;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

@Name("Set Server Icon (Base64)")
@Description("Set's the server's icon from base 64 code.")
@Examples({"set the server-icon to base64 \"*base 64 code*\""})
@Since("0.1")
@SKU.data(
        name = "Set Server Icon (Base64)",
        desc = "Set's the server's icon from base 64 code.",
        example = "set the server-icon to base64 \"*base 64 code*\""
)
public class EffSetIcon64 extends Effect {

    static {
        Skript.registerEffect(EffSetIcon64.class, "set [the] server(-| )icon to base64 %string%");
    }

    private Expression<String> baseString;

    @Override
    protected void execute(Event e) {
        try {
            byte[] imgBytes = Base64.getDecoder().decode(baseString.getSingle(e));
            BufferedImage image = toBufferedImage(ImageIO.read(new ByteArrayInputStream(imgBytes)).getScaledInstance(64, 64, 1));
            SkriptPing.getInstance().setServerIcon(WrappedServerPing.CompressedImage.fromPng(image));
        } catch (IOException | IllegalArgumentException e1) {
            Skript.error("Unable to decode your base-64 string!");
            e1.printStackTrace();
        }
    }

    @Override
    public String toString(Event e, boolean debug) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        baseString = (Expression<String>) exprs[0];
        return true;
    }

    private BufferedImage toBufferedImage(Image image) {
        BufferedImage buffer = new BufferedImage(
                image.getWidth(null),
                image.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D g = buffer.createGraphics();

        g.drawImage(image, null, null);
        return buffer;
    }
}
