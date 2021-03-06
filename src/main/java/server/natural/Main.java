package server.natural;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import server.natural.ChatServer.ConnectHandler;
import server.natural.ChatServer.InitChatServer;
import server.natural.command.CommandInvite;
import server.natural.command.CommandSMG;
import server.natural.command.CommandSystemInfo;
import server.natural.events.AddToTheGroup;
import server.natural.events.RequestSelectorListener;
import server.natural.events.onGroupMessage;


import java.util.Objects;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        try{
         getLogger().info(ChatColor.GREEN + "Welcome!");
         saveDefaultConfig();
         Thread.sleep(3000);
         Bukkit.getPluginManager().registerEvents(new ConnectHandler(),this);
         InitChatServer.Init("0.0.0.0",Utils.config.getInt("ChatServerPort"));
         boolean tmp = new Boolean(getConfig().getString("GroupToGame"));
         boolean tmp1 = new Boolean(getConfig().getString("EnableInvite"));
         getLogger().info(ChatColor.LIGHT_PURPLE + "Register Event Listener...");
         if(tmp == true){
             Bukkit.getPluginManager().registerEvents(new onGroupMessage(), this);
         }


         Bukkit.getPluginManager().registerEvents(new RequestSelectorListener(), this);
         Bukkit.getPluginManager().registerEvents(new AddToTheGroup(), this);
         getLogger().info(ChatColor.LIGHT_PURPLE + "Register the Command of this Plugin");
         Objects.requireNonNull(Bukkit.getPluginCommand("smg")).setExecutor(new CommandSMG());
         Objects.requireNonNull(Bukkit.getPluginCommand("systeminfo")).setExecutor(new CommandSystemInfo());
         if(tmp1 == true){
             Objects.requireNonNull(Bukkit.getPluginCommand("botinvite")).setExecutor(new CommandInvite());

         }
         getLogger().info(ChatColor.GREEN + "Every Thing is Ready Now");
        }catch(Exception ignored){}
    }
    @Override
    public void onDisable() {
        getLogger().info("Goodbye and See You Next Time");
        this.saveConfig();
    }
}
