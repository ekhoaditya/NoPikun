package com.example.nopikun.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nopikun.Activity.PasswordActivity;
import com.example.nopikun.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;
import java.util.Random;

import androidx.annotation.NonNull;

import static com.example.nopikun.Constants.PASSWORD_VALUE_EXTRA;


public class BottomFragment extends BottomSheetDialogFragment {




    public BottomFragment()  {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);

        final EditText edLength = view.findViewById(R.id.ed_length);
        Button btnGenerate = view.findViewById(R.id.btn_generate);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edLength.getText())){
                    Toast.makeText(getActivity(), Objects.requireNonNull(getActivity()).getResources().getString(R.string.enter_length), Toast.LENGTH_SHORT).show();
                    return;
                }
                int length = Integer.parseInt(edLength.getText().toString());
                String password = String.valueOf(passwordGenerator(length));
                startActivity(password);

            }
        });

        return view;
    }

    private void startActivity(String password){
        Intent intent = new Intent(getActivity(), PasswordActivity.class);
        intent.putExtra(PASSWORD_VALUE_EXTRA,password);
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }

    private static char[] passwordGenerator(int length){

        String lCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String LSmall = "abcdefghijklmnopqrstuvwxyz";
        String number = "0123456789";
        String symbols = "!@#$%^&*/=+-.?<>)";

        String passWordSym= lCaps + LSmall + number + symbols;

        Random random = new Random();

        char[] password = new char[length];
        for(int i = 0 ; i < length ; i++){
            password[i] = passWordSym.charAt(random.nextInt(passWordSym.length()));
        }
        return password;
    }
}
