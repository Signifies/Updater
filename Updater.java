package Utilities;

import org.bukkit.entity.*;
import org.bukkit.*;
import java.sql.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class Updater implements Listener
{
    private SQL sql; //SQL instance
    private BroadcastUtils util; //Utilities class for formatting purposes. 
    private boolean hasUpdated; //comparison to check if versioning is different in database.
    private boolean update; //update value
    private String version; //Current software version.
    private String prefix; //Software prefix.
    private String plugin; //Software Name.
    
    public Updater() {
        this.util = new BroadcastUtils();
    }
    
    public void runConnection() {
        this.setVersion("2.1");
        this.setPlugin("Broadcast");
        this.setPrefix("&6&l[&6&lUpdater]");
        this.util.connectionExists();
       // this.sql = this.util.getAccess();
    }


    /**
	Status check for updates to see if an update has been posted in the database. 
	If an update is detected, informational message is sent and a web link to access update is included.
    */
    public void checkStatus(SQL sql, final Player p) {
        try {
            Statement s = sql.getConnection().createStatement();
            String query = "SELECT * FROM Updater where plugin_name='" + getPlugin() + "'";
            ResultSet set = s.executeQuery(query);
            while (set.next()) {
                String result = set.getString(3);
                if (result.equalsIgnoreCase("YES")) {
                    setUpdate(true);
                    p.sendMessage(getPrefix() + " " + ChatColor.GREEN + set.getString(5) + " Get it here: " + set.getString(4));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
   	Checks to see Checks to see if a plugin has properly been updated. 
	Sets update status true or false based on versioning.
    */
    public void hasUpdated(final SQL sql) {
        try {
            Statement s = sql.getConnection().createStatement();
            String query = "SELECT plugin_version FROM Updater WHERE plugin_name='" + this.getPlugin() + "'";
            ResultSet set = s.executeQuery(query);
            while (set.next()) {
                 String result = set.getString(1);
                if (!result.equals(getVersion())) {
                    setUpdateStatus(true);
                }
                else {
                    setUpdateStatus(false);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /** 
	Returns the version indicated by decimal format, IE: 0.1,1.1,1.4 ETC.
    */
    public String getVersion() {
        return this.version;
    }
    /**
	Sets the current version for the resource.
    */
    public void setVersion(String string) {
        this.version = string;
    }
    
    /**
	Gets the update status result.
    */
    public boolean getUpdateStatus() {
        return this.hasUpdated;
    }

    /**
	Sets the updater status.
    */	    
    public void setUpdateStatus(boolean status) {
        this.hasUpdated = status;
    }
    /**
	Sets the update result.
    */
    public void setUpdate(boolean value) {
        this.update = value;
    }
    /**
	Gets the new update and returns it as a boolean.
    */
    public boolean getUpdate() {
        return this.update;
    }
    /**
	Returns the updater prefix.
    */
    private String getPrefix() {
        return this.prefix;
    }
    /**
	Returns the Plugin or resource to be updated.
    */
    private String getPlugin() {
        return this.plugin;
    }
    /**
	Sets the plugin or resource name to be updated.
    */
    private void setPlugin(String plugin) {
        this.plugin = plugin;
    }
    /**
	Set the prefix of the updater.
    */
    private void setPrefix(String prefix) {
        prefix = prefix.replaceAll("&", "§");
        this.prefix = prefix;
    }
    
    @Depricated // This event is for an unrelated function to the updater and is related to the software authors identification.
    @EventHandler
    public void chat(final AsyncPlayerChatEvent event) {
        if (util.checkAuth(event.getPlayer().getUniqueId() + "")) {
            event.getPlayer().setPlayerListName(ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + "" + event.getPlayer().getName() + ChatColor.RESET);
            event.getPlayer().setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "" + event.getPlayer().getName() + ChatColor.RESET);
        }
    }
    
    /**
	Example of updater usage with the PlayerJoinEvent. 
    */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        runConnection();
         Player p = event.getPlayer();
        String uuid = "" + p.getUniqueId();
        hasUpdated(sql);
        if (getUpdateStatus()) {
            checkStatus(sql, p);
        }
    }
}
