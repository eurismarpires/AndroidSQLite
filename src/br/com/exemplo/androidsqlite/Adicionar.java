package br.com.exemplo.androidsqlite;

import br.com.exemplo.androidsqlite.model.Pessoa;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Adicionar extends Activity {

	private Pessoa pessoa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adicionar);
		
		this.pessoa = new Pessoa();
	}
	
	public void salvar(View view) {
		
		EditText nome = (EditText)findViewById(R.id.editTextNome);
		this.pessoa.setNome(nome.getText().toString());
		
		EditText telefone = (EditText)findViewById(R.id.editTextTelefone);
		this.pessoa.setTelefone(telefone.getText().toString());
	}
}
