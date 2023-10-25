# CosmeticsCoreBridge
**CosmeticsCoreBridge**는 CosmeticsCore 플러그인 API의 사용을 돕는 스크립트 애드온입니다.   
**CosmeticsCoreBridge** is a Skript addon plugin that helps you use the API of the CosmeticsCore plugin in Skript.

## 다운로드(Download)
플러그인 다운로드는 [SpigotMC](https://www.spigotmc.org/resources/cosmeticscorebridge.112866/)에서 가능합니다.   
You can download the plugin from the [SpigotMC](https://www.spigotmc.org/resources/cosmeticscorebridge.112866/).

## 필수 플러그인(Needs Plugins)
*  [Skript](https://github.com/SkriptLang/Skript/releases)
*  [CosmeticsCore](https://www.spigotmc.org/resources/cosmeticscore.105324/)
*  [LuckPerms](https://luckperms.net/download)

## 디스코드(Bug Report)
모든 문의, 질문, 개발 요청은 [디스코드](https://discord.gg/hUkaca9ZQu)에서 해주시길 바랍니다. [한국인은 여기 이용]   
Please report bugs to [Discord Server](https://discord.gg/EpkqPZSpjr).

## 사용법(How to use)

### 치장품(Cosmetics)
약간의 꼼수를 사용하여 만든 스크립트 구문입니다. 서버에 유저가 한명도 없으면 작동하지 않습니다.   
This is a skript syntax created with a little trick. It will not work if there is not even one player on the server.
```
# 모든 치장품 가져오기(get all cosmetics)
set {_cosmetics::*} to all cosmetics

# 치장품 랜덤으로 가져오기(get random cosmetics)
set {_cosmetics} to random cosmetics

# CosmeticsCore의 key로 치장품 가져오기(get cosmetics by key)
set {_cosmetics} to cosmetics by "chef_hat"
```
### 치장품 조건문(Cosmetics Condition)
```
# 치장품이 등록됐는지(check cosmetics is registered)
command /isCosmetics [<Text>]:
    trigger:
        set {_cosmetics} to cosmetics by arg-1
        if {_cosmetics} is cosmetics: # or if arg-1 is cosmetics:
            send "%arg-1% is cosmetics"
        else:
            send "%arg-1% is not cosmetics"

# 유저가 옷장 안에 있는지(check player is in wardrobe)
command /isInWardrobe [<Player>]:
    trigger:
        if arg-1 is in wardrobe:
            send "%arg-1% is in!!"
        else:
            send "%arg-1% is out!!"
```
### 치장품 정보(Cosmetics Info)
```
set {_cosmetics} to random cosmetics

# 치장품의 key(get cosmetics's key)
set {_key} to {_cosmetics}'s key

# 치장품의 이름(get cosmetics's display)
set {_display} to {_cosmetics}'s display

# 치장품 아이템(get cosmetics's item)
set {_model} to {_cosmetics}'s model
```
### 치장품 착용(Cosmetics Equip)
테스트 또는 관리자 이외의 유저에게는 이 구문을 사용하지 않는 것을 권장드립니다.

예를 들어, 'equip' 구문으로 'backpack'을 장착하고, 유저가 장착한 치장품을 출력 시 'backpack' 2개가 출력됩니다.   
이후 'unequip' 구문을 사용하여 치장품을 제거하면 하나만 제거됩니다.

해당 현상은 등에 장착되는 치장품이 일반/셀프 모델 두가지로 나뉘기 때문에 발생하는 것으로 추측했었습니다.   
다만, 머리에 장착되는 치장품은 하나의 모델만 사용하지만 동일한 현상이 발생하기 때문에 CosmeticsCore 플러그인의 API 문제인 것으로 생각됩니다.   
(아니면 제가 못 만든 걸 수도 있습니다.)

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
### 유저 치장품(Player Cosmetics)
```
# 유저가 보유 중인 치장품 가져오기(get player's cosmetics)
set {_playerCosmetics::*} to player's cosmetics

# 유저가 장착 중인 치장품 가져오기(get player's equipped cosmetics)
set {_playerEquippedCosmetics::*} to player's equipped cosmetics
```
### 유저 치장품 권한 수정(Player Cosmetics Permission Modify)
유저의 치장품 권한을 추가/제거 하는 구문입니다.   
Simply add/remove permissions to use cosmetics.
```
set {_cosmetic} to random cosmetics

# add [cosmetics|string(cosmetics's key)] to player's cosmetics
add {_cosmetic} to player's cosmetics
add "chef_hat" to player's cosmetics

# remove [cosmetics|string(cosmetics's key)] from player's cosmetics
remove {_cosmetic} from player's cosmetics
remove "chef_hat" from player's cosmetics

# 유저의 모든 치장품 권한 제거(clear cosmetics)
clear player's cosmetics
delete player's cosmetics
```
