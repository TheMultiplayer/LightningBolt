package org.team5763;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import net.sourceforge.yamlbeans.YamlException;
import net.sourceforge.yamlbeans.YamlReader;
import net.sourceforge.yamlbeans.YamlWriter;

public class ConfigurationManager {
    Map<String, String> settings;
    @SuppressWarnings("unchecked")
    public ConfigurationManager() {
        File f = new File("settings.yml");
        if (!f.exists()) {
            System.out.println("[config]   No config exists, creating one");
            createNewConfig();
        }
        try {
            YamlReader reader = new YamlReader(new FileReader("settings.yml"));
            settings = (Map<String, String>)reader.read();
        } catch (Exception e) {
            System.exit(4);
        }
    }
    private boolean createNewConfig() {
        try {
            PrintWriter writer = new PrintWriter("settings.yml", "UTF-8");
            writer.println("team-number: 0000");
            writer.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public int getTeamNumber() {
        return Integer.parseInt(settings.get("team-number"));
    }
    public void save() {
        PrintWriter fWriter;
        try {
            fWriter = new PrintWriter("settings.yml", "UTF-8");
            YamlWriter writer = new YamlWriter(fWriter);
            writer.write(settings);
            writer.close();
            fWriter.close();
        } catch (FileNotFoundException e1) {
            createNewConfig();
            save();
        } catch (UnsupportedEncodingException e1) {
            System.err.println("Unsupported configuration encoding!");
            System.exit(1);
        } catch (YamlException e) {
            System.err.println("Unknown YAML Error.");
            System.exit(1);
        }
    }
}
