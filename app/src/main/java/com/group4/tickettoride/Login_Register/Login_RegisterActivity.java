package com.group4.tickettoride.Login_Register;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.group4.tickettoride.ClientModel.ClientModel;
import com.group4.tickettoride.Network.ServerProxy;
import com.group4.tickettoride.R;

public class Login_RegisterActivity extends AppCompatActivity implements ILogin_RegisterActivity{

    EditText username;
    EditText password;
    EditText hostname;
    Button login;
    Button register;
    Login_RegisterPresenter presenter;
    View decorView;
    String ipAddress;

    public void setBackground(Context context, View view, int drawableId){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawableId);
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        view.setBackground(bitmapDrawable);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        setBackground(this, findViewById(R.id.login_main), R.drawable.papersmall);

        decorView = getWindow().getDecorView();



        this.presenter = new Login_RegisterPresenter(this);

        this.username = (EditText) findViewById(R.id.username_editText);
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("") && !password.getText().toString().equals(""))
                {
                    setLoginEnabled(true);
                    setRegisterEnabled(true);
                }
                else
                {
                    setLoginEnabled(false);
                    setRegisterEnabled(false);
                }
            }
        });

        this.password = (EditText) findViewById(R.id.password_editText);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("") && !username.getText().toString().equals(""))
                {
                    setLoginEnabled(true);
                    setRegisterEnabled(true);
                }
                else
                {
                    setLoginEnabled(false);
                    setRegisterEnabled(false);
                }
            }
        });

        this.hostname = (EditText) findViewById(R.id.hostname_editHost);
        hostname.setText("192.168.2.191");
        ipAddress = hostname.getText().toString();
        ServerProxy.SINGLETON.setIpAddress(ipAddress);
        hostname.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                ipAddress = editable.toString();
                ServerProxy.SINGLETON.setIpAddress(ipAddress);
            }
        });

        this.login = (Button) findViewById(R.id.login_button);
        login.setEnabled(false);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login();
            }
        });

        this.register = (Button) findViewById(R.id.register_button);
        register.setEnabled(false);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.register();
            }
        });

    } // end onCreate()

    @Override
    public String getUsername()
    {
        return this.username.getText().toString();
    }

    @Override
    public String getPassword()
    {
        return this.password.getText().toString();
    }

    @Override
    public void setLoginEnabled(Boolean enabled)
    {
        this.login.setEnabled(enabled);
    }

    @Override
    public void setRegisterEnabled(Boolean enabled)
    {
        register.setEnabled(enabled);
    }


    public void displayError(String error)
    {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
