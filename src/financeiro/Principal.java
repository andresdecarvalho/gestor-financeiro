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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.DropMode;
import java.awt.ComponentOrientation;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ListSelectionModel;

public class Principal extends JDialog {
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
	private JLabel lblData;

	Connection conexao = null; // conex�o
	PreparedStatement pst = null; // executar uma query (script) sql
	ResultSet rs = null; // "trazer" os dados
	private JTextField txtPesq;

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
		setResizable(false);
		setFont(new Font("Arial", Font.BOLD, 14));
		setTitle("Gestor Financeiro");
		setModal(true);
		setBounds(100, 100, 598, 518);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {// a��o para modificar a label e mostrar data e hora
			public void windowActivated(WindowEvent e) {
				alterarLabel();
			}
		});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(295, 44, 32, 32);
		getContentPane().add(lblNewLabel);

		JLabel lblValor = new JLabel("VALOR EM R$");
		lblValor.setFont(new Font("Arial", Font.BOLD, 12));
		lblValor.setHorizontalAlignment(SwingConstants.LEFT);
		lblValor.setBounds(181, 107, 109, 17);
		getContentPane().add(lblValor);

		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setFont(new Font("Arial", Font.BOLD, 11));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setToolTipText("EDITAR M\u00CAS");
		btnEditar.setIcon(null);
		btnEditar.setBounds(134, 383, 100, 32);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(btnEditar);

		txtV2 = new JTextField();
		txtV2.setFont(new Font("Arial", Font.BOLD, 12));
		txtV2.setBounds(181, 150, 109, 20);
		getContentPane().add(txtV2);
		txtV2.setColumns(10);

		JLabel lblPesquisa = new JLabel("DIGITE O M\u00CAS");
		lblPesquisa.setFont(new Font("Arial", Font.BOLD, 12));
		lblPesquisa.setBounds(45, 60, 124, 14);
		getContentPane().add(lblPesquisa);

		JButton btnCalcular = new JButton("CALCULAR");
		btnCalcular.setFont(new Font("Arial", Font.BOLD, 11));
		btnCalcular.setIcon(null);
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcular();
			}
		});
		btnCalcular.setToolTipText("CALCULAR D\u00C9BITOS");
		btnCalcular.setBounds(354, 383, 100, 32);
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
		btnAdicionar.setToolTipText("ADICIONAR M\u00CAS");
		btnAdicionar.setBounds(24, 383, 100, 32);
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
		btnDeletar.setToolTipText("REMOVER M\u00CAS");
		btnDeletar.setBounds(244, 383, 100, 32);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		getContentPane().add(btnDeletar);

		txtV3 = new JTextField();
		txtV3.setFont(new Font("Arial", Font.BOLD, 12));
		txtV3.setColumns(10);
		txtV3.setBounds(181, 173, 109, 20);
		getContentPane().add(txtV3);

		txtV4 = new JTextField();
		txtV4.setFont(new Font("Arial", Font.BOLD, 12));
		txtV4.setColumns(10);
		txtV4.setBounds(181, 196, 109, 20);
		getContentPane().add(txtV4);

		txtV5 = new JTextField();
		txtV5.setFont(new Font("Arial", Font.BOLD, 12));
		txtV5.setColumns(10);
		txtV5.setBounds(181, 219, 109, 20);
		getContentPane().add(txtV5);

		txtV6 = new JTextField();
		txtV6.setFont(new Font("Arial", Font.BOLD, 12));
		txtV6.setColumns(10);
		txtV6.setBounds(181, 242, 109, 20);
		getContentPane().add(txtV6);

		txtV7 = new JTextField();
		txtV7.setFont(new Font("Arial", Font.BOLD, 12));
		txtV7.setColumns(10);
		txtV7.setBounds(181, 265, 109, 20);
		getContentPane().add(txtV7);

		txtV8 = new JTextField();
		txtV8.setFont(new Font("Arial", Font.BOLD, 12));
		txtV8.setColumns(10);
		txtV8.setBounds(181, 288, 109, 20);
		getContentPane().add(txtV8);

		txtV9 = new JTextField();
		txtV9.setFont(new Font("Arial", Font.BOLD, 12));
		txtV9.setColumns(10);
		txtV9.setBounds(181, 311, 109, 20);
		getContentPane().add(txtV9);

		txtV10 = new JTextField();
		txtV10.setFont(new Font("Arial", Font.BOLD, 12));
		txtV10.setColumns(10);
		txtV10.setBounds(181, 334, 109, 20);
		getContentPane().add(txtV10);

		txtV1 = new JTextField();
		txtV1.setFont(new Font("Arial", Font.BOLD, 12));
		txtV1.setColumns(10);
		txtV1.setBounds(181, 127, 109, 20);
		getContentPane().add(txtV1);

		cmb1 = new JComboBox();
		cmb1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmb1.setModel(new DefaultComboBoxModel(new String[] { "", "PAGO", "N\u00C3O PAGO" }));
		cmb1.setFont(new Font("Arial", Font.BOLD, 12));
		cmb1.setBounds(300, 150, 109, 20);
		getContentPane().add(cmb1);

		cmb2 = new JComboBox();
		cmb2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmb2.setModel(new DefaultComboBoxModel(new String[] { "", "PAGO", "N\u00C3O PAGO" }));
		cmb2.setFont(new Font("Arial", Font.BOLD, 12));
		cmb2.setBounds(300, 173, 109, 20);
		getContentPane().add(cmb2);

		cmb3 = new JComboBox();
		cmb3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmb3.setModel(new DefaultComboBoxModel(new String[] { "", "PAGO", "N\u00C3O PAGO" }));
		cmb3.setFont(new Font("Arial", Font.BOLD, 12));
		cmb3.setBounds(300, 196, 109, 20);
		getContentPane().add(cmb3);

		cmb4 = new JComboBox();
		cmb4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmb4.setModel(new DefaultComboBoxModel(new String[] { "", "PAGO", "N\u00C3O PAGO" }));
		cmb4.setFont(new Font("Arial", Font.BOLD, 12));
		cmb4.setBounds(300, 219, 109, 20);
		getContentPane().add(cmb4);

		cmb5 = new JComboBox();
		cmb5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmb5.setModel(new DefaultComboBoxModel(new String[] { "", "PAGO", "N\u00C3O PAGO" }));
		cmb5.setFont(new Font("Arial", Font.BOLD, 12));
		cmb5.setBounds(300, 242, 109, 20);
		getContentPane().add(cmb5);

		cmb6 = new JComboBox();
		cmb6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmb6.setModel(new DefaultComboBoxModel(new String[] { "", "PAGO", "N\u00C3O PAGO" }));
		cmb6.setFont(new Font("Arial", Font.BOLD, 12));
		cmb6.setBounds(300, 265, 109, 20);
		getContentPane().add(cmb6);

		cmb7 = new JComboBox();
		cmb7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmb7.setModel(new DefaultComboBoxModel(new String[] { "", "PAGO", "N\u00C3O PAGO" }));
		cmb7.setFont(new Font("Arial", Font.BOLD, 12));
		cmb7.setBounds(300, 288, 109, 20);
		getContentPane().add(cmb7);

		cmb8 = new JComboBox();
		cmb8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmb8.setModel(new DefaultComboBoxModel(new String[] { "", "PAGO", "N\u00C3O PAGO" }));
		cmb8.setFont(new Font("Arial", Font.BOLD, 12));
		cmb8.setBounds(300, 311, 109, 20);
		getContentPane().add(cmb8);

		cmb9 = new JComboBox();
		cmb9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cmb9.setModel(new DefaultComboBoxModel(new String[] { "", "PAGO", "N\u00C3O PAGO" }));
		cmb9.setFont(new Font("Arial", Font.BOLD, 12));
		cmb9.setBounds(300, 334, 109, 20);
		getContentPane().add(cmb9);

		JLabel lblSituacao = new JLabel("SITUA\u00C7\u00C3O");
		lblSituacao.setHorizontalAlignment(SwingConstants.LEFT);
		lblSituacao.setFont(new Font("Arial", Font.BOLD, 12));
		lblSituacao.setBounds(300, 107, 109, 17);
		getContentPane().add(lblSituacao);

		lblMes = new JLabel("M\u00CAS ATUAL");
		lblMes.setHorizontalAlignment(SwingConstants.LEFT);
		lblMes.setFont(new Font("Arial", Font.BOLD, 12));
		lblMes.setBounds(45, 126, 140, 20);
		getContentPane().add(lblMes);

		lblBanco = new JLabel("BANCO");
		lblBanco.setHorizontalAlignment(SwingConstants.LEFT);
		lblBanco.setFont(new Font("Arial", Font.BOLD, 12));
		lblBanco.setBounds(45, 150, 140, 20);
		getContentPane().add(lblBanco);

		lblC1 = new JLabel("CART\u00C3O 1");
		lblC1.setHorizontalAlignment(SwingConstants.LEFT);
		lblC1.setFont(new Font("Arial", Font.BOLD, 12));
		lblC1.setBounds(45, 173, 140, 20);
		getContentPane().add(lblC1);

		lblC2 = new JLabel("CART\u00C3O 2");
		lblC2.setHorizontalAlignment(SwingConstants.LEFT);
		lblC2.setFont(new Font("Arial", Font.BOLD, 12));
		lblC2.setBounds(45, 196, 140, 20);
		getContentPane().add(lblC2);

		lblLuz = new JLabel("CONTA DE LUZ");
		lblLuz.setHorizontalAlignment(SwingConstants.LEFT);
		lblLuz.setFont(new Font("Arial", Font.BOLD, 12));
		lblLuz.setBounds(45, 218, 140, 20);
		getContentPane().add(lblLuz);

		lblInternet = new JLabel("INTERNET");
		lblInternet.setHorizontalAlignment(SwingConstants.LEFT);
		lblInternet.setFont(new Font("Arial", Font.BOLD, 12));
		lblInternet.setBounds(45, 242, 140, 20);
		getContentPane().add(lblInternet);

		lblFacul = new JLabel("FACULDADE");
		lblFacul.setHorizontalAlignment(SwingConstants.LEFT);
		lblFacul.setFont(new Font("Arial", Font.BOLD, 12));
		lblFacul.setBounds(45, 265, 140, 20);
		getContentPane().add(lblFacul);

		lblPessoal = new JLabel("CONTAS PESSOAIS");
		lblPessoal.setHorizontalAlignment(SwingConstants.LEFT);
		lblPessoal.setFont(new Font("Arial", Font.BOLD, 12));
		lblPessoal.setBounds(45, 288, 140, 20);
		getContentPane().add(lblPessoal);

		lblCasa = new JLabel("CONTAS CASA");
		lblCasa.setHorizontalAlignment(SwingConstants.LEFT);
		lblCasa.setFont(new Font("Arial", Font.BOLD, 12));
		lblCasa.setBounds(45, 311, 140, 20);
		getContentPane().add(lblCasa);

		lblCelular = new JLabel("CELULAR");
		lblCelular.setHorizontalAlignment(SwingConstants.LEFT);
		lblCelular.setFont(new Font("Arial", Font.BOLD, 12));
		lblCelular.setBounds(45, 334, 140, 20);
		getContentPane().add(lblCelular);

		txtNull = new JTextField();
		txtNull.setEditable(false);
		txtNull.setEnabled(false);
		txtNull.setBounds(300, 127, 109, 20);
		getContentPane().add(txtNull);
		txtNull.setColumns(10);

		JLabel lblGanhos = new JLabel("RECEITAS EM R$");
		lblGanhos.setHorizontalAlignment(SwingConstants.LEFT);
		lblGanhos.setFont(new Font("Arial", Font.BOLD, 12));
		lblGanhos.setBounds(437, 106, 109, 17);
		getContentPane().add(lblGanhos);

		JLabel lblDbitos = new JLabel("D\u00C9BITOS EM R$");
		lblDbitos.setHorizontalAlignment(SwingConstants.LEFT);
		lblDbitos.setFont(new Font("Arial", Font.BOLD, 12));
		lblDbitos.setBounds(437, 173, 109, 17);
		getContentPane().add(lblDbitos);

		JLabel lblSobra = new JLabel("SALDO EM R$");
		lblSobra.setHorizontalAlignment(SwingConstants.LEFT);
		lblSobra.setFont(new Font("Arial", Font.BOLD, 12));
		lblSobra.setBounds(437, 242, 109, 17);
		getContentPane().add(lblSobra);

		txtGanhos = new JTextField();
		txtGanhos.setText("1500,00");
		txtGanhos.setFont(new Font("Arial", Font.BOLD, 12));
		txtGanhos.setColumns(10);
		txtGanhos.setBounds(437, 126, 109, 20);
		getContentPane().add(txtGanhos);

		txtDebitos = new JTextField();

		txtDebitos.setFont(new Font("Arial", Font.BOLD, 12));
		txtDebitos.setColumns(10);
		txtDebitos.setBounds(437, 193, 109, 20);
		getContentPane().add(txtDebitos);

		txtSobra = new JTextField();
		txtSobra.setFont(new Font("Arial", Font.BOLD, 12));
		txtSobra.setColumns(10);
		txtSobra.setBounds(437, 262, 109, 20);
		getContentPane().add(txtSobra);

		lblData = new JLabel("DATA");
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		lblData.setFont(new Font("Arial", Font.BOLD, 12));
		lblData.setBounds(165, 22, 255, 14);
		getContentPane().add(lblData);

		JButton btnLimpar = new JButton("LIMPAR");
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar2();
			}
		});
		btnLimpar.setToolTipText("LIMPAR CAMPOS");
		btnLimpar.setFont(new Font("Arial", Font.BOLD, 11));
		btnLimpar.setBounds(464, 383, 100, 32);
		getContentPane().add(btnLimpar);

		JLabel lblNewLabel_1 = new JLabel("DEVELOPED BY ANDR\u00C9 CARVALHO ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel_1.setBounds(181, 439, 228, 14);
		getContentPane().add(lblNewLabel_1);

		txtPesq = new JTextField();
		txtPesq.setFont(new Font("Arial", Font.BOLD, 12));
		txtPesq.setBounds(181, 57, 228, 20);
		getContentPane().add(txtPesq);
		txtPesq.setColumns(10);

		JButton btnPesq = new JButton("PESQUISAR");
		btnPesq.setFont(new Font("Arial", Font.BOLD, 12));
		btnPesq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesq.setBounds(437, 56, 109, 23);
		getContentPane().add(btnPesq);

		javax.swing.UIManager.put("OptionPane.messageFont", new Font("Calibri", Font.BOLD, 14)); // linha para
																									// personalizar o
																									// text e font do
																									// Joptionpane

		conexao = ModuloConexao.conector();// conectar com o banco (conex�o fechada em login)

	}// fim do construtor

	// limpar campos do Jframe
	// private void limpar() {
	// txtPesquisar.setText(null);
	// limparTabela();

	// }

	private void pesquisar() {
		String read = "select * from tb_contas where mes = ?"; // ? � um par�metro
		try {
			pst = conexao.prepareStatement(read);
			pst.setString(1, txtPesq.getText()); // substituir o ? pelo valor da caixa de texto de nome txtId
			rs = pst.executeQuery(); // executa o script e retorna os dados
			if (rs.next()) {
				txtV1.setText(rs.getString(1));
				txtV2.setText(rs.getString(2));
				cmb1.setSelectedItem(rs.getString(3));
				txtV3.setText(rs.getString(4));
				cmb2.setSelectedItem(rs.getString(5));
				txtV4.setText(rs.getString(6));
				cmb3.setSelectedItem(rs.getString(7));
				txtV5.setText(rs.getString(8));
				cmb4.setSelectedItem(rs.getString(9));
				txtV6.setText(rs.getString(10));
				cmb5.setSelectedItem(rs.getString(11));
				txtV7.setText(rs.getString(12));
				cmb6.setSelectedItem(rs.getString(13));
				txtV8.setText(rs.getString(14));
				cmb7.setSelectedItem(rs.getString(15));
				txtV9.setText(rs.getString(16));
				cmb8.setSelectedItem(rs.getString(17));
				txtV10.setText(rs.getString(18));
				cmb9.setSelectedItem(rs.getString(19));

			} else {
				JOptionPane.showMessageDialog(null, "M�s n�o adicionado !");
				// limpar();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// metodo para limpar a tabela
	// private void limparTabela() {
	// while (tblPrincipal.getRowCount() > 0) {
	// ((DefaultTableModel) tblPrincipal.getModel()).removeRow(0);
	// }
	// }

	// Metodo para alterar cliente
	private void editar() {
		if (txtV1.getText().isEmpty() || txtV2.getText().isEmpty() || txtV3.getText().isEmpty()
				|| txtV4.getText().isEmpty() || txtV5.getText().isEmpty() || txtV6.getText().isEmpty()
				|| txtV7.getText().isEmpty() || txtV8.getText().isEmpty() || txtV9.getText().isEmpty()
				|| txtV10.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha os campos obrigat�rios");
		} else {
			String editar = "update tb_contas set banco=?, sBanco=?, cartaoB1=?, sCartaoB1=?, cartaoB2=?, sCartaoB2=?, luz=?, sLuz=?, "
					+ "net=?, sNet=?, facul=?, sFacul=?, pessoal=?, sPessoal=?, casa=?, sCasa=?, celular=?, sCelular=? where mes= ?";
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
					// limpar();
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
					// limpar();
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
			String create = "insert into tb_contas(mes, banco, sBanco, cartaoB1, sCartaoB1, cartaoB2, sCartaoB2, luz, sLuz, net, sNet, facul, "
					+ "sFacul, pessoal, sPessoal, casa, sCasa, celular, sCelular) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
					// limpar();
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
				// limpar();
				if (removido == 1) {
					// limpar();
					JOptionPane.showMessageDialog(null, "Contas deste m�s removidas");
				} else {
					JOptionPane.showMessageDialog(null,
							"N�o foi possivel remover as contas deste m�s ! \nCampos sem preencher !");
				}

			} catch (Exception e) {
				System.out.println(e);
				// limpar();
			}
		}

	}

	private void calcular() {

		if (txtV1.getText().isEmpty() || txtV2.getText().isEmpty() || txtV3.getText().isEmpty()
				|| txtV4.getText().isEmpty() || txtV5.getText().isEmpty() || txtV6.getText().isEmpty()
				|| txtV7.getText().isEmpty() || txtV8.getText().isEmpty() || txtV9.getText().isEmpty()
				|| txtV10.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha os campos obrigat�rios");
		}

		double valor1, valor2, resultado;
		DecimalFormat formatador = new DecimalFormat("0.00");
		valor1 = Double.parseDouble(txtGanhos.getText().replace(",", "."));
		valor2 = Double.parseDouble(txtDebitos.getText().replace(",", "."));
		resultado = valor1 - valor2;
		txtSobra.setText(formatador.format(resultado));
		if (resultado >= 0) {
			JOptionPane.showMessageDialog(null,
					" Voc� tem saldo para gastar este m�s no valor de : " + txtSobra.getText() + " Reais ",
					"Saldo atual", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null,
					" Voc� tem saldo negativo este m�s no valor de : " + txtSobra.getText() + " Reais ", "Saldo atual",
					JOptionPane.INFORMATION_MESSAGE);
		}

		// limpar();
	}

	private void alterarLabel() {// metodo pra mostrar data e hora no lugar do Frame link
									// https://www.it-swarm.dev/pt/java/como-analisarformatar-datas-com-localdatetime-java-8/1044545622/
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("E dd/MM/yyyy HH:mm");
		LocalDateTime data = LocalDateTime.now();
		lblData.setText(data.format(formatador));
	}

	private void limpar2() {
		txtV1.setText(null);
		txtV2.setText(null);
		cmb1.setSelectedItem(null);
		txtV3.setText(null);
		cmb2.setSelectedItem(null);
		txtV4.setText(null);
		cmb3.setSelectedItem(null);
		txtV5.setText(null);
		cmb4.setSelectedItem(null);
		txtV6.setText(null);
		cmb5.setSelectedItem(null);
		txtV7.setText(null);
		cmb6.setSelectedItem(null);
		txtV8.setText(null);
		cmb7.setSelectedItem(null);
		txtV9.setText(null);
		cmb8.setSelectedItem(null);
		txtV10.setText(null);
		cmb9.setSelectedItem(null);
		txtGanhos.setText(null);
		txtDebitos.setText(null);
		txtSobra.setText(null);
		txtPesq.setText(null);
	}
}
