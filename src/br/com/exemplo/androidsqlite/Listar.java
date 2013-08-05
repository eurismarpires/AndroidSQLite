package br.com.exemplo.androidsqlite;

import br.com.exemplo.androidsqlite.bancodedados.Banco;
import br.com.exemplo.androidsqlite.model.Pessoa;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Listar extends Activity {

	private ListView lista;
	private Banco banco;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listar);
		
		this.lista = (ListView)findViewById(R.id.listViewPessoas);
		this.banco = new Banco(getApplicationContext());
		ArrayAdapter<Pessoa> itens = new ArrayAdapter<Pessoa>(getApplicationContext(), 
				android.R.layout.simple_list_item_1, this.banco.getAll()); 
		
		this.lista.setAdapter(itens);
		this.lista.setClickable(true);
		this.lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				Toast.makeText(getApplicationContext(), adapter.getAdapter().getItem(position).toString(), 
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
