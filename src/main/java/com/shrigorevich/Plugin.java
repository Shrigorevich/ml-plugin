package com.shrigorevich;

import com.shrigorevich.authorization.AuthCommandExecutor;
import com.shrigorevich.authorization.AuthListener;
import com.shrigorevich.authorization.PlayerCache;
import com.shrigorevich.authorization.PreventActionListener;
import com.shrigorevich.map.Renderer;
import com.shrigorevich.regions.Cuboid;
import com.shrigorevich.regions.Square;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;


public final class Plugin extends JavaPlugin implements Listener {
    private DataBase db;
    private PlayerCache pCache;

    Square square;

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if(!player.isGlowing() && square.contains(e.getTo())) {
            player.setGlowing(true);
        } else if(player.isGlowing() && !square.contains(e.getTo())) {
            player.setGlowing(false);
        }
    }

    @Override
    public void onEnable() {

        square = new Square(
            new Location(getServer().getWorld("world"), 250, 64, 74 ),
            new Location(getServer().getWorld("world"), 260, 70, 80)
        );
        // Plugin startup logic
        db = new DataBase();
        pCache = new PlayerCache();
        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new AuthListener(), this);
        getServer().getPluginManager().registerEvents(new PingListener(), this);
        getServer().getPluginManager().registerEvents(new PreventActionListener(), this);

        getCommand("auth").setExecutor(new AuthCommandExecutor());

    }

    @EventHandler
    public void onMapInitialise(MapInitializeEvent e) {
        MapView view = e.getMap();
        view.getRenderers().clear();
        view.addRenderer(new Renderer());
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
