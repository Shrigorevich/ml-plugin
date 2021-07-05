package com.shrigorevich;

import com.shrigorevich.commands.AuthExecutor;
import com.shrigorevich.commands.CellExecutor;
import com.shrigorevich.commands.TestExecutor;
import com.shrigorevich.commands.VillageExecutor;
import com.shrigorevich.infrastructure.db.*;
import com.shrigorevich.listeners.*;
import com.shrigorevich.authorization.PlayerManager;
import com.shrigorevich.skins.SkinChanger;
import com.shrigorevich.landRegistry.lands.CellPurchaseProcessor;
import com.shrigorevich.landRegistry.villages.VillageCreator;
import com.shrigorevich.landRegistry.villages.VillageManager;
import com.shrigorevich.landRegistry.SessionManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import static com.shrigorevich.landRegistry.villages.VillageLoader.loadVillages;


public final class Plugin extends JavaPlugin implements Listener {
    private PlayerManager playerManager;
    private SessionManager sessionManager;
    private VillageManager villageManager;
    private VillageCreator villageCreator;
    private CellPurchaseProcessor cellPurchaseProcessor;
    private SkinChanger skinChanger;
    private DbContext dbContext;
    private MatrixCellContext matrixCellContext;
    private UserContext userContext;
    private VillageContext villageContext;
    private SkinContext skinContext;

    @Override
    public void onEnable() {

        this.dbContext = new DbContext();
        this.matrixCellContext = new MatrixCellContext(dbContext.getDatabase());
        this.userContext = new UserContext(dbContext.getDatabase());
        this.villageContext = new VillageContext(dbContext.getDatabase());
        this.skinContext = new SkinContext(dbContext.getDatabase());

        playerManager = new PlayerManager();
        sessionManager = new SessionManager();
        villageManager = new VillageManager();
        villageCreator = new VillageCreator();
        cellPurchaseProcessor = new CellPurchaseProcessor();
        skinChanger = new SkinChanger();

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
        getCommand("test").setExecutor(new TestExecutor());

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

    public CellPurchaseProcessor getCellPurchaseProcessor() {return cellPurchaseProcessor; }

    public SkinChanger getSkinChanger() {
        return skinChanger;
    }

    public MatrixCellContext getMatrixCellContext() {
        return matrixCellContext;
    }

    public UserContext getUserContext() {
        return userContext;
    }

    public VillageContext getVillageContext() {
        return villageContext;
    }

    public SkinContext getSkinContext() {
        return skinContext;
    }

    public static Plugin getInstance() {
        return getPlugin(Plugin.class);
    }
}
