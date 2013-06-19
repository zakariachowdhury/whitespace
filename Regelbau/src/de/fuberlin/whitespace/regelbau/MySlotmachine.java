package de.fuberlin.whitespace.regelbau;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Zeigt eine Slotmachine an.
 * @author Maria, Christoph
 *
 */
public class MySlotmachine {
	Context context;
	/**
	 * 
	 * @param context
	 * @param listview Die zu ersetzende Listview, die nachher wieder rekonstruiert wird.
	 * @param ueberschrift1
	 * @param values1 Werte für die 1. Spalte
	 * @param ueberschrift2
	 * @param values2 Werte für die 2. Spalte
	 * @param ueberschrift3
	 * @param values3 Werte für die 3. Spalte
	 */
	public MySlotmachine(Context context, final ListView listview,
			String ueberschrift1, String[] values1, String ueberschrift2,
			String[] values2, String ueberschrift3, String[] values3) {
		super();
		this.context = context;
		this.listview = listview;
		this.ueberschrift1 = ueberschrift1;
		this.values1 = values1;
		this.ueberschrift2 = ueberschrift2;
		this.values2 = values2;
		this.ueberschrift3 = ueberschrift3;
		this.values3 = values3;
		
		final LinearLayout machine = (LinearLayout) View.inflate(context, R.layout.myslotmachine, null);
		machine.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		((ViewGroup)listview.getParent()).addView(machine);
		listview.setVisibility(View.GONE);
		Button button = (Button)machine.findViewById(R.id.kleinerFertigButton);
	    button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				listview.setVisibility(View.VISIBLE);
				((ViewGroup)listview.getParent()).removeView(machine);
			}
		});
	    
	    ListView lv1 = (ListView)machine.findViewById(R.id.listView1);
	    ListView lv2 = (ListView)machine.findViewById(R.id.listView2);
	    ListView lv3 = (ListView)machine.findViewById(R.id.listView3);
	    
	    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);
	    adapter1.addAll(values1);
	    lv1.setAdapter(adapter1);
	    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);
	    adapter2.addAll(values2);
	    lv2.setAdapter(adapter2);
	    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);
	    adapter3.addAll(values3);
	    lv3.setAdapter(adapter3);
	}
	
	View slotmachine;
	ListView listview;
	
	String ueberschrift1;String[] values1;
	String ueberschrift2;String[] values2;
	String ueberschrift3;String[] values3;
	
}
