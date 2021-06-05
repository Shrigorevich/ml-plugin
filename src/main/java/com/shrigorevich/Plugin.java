package com.shrigorevich;

import com.shrigorevich.authorization.AuthCommandExecutor;
import com.shrigorevich.authorization.AuthListener;
import com.shrigorevich.authorization.PlayerCache;
import com.shrigorevich.authorization.PreventActionListener;
import org.bukkit.plugin.java.JavaPlugin;


public final class Plugin extends JavaPlugin {
    private DataBase db;
    private PlayerCache pCache;

    @Override
    public void onEnable() {
        // Plugin startup logic
        db = new DataBase();
        pCache = new PlayerCache();
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new AuthListener(), this);
        getServer().getPluginManager().registerEvents(new PingListener(), this);
        getServer().getPluginManager().registerEvents(new PreventActionListener(), this);

        getCommand("auth").setExecutor(new AuthCommandExecutor());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public PlayerCache getPlayerCache(){
        return pCache;
    }

    public DataBase getDb() {
        return db;
    }

    public static Plugin getInstance() {
        return getPlugin(Plugin.class);
    }
}
