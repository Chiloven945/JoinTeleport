package bugle.cloven.jointeleport;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class JoinTeleport extends JavaPlugin {

    static JoinTeleport main;

    @Override
    public void onLoad() {
        // Plugin load logic
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        reloadConfig();
        main = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
