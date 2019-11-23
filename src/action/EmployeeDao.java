package action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

public class EmployeeDao {
	String path = "";
	String text = "D:/Project/Eclipes/IOStreamCURD/data.txt";
	File file = null;
	public EmployeeDao(String path) {
		this.path = path;
		this.file = new File(this.path);
	}
	public List<Employee> list = new ArrayList<Employee>();
	public void save(Employee emp){
		List<Employee> empList = new ArrayList<Employee>();
		if(this.file.canRead()) {
		try {
			ObjectInputStream iss = new ObjectInputStream(new FileInputStream(this.file));
			empList = (List<Employee>) iss.readObject();
			emp.setId(empList.size()+1);
			empList.add(emp);
			iss.close();
			ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(this.file));
			oss.writeObject(empList);
			oss.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			emp.setId(0);
			empList.add(emp);
			try {
				ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(this.file));
				oss.writeObject(empList);
				oss.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("��ӳɹ�");
	}
	
	public boolean delete(int id) {
		List<Employee> empList = new ArrayList<Employee>();
		boolean is = false;
		if(this.file.canRead()) {
			try {
				ObjectInputStream iss = new ObjectInputStream(new FileInputStream(this.file));
				empList = (List<Employee>) iss.readObject();
				//empList.remove(id);
				for(int i=0;i<empList.size();i++) {
					if(empList.get(i).getId()==id) {
						empList.remove(i);
						is=true;
					}
				}
				ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(this.file));
				oss.writeObject(empList);
				iss.close();
				oss.close();
				System.out.println("���ɾ��");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return is;
	}
	
	public void update(Employee emp) {
		List<Employee> empList = new ArrayList<Employee>();
		if(this.file.canRead()) {
			try {
				ObjectInputStream iss = new ObjectInputStream(new FileInputStream(this.file));
				empList = (List<Employee>) iss.readObject();
				//empList.set(emp.getId(), emp);
				for(int i=0;i<empList.size();i++) {
					if(emp.getId()==empList.get(i).getId()) {
						empList.set(i, emp);
					}
				}
				ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(this.file));
				oss.writeObject(empList);
				iss.close();
				oss.close();
				System.out.println("��ɸ���");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public List<Employee> findAll(JLabel jlDisplay){
		List<Employee> empList = new ArrayList<Employee>();
		if(this.file.canRead()) {
			try {
				ObjectInputStream iss = new ObjectInputStream(new FileInputStream(this.file));
				empList = (List<Employee>) iss.readObject();
				System.out.println("��ɶ�ȡ");
				iss.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			jlDisplay.setText("��·���²��޴��ļ���");
		}
		
		return empList;
	}
	
	public Employee findOne(int id) {
		List<Employee> empList = new ArrayList<Employee>();
		Employee emp = null;
		boolean is = true;
		if(this.file.canRead()) {
			try {
				ObjectInputStream iss = new ObjectInputStream(new FileInputStream(this.file));
				empList = (List<Employee>) iss.readObject();
				iss.close();
				System.out.println("��ɶ�ȡ");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		if(id>=empList.size()||id<0) {
			return null;
		}else {
			if((id+1)==empList.get(id).getId()){
				emp = empList.get(id);
			}else if((id+1)>empList.get(id).getId()){
				for(int i=id+1;i<empList.size();i++){
					if((id+1)==empList.get(i).getId()){
						emp=empList.get(i);
					}
				}
			}else{
				for(int i=id+1;i>0;i--){
					if((id+1)==empList.get(i).getId()){
						emp=empList.get(i);
					}
				}
			}
			return emp;
		}
	}
}
