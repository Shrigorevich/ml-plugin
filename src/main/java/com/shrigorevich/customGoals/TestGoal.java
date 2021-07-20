package com.shrigorevich.customGoals;

import com.destroystokyo.paper.entity.ai.Goal;
import com.destroystokyo.paper.entity.ai.GoalKey;
import com.destroystokyo.paper.entity.ai.GoalType;
import com.shrigorevich.Plugin;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

import java.util.Collection;
import java.util.EnumSet;

public class TestGoal implements Goal<Zombie> {
    private final GoalKey<Zombie> key;
    private final Mob mob;
    private Player closestPlayer;
    private int cooldown;

    public TestGoal(Plugin plugin, Mob mob) {
        this.key = GoalKey.of(Zombie.class, new NamespacedKey(plugin, "attack"));
        this.mob = mob;
    }

    @Override
    public boolean shouldActivate() {
        if (cooldown > 0) {
            --cooldown;
            return false;
        }
        closestPlayer = getClosestPlayer();
        if (closestPlayer == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean shouldStayActive() {
        Bukkit.broadcast(Component.text(ChatColor.YELLOW + "Attention!" + " " + cooldown));
        return shouldActivate();
    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
        Bukkit.broadcast(Component.text(ChatColor.BLUE + "Goal stopped"));
        mob.getPathfinder().stopPathfinding();
        mob.setTarget(null);
        cooldown = 100;
    }

    @Override
    public void tick() {
        mob.setTarget(closestPlayer);
        if (mob.getLocation().distanceSquared(closestPlayer.getLocation()) < 6.25) {
            mob.getPathfinder().stopPathfinding();
        } else {
            mob.getPathfinder().moveTo(closestPlayer, 1.0D);
        }
    }

    @Override
    public GoalKey<Zombie> getKey() {
        return key;
    }

    @Override
    public EnumSet<GoalType> getTypes() {
        return EnumSet.of(GoalType.MOVE, GoalType.LOOK, GoalType.TARGET);
    }

    private Player getClosestPlayer() {
        Collection<Player> nearbyPlayers = mob.getWorld().getNearbyPlayers(mob.getLocation(), 10.0, player ->
                !player.isDead() && player.getGameMode() != GameMode.SPECTATOR && player.isValid());
        double closestDistance = -1.0;
        Player closestPlayer = null;
        for (Player player : nearbyPlayers) {
            double distance = player.getLocation().distanceSquared(mob.getLocation());
            if (closestDistance != -1.0 && !(distance < closestDistance)) {
                continue;
            }
            closestDistance = distance;
            closestPlayer = player;
        }
        return closestPlayer;
    }
}
