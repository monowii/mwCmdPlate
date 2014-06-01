package me.monowii.cmdplate;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CmdPlate extends JavaPlugin implements Listener
{
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onSignChange(SignChangeEvent e)
	{
		if (e.getLine(0).equalsIgnoreCase("[cmdplate]") && e.getPlayer().hasPermission("cmdplate"))
		{
			e.setLine(0, ChatColor.DARK_GRAY+"[mwCmdPlate]");
		}
		else
		{
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		if (e.getAction() == Action.PHYSICAL)
		{
			if (isPlate(e.getClickedBlock().getType()))
			{
				Location signLoc = e.getClickedBlock().getLocation().add(0, -2, 0);
				
				if (signLoc.getBlock().getState() instanceof Sign)
				{
					Sign s = (Sign) signLoc.getBlock().getState();
					
					if (s.getLine(0).equals(ChatColor.DARK_GRAY+"[mwCmdPlate]"))
					{
						String cmd = s.getLine(1) + s.getLine(2) + s.getLine(3);
						e.getPlayer().performCommand(cmd);
					}
				}
			}
		}
	}

	public boolean isPlate(Material m)
	{
		return m == Material.STONE_PLATE || m == Material.WOOD_PLATE || m == Material.IRON_PLATE || m == Material.GOLD_PLATE;
	}
}
