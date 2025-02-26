# About StarAPI
StarAPI is a library created to make minecraft plugins swiftly and with ease, its meant to simplify challenging and annoying tasks that normally are elongated to the point where it would take lets say 50 lines, this library is good for simplifying stuff down to its core. Wanting to scroll through items in a GUI? we got you covered! Wanting to create custom armor sets, mobs and so much more? we got you covered as well! This is truly the one library that works good on the latest versions of the game. So why struggle? Start using StarAPI today to solve all your annoying tedeous tasks!

# How can i install this?
You install this via maven or gradle, its super simple! check out how to install it below!

# Maven (pom.xml)
Add this to your repositories:
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

Add this to your dependencies:
```xml
<dependency>
    <groupId>com.github.SyntaxSama</groupId>
    <artifactId>StarAPI</artifactId>
    <version>LATEST</version>
</dependency>
```

# Gradle (build.gradle)
```xml
repositories {
    maven { url 'https://jitpack.io' }
}
```
```xml
dependencies {
    implementation 'com.github.SyntaxSama:StarAPI:<version>'
}
```

# Features
Features is what makes this library so great! these features allow for swift accessable features to help create plugins a lot faster then normal!

## Abilities
```yaml
Implosion
MultiShot
Transmition
```
## Builders
```yaml
Action Bars
Boss Bars
Animated Scoreboard # Its a little bit buggy hex colors will not work.
Armor Sets
Commands
Items
Messages # Hover events, click events, executable etc.
Mobs
Potions # Potions are restricted to the user who has the potion, meaning only the user will get the extra effects.
Webhooks
```
## Files
```yaml
Files # Soon to be removed (Replaced with YamlFile)
Folders # Soon to be removed (Replaced with YamlFile)
Read # Soon to be removed (Replaced with YamlFile)
Write # Soon to be removed (Replaced with YamlFile)
YamlFile # New way to create files, folders, read & writting to them.
```
## Inventory Utilities
```yaml
Animated Items # Animate through a list of items in 1 slot
Book Content # Allows the user for clickable, hoverable and runnable commands via books
Book GUIS
Filler # Fills a gui that can with a specific material (Automatically sets a black name and lore.
Inventory Items 
Inventory Name # This is just to get the name of the inventory you're in of the specific inventory.
Inventory Skulls # Put player heads into inventories
```
## Message Utilities
```yaml
Auto Responders
Message Colors # Used for hex colors and regular color codes using this function
```
## Misc
```yaml
Block Placer # Good for making gen buckets if you wanted to or for building walls faster.
Progress Bar
Rarities # These are predefined you cant add rarities since it basically has every rarity thats used till this date.
Skull Utilities # Give custom heads or a players head to someone
```
## Recipes
```yaml
Crafting
Furnace
```
## Timers
```yaml
Active Timers 
Cancel Timer
Delayed Timer
Timer
```
