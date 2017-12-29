package me.wheezygold.skriptping;

import ch.njol.skript.Skript;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerOptions;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import me.wheezygold.skripthack.SkriptHack;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkriptPing extends JavaPlugin {

    private static SkriptPing instance;
    private List<String> hoverListStrings = new ArrayList<>();
    private String customVersionName = "";
    private String customMotd = "";
    private WrappedServerPing.CompressedImage serverIcon = null;
    private PluginManager pluginManager = getServer().getPluginManager();

    @Override
    public void onEnable() {
        log(C.cDAqua + "Loading skript-ping!");
        instance = this;
        if (pluginManager.getPlugin("Skript") != null) {
            if (pluginManager.getPlugin("ProtocolLib") != null) {
                log(C.cDAqua + "Loading Metrics...");
                Metrics metrics = new Metrics(this);
                metrics.addCustomChart(new Metrics.SimplePie("skript_version", () -> Bukkit.getServer().getPluginManager().getPlugin("Skript").getDescription().getVersion()));
                log(C.cDAqua + "Loading Syntax...");
                if (loadSkript()) {
                    log(C.cDAqua + "Loading Ping Listener!");
                    initListener();
                }
            } else {
                log(C.cDRed + "This plugin also requires ProtocolLib to work, please download it and restart your server!");
            }
        } else {
            log(C.cDRed + "This plugin is a Skript Addon so therefore it requires Skript to be enabled!");
        }
    }

    private boolean loadSkript() {
        boolean isPost = false;
        if (!Skript.isAcceptRegistrations()) {
            isPost = true;
            if (pluginManager.getPlugin("skript-hack") == null) {
                log(C.cPurple + "Skript is not accepting registrations currently! To load syntax without restarting your server get skript-hack at: https://tinyurl.com/skript-hack");
                return false;
            }
            try {
                SkriptHack.enableRegistrations();
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log(C.cPurple + "Error while skript-hack'ing into your syntax! Please restart your server!");
                return false;
            }
        }

        try {
            Skript.registerAddon(this).loadClasses("me.wheezygold.skriptping", "skript");
        } catch (IOException e) {
            log(C.cDRed + "Skript was unable to register this addon and load our syntax. Please report this!");
            return false;
        }

        if (isPost) {
            try {
                SkriptHack.disableRegistrations();
            } catch (NoSuchFieldException | IllegalAccessException e) {
                log(C.cPurple + "Error while skript-hack'ing into your syntax! Please restart your server!");
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    private void initListener() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, Collections.singletonList(PacketType.Status.Server.OUT_SERVER_INFO), ListenerOptions.ASYNC) {
            @Override
            public void onPacketSending(PacketEvent event) {
                WrappedServerPing ping = event.getPacket().getServerPings().read(0);
                if (!hoverListStrings.isEmpty()) {
                    int count = 0;
                    final List<WrappedGameProfile> hoverList = new ArrayList<>();
                    for (String curString : hoverListStrings) {
                        hoverList.add(new WrappedGameProfile(String.valueOf(count), ChatColor.translateAlternateColorCodes('&', curString)));
                        count++;
                    }
                    ping.setPlayers(hoverList);
                }
                if (!customVersionName.isEmpty()) {
                    ping.setVersionName(customVersionName);
                }
                if (!customMotd.isEmpty()) {
                    ping.setMotD(ChatColor.translateAlternateColorCodes('&', customMotd));
                }
                if (serverIcon != null) {
                    ping.setFavicon(serverIcon);
                }
            }
        });
    }

    public static SkriptPing getInstance() {
        return instance;
    }

    public List<String> getHoverListStrings() {
        return hoverListStrings;
    }

    public void setCustomVersionName(String customVersionName) {
        this.customVersionName = customVersionName;
    }

    public void setCustomMotd(String customMotd) {
        this.customMotd = customMotd;
    }

    public void setServerIcon(WrappedServerPing.CompressedImage icon) {
        serverIcon = icon;
    }

    public void resetIcon() {
        serverIcon = null;
    }

    private void log(String message) {
        getServer().getConsoleSender().sendMessage("[skript-ping] " + message);
    }

    @SuppressWarnings("unused")
    public static class C {

        static String Error = "§k";
        static String Bold = "§l";
        static String Strike = "§m";
        static String BoldStrike = "§l§m";
        static String NewLine = "§n";
        static String Italics = "§o";

        static String cAqua = "" + ChatColor.AQUA;
        static String cBlack = "" + ChatColor.BLACK;
        static String cBlue = "" + ChatColor.BLUE;
        static String cDAqua = "" + ChatColor.DARK_AQUA;
        static String cDBlue = "" + ChatColor.DARK_BLUE;
        static String cDGray = "" + ChatColor.DARK_GRAY;
        static String cDGreen = "" + ChatColor.DARK_GREEN;
        static String cDPurple = "" + ChatColor.DARK_PURPLE;
        static String cDRed = "" + ChatColor.DARK_RED;
        static String cGold = "" + ChatColor.GOLD;
        static String cGray = "" + ChatColor.GRAY;
        static String cGreen = "" + ChatColor.GREEN;
        static String cPurple = "" + ChatColor.LIGHT_PURPLE;
        static String cRed = "" + ChatColor.RED;
        static String cWhite = "" + ChatColor.WHITE;
        static String cYellow = "" + ChatColor.YELLOW;

    }
}
