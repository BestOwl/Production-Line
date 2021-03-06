/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 GoodTime Studio <https://github.com/GoodTimeStudio>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mcgoodtime.productionline.common.crafting;

import com.mcgoodtime.productionline.common.init.PLBlocks;
import com.mcgoodtime.productionline.common.init.PLItems;
import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.item.IC2Items;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import ic2.core.util.StackUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.List;
import java.util.Map;

/**
 * The class for loading all the recipes of GoodTime-Industrial. Migrated from old loaders.
 *
 * @author liach
 */
public class PLRecipes {
    private static final float XP = 2F;

    /** Load recipes of GoodTime-Industrial.*/
    public static void init() {
        disableRecipes();
        registerVanillaRecipes();
        registerSmelting();
        registerIC2Recipes();
        registerPLAdvRecipes();
        registerOreDictionaryRecipes();
    }

    /** Disable recipes. */
    private static void disableRecipes() {
        disableRecipes(IC2Items.getItem("massFabricator"));
        disableRecipes(IC2Items.getItem("iridiumPlate"));
    }

    /** Vanilla recipe registry. */
    private static void registerVanillaRecipes() {
        GameRegistry.addRecipe(
                PLBlocks.carbonizeFurnace,
                "ABA",
                "CDC",
                "EEE",
                'A', IC2Items.getItem("electronicCircuit"),
                'B', PLBlocks.airBrakeCasing,
                'C', PLItems.airBrakeUnit,
                'D', IC2Items.getItem("electroFurnace"),
                'E', IC2Items.getItem("plateiron")
        );
        GameRegistry.addRecipe(
                PLItems.roller,
                " A",
                "ABA",
                " A",
                'A', Items.iron_ingot,
                'B', IC2Items.getItem("ironFence")
        );
        GameRegistry.addRecipe(
                PLItems.heatInsulationMaterial,
                "AAA",
                "AAA",
                "BBB",
                'A', IC2Items.getItem("rubber"),
                'B', IC2Items.getItem("advIronIngot"));

        GameRegistry.addRecipe(
                new ItemStack(PLBlocks.airBrakeCasing),
                "AAA",
                "BBB",
                "ACA",
                'A', IC2Items.getItem("plateiron"),
                'B', PLItems.airBrakeUnit,
                'C', Items.bucket
        );
        GameRegistry.addRecipe(
                PLItems.airBrakeUnit,
                "AY ",
                "YY ",
                " YY",
                'A', IC2Items.getItem("advIronIngot"),
                'Y', IC2Items.getItem("rubber")
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.diamondApple, 1, 0),
                "DDD",
                "DAD",
                "DDD",
                'D', PLItems.denseDiamondPlate,
                'A', Items.apple
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.diamondApple, 1, 1),
                "DDD",
                "DAD",
                "DDD",
                'D', PLItems.diamondApple,
                'A', Blocks.diamond_block
        );
        GameRegistry.addShapelessRecipe(
                PLItems.dustIridium,
                PLItems.smallDustIridium,
                PLItems.smallDustIridium,
                PLItems.smallDustIridium,
                PLItems.smallDustIridium,
                PLItems.smallDustIridium,
                PLItems.smallDustIridium,
                PLItems.smallDustIridium,
                PLItems.smallDustIridium,
                PLItems.smallDustIridium
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.ironTreetap),
                " X ",
                "XXX",
                "X  ",
                'X', Items.iron_ingot
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.bronzeTreetap),
                " X ",
                "XXX",
                "X  ",
                'X', IC2Items.getItem("bronzeIngot")
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.leadTreetap),
                " X ",
                "XXX",
                "X  ",
                'X', IC2Items.getItem("leadIngot")
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.refinedIronTreetap),
                " X ",
                "XXX",
                "X  ",
                'X', IC2Items.getItem("advIronIngot")
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.advancedAlloyTreetap),
                " X ",
                "XXX",
                "X  ",
                'X', IC2Items.getItem("advancedAlloy")
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.carbonTreetap),
                " XY",
                "XXX",
                "XY ",
                'X', IC2Items.getItem("carbonPlate"),
                'Y', IC2Items.getItem("carbonMesh")
        );
        GameRegistry.addRecipe(
                PLItems.getItems(PLItems.redstoneModule, 2),
                "ABA",
                "BCB",
                "ABA",
                'A', Items.redstone,
                'B', IC2Items.getItem("tinCableItem"),
                'C', IC2Items.getItem("plateiron")
        );

        GameRegistry.addRecipe(
                PLItems.getItems(PLItems.electronicCircuitCore, 3),
                "AAA",
                "BBB",
                "BBB",
                'A', IC2Items.getItem("plateiron"),
                'B', IC2Items.getItem("tinCableItem")
        );
        GameRegistry.addRecipe(
                PLItems.getItems(PLItems.electronicCircuitControl, 2),
                " A ",
                "BCB",
                'A', Blocks.lever,
                'B', IC2Items.getItem("tinCableItem"),
                'C', Blocks.stone_button
        );
        GameRegistry.addRecipe(
                PLItems.lazuliModule,
                "ABA",
                "CDC",
                "ABA",
                'A', IC2Items.getItem("goldCableItem"),
                'B', PLItems.heatInsulationPlate,
                'C', Blocks.lapis_block,
                'D', PLItems.redstoneModule
        );
        GameRegistry.addRecipe(
                PLItems.pulseElectronicCircuitCore,
                "AAA",
                "AAA",
                "BC ",
                'A', PLItems.redstoneModule,
                'B', PLItems.heatInsulationPlate,
                'C', PLItems.electronicCircuitCore
        );
        GameRegistry.addRecipe(
                PLItems.pulseElectronicCircuitControl,
                "AAA",
                "BAC",
                "BDC",
                'A', PLItems.redstoneModule,
                'B', PLItems.pulseElectronicCircuitCore,
                'C', PLItems.heatInsulationPlate,
                'D', PLItems.electronicCircuitControl
        );
        GameRegistry.addRecipe(
                PLItems.cyclotronParticleAccelerator,
                "A A",
                "BCB",
                "ADA",
                'A', IC2Items.getItem("glassFiberCableItem"),
                'B', PLItems.redstoneModule,
                'C', PLItems.lazuliModule,
                'D', IC2Items.getItem("teslaCoil")
        );
        GameRegistry.addShapelessRecipe(
                PLItems.calculateUnit,
                new ItemStack(Blocks.stone_button),
                new ItemStack(Blocks.stone_button),
                new ItemStack(Blocks.stone_button),
                new ItemStack(Blocks.stone_button),
                new ItemStack(Blocks.stone_button),
                new ItemStack(Blocks.stone_button),
                new ItemStack(Blocks.stone_button),
                new ItemStack(Blocks.stone_button),
                new ItemStack(Blocks.stone_button)
        );
        GameRegistry.addShapelessRecipe(
                PLItems.calculateChunk,
                PLItems.calculateUnit,
                PLItems.calculateUnit,
                PLItems.calculateUnit,
                PLItems.calculateUnit,
                PLItems.calculateUnit,
                PLItems.calculateUnit,
                PLItems.calculateUnit,
                PLItems.calculateUnit,
                PLItems.calculateUnit
        );
        GameRegistry.addShapelessRecipe(
                PLItems.calculateArray,
                PLItems.calculateChunk,
                PLItems.calculateChunk,
                PLItems.calculateChunk,
                PLItems.calculateChunk,
                PLItems.calculateChunk,
                PLItems.calculateChunk,
                PLItems.calculateChunk,
                PLItems.calculateChunk,
                PLItems.calculateChunk
        );
        GameRegistry.addShapelessRecipe(
                PLItems.floatPointCalculationsRegion,
                PLItems.calculateArray,
                PLItems.calculateArray,
                PLItems.calculateArray,
                PLItems.calculateArray,
                PLItems.calculateArray,
                PLItems.calculateArray,
                PLItems.calculateArray,
                PLItems.calculateArray,
                PLItems.calculateArray
        );
        GameRegistry.addRecipe(
                PLItems.parallelSpaceConverter,
                "A A",
                " B ",
                "A A",
                'A', PLItems.floatPointCalculationsRegion,
                'B', PLItems.enderCalculationCrystal
        );
        GameRegistry.addRecipe(
                PLItems.uuMatterCore,
                "ABA",
                "CDC",
                "ABA",
                'A', PLItems.cyclotronParticleAccelerator,
                'B', PLItems.lazuliModule,
                'C', PLItems.pulseElectronicCircuitControl,
                'D', PLItems.parallelSpaceConverter
        );
        GameRegistry.addRecipe(
                IC2Items.getItem("massFabricator"),
                "ACA",
                "BDB",
                "ACA",
                'A', PLItems.lazuliModule,
                'B', IC2Items.getItem("teleporter"),
                'C', PLItems.pulseElectronicCircuitControl,
                'D', PLItems.uuMatterCore
        );
        GameRegistry.addRecipe(
                PLItems.obsidianMechanicalFrame,
                "AAA",
                "A A",
                "AAA",
                'A', PLItems.obsidianPlateGravityField
        );
        GameRegistry.addRecipe(
                PLItems.obsidianMechanicalCasing,
                "ABA",
                "ACA",
                'A', PLItems.pulseElectronicCircuitCore,
                'B', IC2Items.getItem("advancedMachine"),
                'C', PLItems.obsidianMechanicalFrame
        );
        GameRegistry.addRecipe(
                PLItems.enderCalculationCrystal,
                "AAA",
                "BCB",
                "BCB",
                'A', IC2Items.getItem("lapotronCrystal"),
                'B', PLItems.calculateArray,
                'C', Items.ender_pearl
        );
        GameRegistry.addRecipe(
                PLItems.millTeeth,
                "ABC",
                'A', Items.flint,
                'B', Blocks.stone,
                'C', Blocks.brick_block
        );
        GameRegistry.addRecipe(
                PLItems.millWheel,
                "AAA",
                "ABA",
                "AAA",
                'A', PLItems.millTeeth,
                'B', IC2Items.getItem("plateiron")
        );
        GameRegistry.addRecipe(
                IC2Items.getItem("iridiumPlate"),
                "ABA",
                "BCB",
                "ABA",
                'A', PLItems.ingotIridium,
                'B', IC2Items.getItem("advancedAlloy"),
                'C', PLItems.denseDiamondPlate
        );
        GameRegistry.addRecipe(
                PLItems.carbonTube,
                "AB",
                "B",
                'A', Items.redstone,
                'B', IC2Items.getItem("coalDust")
        );
        GameRegistry.addRecipe(
                PLItems.rigidPaper,
                "A",
                "B",
                "A",
                'A', Items.paper,
                'B', Items.sugar
        );
        GameRegistry.addShapelessRecipe(
                new ItemStack(Items.book),
                PLItems.rigidPaper,
                PLItems.rigidPaper,
                Items.leather
        );
        GameRegistry.addRecipe(
                PLItems.getItems(PLItems.rigidPaperPack, 2),
                " A ",
                "A A",
                " A ",
                'A', PLItems.rigidPaper
        );
        GameRegistry.addRecipe(
                PLBlocks.heatDryer,
                "ABA",
                "CDC",
                "EFE",
                'A', IC2Items.getItem("plateiron"),
                'B', PLItems.electronicCircuitControl,
                'C', PLItems.roller,
                'D', Blocks.furnace,
                'E', PLItems.redstoneModule,
                'F', IC2Items.getItem("machine")
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.ceu),
                "ABA",
                "CDC",
                "CDC",
                'A', IC2Items.getItem("copperCableItem"),
                'B', IC2Items.getItem("AshesDust"),
                'C', IC2Items.getItem("casingtin"),
                'D', PLItems.carbonTube
        );
        GameRegistry.addRecipe(
                PLItems.advSolarLensUnit,
                "A A",
                "A A",
                " A ",
                'A', Blocks.glass
        );
        GameRegistry.addRecipe(
                PLItems.advSolarLensGroup,
                "A A",
                "A A",
                " A ",
                'A', PLItems.advSolarLensUnit
        );
        GameRegistry.addRecipe(
                PLItems.advSolarLensCluster,
                "A A",
                "A A",
                " A ",
                'A', PLItems.advSolarLensGroup
        );
    }

    /** Smelting registry. */
    private static void registerSmelting() {
        GameRegistry.addSmelting(PLBlocks.oreIridium, PLItems.ingotIridium, XP);
        GameRegistry.addSmelting(PLItems.cleanedCrushedIridium, PLItems.ingotIridium, XP);
        GameRegistry.addSmelting(PLItems.dustIridium, PLItems.ingotIridium, XP);
        GameRegistry.addSmelting(PLItems.crushedIridium, PLItems.ingotIridium, XP);
        GameRegistry.addSmelting(IC2Items.getItem("iridiumOre"), PLItems.ingotIridium, XP);
    }

    /** ic2 recipe registry. */
    private static void registerIC2Recipes() {
        Recipes.compressor.addRecipe(
                new RecipeInputItemStack(PLItems.getItems(PLItems.smallCompressedWaterHyacinth, 8)),
                null,
                PLBlocks.compressedWaterHyacinth
        );
        Recipes.compressor.addRecipe(
                new RecipeInputItemStack(new ItemStack(PLBlocks.waterHyacinth, 8)),
                null,
                PLItems.smallCompressedWaterHyacinth
        );
        Recipes.metalformerRolling.addRecipe(
                new RecipeInputItemStack(new ItemStack(Items.diamond)),
                null,
                PLItems.diamondPlate
        );
        Recipes.metalformerRolling.addRecipe(
                new RecipeInputItemStack(PLItems.heatInsulationMaterial),
                null,
                PLItems.heatInsulationPlate
        );
        Recipes.compressor.addRecipe(
                new RecipeInputItemStack(PLItems.getItems(PLItems.diamondPlate, 9)),
                null,
                PLItems.denseDiamondPlate
        );
        Recipes.compressor.addRecipe(
                new RecipeInputItemStack(PLItems.getItems(PLItems.smallDustIridium, 8)),
                null,
                PLItems.ingotIridium
        );
        Recipes.macerator.addRecipe(
                new RecipeInputItemStack(new ItemStack(PLBlocks.oreIridium)),
                null,
                PLItems.getItems(PLItems.crushedIridium, 2)
        );
        Recipes.macerator.addRecipe(
                new RecipeInputItemStack(PLItems.ingotIridium),
                null,
                PLItems.dustIridium
        );
        Recipes.compressor.addRecipe(
                new RecipeInputItemStack(IC2Items.getItem("diamondDust"), 3),
                null,
                PLItems.carbonCrystal
        );
        Recipes.compressor.addRecipe(
                new RecipeInputItemStack(IC2Items.getItem("denseplateobsidian"), 8),
                null,
                PLItems.obsidianPlateGravityField
        );
        NBTTagCompound oreWash = new NBTTagCompound();
        oreWash.setInteger("amount", 1000);
        Recipes.oreWashing.addRecipe(
                new RecipeInputItemStack(PLItems.crushedIridium),
                oreWash,
                PLItems.cleanedCrushedIridium,
                StackUtil.copyWithSize(IC2Items.getItem("smallTinDust"), 2)
        );
        Recipes.centrifuge.addRecipe(
                new RecipeInputItemStack(PLItems.cleanedCrushedIridium),
                null,
                PLItems.dustIridium,
                PLItems.getItems(PLItems.smallDustIridium, 2)
        );
        Recipes.cannerBottle.addRecipe(
                new RecipeInputItemStack(PLItems.rigidPaperPack),
                new RecipeInputItemStack(new ItemStack(PLItems.salt, 9)),
                new ItemStack(PLItems.packagedSalt)
        );
    }

    /** ProductioneLine adv recipe registry. */
    private static void registerPLAdvRecipes() {
        PLAdvShapedRecipe.addShapedRecipe(
                PLBlocks.cseu,
                "ABA",
                "CCC",
                "CCC",
                'A', IC2Items.getItem("insulatedGoldCableItem"),
                'B', IC2Items.getItem("advancedMachine"),
                'C', PLItems.ceu
        );
        PLAdvShapedRecipe.addShapedRecipe(
                PLBlocks.evsu,
                "ACA",
                "ABD",
                "ACA",
                'A', IC2Items.getItem("evTransformer"),
                'B', IC2Items.getItem("lapotronCrystal"),
                'C', IC2Items.getItem("mfsUnit"),
                'D', PLItems.pulseElectronicCircuitControl
        );
        PLAdvShapedRecipe.addShapedRecipe(
                PLBlocks.parallelSpaceSU,
                "ABA",
                "CDC",
                "EFE",
                'A', PLItems.obsidianPlateGravityField,
                'B', PLItems.cyclotronParticleAccelerator,
                'C', PLBlocks.evsu,
                'D', PLItems.obsidianMechanicalCasing,
                'E', IC2Items.getItem("evTransformer"),
                'F', PLItems.enderCalculationCrystal
        );
    }

    /** OreDictionary recipe registry. */
    private static void registerOreDictionaryRecipes() {
        addOreDictionaryRecipes(
                new ItemStack(PLBlocks.advSolar),
                "A",
                "B",
                "C",
                'A', "advSolarLens",
                'B', IC2Items.getItem("lvTransformer"),
                'C', IC2Items.getItem("solarPanel")
        );
        addOreDictionaryRecipes(
                new ItemStack(PLBlocks.advSolar),
                "A",
                "B",
                "C",
                'A', "advSolarLens",
                'B', IC2Items.getItem("mvTransformer"),
                'C', IC2Items.getItem("solarPanel")
        );
    }

    private static void addOreDictionaryRecipes(ItemStack result, Object... recipe) {
        GameRegistry.addRecipe(new ShapedOreRecipe(result, recipe));
    }

    /**
     * Disable recipes.
     * @param itemStack Disable all recipes of this item.
     */
    @SuppressWarnings("unchecked")
    private static void disableRecipes(ItemStack itemStack) {
        List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();
        for (int i = 0; i < recipeList.size(); i++) {
            IRecipe iRecipe = recipeList.get(i);
            ItemStack recipesResult = iRecipe.getRecipeOutput();
            if (ItemStack.areItemStacksEqual(itemStack, recipesResult)) {
                recipeList.remove(i--);
            }
        }
    }

    @SuppressWarnings({"unchecked", "SuspiciousMethodCalls"})
    public static void disableSmelting(ItemStack itemStack) {
        Map<List<Integer>, ItemStack> smelting = FurnaceRecipes.smelting().getSmeltingList();
        smelting.remove(itemStack);
    }

}
