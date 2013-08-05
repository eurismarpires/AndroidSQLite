package br.com.exemplo.androidsqlite;

import br.com.exemplo.androidsqlite.bancodedados.Banco;
import br.com.exemplo.androidsqlite.model.Pessoa;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Visualizar extends Activity {

	private Pessoa pessoa;
	private Banco banco;
	
	private TextView nome;
	private TextView telefone;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visualizar);
		
		this.banco = new Banco(getApplicationContext());
		
		if(getIntent().getSerializableExtra("pessoa") != null)
			this.pessoa = (Pessoa) getIntent().getSerializableExtra("pessoa");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		this.pessoa = this.banco.getPorId(this.pessoa.getId());
		
		this.nome = (TextView)findViewById(R.id.textViewNome);
		this.nome.setText("Nome: " + this.pessoa.getNome());
		
		this.telefone = (TextView)findViewById(R.id.textViewTelefone);
		this.telefone.setText("Telefone: " + this.pessoa.getTelefone());
	}
	
	public void deletar(View view) {
		if(this.banco.delete(this.pessoa.getId())) {
			Toast.makeText(getApplicationContext(), "Pessoa Deletada", Toast.LENGTH_SHORT).show();
			finish();
		} else {
			Toast.makeText(getApplicationContext(), "Erro ao deletar a Pessoa", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void editar(View view) {
		Intent intent = new Intent(Visualizar.this, Adicionar.class);
		intent.putExtra("pessoa", this.pessoa);
		startActivity(intent);
	}
}
