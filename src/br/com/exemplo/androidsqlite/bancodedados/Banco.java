package br.com.exemplo.androidsqlite.bancodedados;

import java.util.ArrayList;

import br.com.exemplo.androidsqlite.model.Pessoa;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Banco {

	/* Nome do Banco de Dados */
	private static final String DATABASE_NAME = "exemploSQLite";
	
	/* Modo de acesso ao banco de dados
	 * 
	 * Configura as permissões de acesso ao banco de dados.
	 * 
	 * 0 - Modo privado (apenas essa aplicação pode usar o banco).
	 * 1 - Modo leitura para todos (outras aplicações podem usar o banco).
	 * 2 - Modo escrita para todos (outras aplicações podem usar o banco). */
	private static final int DATABASE_ACESS = 0;
	
	/* SQL de criação do banco de dados. */
	private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS pessoa(" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"nome TEXT," +
			"telefone TEXT )";
	
	/* SQL INSERT, DELETE e UPDATE. */
	private static final String SQL_SELECT_ALL = "SELECT * FROM pessoa ORDER BY nome";
	private static final String SQL_SELECT_NOME = "SELECT * FROM pessoa WHERE nome = '%s'";
	private static final String SQL_SELECT_ID = "SELECT * FROM pessoa WHERE id = '%s'";
	
	/* Classe com métodos para executar os comandos SQL e manipular o banco de dados. */
	private SQLiteDatabase banco;
	/* Interface que permite acesso aos dados retornados pelo banco de dados. */
	private Cursor cursor;
	
	/* Índice das colunas no banco de dados */
	private int indexId;
	private int indexNome;
	private int indexTelefone;
	
	/* É necessário passar o Context da aplicação para a criação do banco de dados. */
	public Banco(Context context) {
		this.banco = context.openOrCreateDatabase(DATABASE_NAME, DATABASE_ACESS, null);
		this.banco.execSQL(SQL_CREATE);
	}
	
	/* Método que insere uma classe no banco de dados. */
	public boolean insert(Pessoa pessoa) {
	
		/* Mapa com a coluna e os valores de cada coluna. */
		ContentValues values = new ContentValues();
		values.put("nome", pessoa.getNome());
		values.put("telefone", pessoa.getTelefone());
		
		/* Executa um insert no banco de dados usando o mapa de valores. */
		/* Retorna -1 caso ocorra algum erro no INSERT. */
		long retorno = this.banco.insert("pessoa", null, values);
		
		if(retorno != -1)
			return true;
		else
			return false;
	}
	
	/* Método que recupera todos os registro do banco de dados. */
	public ArrayList<Pessoa> getAll() {
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		Pessoa pessoa;
		
		this.cursor = this.banco.rawQuery(SQL_SELECT_ALL, null);
		configuraIndex();
		
		while(this.cursor.moveToNext()) {
			pessoa = new Pessoa(this.cursor.getString(this.indexId), 
								this.cursor.getString(this.indexNome), 
								this.cursor.getString(this.indexTelefone));
			pessoas.add(pessoa);
		}
		return pessoas;
	}
	
	/* Método que recupera todos os registro com um determinado nome no banco de dados. */
	public ArrayList<Pessoa> get(String nome) {
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		Pessoa pessoa;
		
		String query = String.format(SQL_SELECT_NOME, nome);
		this.cursor = this.banco.rawQuery(query, null);
		configuraIndex();
		
		while(this.cursor.moveToNext()) {
			pessoa = new Pessoa(this.cursor.getString(this.indexId), 
					this.cursor.getString(this.indexNome), 
					this.cursor.getString(this.indexTelefone));
			pessoas.add(pessoa);
		}
		return pessoas;
	}
	
	/* Método que recupera um registro pelo ID. */
	public Pessoa getPorId(String id) {
		Pessoa pessoa = null;
		
		String query = String.format(SQL_SELECT_ID, id);
		this.cursor = this.banco.rawQuery(query, null);
		configuraIndex();
		
		if(this.cursor.moveToFirst()) {
			pessoa = new Pessoa(this.cursor.getString(this.indexId), 
					this.cursor.getString(this.indexNome), 
					this.cursor.getString(this.indexTelefone));
		}
			
		return pessoa;
	}
	
	/* Método que edita um registro baseado no ID. */
	public boolean update(Pessoa pessoa) {
		ContentValues values = new ContentValues();
		values.put("nome", pessoa.getNome());
		values.put("telefone", pessoa.getTelefone());
		
		String where = "id = ?";
		String[] whereArgs = new String[] {pessoa.getId()}; 
		
		int retorno = this.banco.update("pessoa", values, where, whereArgs);
		
		if(retorno != 0)
			return true;
		else
			return false;
	}
	
	/* Método que deleta um registro baseado no ID. */
	public boolean delete(String id) {
		String where = "id = ?";
		String[] whereArgs = new String[] {id};
		
		int retorno = this.banco.delete("pessoa", where, whereArgs);
		
		if(retorno != 0)
			return true;
		else
			return false;
	}
	
	/* Método que carrega todos os índices das colunas. */
	public void configuraIndex() {
		this.indexId = this.cursor.getColumnIndex("id");
		this.indexNome = this.cursor.getColumnIndex("nome");
		this.indexTelefone = this.cursor.getColumnIndex("telefone");
	}
}
