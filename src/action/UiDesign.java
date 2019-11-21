package action;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

public class UiDesign extends JFrame{

	JPanel jpName,jpPosition,jpSalary,jpButton,jpDelect,jpDisplay,jpPath,jpPage;
	JLabel jlId,jlName,jlPosition,jlSalary,jlDisplay,jlPath,jlPage;
	JTextField jtId,jtName,jtPosition,jtSalary,jtPath;
	JButton jbAdd,jbDelect,jbUpdata,jbFindOne,jbFindAll,jbPath,jbup,jbdown;
	EmployeeDao empDao =null ;
	int count = 0;
	int currentCount = 0;
	int reCurrentCount = 0;
	boolean canPage = false;
	List<Employee> list = null;
	
	public UiDesign() {
		jpName = new JPanel();
		jpPosition = new JPanel();
		jpSalary = new JPanel();
		jpButton = new JPanel();
		jpDelect = new JPanel();
		jpDisplay = new JPanel();
		jpPath = new JPanel();
		jpPage = new JPanel();
		
		jlName = new JLabel("姓名：");
		jlPosition = new JLabel("职位：");
		jlSalary = new JLabel("薪资：");
		jlDisplay = new JLabel("无");
		jlId = new JLabel("ID");
		jlPath = new JLabel("文件存储路径：");
		jlPage = new JLabel("只有1页");
		
		jtName = new JTextField(10);
		jtPosition = new JTextField(10);
		jtSalary = new JTextField(10);
		jtId = new JTextField(7);
		jtPath = new JTextField(15);

		//jtPath.setText("D:/Project/Eclipes/IOStreamCURD/data.txt");
		
		jbAdd = new JButton("增加");
		jbup = new JButton("上一页");
		jbdown = new JButton("下一页");
		jbAdd.addActionListener(new ActionListener() {
			@Override
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
		                jlPage.setText("共1页");
					}
				}
            }
		});
		jbDelect = new JButton("删除");
		jbDelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canPage = false;
				int id = 0;
				jlPage.setText("共1页");
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
			@Override
			public void actionPerformed(ActionEvent e) {
				canPage = false;
				jlPage.setText("共1页");
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
			@Override
			public void actionPerformed(ActionEvent e) {
				canPage = false;
				jlPage.setText("共1页");
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
		//翻页用
		jbdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(canPage){
					if(currentCount>count){
						display(0);
						jlPage.setText("第"+(count+1)+"页，共"+(currentCount+1)+"页");
						count++;
						//jlDisplay.setText("1");
					}else {
						if(reCurrentCount>0){
							display(1);
							jlPage.setText("第"+(count+1)+"页，共"+(currentCount+1)+"页");
							//jlDisplay.setText(reCurrentCount+" ");
							//count++;
						}
					}
				}
			}
		});
		//翻页用
		jbup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(canPage){
					count--;
					display(0);
					jlPage.setText("第"+(count+1)+"页，共"+(currentCount+1)+"页");
					System.out.println(count);
					if(count==0){
						count=1;
					}
				}
			}
		});
		jbFindAll = new JButton("获取所有");
		jbFindAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				count=0;
				if(empDao==null) {
					jlDisplay.setText("请先设置路径");
					}else {
						list = empDao.findAll(jlDisplay);
						currentCount = list.size()/3;
						reCurrentCount = list.size()%3;
						display(0);
					jlPage.setText("第"+(count+1)+"页，共"+(currentCount+1)+"页");
						count++;
						canPage = true;
					}
				}
			}
		);
		
		jbPath = new JButton("确定路径");
		jbPath.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				canPage = false;
				jlPage.setText("共1页");
				empDao = new EmployeeDao(jtPath.getText());
				jlDisplay.setText("路径成功设置为："+jtPath.getText());
			}
		});
		this.setLayout(new GridLayout(8, 1));



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

		jpPage.add(jbup);
		jpPage.add(jlPage);
		jpPage.add(jbdown);
		
		jpDisplay.add(jlDisplay);
		
		this.add(jpPath);
		this.add(jpName);
		this.add(jpPosition);
		this.add(jpSalary);
		this.add(jpButton);
		this.add(jpDelect);
		this.add(jpDisplay);
		this.add(jpPage);
		
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


	private void display(int reCount){
		String text = "<html>";
		if(reCount==0){
			for(int i=0+(count*3);i<3+(count*3);i++) {
				text = text + "ID:"+list.get(i).getId()+"...."+"姓名："+list.get(i).getName()+"...."+"职位："+list.get(i).getPosition()+"...."+"工资："+list.get(i).getSalary()+"<br>";
			}
			text = text+"<html>";
			jlDisplay.setText(text);
		}else {
			for(int i=3+((count-1)*3);i<list.size();i++) {
				text = text + "ID:"+list.get(i).getId()+"...."+"姓名："+list.get(i).getName()+"...."+"职位："+list.get(i).getPosition()+"...."+"工资："+list.get(i).getSalary()+"<br>";
			}
			text = text+"<html>";
			jlDisplay.setText(text);
		}

	}


}
