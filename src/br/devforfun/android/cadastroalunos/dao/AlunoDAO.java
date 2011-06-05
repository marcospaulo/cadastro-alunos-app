package br.devforfun.android.cadastroalunos.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.devforfun.android.cadastroalunos.model.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {

	private static final int VERSAO = 1;
	private static final String TABELA = "Aluno";
	private static final String[] COLS = { "id", "nome", "telefone",
			"endereco", "site", "nota", "foto" };

	public AlunoDAO(Context context) {
		super(context, TABELA, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABELA + " ");
		sb.append("(id INTEGER PRIMARY KEY, ");
		sb.append(" nome TEXT UNIQUE NOT NULL, ");
		sb.append(" telefone TEXT, ");
		sb.append(" endereco TEXT, ");
		sb.append(" site TEXT, ");
		sb.append(" nota REAL, ");
		sb.append(" foto TEXT);");
		db.execSQL(sb.toString());

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		StringBuilder sb = new StringBuilder();
		sb.append("DROP TABLE IF EXISTS " + TABELA);
		db.execSQL(sb.toString());
		onCreate(db);
	}

	public void inserir(Aluno aluno) {
		ContentValues values = new ContentValues();
		values.put("nome", aluno.getNome());
		values.put("telefone", aluno.getTelefone());
		values.put("endereco", aluno.getEndereco());
		values.put("site", aluno.getSite());
		values.put("nota", aluno.getNota());
		values.put("foto", aluno.getFoto());
		getWritableDatabase().insert(TABELA, null, values);
	}

	public List<Aluno> getLista() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		Cursor c = getWritableDatabase().query(TABELA, COLS, null, null, null,
				null, null);

		while (c.moveToNext()) {
			Aluno aluno = new Aluno();
			aluno.setId(c.getLong(0));
			aluno.setNome(c.getString(1));
			aluno.setTelefone(c.getString(2));
			aluno.setEndereco(c.getString(3));
			aluno.setSite(c.getString(4));
			aluno.setNota(c.getDouble(5));
			aluno.setFoto(c.getString(6));

			alunos.add(aluno);
		}
		c.close();

		return alunos;
	}

	public void deletar(Aluno aluno) {
		getWritableDatabase().delete(TABELA, "id=?",
				new String[] { aluno.getId().toString() });
	}

}
