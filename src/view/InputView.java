package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * @author Fadi M. H. Asbih
 * @email fadi_asbih@yahoo.de
 * @copyright 2013
 * 
 * TERMS AND CONDITIONS:
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
public class InputView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputView frame = new InputView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InputView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JButton btnBugIssue = new JButton("Bug / issue report");
		btnBugIssue.setBounds(226, 0, 157, 41);
		panel.setLayout(null);
		panel.add(btnBugIssue);
		
		JButton btnNewButton = new JButton();
		btnNewButton.setIcon(new ImageIcon("/Users/fadi/workspace/IUI-trunk/img/config.png"));
		btnNewButton.setBounds(7, 12, 38, 29);
		btnNewButton.setPreferredSize(new Dimension(25, 25));
		btnNewButton.setEnabled(true);
		btnNewButton.setBorder(new MatteBorder(null));
		btnNewButton.setToolTipText("Show Config Dialog");
		panel.add(btnNewButton);
		
		JButton button = new JButton("Bug / issue report");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(226, 43, 157, 41);
		panel.add(button);
		
		JButton button_1 = new JButton("Bug / issue report");
		button_1.setBounds(57, 43, 157, 41);
		panel.add(button_1);
		
		JButton button_2 = new JButton("Bug / issue report");
		button_2.setBounds(57, 0, 157, 41);
		panel.add(button_2);
		
		JButton button_3 = new JButton();
		button_3.setToolTipText("Show Config Dialog");
		button_3.setPreferredSize(new Dimension(25, 25));
		button_3.setEnabled(true);
		button_3.setBorder(new MatteBorder(null));
		button_3.setBounds(7, 49, 38, 29);
		panel.add(button_3);
		
		JLabel lblVer = new JLabel("ver");
		lblVer.setBounds(6, 53, 61, 16);
		panel.add(lblVer);
		setSize(400, 150);
		textField = new JTextField();
		contentPane.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
	}
}
