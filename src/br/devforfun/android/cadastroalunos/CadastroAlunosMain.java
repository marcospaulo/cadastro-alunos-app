package br.devforfun.android.cadastroalunos;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

/**
 * 
 * @site devforfun.com
 * @author marcospaulo
 * 
 */
public class CadastroAlunosMain extends Activity {

	private ListView listViewAlunos;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		List<String> alunos = Arrays.asList("1", "2", "3");

		listViewAlunos = (ListView) findViewById(R.id.list_view_alunos);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, alunos);

		listViewAlunos.setAdapter(adapter);
		bind();
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

		Toast.makeText(this, "Você clicou no item: '" + item.getTitle() + "'",
				Toast.LENGTH_LONG).show();

		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		Toast.makeText(this, "Você clicou no item: '" + item.getTitle() + "'",
				Toast.LENGTH_LONG).show();

		return super.onOptionsItemSelected(item);
	}

	private void bind() {
		listViewAlunos.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(CadastroAlunosMain.this,
						"Você clicou na posição " + arg2, Toast.LENGTH_LONG)
						.show();
			}
		});

		listViewAlunos
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					public boolean onItemLongClick(AdapterView<?> arg0,
							View arg1, int arg2, long arg3) {
						registerForContextMenu(arg1);
						return false;
					}

				});
	}

}