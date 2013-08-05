package br.com.exemplo.androidsqlite.bancodedados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Banco {

	private static final String DATABASE_NAME = "exemploSQLite";
	private static final int DATABASE_ACESS = 0;
	
	private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS pessoa(" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"nome TEXT," +
			"telefone TEXT )";
	
	private static final String SQL_INSERT = "INSERT INTO pessoa (nome, telefone) VALUES ('%s', '%s')";
	private static final String SQL_SELECT_ALL = "SELECT * FROM pessoa ORDER BY nome";
	
	private SQLiteDatabase banco;
	private Cursor cursor;
	
	private int indexId;
	private int indexNome;
	private int indexTelefone;
	
	public Banco(Context context) {
		this.banco = context.openOrCreateDatabase(DATABASE_NAME, DATABASE_ACESS, null);
	}
}
