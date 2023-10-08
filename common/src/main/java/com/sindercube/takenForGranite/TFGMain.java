package com.sindercube.takenForGranite;

import com.sindercube.takenForGranite.blockProperties.BlockPropertyStorage;
import com.sindercube.takenForGranite.blockProperties.properties.*;
import com.sindercube.takenForGranite.registry.BlockData;
import com.sindercube.takenForGranite.staticData.StaticContent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TFGMain {
    public static final String MOD_ID = "taken_for_granite";
    public static final String MOD_NAME = "Taken For Granite";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static BlockPropertyStorage BPS = BlockPropertyStorage.INSTANCE;
    public static void init() {
        BPS.add("cube", new CubeProperties());
        BPS.add("polished", new PolishedProperties());
        BPS.add("bricks", new BricksProperties());
        BPS.add("tiles", new TilesProperties());
        BPS.add("cut", new CutProperties());
        BPS.add("pillar", new PillarProperties());
        BPS.add("chiseled", new ChiseledProperties());
        BPS.add("slab", new SlabProperties());
        BPS.add("stairs", new StairsProperties());
        BPS.add("wall", new WallProperties());

        StaticContent.load(new Identifier(MOD_ID, "blocks"), new BlockData());
    }
}
