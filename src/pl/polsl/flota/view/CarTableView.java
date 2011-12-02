package pl.polsl.flota.view;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import pl.polsl.flota.controller.CarController;
import pl.polsl.flota.exceptions.ElementNotFound;
import pl.polsl.flota.model.Car;

public class CarTableView extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	CarController carController;
	List<Car> listOfCars;
	public CarTableView(CarController carController) {
		this.carController = carController;
		listOfCars = carController.getRawList();
	}

	@Override
	public int getRowCount() {
		return listOfCars.size();
	}

	@Override
	public int getColumnCount() {
		//There are 6 columns in Car model except historyOfRefuel
		return 5;
	}
	
	  public String getColumnName(int column) {
			switch (column){
			case 0: return "Numer rej. ";
			case 1: return "Nazwa ";
			case 2: return "Przebieg ";
			case 3: return "Id kierowcy ";
			case 4: return "Średnie spalanie ";
			}
			return null;
		  }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Car currentCar = listOfCars.get(rowIndex);
		
		Object returnVal = null;
		switch (columnIndex){
		case 0: returnVal = currentCar.getRegNumber(); break;
		case 1: returnVal = currentCar.getName(); break;
		case 2: returnVal = currentCar.getDistance(); break;
		case 3: returnVal = currentCar.getAcctualDriverId(); break;
		case 4: returnVal = currentCar.getAvgConsumpion(); break;
		}
		return returnVal;
	}
	
	public Class getColumnClass(int c) {
       return getValueAt(0, c).getClass();
    }
	
    public boolean isCellEditable(int row, int col) {
        if (col < 1 || col == 3 ) {
            return false;
        } else {
            return true;
        }
    }

    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
        Car tempCarObject = listOfCars.get(row);
        String regNumber = tempCarObject.getRegNumber();
        String carName = "-";
        Integer userDistance = tempCarObject.getDistance();
        Float consumpition = tempCarObject.getAvgConsumpion();
        
		switch (col){
		case 0: break; //Change of reg number is not permitted to edit.
		case 1: carName = (String) value; break;
		case 2: userDistance = (Integer) value; break;
		case 3: break; //Change of driver id is not permitted.
		case 4: consumpition = (Float) value; break;
		}
        
        
		try {
			carController.editCar(regNumber, carName, userDistance.toString(), consumpition.toString());
		} catch (ElementNotFound e) {
			 	String title = "Internal Error";
			    Object text = "Błąd podczas edycji samochodu." + e.toString();
			    String[] buttons = { "OK"};
			    JOptionPane.showOptionDialog(null, text, title, JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, buttons, null);
			e.printStackTrace();
		}
    }


}
