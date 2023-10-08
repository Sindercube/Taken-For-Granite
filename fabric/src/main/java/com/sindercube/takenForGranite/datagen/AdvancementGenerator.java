package com.sindercube.takenForGranite.datagen;

import com.sindercube.takenForGranite.registry.BlockDataStorage;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

import static com.sindercube.takenForGranite.TFGMain.MOD_ID;

public class AdvancementGenerator extends FabricAdvancementProvider {
    public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;
    public AdvancementGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        new BlockRecipeAdvancement().accept(consumer);
    }

    public static class BlockRecipeAdvancement implements Consumer<Consumer<Advancement>> {
        @Override
        public void accept(Consumer<Advancement> consumer) {
            STORAGE.forEach(blockData -> {
                Advancement advancement = Advancement.Builder.create()
                    .criterion("has_item", InventoryChangedCriterion.Conditions.items(blockData.item))
                    // TODO check if the player has recipe after making recipe gen
                    //.criterion("has_recipe", RecipeUnlockedCriterion.create()
                    //        .matches(new ShapedRecipe(new Identifier(MODID, blockData.id.getPath())))
                    //)
                    .rewards(AdvancementRewards.Builder
                        .recipe(new Identifier(MOD_ID, blockData.getID().getPath()))
                    )
                    .build(new Identifier(MOD_ID, "recipes/building_blocks/" + blockData.getID().getPath()));
                consumer.accept(advancement);
            });
        }
    }
}