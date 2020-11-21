package co.uk.isxander.lowhptint.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;

import java.io.File;

public class LowHpTintConfig {

    private Configuration config = new Configuration(new File(Loader.instance().getConfigDir(), "lowHpTint.cfg"));

    private boolean enabled;
    private int health;
    private int r;
    private int g;
    private int b;
    private int speed;

    public void load() {
        this.config.load();
        enabled = config.get("client", "enabled", true).getBoolean();
        health = config.get("client", "health", 5).getInt();
        r = config.get("client", "red", 200).getInt();
        g = config.get("client", "green", 0).getInt();
        b = config.get("client", "blue", 0).getInt();
        speed = config.get("client", "speed", 5).getInt();
        if (config.hasChanged())
            config.save();
    }

    public void save() {
        config.get("client", "enabled", true).set(enabled);
        config.get("client", "health", 5).set(health);
        config.get("client", "red", 200).set(r);
        config.get("client", "green", 0).set(g);
        config.get("client", "blue", 0).set(b);
        config.get("client", "speed", 5).set(speed);
        if (config.hasChanged())
            config.save();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getRed() {
        return r;
    }

    public void setRed(int r) {
        this.r = r;
    }

    public int getGreen() {
        return g;
    }

    public void setGreen(int g) {
        this.g = g;
    }

    public int getBlue() {
        return b;
    }

    public void setBlue(int b) {
        this.b = b;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
