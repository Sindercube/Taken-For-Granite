package com.sindercube.takenForGranite.registry;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.sindercube.takenForGranite.TFGMain;
import com.sindercube.takenForGranite.blockProperties.BlockProperties;
import com.sindercube.takenForGranite.blockProperties.BlockPropertyStorage;
import com.sindercube.takenForGranite.staticData.ContentData;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class BlockData extends ContentData {

    public static BlockPropertyStorage BPS = BlockPropertyStorage.INSTANCE;

    public static BlockDataStorage BDS = BlockDataStorage.INSTANCE;

    public BlockData() {}

    private Identifier id;
    public Identifier getID() {
        return this.id;
    }

    private String name;
    public String getName() {
        return this.name;
    }


    public String group = "stone"; // TODO remove all default values
    public String getGroup() {
        return group;
    }

    public String type = "polished";
    public String getType() {
        return this.type;
    }

    public String model = "cube";
    public String getModel() {
        return this.model;
    }

    public String sound = "stone";
    public String getSound() {
        return this.sound;
    }

    public Float hardness = 1.5F;
    public Float getHardness() {
        return this.hardness;
    }

    public Float strength = 6.0F;
    public Float getStrength() {
        return this.strength;
    }

    public Identifier craftingMaterial = new Identifier("minecraft:stone");
    public Identifier getCraftingMaterial() {
        return this.craftingMaterial;
    }

    public List<Map<String, String>> variants = new ArrayList<>();
    public List<Map<String, String>> getVariants() {
        return this.variants;
    }

    @Override
    public BlockData register(Identifier file, JsonObject json) {
        BDS.add(this);

        if (!this.variants.isEmpty()) {
            for (Map<String, String> variant : this.variants) {
                addVariant(json, variant);
            }
        }

        return this;
    }


    public Codec<BlockData> getCodec() {
        return RecordCodecBuilder.create(instance -> instance.group(
                Identifier.CODEC.fieldOf("id").forGetter(BlockData::getID),
                Codec.STRING.optionalFieldOf("name", "").forGetter(BlockData::getName),
                Codec.STRING.optionalFieldOf("group", "stone").forGetter(BlockData::getGroup),
                Codec.STRING.optionalFieldOf("type", "polished").forGetter(BlockData::getType),
                Codec.STRING.optionalFieldOf("model", "cube").forGetter(BlockData::getModel),
                Codec.STRING.optionalFieldOf("sound", "stone").forGetter(BlockData::getSound),
                Codec.FLOAT.optionalFieldOf("hardness", 1.5F).forGetter(BlockData::getHardness),
                Codec.FLOAT.optionalFieldOf("strength", 6.0F).forGetter(BlockData::getStrength),
                Identifier.CODEC.optionalFieldOf("crafting_material", new Identifier("minecraft:stone")).forGetter(BlockData::getCraftingMaterial),
                Codec.unboundedMap(Codec.STRING, Codec.STRING).listOf().optionalFieldOf("variants", new ArrayList<>()).forGetter(BlockData::getVariants)
        ).apply(instance, BlockData::new));
    }

    public boolean isVariant = false;
    public BlockData parent;
    public Block block;
    public Item item;

    public BlockData(Identifier id, String name, String group, String blockType, String modelType, String sound, Float hardness, Float strength, Identifier craftingMaterial, List<Map<String, String>> variants) {
        this.id = id;

        if (name.isBlank()) this.name = titlecase(this.id.getPath());
        else this.name = name;

        this.group = group;
        this.type = blockType;
        this.model = modelType;
        this.sound = sound;
        this.hardness = hardness;
        this.strength = strength;
        this.craftingMaterial = craftingMaterial;
        this.variants = variants;
    }

    public static String titlecase(String string) {
        return Arrays
            .stream(string.split("_"))
            .map(StringUtils::capitalize)
            .collect(Collectors.joining(" "));
    }

    public void genBlock() {
        BlockSoundGroup sound;
        try {
            sound = getSoundFromString(this.sound);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            sound = BlockSoundGroup.STONE;
        }

        AbstractBlock.Settings settings = AbstractBlock.Settings.create()
                .requiresTool()
                .strength(this.strength)
                .hardness(this.hardness)
                .sounds(sound);

        BlockProperties model = BPS.get(this.model);

        if (this.parent == null) {
            this.block = model.block(settings);
        } else {
            System.out.println("Parent:" + this.parent);
            this.block = model.blockFromParent(this.parent.block, settings);
        }
        this.item = new BlockItem(this.block, new Item.Settings());
        BDS.itemList.add(this.item);
    }

    public void addVariant(JsonObject source, Map<String, String> variantData) {
        JsonObject variantJson = source.deepCopy();
        variantJson.remove("variants");
        variantJson.remove("name");
        variantJson.addProperty("id", variantData.get("id"));
        variantJson.addProperty("type", variantData.get("type"));
        variantJson.addProperty("model", variantData.get("type"));

        variantJson.addProperty("crafting_material", this.id.toString());

        BlockData finalData = this.getCodec().parse(JsonOps.INSTANCE, variantJson)
                .resultOrPartial(TFGMain.LOGGER::error).orElseThrow();
        BlockData blockData = finalData.register(new Identifier(variantData.get("id")), variantJson);

        blockData.parent = this;
    }

    public static BlockSoundGroup getSoundFromString(String sound) throws NoSuchFieldException, IllegalAccessException {
        sound = sound.toUpperCase();
        Field soundField = Objects.requireNonNull(BlockSoundGroup.class.getField(sound));
        soundField.setAccessible(true);
        return (BlockSoundGroup)soundField.get(BlockSoundGroup.class);
    }

}
