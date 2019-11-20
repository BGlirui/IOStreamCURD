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
		
		jlName = new JLabel("姓名：");
		jlPosition = new JLabel("职位：");
		jlSalary = new JLabel("薪资：");
		jlDisplay = new JLabel("无");
		jlId = new JLabel("ID");
		jlPath = new JLabel("文件存储路径：");
		
		jtName = new JTextField(10);
		jtPosition = new JTextField(10);
		jtSalary = new JTextField(10);
		jtId = new JTextField(7);
		jtPath = new JTextField(15);
		
		jbAdd = new JButton("增加");
		jbAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empDao==null) {
					jlDisplay.setText("请先设置路径");
				}else {
					if(notNull()) {
						Employee emp = new Employee();
						emp.setName(jtName.getText());
						emp.setPosition(jtPosition.getText());
						emp.setSalary(Double.parseDouble(jtSalary.getText()));
						empDao.save(emp);
		                jlDisplay.setText("添加成功");
					}
				}
            }
		});
		jbDelect = new JButton("删除");
		jbDelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = 0;
				if(empDao==null) {
					jlDisplay.setText("请先设置路径");
				}else {
					if(jtId.getText().equals("")) {
						jlDisplay.setText("请设置ID");
					}else {
						id= Integer.parseInt(jtId.getText());
						if(empDao.delete(id)) {
							jlDisplay.setText("删除成功");
						}else {
							jlDisplay.setText("删除失败");
						}
					}
					
				}
				
			}
		});
		jbUpdata = new JButton("更新");
		jbUpdata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empDao==null) {
					jlDisplay.setText("请先设置路径");
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
						jlDisplay.setText("更新成功");
					}
				}
			}
		});
		jbFindOne = new JButton("获取一个");
		jbFindOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empDao==null) {
					jlDisplay.setText("请先设置路径");
				}else {
					if(jtId.getText().equals("")) {
						jlDisplay.setText("请输入Id值");
					}else {
						int id=Integer.parseInt(jtId.getText())-1;
						Employee emp = empDao.findOne(id);
						if(emp==null) {
							jlDisplay.setText("查无此人");
						}else {
							jlDisplay.setText("ID:"+emp.getId()+"...."+"姓名："+emp.getName()+"...."+"职位："+emp.getPosition()+"...."+"工资："+emp.getSalary());
						}
					}
					
				}
			}
		});
		jbFindAll = new JButton("获取所有");
		jbFindAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empDao==null) {
					jlDisplay.setText("请先设置路径");
				}else {
					String text = "<html>";
					List<Employee> list = empDao.findAll(jlDisplay);
					for(int i=0;i<list.size();i++) {
						text = text + "ID:"+list.get(i).getId()+"...."+"姓名："+list.get(i).getName()+"...."+"职位："+list.get(i).getPosition()+"...."+"工资："+list.get(i).getSalary()+"<br>";
					}
					text = text+"<html>";
					jlDisplay.setText(text);
					}
				}
			}
		);
		
		jbPath = new JButton("确定路径");
		jbPath.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				empDao = new EmployeeDao(jtPath.getText());
				jlDisplay.setText("路径成功设置为："+jtPath.getText());
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
		
		this.setTitle("增删改查");
        this.setSize(600, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	private boolean notNull() {
		boolean result = true;
		if(jtName.getText().equals("")) {
			result = false;
			jlDisplay.setText("请输入姓名！");
		}
		if(jtPosition.getText().equals("")) {
			result = false;
			jlDisplay.setText("请输入职位！");
		}
		if(jtSalary.getText().equals("")) {
			result = false;
			jlDisplay.setText("请输入薪资！");
		}
		return result;
	}
}
