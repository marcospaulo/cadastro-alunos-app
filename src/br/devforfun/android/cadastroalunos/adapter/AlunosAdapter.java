package br.devforfun.android.cadastroalunos.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.devforfun.android.cadastroalunos.R;
import br.devforfun.android.cadastroalunos.model.Aluno;

public class AlunosAdapter extends ArrayAdapter<Aluno> {

	private List<Aluno> alunos;

	public AlunosAdapter(Context context, int textViewResourceId,
			List<Aluno> alunos) {
		super(context, textViewResourceId);
		this.alunos = alunos;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (alunos.size() > 0) {
			Aluno aluno = alunos.get(position);

			view = ((Activity) getContext()).getLayoutInflater().inflate(
					R.layout.item_list, null);

			LinearLayout fundo = (LinearLayout) view.findViewById(R.id.fundo);

			if (position % 2 == 0) {
				fundo.setBackgroundColor(0xFFFF0000);
			} else {
				fundo.setBackgroundColor(0xFF00FF00);
			}

			ImageView foto = (ImageView) view.findViewById(R.id.foto);
			Bitmap bm = BitmapFactory.decodeResource(((Activity) getContext())
					.getResources(), R.drawable.icon);
			if (aluno.getFoto() != null) {
				bm = BitmapFactory.decodeFile(aluno.getFoto());
			}
			bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
			foto.setImageBitmap(bm);

			TextView nome = (TextView) view.findViewById(R.id.nome);
			nome.setText(aluno.toString());
		}

		return view;
	}

	public long getItemId(int position) {
		if (alunos.size() > 0) {
			return alunos.get(position).getId();
		}
		return 0;
	}

	public int getCount() {
		return alunos.size();
	}

	public Aluno getItemAtPosition(int position) {
		if (alunos.size() > 0) {
			return alunos.get(position);
		}
		return null;
	}

}
