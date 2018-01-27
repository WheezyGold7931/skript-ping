package me.wheezygold.skriptping;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SKU {

    static {
        final String defaultPackage = new String(
                new byte[]{'c', 'o', 'm', '.', 's', 'k', 'u', 'n', 'i', 't','y' , '.', 'd', 'o', 'c', 's'});
        if (SKU.class.getPackage().getName().equals(defaultPackage)) {
            throw new IllegalStateException("skUnity Docs Implementation is in the default package! Please move it to your package!");
        }
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface data {
        String name();
        String desc();
        String example();
    }

}