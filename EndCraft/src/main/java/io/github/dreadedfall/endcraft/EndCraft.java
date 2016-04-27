package io.github.dreadedfall.endcraft;
/**
 * 
 */

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author DreadedFall
 *
 * This plugin adds crafting recipes for end portal frames, end stone, ender pearls, and nether stars
 * To be added:
 * Factions support
 */
public class EndCraft extends JavaPlugin
{
	
	private String tool = "DIAMOND_PICKAXE";
	private boolean endportalconfig = true;
	private boolean pearlconfig = true;
	private boolean stoneconfig = true;
	private boolean starconfig = true;
	private PluginManager pm = this.getServer().getPluginManager();
	
	public void onEnable()
	{
		this.saveDefaultConfig();
		this.setTool(this.getConfig().getString("tool").toUpperCase());
		this.log("EndCraft has been enabled", Level.INFO);
		this.endportalconfig = this.getConfig().getBoolean("End Portal Frame");
		this.pearlconfig = this.getConfig().getBoolean("Ender Pearl");
		this.stoneconfig = this.getConfig().getBoolean("End Stone");
		this.starconfig = this.getConfig().getBoolean("Nether Star");
		pm.registerEvents(new EndCraftListener(this), this);
		if(endportalconfig)
		{
			ShapedRecipe endportal = new ShapedRecipe(new ItemStack(Material.ENDER_PORTAL_FRAME, 1));
			endportal.shape(new String[]{"EEE","ESE","SSS"}).setIngredient('E', Material.EMERALD).setIngredient('S', Material.ENDER_STONE);
			Bukkit.getServer().addRecipe(endportal);
		}
		if(stoneconfig)
		{
			ShapedRecipe endstone = new ShapedRecipe(new ItemStack(Material.ENDER_STONE, 1));
			endstone.shape(new String[]{"XXX", "XDX", "XXX"}).setIngredient('X', Material.SANDSTONE).setIngredient('D', Material.DIAMOND);
			Bukkit.getServer().addRecipe(endstone);
		}
		if(starconfig)
		{
			ShapedRecipe netherstar = new ShapedRecipe(new ItemStack(Material.NETHER_STAR, 1));
			netherstar.shape(new String[]{"BNB", "NRN", "BNB"}).
				setIngredient('B', Material.DIAMOND_BLOCK).
				setIngredient('N', Material.NETHERRACK).
				setIngredient('R', Material.BLAZE_ROD);
			Bukkit.getServer().addRecipe(netherstar);
		}
		if (pearlconfig)
		{
			ShapedRecipe enderpearl = new ShapedRecipe(new ItemStack(Material.ENDER_PEARL, 1));
			enderpearl.shape(new String[]{"EEE", "E E", "EEE"}).setIngredient('E', Material.EMERALD);
			Bukkit.getServer().addRecipe(enderpearl);
		}
	}
	
	public void log(String s, Level l)
	{
		getLogger().log(l, s);
	}

	public void onDisable()
	{
		this.log("EndCraft has been disabled", Level.INFO);
		Bukkit.getServer().clearRecipes();
	}

	public String getTool() {
		return tool;
	}

	private void setTool(String tool) {
		this.tool = tool;
	}
}
