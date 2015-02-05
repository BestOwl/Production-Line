package goodtime.mod.Industrial.common.block;


import goodtime.mod.Industrial.common.core.CreativeTabGTI;

import javax.tools.Tool;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class Ore extends Block {

	public static Block IR = new Ore(Material.rock)
	.setBlockName("ҿ��ʯ")
	.setHardness(10)
	.setCreativeTab(CreativeTabGTI.tab)
	.setBlockTextureName("gti:blockOreIR")
	.setResistance(20);
	
	protected Ore(Material str) {
		super(str);
	}


    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
    	GameRegistry.registerBlock(IR, "iridiumore");	 
	}
	

    static {//ע�᷽��
    	Block.blockRegistry.addObject(1000, "iridiumore", IR);	
    }
    
    static {//ע������
    	LanguageRegistry.addName(IR, "ҿ��ʯ");
    }

    
    static {//ע����������
    	IR.setHarvestLevel("pickaxe" , 3);
    }
    
    
	
}
