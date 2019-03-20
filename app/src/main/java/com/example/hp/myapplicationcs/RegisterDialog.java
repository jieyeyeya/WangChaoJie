package com.example.hp.myapplicationcs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hp.myapplicationcs.R;
import com.example.hp.myapplicationcs.App;

public class RegisterDialog extends DialogFragment {
    private EditText re_username, re_password, re_again_password;
    private Button re_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_my2018shiti1, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        re_username = view.findViewById(R.id.phone_num_Edtb);
        re_password = view.findViewById(R.id.pswd_Edtb);
        re_again_password = view.findViewById(R.id.confirm_pswd_Edtb);
        re_btn = view.findViewById(R.id.reg_Btnb);
        re_btn.setOnClickListener(new Click());
    }

    class Click implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.reg_Btnb) {
                if (!re_username.getText().toString().equals("") && !re_password.getText().toString().equals("") && !re_again_password.getText().toString().equals("")) {
                    if (re_password.getText().toString().equals(re_again_password.getText().toString())) {
                        App.setUserName(re_username.getText().toString());
                        App.setPassword(re_password.getText().toString());
                        RegisterDialog.this.dismiss();
                        App.showAlertDialog(getContext(), "提醒", "注册成功", null);
                    } else
                        App.showAlertDialog(getContext(), "提醒", "两次密码不同", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                re_password.setText("");
                                re_again_password.setText("");
                            }
                        });
                } else {
                    App.showAlertDialog(getContext(), "提醒", "账号密码不能为空", null);
                }
            }
        }
    }
}
