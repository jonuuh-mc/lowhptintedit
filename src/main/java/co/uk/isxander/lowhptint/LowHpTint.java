package co.uk.isxander.lowhptint;

import co.uk.isxander.lowhptint.config.LowHpTintCommand;
import co.uk.isxander.lowhptint.config.LowHpTintConfig;
import co.uk.isxander.lowhptint.event.VignetteRenderer;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

// this whole codebase is a disaster but oh well
@Mod(name = LowHpTint.NAME, modid = LowHpTint.MODID, version = LowHpTint.VERSION, acceptedMinecraftVersions = "[1.8.9]", clientSideOnly = true)
public class LowHpTint
{
    public static final String NAME = "LowHpTint";
    public static final String MODID = "lowhptint";
    public static final String VERSION = "1.1";
    private LowHpTintConfig config;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        config = new LowHpTintConfig();
        config.load();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        ClientCommandHandler.instance.registerCommand(new LowHpTintCommand(config));
        MinecraftForge.EVENT_BUS.register(new VignetteRenderer(config));
    }
}
