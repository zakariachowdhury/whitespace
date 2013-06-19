package de.fuberlin.whitespace.regelbau.listenlogik;

import java.util.Vector;

import de.fuberlin.whitespace.regelbau.Satzanzeige;
import de.fuberlin.whitespace.regelbau.R.drawable;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ebene1listener_zeigemir implements OnItemClickListener {

	private View obereregelbuttonview;
	private ListView listview;
	private Satzanzeige satzanzeige;
	
	public ebene1listener_zeigemir(View obereregelbuttonview, ListView listview){
		this.obereregelbuttonview = obereregelbuttonview;
		this.listview = listview;
		
		listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); //Multichoice einstellen
		//Hinzufgüen von FERTIG am Ende der Liste.
		Adapter adapter = listview.getAdapter();
		String[] copyarray = new String[adapter.getCount()+1];
		for(int i = 0; i < adapter.getCount(); i++) 
			copyarray[i] = (String)adapter.getItem(i);
		//TODO "F E R T I G" weg und Button dahin
		copyarray[copyarray.length-1] = "F E R T I G";
		ArrayAdapter<String> adapterneu = new ArrayAdapter<String>(listview.getContext(),android.R.layout.simple_list_item_multiple_choice,copyarray);
		listview.setAdapter(adapterneu);
		// OK fertig hinzugefügt.
		
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Drawable img = listview.getContext().getResources().getDrawable( drawable.mycheck );
		if(((String)((TextView)arg1).getText()).contains("F E R T I G")){ // Fertig gedrückt.
			Vector<String> checkedItems = new Vector<String>();
			SparseBooleanArray checked = listview.getCheckedItemPositions();
			
			for (int i = 0; i < listview.getCount(); i++){
				if (checked.get(i)) {
					checkedItems.add((String) listview.getAdapter().getItem(i));
				}
			}	
			//listview.setAdapter(null);
			/** auskommentiert, da Baukasten leer sein soll, wenn auf Button geklickt wurde */
			String[] elemente = listview.getContext().getResources().getStringArray(de.fuberlin.whitespace.regelbau.R.array.Button3Array);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(listview.getContext(),android.R.layout.simple_list_item_1,elemente);
			listview.setAdapter(adapter);
			// Setze wieder den normalen onitemclick listener
			listview.setOnItemClickListener(new ebene0listener(obereregelbuttonview, listview));
			
			
			// Das als letztes ausgewählte Element war FERTIG, also entfernen.
			if(checkedItems.size() > 0) checkedItems.removeElementAt(checkedItems.size()-1);
			// Hier das Layout oben updaten .
			satzanzeige = (Satzanzeige)obereregelbuttonview;
			satzanzeige.getButtona().setCompoundDrawablesWithIntrinsicBounds(null, null, img, null );
			satzanzeige.getButtonb().setCompoundDrawablesWithIntrinsicBounds(null, null, img, null );
			satzanzeige.getButtonc().setCompoundDrawablesWithIntrinsicBounds(null, null, null, null );
			Object tmpElements[] = checkedItems.toArray();
			if(tmpElements.length>1)
				satzanzeige.setButtonLabelZwei(tmpElements[0]+" und "+tmpElements[1]+",");
			else satzanzeige.setButtonLabelZwei(tmpElements[0]+",");
		}else{
			/*
			 * Eines der Elemente wurde ausgewählt
			 */
			
		}
	}

}