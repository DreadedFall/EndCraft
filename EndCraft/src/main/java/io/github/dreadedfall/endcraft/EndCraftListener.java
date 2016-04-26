package io.github.dreadedfall.endcraft;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EndCraftListener implements Listener
{
	private final EndCraft main;
	private BlockFace[] portals = {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
	public EndCraftListener(EndCraft e)
	{
		this.main = e;
	}

	private void checkFace(int i, Block b)
	{
		Block currentFace = b.getRelative(portals[i]);
		if(currentFace.getType().equals(Material.ENDER_PORTAL))
		{
			currentFace.breakNaturally();
			for(int x = 0; x < 4; x++)
			{
				checkFace(x, currentFace);
			}
		}
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		String tool = main.getTool();
		Player player = event.getPlayer();
		if(player.hasPermission("endcraft.break"))
		{
			if(event.getItem().getType().toString().equals(tool))
			{
				if(event.getAction() == Action.LEFT_CLICK_BLOCK)
				{
					if(event.getClickedBlock().getType().equals(Material.ENDER_PORTAL_FRAME))
					{
						if(player.getGameMode().equals(GameMode.SURVIVAL))
						{
							event.setCancelled(true);
							event.getClickedBlock().setType(Material.AIR);
							for(int i = 0; i < 4; i++)
							{
								checkFace(i, event.getClickedBlock());
							}
							event.getClickedBlock().getWorld().dropItemNaturally(event.getClickedBlock().getLocation(), new ItemStack(Material.ENDER_PORTAL_FRAME));
						}
					}
				}
			}
		}
	}
}
