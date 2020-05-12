/* Frame */

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

class MyFrame extends JFrame {

	public final int WIDTH = 400;
	public final int HEIGHT = 400;
	private JTextField input; // �л� �� �Է¹޴� �ؽ�Ʈ �ʵ�
	private String filePath;
	private JTextField tf1;
	private int item1_check1 = 1; // default
	private int item1_check2 = 0;
	private int item2_check1 = 1; // default
	private int item2_check2 = 0;
	private JTextField tf2;

	private Image background;
	
	public MyFrame() {
		setTitle("Sorting Program");
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Frame ���ȭ�� �̹��� ���
		String path_t = Main.class.getResource("").getPath() + "images/background.png";
		
		// �̹��� ������ ���
		background 
		= new ImageIcon(path_t).getImage();
		
		// �г��� ��� �̹����� ����
		JPanel pCenter = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(background, 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		// null ��ġ ������
		pCenter.setLayout(null);

		// �л� �� �Է¹޴� �ؽ�Ʈ �ʵ�
		input = new JTextField(3);
		input.setBounds(185, 10, 30, 25);
		input.setToolTipText("1000 �̸��� �л� ���� �Է� �� Make csv�� ��������");
		input.setText("40"); // default

		// csv �Է� ���� ����, ���� ���� ��ư
		JButton btn1 = new JButton("Make csv");
		JButton btn2 = new JButton("Choose a File");

		btn1.setFocusPainted(false);
		btn1.setToolTipText("������ �л� ������ ��� csv������ �����մϴ�");
		btn2.setToolTipText("������ ������ �����ϼ���");
		btn1.setBounds(140, 50, 120, 30);
		btn2.setBounds(140, 100, 120, 30);

		// Make csv ��ư �̺�Ʈ ó��
		// Choose a File ��ư �̺�Ʈ ó��
		btn1.addActionListener(new MakeCsv());
		btn2.addActionListener(new ChooseFile());

		// ���� ���� ��� ��� �ؽ�Ʈ �ʵ�
		tf1 = new JTextField();
		tf1.setBackground(Color.darkGray);
		tf1.setBounds(60, 140, 280, 25);
		tf1.setText("result...");
		tf1.setEnabled(false);
		tf1.setToolTipText("������ ������ ǥ�õ˴ϴ�");

		// �ɼ� ���� ��, üũ �ڽ�
		JLabel la1 = new JLabel("���� ����");
		JLabel la2 = new JLabel("���� ���");
		la1.setForeground(Color.WHITE);
		la2.setForeground(Color.WHITE);
		
		CheckboxGroup group1 = new CheckboxGroup();
		CheckboxGroup group2 = new CheckboxGroup();

		Checkbox rcb1 = new Checkbox("�й�", true, group1);
		Checkbox rcb2 = new Checkbox("�г�", false, group1);
		Checkbox rcb3 = new Checkbox("��������", true, group2);
		Checkbox rcb4 = new Checkbox("��������", false, group2);

		rcb1.setBackground(Color.darkGray);
		rcb1.setForeground(Color.WHITE);
		rcb2.setBackground(Color.darkGray);
		rcb2.setForeground(Color.WHITE);
		
		rcb3.setBackground(Color.darkGray);
		rcb3.setForeground(Color.WHITE);
		rcb4.setBackground(Color.darkGray);
		rcb4.setForeground(Color.WHITE);
		
		
		// Checkbox ���� �� �� ����
		rcb1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				item1_check1 = 1;
				item1_check2 = 0;
			}
		});
		rcb2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				item1_check2 = 1;
				item1_check1 = 0;
			}
		});
		rcb3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				item2_check1 = 1;
				item2_check2 = 0;
			}
		});
		rcb4.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				item2_check2 = 1;
				item2_check1 = 0;
			}
		});

		final int X1 = 120;
		final int Y1 = 180;
		final int X2 = 220;
		final int Y2 = 180;

		la1.setBounds(X1, Y1, 60, 15);
		la2.setBounds(X2, Y2, 60, 15);
		rcb1.setBounds(X1, Y1 + 20, 60, 15);
		rcb2.setBounds(X1, Y1 + 40, 60, 15);
		rcb3.setBounds(X2, Y2 + 20, 65, 15);
		rcb4.setBounds(X2, Y2 + 40, 65, 15);

		rcb2.setCheckboxGroup(group1);
		rcb4.setCheckboxGroup(group2);

		// ���� ��ư
		JButton btn3 = new JButton("Sorting");
		btn3.addActionListener(new Sorting());
		btn3.setBounds(90, 260, 105, 25);
		btn3.setToolTipText("����");
		
		// ���� ���� ��ư
		JButton btn4 = new JButton("Open Folder");
		btn4.addActionListener(new OpenFolder());
		btn4.setBounds(205, 260, 105, 25);
		btn4.setToolTipText("�⺻ ��� ���� ����");
		
		
		// ��� ��� �ؽ�Ʈ �ʵ�
		tf2 = new JTextField();
		tf2.setBounds(90, 300, 220, 25);
		tf2.setText("result...");
		tf2.setEnabled(false);
		tf2.setToolTipText("���ĵ� ���� ���� ����� ��µ˴ϴ�");
		tf2.setBackground(Color.darkGray);
		
		// ���� �޼���
		JLabel warning = new JLabel();
		warning.setText("�� �Է� ������ �̸��� �ݵ�� stuInfo.csv �̿��� ��");
		warning.setBounds(10, 345, 400, 20);
		warning.setForeground(Color.WHITE);
		
		pCenter.add(input); // �л� �� �Է� TextField
		pCenter.add(btn1);  // Make csv
		pCenter.add(btn2);  // Choose a File
		pCenter.add(tf1);   // ���� ���� ǥ�� TextField
		pCenter.add(la1);   // ���� ���� label
		pCenter.add(la2);   // ���� ��� label
		pCenter.add(rcb1);  // �й� Checkbox
		pCenter.add(rcb2);  // �г� Checkbox
		pCenter.add(rcb3);  // �������� Checkbox
		pCenter.add(rcb4);  // �������� Checkbox
		pCenter.add(btn3);  // Sorting
		pCenter.add(btn4);  // Open Folder
		pCenter.add(tf2);   // ���� ��� ǥ�� TextField
		pCenter.add(warning);  // ���� �޼��� label

		this.add(pCenter);
		setVisible(true);
	} // MyFrame()
	
	// Make csv ��ư �ڵ鷯 (btn1)
	class MakeCsv implements ActionListener {
		private int size;
		private CheckNum cn = new CheckNum();

		@Override
		public void actionPerformed(ActionEvent e) {
			int result = 1;
			
			// TextField�� ���� �л� �� �� ��������
			if(!cn.isNumber(input.getText())) { // ���� �Է� ��
				JOptionPane.showMessageDialog(null, "���ڸ� �Է��ϼ���", "Warning", JOptionPane.WARNING_MESSAGE);
			} // 1000 �̻��� �� �Է� ��
			else if ((size = Integer.parseInt(input.getText())) >= 1000) { 
				JOptionPane.showMessageDialog(null, "�л� ���� 1000 �̸����� �����ϼ���", "Warning", JOptionPane.WARNING_MESSAGE);
			} 
			else { // ���� ó��
				MakeCsvFile input_csv = new MakeCsvFile(); // csv ���� ���� ��ü
				try {
					input_csv.makeCSV(size);
				} catch (IOException exception) {
					exception.printStackTrace();
					result = -1;
				} finally {
					if (result == 1) {
						JOptionPane.showMessageDialog(null, "CSV File has been successfully created!");
					} else {
						JOptionPane.showMessageDialog(null, "Fail to write a file..", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				} // finally
			} // else
		} // actionPerformed()
		
		// ���� �����̸� true ��ȯ
		class CheckNum {
		    public boolean isNumber(String value) {
		        try {
		            Double.parseDouble(value);
		        }
		        catch(NumberFormatException e) {
		            return false;
		        }
		        return true;
		    }
		}
	} // MakeCsv class

	// Choose a file ��ư �ڵ鷯 (btn2)
	class ChooseFile implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// �Է� ���� ������ ��η� �⺻ ��� ����
			JFileChooser chooser = new JFileChooser(Main.class.getResource("").getPath());
			chooser.setDialogTitle("���� ����");

			// .csv ���� ���� �߰�
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".csv", "csv");
			chooser.setFileFilter(filter);

			int returnVal = chooser.showOpenDialog(null);

			// ���� ����
			if (returnVal == JFileChooser.APPROVE_OPTION) { // ���� ��ư
				// �ؽ�Ʈ �ʵ� ����
				filePath = chooser.getSelectedFile().toString();
				tf1.setText(filePath);
			}
		}
	}

	// Sorting ��ư �ڵ鷯 (btn3)
	class Sorting implements ActionListener {

		private Token tok;
		private MakeCsvFile output = new MakeCsvFile(); // csv_sorted
		private Vector<Student> vec = new Vector<>();
		private Student[] sortedArray = null;

		@Override
		public void actionPerformed(ActionEvent e) {
			int result = 1;
			String fileName = tf1.getText();
			
			if (fileName.equals("result...")) {
				JOptionPane.showMessageDialog(null, "������ ������ ���� �����ϼ���", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				// csv ������ ���� �� row�� Student�� ���Ϳ� �����Ͽ� ��ȯ
				try {
					tok = new Token();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
				vec = tok.getStuVector();

				if (item1_check1 == 1) { // �й� ����
					if (item2_check1 == 1) { // ���� ����
												// type(���� ����): 1(�й�), 2(�г�)
						sortedArray = Sort.ascSort(vec, 1);
						try {
							// csv_sorted file ����
							if (output.makeCSV(sortedArray, tf1.getText()) == -2) {
								JOptionPane.showMessageDialog(null, "stuInfo_sorted.csv ������ ���� �� �����ϼ���.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								result = -1;
							}
						} catch (IOException exception) {
							exception.printStackTrace();
							result = -1;
						} finally {
							if (result == 1) {
								JOptionPane.showMessageDialog(null, "CSV_sorted File has been successfully created!");
								tf2.setText("���ĵ� ������ �����Ǿ����ϴ�.");
							} else {
								JOptionPane.showMessageDialog(null, "Fail to write a file..", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								tf2.setText("���� ���� ����...");
							}
						}
					} // if
					else { // �й� ���� ���� ����
						sortedArray = Sort.dscSort(vec, 1);
						try {
							// csv_sorted file ����
							if (output.makeCSV(sortedArray, tf1.getText()) == -2) {
								JOptionPane.showMessageDialog(null, "stuInfo_sorted.csv ������ ���� �� �����ϼ���.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								result = -1;
							}
						} catch (IOException exception) {
							exception.printStackTrace();
							result = -1;
						} finally {
							if (result == 1) {
								JOptionPane.showMessageDialog(null, "CSV_sorted File has been successfully created!");
								tf2.setText("���ĵ� ������ �����Ǿ����ϴ�.");
							} else {
								JOptionPane.showMessageDialog(null, "Fail to write a file..", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								tf2.setText("���� ���� ����...");
							}
						}
					} // else
				} // if
				else { // �г� ����
					if (item2_check1 == 1) { // ���� ����
						sortedArray = Sort.ascSort(vec, 2);
						try {
							// csv_sorted file ����
							if (output.makeCSV(sortedArray, tf1.getText()) == -2) {
								JOptionPane.showMessageDialog(null, "stuInfo_sorted.csv ������ ���� �� �����ϼ���.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								result = -1;
							}
						} catch (IOException exception) {
							exception.printStackTrace();
							result = -1;
						} finally {
							if (result == 1) {
								JOptionPane.showMessageDialog(null, "CSV_sorted File has been successfully created!");
								tf2.setText("���ĵ� ������ �����Ǿ����ϴ�.");
							} else {
								JOptionPane.showMessageDialog(null, "Fail to write a file..", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								tf2.setText("���� ���� ����...");
							}
						}
					} else { // ���� ����
						sortedArray = Sort.dscSort(vec, 2);
						try {
							// csv_sorted file ����
							if (output.makeCSV(sortedArray, tf1.getText()) == -2) {
								JOptionPane.showMessageDialog(null, "stuInfo_sorted.csv ������ ���� �� �����ϼ���.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								result = -1;
							}
						} catch (IOException exception) {
							exception.printStackTrace();
							result = -1;
						} finally {
							if (result == 1) {
								JOptionPane.showMessageDialog(null, "CSV_sorted File has been successfully created!!");
								tf2.setText("���ĵ� ������ �����Ǿ����ϴ�.");
							} else {
								JOptionPane.showMessageDialog(null, "Fail to write a file..", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								tf2.setText("���� ���� ����...");
							}
						}
					}
				}
			} // actionPerformed()
		}
	} // Sorting class

	// Oepn Folder ��ư  �ڵ鷯 (btn4)
	class OpenFolder implements ActionListener {
		
		private String path = Main.class.getResource("").getPath();
		private File folder;
		private int result = 1;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			folder = new File(path);
			try {
				openFolder(folder);
			} catch(IOException exception) {
				exception.printStackTrace();
				result = -1;
			} finally {
				if(result == -1)
					JOptionPane.showMessageDialog(null, "�� �� ���� ����..", "ERROR",
										JOptionPane.ERROR_MESSAGE);
			}
		} // actionPerformed()
			
		void openFolder(File folder) throws IOException {
			if(Desktop.isDesktopSupported()) 
				Desktop.getDesktop().open(folder);
		}
	} // OpenFolder class
	
} // MyFrame class
