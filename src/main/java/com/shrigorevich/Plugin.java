package com.shrigorevich;

import com.shrigorevich.commands.AuthExecutor;
import com.shrigorevich.commands.RegionExecutor;
import com.shrigorevich.listeners.OnInteract;
import com.shrigorevich.listeners.OnLeft;
import com.shrigorevich.listeners.PreLogin;
import com.shrigorevich.authorization.PlayerCache;
import com.shrigorevich.listeners.PreventActionListener;
import com.shrigorevich.regions.RegionManager;
import com.shrigorevich.regions.session.SessionManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;


public final class Plugin extends JavaPlugin implements Listener {
    private DataBase db;
    private PlayerCache pCache;
    private SessionManager sessionManager;
    private RegionManager regionManager;

    @Override
    public void onEnable() {


        db = new DataBase();
        pCache = new PlayerCache();
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

    public PlayerCache getPlayerCache(){
        return pCache;
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
