package com.lothus.sync.stats.menus.games.skywars.select.ability;

import com.lothus.core.Core;
import com.lothus.core.games.room.RoomType;
import com.lothus.core.player.LothPlayer;
import com.lothus.core.utils.bukkit.ItemCreator;
import com.lothus.sync.stats.data.type.DataType;
import com.lothus.sync.stats.games.addons.ability.Ability;
import com.lothus.sync.stats.platform.Platform;
import com.lothus.sync.stats.player.games.skywars.SkyPlayer;
import com.lothus.sync.stats.player.games.skywars.stats.SkyStats;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SelectAbilityMenu implements Listener {

    public static void open(Player player, RoomType type) {
        LothPlayer lothPlayer = Core.getPlayerController().get(player.getUniqueId());
        SkyPlayer skyPlayer = Platform.getSkyPlatform().getSkyPlayerController().getAccount(player.getUniqueId());
        Inventory inventory = Bukkit.createInventory(null, 9*6, "Selecione uma Hab. - Sky Wars " + type.getName());

        int slot = 9;
        for (Ability kit : Platform.getSkyPlatform().getAbilityController().getAbility()) {
            slot++;

            if (slot == 17) slot+=2;
            if (slot == 26) slot+=2;
            if (slot == 35) slot+=2;

            inventory.setItem(slot, kit.icon(player,type));
        }

        inventory.setItem(49, new ItemCreator(Material.ARROW, "§cVoltar").build());

        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack currentItem = event.getCurrentItem();

        if (inventory == null)return;
        if (currentItem == null)return;

        if (!inventory.getName().startsWith("Selecione uma Hab. - Sky Wars"))return;

        event.setCancelled(true);

        if (event.getRawSlot() == 49) {
            player.closeInventory();
            return;
        }

        if (currentItem.getType() == Material.AIR)return;

        net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(currentItem);

        if (!nms.hasTag())return;

        NBTTagCompound t = nms.getTag();
        if (!t.hasKey("type"))return;

        RoomType type = RoomType.valueOf(t.getString("type"));
        Ability k = Platform.getSkyPlatform().getAbilityController().getKit(t.getString("ability"));

        if (type == RoomType.SOLO) {
            SkyStats solo = Platform.getSkyPlatform().getSkyPlayerController().get(DataType.SKY_WARS_SOLO, player.getUniqueId());
            if (!solo.hasAbility(RoomType.SOLO, k)) {
                player.sendMessage("§cVocê não possui essa habilidade.");
                return;
            }
            if (solo.getAbility().equalsIgnoreCase(k.getIdentify())) {
                player.sendMessage("§cVocê já selecionou essa habilidade.");
                player.closeInventory();
                return;
            }
            solo.setAbility(k.getIdentify());
            Platform.getDataStats().update(DataType.SKY_WARS_SOLO, solo);
        } else if (type == RoomType.DUPLAS){
            SkyStats solo = Platform.getSkyPlatform().getSkyPlayerController().get(DataType.SKY_WARS_TEAM, player.getUniqueId());
            if (!solo.hasAbility(RoomType.DUPLAS, k)) {
                player.sendMessage("§cVocê não possui essa habilidade.");
                return;
            }
            if (solo.getAbility().equalsIgnoreCase(k.getIdentify())) {
                player.sendMessage("§cVocê já selecionou essa habilidade.");
                player.closeInventory();
                return;
            }
            solo.setAbility(k.getIdentify());
            Platform.getDataStats().update(DataType.SKY_WARS_TEAM, solo);
        }

        player.sendMessage("§eVocê selecionou §b" + k.getIdentify() + "§e.");
        player.closeInventory();
    }
}
