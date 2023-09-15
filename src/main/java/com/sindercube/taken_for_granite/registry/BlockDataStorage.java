package com.sindercube.taken_for_granite.registry;

import net.minecraft.item.Item;

import java.util.*;
import java.util.function.Consumer;

public class BlockDataStorage {
    public static BlockDataStorage INSTANCE = new BlockDataStorage();
    public static List<Item> ITEMS = new LinkedList<>();

    public HashMap<String, BlockData> blockData;

    public BlockDataStorage() {
        this.blockData = new LinkedHashMap<>();
    }
    public void add(BlockData blockData) {
        this.blockData.put(blockData.id.getPath(), blockData);
    }
    public void forEach(Consumer<BlockData> action) {
        this.blockData.values().forEach(action);
    }
    public boolean has(String id) {
        return this.blockData.containsKey(id);
    }
    public BlockData get(String id) {
        return this.blockData.get(id);
    }
    public void clear() {
        this.blockData.clear();
    }
}
