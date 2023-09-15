package com.sindercube.taken_for_granite.registry;

import com.google.gson.JsonObject;
import com.sindercube.taken_for_granite.templates.BaseModel;
import com.sindercube.taken_for_granite.templates.ModelTemplateStorage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Objects;

import static com.sindercube.taken_for_granite.TakenForGranite.MODID;
import static com.sindercube.taken_for_granite.registry.BlockDataStorage.ITEMS;

public class BlockData {
    public static ModelTemplateStorage MTS = ModelTemplateStorage.INSTANCE;
    public static BlockDataStorage STORAGE = BlockDataStorage.INSTANCE;

    public Identifier id;
    public String name;
    public String group = "stone";
    public String blockType = "polished";
    public String modelType = "cube";
    public String sound = "stone";
    public Float hardness = 1.5F;
    public Float strength = 6.0F;
    public Identifier craftingMaterial = new Identifier("minecraft:stone");

    public boolean isVariant = false;

    public BlockData parent;
    public Block block;
    public Item item;

    public BlockData(String id, JsonObject data) {
        if (data.has("id")) {
            this.id = new Identifier(MODID, data.get("id").getAsString());
        } else {
            this.id = new Identifier(MODID, id);
        }

        if (data.has("name")) {
            this.name = data.get("name").getAsString();
        } else {
            this.name = StringUtils.capitalize(this.id.getPath());
        }

        if (data.has("group")) this.group = data.get("group").getAsString();

        if (data.has("block_type")) this.blockType = data.get("block_type").getAsString();
        if (data.has("model_type")) this.modelType = data.get("model_type").getAsString();

        if (data.has("crafting_material")) this.craftingMaterial = new Identifier(data.get("crafting_material").getAsString());

        if (data.has("sound")) this.sound = data.get("sound").getAsString();
        if (data.has("hardness")) this.hardness = data.get("hardness").getAsFloat();
        if (data.has("strength")) this.strength = data.get("strength").getAsFloat();
    }
    public void addVariants(JsonObject data) {
        JsonObject finalData = data.deepCopy();
        data.get("variants").getAsJsonArray().forEach(variant -> {
            finalData.add("model_type", variant);
            finalData.add("block_type", variant);
            finalData.remove("variants");
            finalData.addProperty("crafting_material", this.id.toString());

            String name = data.get("name").getAsString();
            if (name.endsWith("s")) name = name.substring(0, name.length()-1);
            name += " " + StringUtils.capitalize(variant.getAsString());

            finalData.addProperty("name", name);

            String id = this.id.getPath();
            if (id.endsWith("s")) id = id.substring(0, id.length()-1);
            id += "_" + variant.getAsString();

            BlockData variantBlockData = new BlockData(id, finalData.getAsJsonObject());
            variantBlockData.parent = this;

            variantBlockData.isVariant = true;

            STORAGE.add(variantBlockData);
        });
    }
    public void genBlock() {
        BlockSoundGroup sound;
        try {
            sound = getSoundFromString(this.sound);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            sound = BlockSoundGroup.STONE;
        }

        FabricBlockSettings settings = FabricBlockSettings.create()
                .requiresTool()
                .strength(this.strength)
                .hardness(this.hardness)
                .sounds(sound);
        BaseModel model = MTS.get(this.modelType);

        if (this.parent == null) {
            this.block = model.block(settings);
        } else {
            System.out.println(this.parent.id);
            System.out.println(this.parent.block);
            this.block = model.blockFromParent(this.parent.block, settings);
        }
        this.item = new BlockItem(this.block, new FabricItemSettings());
        ITEMS.add(this.item);
    }

    public static BlockSoundGroup getSoundFromString(String sound) throws NoSuchFieldException, IllegalAccessException {
        sound = sound.toUpperCase();
        Field soundField = Objects.requireNonNull(BlockSoundGroup.class.getField(sound));
        soundField.setAccessible(true);
        //if (!soundField.canAccess(BlockSoundGroup.class)) throw new NoSuchFieldException();
        return (BlockSoundGroup)soundField.get(BlockSoundGroup.class);
    }
}
