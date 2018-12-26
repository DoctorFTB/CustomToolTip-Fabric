package ftblag.customtooltip;

import ftblag.customtooltip.gson.CTTGsonParser;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.FabricLoader;

import java.io.File;

public class CustomToolTip implements ModInitializer {
    @Override
    public void onInitialize() {
        CTTGsonParser.parseFile(new File(FabricLoader.INSTANCE.getConfigDirectory(), "customtooltip.json"));
    }
}
