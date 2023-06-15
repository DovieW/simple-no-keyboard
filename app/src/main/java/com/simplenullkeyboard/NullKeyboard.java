package com.simplenullkeyboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

public class NullKeyboard extends InputMethodService {

    @SuppressLint("MissingSuperCall")
    @Override
    public boolean onEvaluateInputViewShown() {
        return false;
    }

    @Override
    public void hideWindow() {
        this.getWindow().getWindow().setSoftInputMode(InputMethodManager.HIDE_NOT_ALWAYS);
        super.hideWindow();
    }

    @Override
    public void onStartInputView(EditorInfo attribute, boolean restarting) {
        super.onStartInputView(attribute, restarting);
        Intent serviceIntent = new Intent(this, SwitchService.class);
        startForegroundService(serviceIntent);
    }

    @Override
    public void onFinishInput() {
        super.onFinishInput();
        Intent serviceIntent = new Intent(this, SwitchService.class);
        stopService(serviceIntent);
    }
}
