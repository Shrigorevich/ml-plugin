package com.shrigorevich;

import com.shrigorevich.commands.CellExecutor;
import com.shrigorevich.commands.MatrixExecutor;
import com.shrigorevich.commands.MobExecutor;
import com.shrigorevich.commands.VillageExecutor;
import com.shrigorevich.infrastructure.db.*;
import com.shrigorevich.infrastructure.services.MatrixService;
import com.shrigorevich.infrastructure.services.UserService;
import com.shrigorevich.infrastructure.services.VillageService;
import com.shrigorevich.listeners.*;
import com.shrigorevich.listeners.mobs.OnEntitySpawn;
import com.shrigorevich.skins.SkinChanger;
import com.shrigorevich.landRegistry.lands.CellPurchaseProcessor;
import com.shrigorevich.landRegistry.villages.MatrixCreator;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.shrigorevich.landRegistry.villages.VillageLoader.loadVillages;


public final class Plugin extends JavaPlugin implements Listener {
    private CellPurchaseProcessor cellPurchaseProcessor;
    private SkinChanger skinChanger;
    private DbContext dbContext;
    private SkinContext skinContext;
    private MatrixCreator matrixCreator;
    private VillageService villageService;
    private MatrixService matrixService;
    private UserService userService;

    public Map<String, Entity> mobs = new ConcurrentHashMap<>();

    @Override
    public void onEnable() {

        this.dbContext = new DbContext();
        this.skinContext = new SkinContext(dbContext.getDatabase());
        this.villageService = new VillageService(dbContext.getDatabase());
        this.matrixService = new MatrixService(dbContext.getDatabase());
        this.userService = new UserService(dbContext.getDatabase());
        //TODO: encapsulate to CellService
        cellPurchaseProcessor = new CellPurchaseProcessor();
        skinChanger = new SkinChanger();
        this.matrixCreator = new MatrixCreator();

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PingListener(), this);
        getServer().getPluginManager().registerEvents(new OnEntitySpawn(), this);
//        getServer().getPluginManager().registerEvents(new OnLeft(), this);
//        getServer().getPluginManager().registerEvents(new PreLogin(), this);
//        getServer().getPluginManager().registerEvents(new PreventActionListener(), this);
        getServer().getPluginManager().registerEvents(new OnInteract(), this);
//        getServer().getPluginManager().registerEvents(new OnBreak(), this);
//        getServer().getPluginManager().registerEvents(new OnJoin(), this);
//        getServer().getPluginManager().registerEvents(new OnBlockPlace(), this);
//        getCommand("auth").setExecutor(new AuthExecutor());
        getCommand("matrix").setExecutor(new MatrixExecutor());
        getCommand("village").setExecutor(new VillageExecutor());
        getCommand("cells").setExecutor(new CellExecutor());
        getCommand("mob").setExecutor(new MobExecutor());
//        //TODO: move logic to startup class
//        loadVillages();
    }

    @Override
    public void onDisable() {
    }

    public MatrixService getMatrixService() {
        return matrixService;
    }

    public VillageService getVillageService() {
        return villageService;
    }

    public UserService getUserService() {
        return userService;
    }

    public CellPurchaseProcessor getCellPurchaseProcessor() {return cellPurchaseProcessor; }

    public SkinChanger getSkinChanger() {
        return skinChanger;
    }

    public MatrixCreator getMatrixCreator() {
        return matrixCreator;
    }

    public SkinContext getSkinContext() {
        return skinContext;
    }

    public static Plugin getInstance() {
        return getPlugin(Plugin.class);
    }
}
