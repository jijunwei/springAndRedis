package com.example.utils.enordecryptutil;


import com.hazelcast.util.StringUtil;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;


public class AESProterties extends PropertyPlaceholderConfigurer {
	
	protected void processProperties(ConfigurableListableBeanFactory beanFactory , Properties props) throws BeansException{
		
		try{
			
			boolean temp = writeDatFile();
			
			String driverClassName = props.getProperty("mysql.driverClassName");
			String url = props.getProperty("mysql.url");
			String userName = props.getProperty("mysql.username");
			String password = props.getProperty("mysql.password");
			AESTool aes = new AESTool();

		/*				String path = this.getClass().getResource("/").toString().replaceFirst("file:/", "");
			Properties p=new Properties();
			
			InputStream fs=new BufferedInputStream (new FileInputStream(path+"/properties/jdbc.properties"));
			p.load(fs);
			p.put("mysql.driverClassName",aes.encrypt(driverClassName));
			p.put("mysql.url", aes.encrypt(url));
			p.put("mysql.username", aes.encrypt(userName));
			p.put("mysql.password", aes.encrypt(password));
			fs.close();
			
			FileOutputStream fos=new FileOutputStream(path+"/properties/jdbc.properties");
			
			p.store(fos,null);
			fos.close();
			
			driverClassName = p.getProperty("mysql.driverClassName");
			url = p.getProperty("mysql.url");
			userName = p.getProperty("mysql.username");
			password = p.getProperty("mysql.password");*/

			if(!StringUtil.isNullOrEmptyAfterTrim(driverClassName))
				props.setProperty("mysql.driverClassName", aes.decrypt(driverClassName));
			
			if(!StringUtil.isNullOrEmptyAfterTrim(url))
				props.setProperty("mysql.url", aes.decrypt(url));
			
			if(!StringUtil.isNullOrEmptyAfterTrim(userName))
				props.setProperty("mysql.username", aes.decrypt(userName));
			
			if(!StringUtil.isNullOrEmptyAfterTrim(password))
				props.setProperty("mysql.password", aes.decrypt(password));
			
			super.processProperties(beanFactory , props);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new BeanInitializationException(e.getMessage());
		}
	}
	
	public  boolean writeDatFile() {
		try
		{
			File file =  new File(System.getProperty("user.dir")+"/key.dat");
			
			if(file.exists()){
				return false;
			}
			try{
				file.createNewFile();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			DataOutputStream out=new DataOutputStream(new FileOutputStream("key.dat"));
			
	        RandomGUID myGUID = new RandomGUID();
	        
	        String  guidKey = myGUID.valueAfterMD5;
	        
	        out.writeBytes(guidKey);
			out.close();
			
			return true;
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
