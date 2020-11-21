package co.uk.isxander.lowhptint.gui.impl;

import co.uk.isxander.lowhptint.LowHpTint;
import co.uk.isxander.lowhptint.config.LowHpTintConfig;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.minecraftforge.fml.client.config.GuiSlider;

import java.io.IOException;

public class GuiLowHpTint extends GuiScreen {

    private final LowHpTintConfig config;

    public GuiLowHpTint() {
        this.config = LowHpTint.getInstance().getConfig();
    }

    @Override
    public void initGui() {
        this.buttonList.add(   new GuiSlider(0, width / 2 - 75, getRowPos(2), 150, 20, "Health: ", "", 0.0, 20.0, config.getHealth(), false, true) {
            @Override
            public void updateSlider() {
                super.updateSlider();
                GuiLowHpTint.this.config.setHealth(this.getValueInt());
            }
        });
        this.buttonList.add(   new GuiSlider(1, width / 2 - 75, getRowPos(3), 150, 20, "Red: ",    "", 0.0, 255.0, config.getRed(),   false, true) {
            @Override
            public void updateSlider() {
                super.updateSlider();
                GuiLowHpTint.this.config.setRed(this.getValueInt());
            }
        });
        this.buttonList.add(   new GuiSlider(2, width / 2 - 75, getRowPos(4), 150, 20, "Green: ",  "", 0.0, 255.0, config.getGreen(), false, true) {
            @Override
            public void updateSlider() {
                super.updateSlider();
                GuiLowHpTint.this.config.setGreen(this.getValueInt());
            }
        });
        this.buttonList.add(   new GuiSlider(3, width / 2 - 75, getRowPos(5), 150, 20, "Blue: ",   "", 0.0, 255.0, config.getBlue(),  false, true) {
            @Override
            public void updateSlider() {
                super.updateSlider();
                GuiLowHpTint.this.config.setBlue(this.getValueInt());
            }
        });
        this.buttonList.add(new GuiButtonExt(4, width / 2 - 75, getRowPos(6), 150, 20, "Finished"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 4)
            mc.displayGuiScreen(null);
    }

    @Override
    public void onGuiClosed() {
        config.save();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private int getRowPos(int rowNum) {
        return height / 4 + (24 * rowNum - 24) - 16;
    }
}
