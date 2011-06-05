package br.devforfun.android.cadastroalunos.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import br.devforfun.android.cadastroalunos.R;

public class CadastroForm extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		Button botao = (Button) findViewById(R.id.inserir_aluno);
		

	}

}
