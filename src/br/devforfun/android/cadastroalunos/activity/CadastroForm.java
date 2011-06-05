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

	private Aluno aluno;
	private Button inserirAlunoButton;

	private EditText nome;
	private EditText telefone;
	private EditText site;
	private RatingBar nota;
	private EditText endereco;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);

		aluno = (Aluno) getIntent().getSerializableExtra("alunoSelecionado");
		inserirAlunoButton = (Button) findViewById(R.id.inserir_aluno);

		if (aluno == null) {
			aluno = new Aluno();
		} else {
			inserirAlunoButton.setText("Alterar");
			carregarViews();
			preencherTela();
		}

		bind();
	}

	public void bind() {
		inserirAlunoButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				carregarViews();
				aluno.setNome(nome.getEditableText().toString());
				aluno.setTelefone(telefone.getEditableText().toString());
				aluno.setSite(site.getEditableText().toString());
				aluno.setNota(nota.getRating());
				aluno.setEndereco(endereco.getEditableText().toString());

				AlunoDAO dao = new AlunoDAO(CadastroForm.this);

				dao.insereOuAltera(aluno);
				dao.close();

				finish();
			}
		});
	}

	private void carregarViews() {
		nome = (EditText) findViewById(R.id.nome);
		telefone = (EditText) findViewById(R.id.telefone);
		site = (EditText) findViewById(R.id.site);
		nota = (RatingBar) findViewById(R.id.nota);
		endereco = (EditText) findViewById(R.id.endereco);
	}

	private void preencherTela() {
		nome.setText(aluno.getNome());
		telefone.setText(aluno.getTelefone());
		site.setText(aluno.getSite());
		nota.setRating(Float.parseFloat(String.valueOf(aluno.getNota())));
		endereco.setText(aluno.getEndereco());
	}
}
