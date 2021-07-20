package com.shrigorevich.listeners.mobs;
import com.shrigorevich.Plugin;
import com.shrigorevich.customGoals.TestGoal;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class OnEntitySpawn implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnEntitySpawn(EntitySpawnEvent event) {
        Plugin p = Plugin.getInstance();
        Entity entity = event.getEntity();
        if (event.getEntityType() == EntityType.ZOMBIE && p.mobs.containsKey(entity.getUniqueId())) {
            Zombie zombie = (Zombie) event.getEntity();
            TestGoal goal = new TestGoal(p, zombie);
            if (!Bukkit.getMobGoals().hasGoal(zombie, goal.getKey())) {
                Bukkit.getMobGoals().addGoal(zombie, 3, goal);
            }
        }
    }
}
