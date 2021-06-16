package com.shrigorevich.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Deprecated
public class LoginEvent extends Event implements Cancellable {

    private final Player player;
    private final String message;
    private static final HandlerList HANDLERS = new HandlerList();
    private boolean cancelled;

    public LoginEvent(Player p, String msg) {
        this.player = p;
        this.message = msg;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean result) {
        this.cancelled = result;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
