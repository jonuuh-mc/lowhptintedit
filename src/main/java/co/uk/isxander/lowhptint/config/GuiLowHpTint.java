package co.uk.isxander.lowhptint.config;

import co.uk.isxander.lowhptint.utils.MathUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.minecraftforge.fml.client.config.GuiSlider;
import org.lwjgl.opengl.GL11;

// TODO: this class is disgusting but i can't be bothered to fix it
public class GuiLowHpTint extends GuiScreen
{
    private final LowHpTintConfig config;

    public GuiLowHpTint(LowHpTintConfig config)
    {
        this.config = config;
    }

    @Override
    public void initGui()
    {
        this.buttonList.add(new GuiButtonExt(0, width / 2 - 75, getRowPos(1), 150, 20, "Enabled: " + getFormattedBoolean(config.isEnabled())));

        // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // //
        this.buttonList.add(new GuiSlider(2, width / 2 - 75, getRowPos(2), 150, 20, "Red: ", "", 0.0, 255.0, config.getRed(), false, true)
        {
            @Override
            public void updateSlider()
            {
                super.updateSlider();
                config.setRed(this.getValueInt());
            }
        });
        this.buttonList.add(new GuiSlider(3, width / 2 - 75, getRowPos(3), 150, 20, "Green: ", "", 0.0, 255.0, config.getGreen(), false, true)
        {
            @Override
            public void updateSlider()
            {
                super.updateSlider();
                config.setGreen(this.getValueInt());
            }
        });
        this.buttonList.add(new GuiSlider(4, width / 2 - 75, getRowPos(4), 150, 20, "Blue: ", "", 0.0, 255.0, config.getBlue(), false, true)
        {
            @Override
            public void updateSlider()
            {
                super.updateSlider();
                config.setBlue(this.getValueInt());
            }
        });
        // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // //

        this.buttonList.add(new GuiSlider(1, width / 2 - 75, getRowPos(5), 75, 20, "Min health: ", "", 1, mc.thePlayer.getMaxHealth(), config.getMinHealth(), false, true)
        {
            @Override
            public void updateSlider()
            {
                super.updateSlider();
                config.setMinHealth(this.getValueInt());
            }
        });
        this.buttonList.add(new GuiSlider(5, width / 2, getRowPos(5), 75, 20, "Max heath: ", "", 1, mc.thePlayer.getMaxHealth(), config.getMaxHealth(), false, true)
        {
            @Override
            public void updateSlider()
            {
                super.updateSlider();
                config.setMaxHealth(this.getValueInt());
            }
        });
        // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // // //

        this.buttonList.add(new GuiSlider(5, width / 2 - 75, getRowPos(6), 75, 20, "Ease type: ", "", 1, 3, config.getEaseType(), false, true)
        {
            @Override
            public void updateSlider()
            {
                super.updateSlider();
                config.setEaseType(this.getValueInt());
            }
        });
        this.buttonList.add(new GuiSlider(5, width / 2, getRowPos(6), 75, 20, "Ease scale: ", "", 1, 10, config.getEaseScale(), false, true)
        {
            @Override
            public void updateSlider()
            {
                super.updateSlider();
                config.setEaseScale(this.getValueInt());
            }
        });
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

        int scaled = 60;

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glLineWidth(1F);
        GL11.glColor4f(0.5F, 0.5F, 0.5F, 1F);

        GuiButton lastButton = buttonList.get(buttonList.size() - 1);
        GL11.glTranslatef(lastButton.xPosition - (scaled / 2F), lastButton.yPosition + 20 + 5, 0);

        GL11.glBegin(GL11.GL_LINE_LOOP);
        GL11.glVertex3d(0, 0, 0);
        GL11.glVertex3d(0, scaled, 0);
        GL11.glVertex3d(scaled, scaled, 0);
        GL11.glVertex3d(scaled, 0, 0);
        GL11.glEnd();

        GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        for (int i = config.getMinHealth(); i <= config.getMaxHealth(); i++)
        {
            float normalHealth = MathUtils.normalize(i, config.getMinHealth(), config.getMaxHealth());
            float easedOpacity = (float) (1 - MathUtils.ease(config.getEaseType(), config.getEaseScale(), normalHealth));

            if (i == (mc.thePlayer.getMaxHealth() - mc.thePlayer.getHealth()))
            {
                GL11.glColor4f(1F, 0F, 0F, 1F);
                GL11.glVertex3d(normalHealth * scaled, easedOpacity * scaled, 0);
                GL11.glColor4f(1F, 1F, 1F, 1F);
            }
            else
            {
                GL11.glVertex3d(normalHealth * scaled, easedOpacity * scaled, 0);
            }
        }
        GL11.glEnd();

        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        int hpStrX = (scaled / 2) - ((fontRendererObj.getStringWidth("Missing HP") - 1) / 2);
        fontRendererObj.drawString("Missing HP", hpStrX, (scaled + 2), -1);

        GL11.glRotatef(90, 0, 0, 1);
        int opacityStrX = (scaled - (fontRendererObj.getStringWidth("Tint Opacity") - 1)) / 2;
        fontRendererObj.drawString("Tint Opacity", opacityStrX, 2, -1);

        GL11.glPopMatrix();
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if (button.id == 0)
        {
            config.setEnabled(!config.isEnabled());
            button.displayString = "Enabled: " + getFormattedBoolean(config.isEnabled());
        }
    }

    @Override
    public void onGuiClosed()
    {
        config.save();
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    private int getRowPos(int rowNum)
    {
        return height / 4 + (24 * rowNum - 24) - 48;
    }

    private String getFormattedBoolean(boolean b)
    {
        return (b ? EnumChatFormatting.GREEN + "ON" : EnumChatFormatting.RED + "OFF");
    }
}
