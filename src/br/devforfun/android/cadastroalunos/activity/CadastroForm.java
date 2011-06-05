package br.devforfun.android.cadastroalunos.activity;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import br.devforfun.android.cadastroalunos.R;
import br.devforfun.android.cadastroalunos.dao.AlunoDAO;
import br.devforfun.android.cadastroalunos.model.Aluno;

public class CadastroForm extends Activity {

	private static final int TIRA_FOTO = 101;

	private Aluno aluno;
	private Button inserirAlunoButton;

	private EditText nome;
	private EditText telefone;
	private EditText site;
	private RatingBar nota;
	private EditText endereco;
	private ImageButton imageButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);

		aluno = (Aluno) getIntent().getSerializableExtra("alunoSelecionado");
		inserirAlunoButton = (Button) findViewById(R.id.inserir_aluno);
		imageButton = (ImageButton) findViewById(R.id.imagem);
		

		if (aluno == null) {
			aluno = new Aluno();
		} else {
			inserirAlunoButton.setText("Alterar");
			carregarImagem();
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

		imageButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

				String arquivo = Environment.getExternalStorageDirectory()
						+ "/" + System.currentTimeMillis() + ".jpg";
				aluno.setFoto(arquivo);

				File file = new File(arquivo);
				Uri outputFileUri = Uri.fromFile(file);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

				startActivityForResult(intent, TIRA_FOTO);

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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == TIRA_FOTO) {
			if (resultCode != RESULT_OK) {
				aluno.setFoto(null);
			}
		}
		carregarImagem();
	}

	private void carregarImagem() {
		if (aluno.getFoto() != null) {
			Bitmap bm = BitmapFactory.decodeFile(aluno.getFoto());
			bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
			imageButton.setImageBitmap(bm);
		}
	}
}
