/*
 * This file is part of Charlie CI API.
 *
 * Copyright (c) 2013, Stephen Williams (alta189) <http://charlie.alta189.com/>
 * Charlie CI API is licensed under the GNU Lesser General Public License.
 *
 * Charlie CI API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Charlie CI API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.alta189.charlie.api.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommandMap {
	private final Map<String, Command> commandMap = new HashMap<String, Command>();
	private final Map<String, Command> aliasMap = new HashMap<String, Command>();

	public void addCommand(Command command) {
		if (commandMap.get(command.getCommand()) == null) {
			commandMap.put(command.getCommand(), command);
			for (String alias : command.getAliases()) {
				registerAlias(alias, command);
			}
		}
	}

	private void registerAlias(String alias, Command command) {
		if (aliasMap.get(alias.toLowerCase()) == null) {
			aliasMap.put(alias.toLowerCase(), command);
		}
	}

	public Command getCommand(String cmd) {
		return getCommand(cmd, false);
	}

	public Command getCommand(String cmd, boolean ignoreAliases) {
		Command command = commandMap.get(cmd.toLowerCase());
		if (command == null && !ignoreAliases) {
			command = aliasMap.get(cmd.toLowerCase());
		}
		return command;
	}

	public Collection<Command> getCommands() {
		return commandMap.values();
	}
}
