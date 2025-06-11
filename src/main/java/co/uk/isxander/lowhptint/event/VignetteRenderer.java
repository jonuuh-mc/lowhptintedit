package co.uk.isxander.lowhptint.event;

import co.uk.isxander.lowhptint.config.LowHpTintConfig;
import co.uk.isxander.lowhptint.utils.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class VignetteRenderer
{
    private final Minecraft mc;
    private final LowHpTintConfig config;
    private final ResourceLocation vignetteTex = new ResourceLocation("lowhptint:vignette.png");

    public VignetteRenderer(LowHpTintConfig config)
    {
        this.mc = Minecraft.getMinecraft();
        this.config = config;
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event)
    {
        if (event.type == RenderGameOverlayEvent.ElementType.HELMET)
        {
            if (config.isEnabled())
            {
                renderTint(new ScaledResolution(mc));
            }
        }
    }

    private void renderTint(ScaledResolution sr)
    {
        float normalR = MathUtils.normalize(config.getRed(), 0, 255);
        float normalG = MathUtils.normalize(config.getGreen(), 0, 255);
        float normalB = MathUtils.normalize(config.getBlue(), 0, 255);

        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

        float normalMissingHealth = MathUtils.normalize(mc.thePlayer.getMaxHealth() - mc.thePlayer.getHealth(), config.getMinHealth(), config.getMaxHealth());
        float easedOpacity = (float) (MathUtils.ease(config.getEaseType(), config.getEaseScale(), normalMissingHealth));

        GlStateManager.color(normalR, normalG, normalB, easedOpacity);
        GlStateManager.disableAlpha();

        mc.getTextureManager().bindTexture(vignetteTex);
        drawTexturedRect(0,0, -90, sr.getScaledWidth(), sr.getScaledHeight());

        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void drawTexturedRect(int x, int y, int zLevel, int width, int height)
    {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer wr = tessellator.getWorldRenderer();
        wr.begin(7, DefaultVertexFormats.POSITION_TEX);
        wr.pos(x, y + height, zLevel).tex(0, 1).endVertex();
        wr.pos(x + width, y + height, zLevel).tex(1, 1).endVertex();
        wr.pos(x + width, y, zLevel).tex(1, 0).endVertex();
        wr.pos(x, y, zLevel).tex(0, 0).endVertex();
        tessellator.draw();
    }
}
