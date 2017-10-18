package com.example.lenovo.sitra;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import  com.example.lenovo.sitra.card.Storage;

public class CardFragment extends Fragment {
	
	public static final String TAG = "CardFragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	}
	
	public static CardFragment newInstance() {
		CardFragment fragment = new CardFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_card, container, false);
		EditText account = (EditText) v.findViewById(R.id.card_account_field);
		account.setText(Storage.GetAccount(getActivity()));
		account.addTextChangedListener(new AccountUpdater());
		return v;
	}
	
	private class AccountUpdater implements TextWatcher {
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// Not implemented.
		}
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// Not implemented.
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			String account = s.toString();
			Storage.SetAccount(getActivity(), account);
		}
	}
	
}
