# BetterTracking

A Minecraft plugin that allows players to track others using directional indicators and optional distance display. Built for Spigot 1.13+.

---

## 🛍️ Features

- Track any player with an on-screen directional arrow
- Toggle distance display between tracker and target
- Simple and intuitive command usage
- Optimized for performance and cross-world safety

---

## 📚 What I Learned

While developing **BetterTracking**, I learned how to calculate **relative rotation** between two players (the tracker and the tracked). This was used to determine which direction the tracker should look to face the tracked player, and display an appropriate arrow (⬆️🔽⬅️➡ etc.).

Here’s the core of the directional logic:

```java
Location trackerLoc = tracker.clone();
Location trackedLoc = tracked.clone();

if (trackerLoc.getWorld().getEnvironment() != trackedLoc.getWorld().getEnvironment())
    return "•"; // Different dimensions

trackerLoc.setY(0);
trackedLoc.setY(0);

Vector d = trackerLoc.getDirection();
Vector v = trackedLoc.subtract(trackerLoc).toVector().normalize();

double a = Math.toDegrees(Math.atan2(d.getX(), d.getZ()));
a -= Math.toDegrees(Math.atan2(v.getX(), v.getZ()));
a = (int) (a + 22.5) % 360;

if (a < 0)
    a += 360;

return Character.toString("⬆️⮗➡️⮚🔽⬅️⮘".charAt((int) a / 45));
```

### 🔍 Explanation:

- The two locations are flattened (Y = 0) to avoid vertical distortion.
- We calculate the direction vector the tracker is facing (`d`).
- We calculate the normalized vector from the tracker to the tracked player (`v`).
- We convert both to angles, subtract them to find the difference in rotation.
- That angle is converted into one of eight directional Unicode arrows.

This method ensures the arrow always shows the closest 45-degree direction toward the target, making tracking intuitive and easy to follow.

---

## ⚙️ Commands

| Command                  | Description                                          |
|--------------------------|------------------------------------------------------|
| `/track <player>`        | Start tracking the specified player                 |
| `/track STOP`            | Stop tracking your current target                   |
| `/track distance ON`     | Show distance between you and the tracked player    |
| `/track distance OFF`    | Hide distance display                               |

---

## ✅ Compatibility

- Minecraft 1.13+
- Paper / Spigot-based servers

---

## 📂 Installation

1. Download the latest release `.jar` from the [Releases](https://github.com/mingolino/better-tracking-plugin/releases) page.
2. Drop it into your server’s `/plugins` folder.
3. Restart or reload your server.
4. You’re good to go!

---

## 💡 Ideas / Contributions

If you have suggestions or want to improve the plugin, feel free to open an [issue](https://github.com/mingolino/better-tracking-plugin/issues) or submit a pull request!

