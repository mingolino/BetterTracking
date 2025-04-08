package vb.$bettertracking;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.event.*;
import org.bukkit.plugin.java.*;

public class PluginMain extends JavaPlugin implements Listener {

	private static PluginMain instance;

	public static org.bukkit.configuration.file.YamlConfiguration PERSISTENT_VARIABLES;

	@Override
	public void onEnable() {
		instance = this;
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(PlayerDataManager.getInstance(), this);
		PERSISTENT_VARIABLES = org.bukkit.configuration.file.YamlConfiguration
				.loadConfiguration(new File(getDataFolder(), "data.yml"));
		try {
			Object $266a24d4d9534755f16b57802931ac25 = null;
			if ((PluginMain.PERSISTENT_VARIABLES.get("distance") == null)) {
				PluginMain.PERSISTENT_VARIABLES.set("distance", ((java.lang.Object) (Object) false));
			}
			Object TEMP_WVdzVJkxdaUofxZb = $266a24d4d9534755f16b57802931ac25;
			new org.bukkit.scheduler.BukkitRunnable() {
				Object FINAL_yguBIHFEtIUSvVZG = TEMP_WVdzVJkxdaUofxZb;

				public void run() {
					try {
						for (Object FINAL_loopValue1 : PluginMain.createList(org.bukkit.Bukkit.getOnlinePlayers())) {
							if (!(PlayerDataManager.getInstance().getData(
									((org.bukkit.OfflinePlayer) (Object) FINAL_loopValue1), "trackedPlayer") == null)) {
								FINAL_yguBIHFEtIUSvVZG = PluginMain.function("trackPlayer",
										new ArrayList(Arrays.asList(FINAL_loopValue1,
												PlayerDataManager.getInstance().getData(
														((org.bukkit.OfflinePlayer) (Object) FINAL_loopValue1),
														"trackedPlayer"))));
								if (PluginMain.checkEquals(PluginMain.PERSISTENT_VARIABLES.get("distance"),
										((java.lang.Object) (Object) true))) {
									((org.bukkit.entity.Player) (Object) FINAL_loopValue1).spigot().sendMessage(
											net.md_5.bungee.api.ChatMessageType.ACTION_BAR,
											net.md_5.bungee.api.chat.TextComponent.fromLegacyText((((((ChatColor
													.translateAlternateColorCodes('&', "&a&l")
													+ ((java.lang.String) ((org.bukkit.command.CommandSender) (Object) PlayerDataManager
															.getInstance().getData(
																	((org.bukkit.OfflinePlayer) (Object) FINAL_loopValue1),
																	"trackedPlayer")).getName()))
													+ ChatColor.translateAlternateColorCodes('&', " &f"))
													+ String.valueOf(
															((java.lang.Object) ((java.util.List) (Object) FINAL_yguBIHFEtIUSvVZG)
																	.get(((int) (0d))))))
													+ " ")
													+ String.valueOf(
															((java.lang.Object) ((java.util.List) (Object) FINAL_yguBIHFEtIUSvVZG)
																	.get(((int) (1d))))))));
								} else {
									((org.bukkit.entity.Player) (Object) FINAL_loopValue1).spigot().sendMessage(
											net.md_5.bungee.api.ChatMessageType.ACTION_BAR,
											net.md_5.bungee.api.chat.TextComponent.fromLegacyText((((ChatColor
													.translateAlternateColorCodes('&', "&a&l")
													+ ((java.lang.String) ((org.bukkit.command.CommandSender) (Object) PlayerDataManager
															.getInstance().getData(
																	((org.bukkit.OfflinePlayer) (Object) FINAL_loopValue1),
																	"trackedPlayer")).getName()))
													+ ChatColor.translateAlternateColorCodes('&', " &f"))
													+ String.valueOf(
															((java.lang.Object) ((java.util.List) (Object) FINAL_yguBIHFEtIUSvVZG)
																	.get(((int) (0d))))))));
								}
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}.runTaskTimer(PluginMain.getInstance(), 0, ((long) (5d)));
			((org.bukkit.command.CommandSender) (Object) ((org.bukkit.command.ConsoleCommandSender) org.bukkit.Bukkit
					.getConsoleSender()))
							.sendMessage(ChatColor.translateAlternateColorCodes('&',
									"&b[&fBetterTracking&b] &rPlugin succesfully enabled - Made by &bItsMingolino"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onDisable() {
		PlayerDataManager.getInstance().saveAllData();
		try {
			PERSISTENT_VARIABLES.save(new File(getDataFolder(), "data.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String label, String[] commandArgs) {
		if (command.getName().equalsIgnoreCase("track")) {
			try {
				if (((boolean) PluginMain.createList(commandArgs).isEmpty())) {
					commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&cErreur &f- Syntaxe : /track [PLAYER] | /track STOP"));
					if (((boolean) ((org.bukkit.permissions.ServerOperator) (Object) commandSender).isOp())) {
						commandSender.sendMessage(
								ChatColor.translateAlternateColorCodes('&', " &b+ &f/track distance ON|OFF"));
					}
				} else if (((boolean) (commandArgs.length > ((int) (0d)) ? commandArgs[((int) (0d))] : null)
						.equalsIgnoreCase("STOP"))) {
					PlayerDataManager.getInstance().setData(((org.bukkit.OfflinePlayer) (Object) commandSender),
							"trackedPlayer", ((java.lang.Object) null));
				} else if (((!(((org.bukkit.entity.Player) org.bukkit.Bukkit
						.getPlayer((commandArgs.length > ((int) (0d)) ? commandArgs[((int) (0d))] : null))) == null))
						&& ((boolean) ((org.bukkit.OfflinePlayer) (Object) ((org.bukkit.entity.Player) org.bukkit.Bukkit
								.getPlayer((commandArgs.length > ((int) (0d)) ? commandArgs[((int) (0d))] : null))))
										.isOnline()))) {
					if (PluginMain.checkEquals(
							((org.bukkit.entity.Player) org.bukkit.Bukkit
									.getPlayer((commandArgs.length > ((int) (0d)) ? commandArgs[((int) (0d))] : null))),
							commandSender)) {
						commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&cTu ne peux pas te traquer toi m\u00EAme"));
					} else {
						PlayerDataManager.getInstance().setData(((org.bukkit.OfflinePlayer) (Object) commandSender),
								"trackedPlayer", ((org.bukkit.entity.Player) org.bukkit.Bukkit.getPlayer(
										(commandArgs.length > ((int) (0d)) ? commandArgs[((int) (0d))] : null))));
					}
				} else if ((PluginMain
						.checkEquals((commandArgs.length > ((int) (0d)) ? commandArgs[((int) (0d))] : null), "distance")
						&& ((boolean) ((org.bukkit.permissions.ServerOperator) (Object) commandSender).isOp()))) {
					if (PluginMain.checkEquals((commandArgs.length > ((int) (1d)) ? commandArgs[((int) (1d))] : null),
							"ON")) {
						PluginMain.PERSISTENT_VARIABLES.set("distance", ((java.lang.Object) (Object) true));
					} else if (PluginMain.checkEquals(
							(commandArgs.length > ((int) (1d)) ? commandArgs[((int) (1d))] : null), "OFF")) {
						PluginMain.PERSISTENT_VARIABLES.set("distance", ((java.lang.Object) (Object) false));
					} else {
						commandSender.sendMessage("syntaxe /track distance ON/OFF");
					}
				} else {
					commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&cErreur &f- Syntaxe : /track [PLAYER] | /track STOP"));
					if (((boolean) ((org.bukkit.permissions.ServerOperator) (Object) commandSender).isOp())) {
						commandSender.sendMessage(
								ChatColor.translateAlternateColorCodes('&', " &b+ &f/track distance ON|OFF"));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return true;
	}

	public static void procedure(String procedure, List procedureArgs) throws Exception {
	}

	public static Object function(String function, List functionArgs) throws Exception {
		if (function.equalsIgnoreCase("trackPlayer")) {
			Object $121d029516073994e057639831c25980 = null;
			Object $0b2f93af7c8259eb0447305da351d42e = null;
			Object $68714194d13a5feb8826dd1adc4c7f19 = null;
			Object $897859f6655555855a890e51483ab5e6 = null;
			Object $395ad2ffcef07bd20d4d2d232b618e09 = null;
			Object $f8889b1f7fbcf1f942a789d44a22286c = null;
			Object $34c96218030747127518442dcdf79d57 = null;
			Object $c78f2fa048223694246eb6acb39bb15e = null;
			Object $76f74784cdf272cbdd1c37d471a532a0 = null;
			Object $c728a7436e8e4ede9155004ad8e51cae = null;
			if (((boolean) ((org.bukkit.OfflinePlayer) (Object) functionArgs.get(((int) (1d)))).isOnline())) {
				$395ad2ffcef07bd20d4d2d232b618e09 = ((org.bukkit.World) ((org.bukkit.entity.Entity) (Object) functionArgs
						.get(((int) (0d)))).getWorld());
				$121d029516073994e057639831c25980 = ((org.bukkit.World) ((org.bukkit.entity.Entity) (Object) functionArgs
						.get(((int) (1d)))).getWorld());
				$c78f2fa048223694246eb6acb39bb15e = new org.bukkit.Location(
						((org.bukkit.World) ((org.bukkit.Location) ((org.bukkit.entity.Entity) (Object) functionArgs
								.get(((int) (0d)))).getLocation()).getWorld()),
						((double) ((org.bukkit.Location) ((org.bukkit.entity.Entity) (Object) functionArgs
								.get(((int) (0d)))).getLocation()).getX()),
						(0d),
						((double) ((org.bukkit.Location) ((org.bukkit.entity.Entity) (Object) functionArgs
								.get(((int) (0d)))).getLocation()).getZ()),
						((float) ((org.bukkit.Location) ((org.bukkit.entity.Entity) (Object) functionArgs
								.get(((int) (0d)))).getLocation()).getYaw()),
						((float) ((org.bukkit.Location) ((org.bukkit.entity.Entity) (Object) functionArgs
								.get(((int) (0d)))).getLocation()).getPitch()));
				$c728a7436e8e4ede9155004ad8e51cae = new org.bukkit.Location(
						((org.bukkit.World) ((org.bukkit.Location) ((org.bukkit.entity.Entity) (Object) functionArgs
								.get(((int) (1d)))).getLocation()).getWorld()),
						((double) ((org.bukkit.Location) ((org.bukkit.entity.Entity) (Object) functionArgs
								.get(((int) (1d)))).getLocation()).getX()),
						(0d), ((double) ((org.bukkit.Location) ((org.bukkit.entity.Entity) (Object) functionArgs
								.get(((int) (1d)))).getLocation()).getZ()));
				if (!PluginMain.checkEquals($395ad2ffcef07bd20d4d2d232b618e09, $121d029516073994e057639831c25980)) {
					if (PluginMain.checkEquals(
							((org.bukkit.World.Environment) ((org.bukkit.generator.WorldInfo) (Object) $395ad2ffcef07bd20d4d2d232b618e09)
									.getEnvironment()),
							((org.bukkit.World.Environment) org.bukkit.World.Environment.NORMAL))) {
						$0b2f93af7c8259eb0447305da351d42e = PlayerDataManager.getInstance().getData(
								((org.bukkit.OfflinePlayer) (Object) functionArgs.get(((int) (1d)))), "oPortal");
						$c728a7436e8e4ede9155004ad8e51cae = new org.bukkit.Location(
								((org.bukkit.World) ((org.bukkit.Location) (Object) $0b2f93af7c8259eb0447305da351d42e)
										.getWorld()),
								((double) ((org.bukkit.Location) (Object) $0b2f93af7c8259eb0447305da351d42e).getX()),
								(0d),
								((double) ((org.bukkit.Location) (Object) $0b2f93af7c8259eb0447305da351d42e).getZ()));
					} else if (PluginMain.checkEquals(
							((org.bukkit.World.Environment) ((org.bukkit.generator.WorldInfo) (Object) $395ad2ffcef07bd20d4d2d232b618e09)
									.getEnvironment()),
							((org.bukkit.World.Environment) org.bukkit.World.Environment.NETHER))) {
						$0b2f93af7c8259eb0447305da351d42e = PlayerDataManager.getInstance().getData(
								((org.bukkit.OfflinePlayer) (Object) functionArgs.get(((int) (1d)))), "nPortal");
						$c728a7436e8e4ede9155004ad8e51cae = new org.bukkit.Location(
								((org.bukkit.World) ((org.bukkit.Location) (Object) $0b2f93af7c8259eb0447305da351d42e)
										.getWorld()),
								((double) ((org.bukkit.Location) (Object) $0b2f93af7c8259eb0447305da351d42e).getX()),
								(0d),
								((double) ((org.bukkit.Location) (Object) $0b2f93af7c8259eb0447305da351d42e).getZ()));
					} else {
						if (true)
							return new ArrayList(Arrays.asList("\u2022", "?"));
					}
				}
				$f8889b1f7fbcf1f942a789d44a22286c = ((java.lang.Object) (Object) Math
						.round(((double) ((org.bukkit.Location) (Object) $c728a7436e8e4ede9155004ad8e51cae)
								.distance(((org.bukkit.Location) (Object) $c78f2fa048223694246eb6acb39bb15e)))));
				$34c96218030747127518442dcdf79d57 = ((org.bukkit.util.Vector) ((org.bukkit.util.Vector) ((org.bukkit.Location) ((org.bukkit.Location) (Object) $c728a7436e8e4ede9155004ad8e51cae)
						.subtract(((org.bukkit.Location) (Object) $c78f2fa048223694246eb6acb39bb15e))).toVector())
								.normalize());
				$76f74784cdf272cbdd1c37d471a532a0 = ((org.bukkit.util.Vector) ((org.bukkit.Location) (Object) $c78f2fa048223694246eb6acb39bb15e)
						.getDirection());
				$897859f6655555855a890e51483ab5e6 = ((java.lang.Object) (Object) ((((Number) Math.atan2(
						((double) ((org.bukkit.util.Vector) (Object) $76f74784cdf272cbdd1c37d471a532a0).getX()),
						((double) ((org.bukkit.util.Vector) (Object) $76f74784cdf272cbdd1c37d471a532a0).getZ())))
								.doubleValue()
						* (180d)) / Math.PI));
				$897859f6655555855a890e51483ab5e6 = ((java.lang.Object) (Object) (((Number) $897859f6655555855a890e51483ab5e6)
						.doubleValue()
						- ((((Number) Math.atan2(
								((double) ((org.bukkit.util.Vector) (Object) $34c96218030747127518442dcdf79d57).getX()),
								((double) ((org.bukkit.util.Vector) (Object) $34c96218030747127518442dcdf79d57)
										.getZ()))).doubleValue()
								* (180d)) / Math.PI)));
				if ((((Number) $897859f6655555855a890e51483ab5e6).doubleValue() < (0d))) {
					$897859f6655555855a890e51483ab5e6 = ((java.lang.Object) (Object) ((long) Math
							.ceil((((Number) $897859f6655555855a890e51483ab5e6).doubleValue() + (22.5d)))));
				} else {
					$897859f6655555855a890e51483ab5e6 = ((java.lang.Object) (Object) ((long) Math
							.floor((((Number) $897859f6655555855a890e51483ab5e6).doubleValue() + (22.5d)))));
				}
				$897859f6655555855a890e51483ab5e6 = ((java.lang.Object) (Object) (((Number) $897859f6655555855a890e51483ab5e6)
						.doubleValue() % (360d)));
				if ((((Number) $897859f6655555855a890e51483ab5e6).doubleValue() < (0d))) {
					$897859f6655555855a890e51483ab5e6 = ((java.lang.Object) (Object) (((Number) $897859f6655555855a890e51483ab5e6)
							.doubleValue() + (360d)));
				}
				$68714194d13a5feb8826dd1adc4c7f19 = ((java.lang.Object) (Object) ((char) "\u2B06\u2B08\u27A1\u2B0A\u2B07\u2B0B\u2B05\u2B09"
						.charAt(((int) ((long) Math
								.floor((((Number) $897859f6655555855a890e51483ab5e6).doubleValue() / (45d))))))));
				if (true)
					return new ArrayList(
							Arrays.asList($68714194d13a5feb8826dd1adc4c7f19, $f8889b1f7fbcf1f942a789d44a22286c));
			} else {
				if (true)
					return new ArrayList(Arrays.asList("\u2022", "?"));
			}
		}
		return null;
	}

	public static List createList(Object obj) {
		if (obj instanceof List) {
			return (List) obj;
		}
		List list = new ArrayList<>();
		if (obj.getClass().isArray()) {
			int length = java.lang.reflect.Array.getLength(obj);
			for (int i = 0; i < length; i++) {
				list.add(java.lang.reflect.Array.get(obj, i));
			}
		} else if (obj instanceof Collection<?>) {
			list.addAll((Collection<?>) obj);
		} else if (obj instanceof Iterator) {
			((Iterator<?>) obj).forEachRemaining(list::add);
		} else {
			list.add(obj);
		}
		return list;
	}

	public static void createResourceFile(String path) {
		Path file = getInstance().getDataFolder().toPath().resolve(path);
		if (Files.notExists(file)) {
			try (InputStream inputStream = PluginMain.class.getResourceAsStream("/" + path)) {
				Files.createDirectories(file.getParent());
				Files.copy(inputStream, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static PluginMain getInstance() {
		return instance;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void event1(org.bukkit.event.player.PlayerPortalEvent event) throws Exception {
		if (PluginMain.checkEquals(
				((org.bukkit.World.Environment) ((org.bukkit.generator.WorldInfo) (Object) ((org.bukkit.World) ((org.bukkit.entity.Entity) (Object) ((org.bukkit.entity.Player) event
						.getPlayer())).getWorld())).getEnvironment()),
				((org.bukkit.World.Environment) org.bukkit.World.Environment.NORMAL))) {
			PlayerDataManager.getInstance().setData(
					((org.bukkit.OfflinePlayer) (Object) ((org.bukkit.entity.Player) event.getPlayer())), "oPortal",
					((org.bukkit.Location) ((org.bukkit.entity.Entity) (Object) ((org.bukkit.entity.Player) event
							.getPlayer())).getLocation()));
		} else if (PluginMain.checkEquals(
				((org.bukkit.World.Environment) ((org.bukkit.generator.WorldInfo) (Object) ((org.bukkit.World) ((org.bukkit.entity.Entity) (Object) ((org.bukkit.entity.Player) event
						.getPlayer())).getWorld())).getEnvironment()),
				((org.bukkit.World.Environment) org.bukkit.World.Environment.NETHER))) {
			PlayerDataManager.getInstance().setData(
					((org.bukkit.OfflinePlayer) (Object) ((org.bukkit.entity.Player) event.getPlayer())), "nPortal",
					((org.bukkit.Location) ((org.bukkit.entity.Entity) (Object) ((org.bukkit.entity.Player) event
							.getPlayer())).getLocation()));
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void event2(org.bukkit.event.player.PlayerJoinEvent event) throws Exception {
		Object $93b2f076cea65ac3db837a144b08c84d = null;
		$93b2f076cea65ac3db837a144b08c84d = ((org.bukkit.entity.Player) event.getPlayer());
		((org.bukkit.entity.Player) (Object) $93b2f076cea65ac3db837a144b08c84d).spigot().sendMessage(
				net.md_5.bungee.api.ChatMessageType.ACTION_BAR, net.md_5.bungee.api.chat.TextComponent.fromLegacyText(
						ChatColor.translateAlternateColorCodes('&', "/track &b[PLAYER] &7to track a player")));
		Object TEMP_rxhyYdXOoLOJBltd = $93b2f076cea65ac3db837a144b08c84d;
		new org.bukkit.scheduler.BukkitRunnable() {
			Object FINAL_dcKTEmmICjLcnnFG = TEMP_rxhyYdXOoLOJBltd;

			public void run() {
				try {
					((org.bukkit.entity.Player) (Object) FINAL_dcKTEmmICjLcnnFG).spigot().sendMessage(
							net.md_5.bungee.api.ChatMessageType.ACTION_BAR,
							net.md_5.bungee.api.chat.TextComponent.fromLegacyText(ChatColor
									.translateAlternateColorCodes('&', "/track &b[PLAYER] &7to track a player")));
					Object TEMP_jSXgwJbLouXijQyS = FINAL_dcKTEmmICjLcnnFG;
					new org.bukkit.scheduler.BukkitRunnable() {
						Object FINAL_WrmAvLgyPyLhAELW = TEMP_jSXgwJbLouXijQyS;

						public void run() {
							try {
								((org.bukkit.entity.Player) (Object) FINAL_WrmAvLgyPyLhAELW).spigot().sendMessage(
										net.md_5.bungee.api.ChatMessageType.ACTION_BAR,
										net.md_5.bungee.api.chat.TextComponent
												.fromLegacyText(ChatColor.translateAlternateColorCodes('&',
														"/track &b[PLAYER] &7to track a player")));
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}.runTaskLater(PluginMain.getInstance(), ((long) (40d)));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}.runTaskLater(PluginMain.getInstance(), ((long) (40d)));
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void event3(org.bukkit.event.server.TabCompleteEvent event) throws Exception {
		Object $bc1fea4464a4f64f0fb70aa60b009f07 = null;
		if ((PluginMain.checkEquals(((java.lang.String) event.getBuffer()), "/track ")
				&& ((boolean) ((org.bukkit.permissions.ServerOperator) (Object) ((org.bukkit.command.CommandSender) event
						.getSender())).isOp()))) {
			$bc1fea4464a4f64f0fb70aa60b009f07 = new ArrayList(Arrays.asList("distance", "STOP"));
			for (Object FINAL_loopValue1 : PluginMain.createList(org.bukkit.Bukkit.getOnlinePlayers())) {
				((java.util.List) (Object) $bc1fea4464a4f64f0fb70aa60b009f07).add(
						((java.lang.String) ((org.bukkit.command.CommandSender) (Object) FINAL_loopValue1).getName()));
			}
			((java.util.List) (Object) $bc1fea4464a4f64f0fb70aa60b009f07)
					.remove(((java.lang.String) ((org.bukkit.command.CommandSender) event.getSender()).getName()));
			event.setCompletions(((java.util.List) (Object) $bc1fea4464a4f64f0fb70aa60b009f07));
		} else if (PluginMain.checkEquals(((java.lang.String) event.getBuffer()), "/track ")) {
			$bc1fea4464a4f64f0fb70aa60b009f07 = new ArrayList();
			for (Object FINAL_loopValue1 : PluginMain.createList(org.bukkit.Bukkit.getOnlinePlayers())) {
				((java.util.List) (Object) $bc1fea4464a4f64f0fb70aa60b009f07).add(
						((java.lang.String) ((org.bukkit.command.CommandSender) (Object) FINAL_loopValue1).getName()));
			}
			((java.util.List) (Object) $bc1fea4464a4f64f0fb70aa60b009f07)
					.remove(((java.lang.String) ((org.bukkit.command.CommandSender) event.getSender()).getName()));
			event.setCompletions(((java.util.List) (Object) $bc1fea4464a4f64f0fb70aa60b009f07));
		} else if ((PluginMain.checkEquals(((java.lang.String) event.getBuffer()), "/track distance ")
				&& ((boolean) ((org.bukkit.permissions.ServerOperator) (Object) ((org.bukkit.command.CommandSender) event
						.getSender())).isOp()))) {
			event.setCompletions(new ArrayList(Arrays.asList("ON", "OFF")));
		}
	}

	public static boolean checkEquals(Object o1, Object o2) {
		if (o1 == null || o2 == null) {
			return false;
		}
		return o1 instanceof Number && o2 instanceof Number
				? ((Number) o1).doubleValue() == ((Number) o2).doubleValue()
				: o1.equals(o2);
	}
}
