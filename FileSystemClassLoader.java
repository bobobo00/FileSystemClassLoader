package ClassLoad;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * �Զ����ļ�ϵͳ������
 * @author dell
 *
 */

public class FileSystemClassLoader extends ClassLoader {
	private String rootDir;

	public FileSystemClassLoader(String rootDir) {
		super();
		this.rootDir = rootDir;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?>c=findLoadedClass(name);
		
		//�Ȳ�ѯ�Ƿ��Ѿ����ظ��࣬�Ѿ�������ֱ�ӷ��ؼ��غõ��࣬δ���أ�������µ��ࡣ
		if(c!=null) {
			return c;
		}else {
			ClassLoader parent=this.getParent();
			try {
				c=parent.loadClass(name);
			}catch(Exception e) {
				
			}
			if(c!=null) {
				return c;
			}else {
				byte[] classData=getClassData(name);
				if(classData==null) {
					throw new ClassNotFoundException();
				}else {
					c=defineClass(name,classData,0,classData.length);
				}
			}
		}
		return c;
	}
	
	private byte[] getClassData(String classname) {
		String path=rootDir+"/"+classname.replace('.', '/')+".class";
		byte[] data=new byte[1024];
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		InputStream is=null;
		try {
			is=new FileInputStream(path);
			int len=-1;
			while((len=is.read(data))!=-1) {
				baos.write(data,0,len);
			}
			return baos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if(null!=is) {
				is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
