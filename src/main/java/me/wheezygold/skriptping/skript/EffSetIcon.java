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
import java.io.File;
import java.io.IOException;

@Name("Set Server Icon (Image File)")
@Description("Set's the server's icon")
@Examples({"set the server-icon to image at file \"test.png\""})
@Since("0.1")
@SKU.data(
        name = "Set Server Icon (Image File)",
        desc = "Set's the server's icon",
        example = "set the server-icon to image at file \"test.png\""
)
public class EffSetIcon extends Effect {

    static {
        Skript.registerEffect(EffSetIcon.class, "set [the] server(-| )icon to [image at] file %string%");
    }

    private Expression<String> fileString;

    @Override
    protected void execute(Event e) {
        if (!fileString.getSingle(e).endsWith(".png")) {
            Skript.error("Icon file must be a png!");
            return;
        }
        File file = new File(fileString.getSingle(e));
        if (!file.exists()) {
            Skript.error("Icon file does exist!");
            return;
        }
        BufferedImage image;
        try {
            image = toBufferedImage(ImageIO.read(file).getScaledInstance(64, 64, 1));
        } catch (IOException e1) {
            Skript.error("Unable to access file!");
            e1.printStackTrace();
            return;
        }
        try {
            SkriptPing.getInstance().setServerIcon(WrappedServerPing.CompressedImage.fromPng(image));
        } catch (IOException e1) {
            Skript.error("Unable to set server icon!");
            e1.printStackTrace();
        }
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

    @Override
    public String toString(Event e, boolean debug) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        fileString = (Expression<String>) exprs[0];
        return true;
    }
}
