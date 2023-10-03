# CosmeticsCoreBridge
**CosmeticsCoreBridge** is a Skript addon plugin that helps you use the API of the CosmeticsCore plugin in Skript.

## Download
You can download the plugin from the [SpigotMC](https://www.spigotmc.org/resources/cosmeticscorebridge.112866/).

## Needs Plugins
*  [Skript](https://github.com/SkriptLang/Skript/releases)
*  [CosmeticsCore](https://www.spigotmc.org/resources/cosmeticscore.105324/)
*  [LuckPerms](https://luckperms.net/download)

## Bug Report
Please report bugs to [Discord Server](https://discord.gg/EpkqPZSpjr).

## How to use

### Cosmetics
This is a skript syntax created with a little trick. It will not work if there is not even one player on the server.
```
# get all cosmetics
set {_cosmetics::*} to all cosmetics

# get random cosmetics
set {_cosmetics} to random cosmetics

# get cosmetics by key
set {_cosmetics} to cosmetics by "chef_hat"
```
### Cosmetics Condition
```
# check cosmetics is registered
command /isCosmetics [<Text>]:
    trigger:
        set {_cosmetics} to cosmetics by arg-1
        if {_cosmetics} is cosmetics: # or if arg-1 is cosmetics:
            send "%arg-1% is cosmetics"
        else:
            send "%arg-1% is not cosmetics"

# check player is in wardrobe
command /isInWardrobe [<Player>]:
    trigger:
        if arg-1 is in wardrobe:
            send "%arg-1% is in!!"
        else:
            send "%arg-1% is out!!"
```
### Cosmetics Info
```
set {_cosmetics} to random cosmetics

# get cosmetics's key
set {_key} to {_cosmetics}'s key

# get cosmetics's display
set {_display} to {_cosmetics}'s display

# get cosmetics's item
set {_model} to {_cosmetics}'s model
```
### Cosmetics Equip
We recommend that you do not use this syntax for players other than testing or admin.

For example, if you equip 'backpack' with the 'equip' syntax and output the player's equipped cosmetics, two 'backpack' will be output.
Afterwards, when you remove it using the 'unequip' syntax, only one is removed.

My guess is that this phenomenon occurs because the cosmetics mounted on the back are divided into two types: the general model and the self model.
However, since the same phenomenon occurs even though only one model is used for the cosmetics mounted on the head, I think it is an API issue with the CosmeticsCore plugin.
(or maybe I didn't make it.)
```
set {_cosmetics} to random cosmetics

# equip [cosmetics|string(cosmetics's key)] to player
equip {_cosmetics} to player
equip "chef_hat" to player

# unequip [cosmetics|string(cosmetics's key)] from player
unequip {_cosmetics} from player
unequip "chef_hat" from player
```
### Player Cosmetics
```
# get player's cosmetics
set {_playerCosmetics::*} to player's cosmetics

# get player's equipped cosmetics
set {_playerEquippedCosmetics::*} to player's equipped cosmetics
```
### Player Cosmetics (Modify)
Simply add/remove permissions to use cosmetics.
```
set {_cosmetic} to random cosmetics

# add [cosmetics|string(cosmetics's key)] to player's cosmetics
add {_cosmetic} to player's cosmetics
add "chef_hat" to player's cosmetics

# remove [cosmetics|string(cosmetics's key)] from player's cosmetics
remove {_cosmetic} from player's cosmetics
remove "chef_hat" from player's cosmetics

# clear cosmetics
clear player's cosmetics
delete player's cosmetics
```
