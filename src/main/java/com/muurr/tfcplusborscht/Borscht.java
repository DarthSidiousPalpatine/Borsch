package com.muurr.tfcplusborscht;

import net.minecraft.init.Blocks;

import com.dunk.tfc.CommonProxy;
import com.dunk.tfc.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Borscht.MODID, version = Borscht.VERSION, dependencies = "required-after:terrafirmacraftplus;")
public class Borscht
{
    public static final String MODID = "tfcplusborscht";
    public static final String VERSION = "1.1";

    @SidedProxy(clientSide = "com.muurr.tfcplusborscht.Proxy", serverSide = "com.muurr.tfcplusborscht.Proxy")
	public static Proxy proxy;
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ItemSetup.setup();
		proxy.registerFluids();
		proxy.registerItems();
	}
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	
		ItemSetup.setuprecipes();
    }
}
