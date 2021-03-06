package net.dovakiin.event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import net.dovakiin.client.gui.GuiBanned;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BannedEvent {
	
	private GuiBanned banned = new GuiBanned();
	
	@SubscribeEvent
	public void banned(RenderGameOverlayEvent e) throws MalformedURLException, IOException{
		if(isBanned(Minecraft.getMinecraft().thePlayer)){
			banned.draw();
		}
	}
	
	public boolean isBanned(EntityPlayer player) throws MalformedURLException, IOException{
		BufferedReader file = new BufferedReader(new InputStreamReader(new URL("https://raw.github.com/TheSlayerMC/Dovakiin/master/BannedPlayers.txt").openStream()));
		String name = file.readLine();
		file.close();
		
		if(!name.contains(player.getDisplayName())){
			return true;
		}else{
			return false;
		}
	}
	
}
