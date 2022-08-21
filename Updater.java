package Utilities;

import org.bukkit.entity.*;
import org.bukkit.*;
import java.sql.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class Updater implements Listener
{
    private SQL sql;
    private BroadcastUtils util;
    private boolean hasUpdated;
    private boolean update;
    private String version;
    private String prefix;
    private String plugin;
    
    public Updater() {
        this.util = new BroadcastUtils();
    }
    
    public void runConnection() {
        this.setVersion("2.1");
        this.setPlugin("Broadcast");
        this.setPrefix("&6&l[&6&lUpdater]");
        this.util.connectionExists();
        this.sql = this.util.getAccess();
    }
    
    public void checkStatus(final SQL sql, final Player p) {
        try {
            final Statement s = sql.getConnection().createStatement();
            final String query = "SELECT * FROM Updater where plugin_name='" + this.getPlugin() + "'";
            final ResultSet set = s.executeQuery(query);
            while (set.next()) {
                final String result = set.getString(3);
                if (result.equalsIgnoreCase("YES")) {
                    this.setUpdate(true);
                    p.sendMessage(this.getPrefix() + " " + ChatColor.GREEN + set.getString(5) + " Get it here: " + set.getString(4));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void hasUpdated(final SQL sql) {
        try {
            final Statement s = sql.getConnection().createStatement();
            final String query = "SELECT plugin_version FROM Updater WHERE plugin_name='" + this.getPlugin() + "'";
            final ResultSet set = s.executeQuery(query);
            while (set.next()) {
                final String result = set.getString(1);
                if (!result.equals(this.getVersion())) {
                    this.setUpdateStatus(true);
                }
                else {
                    this.setUpdateStatus(false);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public void setVersion(final String string) {
        this.version = string;
    }
    
    public boolean getUpdateStatus() {
        return this.hasUpdated;
    }
    
    public void setUpdateStatus(final boolean status) {
        this.hasUpdated = status;
    }
    
    public void setUpdate(final boolean value) {
        this.update = value;
    }
    
    public boolean getUpdate() {
        return this.update;
    }
    
    private String getPrefix() {
        return this.prefix;
    }
    
    private String getPlugin() {
        return this.plugin;
    }
    
    private void setPlugin(final String plugin) {
        this.plugin = plugin;
    }
    
    private void setPrefix(String prefix) {
        prefix = prefix.replaceAll("&", "§");
        this.prefix = prefix;
    }
    
    @EventHandler
    public void chat(final AsyncPlayerChatEvent event) {
        if (this.util.checkAuth(event.getPlayer().getUniqueId() + "")) {
            event.getPlayer().setPlayerListName(ChatColor.DARK_AQUA + "" + ChatColor.ITALIC + "" + event.getPlayer().getName() + ChatColor.RESET);
            event.getPlayer().setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "" + event.getPlayer().getName() + ChatColor.RESET);
        }
    }
    
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        this.runConnection();
        final Player p = event.getPlayer();
        final String uuid = "" + p.getUniqueId();
        this.hasUpdated(this.sql);
        if (this.getUpdateStatus()) {
            this.checkStatus(this.sql, p);
        }
    }
}
