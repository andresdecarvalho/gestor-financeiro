package financeiro;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dal.ModuloConexao;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Principal extends JDialog {
	private JTextField txtPesquisar;
	private JTable tblPrincipal;
	private JTextField txtV2;
	private JTextField txtV4;
	private JTextField txtV5;
	private JTextField txtV6;
	private JTextField txtV7;
	private JTextField txtV8;
	private JTextField txtV9;
	private JTextField txtV10;
	private JTextField txtV1;
	private JTextField txtNull;

	Connection conexao = null; // conex�o
	PreparedStatement pst = null; // executar uma query (script) sql
	ResultSet rs = null; // "trazer" os dados
	private JTextField txtV3;
	private JComboBox cmb1;
	private JComboBox cmb2;
	private JComboBox cmb3;
	private JComboBox cmb4;
	private JComboBox cmb5;
	private JComboBox cmb6;
	private JComboBox cmb7;
	private JComboBox cmb8;
	private JComboBox cmb9;
	private JLabel lblMes;
	private JLabel lblBanco;
	private JLabel lblC1;
	private JLabel lblC2;
	private JLabel lblLuz;
	private JLabel lblInternet;
	private JLabel lblFacul;
	private JLabel lblPessoal;
	private JLabel lblCasa;
	private JLabel lblCelular;
	private JTextField txtGanhos;
	private JTextField txtDebitos;
	private JTextField txtSobra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal dialog = new Principal();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Principal() {
		setFont(new Font("Arial", Font.BOLD, 14));
		setTitle("Gestor Financeiro");
		// setIconImage(
		// Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/br/com/gde/icones/Principal.png")));
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 1001, 695);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);

		txtPesquisar = new JTextField();
		txtPesquisar.setFont(new Font("Arial", Font.BOLD, 12));
		txtPesquisar.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				pesquisarPrincipal();
			}
		});
		txtPesquisar.setBounds(24, 45, 294, 20);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		// lblNewLabel.setIcon(new
		// ImageIcon(Principal.class.getResource("/br/com/gde/icones/pesquisar.png")));
		lblNewLabel.setBounds(295, 33, 32, 32);
		getContentPane().add(lblNewLabel);

		JLabel lblValor = new JLabel("VALOR");
		lblValor.setFont(new Font("Arial", Font.BOLD, 12));
		lblValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblValor.setBounds(160, 279, 109, 17);
		getContentPane().add(lblValor);

		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setFont(new Font("Arial", Font.BOLD, 11));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setToolTipText("EDITAR PRODUTO");
		btnEditar.setIcon(null);
		btnEditar.setBounds(358, 566, 119, 32);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(btnEditar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 76, 938, 131);
		getContentPane().add(scrollPane);

		tblPrincipal = new JTable();
		tblPrincipal.setFont(new Font("Arial", Font.BOLD, 12));
		tblPrincipal.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		scrollPane.setViewportView(tblPrincipal);
		tblPrincipal.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));

		txtV2 = new JTextField();
		txtV2.setFont(new Font("Arial", Font.BOLD, 12));
		txtV2.setBounds(160, 322, 109, 20);
		getContentPane().add(txtV2);
		txtV2.setColumns(10);

		JLabel lblPesquisa = new JLabel("DIGITE O M\u00CAS");
		lblPesquisa.setFont(new Font("Arial", Font.BOLD, 12));
		lblPesquisa.setBounds(24, 25, 286, 14);
		getContentPane().add(lblPesquisa);

		JButton btnCalcular = new JButton("CALCULAR");
		btnCalcular.setFont(new Font("Arial", Font.BOLD, 11));
		btnCalcular.setIcon(null);
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnCalcular.setToolTipText("CALCULAR");
		btnCalcular.setBounds(651, 566, 119, 32);
		btnCalcular.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(btnCalcular);

		JButton btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.setFont(new Font("Arial", Font.BOLD, 11));
		btnAdicionar.setIcon(null);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setToolTipText("ADICIONAR PRODUTO");
		btnAdicionar.setBounds(212, 566, 119, 32);
		btnAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(btnAdicionar);

		JButton btnDeletar = new JButton("REMOVER");
		btnDeletar.setFont(new Font("Arial", Font.BOLD, 11));
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apagar();
			}
		});
		btnDeletar.setIcon(null);
		btnDeletar.setToolTipText("REMOVER PRODUTO");
		btnDeletar.setBounds(505, 566, 119, 32);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(btnDeletar);

		txtV3 = new JTextField();
		txtV3.setFont(new Font("Arial", Font.BOLD, 12));
		txtV3.setColumns(10);
		txtV3.setBounds(160, 345, 109, 20);
		getContentPane().add(txtV3);

		txtV4 = new JTextField();
		txtV4.setFont(new Font("Arial", Font.BOLD, 12));
		txtV4.setColumns(10);
		txtV4.setBounds(160, 368, 109, 20);
		getContentPane().add(txtV4);

		txtV5 = new JTextField();
		txtV5.setFont(new Font("Arial", Font.BOLD, 12));
		txtV5.setColumns(10);
		txtV5.setBounds(160, 391, 109, 20);
		getContentPane().add(txtV5);

		txtV6 = new JTextField();
		txtV6.setFont(new Font("Arial", Font.BOLD, 12));
		txtV6.setColumns(10);
		txtV6.setBounds(160, 414, 109, 20);
		getContentPane().add(txtV6);

		txtV7 = new JTextField();
		txtV7.setFont(new Font("Arial", Font.BOLD, 12));
		txtV7.setColumns(10);
		txtV7.setBounds(160, 437, 109, 20);
		getContentPane().add(txtV7);

		txtV8 = new JTextField();
		txtV8.setFont(new Font("Arial", Font.BOLD, 12));
		txtV8.setColumns(10);
		txtV8.setBounds(160, 460, 109, 20);
		getContentPane().add(txtV8);

		txtV9 = new JTextField();
		txtV9.setFont(new Font("Arial", Font.BOLD, 12));
		txtV9.setColumns(10);
		txtV9.setBounds(160, 483, 109, 20);
		getContentPane().add(txtV9);

		txtV10 = new JTextField();
		txtV10.setFont(new Font("Arial", Font.BOLD, 12));
		txtV10.setColumns(10);
		txtV10.setBounds(160, 506, 109, 20);
		getContentPane().add(txtV10);

		txtV1 = new JTextField();
		txtV1.setFont(new Font("Arial", Font.BOLD, 12));
		txtV1.setColumns(10);
		txtV1.setBounds(160, 299, 109, 20);
		getContentPane().add(txtV1);

		cmb1 = new JComboBox();
		cmb1.setModel(new DefaultComboBoxModel(new String[] { "PAGO", "N\u00C3O PAGO" }));
		cmb1.setFont(new Font("Arial", Font.BOLD, 12));
		cmb1.setBounds(279, 322, 109, 20);
		getContentPane().add(cmb1);

		cmb2 = new JComboBox();
		cmb2.setModel(new DefaultComboBoxModel(new String[] { "PAGO", "N\u00C3O PAGO" }));
		cmb2.setFont(new Font("Arial", Font.BOLD, 12));
		cmb2.setBounds(279, 345, 109, 20);
		getContentPane().add(cmb2);

		cmb3 = new JComboBox();
		cmb3.setModel(new DefaultComboBoxModel(new String[] { "PAGO", "N\u00C3O PAGO" }));
		cmb3.setFont(new Font("Arial", Font.BOLD, 12));
		cmb3.setBounds(279, 368, 109, 20);
		getContentPane().add(cmb3);

		cmb4 = new JComboBox();
		cmb4.setModel(new DefaultComboBoxModel(new String[] { "PAGO", "N\u00C3O PAGO" }));
		cmb4.setFont(new Font("Arial", Font.BOLD, 12));
		cmb4.setBounds(279, 391, 109, 20);
		getContentPane().add(cmb4);

		cmb5 = new JComboBox();
		cmb5.setModel(new DefaultComboBoxModel(new String[] { "PAGO", "N\u00C3O PAGO" }));
		cmb5.setFont(new Font("Arial", Font.BOLD, 12));
		cmb5.setBounds(279, 414, 109, 20);
		getContentPane().add(cmb5);

		cmb6 = new JComboBox();
		cmb6.setModel(new DefaultComboBoxModel(new String[] { "PAGO", "N\u00C3O PAGO" }));
		cmb6.setFont(new Font("Arial", Font.BOLD, 12));
		cmb6.setBounds(279, 437, 109, 20);
		getContentPane().add(cmb6);

		cmb7 = new JComboBox();
		cmb7.setModel(new DefaultComboBoxModel(new String[] { "PAGO", "N\u00C3O PAGO" }));
		cmb7.setFont(new Font("Arial", Font.BOLD, 12));
		cmb7.setBounds(279, 460, 109, 20);
		getContentPane().add(cmb7);

		cmb8 = new JComboBox();
		cmb8.setModel(new DefaultComboBoxModel(new String[] { "PAGO", "N\u00C3O PAGO" }));
		cmb8.setFont(new Font("Arial", Font.BOLD, 12));
		cmb8.setBounds(279, 483, 109, 20);
		getContentPane().add(cmb8);

		cmb9 = new JComboBox();
		cmb9.setModel(new DefaultComboBoxModel(new String[] { "PAGO", "N\u00C3O PAGO" }));
		cmb9.setFont(new Font("Arial", Font.BOLD, 12));
		cmb9.setBounds(279, 506, 109, 20);
		getContentPane().add(cmb9);

		JLabel lblSituacao = new JLabel("SITUA\u00C7\u00C3O");
		lblSituacao.setHorizontalAlignment(SwingConstants.LEFT);
		lblSituacao.setFont(new Font("Arial", Font.BOLD, 12));
		lblSituacao.setBounds(279, 279, 109, 17);
		getContentPane().add(lblSituacao);

		lblMes = new JLabel("M\u00CAS");
		lblMes.setHorizontalAlignment(SwingConstants.LEFT);
		lblMes.setFont(new Font("Arial", Font.BOLD, 12));
		lblMes.setBounds(24, 298, 140, 20);
		getContentPane().add(lblMes);

		lblBanco = new JLabel("BANCO");
		lblBanco.setHorizontalAlignment(SwingConstants.LEFT);
		lblBanco.setFont(new Font("Arial", Font.BOLD, 12));
		lblBanco.setBounds(24, 322, 140, 20);
		getContentPane().add(lblBanco);

		lblC1 = new JLabel("CART\u00C3O 1");
		lblC1.setHorizontalAlignment(SwingConstants.LEFT);
		lblC1.setFont(new Font("Arial", Font.BOLD, 12));
		lblC1.setBounds(24, 345, 140, 20);
		getContentPane().add(lblC1);

		lblC2 = new JLabel("CART\u00C3O 2");
		lblC2.setHorizontalAlignment(SwingConstants.LEFT);
		lblC2.setFont(new Font("Arial", Font.BOLD, 12));
		lblC2.setBounds(24, 368, 140, 20);
		getContentPane().add(lblC2);

		lblLuz = new JLabel("CONTA DE LUZ");
		lblLuz.setHorizontalAlignment(SwingConstants.LEFT);
		lblLuz.setFont(new Font("Arial", Font.BOLD, 12));
		lblLuz.setBounds(24, 390, 140, 20);
		getContentPane().add(lblLuz);

		lblInternet = new JLabel("INTERNET");
		lblInternet.setHorizontalAlignment(SwingConstants.LEFT);
		lblInternet.setFont(new Font("Arial", Font.BOLD, 12));
		lblInternet.setBounds(24, 414, 140, 20);
		getContentPane().add(lblInternet);

		lblFacul = new JLabel("FACULDADE");
		lblFacul.setHorizontalAlignment(SwingConstants.LEFT);
		lblFacul.setFont(new Font("Arial", Font.BOLD, 12));
		lblFacul.setBounds(24, 437, 140, 20);
		getContentPane().add(lblFacul);

		lblPessoal = new JLabel("CONTAS PESSOAIS");
		lblPessoal.setHorizontalAlignment(SwingConstants.LEFT);
		lblPessoal.setFont(new Font("Arial", Font.BOLD, 12));
		lblPessoal.setBounds(24, 460, 140, 20);
		getContentPane().add(lblPessoal);

		lblCasa = new JLabel("CONTAS CASA");
		lblCasa.setHorizontalAlignment(SwingConstants.LEFT);
		lblCasa.setFont(new Font("Arial", Font.BOLD, 12));
		lblCasa.setBounds(24, 483, 140, 20);
		getContentPane().add(lblCasa);

		lblCelular = new JLabel("CELULAR");
		lblCelular.setHorizontalAlignment(SwingConstants.LEFT);
		lblCelular.setFont(new Font("Arial", Font.BOLD, 12));
		lblCelular.setBounds(24, 506, 140, 20);
		getContentPane().add(lblCelular);

		txtNull = new JTextField();
		txtNull.setEditable(false);
		txtNull.setEnabled(false);
		txtNull.setBounds(279, 299, 109, 20);
		getContentPane().add(txtNull);
		txtNull.setColumns(10);
		
		JLabel lblGanhos = new JLabel("GANHOS");
		lblGanhos.setHorizontalAlignment(SwingConstants.LEFT);
		lblGanhos.setFont(new Font("Arial", Font.BOLD, 12));
		lblGanhos.setBounds(473, 280, 109, 17);
		getContentPane().add(lblGanhos);
		
		JLabel lblDbitos = new JLabel("D\u00C9BITOS");
		lblDbitos.setHorizontalAlignment(SwingConstants.LEFT);
		lblDbitos.setFont(new Font("Arial", Font.BOLD, 12));
		lblDbitos.setBounds(612, 280, 109, 17);
		getContentPane().add(lblDbitos);
		
		JLabel lblSobra = new JLabel("SOBRA");
		lblSobra.setHorizontalAlignment(SwingConstants.LEFT);
		lblSobra.setFont(new Font("Arial", Font.BOLD, 12));
		lblSobra.setBounds(758, 280, 109, 17);
		getContentPane().add(lblSobra);
		
		txtGanhos = new JTextField();
		txtGanhos.setFont(new Font("Arial", Font.BOLD, 12));
		txtGanhos.setColumns(10);
		txtGanhos.setBounds(473, 299, 109, 20);
		getContentPane().add(txtGanhos);
		
		txtDebitos = new JTextField();
		
		txtDebitos.setFont(new Font("Arial", Font.BOLD, 12));
		txtDebitos.setColumns(10);
		txtDebitos.setBounds(612, 299, 109, 20);
		getContentPane().add(txtDebitos);
		
		txtSobra = new JTextField();
		txtSobra.setFont(new Font("Arial", Font.BOLD, 12));
		txtSobra.setColumns(10);
		txtSobra.setBounds(758, 299, 109, 20);
		getContentPane().add(txtSobra);

		conexao = ModuloConexao.conector();// conectar com o banco (conex�o fechada em login)

	}// fim do construtor

	// limpar campos do Jframe
	private void limpar() {
		txtPesquisar.setText(null);
		limparTabela();

	}

	// metodo para limpar a tabela
	private void limparTabela() {
		while (tblPrincipal.getRowCount() > 0) {
			((DefaultTableModel) tblPrincipal.getModel()).removeRow(0);
		}
	}

	// metodo para pesquisar Principal dinamicamente
	private void pesquisarPrincipal() {
		String consultar = "select * from tb_contas where mes like?";
		try {
			pst = conexao.prepareStatement(consultar);
			// aten��o ao "%" na passagem do parametro
			pst.setString(1, txtPesquisar.getText() + "%");
			rs = pst.executeQuery();
			// a linha abaixo usa a biblioteca rs2xml.jar para "popular" a tabela
			tblPrincipal.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// METODO PARA SETAR OS CAMPOS DO jFRAME setSelectedItem
	private void setarCampos() {
		int setar = tblPrincipal.getSelectedRow();
		txtV1.setText(tblPrincipal.getModel().getValueAt(setar, 0).toString().replace(".", ","));
		txtV2.setText(tblPrincipal.getModel().getValueAt(setar, 1).toString().replace(".", ","));
		cmb1.setSelectedItem(tblPrincipal.getModel().getValueAt(setar, 2).toString().replace(".", ","));
		txtV3.setText(tblPrincipal.getModel().getValueAt(setar, 3).toString().replace(".", ","));
		cmb2.setSelectedItem(tblPrincipal.getModel().getValueAt(setar, 4).toString().replace(".", ","));
		txtV4.setText(tblPrincipal.getModel().getValueAt(setar, 5).toString().replace(".", ","));
		cmb3.setSelectedItem(tblPrincipal.getModel().getValueAt(setar, 6).toString().replace(".", ","));
		txtV5.setText(tblPrincipal.getModel().getValueAt(setar, 7).toString().replace(".", ","));
		cmb4.setSelectedItem(tblPrincipal.getModel().getValueAt(setar, 8).toString().replace(".", ","));
		txtV6.setText(tblPrincipal.getModel().getValueAt(setar, 9).toString().replace(".", ","));
		cmb5.setSelectedItem(tblPrincipal.getModel().getValueAt(setar, 10).toString().replace(".", ","));
		txtV7.setText(tblPrincipal.getModel().getValueAt(setar, 11).toString().replace(".", ","));
		cmb6.setSelectedItem(tblPrincipal.getModel().getValueAt(setar, 12).toString().replace(".", ","));
		txtV8.setText(tblPrincipal.getModel().getValueAt(setar, 13).toString().replace(".", ","));
		cmb7.setSelectedItem(tblPrincipal.getModel().getValueAt(setar, 14).toString().replace(".", ","));
		txtV9.setText(tblPrincipal.getModel().getValueAt(setar, 15).toString().replace(".", ","));
		cmb8.setSelectedItem(tblPrincipal.getModel().getValueAt(setar, 16).toString().replace(".", ","));
		txtV10.setText(tblPrincipal.getModel().getValueAt(setar, 17).toString().replace(".", ","));
		cmb9.setSelectedItem(tblPrincipal.getModel().getValueAt(setar, 18).toString().replace(".", ","));
		
		double valor1,valor2,valor3,valor4,valor5,valor6,valor7,valor8,valor9,resultado;
		DecimalFormat formatador= new DecimalFormat("0.00");
		valor1 = Double.parseDouble(txtV2.getText().replace(",","."));
		valor2 = Double.parseDouble(txtV3.getText().replace(",","."));
		valor3 = Double.parseDouble(txtV4.getText().replace(",","."));
		valor4 = Double.parseDouble(txtV5.getText().replace(",","."));
		valor5 = Double.parseDouble(txtV6.getText().replace(",","."));
		valor6 = Double.parseDouble(txtV7.getText().replace(",","."));
		valor7 = Double.parseDouble(txtV8.getText().replace(",","."));
		valor8 = Double.parseDouble(txtV9.getText().replace(",","."));
		valor9 = Double.parseDouble(txtV10.getText().replace(",","."));
		resultado = valor1 + valor2 + valor3 + valor4 + valor5 + valor6 + valor7 + valor8 + valor9;
		txtDebitos.setText(formatador.format(resultado));
	}

	// Metodo para alterar cliente
	private void editar() {
		if (txtV1.getText().isEmpty() || txtV2.getText().isEmpty() || txtV3.getText().isEmpty()
				|| txtV4.getText().isEmpty() || txtV5.getText().isEmpty() || txtV6.getText().isEmpty()
				|| txtV7.getText().isEmpty() || txtV8.getText().isEmpty() || txtV9.getText().isEmpty()
				|| txtV10.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha os campos obrigat�rios");
		} else {
			String editar = "update tb_contas set banco=?, sBanco=?, cartaoB1=?, sCartaoB1=?, cartaoB2=?, sCartaoB2=?, luz=?, sLuz=?, net=?, sNet=?, facul=?, sFacul=?, pessoal=?, sPessoal=?, casa=?, sCasa=?, celular=?, sCelular=? where mes= ?";
			try {
				pst = conexao.prepareStatement(editar);
				pst.setString(1, txtV2.getText().replace(",", "."));
				pst.setString(2, cmb1.getSelectedItem().toString());
				pst.setString(3, txtV3.getText().replace(",", "."));
				pst.setString(4, cmb2.getSelectedItem().toString());
				pst.setString(5, txtV4.getText().replace(",", "."));
				pst.setString(6, cmb3.getSelectedItem().toString());
				pst.setString(7, txtV5.getText().replace(",", "."));
				pst.setString(8, cmb4.getSelectedItem().toString());
				pst.setString(9, txtV6.getText().replace(",", "."));
				pst.setString(10, cmb5.getSelectedItem().toString());
				pst.setString(11, txtV7.getText().replace(",", "."));
				pst.setString(12, cmb6.getSelectedItem().toString());
				pst.setString(13, txtV8.getText().replace(",", "."));
				pst.setString(14, cmb7.getSelectedItem().toString());
				pst.setString(15, txtV9.getText().replace(",", "."));
				pst.setString(16, cmb8.getSelectedItem().toString());
				pst.setString(17, txtV10.getText().replace(",", "."));
				pst.setString(18, cmb9.getSelectedItem().toString());
				pst.setString(19, txtV1.getText().replace(",", "."));

				int adicionado = pst.executeUpdate();
				if (adicionado == 1) {
					JOptionPane.showMessageDialog(null, "\n Pend�ncias do m�s de : " + txtV1.getText()
							+ " foram atualizadas !!!" + "\n O Valor de : " + txtV2.getText().replace(".", ",")
							+ " R$ reais de " + lblBanco.getText() + " na situa��o de " + cmb1.getSelectedItem()
							+ " foi atualizado com sucesso" + "\n O Valor de : " + txtV3.getText().replace(".", ",")
							+ " R$ reais de " + lblC1.getText() + " na situa��o de " + cmb2.getSelectedItem()
							+ " foi atualizado com sucesso" + "\n O Valor de : " + txtV4.getText().replace(".", ",")
							+ " R$ reais de " + lblC2.getText() + " na situa��o de " + cmb3.getSelectedItem()
							+ " foi atualizado com sucesso" + "\n O Valor de : " + txtV5.getText().replace(".", ",")
							+ " R$ reais de " + lblLuz.getText() + " na situa��o de " + cmb4.getSelectedItem()
							+ " foi atualizado com sucesso" + "\n O Valor de : " + txtV6.getText().replace(".", ",")
							+ " R$ reais de " + lblInternet.getText() + " na situa��o de " + cmb5.getSelectedItem()
							+ " foi atualizado com sucesso" + "\n O Valor de : " + txtV7.getText().replace(".", ",")
							+ " R$ reais de " + lblFacul.getText() + " na situa��o de " + cmb6.getSelectedItem()
							+ " foi atualizado com sucesso" + "\n O Valor de : " + txtV8.getText().replace(".", ",")
							+ " R$ reais de " + lblPessoal.getText() + " na situa��o de " + cmb7.getSelectedItem()
							+ " foi atualizado com sucesso" + "\n O Valor de : " + txtV9.getText().replace(".", ",")
							+ " R$ reais de " + lblCasa.getText() + " na situa��o de " + cmb8.getSelectedItem()
							+ " foi atualizado com sucesso" + "\n O Valor de : " + txtV10.getText().replace(".", ",")
							+ " R$ reais de " + lblCelular.getText() + " na situa��o de " + cmb9.getSelectedItem()
							+ " foi atualizado com sucesso");

					limpar();
				} else {
					JOptionPane.showMessageDialog(null, "N�o foi poss�vel atualizar o m�s de :" + txtV1.getText()
							+ "N�o foi poss�vel atualizar o valor de :" + txtV2.getText().replace(".", ",") + "R$ reais"
							+ "N�o foi poss�vel atualizar o valor de :" + txtV3.getText().replace(".", ",") + "R$ reais"
							+ "N�o foi poss�vel atualizar o valor de :" + txtV4.getText().replace(".", ",") + "R$ reais"
							+ "N�o foi poss�vel atualizar o valor de :" + txtV5.getText().replace(".", ",") + "R$ reais"
							+ "N�o foi poss�vel atualizar o valor de :" + txtV6.getText().replace(".", ",") + "R$ reais"
							+ "N�o foi poss�vel atualizar o valor de :" + txtV7.getText().replace(".", ",") + "R$ reais"
							+ "N�o foi poss�vel atualizar o valor de :" + txtV8.getText().replace(".", ",") + "R$ reais"
							+ "N�o foi poss�vel atualizar o valor de :" + txtV9.getText().replace(".", ",")
							+ "R$ reais");
					limpar();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void adicionar() {
		// valida��o dos campos obrigat�rios
		if (txtV1.getText().isEmpty() || txtV2.getText().isEmpty() || txtV3.getText().isEmpty()
				|| txtV4.getText().isEmpty() || txtV5.getText().isEmpty() || txtV6.getText().isEmpty()
				|| txtV7.getText().isEmpty() || txtV8.getText().isEmpty() || txtV9.getText().isEmpty()
				|| txtV10.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha os campos Obrigat�rios");
		} else {
			// insert
			String create = "insert into tb_contas(mes, banco, sBanco, cartaoB1, sCartaoB1, cartaoB2, sCartaoB2, luz, sLuz, net, sNet, facul, sFacul, pessoal, sPessoal, casa, sCasa, celular, sCelular) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				pst = conexao.prepareStatement(create);
				// armazenar no banco o conte�do do Jframe Formul�rio
				pst.setString(1, txtV1.getText());
				pst.setString(2, txtV2.getText().replace(",", "."));
				pst.setString(3, cmb1.getSelectedItem().toString());
				pst.setString(4, txtV3.getText().replace(",", "."));
				pst.setString(5, cmb2.getSelectedItem().toString());
				pst.setString(6, txtV4.getText().replace(",", "."));
				pst.setString(7, cmb3.getSelectedItem().toString());
				pst.setString(8, txtV5.getText().replace(",", "."));
				pst.setString(9, cmb4.getSelectedItem().toString());
				pst.setString(10, txtV6.getText().replace(",", "."));
				pst.setString(11, cmb5.getSelectedItem().toString());
				pst.setString(12, txtV7.getText().replace(",", "."));
				pst.setString(13, cmb6.getSelectedItem().toString());
				pst.setString(14, txtV8.getText().replace(",", "."));
				pst.setString(15, cmb7.getSelectedItem().toString());
				pst.setString(16, txtV9.getText().replace(",", "."));
				pst.setString(17, cmb8.getSelectedItem().toString());
				pst.setString(18, txtV10.getText().replace(",", "."));
				pst.setString(19, cmb9.getSelectedItem().toString());
				// Criando uma estrutura para "avisar" o usu�rio que o contato foi cadastrado
				int adicionado = pst.executeUpdate();
				// a linha abaixo � utilizada para entender a l�gica
				System.out.println(adicionado);
				if (adicionado == 1) {
					JOptionPane.showMessageDialog(null, "contas deste m�s adicionadas com sucesso");
					limpar();
				} else {
					JOptionPane.showMessageDialog(null, "N�o foi possivel adicionar as contas deste m�s");
				}

			} catch (Exception ConstraintViolationException) {
				JOptionPane.showMessageDialog(null, "M�s j� existente");
				// System.out.println(e);

			}
		}
	}

	private void apagar() {
		// a linha abaixo cria uma caixa de confirma��o
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclus�o deste m�s?", "ATEN��O",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from tb_contas where mes = ?";
			try {
				pst = conexao.prepareStatement(delete);
				pst.setString(1, txtV1.getText());

				int removido = pst.executeUpdate();
				limpar();
				if (removido == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Contas deste m�s removidas");
				}

			} catch (Exception e) {
				System.out.println(e);
				limpar();
			}
		}

	}
	
}