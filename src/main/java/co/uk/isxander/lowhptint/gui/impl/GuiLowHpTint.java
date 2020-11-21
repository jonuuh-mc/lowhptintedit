package co.uk.isxander.lowhptint.gui.impl;

import co.uk.isxander.lowhptint.LowHpTint;
import co.uk.isxander.lowhptint.config.LowHpTintConfig;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.minecraftforge.fml.client.config.GuiSlider;

public class GuiLowHpTint extends GuiScreen {

    private final LowHpTintConfig config;

    public GuiLowHpTint() {
        this.config = LowHpTint.getInstance().getConfig();
    }

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButtonExt(0, width / 2 - 75, getRowPos(1), 150, 20, "Enabled: " + getFormattedBoolean(config.isEnabled())));
        this.buttonList.add(   new GuiSlider(1, width / 2 - 75, getRowPos(2), 150, 20, "Health: ", "", 0.0, 20.0, config.getHealth(), false, true) {
            @Override
            public void updateSlider() {
                super.updateSlider();
                GuiLowHpTint.this.config.setHealth(this.getValueInt());
            }
        });
        this.buttonList.add(   new GuiSlider(2, width / 2 - 75, getRowPos(3), 150, 20, "Red: ",    "", 0.0, 255.0, config.getRed(),   false, true) {
            @Override
            public void updateSlider() {
                super.updateSlider();
                GuiLowHpTint.this.config.setRed(this.getValueInt());
            }
        });
        this.buttonList.add(   new GuiSlider(3, width / 2 - 75, getRowPos(4), 150, 20, "Green: ",  "", 0.0, 255.0, config.getGreen(), false, true) {
            @Override
            public void updateSlider() {
                super.updateSlider();
                GuiLowHpTint.this.config.setGreen(this.getValueInt());
            }
        });
        this.buttonList.add(   new GuiSlider(4, width / 2 - 75, getRowPos(5), 150, 20, "Blue: ",   "", 0.0, 255.0, config.getBlue(),  false, true) {
            @Override
            public void updateSlider() {
                super.updateSlider();
                GuiLowHpTint.this.config.setBlue(this.getValueInt());
            }
        });
        this.buttonList.add(   new GuiSlider(5, width / 2 - 75, getRowPos(6), 150, 20, "Speed: ", "", 1, 20, config.getSpeed(), false, true) {
            @Override
            public void updateSlider() {
                super.updateSlider();
                GuiLowHpTint.this.config.setSpeed(this.getValueInt());
            }
        });
        this.buttonList.add(new GuiButtonExt(6, width / 2 - 75, getRowPos(7), 150, 20, "Finished"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                config.setEnabled(!config.isEnabled());
                button.displayString = "Enabled: " + getFormattedBoolean(config.isEnabled());
                break;
            case 6:
                mc.displayGuiScreen(null);
                break;
        }
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

    private String getFormattedBoolean(boolean b) {
        return (b ? EnumChatFormatting.GREEN + "ON" : EnumChatFormatting.RED + "OFF");
    }
}
