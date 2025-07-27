package com.ind.mod.datagenerator;

import com.ind.mod.StoneWoodIndustry;
import com.ind.mod.block.ModBlocks;
import com.ind.mod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class RecipeProvider extends FabricRecipeProvider {
    public RecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ACACIA_BOOTS)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.ACACIA_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.ACACIA_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "acacia_boots"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ACACIA_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', Blocks.ACACIA_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.ACACIA_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "acacia_chestplate"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ACACIA_HELMET)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .input('#', Blocks.ACACIA_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.ACACIA_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "acacia_helmet"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.ACACIA_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.ACACIA_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.ACACIA_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "acacia_leggings"));
        
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.BIRCH_BOOTS)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.BIRCH_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.BIRCH_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "birch_boots"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.BIRCH_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', Blocks.BIRCH_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.BIRCH_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "birch_chestplate"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.BIRCH_HELMET)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .input('#', Blocks.BIRCH_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.BIRCH_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "birch_helmet"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.BIRCH_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.BIRCH_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.BIRCH_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "birch_leggings"));
        
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.JUNGLE_BOOTS)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.JUNGLE_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.JUNGLE_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "jungle_boots"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.JUNGLE_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', Blocks.JUNGLE_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.JUNGLE_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "jungle_chestplate"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.JUNGLE_HELMET)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .input('#', Blocks.JUNGLE_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.JUNGLE_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "jungle_helmet"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.JUNGLE_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.JUNGLE_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.JUNGLE_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "jungle_leggings"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CHERRY_BOOTS)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.CHERRY_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.CHERRY_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "cherry_boots"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CHERRY_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.CHERRY_PLANKS))
                .input('#', Blocks.CHERRY_PLANKS)
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "cherry_chestplate"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CHERRY_HELMET)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .input('#', Blocks.CHERRY_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.CHERRY_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "cherry_helmet"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CHERRY_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.CHERRY_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.CHERRY_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "cherry_leggings"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DARK_OAK_BOOTS)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.DARK_OAK_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.DARK_OAK_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "dark_oak_boots"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DARK_OAK_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', Blocks.DARK_OAK_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.DARK_OAK_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "dark_oak_chestplate"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DARK_OAK_HELMET)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .input('#', Blocks.DARK_OAK_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.DARK_OAK_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "dark_oak_helmet"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.DARK_OAK_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.DARK_OAK_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.DARK_OAK_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "dark_oak_leggings"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SPRUCE_BOOTS)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.SPRUCE_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.SPRUCE_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "spruce_boots"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SPRUCE_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', Blocks.SPRUCE_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.SPRUCE_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "spruce_chestplate"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SPRUCE_HELMET)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .input('#', Blocks.SPRUCE_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.SPRUCE_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "spruce_helmet"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SPRUCE_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.SPRUCE_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.SPRUCE_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "spruce_leggings"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.MANGROVE_BOOTS)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.MANGROVE_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.MANGROVE_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "mangrove_boots"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.MANGROVE_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', Blocks.MANGROVE_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.MANGROVE_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "mangrove_chestplate"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.MANGROVE_HELMET)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .input('#', Blocks.MANGROVE_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.MANGROVE_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "mangrove_helmet"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.MANGROVE_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.MANGROVE_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.MANGROVE_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "mangrove_leggings"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.OAK_BOOTS)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.OAK_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.OAK_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "oak_boots"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.OAK_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', Blocks.OAK_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.OAK_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "oak_chestplate"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.OAK_HELMET)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .input('#', Blocks.OAK_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.OAK_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "oak_helmet"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.OAK_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.OAK_PLANKS)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.OAK_PLANKS))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "oak_leggings"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SMOOTH_STONE_BOOTS)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.SMOOTH_STONE)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.SMOOTH_STONE))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "smooth_stone_boots"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SMOOTH_STONE_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.SMOOTH_STONE))
                .input('#', Blocks.SMOOTH_STONE)
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "smooth_stone_chestplate"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SMOOTH_STONE_HELMET)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .input('#', Blocks.SMOOTH_STONE)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.SMOOTH_STONE))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "smooth_stone_helmet"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SMOOTH_STONE_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.SMOOTH_STONE)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.SMOOTH_STONE))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "smooth_stone_leggings"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.STONE_BOOTS)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.STONE)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.STONE))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "stone_boots"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.STONE_CHESTPLATE)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .input('#', Blocks.STONE)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.STONE))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "stone_chestplate"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.STONE_HELMET)
                .pattern("###")
                .pattern("# #")
                .pattern("   ")
                .input('#', Blocks.STONE)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.STONE))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "stone_helmet"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.STONE_LEGGINGS)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .input('#', Blocks.STONE)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.STONE))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "stone_leggings"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SMOOTH_STONE_SWORD)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" I ")
                .input('#', Blocks.SMOOTH_STONE)
                .input('I', Items.STICK)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.SMOOTH_STONE))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "smooth_stone_sword"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SMOOTH_STONE_AXE)
                .pattern("## ")
                .pattern("#I ")
                .pattern(" I ")
                .input('#', Blocks.SMOOTH_STONE)
                .input('I', Items.STICK)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.SMOOTH_STONE))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "smooth_stone_axe"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.SMOOTH_STONE_PICKAXE)
                .pattern("###")
                .pattern(" I ")
                .pattern(" I ")
                .input('#', Blocks.SMOOTH_STONE)
                .input('I', Items.STICK)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.SMOOTH_STONE))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "smooth_stone_pickaxe"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.SMOOTH_STONE_HOE)
                .pattern("## ")
                .pattern(" I ")
                .pattern(" I ")
                .input('#', Blocks.SMOOTH_STONE)
                .input('I', Items.STICK)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.SMOOTH_STONE))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "smooth_stone_hoe"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.SMOOTH_STONE_SHOVEL)
                .pattern(" # ")
                .pattern(" I ")
                .pattern(" I ")
                .input('#', Blocks.SMOOTH_STONE)
                .input('I', Items.STICK)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.SMOOTH_STONE))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "smooth_stone_shovel"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_STAIRS)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .input('#', Blocks.SMOOTH_STONE)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.SMOOTH_STONE))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "smooth_stone_stairs"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.SMOOTH_STONE_PRESSURE_PLATE)
                .pattern("##")
                .input('#', Blocks.SMOOTH_STONE)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.SMOOTH_STONE))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "smooth_stone_pressure_plate"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.SMOOTH_STONE_BUTTON)
                .input(Items.SMOOTH_STONE)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Blocks.SMOOTH_STONE))
                .offerTo(exporter, Identifier.of(StoneWoodIndustry.MOD_ID, "smooth_stone_pressure_button"));
    }
}
