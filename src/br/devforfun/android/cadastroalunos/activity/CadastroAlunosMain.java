package br.devforfun.android.cadastroalunos.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import br.devforfun.android.cadastroalunos.R;
import br.devforfun.android.cadastroalunos.adapter.AlunosAdapter;
import br.devforfun.android.cadastroalunos.dao.AlunoDAO;
import br.devforfun.android.cadastroalunos.model.Aluno;
import br.devforfun.android.cadastroalunos.service.Sincronismo;

/**
 * 
 * @site devforfun.com
 * @author marcospaulo
 * 
 */
public class CadastroAlunosMain extends Activity {

	private ListView listViewAlunos;
	private Aluno alunoSelecionado;
	private List<Aluno> alunos;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		AlunoDAO dao = new AlunoDAO(this);
		List<Aluno> alunos = dao.getLista();
		dao.close();

		listViewAlunos = (ListView) findViewById(R.id.list_view_alunos);
		AlunosAdapter adapter = new AlunosAdapter(this,
				android.R.layout.simple_list_item_1, alunos);

		listViewAlunos.setAdapter(adapter);
		bind();
	}

	@Override
	protected void onResume() {
		super.onResume();
		carregaLista();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem novo = menu.add(0, 0, 0, "Novo");
		novo.setIcon(android.R.drawable.ic_menu_add);

		MenuItem sincronizar = menu.add(0, 1, 0, "Sincronizar");
		sincronizar.setIcon(android.R.drawable.ic_popup_sync);

		MenuItem galeria = menu.add(0, 2, 0, "Galeria");
		galeria.setIcon(android.R.drawable.ic_menu_gallery);

		MenuItem mapa = menu.add(0, 3, 0, "Mapa");
		mapa.setIcon(android.R.drawable.ic_menu_mapmode);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderTitle("Escolha uma das operações");
		menu.add(0, 0, 0, "Ligar");
		menu.add(0, 1, 0, "Enviar SMS");
		menu.add(0, 2, 0, "Achar no Mapa");
		menu.add(0, 3, 0, "Navegar no Site");
		menu.add(0, 4, 0, "Deletar");
		menu.add(0, 5, 0, "Enviar Email");

		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		if (item.getItemId() == 4) {
			AlunoDAO dao = new AlunoDAO(this);
			dao.deletar(alunoSelecionado);
			dao.close();
			carregaLista();
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == 0) {
			startActivity(new Intent(this, CadastroForm.class));
		} else if(item.getItemId() == 1){
			Sincronismo s = new Sincronismo(CadastroAlunosMain.this);
			s.sincronizar();
		} else if(item.getItemId() == 2){
			startActivity(new Intent(this, GaleriaAlunos.class));
		}
		
		return super.onOptionsItemSelected(item);
	}

	private void bind() {
		listViewAlunos.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View view, int posicao,
					long id) {
				
				Intent edicao = new Intent(CadastroAlunosMain.this, CadastroForm.class);
				edicao.putExtra("alunoSelecionado", alunos.get(posicao));
				startActivity(edicao);
			
			}
		});

		listViewAlunos
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					public boolean onItemLongClick(AdapterView<?> adapter,
							View view, int posicao, long id) {
						alunoSelecionado = ((AlunosAdapter) adapter.getAdapter())
								.getItemAtPosition(posicao);
						registerForContextMenu(view);
						return false;
					}

				});
	}

	private void carregaLista() {
		AlunoDAO dao = new AlunoDAO(this);
		alunos = dao.getLista();
		dao.close();

		AlunosAdapter adapter = new AlunosAdapter(this,
				android.R.layout.simple_list_item_1, alunos);
		listViewAlunos.setAdapter(adapter);
	}

}