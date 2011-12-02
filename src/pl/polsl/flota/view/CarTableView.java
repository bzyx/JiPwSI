package pl.polsl.flota.view;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import pl.polsl.flota.controller.CarController;
import pl.polsl.flota.exceptions.ElementNotFound;
import pl.polsl.flota.model.Car;

/**
 * The Class CarTableView a model for JTable
 */
public class CarTableView extends AbstractTableModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The car controller. */
	CarController carController;
	
	/** The list of cars. */
	List<Car> listOfCars;
	
	/**
	 * Instantiates a new car table model.
	 *
	 * @param carController the car controller
	 */
	public CarTableView(CarController carController) {
		this.carController = carController;
		listOfCars = carController.getRawList();
	}

	/**
	 * Reruns a list of rows in table.
	 */
	@Override
	public int getRowCount() {
		return listOfCars.size();
	}

	/**
	 * Reruns a list of columns in table.
	 */
	@Override
	public int getColumnCount() {
		//There are 5 columns in Car model except historyOfRefuel
		return 5;
	}
	
	/**
	 * Reruns a column name for a header in a JTable.
	 */
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

	/**
	 * Reruns a value from a model for a given row and column.
	 */
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
	
	/**
	 * Reruns a type of a data in a column.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int c) {
       return getValueAt(0, c).getClass();
    }
	
	/**
	 * Sets is a cell can be edited or not.
	 * @param row row
	 * @parm col col
	 */
    public boolean isCellEditable(int row, int col) {
        if (col < 1 || col == 3 ) {
            return false;
        } else {
            return true;
        }
    }

	/**
	 * Sets a value for a given row an col.
	 * @param value a value to set.
	 * @param row the row of value
	 * @param col the col of the value
	 */
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
    
	/**
	 * Delete item from a row and model.
	 *
	 * @param selectedRow the selected row to remove an item.
	 */
	public void deleteItem(int selectedRow) {
		String carRegNumber = (String) this.getValueAt(selectedRow, 0);
		this.fireTableRowsDeleted(selectedRow, selectedRow);
		
		carController.deleteCar(carRegNumber);
		
	}


}
