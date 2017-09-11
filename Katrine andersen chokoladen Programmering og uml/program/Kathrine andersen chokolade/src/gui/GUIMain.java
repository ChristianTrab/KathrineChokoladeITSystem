package gui;

import javax.swing.*;
import java.util.*;

import ctr.CustomerCtr;
import ctr.IngredientsCtr;
import ctr.OrderCtr;
import ctr.ProductCtr;
import ctr.SaleLineItemCtr;
import model.Company;
import model.Customer;
import model.Employee;
import model.Order;
import model.Product;
import model.SaleLineItem;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.ScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.List;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class GUIMain extends JFrame {
	private JTextField txtIdKundeFirma, txtLeveringdato, txtRabatBelb;
	DefaultTableModel oi = new DefaultTableModel();
	DefaultTableModel ol = new DefaultTableModel();
	private JTable table2, table_1, table_2, table_3, table_4, table_5;
	private JLabel lblFirmaNavn_1, lblFornavnEfternavn, lblType, lblCvr_1, lblKontoOplysninger, lblAddresse,
			lblByZipcode, lblPhoneNumber, lblEmailemaildk, lblBetaltElIkke, lblMomsBelb, lblTilbudsbelb, lblTotalPrisI;

	OrderCtr orderCtr = new OrderCtr();
	ProductCtr productCtr = new ProductCtr();
	CustomerCtr customerCtr = new CustomerCtr();
	private int orderId, selectedOrderRow, selectedProductRow;
	private GUIMain guiMain;
	public LinkedList<SaleLineItem> productList;
	private LinkedList<Order> orderList, activeList = new LinkedList<Order>();
	private SaleLineItemCtr saleLineItemCtr = new SaleLineItemCtr();

	public GUIMain() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, SQLException {
		guiMain = this;
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		setTitle("Kathrine Andersen Chokolade ApS");
		setSize(1920, 1045);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton btnNewButton = new JButton("Produkt");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIProduct guiProduct = new GUIProduct();
			}
		});
		menuBar.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Kunde");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GUICustomer guiCustomer = new GUICustomer();
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuBar.add(btnNewButton_1);
		getContentPane().setLayout(null);

		txtIdKundeFirma = new JTextField();
		txtIdKundeFirma.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtIdKundeFirma.setText("");
			}
		});
		txtIdKundeFirma.setText("Id, Kunde, Firma, CVR");
		txtIdKundeFirma.setBounds(20, 39, 163, 25);
		getContentPane().add(txtIdKundeFirma);
		txtIdKundeFirma.setColumns(10);

		JScrollPane Active_Orders_ScrollPane = new JScrollPane();
		Active_Orders_ScrollPane.setBounds(20, 75, 1672, 474);
		getContentPane().add(Active_Orders_ScrollPane);

		table2 = new JTable(ol);
		table2.setDefaultEditor(Object.class, null); 				// Makes the cell non-editable

		ol.addColumn("ID");
		ol.addColumn("Levering dato");
		ol.addColumn("Kunde");
		ol.addColumn("Firma");
		ol.addColumn("Type");
		ol.addColumn("Beløb");
		ol.addColumn("Produkt mængde");
		ol.addColumn("Status");
		Active_Orders_ScrollPane.setViewportView(table2);

		showActiveOrders();

		table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {

					try {
						showProductsOnOrder();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

		JButton btnNewButton_2 = new JButton("Ny Ordre");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUINewOrder orderGUI = new GUINewOrder();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(1702, 75, 181, 37);
		getContentPane().add(btnNewButton_2);

		JButton btnNewButton_4 = new JButton("Fjern Ordre");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// GUIWarning warningGUI = new GUIWarning();
				try {
					GUIWarning warning = new GUIWarning("Sikker på du vil slette den markerede ordre?");
					warning.setModal(true);
					warning.setVisible(true);
					if (warning.getWarningResult() == 1) {
						warning.dispose();
						cancelOrder();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_4.setBackground(Color.RED);
		btnNewButton_4.setBounds(1702, 512, 181, 37);
		getContentPane().add(btnNewButton_4);

		JScrollPane Order_Informatin_ScrollPane = new JScrollPane();
		Order_Informatin_ScrollPane.setBounds(20, 615, 945, 341);
		getContentPane().add(Order_Informatin_ScrollPane);

		table_1 = new JTable(oi);
		  


		oi.addColumn("ID");
		oi.addColumn("Produkt navn");
		oi.addColumn("Emb. Type");
		oi.addColumn("Antal Bestilt");
		oi.addColumn("Stk. Pris");
		oi.addColumn("Total Pris");
		Order_Informatin_ScrollPane.setViewportView(table_1);

		JButton btnNewButton_5 = new JButton("Tilf\u00F8j vare");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIAddProduct addProduct = new GUIAddProduct(activeList.get(selectedOrderRow), guiMain);
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_5.setBounds(994, 615, 166, 37);
		getContentPane().add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("Redigere antal");
		btnNewButton_6.addActionListener(new ActionListener() 

//						Åbner Nyt Vindue - Langt fra færdig.				
//		{
//			public void actionPerformed(ActionEvent arg0) {
//			GUIEditQty editQtyWindow = new GUIEditQty(activeList.get(selectedOrderRow), guiMain);
//		}
//			});

				
// 					Rediger i SalesLineFelt (Husk at trykke enter efter endt redigering) og tryk rediger antal - Fuld Funktionel - Dog uden RefreshTables				
		
		{
			public void actionPerformed(ActionEvent e) {
				try {
					GUIConfirm confirm = new GUIConfirm("Antal bestilt er blevet redigeret!");
					confirm.setModal(true);
					confirm.setVisible(true);
					if (confirm.getConfirmResult() == 1) {
						editSaleLineItemQuantity();
						confirm.setVisible(false);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_6.setBounds(994, 663, 166, 37);
		getContentPane().add(btnNewButton_6);

		JButton btnFjernVare = new JButton("Fjern  vare");
		btnFjernVare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					GUIWarning warning = new GUIWarning("Sikker på du vil slette den markerede vare?");
					warning.setModal(true);
					warning.setVisible(true);
					if (warning.getWarningResult() == 1) {
						warning.dispose();
						removeSaleLineItemFromOrder();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnFjernVare.setBackground(Color.RED);
		btnFjernVare.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFjernVare.setBounds(994, 919, 166, 37);
		getContentPane().add(btnFjernVare);

		JButton btnSgVare = new JButton("S\u00F8g ordre");
		btnSgVare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					searchOrder(txtIdKundeFirma.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSgVare.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSgVare.setBounds(193, 38, 92, 25);
		getContentPane().add(btnSgVare);

		JLabel lblFirmaNavn = new JLabel("Firma Navn:");
		lblFirmaNavn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirmaNavn.setBounds(1210, 616, 73, 14);
		getContentPane().add(lblFirmaNavn);

		JLabel lblKontaktPerson = new JLabel("Kontakt Person:");
		lblKontaktPerson.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKontaktPerson.setBounds(1210, 648, 109, 14);
		getContentPane().add(lblKontaktPerson);

		JLabel lblPrivateElErhverv = new JLabel("Private el. Erhverv:");
		lblPrivateElErhverv.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPrivateElErhverv.setBounds(1210, 684, 121, 14);
		getContentPane().add(lblPrivateElErhverv);

		JLabel lblCvr = new JLabel("CVR:");
		lblCvr.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCvr.setBounds(1210, 719, 109, 14);
		getContentPane().add(lblCvr);

		JLabel lblKontoOplys = new JLabel("Konto oplys:");
		lblKontoOplys.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKontoOplys.setBounds(1210, 755, 109, 14);
		getContentPane().add(lblKontoOplys);

		lblFirmaNavn_1 = new JLabel("Firma navn");
		lblFirmaNavn_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFirmaNavn_1.setBounds(1343, 616, 172, 14);
		getContentPane().add(lblFirmaNavn_1);

		lblFornavnEfternavn = new JLabel("Fornavn + Efternavn");
		lblFornavnEfternavn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFornavnEfternavn.setBounds(1343, 649, 172, 14);
		getContentPane().add(lblFornavnEfternavn);

		lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblType.setBounds(1343, 685, 172, 14);
		getContentPane().add(lblType);

		lblCvr_1 = new JLabel("CVR");
		lblCvr_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCvr_1.setBounds(1343, 720, 172, 14);
		getContentPane().add(lblCvr_1);

		lblKontoOplysninger = new JLabel("Konto oplysninger");
		lblKontoOplysninger.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblKontoOplysninger.setBounds(1343, 756, 172, 14);
		getContentPane().add(lblKontoOplysninger);

		JLabel lblLeveringsaddresse = new JLabel("Leveringsadresse:");
		lblLeveringsaddresse.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLeveringsaddresse.setBounds(1564, 615, 123, 14);
		getContentPane().add(lblLeveringsaddresse);

		lblAddresse = new JLabel("Addresse");
		lblAddresse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddresse.setBounds(1697, 615, 172, 14);
		getContentPane().add(lblAddresse);

		lblByZipcode = new JLabel("By + zipCode");
		lblByZipcode.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblByZipcode.setBounds(1697, 648, 172, 14);
		getContentPane().add(lblByZipcode);

		JLabel lblByPostnr = new JLabel("By & Postnr:");
		lblByPostnr.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblByPostnr.setBounds(1564, 647, 109, 14);
		getContentPane().add(lblByPostnr);

		JLabel lblTelefonNr = new JLabel("Telefon Nr:");
		lblTelefonNr.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefonNr.setBounds(1564, 683, 121, 14);
		getContentPane().add(lblTelefonNr);

		lblPhoneNumber = new JLabel("Phone number");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPhoneNumber.setBounds(1697, 684, 172, 14);
		getContentPane().add(lblPhoneNumber);

		lblEmailemaildk = new JLabel("Email@email.dk");
		lblEmailemaildk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmailemaildk.setBounds(1697, 719, 172, 14);
		getContentPane().add(lblEmailemaildk);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(1564, 718, 109, 14);
		getContentPane().add(lblEmail);

		JLabel lblStatus = new JLabel("Betalings Status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStatus.setBounds(1210, 870, 73, 14);
		getContentPane().add(lblStatus);

		lblBetaltElIkke = new JLabel("Betalt el. Ikke betalt");
		lblBetaltElIkke.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBetaltElIkke.setBounds(1343, 870, 172, 14);
		getContentPane().add(lblBetaltElIkke);

		JLabel lblMoms = new JLabel("Moms 25%:");
		lblMoms.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMoms.setBounds(1210, 905, 109, 14);
		getContentPane().add(lblMoms);

		lblMomsBelb = new JLabel("Moms bel\u00F8b");
		lblMomsBelb.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMomsBelb.setBounds(1343, 905, 172, 14);
		getContentPane().add(lblMomsBelb);

		JLabel lblRabatPris = new JLabel("Rabat pris:");
		lblRabatPris.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRabatPris.setBounds(1210, 835, 121, 14);
		getContentPane().add(lblRabatPris);

		lblTilbudsbelb = new JLabel("Tilbuds bel\u00F8b");
		lblTilbudsbelb.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTilbudsbelb.setBounds(1343, 835, 172, 14);
		getContentPane().add(lblTilbudsbelb);

		JLabel lblTotalAtBetale = new JLabel("Total at betale:");
		lblTotalAtBetale.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalAtBetale.setBounds(1210, 940, 109, 14);
		getContentPane().add(lblTotalAtBetale);

		lblTotalPrisI = new JLabel("Total pris i DKK");
		lblTotalPrisI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTotalPrisI.setBounds(1343, 940, 172, 14);
		getContentPane().add(lblTotalPrisI);

		table_3 = new JTable();
		table_3.setBorder(
				new TitledBorder(null, "Ordre Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table_3.setBackground(UIManager.getColor("Button.background"));
		table_3.setBounds(10, 586, 1165, 384);
		getContentPane().add(table_3);

		JSeparator separator = new JSeparator();
		separator.setBounds(20, 571, 1904, 14);
		getContentPane().add(separator);

		getContentPane();

		txtLeveringdato = new JTextField();
		txtLeveringdato.setBounds(1564, 748, 109, 30);
		getContentPane().add(txtLeveringdato);
		txtLeveringdato.setColumns(10);

		JButton btnRedigerDato = new JButton("Rediger dato");
		btnRedigerDato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					orderCtr.updateOrderInDB(activeList.get(selectedOrderRow),
							activeList.get(selectedOrderRow).getTotalPrice(), activeList.get(selectedOrderRow).getTax(),
							txtLeveringdato.getText(), activeList.get(selectedOrderRow).getStatus(),
							activeList.get(selectedOrderRow).getDiscountPrice(),
							activeList.get(selectedOrderRow).getCustomer(),
							activeList.get(selectedOrderRow).getCompany(),
							activeList.get(selectedOrderRow).getEmployee());
					showProductsOnOrder();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				updateTable();
			}
		});
		btnRedigerDato.setBounds(1697, 748, 109, 30);
		getContentPane().add(btnRedigerDato);

		table_4 = new JTable();
		table_4.setBorder(
				new TitledBorder(null, "Kontakt Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table_4.setBackground(UIManager.getColor("Button.background"));
		table_4.setBounds(1197, 586, 697, 203);
		getContentPane().add(table_4);

		txtRabatBelb = new JTextField();
		txtRabatBelb.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtRabatBelb.setText("");
			}
		});

		txtRabatBelb.setText("Rabat bel\u00F8b");
		txtRabatBelb.setColumns(10);
		txtRabatBelb.setBounds(1564, 828, 109, 30);
		getContentPane().add(txtRabatBelb);

		JButton btnOpretRabat = new JButton("Opret rabat");
		btnOpretRabat.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				try {
					GUIWarning warning = new GUIWarning("Sikker på du vil sætte/ændre rabat den markerede ordre?");
					warning.setModal(true);
					warning.setVisible(true);
					if (warning.getWarningResult() == 1) {
						warning.dispose();
						orderCtr.updateDiscount(activeList.get(selectedOrderRow),
						Double.parseDouble(txtRabatBelb.getText()));
						showProductsOnOrder();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOpretRabat.setBounds(1697, 828, 109, 30);
		getContentPane().add(btnOpretRabat);

		table_5 = new JTable();
		table_5.setBorder(
				new TitledBorder(null, "Betalings information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table_5.setBackground(UIManager.getColor("Button.background"));
		table_5.setBounds(1197, 800, 697, 170);
		getContentPane().add(table_5);

		JButton btnSgAktive = new JButton("Vis aktive ordre");
		btnSgAktive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showActiveOrders();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSgAktive.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSgAktive.setBounds(295, 38, 129, 25);
		getContentPane().add(btnSgAktive);

		JButton btnVisAlleOrdre = new JButton("Vis alle ordre");
		btnVisAlleOrdre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					showAllOrders();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVisAlleOrdre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVisAlleOrdre.setBounds(434, 38, 129, 25);
		getContentPane().add(btnVisAlleOrdre);

		JButton btnAfslutOrdre = new JButton("Print Faktura");
		btnAfslutOrdre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					GUIShowReceipt guiShowRecipt = new GUIShowReceipt(activeList.get(selectedOrderRow), productList,
							saleLineItemCtr);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAfslutOrdre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAfslutOrdre.setBounds(1704, 125, 181, 37);
		getContentPane().add(btnAfslutOrdre);

		JButton btnAfslutOrdre_1 = new JButton("Afslut Ordre");
		btnAfslutOrdre_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GUIWarning warning = new GUIWarning("Sikker på du vil afslutte den markerede ordre?");
					warning.setModal(true);
					warning.setVisible(true);
					if (warning.getWarningResult() == 1) {
						warning.dispose();
						endOrder();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAfslutOrdre_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAfslutOrdre_1.setBounds(1704, 175, 181, 37);
		getContentPane().add(btnAfslutOrdre_1);

		table_2 = new JTable();
		table_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table_2.setBackground(UIManager.getColor("Button.background"));
		table_2.setBorder(new TitledBorder(null, "Ordre Liste", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table_2.setBounds(10, 15, 1884, 544);
		getContentPane().add(table_2);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public void searchOrder(String search) throws SQLException {
		clearOrderTable();
		activeList = orderCtr.findOrderByString(txtIdKundeFirma.getText());

		int orderListIndex = 0;
		while (orderListIndex < activeList.size()) {
			Order o = activeList.get(orderListIndex);
			Customer c = o.getCustomer();
			ol.addRow(new Object[] { o.getId(), o.getDate(), c.getfName() + " " + c.getlName(), c.getCompanyName(),
					c.getType(), o.getTotalPrice(), "Mangler", o.getStatus() });
			orderListIndex++;
		}
	}

	public void showAllOrders() throws SQLException {
		clearOrderTable();
		activeList = orderCtr.getAllOrders();
		table2.clearSelection();
		
		activeList.parallelStream().sorted();

		int orderListIndex = 0;
		while (orderListIndex < activeList.size()) {
			ol.addRow(new Object[] { activeList.get(orderListIndex).getId(), activeList.get(orderListIndex).getDate(),
					activeList.get(orderListIndex).getCustomer().getfName() + " "
							+ activeList.get(orderListIndex).getCustomer().getlName(),
					activeList.get(orderListIndex).getCustomer().getCompanyName(),
					activeList.get(orderListIndex).getCustomer().getType(),
					activeList.get(orderListIndex).getTotalPrice(), "Mangler",
					activeList.get(orderListIndex).getStatus() });
			orderListIndex++;
		}
	}

	public void showActiveOrders() throws SQLException {
		// Method for getting active orders
		clearOrderTable();
		activeList = orderCtr.getAllActiveOrders();
		table2.clearSelection();

		activeList.parallelStream().sorted();

		int activeListIndex = 0;
		while (activeListIndex < activeList.size()) {
			ol.addRow(new Object[] { activeList.get(activeListIndex).getId(), activeList.get(activeListIndex).getDate(),
					activeList.get(activeListIndex).getCustomer().getfName() + " "
							+ activeList.get(activeListIndex).getCustomer().getlName(),
					activeList.get(activeListIndex).getCustomer().getCompanyName(),
					activeList.get(activeListIndex).getCustomer().getType(),
					activeList.get(activeListIndex).getTotalPrice(), "Mangler",
					activeList.get(activeListIndex).getStatus() });
			activeListIndex++;
		}
	}

	public void showProductsOnOrder() throws SQLException {

		selectedOrderRow = table2.getSelectedRow();

		if (selectedOrderRow == -1) {

		} else {

			oi.setRowCount(0);
			productList = orderCtr.getAllProductsInOrder(activeList.get(selectedOrderRow).getId());

			int productListIndex = 0;
			while (productListIndex < productList.size()) {
				SaleLineItem sli = productList.get(productListIndex);
				Product p = sli.getProduct();
				oi.addRow(new Object[] { p.getId(), p.getName(), "Emb Type", sli.getQuantity(), p.getPrice(),
						sli.getPrice() });
				productListIndex++;
			}

			// Changes labes on contactinformation with selected row in table2
			lblFirmaNavn_1.setText(activeList.get(selectedOrderRow).getCustomer().getCompanyName());
			lblFornavnEfternavn.setText(activeList.get(selectedOrderRow).getCustomer().getfName() + " "
					+ activeList.get(selectedOrderRow).getCustomer().getlName());
			lblType.setText(activeList.get(selectedOrderRow).getCustomer().getType());
			lblCvr_1.setText(activeList.get(selectedOrderRow).getCustomer().getCvr());
			// lblKontoOplysninger.setText(o.getCustomer());
			lblAddresse.setText(activeList.get(selectedOrderRow).getCustomer().getAddress());
			lblByZipcode.setText(activeList.get(selectedOrderRow).getCustomer().getCity() + " - "
					+ activeList.get(selectedOrderRow).getCustomer().getZipCode());
			lblPhoneNumber.setText(activeList.get(selectedOrderRow).getCustomer().getPhone());
			lblEmailemaildk.setText(activeList.get(selectedOrderRow).getCustomer().getEmail());
			// lblBetaltElIkke.setText(o.getCustomer());
			lblMomsBelb.setText(String.valueOf(activeList.get(selectedOrderRow).getTax() + ",-"));
			lblTilbudsbelb.setText(String.valueOf(activeList.get(selectedOrderRow).getDiscountPrice() + ",-"));
			lblTotalPrisI.setText(String.valueOf(activeList.get(selectedOrderRow).getTotalPrice() + ",-"));
			txtLeveringdato.setText(activeList.get(selectedOrderRow).getDate());
			orderId = activeList.get(selectedOrderRow).getId();
		}
	}

	public void removeSaleLineItemFromOrder() throws SQLException {
		selectedProductRow = table_1.getSelectedRow();
		System.out.println(selectedProductRow);
		SaleLineItem sli = productList.get(selectedProductRow);
		System.out.println(sli.getProduct().getName());
		oi.removeRow(selectedProductRow);
		saleLineItemCtr.deleteSaleLineItem(activeList.get(selectedOrderRow), sli);
	}

	public void clearOrderTable() {
		for (int x = ol.getRowCount() - 1; x >= 0; x--) {
			ol.removeRow(x);
			System.out.println("Row removed");
		}

		while (!activeList.isEmpty()) {
			activeList.removeFirst();
			System.out.println("remove order from list");
		}
	}

	public void clearProductTable() {
		for (int x = oi.getRowCount() - 1; x >= 0; x--) {
			oi.removeRow(x);
			System.out.println("Row removed");
		}

		while (!productList.isEmpty()) {
			productList.removeFirst();
			System.out.println("remove order from list");
		}
	}

	public void updateTable() {
		ol.setValueAt(activeList.get(selectedOrderRow).getDate(), selectedOrderRow, 1);
		// ol.setRowCount(0);
		table_1.revalidate();
	}

	public void cancelOrder() throws SQLException {
		for (SaleLineItem sli : productList) {
			saleLineItemCtr.deleteSaleLineItem(activeList.get(selectedOrderRow), sli);
		}
		orderCtr.deleteOrder(activeList.get(selectedOrderRow));
		activeList.remove(selectedOrderRow);
		ol.removeRow(selectedOrderRow);
		clearProductTable();
	}

	public void editSaleLineItemQuantity() throws SQLException {
		orderCtr.updatePriceToZeroForUpdatingSaleLineItem(activeList.get(selectedOrderRow));

		for (int x = 0; x < productList.size(); x++) {
			int quantity = Integer.parseInt(oi.getValueAt(x, 3).toString());
			saleLineItemCtr.updateSaleLineItem(activeList.get(selectedOrderRow), productList.get(x), quantity);
		}

	}

	public void endOrder() throws SQLException {
		selectedOrderRow = table2.getSelectedRow();
		Order o = activeList.get(selectedOrderRow);
		orderCtr.endSale(o);
		ol.removeRow(selectedOrderRow);
		clearProductTable();
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		try {
			GUIMain gui = new GUIMain();
			IngredientsCtr ingredientsCtr = new IngredientsCtr();
			ingredientsCtr.resetMonthlyConsumption();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
