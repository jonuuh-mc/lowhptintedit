package co.uk.isxander.lowhptint.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class GuiHandler {

    private GuiScreen screen = null;

    public void open(GuiScreen screen) {
        this.screen = screen;
    }

    @SubscribeEvent
    public void tick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            if (screen != null) {
                Minecraft.getMinecraft().displayGuiScreen(screen);
                screen = null;
            }
        }
    }

}
