package com.shrigorevich;

import com.shrigorevich.commands.AuthExecutor;
import com.shrigorevich.commands.RegionExecutor;
import com.shrigorevich.listeners.OnInteract;
import com.shrigorevich.listeners.OnLeft;
import com.shrigorevich.listeners.PreLogin;
import com.shrigorevich.authorization.PlayerManager;
import com.shrigorevich.listeners.PreventActionListener;
import com.shrigorevich.regions.RegionManager;
import com.shrigorevich.regions.session.SessionManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public final class Plugin extends JavaPlugin implements Listener {
    private DataBase db;
    private PlayerManager playerManager;
    private SessionManager sessionManager;
    private RegionManager regionManager;

    @Override
    public void onEnable() {


        db = new DataBase();
        playerManager = new PlayerManager();
        sessionManager = new SessionManager();
        regionManager = new RegionManager();

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new OnLeft(), this);
        getServer().getPluginManager().registerEvents(new PreLogin(), this);
        getServer().getPluginManager().registerEvents(new PingListener(), this);
        getServer().getPluginManager().registerEvents(new PreventActionListener(), this);
        getServer().getPluginManager().registerEvents(new OnInteract(), this);

        getCommand("auth").setExecutor(new AuthExecutor());
        getCommand("region").setExecutor(new RegionExecutor());

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

    public RegionManager getRegionManager() {
        return regionManager;
    }

    public DataBase getDb() {
        return db;
    }

    public static Plugin getInstance() {
        return getPlugin(Plugin.class);
    }
}
