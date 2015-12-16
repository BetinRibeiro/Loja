package Controller.Model.Tabela.Movimentacao;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import Bin.Compra;;

public class ModelTabelaListaCompras extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Compra> dados;
	private String[] colunas = { "CÓDIGO", "DATA", "VALOR" };
	DecimalFormat dfValor = new DecimalFormat("0.00");
	SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

	// você precisar que os dados também sejam imediatamente alterados no banco
	// de dados por exemplo,
	// você vai precisar adicionar um TableModelListener ao seu model que
	// executará o método
	// tableChanged toda vez que os dados da JTable forem alterados.
	public ModelTabelaListaCompras() {
		dados = new ArrayList<Compra>();

	}

	public void addRow(Compra p) {
		this.dados.add(p);
		this.fireTableDataChanged();
	}

	public void removeTudo() {

		this.dados.clear();
		this.fireTableDataChanged();
	}

	public String getColumnName(int num) {
		return this.colunas[num];
	}

	// Também iremos precisar de um método que remova uma linha da tabela
	public int removeRow(int linha) {
		int id = this.dados.get(linha).getId();
		this.dados.remove(linha);
		this.fireTableRowsDeleted(linha, linha);
		return id;
	}

	// Se você deve lembrar quando utilizávamos o DefaultTableModel podíamos
	// alterar a tabela
	// simplesmente dando um duplo clique em cima e alguma célula e ela
	// permitiria a edição.
	// Isso acontecia porque no DefaultTableModel o método isCellEditable(int
	// linha, int coluna)
	// que é chamado para saber se uma célula é editável sempre retornava true,
	// mas no AbstractTableModel
	// ele retorna sempre false, então devemos sobrescreve-lo
	public boolean isCellEditable(int linha, int coluna) {
		return true;
	}

	// Estes métodos devem retornar respectivamente o numero de linhas, o numero
	// de colunas
	// e o valor da célula correspondente aos valores de linha e coluna
	// fornecidos por parâmetro.
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		return dados.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {

		switch (coluna) {

		case 0:
			return dados.get(linha).getId();
		case 1:
			return dt.format(dados.get(linha).getData());
		case 2:
			return dfValor.format(dados.get(linha).getTotal());

		}
		return null;
	}

	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}

	public Compra retornaCompra(int linha) {
		return dados.get(linha);
	}

}