package net.shortninja.staffplus.core.domain.staff.ban.playerbans.gui.cmd;

import be.garagepoort.mcioc.IocBean;
import be.garagepoort.mcioc.IocMultiProvider;
import be.garagepoort.mcioc.gui.GuiActionService;
import net.shortninja.staffplus.core.application.config.Messages;
import net.shortninja.staffplus.core.common.cmd.AbstractCmd;
import net.shortninja.staffplus.core.common.cmd.Command;
import net.shortninja.staffplus.core.common.cmd.CommandService;
import net.shortninja.staffplus.core.common.cmd.SppCommand;
import net.shortninja.staffplus.core.common.exceptions.BusinessException;
import net.shortninja.staffplus.core.common.permissions.PermissionHandler;
import net.shortninja.staffplusplus.session.SppPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;

@Command(
    command = "commands:bans.manage.gui",
    permissions = "permissions:ban-view",
    description = "Open the manage Bans GUI."
)
@IocBean(conditionalOnProperty = "ban-module.enabled=true")
@IocMultiProvider(SppCommand.class)
public class ManageBansGuiCmd extends AbstractCmd {

    private final GuiActionService guiActionService;

    public ManageBansGuiCmd(Messages messages,
                            CommandService commandService,
                            PermissionHandler permissionHandler, GuiActionService guiActionService) {
        super(messages, permissionHandler, commandService);
        this.guiActionService = guiActionService;
    }

    @Override
    protected boolean executeCmd(CommandSender sender, String alias, String[] args, SppPlayer player, Map<String, String> optionalParameters) {
        if (!(sender instanceof Player)) {
            throw new BusinessException(messages.onlyPlayers);
        }

        guiActionService.executeAction((Player) sender, "manage-bans/view/overview");
        return true;
    }

    @Override
    protected int getMinimumArguments(CommandSender sender, String[] args) {
        return 0;
    }

    @Override
    protected Optional<String> getPlayerName(CommandSender sender, String[] args) {
        return Optional.empty();
    }
}
