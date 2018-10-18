package io.alexanderschaefer.architectureexample.base;

import android.os.Bundle;

import java.util.UUID;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.alexanderschaefer.architectureexample.di.Injector;

public abstract class BaseActivity extends AppCompatActivity {

    private String instanceId;
    private static final String INSTANCE_ID_KEY = "instance_id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
        } else {
            instanceId = UUID.randomUUID().toString();
        }
        Injector.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_ID_KEY, instanceId);
    }

    public String getInstanceId() {
        return instanceId;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isFinishing()) {
            Injector.clearComponent(this);
        }
    }
}
