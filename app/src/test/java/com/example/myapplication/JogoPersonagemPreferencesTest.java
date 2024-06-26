package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.core.app.ActivityScenario;


import org.junit.runner.RunWith;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class JogoPersonagemPreferencesTest {

    private static final String PREFERENCES_NAME = "JogosFiltroPreferences";
    private static final String LAST_ACTIVITY_KEY = "lastActivity";

    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        sharedPreferences = ApplicationProvider.getApplicationContext()
                .getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Test
    public void testToggleActivity() {
        sharedPreferences.edit().putBoolean(LAST_ACTIVITY_KEY, true).apply();

        try (ActivityScenario<JogoPersonagem> scenario = ActivityScenario.launch(JogoPersonagem.class)) {
            scenario.onActivity(activity -> {
                activity.toggleActivity();
                boolean lastWasPersonagem1 = sharedPreferences.getBoolean(LAST_ACTIVITY_KEY, true);
                assertFalse(lastWasPersonagem1);

                activity.toggleActivity();
                lastWasPersonagem1 = sharedPreferences.getBoolean(LAST_ACTIVITY_KEY, false);
                assertTrue(lastWasPersonagem1);
            });
        }
    }
}
