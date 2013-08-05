package br.com.exemplo.androidsqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
	}
	
	public void adicionar(View view) {
		Intent intent = new Intent(this, Adicionar.class);
		startActivity(intent);
	}
	
	public void listar(View view) {
		Intent intent = new Intent(this, Listar.class);
		startActivity(intent);
	}
}
