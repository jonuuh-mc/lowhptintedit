package co.uk.isxander.lowhptint.command;

import co.uk.isxander.lowhptint.LowHpTint;
import co.uk.isxander.lowhptint.gui.impl.GuiLowHpTint;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class LowHpTintCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "lowhptint";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        LowHpTint.getInstance().getGuiHandler().open(new GuiLowHpTint());
    }

}
