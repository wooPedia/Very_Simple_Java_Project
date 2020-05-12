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
	private JTextField input; // 학생 수 입력받는 텍스트 필드
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

		// Frame 배경화면 이미지 경로
		String path_t = Main.class.getResource("").getPath() + "images/background.png";
		
		// 이미지 변수에 담기
		background 
		= new ImageIcon(path_t).getImage();
		
		// 패널의 배경 이미지로 설정
		JPanel pCenter = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(background, 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		
		// null 배치 관리자
		pCenter.setLayout(null);

		// 학생 수 입력받는 텍스트 필드
		input = new JTextField(3);
		input.setBounds(185, 10, 30, 25);
		input.setToolTipText("1000 미만의 학생 수를 입력 후 Make csv를 누르세요");
		input.setText("40"); // default

		// csv 입력 파일 생성, 파일 선택 버튼
		JButton btn1 = new JButton("Make csv");
		JButton btn2 = new JButton("Choose a File");

		btn1.setFocusPainted(false);
		btn1.setToolTipText("임의의 학생 정보가 담긴 csv파일을 생성합니다");
		btn2.setToolTipText("정렬할 파일을 선택하세요");
		btn1.setBounds(140, 50, 120, 30);
		btn2.setBounds(140, 100, 120, 30);

		// Make csv 버튼 이벤트 처리
		// Choose a File 버튼 이벤트 처리
		btn1.addActionListener(new MakeCsv());
		btn2.addActionListener(new ChooseFile());

		// 파일 선택 결과 출력 텍스트 필드
		tf1 = new JTextField();
		tf1.setBackground(Color.darkGray);
		tf1.setBounds(60, 140, 280, 25);
		tf1.setText("result...");
		tf1.setEnabled(false);
		tf1.setToolTipText("선택한 파일이 표시됩니다");

		// 옵션 선택 라벨, 체크 박스
		JLabel la1 = new JLabel("정렬 기준");
		JLabel la2 = new JLabel("정렬 방법");
		la1.setForeground(Color.WHITE);
		la2.setForeground(Color.WHITE);
		
		CheckboxGroup group1 = new CheckboxGroup();
		CheckboxGroup group2 = new CheckboxGroup();

		Checkbox rcb1 = new Checkbox("학번", true, group1);
		Checkbox rcb2 = new Checkbox("학년", false, group1);
		Checkbox rcb3 = new Checkbox("오름차순", true, group2);
		Checkbox rcb4 = new Checkbox("내림차순", false, group2);

		rcb1.setBackground(Color.darkGray);
		rcb1.setForeground(Color.WHITE);
		rcb2.setBackground(Color.darkGray);
		rcb2.setForeground(Color.WHITE);
		
		rcb3.setBackground(Color.darkGray);
		rcb3.setForeground(Color.WHITE);
		rcb4.setBackground(Color.darkGray);
		rcb4.setForeground(Color.WHITE);
		
		
		// Checkbox 선택 시 값 변경
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

		// 정렬 버튼
		JButton btn3 = new JButton("Sorting");
		btn3.addActionListener(new Sorting());
		btn3.setBounds(90, 260, 105, 25);
		btn3.setToolTipText("정렬");
		
		// 폴더 열기 버튼
		JButton btn4 = new JButton("Open Folder");
		btn4.addActionListener(new OpenFolder());
		btn4.setBounds(205, 260, 105, 25);
		btn4.setToolTipText("기본 경로 폴더 열기");
		
		
		// 결과 출력 텍스트 필드
		tf2 = new JTextField();
		tf2.setBounds(90, 300, 220, 25);
		tf2.setText("result...");
		tf2.setEnabled(false);
		tf2.setToolTipText("정렬된 파일 생성 결과가 출력됩니다");
		tf2.setBackground(Color.darkGray);
		
		// 주의 메세지
		JLabel warning = new JLabel();
		warning.setText("※ 입력 파일의 이름은 반드시 stuInfo.csv 이여야 함");
		warning.setBounds(10, 345, 400, 20);
		warning.setForeground(Color.WHITE);
		
		pCenter.add(input); // 학생 수 입력 TextField
		pCenter.add(btn1);  // Make csv
		pCenter.add(btn2);  // Choose a File
		pCenter.add(tf1);   // 선택 파일 표시 TextField
		pCenter.add(la1);   // 정렬 기준 label
		pCenter.add(la2);   // 정렬 방법 label
		pCenter.add(rcb1);  // 학번 Checkbox
		pCenter.add(rcb2);  // 학년 Checkbox
		pCenter.add(rcb3);  // 오름차순 Checkbox
		pCenter.add(rcb4);  // 내림차순 Checkbox
		pCenter.add(btn3);  // Sorting
		pCenter.add(btn4);  // Open Folder
		pCenter.add(tf2);   // 정렬 결과 표시 TextField
		pCenter.add(warning);  // 주의 메세지 label

		this.add(pCenter);
		setVisible(true);
	} // MyFrame()
	
	// Make csv 버튼 핸들러 (btn1)
	class MakeCsv implements ActionListener {
		private int size;
		private CheckNum cn = new CheckNum();

		@Override
		public void actionPerformed(ActionEvent e) {
			int result = 1;
			
			// TextField로 부터 학생 수 값 가져오기
			if(!cn.isNumber(input.getText())) { // 문자 입력 시
				JOptionPane.showMessageDialog(null, "숫자를 입력하세요", "Warning", JOptionPane.WARNING_MESSAGE);
			} // 1000 이상의 수 입력 시
			else if ((size = Integer.parseInt(input.getText())) >= 1000) { 
				JOptionPane.showMessageDialog(null, "학생 수를 1000 미만으로 설정하세요", "Warning", JOptionPane.WARNING_MESSAGE);
			} 
			else { // 정상 처리
				MakeCsvFile input_csv = new MakeCsvFile(); // csv 파일 생성 객체
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
		
		// 값이 숫자이면 true 반환
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

	// Choose a file 버튼 핸들러 (btn2)
	class ChooseFile implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// 입력 파일 생성된 경로로 기본 경로 설정
			JFileChooser chooser = new JFileChooser(Main.class.getResource("").getPath());
			chooser.setDialogTitle("파일 선택");

			// .csv 파일 필터 추가
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".csv", "csv");
			chooser.setFileFilter(filter);

			int returnVal = chooser.showOpenDialog(null);

			// 파일 선택
			if (returnVal == JFileChooser.APPROVE_OPTION) { // 열기 버튼
				// 텍스트 필드 수정
				filePath = chooser.getSelectedFile().toString();
				tf1.setText(filePath);
			}
		}
	}

	// Sorting 버튼 핸들러 (btn3)
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
				JOptionPane.showMessageDialog(null, "정렬할 파일을 먼저 선택하세요", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				// csv 파일을 열어 각 row를 Student형 벡터에 저장하여 반환
				try {
					tok = new Token();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
				vec = tok.getStuVector();

				if (item1_check1 == 1) { // 학번 기준
					if (item2_check1 == 1) { // 오름 차순
												// type(정렬 기준): 1(학번), 2(학년)
						sortedArray = Sort.ascSort(vec, 1);
						try {
							// csv_sorted file 생성
							if (output.makeCSV(sortedArray, tf1.getText()) == -2) {
								JOptionPane.showMessageDialog(null, "stuInfo_sorted.csv 파일을 닫은 후 실행하세요.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								result = -1;
							}
						} catch (IOException exception) {
							exception.printStackTrace();
							result = -1;
						} finally {
							if (result == 1) {
								JOptionPane.showMessageDialog(null, "CSV_sorted File has been successfully created!");
								tf2.setText("정렬된 파일이 생성되었습니다.");
							} else {
								JOptionPane.showMessageDialog(null, "Fail to write a file..", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								tf2.setText("파일 생성 실패...");
							}
						}
					} // if
					else { // 학번 기준 내림 차순
						sortedArray = Sort.dscSort(vec, 1);
						try {
							// csv_sorted file 생성
							if (output.makeCSV(sortedArray, tf1.getText()) == -2) {
								JOptionPane.showMessageDialog(null, "stuInfo_sorted.csv 파일을 닫은 후 실행하세요.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								result = -1;
							}
						} catch (IOException exception) {
							exception.printStackTrace();
							result = -1;
						} finally {
							if (result == 1) {
								JOptionPane.showMessageDialog(null, "CSV_sorted File has been successfully created!");
								tf2.setText("정렬된 파일이 생성되었습니다.");
							} else {
								JOptionPane.showMessageDialog(null, "Fail to write a file..", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								tf2.setText("파일 생성 실패...");
							}
						}
					} // else
				} // if
				else { // 학년 기준
					if (item2_check1 == 1) { // 오름 차순
						sortedArray = Sort.ascSort(vec, 2);
						try {
							// csv_sorted file 생성
							if (output.makeCSV(sortedArray, tf1.getText()) == -2) {
								JOptionPane.showMessageDialog(null, "stuInfo_sorted.csv 파일을 닫은 후 실행하세요.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								result = -1;
							}
						} catch (IOException exception) {
							exception.printStackTrace();
							result = -1;
						} finally {
							if (result == 1) {
								JOptionPane.showMessageDialog(null, "CSV_sorted File has been successfully created!");
								tf2.setText("정렬된 파일이 생성되었습니다.");
							} else {
								JOptionPane.showMessageDialog(null, "Fail to write a file..", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								tf2.setText("파일 생성 실패...");
							}
						}
					} else { // 내림 차순
						sortedArray = Sort.dscSort(vec, 2);
						try {
							// csv_sorted file 생성
							if (output.makeCSV(sortedArray, tf1.getText()) == -2) {
								JOptionPane.showMessageDialog(null, "stuInfo_sorted.csv 파일을 닫은 후 실행하세요.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								result = -1;
							}
						} catch (IOException exception) {
							exception.printStackTrace();
							result = -1;
						} finally {
							if (result == 1) {
								JOptionPane.showMessageDialog(null, "CSV_sorted File has been successfully created!!");
								tf2.setText("정렬된 파일이 생성되었습니다.");
							} else {
								JOptionPane.showMessageDialog(null, "Fail to write a file..", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								tf2.setText("파일 생성 실패...");
							}
						}
					}
				}
			} // actionPerformed()
		}
	} // Sorting class

	// Oepn Folder 버튼  핸들러 (btn4)
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
					JOptionPane.showMessageDialog(null, "알 수 없는 에러..", "ERROR",
										JOptionPane.ERROR_MESSAGE);
			}
		} // actionPerformed()
			
		void openFolder(File folder) throws IOException {
			if(Desktop.isDesktopSupported()) 
				Desktop.getDesktop().open(folder);
		}
	} // OpenFolder class
	
} // MyFrame class
