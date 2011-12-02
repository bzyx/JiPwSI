package pl.polsl.flota.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import pl.polsl.flota.controller.CarController;

/**
 * The Class ApplicationWindow.
 */
public class ApplicationWindow extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The left part of frame. */
	Component leftPart;
	
	/** The right part of frame. */
	Component rightPart;
	
	/** The splitPane to make 2 parts. */
	JSplitPane splitPane;
	
	/** The car controller. */
	private CarController carController;
	
	/** The table. */
	JTable table;
	
	/** The car refuel list. */
	CarRefuelList carRefuelList;
	
	/** The list. */
	@SuppressWarnings("rawtypes")
	JList list;
	
	/** The jframe. */
	JFrame jframe;
	
	/** The car table view. */
	CarTableView carTableView;

	/** The menu bar. */
	JMenuBar menuBar;
	
	/** The submenu. */
	JMenu menu, submenu;
	
	/**
	 * Instantiates a new application window.
	 *
	 * @param fileName the file name
	 * @throws HeadlessException the headless exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("rawtypes")
	public ApplicationWindow(final String fileName) throws HeadlessException,
	IOException {
		super();
		jframe = this;
		this.setTitle("Fleet Manager! ;) ");
		this.setSize(800, 550);
		this.setLocation(100, 25);
		this.setResizable(false);

		carController = new CarController(fileName);

		carTableView = new CarTableView(carController);

		table = new JTable(carTableView);
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn col = table.getColumnModel().getColumn(1);
		col.setPreferredWidth(150);

		JScrollPane tableScrollPane = new JScrollPane(table);

		table.getSelectionModel().addListSelectionListener(new RowListener());

		carRefuelList = new CarRefuelList(carController);

		list = new JList();
		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.setColumnHeaderView(new JLabel(
				"Data / Wartość - Ilość / Przebieg"));

		leftPart = tableScrollPane;
		rightPart = listScrollPane;

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPart, rightPart);
		splitPane.setDividerLocation(500);
		splitPane.setDividerSize(10);

		this.add(splitPane);
		this.setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				carController.save(fileName);
				System.exit(0);
			}
		});

		menuBar = new JMenuBar();

		menu = new JMenu("Plik");
		menuBar.add(menu);

		JMenuItem saveChangesMenuItem = new JMenuItem("Zapisz zmiany");
		menu.add(saveChangesMenuItem);
		saveChangesMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				carController.save(fileName);
			}
		});

		menu.addSeparator();

		JMenuItem exitMenuItem = new JMenuItem("Wyjście");
		menu.add(exitMenuItem);
		exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		menu = new JMenu("Obsługa");
		menuBar.add(menu);

		JMenuItem addCarMenuItem = new JMenuItem("Dodaj samochód");
		menu.add(addCarMenuItem);
		addCarMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final AddCarDialog dialog = new AddCarDialog(null, true,
						carController);
				dialog.setVisible(true);
				carTableView.fireTableDataChanged();
			}
		});

		JMenuItem deleteCarMenuItem = new JMenuItem("Usuń samochód");
		menu.add(deleteCarMenuItem);
		deleteCarMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				carTableView.deleteItem(table.getSelectedRow());
			}
		});

		menu = new JMenu("Pomoc");
		menuBar.add(menu);

		JMenuItem aboutMenuItem = new JMenuItem("O programie");
		menu.add(aboutMenuItem);
		aboutMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"FleetManager;) \nMarcin Jabrzyk\n JiPWSI gr. R",
						"O Programie", JOptionPane.INFORMATION_MESSAGE);

			}
		});

		this.setJMenuBar(menuBar);

		JToolBar toolbar = new JToolBar();
		toolbar.setRollover(true);

		JButton button = new JButton("Dodaj samochód");
		toolbar.add(button);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final AddCarDialog dialog = new AddCarDialog(null, true,
						carController);
				dialog.setVisible(true);
				carTableView.fireTableDataChanged();
			}
		});

		toolbar.addSeparator();

		Container contentPane = this.getContentPane();
		contentPane.add(toolbar, BorderLayout.NORTH);
	}

	/**
	 * The listener interface for receiving row events.
	 * Listens for change of row - the selection of row.
	 *
	 * @see RowEvent
	 */
	private class RowListener implements ListSelectionListener {
		@SuppressWarnings("unchecked")
		public void valueChanged(ListSelectionEvent event) {
			if (event.getValueIsAdjusting()) {
				return;
			}
			carRefuelList.setCurrentElement(table.getSelectionModel()
					.getLeadSelectionIndex());
			list.setModel(carRefuelList);
		}
	}
}
