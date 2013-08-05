package br.com.exemplo.androidsqlite;

import br.com.exemplo.androidsqlite.bancodedados.Banco;
import br.com.exemplo.androidsqlite.model.Pessoa;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Adicionar extends Activity {

	private Pessoa pessoa;
	private Banco banco;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adicionar);
		
		this.pessoa = new Pessoa();
		this.banco = new Banco(getApplicationContext());
	}
	
	public void salvar(View view) {
		
		EditText nome = (EditText)findViewById(R.id.editTextNome);
		this.pessoa.setNome(nome.getText().toString());
		
		EditText telefone = (EditText)findViewById(R.id.editTextTelefone);
		this.pessoa.setTelefone(telefone.getText().toString());
		
		if(this.banco.insert(this.pessoa)) {
			Toast.makeText(getApplicationContext(), "Pessoa Cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
			finish();
		} else {
			Toast.makeText(getApplicationContext(), "Ocorreu um erro ao Cadastrar uma Pessoa!", Toast.LENGTH_SHORT).show();
		}
	}
}
