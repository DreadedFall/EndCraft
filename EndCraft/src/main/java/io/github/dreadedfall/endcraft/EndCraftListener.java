package io.github.dreadedfall.endcraft;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EndCraftListener implements Listener
{
	private final EndCraft main;
	public EndCraftListener(EndCraft e)
	{
		this.main = e;
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		String tool = main.getTool();
		if(event.getAction() == Action.LEFT_CLICK_BLOCK)
		{
			if(event.getClickedBlock().getType().equals(Material.ENDER_PORTAL_FRAME))
			{
				if(event.getPlayer().getGameMode().equals(GameMode.SURVIVAL))
				{
					if(event.getItem().getType().toString().equals(tool))
					{
						if(event.getPlayer().hasPermission("endcraft.break"))
						{
							event.setCancelled(true);
							event.getClickedBlock().setType(Material.AIR);
							event.getClickedBlock().getWorld().dropItemNaturally(event.getClickedBlock().getLocation(), new ItemStack(Material.ENDER_PORTAL_FRAME));
						}
						else
						{
							return;
						}
					}
					else
					{
						return;
					}
				}
				else
				{
					return;
				}
			}
			else
			{
				return;
			}
		}
	}
}
