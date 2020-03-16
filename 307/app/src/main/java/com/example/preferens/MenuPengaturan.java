package com.example.preferens;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MenuPengaturan extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.menu_pengaturan);
    }
}
