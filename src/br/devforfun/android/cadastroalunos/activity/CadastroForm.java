package br.devforfun.android.cadastroalunos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import br.devforfun.android.cadastroalunos.R;
import br.devforfun.android.cadastroalunos.dao.AlunoDAO;
import br.devforfun.android.cadastroalunos.model.Aluno;

public class CadastroForm extends Activity {

	private Aluno aluno = new Aluno();
	private Button inserirAlunoButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);

		inserirAlunoButton = (Button) findViewById(R.id.inserir_aluno);
		bind();
	}

	public void bind() {
		inserirAlunoButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				EditText nome = (EditText) findViewById(R.id.nome);
				EditText telefone = (EditText) findViewById(R.id.telefone);
				EditText site = (EditText) findViewById(R.id.site);
				RatingBar nota = (RatingBar) findViewById(R.id.nota);
				EditText endereco = (EditText) findViewById(R.id.endereco);
				
				aluno.setNome(nome.getEditableText().toString());
				aluno.setTelefone(telefone.getEditableText().toString());
				aluno.setSite(site.getEditableText().toString());
				aluno.setNota(nota.getRating());
				aluno.setEndereco(endereco.getEditableText().toString());
				
				AlunoDAO dao = new AlunoDAO(CadastroForm.this);
				dao.inserir(aluno);
				dao.close();
				
				finish();
			}
		});
	}
}
