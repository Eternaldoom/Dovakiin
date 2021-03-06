package net.dovakiin.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import net.dovakiin.api.DovakiinAPI;
import net.dovakiin.client.gui.GUIOverlay;
import net.dovakiin.util.UpdateChecker;
import net.dovakiin.util.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RenderEvent {
	private boolean hasSeen;
	private final GUIOverlay gui = new GUIOverlay();

	@SubscribeEvent
	public void onRenderOverlay(RenderGameOverlayEvent event){
		if(event.isCancelable() || event.type != ElementType.EXPERIENCE) return;
		gui.draw();
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onPlayerLogin(EntityJoinWorldEvent e) {

		if (e.entity instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) e.entity;
			if (p.worldObj.isRemote) {
				if(!hasSeen) {
					try {
						if(!UpdateChecker.isOnline()){
							
							if (p.getDisplayName().equals("The_SlayerMC")) {
								p.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.DARK_PURPLE, "Oh hey! Look! A Developer!"));
								p.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.LIGHT_PURPLE, "Your internet crashed from how awesome you are."));
							} else {
								p.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.AQUA, "Thank you " + p.getDisplayName() + ", for downloading and playing" + DovakiinAPI.GREEN + " Dovakiin!"));
								p.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.AQUA, "[Version: " + Utils.MOD_VERSION + "]"));
								p.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.LIGHT_PURPLE, "Unable to check for latest version, you may want to check your internet connection!"));
							}
						}
						if (UpdateChecker.isUpdateAvailable() && UpdateChecker.isOnline()) {

							if (p.getDisplayName().equals("The_SlayerMC")) {
								p.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.DARK_PURPLE, "Oh hey! A Developer!"));
								p.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.YELLOW, "Wow, you don't even have the newest version of your own mod... Nice.."));
							} else {
								BufferedReader versionFile = new BufferedReader(new InputStreamReader(new URL("https://raw.github.com/TheSlayerMC/Dovakiin/master/Version.txt").openStream()));
								String curVersion = versionFile.readLine();
								p.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.AQUA, "Thank you " + p.getDisplayName() + ", for downloading and playing" + DovakiinAPI.GREEN + " Dovakiin!"));
								p.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.RED, "[Version: " + Utils.MOD_VERSION + "]"));
								p.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.YELLOW, "A Dovakiin update is avaliable."));
								p.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.YELLOW, "[New Version: " + curVersion + "]")); 
							}
						}
						if ((!UpdateChecker.isUpdateAvailable()) && UpdateChecker.isOnline()) {

							if (p.getDisplayName().equals("The_SlayerMC")) {
								p.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.DARK_PURPLE, "Oh hey! A Developer!"));
							} else {
								p.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.AQUA, "Thank you "  + p.getDisplayName() + ", for downloading and playing" + DovakiinAPI.GREEN + " Dovakiin!"));
								p.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.AQUA, "[Version: " + Utils.MOD_VERSION + "]"));
								p.addChatMessage(DovakiinAPI.addChatMessage(EnumChatFormatting.GREEN, "Dovakiin is up to date."));
							}
						}
					} catch (MalformedURLException e1) {
						e1.printStackTrace();

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				hasSeen = true;
			}
		}
	} 

}
