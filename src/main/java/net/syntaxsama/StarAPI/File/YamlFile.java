package net.syntaxsama.StarAPI.File;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class YamlFile {
    private final File file;
    private Map<String, Object> data;
    private final Yaml yaml;

    public YamlFile(File pluginsFolder, String fileName) {
        this.file = new File(pluginsFolder, fileName.endsWith(".yml") ? fileName : fileName + ".yml");

        // Set YAML options for proper formatting
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK); // Enforce indentation
        options.setPrettyFlow(true); // Makes YAML easier to read
        options.setIndent(2); // Standard indentation
        options.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN); // Keeps numbers raw, no !!int nonsense
        options.setAllowUnicode(true);

        this.yaml = new Yaml(options);

        load();
    }

    private void load() {
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                data = new LinkedHashMap<>(); // Use LinkedHashMap to maintain order
                save();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        try (InputStream input = new FileInputStream(file)) {
            this.data = yaml.load(input);
            if (this.data == null) {
                this.data = new LinkedHashMap<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object get(String path) {
        String[] keys = path.split("\\.");
        Map<String, Object> current = data;

        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            Object value = current.get(key);
            if (i == keys.length - 1) {
                return value;
            }
            if (value instanceof Map) {
                current = (Map<String, Object>) value;
            } else {
                return null;
            }
        }
        return null;
    }

    public void set(String path, Object value) {
        String[] keys = path.split("\\.");
        Map<String, Object> current = data;

        for (int i = 0; i < keys.length - 1; i++) {
            String key = keys[i];
            if (!current.containsKey(key) || !(current.get(key) instanceof Map)) {
                current.put(key, new LinkedHashMap<>());
            }
            current = (Map<String, Object>) current.get(key);
        }

        current.put(keys[keys.length - 1], value);
        save();
    }

    private void save() {
        try (Writer writer = new FileWriter(file)) {
            yaml.dump(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean exists() {
        return file.exists();
    }

    public String getFilePath() {
        return file.getAbsolutePath();
    }
}
