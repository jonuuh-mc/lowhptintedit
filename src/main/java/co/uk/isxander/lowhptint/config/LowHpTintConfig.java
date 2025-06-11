package co.uk.isxander.lowhptint.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;

import java.io.File;

public class LowHpTintConfig
{
    private final Configuration config = new Configuration(new File(Loader.instance().getConfigDir(), "lowHpTint.cfg"));

    private boolean enabled;
    private int r;
    private int g;
    private int b;
    private int minHealth;
    private int maxHealth;
    private int easeType;
    private int easeScale;

    public void load()
    {
        config.load();

        enabled = config.get("client", "enabled", true).getBoolean();

        r = config.get("client", "red", 180).getInt();
        g = config.get("client", "green", 0).getInt();
        b = config.get("client", "blue", 0).getInt();

        minHealth = config.get("client", "minHealth", 1).getInt();
        maxHealth = config.get("client", "maxHealth", 20).getInt();

        easeType = config.get("client", "easeType", 3).getInt();
        easeScale = config.get("client", "easeScale", 3).getInt();

        if (config.hasChanged())
        {
            config.save();
        }
    }

    public void save()
    {
        config.get("client", "enabled", true).set(enabled);

        config.get("client", "red", 180).set(r);
        config.get("client", "green", 0).set(g);
        config.get("client", "blue", 0).set(b);

        config.get("client", "minHealth", 1).set(minHealth);
        config.get("client", "maxHealth", 20).set(maxHealth);

        config.get("client", "easeType", 3).set(easeType);
        config.get("client", "easeScale", 3).set(easeScale);

        if (config.hasChanged())
        {
            config.save();
        }
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public int getRed()
    {
        return r;
    }

    public void setRed(int r)
    {
        this.r = r;
    }

    public int getGreen()
    {
        return g;
    }

    public void setGreen(int g)
    {
        this.g = g;
    }

    public int getBlue()
    {
        return b;
    }

    public void setBlue(int b)
    {
        this.b = b;
    }

    public int getMinHealth()
    {
        return minHealth;
    }

    public void setMinHealth(int minHealth)
    {
        this.minHealth = minHealth;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
    }

    public int getEaseType()
    {
        return easeType;
    }

    public void setEaseType(int easeType)
    {
        this.easeType = easeType;
    }

    public int getEaseScale()
    {
        return easeScale;
    }

    public void setEaseScale(int easeScale)
    {
        this.easeScale = easeScale;
    }
}
