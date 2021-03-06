/*
 * SafeChat - A Minecraft plugin to keep your chat safe.
 * Copyright (C) 2020 TheViperShow
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

package me.thevipershow.safechat.core.configuration;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public final class ValuesImplementation extends AbstractValues {
    private final JavaPlugin plugin;

    private static ValuesImplementation instance;

    public static synchronized ValuesImplementation getInstance(Configuration configuration, JavaPlugin plugin) {
        return instance != null ? instance : (instance = new ValuesImplementation(configuration, plugin));
    }

    public ValuesImplementation(Configuration configuration, JavaPlugin plugin) {
        super(configuration);
        this.plugin = plugin;
    }

    @Override
    public void updateAll() throws IllegalArgumentException {
        plugin.reloadConfig();
        super.updateConfigValues();
    }
}
