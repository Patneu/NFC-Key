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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import pl.net.szafraniec.NFCKey.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.util.Linkify;
import android.widget.TextView;

public class AboutDialog extends Dialog{

	private static Context mContext = null;
	
	public AboutDialog(Context context) {
		super(context);
		mContext = context;
	}
	
	/**
     * This is the standard Android on create method that gets called when the activity initialized.
     */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.about);
		TextView tv = (TextView)findViewById(R.id.legal_text);
		//tv.setText(readRawTextFile(R.raw.legal));
		tv.setText(Html.fromHtml(readRawTextFile(R.raw.legal)));
		Linkify.addLinks(tv, Linkify.ALL);
		tv = (TextView)findViewById(R.id.info_text);
		tv.setText(Html.fromHtml(readRawTextFile(R.raw.info)+MainActivity.version));
		tv.setLinkTextColor(Color.WHITE);
		Linkify.addLinks(tv, Linkify.ALL);
		
	}
	
	public static String readRawTextFile(int id) {
		InputStream inputStream = mContext.getResources().openRawResource(id);
        InputStreamReader in = new InputStreamReader(inputStream);
        BufferedReader buf = new BufferedReader(in);
        String line;
        StringBuilder text = new StringBuilder();
        try {
        	while (( line = buf.readLine()) != null) text.append(line);
         } catch (IOException e) {
            return null;
         }
         return text.toString();
     }

}
