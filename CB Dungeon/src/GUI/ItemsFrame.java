package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import characters.Characters;
import items.Items;

public class ItemsFrame {

	private Characters c;

	private Items item;

	private JFrame itemsFrame;
	private JList<String> itemList;

	private JButton btnUse;
	private JScrollPane scrollPane;

	public void startGui(Characters character) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemsFrame window = new ItemsFrame(character);
					window.itemsFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ItemsFrame(Characters c) {
		itemsFrame = new JFrame();
		itemsFrame.setResizable(false);
		itemsFrame.setBounds(0, 0, 250, 400);
		itemsFrame.setLocationRelativeTo(null);
		itemsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		itemsFrame.getContentPane().setLayout(null);

		btnUse = new JButton("Use");
		btnUse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUse.setBounds(10, 310, 100, 40);
		btnUse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (item.getType() == Items.TYPE.WEAPON || item.getType() == Items.TYPE.DEFENSE)
					c.setItemEquiped(item);

				else
					item.useItem(c);

				MainFrame.getEncounterPanel().updatePanel();
				updatelist();

				itemList.setModel(c.getItemNamesList());
			}
		});
		itemsFrame.getContentPane().add(btnUse);

		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setBounds(134, 310, 100, 40);
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				itemsFrame.dispose();

			}
		});
		itemsFrame.getContentPane().add(btnClose);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 224, 288);
		itemsFrame.getContentPane().add(scrollPane);

		itemList = new JList();
		scrollPane.setViewportView(itemList);
		itemList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		itemList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		itemList.setModel(c.getItemNamesList());
		itemList.setBorder(new LineBorder(new Color(0, 0, 0)));
		itemList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = itemList.getSelectedIndex();
				
				if (index >= 0) {
					Items i = (Items) c.getItemList().get(index);
					item = i;
				}
				updatelist();
			}
		});

	}

	public void updatelist() {

		if ((item.getType() == Items.TYPE.WEAPON || item.getType() == Items.TYPE.DEFENSE)
				&& item.getIsEquiped() == false)
			btnUse.setText("Equip");

		else if ((item.getType() == Items.TYPE.WEAPON || item.getType() == Items.TYPE.DEFENSE)
				&& item.getIsEquiped() == true)
			btnUse.setText("Unequip");

		else
			btnUse.setText("Use");
	}
}
