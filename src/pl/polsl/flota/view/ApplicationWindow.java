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
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import pl.polsl.flota.controller.CarController;


public class ApplicationWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	Component leftPart;
	Component rightPart;
	JSplitPane hpane;
	private CarController carController;
	JTable table;
	CarRefuelList carRefuelList;
	JList list;
	JFrame jframe;
	CarTableView carTableView;

	
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	
	
	public ApplicationWindow(final String fileName) throws HeadlessException, IOException {
		//this.fileName = fileName;
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
	    listScrollPane.setColumnHeaderView(new JLabel("Data / Wartość - Ilość / Przebieg") );

		
		leftPart = tableScrollPane;
		rightPart = listScrollPane;
		
		hpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftPart,rightPart);
		hpane.setDividerLocation(500);
		hpane.setDividerSize(10);
		
		this.add(hpane);
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
				System.out.println("ZAPISZ");
				
			}
		});
		
		 menu.addSeparator();
		 
		 JMenuItem exitMenuItem = new JMenuItem("Wyjście");
		 menu.add(exitMenuItem);
		 exitMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Wyjście");
				
			}
		});
		 
		 menu = new JMenu("Obsługa");
		 menuBar.add(menu);
		 
		 JMenuItem addCarMenuItem = new JMenuItem("Dodaj samochód");
		 menu.add(addCarMenuItem);
		 addCarMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Dodaj auto");
				
			}
		});
		 
		 JMenuItem deleteCarMenuItem = new JMenuItem("Usuń samochód");
		 menu.add(deleteCarMenuItem);
		 deleteCarMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Usuń");
				
			}
		});
		 
		 menu = new JMenu("Pomoc");
		 menuBar.add(menu);
		 
		 JMenuItem aboutMenuItem = new JMenuItem("O programie");
		 menu.add(aboutMenuItem);
		 aboutMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("O prograie");
				
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
				final AddCarDialog dialog = new AddCarDialog(carController);
				dialog.setVisible(true);
				carTableView.fireTableDataChanged();
				carTableView.fireTableStructureChanged();
				table.repaint();
				table.editingStopped(null);
				table.setModel(carTableView);
				jframe.requestFocus();
				
				jframe.repaint();
			}
		});
		 
		toolbar.addSeparator();
		
	    Container contentPane = this.getContentPane();
	    contentPane.add(toolbar, BorderLayout.NORTH);
	}
	
	private class RowListener implements ListSelectionListener {
	    public void valueChanged(ListSelectionEvent event) {
	        if (event.getValueIsAdjusting()) {
	            return;
	        }
	        carRefuelList.setCurrentElement(table.getSelectionModel().getLeadSelectionIndex());
	        list.setModel(carRefuelList);
	    }
	}
}


