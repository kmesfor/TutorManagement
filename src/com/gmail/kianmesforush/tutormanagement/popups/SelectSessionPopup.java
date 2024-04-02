package com.gmail.kianmesforush.tutormanagement.popups;

import com.gmail.kianmesforush.tutormanagement.ScreenManager;
import com.gmail.kianmesforush.tutormanagement.datatypes.GeneralData;
import com.gmail.kianmesforush.tutormanagement.datatypes.ScreenPopup;
import com.gmail.kianmesforush.tutormanagement.screens.authenticated.CreateAptScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectSessionPopup extends ScreenPopup {
	private final ArrayList<GeneralData> sessions;
	
	public SelectSessionPopup(ArrayList<GeneralData> sessions) {
		this.sessions = sessions;
	}
	
	public JComponent show(JPanel panel) {
		JPanel upperPanel = new JPanel(new GridLayout(0, 2));
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new BackBtnPressed());
		
		for (GeneralData session : sessions) {
			upperPanel.add(new JLabel(session.getInfo()));
			JButton button = new JButton("Select session");
			button.addActionListener(new SelectBtnPressed(session));
			upperPanel.add(button);
		}
		
		panel.setLayout(new BorderLayout());
		panel.add(upperPanel, BorderLayout.CENTER);
		panel.add(backBtn, BorderLayout.SOUTH);
		
		return panel;
	}
	
	private static class BackBtnPressed implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ScreenManager.closePopup();
		}
	}
	
	private static class SelectBtnPressed implements ActionListener {
		private final GeneralData session;
		public SelectBtnPressed(GeneralData session) {
			this.session = session;
		}
		
		public void actionPerformed(ActionEvent e) {
			((CreateAptScreen) ScreenManager.getCurrentScreen()).setSessionSelected(session);
		}
	}
}
