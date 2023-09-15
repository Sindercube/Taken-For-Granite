package com.sindercube.taken_for_granite.templates;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sindercube.taken_for_granite.registry.BlockData;
import com.sindercube.taken_for_granite.registry.BlockDataStorage;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SingleItemRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.StonecuttingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public abstract class BaseRecipe {
    public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;
    public abstract String getID();
    public abstract void build(Consumer<RecipeJsonProvider> exporter, BlockData data);
    public static ShapedRecipeJsonBuilder simpleShapedRecipe(BlockData data, List<String> pattern, Integer count) {
        Item material = Registries.ITEM.get(data.craftingMaterial);
        ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, data.block, count);
        pattern.forEach(builder::pattern);
        builder.input('#', material).criterion(
            FabricRecipeProvider.hasItem(data.item),
            FabricRecipeProvider.conditionsFromItem(material)
        );
        return builder;
    }

    public static void _simpleStonecuttingRecipe(Consumer<RecipeJsonProvider> exporter, BlockData data, Item item) {
//        System.out.println("item: " + item);
//        Item materialItem = Registries.ITEM.get(data.craftingMaterial);
//        System.out.println("material: " + materialItem);
//        RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, item, materialItem);

        if (!data.isVariant) {
            System.out.println("item: " + item);
            Item materialItem = Registries.ITEM.get(data.craftingMaterial);
            System.out.println("material: " + materialItem);
            RecipeProvider.offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, item, materialItem);
            //return SingleItemRecipeJsonBuilder.createStonecutting(ingredient, RecipeCategory.BUILDING_BLOCKS, data.item);
        }
        if (STORAGE.has(data.craftingMaterial.getPath())) {
            BlockData newMaterialData = STORAGE.get(data.craftingMaterial.getPath());
            simpleStonecuttingRecipe(exporter, newMaterialData, item);
        }
    }

    public static void simpleStonecuttingRecipe(Consumer<RecipeJsonProvider> exporter, BlockData data, Item item) {

        List<Item> items = new ArrayList<>();
        gatherIngredients(items, data);

        Ingredient ingredients = Ingredient.ofStacks(items.stream().map(ItemStack::new));

        SingleItemRecipeJsonBuilder builder = SingleItemRecipeJsonBuilder.createStonecutting(
            ingredients,
            RecipeCategory.BUILDING_BLOCKS,
            item
        )
        .criterion(
            RecipeProvider.hasItem(items.get(0)),
            RecipeProvider.conditionsFromItem(items.get(0))
        );
        String recipe = RecipeProvider.convertBetween(item, items.get(0));
        builder.offerTo(exporter, recipe + "_stonecutting");
    }

    public static void gatherIngredients(List<Item> items, BlockData data) {
        if (STORAGE.has(data.craftingMaterial.getPath())) {
            BlockData newData = STORAGE.get(data.craftingMaterial.getPath());
            gatherIngredients(items, newData);
            if (newData.isVariant) return;
        }
        items.add(Registries.ITEM.get(data.craftingMaterial));
    }

}
