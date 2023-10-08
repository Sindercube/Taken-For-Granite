package com.sindercube.takenForGranite.staticData;

import net.minecraft.util.Identifier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class StaticDataItem {
    private final Identifier id;
    private final Path path;

    public StaticDataItem(Identifier id, Path path) {
        this.id = id;
        this.path = path;
    }

    public InputStream createInputStream() throws IOException {
        if (!Files.exists(path)) throw new FileNotFoundException();
        return Files.newInputStream(path, StandardOpenOption.READ);
    }

    public Identifier getIdentifier() {
        return id;
    }
}
