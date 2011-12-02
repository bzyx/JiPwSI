package pl.polsl.flota.view;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import pl.polsl.flota.controller.CarController;
import pl.polsl.flota.exceptions.ElementAlredyExists;

/**
 * The Class AddCarDialog.
 */
public class AddCarDialog extends JDialog {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The reg number. */
	JLabel regNumber = new JLabel("Numer rejestarcyjny : ");
	
	/** The name. */
	JLabel name = new JLabel("Nazwa [Marka model] :");
	
	/** The distance. */
	JLabel distance = new JLabel("Przebieg początkowy :");
	
	/** The avg consumpiton. */
	JLabel avgConsumpiton = new JLabel("Średnie spalanie :");
	
	/** The reg number field. */
	JTextField regNumberField = new JTextField();
	
	/** The name field. */
	JTextField nameField = new JTextField();
	
	/** The distance field. */
	JTextField distanceField = new JTextField();
	
	/** The avg consumpiton field. */
	JTextField avgConsumpitonField = new JTextField();
	
	/** The d. */
	JDialog d;
	
	/** The car data. */
	String[] carData = new String[4];

	public AddCarDialog(Frame owner, boolean modal) {
		super(owner, modal);
		d = this;
		this.setTitle("Dodaj samochód");
		this.setLayout(new GridLayout(5, 2));
		this.add(regNumber);
		this.add(regNumberField);
		this.add(name);
		this.add(nameField);
		this.add(distance);
		this.add(distanceField);
		this.add(avgConsumpiton);
		this.add(avgConsumpitonField);
		this.setLocation(300, 300);
		this.setSize(400, 200);
		JButton closeButton = new JButton("Dodaj");
		this.add(closeButton);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				d.setVisible(false);
				d.dispose();
			}
		});
	}

	/**
	 * Instantiates a new CarDialog.
	 * 
	 * A CarDialog is used to get data from user, and create a new Car.
	 *
	 * @param owner the owner
	 * @param modal the modal
	 */
	public AddCarDialog(Frame owner, boolean modal,
			final CarController carController) {
		super(owner, modal);
		d = this;
		this.setTitle("Dodaj samochód");
		this.setLayout(new GridLayout(5, 2));
		this.add(regNumber);
		this.add(regNumberField);
		this.add(name);
		this.add(nameField);
		this.add(distance);
		this.add(distanceField);
		this.add(avgConsumpiton);
		this.add(avgConsumpitonField);
		this.setLocation(300, 300);
		this.setSize(400, 200);
		JButton closeButton = new JButton("Dodaj");
		this.add(closeButton);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					carController.addCar(regNumberField.getText(),
							nameField.getText(),
							Integer.parseInt(distanceField.getText(), 10),
							Float.parseFloat(avgConsumpitonField.getText()));

				} catch (NumberFormatException | ElementAlredyExists e1) {
					String[] button = { "OK" };
					JOptionPane.showOptionDialog(null,
							"Wystąpił błąd podczas dodawania pojazdu", "Bład",
							JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE,
							null, button, null);
				}
				d.dispose();
			}
		});
	}

}
