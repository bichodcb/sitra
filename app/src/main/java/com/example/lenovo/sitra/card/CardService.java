package com.example.lenovo.sitra.card;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;
import java.util.Arrays;

public class CardService extends HostApduService {
	private static final String TAG = "CardService";
	private static final String SAMPLE_LOYALTY_CARD_AID = "F0010203040506";
	private static final String SELECT_APDU_HEADER = "00A40400";
	private static final byte[] SELECT_OK_SW = HexStringToByteArray("9000");
	private static final byte[] UNKNOWN_CMD_SW = HexStringToByteArray("0000");
	private static final byte[] SELECT_APDU = BuildSelectApdu(SAMPLE_LOYALTY_CARD_AID);
	
	@Override
	public void onDeactivated(int reason) { }
	
	@Override
	public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
		if (Arrays.equals(SELECT_APDU, commandApdu)) {
			String account = Storage.GetAccount(this);
			byte[] accountBytes = account.getBytes();
			return ConcatArrays(accountBytes, SELECT_OK_SW);
		} else {
			//return UNKNOWN_CMD_SW;
			String account = Storage.GetAccount(this);
			byte[] accountBytes = account.getBytes();
			return ConcatArrays(accountBytes, SELECT_OK_SW);
		}
	}
	
	public static byte[] BuildSelectApdu(String aid) {
		return HexStringToByteArray(SELECT_APDU_HEADER + String.format("%02X", aid.length() / 2) + aid);
	}
	
	public static byte[] HexStringToByteArray(String s) throws IllegalArgumentException {
		int len = s.length();
		if (len % 2 == 1) {
			throw new IllegalArgumentException("Hex string must have even number of characters");
		}
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
		}
		return data;
	}
	public static byte[] ConcatArrays(byte[] first, byte[]... rest) {
		int totalLength = first.length;
		for (byte[] array : rest) {
			totalLength += array.length;
		}
		byte[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (byte[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}
}
