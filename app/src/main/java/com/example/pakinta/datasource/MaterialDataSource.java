package com.example.pakinta.datasource;

import com.example.pakinta.model.MaterialModel;

import java.util.ArrayList;
import java.util.List;

public class MaterialDataSource {

    private ArrayList<MaterialModel> materials = new ArrayList<>();

    public ArrayList<MaterialModel> getMaterials(){
        ArrayList<MaterialModel> materialArraylist = new ArrayList<>();

        for (int i = 0; i < images.length; i++){
            MaterialModel materialModel = new MaterialModel(images[i], names[i]);
            materialArraylist.add(materialModel); // Add the materialModel to the list
        }
        return materialArraylist;
    }

    private final String[] images = {
            "https://i.pinimg.com/564x/29/24/b9/2924b9faa1034944172232db6e5346a6.jpg",
            "https://i.pinimg.com/564x/02/5b/fe/025bfe2e388b721815d0597524962f8e.jpg",
            "https://i.pinimg.com/564x/22/1c/49/221c49a09b967848e12e03502d793bd4.jpg",
            "https://i.pinimg.com/564x/1d/af/66/1daf66bb50e8e819d7a644c5cbf93d6b.jpg",
            "https://i.pinimg.com/564x/df/e3/85/dfe385d13b8c1423b7fe5bb6fc50ac1b.jpg",
            "https://i.pinimg.com/564x/8c/f2/5e/8cf25ef3a08a1e758fc3bac52d6998a5.jpg",
    };

    private final String[] names = {
            "Bata",
            "Kerikil",
            "Besi",
            "Genteng",
            "Granit",
            "Pasir",
    };
}

