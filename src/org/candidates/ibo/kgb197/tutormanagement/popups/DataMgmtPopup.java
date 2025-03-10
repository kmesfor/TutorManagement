package org.candidates.ibo.kgb197.tutormanagement.popups;

import org.candidates.ibo.kgb197.tutormanagement.DataManager;
import org.candidates.ibo.kgb197.tutormanagement.ScreenManager;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.StyleType;
import org.candidates.ibo.kgb197.tutormanagement.StylingManager;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.GeneralData;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.GeneralDataType;
import org.candidates.ibo.kgb197.tutormanagement.datatypes.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DataMgmtPopup extends Screen {
	private final String addBtnText;
	private final String inputTextFieldText;
	private final String menuLabelText;
	private final ArrayList<GeneralData> dataList;
	private final GeneralDataType type;
	
	private final JPanel upperPanel = new JPanel();
	private final JPanel newDataPanel = new JPanel();
	private final JPanel dataPanel = new JPanel();
	private final JPanel lowerPanel = new JPanel();
	
	private final JButton backBtn = new JButton("Back");
	private final JButton saveBtn = new JButton("Save");
	
	private final JButton addBtn;
	private final JTextField textField;
	private final JLabel dataTypeLabel;
	
	/**
	 * A popup that allows a GeneralData object to be edited
	 * @param addBtnText the text of the add object button
	 * @param inputTextFieldText the text of the input text field
	 * @param menuLabelText the text of the menu label
	 * @param dataList the list of data to add to
	 * @param type the type of GeneralData object
	 */
	@SuppressWarnings("unchecked")
	public DataMgmtPopup(String addBtnText, String inputTextFieldText, String menuLabelText, ArrayList<GeneralData> dataList, GeneralDataType type) {
		this.addBtnText = addBtnText;
		this.inputTextFieldText = inputTextFieldText;
		this.menuLabelText = menuLabelText;
		// #clone() creates a shallow copy, this is okay for GeneralData which is just text
		// SuppressWarnings for an unchecked cast, GeneralData should never be deep
		this.dataList = (ArrayList<GeneralData>) dataList.clone();
		this.type = type;
		
		addBtn = new JButton(addBtnText);
		textField = new JTextField(inputTextFieldText);
		dataTypeLabel = new JLabel(menuLabelText);
	}
	
	/**
	 * Display the GeneralData management screen for a list of the same GeneralData
	 * @param panel the panel to display components on
	 * @return a JComponent containing the ScreenPopup's rendered components
	 */
	public JComponent show(JPanel panel) {
		//Use BorderLayout for the main panel
		panel.setLayout(new BorderLayout());
		
		//Use BorderLayout for the sub panel
		newDataPanel.setLayout(new BorderLayout());
		
		//Align and add the data type label
		dataTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		upperPanel.add(dataTypeLabel, BorderLayout.NORTH);
		
		//Add an input text field
		newDataPanel.add(textField, BorderLayout.CENTER);
		
		//Set up an add GeneralData button
		addBtn.addActionListener(new AddBtnPressed());
		newDataPanel.add(addBtn, BorderLayout.EAST);
		
		upperPanel.setLayout(new BorderLayout());
		upperPanel.add(newDataPanel, BorderLayout.SOUTH);
		
		//Ensures there are at least 10 rows (styling)
		dataPanel.setLayout(new GridLayout(Math.max(dataList.size(), 10), 1));
		//Add a remove button and name label for each GeneralData within the list
		for (GeneralData data : dataList) {
			//Set up remove button
			JButton componentBtn = new JButton("X");
			componentBtn.addActionListener(new RemoveBtnPressed(data));
			StylingManager.stylize(componentBtn, StyleType.SECONDARY);
			
			//Set up panel
			JPanel componentPanel = new JPanel(new BorderLayout());
			componentPanel.add(componentBtn, BorderLayout.WEST);
			
			//Set up label
			JLabel componentLabel = new JLabel(data.getInfo());
			componentPanel.add(componentLabel, BorderLayout.CENTER);
			StylingManager.stylize(componentLabel, StyleType.SECONDARY);
			dataPanel.add(componentPanel);
		}
		
		lowerPanel.setLayout(new BorderLayout());
		
		//Set up back button
		backBtn.addActionListener(new BackBtnPressed());
		lowerPanel.add(backBtn, BorderLayout.WEST);
		
		//Set up save button
		saveBtn.addActionListener(new SaveBtnPressed());
		lowerPanel.add(saveBtn, BorderLayout.EAST);
		
		//Add components to panel
		panel.add(upperPanel, BorderLayout.NORTH);
		panel.add(dataPanel, BorderLayout.CENTER);
		panel.add(lowerPanel, BorderLayout.SOUTH);
		
		//Stylize components
		StylingManager.stylize(dataTypeLabel, StyleType.SECONDARY);
		StylingManager.stylize(textField, StyleType.PRIMARY);
		StylingManager.stylize(addBtn, StyleType.PRIMARY);
		StylingManager.stylize(saveBtn, StyleType.PRIMARY);
		StylingManager.stylize(backBtn, StyleType.SECONDARY);
		StylingManager.stylize(panel, StyleType.PRIMARY);
		
		return panel;
	}
	
	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.closePopup();
		}
	}
	
	private class SaveBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (type == GeneralDataType.CLASS) {
				//Loop through class names
				DataManager.classNames.forEach(data -> {
					//If the dataList does not contain the DataManager list index,
					// it was removed, therefore safely destroy the GeneralData
					if (!DataManager.listContains(dataList, data)) {
						data.destroy();
					}
				});
				//Set the saved classNames to the updated dataList
				DataManager.classNames = dataList;
			} else if (type == GeneralDataType.SKILL) {
				//Loop through skills
				DataManager.skills.forEach(data -> {
					//If the dataList does not contain the DataManager list index,
					// it was removed, therefore safely destroy the GeneralData
					if (!DataManager.listContains(dataList, data)) {
						data.destroy();
					}
				});
				//Set the saved skills to the updated dataList
				DataManager.skills = dataList;
			} else if (type == GeneralDataType.SESSION) {
				//Loop through sessions
				DataManager.sessions.forEach(data -> {
					//If the dataList does not contain the DataManager list index,
					// it was removed, therefore safely destroy the GeneralData
					if (!DataManager.listContains(dataList, data)) {
						data.destroy();
					}
				});
				//Set the saved sessions to the updated dataList
				DataManager.sessions = dataList;
			} else if (type == GeneralDataType.PROFICIENCY) {
				//Loop through proficiencies
				DataManager.proficiencies.forEach(data -> {
					//If the dataList does not contain the DataManager list index,
					// it was removed, therefore safely destroy the GeneralData
					if (!DataManager.listContains(dataList, data)) {
						data.destroy();
					}
				});
				//Set the saved proficiencies to the updated dataList
				DataManager.proficiencies = dataList;
			} else {
				//Display error
				System.out.println("Invalid GeneralData tried to be removed");
			}
			//Close popup
			ScreenManager.closePopup();
		}
	}
	
	/**
	 * Remove an existing GeneralData object from the appropriate list
	 */
	private class RemoveBtnPressed implements ActionListener {
		// The GeneralData object to be removed
		private final GeneralData data;
		public RemoveBtnPressed(GeneralData data) {
			this.data = data;
		}
		public void actionPerformed(ActionEvent e) {
			//Remove the object from the appropriate list
			dataList.remove(data);
			//Refresh the displayed screen to update changes
			refresh();
		}
	}
	
	/**
	 * Adds a new GeneralData object to the appropriate list based on text input
	 */
	private class AddBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Add the new object
			dataList.add(new GeneralData(textField.getText(), type));
			//Refresh the displayed screen to update changes
			refresh();
		}
	}
	
	/**
	 * Refresh the displayed screen to update changes
	 */
	private void refresh() {
		ScreenManager.showPopup(new DataMgmtPopup(addBtnText, inputTextFieldText, menuLabelText, dataList, type));
	}
	
}
