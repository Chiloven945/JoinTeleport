package bugle.cloven.jointeleport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        FileConfiguration config = JoinTeleport.main.getConfig(); //调用配置。
        int stopCount = config.getInt("stopMessagingTime");
        Player player = event.getPlayer();
        Location spawnLocation = new Location(Bukkit.getWorld("world"), //指定玩家传送的位置。
                config.getDouble("cords.x"),
                config.getDouble("cords.y"),
                config.getDouble("cords.z"));
        int joinCount = player.getScoreboard().getObjective("leave_game").getScore(player.getName()).getScore();

        player.teleport(spawnLocation); //执行传送。

        if (stopCount > 0 && joinCount <= stopCount) { //判断功能情况。
            player.sendMessage(ChatColor.GOLD + Objects.requireNonNull(config.getString("joinMessage")));
            player.getScoreboard().getObjective("leave_game").getScore(player.getName()).setScore(joinCount + 1);
        } else if (stopCount == 0) {
            player.sendMessage(ChatColor.GOLD + Objects.requireNonNull(config.getString("joinMessage")));
        }

    }

}
