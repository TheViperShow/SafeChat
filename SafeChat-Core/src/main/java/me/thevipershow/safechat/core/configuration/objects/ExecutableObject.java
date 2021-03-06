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

package me.thevipershow.safechat.core.configuration.objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;


@SerializableAs("ExecutableObject")
@RequiredArgsConstructor
@Getter
public final class ExecutableObject implements ConfigurationSerializable, Cloneable {
    private final List<String> commands;
    private final int flags;

    @Override
    public final Map<String, Object> serialize() {
        final Map<String, Object> result = new HashMap<>();
        result.put("commands", commands);
        result.put("flags", flags);
        return result;
    }

    public static ExecutableObject deserialize(Map<String, Object> map) {
        return new ExecutableObject(
                (List<String>) map.get("commands"),
                (int) map.get("flags")
        );
    }
}
