/**
* Copyright (C) 2014 Mateusz Szafraniec
* This file is part of NFCKey.
*
* NFCKey is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License, or
* (at your option) any later version.
*
* NFCKey is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with NFCKey; if not, write to the Free Software
* Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
*
* Ten plik jest cz�ci� NFCKey.
*
* NFCKey jest wolnym oprogramowaniem; mo�esz go rozprowadza� dalej
* i/lub modyfikowa� na warunkach Powszechnej Licencji Publicznej GNU,
* wydanej przez Fundacj� Wolnego Oprogramowania - wed�ug wersji 2 tej
* Licencji lub (wed�ug twojego wyboru) kt�rej� z p�niejszych wersji.
*
* Niniejszy program rozpowszechniany jest z nadziej�, i� b�dzie on
* u�yteczny - jednak BEZ JAKIEJKOLWIEK GWARANCJI, nawet domy�lnej
* gwarancji PRZYDATNO�CI HANDLOWEJ albo PRZYDATNO�CI DO OKRE�LONYCH
* ZASTOSOWA�. W celu uzyskania bli�szych informacji si�gnij do
* Powszechnej Licencji Publicznej GNU.
*
* Z pewno�ci� wraz z niniejszym programem otrzyma�e� te� egzemplarz
* Powszechnej Licencji Publicznej GNU (GNU General Public License);
* je�li nie - napisz do Free Software Foundation, Inc., 59 Temple
* Place, Fifth Floor, Boston, MA  02110-1301  USA
*/
package pl.net.szafraniec.NFCKey;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import pl.net.szafraniec.NFCKey.R;

public class DonateActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_donate);

	    ImageView iv = (ImageView) findViewById(R.id.bitcoin);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View self) {
            	String url = getString(R.string.donateBitcoin_uri);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                //return false;
            }
        });
        
	    ImageView pp = (ImageView) findViewById(R.id.paypal);
        pp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View self) {
            	String url = getString(R.string.donatePayPal_uri);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                //return false;
            }
        });
	    // TODO Auto-generated method stub
	}

}
