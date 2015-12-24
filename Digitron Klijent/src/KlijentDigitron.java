import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.ScrollPaneConstants;


public class KlijentDigitron extends JFrame {

	private JPanel contentPane;
	private JLabel lblIzaberiteOperaciju;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTextArea textAreaStatus;
	private JButton btnSabiranje;
	private JButton btnOduzimanje;
	private JButton btnMnozenje;
	private JButton btnDijeljenje;
	private JLabel lblUnesiteBrojeve;
	private JButton btnNewButton;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
	private JButton button_8;
	private JTextField textFieldBrojevi;
	private JLabel lblRezultat;
	private JLabel labelRezultat;
	private JButton button_9;
	private JButton button_10;
	private String textAreaTekst = "";
	private Socket soketZaKontrolu;
	private Socket soketZaPodatke;
	
	private PrintStream kaServeruKontrola;
	private BufferedReader odServeraKontrola;
	private PrintStream kaServeruPodaci;
	private BufferedReader odServeraPodaci;
	
	private String odgovorKontrola;
	private String podaciZaServer;
	private String odgovorOdServera;
	private JButton button_11;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KlijentDigitron frame = new KlijentDigitron();
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
	public KlijentDigitron() {
		setDefaultCloseOperation(izlaz());
		setBounds(100, 100, 523, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblIzaberiteOperaciju());
		contentPane.add(getLblNewLabel());
		contentPane.add(getBtnSabiranje());
		contentPane.add(getPanel());
		contentPane.add(getBtnOduzimanje());
		contentPane.add(getBtnMnozenje());
		contentPane.add(getBtnDijeljenje());
		contentPane.add(getLblUnesiteBrojeve());
		contentPane.add(getBtnNewButton());
		contentPane.add(getButton());
		contentPane.add(getButton_1());
		contentPane.add(getButton_2());
		contentPane.add(getButton_3());
		contentPane.add(getButton_4());
		contentPane.add(getButton_5());
		contentPane.add(getButton_6());
		contentPane.add(getButton_7());
		contentPane.add(getButton_8());
		contentPane.add(getTextFieldBrojevi());
		contentPane.add(getLblRezultat());
		contentPane.add(getLabelRezultat());
		contentPane.add(getButton_9());
		contentPane.add(getButton_10());
		
		button_9.setEnabled(false);
		contentPane.add(getButton_11());
		
		otvoriVezuKontrole();
	}
	private int izlaz() {
		if(soketZaKontrolu != null && soketZaPodatke != null ) {
			zatvoriVezuKontrole();
			zatvoriVezuPodataka();
			
			
		}
		
		return JFrame.EXIT_ON_CLOSE;
	}
	private JLabel getLblIzaberiteOperaciju() {
		if (lblIzaberiteOperaciju == null) {
			lblIzaberiteOperaciju = new JLabel("Izaberite operaciju:");
			lblIzaberiteOperaciju.setBounds(10, 11, 99, 31);
		}
		return lblIzaberiteOperaciju;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("STATUS");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(349, 15, 148, 22);
		}
		return lblNewLabel;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(359, 49, 138, 238);
			panel.setLayout(null);
			panel.add(getTextAreaStatus());
			panel.add(getScrollPane());
		}
		return panel;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPane.setBounds(0, 235, 138, -471);
		}
		return scrollPane;
	}
	private JTextArea getTextAreaStatus() {
		if (textAreaStatus == null) {
			textAreaStatus = new JTextArea();
			textAreaStatus.setEditable(false);
			textAreaStatus.setBounds(0, 0, 138, 255);
		}
		return textAreaStatus;
	}
	private JButton getBtnSabiranje() {
		if (btnSabiranje == null) {
			btnSabiranje = new JButton("Sabiranje");
			btnSabiranje.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textAreaStatus.append("Zahtjev poslat.. \n");
					otvoriVezuPodataka();
					
					kaServeruKontrola.println("Sabiranje");
					
					btnSabiranje.setBackground(Color.RED);
					btnOduzimanje.setBackground(null);
					btnMnozenje.setBackground(null);
					btnDijeljenje.setBackground(null);
					
					try {
						odgovorKontrola = odServeraKontrola.readLine();
						if(odgovorKontrola.equals("Odobren")) {
							textAreaStatus.append("Zahtjev odobren! \n");
						}
						
						
					} catch (IOException e1) {
						
					}
				}
			});
			btnSabiranje.setBounds(108, 15, 89, 23);
		}
		return btnSabiranje;
	}
	private JButton getBtnOduzimanje() {
		if (btnOduzimanje == null) {
			btnOduzimanje = new JButton("Oduzimanje");
			btnOduzimanje.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textAreaStatus.append("Zahtjev poslat.. \n");
					otvoriVezuPodataka();
					kaServeruKontrola.println("Oduzimanje");
					
					btnSabiranje.setBackground(null);
					btnOduzimanje.setBackground(Color.RED);
					btnMnozenje.setBackground(null);
					btnDijeljenje.setBackground(null);
					
					try {
						odgovorKontrola = odServeraKontrola.readLine();
						if(odgovorKontrola.equals("Odobren")) {
							textAreaStatus.append("Zahtjev odobren! \n");
						}
						
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnOduzimanje.setBounds(207, 15, 89, 23);
		}
		return btnOduzimanje;
	}
	private JButton getBtnMnozenje() {
		if (btnMnozenje == null) {
			btnMnozenje = new JButton("Mnozenje");
			btnMnozenje.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textAreaStatus.append("Zahtjev poslat.. \n");
					otvoriVezuPodataka();
					kaServeruKontrola.println("Mnozenje");
					
					btnSabiranje.setBackground(null);
					btnOduzimanje.setBackground(null);
					btnMnozenje.setBackground(Color.RED);
					btnDijeljenje.setBackground(null);
					
					try {
						odgovorKontrola = odServeraKontrola.readLine();
						if(odgovorKontrola.equals("Odobren")) {
							textAreaStatus.append("Zahtjev odobren! \n");
						}
						
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnMnozenje.setBounds(108, 49, 89, 23);
		}
		return btnMnozenje;
	}
	private JButton getBtnDijeljenje() {
		if (btnDijeljenje == null) {
			btnDijeljenje = new JButton("Dijeljenje");
			btnDijeljenje.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textAreaStatus.append("Zahtjev poslat.. \n");
					
					otvoriVezuPodataka();
					
					kaServeruKontrola.println("Dijeljenje");
					
					btnSabiranje.setBackground(null);
					btnOduzimanje.setBackground(null);
					btnMnozenje.setBackground(null);
					btnDijeljenje.setBackground(Color.RED);
					
					try {
						odgovorKontrola = odServeraKontrola.readLine();
						if(odgovorKontrola.equals("Odobren")) {
							textAreaStatus.append("Zahtjev odobren! \n");
						}
						
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnDijeljenje.setBounds(207, 49, 89, 23);
		}
		return btnDijeljenje;
	}
	private JLabel getLblUnesiteBrojeve() {
		if (lblUnesiteBrojeve == null) {
			lblUnesiteBrojeve = new JLabel("Unesite brojeve:");
			lblUnesiteBrojeve.setBounds(10, 75, 99, 14);
		}
		return lblUnesiteBrojeve;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("7");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldBrojevi.setText(textFieldBrojevi.getText() + "7");
					button_9.setEnabled(true);
					button_11.setEnabled(true);
				}
			});
			btnNewButton.setBounds(10, 100, 45, 31);
		}
		return btnNewButton;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("8");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldBrojevi.setText(textFieldBrojevi.getText() + "8");
					button_9.setEnabled(true);
					button_11.setEnabled(true);
				}
			});
			button.setBounds(64, 100, 45, 31);
		}
		return button;
	}
	private JButton getButton_1() {
		if (button_1 == null) {
			button_1 = new JButton("4");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldBrojevi.setText(textFieldBrojevi.getText() + "4");
					button_9.setEnabled(true);
					button_11.setEnabled(true);
				}
			});
			button_1.setBounds(10, 142, 45, 31);
		}
		return button_1;
	}
	private JButton getButton_2() {
		if (button_2 == null) {
			button_2 = new JButton("1");
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldBrojevi.setText(textFieldBrojevi.getText() + "1");
					button_9.setEnabled(true);
					button_11.setEnabled(true);
				}
			});
			button_2.setBounds(10, 184, 45, 31);
		}
		return button_2;
	}
	private JButton getButton_3() {
		if (button_3 == null) {
			button_3 = new JButton("5");
			button_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldBrojevi.setText(textFieldBrojevi.getText() + "5");
					button_9.setEnabled(true);
					button_11.setEnabled(true);
				}
			});
			button_3.setBounds(64, 142, 45, 31);
		}
		return button_3;
	}
	private JButton getButton_4() {
		if (button_4 == null) {
			button_4 = new JButton("2");
			button_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldBrojevi.setText(textFieldBrojevi.getText() + "2");
					button_9.setEnabled(true);
					button_11.setEnabled(true);
				}
			});
			button_4.setBounds(64, 184, 45, 31);
		}
		return button_4;
	}
	private JButton getButton_5() {
		if (button_5 == null) {
			button_5 = new JButton("6");
			button_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldBrojevi.setText(textFieldBrojevi.getText() + "6");
					button_9.setEnabled(true);
					button_11.setEnabled(true);
				}
			});
			button_5.setBounds(119, 142, 45, 31);
		}
		return button_5;
	}
	private JButton getButton_6() {
		if (button_6 == null) {
			button_6 = new JButton("9");
			button_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldBrojevi.setText(textFieldBrojevi.getText() + "9");
					button_9.setEnabled(true);
					button_11.setEnabled(true);
				}
			});
			button_6.setBounds(119, 100, 45, 31);
		}
		return button_6;
	}
	private JButton getButton_7() {
		if (button_7 == null) {
			button_7 = new JButton("3");
			button_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldBrojevi.setText(textFieldBrojevi.getText() + "3");
					button_9.setEnabled(true);
					button_11.setEnabled(true);
				}
			});
			button_7.setBounds(119, 184, 45, 31);
		}
		return button_7;
	}
	private JButton getButton_8() {
		if (button_8 == null) {
			button_8 = new JButton("0");
			button_8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldBrojevi.setText(textFieldBrojevi.getText() + "0");
					button_9.setEnabled(true);
					button_11.setEnabled(true);
				}
			});
			button_8.setBounds(10, 226, 45, 31);
		}
		return button_8;
	}
	private JTextField getTextFieldBrojevi() {
		if (textFieldBrojevi == null) {
			textFieldBrojevi = new JTextField();
			textFieldBrojevi.setEditable(false);
			textFieldBrojevi.setBounds(10, 265, 339, 22);
			textFieldBrojevi.setColumns(10);
		}
		return textFieldBrojevi;
	}
	private JLabel getLblRezultat() {
		if (lblRezultat == null) {
			lblRezultat = new JLabel("REZULTAT:");
			lblRezultat.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblRezultat.setBounds(175, 234, 89, 14);
		}
		return lblRezultat;
	}
	private JLabel getLabelRezultat() {
		if (labelRezultat == null) {
			labelRezultat = new JLabel("0");
			labelRezultat.setFont(new Font("Tahoma", Font.PLAIN, 15));
			labelRezultat.setBounds(261, 234, 88, 14);
		}
		return labelRezultat;
	}
	private JButton getButton_9() {
		if (button_9 == null) {
			button_9 = new JButton("_");
			button_9.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldBrojevi.setText(textFieldBrojevi.getText() + " ");
					button_9.setEnabled(false);
					button_11.setEnabled(false);
				}
			});
			button_9.setBounds(64, 226, 45, 31);
		}
		return button_9;
	}
	private JButton getButton_10() {
		if (button_10 == null) {
			button_10 = new JButton("=");
			button_10.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						button_9.setEnabled(false);
						button_11.setEnabled(false);
						if(odgovorKontrola.equals("Odobren")) {
							podaciZaServer = textFieldBrojevi.getText();
							kaServeruPodaci.println(podaciZaServer);
							textFieldBrojevi.setText("");
								odgovorOdServera = odServeraPodaci.readLine();
								labelRezultat.setText(odgovorOdServera);
								zatvoriVezuPodataka();
								resetujDugmice();
							
						} else {
							textAreaStatus.append("ODABERITE OPERACIJU");
						}
					} catch (IOException e1) {
						textAreaStatus.append("Molimo odaberite operaciju! \n");
					} catch (NullPointerException n) {
						textAreaStatus.append("Molimo odaberite operaciju! \n");
						textFieldBrojevi.setText("");
					}
				}
			});
			button_10.setBounds(119, 226, 45, 31);
		}
		return button_10;
	}
	
	private void resetujDugmice() {
		btnSabiranje.setBackground(null);
		btnOduzimanje.setBackground(null);
		btnMnozenje.setBackground(null);
		btnDijeljenje.setBackground(null);
	}
	
	private void otvoriVezuKontrole() {
		try {
			soketZaKontrolu = new Socket("localhost", 1908);
			kaServeruKontrola = new PrintStream(soketZaKontrolu.getOutputStream());
			odServeraKontrola = new BufferedReader(new InputStreamReader(soketZaKontrolu.getInputStream()));
		} catch (IOException e) {
			textAreaStatus.append("Server ne radi! \n");
		}
	}
	private void otvoriVezuPodataka() {
		try {
			soketZaPodatke = new Socket("localhost", 185);
			kaServeruPodaci = new PrintStream(soketZaPodatke.getOutputStream());
			odServeraPodaci = new BufferedReader(new InputStreamReader(soketZaPodatke.getInputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void zatvoriVezuPodataka() {
		kaServeruPodaci.close();
		try {
			odServeraPodaci.close();
			soketZaPodatke.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void zatvoriVezuKontrole() {
		kaServeruKontrola.close();
		try {
			odServeraKontrola.close();
			soketZaKontrolu.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private JButton getButton_11() {
		if (button_11 == null) {
			button_11 = new JButton(".");
			button_11.setEnabled(false);
			button_11.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textFieldBrojevi.setText(textFieldBrojevi.getText() + ".");
					button_9.setEnabled(false);
				}
			});
			button_11.setBounds(174, 184, 45, 31);
		}
		return button_11;
	}
}
