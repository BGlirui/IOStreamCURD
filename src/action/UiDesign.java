package action;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

public class UiDesign extends JFrame{

	JPanel jpName,jpPosition,jpSalary,jpButton,jpDelect,jpDisplay,jpPath;
	JLabel jlId,jlName,jlPosition,jlSalary,jlDisplay,jlPath;
	JTextField jtId,jtName,jtPosition,jtSalary,jtPath;
	JButton jbAdd,jbDelect,jbUpdata,jbFindOne,jbFindAll,jbPath;
	EmployeeDao empDao =null ;
	
	public UiDesign() {
		jpName = new JPanel();
		jpPosition = new JPanel();
		jpSalary = new JPanel();
		jpButton = new JPanel();
		jpDelect = new JPanel();
		jpDisplay = new JPanel();
		jpPath = new JPanel();
		
		jlName = new JLabel("������");
		jlPosition = new JLabel("ְλ��");
		jlSalary = new JLabel("н�ʣ�");
		jlDisplay = new JLabel("��");
		jlId = new JLabel("ID");
		jlPath = new JLabel("�ļ��洢·����");
		
		jtName = new JTextField(10);
		jtPosition = new JTextField(10);
		jtSalary = new JTextField(10);
		jtId = new JTextField(7);
		jtPath = new JTextField(15);
		
		jbAdd = new JButton("����");
		jbAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empDao==null) {
					jlDisplay.setText("��������·��");
				}else {
					if(notNull()) {
						Employee emp = new Employee();
						emp.setName(jtName.getText());
						emp.setPosition(jtPosition.getText());
						emp.setSalary(Double.parseDouble(jtSalary.getText()));
						empDao.save(emp);
		                jlDisplay.setText("��ӳɹ�");
					}
				}
            }
		});
		jbDelect = new JButton("ɾ��");
		jbDelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = 0;
				if(empDao==null) {
					jlDisplay.setText("��������·��");
				}else {
					if(jtId.getText().equals("")) {
						jlDisplay.setText("������ID");
					}else {
						id= Integer.parseInt(jtId.getText());
						if(empDao.delete(id)) {
							jlDisplay.setText("ɾ���ɹ�");
						}else {
							jlDisplay.setText("ɾ��ʧ��");
						}
					}
					
				}
				
			}
		});
		jbUpdata = new JButton("����");
		jbUpdata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empDao==null) {
					jlDisplay.setText("��������·��");
				}else {
					if(notNull()) {
						Employee emp = new Employee();
						int id = 0;
						double salary = 0.00;
						salary = Double.parseDouble(jtSalary.getText());
						id= Integer.parseInt(jtId.getText());
						emp.setId(id);
						emp.setName(jtName.getText());
						emp.setPosition(jtPosition.getText());
						emp.setSalary(salary);
						empDao.update(emp);
						jlDisplay.setText("���³ɹ�");
					}
				}
			}
		});
		jbFindOne = new JButton("��ȡһ��");
		jbFindOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empDao==null) {
					jlDisplay.setText("��������·��");
				}else {
					if(jtId.getText().equals("")) {
						jlDisplay.setText("������Idֵ");
					}else {
						int id=Integer.parseInt(jtId.getText())-1;
						Employee emp = empDao.findOne(id);
						if(emp==null) {
							jlDisplay.setText("���޴���");
						}else {
							jlDisplay.setText("ID:"+emp.getId()+"...."+"������"+emp.getName()+"...."+"ְλ��"+emp.getPosition()+"...."+"���ʣ�"+emp.getSalary());
						}
					}
					
				}
			}
		});
		jbFindAll = new JButton("��ȡ����");
		jbFindAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empDao==null) {
					jlDisplay.setText("��������·��");
				}else {
					String text = "<html>";
					List<Employee> list = empDao.findAll(jlDisplay);
					for(int i=0;i<list.size();i++) {
						text = text + "ID:"+list.get(i).getId()+"...."+"������"+list.get(i).getName()+"...."+"ְλ��"+list.get(i).getPosition()+"...."+"���ʣ�"+list.get(i).getSalary()+"<br>";
					}
					text = text+"<html>";
					jlDisplay.setText(text);
					}
				}
			}
		);
		
		jbPath = new JButton("ȷ��·��");
		jbPath.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				empDao = new EmployeeDao(jtPath.getText());
				jlDisplay.setText("·���ɹ�����Ϊ��"+jtPath.getText());
			}
		});
		this.setLayout(new GridLayout(7, 1));
		
		jpPath.add(jlPath);
		jpPath.add(jtPath);
		jpPath.add(jbPath);
		
		jpName.add(jlName);
		jpName.add(jtName);
		
		jpPosition.add(jlPosition);
		jpPosition.add(jtPosition);
		
		jpSalary.add(jlSalary);
		jpSalary.add(jtSalary);
		
		jpButton.add(jbAdd);
		jpButton.add(jbFindAll);
		
		jpDelect.add(jlId);
		jpDelect.add(jtId);
		jpDelect.add(jbDelect);
		jpDelect.add(jbFindOne);
		jpDelect.add(jbUpdata);
		
		jpDisplay.add(jlDisplay);
		
		this.add(jpPath);
		this.add(jpName);
		this.add(jpPosition);
		this.add(jpSalary);
		this.add(jpButton);
		this.add(jpDelect);
		this.add(jpDisplay);
		
		this.setTitle("��ɾ�Ĳ�");
        this.setSize(600, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	private boolean notNull() {
		boolean result = true;
		if(jtName.getText().equals("")) {
			result = false;
			jlDisplay.setText("������������");
		}
		if(jtPosition.getText().equals("")) {
			result = false;
			jlDisplay.setText("������ְλ��");
		}
		if(jtSalary.getText().equals("")) {
			result = false;
			jlDisplay.setText("������н�ʣ�");
		}
		return result;
	}
}
