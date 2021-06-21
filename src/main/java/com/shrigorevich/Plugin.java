package com.shrigorevich;

import com.shrigorevich.commands.AuthExecutor;
import com.shrigorevich.commands.VillageExecutor;
import com.shrigorevich.listeners.*;
import com.shrigorevich.authorization.PlayerManager;
import com.shrigorevich.regions.VillageManager;
import com.shrigorevich.regions.session.SessionManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public final class Plugin extends JavaPlugin implements Listener {
    private DataBase db;
    private PlayerManager playerManager;
    private SessionManager sessionManager;
    private VillageManager villageManager;

    @Override
    public void onEnable() {

        db = new DataBase();
        playerManager = new PlayerManager();
        sessionManager = new SessionManager();
        villageManager = new VillageManager();

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new OnLeft(), this);
        getServer().getPluginManager().registerEvents(new PreLogin(), this);
        getServer().getPluginManager().registerEvents(new PingListener(), this);
        getServer().getPluginManager().registerEvents(new PreventActionListener(), this);
        getServer().getPluginManager().registerEvents(new OnInteract(), this);

        getCommand("auth").setExecutor(new AuthExecutor());
        getCommand("village").setExecutor(new VillageExecutor());

    }

    @Override
    public void onDisable() {
    }

    public PlayerManager getPlayerManager(){
        return playerManager;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public VillageManager getVillageManager() {
        return villageManager;
    }

    public DataBase getDb() {
        return db;
    }

    public static Plugin getInstance() {
        return getPlugin(Plugin.class);
    }
}
