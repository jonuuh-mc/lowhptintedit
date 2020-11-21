package co.uk.isxander.lowhptint.gui.impl;

import co.uk.isxander.lowhptint.LowHpTint;
import co.uk.isxander.lowhptint.config.LowHpTintConfig;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
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
        this.buttonList.add(   new GuiSlider(0, width / 2 - 150, getRowPos(3), 150, 20, "Red: ",   "", 0.0, 255.0, config.getRed(),   false, true) {
            @Override
            public void updateSlider() {
                super.updateSlider();
                GuiLowHpTint.this.config.setRed(this.getValueInt());
            }
        });
        this.buttonList.add(   new GuiSlider(1, width / 2 - 150, getRowPos(4), 150, 20, "Green: ", "", 0.0, 255.0, config.getGreen(), false, true) {
            @Override
            public void updateSlider() {
                super.updateSlider();
                GuiLowHpTint.this.config.setGreen(this.getValueInt());
            }
        });
        this.buttonList.add(   new GuiSlider(2, width / 2 - 150, getRowPos(5), 150, 20, "Blue: ",  "", 0.0, 255.0, config.getBlue(),  false, true) {
            @Override
            public void updateSlider() {
                super.updateSlider();
                GuiLowHpTint.this.config.setBlue(this.getValueInt());
            }
        });
        this.buttonList.add(   new GuiSlider(3, width / 2 - 150, getRowPos(6), 150, 20, "Alpha: ", "", 0.0, 255.0, config.getAlpha(), false, true) {
            @Override
            public void updateSlider() {
                super.updateSlider();
                GuiLowHpTint.this.config.setAlpha(this.getValueInt());
            }
        });
        this.buttonList.add(new GuiButtonExt(4, width / 2 - 150, getRowPos(7), 150, 20, "Finished"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
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
        return 55 + rowNum * 23;
    }
}
