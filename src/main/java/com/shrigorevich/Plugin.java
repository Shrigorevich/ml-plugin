package com.shrigorevich;

import com.shrigorevich.commands.AuthExecutor;
import com.shrigorevich.commands.VillageExecutor;
import com.shrigorevich.listeners.*;
import com.shrigorevich.authorization.PlayerManager;
import com.shrigorevich.villages.VillageCreator;
import com.shrigorevich.villages.VillageManager;
import com.shrigorevich.villages.session.SessionManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import static com.shrigorevich.villages.VillageLoader.loadVillages;


public final class Plugin extends JavaPlugin implements Listener {
    private DataBase db;
    private PlayerManager playerManager;
    private SessionManager sessionManager;
    private VillageManager villageManager;
    private VillageCreator villageCreator;

    @Override
    public void onEnable() {

        db = new DataBase();
        playerManager = new PlayerManager();
        sessionManager = new SessionManager();
        villageManager = new VillageManager();
        villageCreator = new VillageCreator();

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new OnLeft(), this);
        getServer().getPluginManager().registerEvents(new PreLogin(), this);
        getServer().getPluginManager().registerEvents(new PingListener(), this);
        getServer().getPluginManager().registerEvents(new PreventActionListener(), this);
        getServer().getPluginManager().registerEvents(new OnInteract(), this);
        getServer().getPluginManager().registerEvents(new OnBreak(), this);

        getCommand("auth").setExecutor(new AuthExecutor());
        getCommand("village").setExecutor(new VillageExecutor());

        loadVillages();
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

    public VillageManager getVillageManager() { return villageManager; }

    public VillageCreator getVillageCreator() { return villageCreator; }

    public DataBase getDb() {
        return db;
    }

    public static Plugin getInstance() {
        return getPlugin(Plugin.class);
    }
}
