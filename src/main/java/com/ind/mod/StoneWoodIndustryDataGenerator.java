package com.ind.mod;

import com.ind.mod.datagenerator.EnUsLanguageGenerator;
import com.ind.mod.datagenerator.ModelGenerator;
import com.ind.mod.datagenerator.RecipeProvider;
import com.ind.mod.datagenerator.ZhCnLanguageGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class StoneWoodIndustryDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(EnUsLanguageGenerator::new);
		pack.addProvider(ZhCnLanguageGenerator::new);
		pack.addProvider(ModelGenerator::new);
		pack.addProvider(RecipeProvider::new);
	}
}
