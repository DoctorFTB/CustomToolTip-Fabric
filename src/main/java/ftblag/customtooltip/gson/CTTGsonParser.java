package ftblag.customtooltip.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.registry.Registry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CTTGsonParser {

    public static CTTGson.ToolTips cfg;
    public static Map<Item, List<String>> tooltips = new HashMap<>();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void parseFile(File file) {
        if (!file.exists()) {
            try {
                createDefault(file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        try {
            cfg = gson.fromJson(new BufferedReader(new FileReader(file)), CTTGson.ToolTips.class);
        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
            crashReport("Failed to load cfg!", e);
        }
        try {
            for (CTTGson.ToolTip tooltip : cfg.tooltips) {
                String[] name = tooltip.item.split(":");
                tooltips.put(Registry.ITEM.get(new Identifier(name[0], name[1])), tooltip.tooltip);
            }
        } catch (Exception e) {
            crashReport("Failed to parse cfg!", e);
        }
    }

    private static void createDefault(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }

        CTTGson.ToolTips cfg = new CTTGson.ToolTips(Arrays.asList(new CTTGson.ToolTip("minecraft:apple", Arrays.asList("Its apple!", "With custom tooltip!")), new CTTGson.ToolTip("minecraft:stone", Arrays.asList("Its stone!", "With custom tooltip!")), new CTTGson.ToolTip("minecraft:cobblestone", Arrays.asList("Its stone!", "With custom tooltip!"))));

        try (BufferedWriter fw = new BufferedWriter(new FileWriter(file))) {
            fw.write(gson.toJson(cfg));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void crashReport(String message, Throwable throwable) {
        throw new CrashException(new CrashReport(message, throwable));
    }
}
