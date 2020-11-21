package co.uk.isxander.lowhptint;

import co.uk.isxander.lowhptint.command.LowHpTintCommand;
import co.uk.isxander.lowhptint.config.LowHpTintConfig;
import co.uk.isxander.lowhptint.gui.GuiHandler;
import co.uk.isxander.lowhptint.gui.impl.GuiLowHpTint;
import co.uk.isxander.lowhptint.utils.MathUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

@Mod(name = LowHpTint.NAME, modid = LowHpTint.MODID, version = LowHpTint.VERSION, acceptedMinecraftVersions = "[1.8.9]", clientSideOnly = true)
public class LowHpTint {

    public static final String NAME = "isXander's LowHpTint Mod";
    public static final String MODID = "lowhptint";
    public static final String VERSION = "1.0";

    @Mod.Instance
    private static LowHpTint instance;

    private LowHpTintConfig config;

    private GuiHandler guiHandler;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        config = new LowHpTintConfig();
        config.load();
        MinecraftForge.EVENT_BUS.register(guiHandler = new GuiHandler());
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new LowHpTintCommand());
    }

    public static LowHpTint getInstance() {
        return instance;
    }

    public GuiHandler getGuiHandler() {
        return guiHandler;
    }

    public LowHpTintConfig getConfig() {
        return config;
    }

    @SubscribeEvent
    public void onRenderHealth(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.FOOD) {
            renderTint(Minecraft.getMinecraft().thePlayer.getHealth(), new ScaledResolution(Minecraft.getMinecraft()));
        }
    }

    private static final ResourceLocation vignette = new ResourceLocation("lowhptint/tintshape.png");

    private void renderTint(float currentHealth, ScaledResolution res) {
        float threshold = config.getHealth();
        if (currentHealth <= threshold) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.depthMask(false);
            GlStateManager.tryBlendFuncSeparate(0, 769, 1, 0);
            float f = (threshold - currentHealth) / threshold + 1.0F / threshold * 2.0F;
            float r = 1.0f - MathUtils.lerp(MathUtils.getPercent(config.getRed(),   0, 255), 0.0f, f);
            float g = 1.0f - MathUtils.lerp(MathUtils.getPercent(config.getGreen(), 0, 255), 0.0f, f);
            float b = 1.0f - MathUtils.lerp(MathUtils.getPercent(config.getBlue(),  0, 255), 0.0f, f);
            GlStateManager.color(r, g, b, 1.0f);
            Minecraft.getMinecraft().getTextureManager().bindTexture(vignette);
            Tessellator tes = Tessellator.getInstance();
            WorldRenderer wr = tes.getWorldRenderer();
            wr.begin(7, DefaultVertexFormats.POSITION_TEX);
            wr.pos(0.0, res.getScaledHeight_double(), -90.0).tex(0.0, 1.0).endVertex();
            wr.pos(res.getScaledWidth_double(), res.getScaledHeight_double(), -90.0).tex(1.0, 1.0).endVertex();
            wr.pos(res.getScaledWidth_double(), 0.0, -90.0).tex(1.0, 0.0).endVertex();
            wr.pos(0.0, 0.0, -90.0).tex(0.0, 0.0).endVertex();
            tes.draw();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }

    }

}
