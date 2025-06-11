package co.uk.isxander.lowhptint.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class LowHpTintCommand extends CommandBase
{
    private final GuiLowHpTint gui;

    public LowHpTintCommand(LowHpTintConfig config)
    {
        this.gui = new GuiLowHpTint(config);
    }

    @Override
    public String getCommandName()
    {
        return "lowhptint";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "/" + getCommandName();
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args)
    {
        if (!(sender.getCommandSenderEntity() instanceof EntityPlayerSP))
        {
            return;
        }

        MinecraftForge.EVENT_BUS.register(this);
    }

    // Need to wait until the start of the next tick to display GUI
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
        Minecraft.getMinecraft().displayGuiScreen(gui);
        MinecraftForge.EVENT_BUS.unregister(this);
    }
}
