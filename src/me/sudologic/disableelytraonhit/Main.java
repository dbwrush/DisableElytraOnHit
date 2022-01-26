package me.sudologic.disableelytraonhit;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

public class Main extends JavaPlugin{
    public int timer;
    public static HashMap<Player, Long> recentlyHit = new HashMap<>();

    private File customConfigFile;
    private FileConfiguration customConfig;

    public static Main plugin;
    public void Plugin(Main plugin) {Main.plugin = plugin;}
    public static Main getPlugin() {return plugin;}

    @Override
    public void onEnable() {
        Plugin(this);
        Bukkit.getLogger().log(Level.INFO, "[DisableElytraOnHit] Plugin has started!");

        createCustomConfig();
        createConfigs();

        registerListeners();


    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().log(Level.INFO, "[DisableElytraOnHit] Plugin has shut down.");
    }

    public void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new OnPlayerDamage(), this);
        pluginManager.registerEvents(new OnPlayerFly(), this);
    }

    public void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "config.yml");
        if(!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        customConfig = new YamlConfiguration();
        try{
            customConfig.load(customConfigFile);
        } catch(IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void createConfigs() {
        this.saveDefaultConfig();
        this.getConfig();
        FileConfiguration config = this.getConfig();

        timer = config.getInt("timer");
    }


}
