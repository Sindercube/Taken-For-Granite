package com.sindercube.taken_for_granite.templates;

import java.util.HashMap;

public class ModelTemplateStorage {
    public static String DEFAULT = "cube";
    public static ModelTemplateStorage INSTANCE = new ModelTemplateStorage();
    public HashMap<String, BaseModel> modelTemplates;
    public ModelTemplateStorage() {
        this.modelTemplates = new HashMap<>();
    }
    public void add(BaseModel baseModel) {
        this.modelTemplates.put(baseModel.getID(), baseModel);
    }
    public boolean contains(String id) {
        return this.modelTemplates.containsKey(id);
    }
    public BaseModel get(String id) {
        return this.modelTemplates.getOrDefault(id, this.modelTemplates.get(DEFAULT));
    }
}
