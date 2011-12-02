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

public class AddCarDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JLabel regNumber = new JLabel("Numer rejestarcyjny : ");
	JLabel name = new JLabel("Nazwa [Marka model] :");
	JLabel distance = new JLabel("Przebieg początkowy :");
	JLabel avgConsumpiton = new JLabel("Średnie spalanie :");
	JTextField regNumberField = new JTextField();
	JTextField nameField = new JTextField();
	JTextField distanceField = new JTextField();
	JTextField avgConsumpitonField = new JTextField();
	JDialog d;
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

	public AddCarDialog(final CarController carController) {
		super();
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
					String[] button = {"OK"};
					JOptionPane.showOptionDialog(null,
							"Wystąpił błąd podczas dodawania pojazdu", "Bład",
							JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE,
							 null, button , null);
				}
				d.dispose();
			}
		});
	}

	public String[] getData() {
		carData[0] = regNumberField.getText();
		carData[1] = nameField.getText();
		carData[2] = distanceField.getText();
		carData[3] = avgConsumpitonField.getText();
		return carData;
	}

}
