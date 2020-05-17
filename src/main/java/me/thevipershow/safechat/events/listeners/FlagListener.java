/*
 * SafeChat - A Minecraft plugin to keep your chat safe.
 *  Copyright (C) 2020 TheViperShow
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.thevipershow.safechat.events.listeners;

import java.util.logging.Logger;
import me.thevipershow.safechat.config.Values;
import me.thevipershow.safechat.events.FlagThrownEvent;
import me.thevipershow.safechat.sql.DataManager;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class FlagListener implements Listener {
    private static FlagListener instance = null;
    private final DataManager dataManager;
    private final Logger logger;
    private final Values values;
    private final ConsoleCommandSender consoleCommandSender;

    private FlagListener(final Logger logger,
                         final Values values,
                         final DataManager manager,
                         final ConsoleCommandSender consoleCommandSender) {
        this.logger = logger;
        this.values = values;
        this.dataManager = manager;
        this.consoleCommandSender = consoleCommandSender;
    }

    public static FlagListener getInstance(final Logger logger,
                                           final Values values,
                                           final DataManager manager,
                                           final ConsoleCommandSender console) {
        return instance != null ? instance : (instance = new FlagListener(logger, values, manager, console));
    }

    @EventHandler(ignoreCancelled = true)
    public void onFlagThrown(final FlagThrownEvent event) {
        if (values.isEnableConsoleLogging())
            logger.info("Player: " + event.getPlayerName() + " has failed a " + event.getCheckName().name() + " check!");

        dataManager.addPlayerData(event.getSenderUUID(), event.getPlayerName(), event.generatePlayerData(), event.getCheckName(), event.getSeverity());
        dataManager.checkExecute(event.getSenderUUID(), event.getPlayerName(), values.getWordsExecutables(), values.getIpv4Executables(), values.getDomainExecutables(), consoleCommandSender);
    }
}
