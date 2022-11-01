package Utilities;

import org.bukkit.entity.*;
import me.es359.Broadcast.*;
import org.bukkit.command.*;
import org.bukkit.*;
import java.util.*;

public class BroadcastUtils
{
    private String VERSION_ID = "3.0.2 6/5/16";
    private String prefix;
    private String permission;
    private String donationURL;
    String author;
    
    public BroadcastUtils() {
        this.prefix = ChatColor.translateAlternateColorCodes('&', "&2&l[&b&lBroadcast&2&l]&6: &f");
        this.permission = this.getPrefix() + color("&cUnknown command. Type \"/help\" for help.");
        this.donationURL = color("https://www.paypal.me/ES359");
        this.author = "9c5dd792-dcb3-443b-ac6c-605903231eb2";
    }
    
    public String getPermission() {
        return this.permission;
    }
    
    public String getDonationURL() {
        return this.donationURL;
    }
    
    public boolean checkAuth(UUID user) {
        return user.toString().equals(this.author);
    }
    
    public void displayAuthInfo(Player p) {
        if (checkAuth(p.getUniqueId())) {
            p.sendMessage(color("&a&l&oHello, &7" + p.getName() + "\n &aThis server is using " + getPrefix() + "&r\n&cVersion ID: &7" + "3.0.2 6/5/16"));
        }
    }
    
    public String getPluginVersion(Broadcast main, CommandSender sender) {
        return color("&fHello, &a&n" + sender.getName() + ".&r\nYou are currently running version &b&n" + main.pdfFile.getVersion() + "&r of &e&n" + main.pdfFile.getName() + "&r\n \n&6Your server is running version &c&n" + main.getServer().getBukkitVersion());
    }
    
    public void authorToggle(Broadcast main, Player author) {
        if (!main.getAuthor().contains(author.getUniqueId())) {
            displayAuthInfo(author);
        }
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public void logToConsole(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
    
    public void desc(CommandSender sender, Broadcast main) {
        sender.sendMessage(color("&2========== " + this.getPrefix().replace(":", "") + "&2=========="));
        sender.sendMessage(color("&7[&9" + main.pdfFile.getName() + "&7] &6Created by, &b&l" + main.pdfFile.getAuthors() + "&6."));
        sender.sendMessage(color("&2" + main.pdfFile.getDescription() + "&2."));
        sender.sendMessage(color("&bWebsite: &e&l" + main.pdfFile.getWebsite()));
        sender.sendMessage(color("&bList of sounds: &3&nhttps://gist.github.com/ES359/7aa8da5dbf88496e4098 - Configuration sounds... "));
        sender.sendMessage(color("&bMinecraft Color Codes: &a&nhttp://minecraftcolorcodes.com/"));
        sender.sendMessage(color("&7If you find bugs, errors, or would like to suggest ideas: https://github.com/ES359/Broadcast/issues/new"));
        sender.sendMessage(color("&9If you like my work, you can support me by donating &ahere: " + this.getDonationURL()));
        sender.sendMessage(color("&8-----------------------------"));
    }
    
    public void displayHelp(CommandSender sender, String title, String command, String info) {
        sender.sendMessage(color(title));
        sender.sendMessage("");
        sender.sendMessage(color(command));
        sender.sendMessage("");
        sender.sendMessage(color(info));
    }
    
    public void displayHelpMsg(Player player, String title, String body, String information) {
        player.sendMessage(color(title));
        player.sendMessage("");
        player.sendMessage(color(body));
        player.sendMessage(" ");
        player.sendMessage(color(information));
    }
    
    public String color(String message) {
        String msg = message;
        msg = msg.replace("&", "§");
        msg = msg.replace("{prefix}", this.getPrefix());
        return msg;
    }
    
    public void exceptionDebug(Exception e) {
        if (Broadcast.DEBUG) {
            logToConsole("&cERROR: &3" + e.getMessage());
            e.printStackTrace();
        }
        else {
            logToConsole("&cERROR: &3" + e.getMessage());
        }
    }
    
    public void broadcastSound(String sound, boolean enabled) {
        if (enabled) {
            try {
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    p.playSound(p.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                }
            }
            catch (NullPointerException e) {
                this.exceptionDebug(e);
            }
        }
    }
    
    public void sendText(ArrayList<String> text, CommandSender sender) {
        for (String txt : text) {
            txt = txt.replace("%player%", sender.getName());
            sender.sendMessage(color(txt));
        }
    }
}
