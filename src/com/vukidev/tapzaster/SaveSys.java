package com.vukidev.tapzaster;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ScrollView;
import android.os.Handler;
import android.media.MediaPlayer;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.content.Intent;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class SaveSys {
	private static String formatBigInt(BigInteger value) {
		String s = value.toString();
		int len = s.length();

		if (len <= 3) {
			return s; // 0 to 999
		} else if (len <= 6) {
			// Thousands (K)
			return s.substring(0, len - 3) + "." + s.substring(len - 3, len - 2) + "K";
		} else if (len <= 9) {
			// Millions (M)
			return s.substring(0, len - 6) + "." + s.substring(len - 6, len - 5) + "M";
		} else if (len <= 12) {
			// Billions (B)
			return s.substring(0, len - 9) + "." + s.substring(len - 9, len - 8) + "B";
		} else if (len <= 15) {
			// Trillions (T)
			return s.substring(0, len - 12) + "." + s.substring(len - 12, len - 11) + "T";
		} else {
			// Quadrillion and beyond (QA)
			return s.substring(0, len - 15) + "QA";
		}
	}
	public static void saveGame(Activity activity, int slot) {
		android.content.SharedPreferences prefs = activity.getSharedPreferences("ZasterPrefs", activity.MODE_PRIVATE);
		android.content.SharedPreferences.Editor editor = prefs.edit();

		// Use the slot number in the key name
		editor.putString("score_slot" + slot, SAVEMEM.SCORE.toString());
		editor.putString("phoneupg_slot" + slot, SAVEMEM.phonesupg.toString());
		editor.putString("techs_slot" + slot, SAVEMEM.Techzasters.toString());
		editor.putString("mods_slot" + slot, SAVEMEM.Mods.toString());
		editor.putInt("sdcard_slot" + slot, SAVEMEM.hasSDCard);
		editor.putInt("twrp_slot" + slot, SAVEMEM.hasTWRP);
		editor.putInt("roms_slot" + slot, SAVEMEM.hasROMs);
		
		// Remember which slot was used last for auto-loading
		editor.putInt("lastslot", slot);
		
		editor.commit(); 
	}
	public static void loadGame(Activity activity, android.widget.TextView StatusText) {
		android.content.SharedPreferences prefs = activity.getSharedPreferences("ZasterPrefs", activity.MODE_PRIVATE);
		
		int slot = prefs.getInt("lastslot", 1);
		
		String savedScore = prefs.getString("score_slot" + slot, "0");
		String savedPhones = prefs.getString("phoneupg_slot" + slot, "0");
		String savedTech = prefs.getString("techs_slot" + slot, "1");
		String savedMods = prefs.getString("mods_slot" + slot, "1");
		SAVEMEM.hasSDCard = prefs.getInt("sdcard_slot" + slot, 0);
		SAVEMEM.hasTWRP = prefs.getInt("twrp_slot" + slot, 0);
		SAVEMEM.hasROMs = prefs.getInt("roms_slot" + slot, 0);
		
		int savedFINALMULTINT = prefs.getInt("MULTFIN_slot" + slot, 20);
		//String saved = prefs.getString("_slot" + slot, "1");

		SAVEMEM.SCORE = new java.math.BigInteger(savedScore);
		SAVEMEM.phonesupg = new java.math.BigInteger(savedPhones);
		SAVEMEM.Techzasters = new java.math.BigInteger(savedTech);
		SAVEMEM.Mods = new java.math.BigInteger(savedMods);
		
		SAVEMEM.phones = SAVEMEM.phonesupg; 
		if (SAVEMEM.hasSDCard == 1) {
			SAVEMEM.SuperUpgradeFinal = 200;
		}else {
			SAVEMEM.SuperUpgradeFinal = 20;
		}
		if (StatusText != null) {
			StatusText.setText(formatBigInt(SAVEMEM.SCORE));
		}
	}
	public static void saveToSlot(Activity activity, String slotName) {
		String scoreToSave = activity.getIntent().getStringExtra("score");
		String phonesToSave = activity.getIntent().getStringExtra("phones");
		String techToSave = activity.getIntent().getStringExtra("techs");
		String modsToSave = activity.getIntent().getStringExtra("mods");

		android.content.SharedPreferences prefs = activity.getSharedPreferences("ZasterPrefs", activity.MODE_PRIVATE);
		android.content.SharedPreferences.Editor editor = prefs.edit();

		int slotNum = slotName.contains("1") ? 1 : 2;

		editor.putString("score_slot" + slotNum, scoreToSave);
		editor.putString("phoneupg_slot" + slotNum, phonesToSave);
		editor.putString("techs_slot" + slotNum, techToSave);
		editor.putString("mods_slot" + slotNum, modsToSave);
		editor.putInt("sdcard_slot" + slotNum, SAVEMEM.hasSDCard);
		editor.putInt("twrp_slot" + slotNum, SAVEMEM.hasTWRP);
		editor.putInt("roms_slot" + slotNum, SAVEMEM.hasROMs);
		
		editor.putInt("lastslot", slotNum);
		
		editor.commit();
	}

    public static void loadFromSlot(Activity activity, String slotName) {
		android.content.SharedPreferences prefs = activity.getSharedPreferences("ZasterPrefs", activity.MODE_PRIVATE);
		android.content.SharedPreferences.Editor editor = prefs.edit();

		int slotNum = slotName.contains("1") ? 1 : 2;

		editor.putInt("lastslot", slotNum);
		editor.commit();
	}
}
