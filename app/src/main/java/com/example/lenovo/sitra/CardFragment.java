package com.example.lenovo.sitra;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.lenovo.sitra.card.Storage;

public class CardFragment extends Fragment {

    public static final String TAG = "CardFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_fragment, container, false);
        EditText account = (EditText) v.findViewById(R.id.card_account_field);
        account.setText(Storage.GetAccount(getActivity()));
        account.addTextChangedListener(new AccountUpdater());
        return v;
    }

    private class AccountUpdater implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            String account = s.toString();
            Storage.SetAccount(getActivity(), account);
        }
    }
}
