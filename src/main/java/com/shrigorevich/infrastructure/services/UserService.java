package com.shrigorevich.infrastructure.services;

import com.mongodb.client.MongoDatabase;
import com.shrigorevich.Plugin;
import com.shrigorevich.authorization.UserData;
import com.shrigorevich.state.UserManager;
import com.shrigorevich.infrastructure.db.UserContext;
import org.bukkit.entity.Player;

public class UserService {

    private UserManager userManager;
    private UserContext userContext;

    public UserService(MongoDatabase dataBase) {
        this.userManager = new UserManager();
        this.userContext = new UserContext(dataBase);
    }

    public void joinVillage(Player player, String villageName) {
        Plugin p = Plugin.getInstance();
        if(p.getVillageService().isVillageExist(villageName)) {
            UserData uData = userManager.getUser(player.getName());
            uData.setVillage(villageName);
            userContext.joinVillage(player.getName(), villageName);
        } else {
            player.sendMessage("Village with that name does not exist");
        }
    }

    public UserData getUserData(String userName) {
        return userManager.getUser(userName);
    }

    public UserData authPlayer(Player player, String password) {
        return userContext.authPlayer(player.getName(), password);
    }

    public void addUserToState(UserData uData) {
        userManager.addUser(uData);
    }

    public void removeUserFromState(String playerName) {
        userManager.removeUser(playerName);
    }
}
