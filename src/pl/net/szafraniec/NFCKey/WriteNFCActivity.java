package pl.net.szafraniec.NFCKey;

import java.io.IOException;

import pl.net.szafraniec.NFCKey.R;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WriteNFCActivity extends Activity {
//private static final String LOG_TAG = "WriteNFCActivity";


    protected void onCreate(Bundle sis) {
        super.onCreate(sis);
        setContentView(R.layout.activity_write_nfc);

        setResult(0);
        Button b = (Button) findViewById(R.id.cancel_nfc_write_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View self) {
                nfc_disable();
                finish();
            }
        });
    }

    private void nfc_enable()
    {
        // Register for any NFC event (only while we're in the foreground)

        NfcAdapter adapter = NfcAdapter.getDefaultAdapter(this);
        PendingIntent pending_intent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        adapter.enableForegroundDispatch(this, pending_intent, null, null);
    }

    private void nfc_disable()
    {
        NfcAdapter adapter = NfcAdapter.getDefaultAdapter(this);

        adapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        nfc_enable();
    }

    @Override
    protected void onPause() {
        super.onPause();

        nfc_disable();
    }

    @Override
    public void onNewIntent(Intent intent)
    {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)) {
            int success = 0;

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            Ndef ndef = Ndef.get(tag);
            if (ndef != null) {
            try{
              ndef.connect();
              ndef.writeNdefMessage(WriteActivity.nfc_payload);
              ndef.close();
              success = 1;
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "IOExceptionWrite", Toast.LENGTH_SHORT).show();
          
            } catch (NullPointerException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "NullPointerWrite", Toast.LENGTH_SHORT).show();
             
            } catch (FormatException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "FormatExceptionWrite", Toast.LENGTH_SHORT).show();
            }
 
            } else {
              NdefFormatable format = NdefFormatable.get(tag);
              if (format != null) {
              try{  
                format.connect();
                format.format(WriteActivity.nfc_payload);
                format.close();
                success = 1;
              } catch (IOException e) {
                  e.printStackTrace();
                  Toast.makeText(getApplicationContext(), "IOExceptionFormat", Toast.LENGTH_SHORT).show();
            
              } catch (NullPointerException e) {
                  e.printStackTrace();
                  Toast.makeText(getApplicationContext(), "NullPointerFormat", Toast.LENGTH_SHORT).show();
               
              } catch (FormatException e) {
                  e.printStackTrace();
                  Toast.makeText(getApplicationContext(), "FormatExceptionFormat", Toast.LENGTH_SHORT).show();
              	}
              }} 
            setResult(success);
            finish();
        }
    }
}
