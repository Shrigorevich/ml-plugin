package com.shrigorevich;

import com.shrigorevich.commands.AuthExecutor;
import com.shrigorevich.commands.CellExecutor;
import com.shrigorevich.commands.VillageExecutor;
import com.shrigorevich.infrastructure.db.*;
import com.shrigorevich.infrastructure.services.CellService;
import com.shrigorevich.infrastructure.services.UserService;
import com.shrigorevich.infrastructure.services.VillageService;
import com.shrigorevich.listeners.*;
import com.shrigorevich.skins.SkinChanger;
import com.shrigorevich.landRegistry.lands.CellPurchaseProcessor;
import com.shrigorevich.landRegistry.villages.VillageCreator;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import static com.shrigorevich.landRegistry.villages.VillageLoader.loadVillages;


public final class Plugin extends JavaPlugin implements Listener {
    private CellPurchaseProcessor cellPurchaseProcessor;
    private SkinChanger skinChanger;
    private DbContext dbContext;
    private SkinContext skinContext;
    private VillageCreator villageCreator;
    private VillageService villageService;
    private CellService cellService;
    private UserService userService;

    @Override
    public void onEnable() {

        this.dbContext = new DbContext();
        this.skinContext = new SkinContext(dbContext.getDatabase());
        this.villageService = new VillageService(dbContext.getDatabase());
        this.cellService = new CellService(dbContext.getDatabase());
        this.userService = new UserService(dbContext.getDatabase());
        //TODO: encapsulate to CellService
        cellPurchaseProcessor = new CellPurchaseProcessor();
        skinChanger = new SkinChanger();
        this.villageCreator = new VillageCreator();

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new OnLeft(), this);
        getServer().getPluginManager().registerEvents(new PreLogin(), this);
        getServer().getPluginManager().registerEvents(new PingListener(), this);
        getServer().getPluginManager().registerEvents(new PreventActionListener(), this);
        getServer().getPluginManager().registerEvents(new OnInteract(), this);
        getServer().getPluginManager().registerEvents(new OnBreak(), this);
        getServer().getPluginManager().registerEvents(new OnJoin(), this);
        getServer().getPluginManager().registerEvents(new OnBlockPlace(), this);
        getCommand("auth").setExecutor(new AuthExecutor());
        getCommand("village").setExecutor(new VillageExecutor());
        getCommand("cells").setExecutor(new CellExecutor());
        //TODO: move logic to startup class
        loadVillages();
    }

    @Override
    public void onDisable() {
    }

    public CellService getCellService() {
        return cellService;
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

    public VillageCreator getVillageCreator() {
        return villageCreator;
    }

    public SkinContext getSkinContext() {
        return skinContext;
    }

    public static Plugin getInstance() {
        return getPlugin(Plugin.class);
    }
}
