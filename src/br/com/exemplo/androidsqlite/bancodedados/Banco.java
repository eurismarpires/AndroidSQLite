package br.com.exemplo.androidsqlite.bancodedados;

import java.util.ArrayList;

import br.com.exemplo.androidsqlite.model.Pessoa;
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
	private static final String SQL_INSERT = "INSERT INTO pessoa (nome, telefone) VALUES ('%s', '%s')";
	private static final String SQL_SELECT_ALL = "SELECT * FROM pessoa ORDER BY nome";
	private static final String SQL_SELECT_NOME = "SELECT * FROM pessoa WHERE nome = '%s'";
	private static final String SQL_DELETE_ID = "DELETE FROM pessoa WHERE id = '%s'";
	private static final String SQL_UPDATE_ID = "UPDATE pessoa SET nome = '%s', telefone = '%s' WHERE id = '%s'";
	
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
	public void insert(Pessoa pessoa) {
		/* Inserção dos parâmetros na String do SQL INSERT. */
		String query = String.format(SQL_INSERT, pessoa.getNome(), pessoa.getTelefone());
		this.banco.execSQL(query);
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
	
	/* Método que edita um registro baseado no ID. */
	public void edit(Pessoa pessoa) {
		String query = String.format(SQL_UPDATE_ID, pessoa.getNome(), pessoa.getTelefone());
		this.banco.execSQL(query);
	}
	
	/* Método que deleta um registro baseado no ID. */
	public void delete(String id) {
		String query = String.format(SQL_DELETE_ID, id);
		this.banco.execSQL(query);
	}
	
	/* Método que carrega todos os índices das colunas. */
	public void configuraIndex() {
		this.indexId = this.cursor.getColumnIndex("id");
		this.indexNome = this.cursor.getColumnIndex("nome");
		this.indexTelefone = this.cursor.getColumnIndex("telefone");
	}
}
