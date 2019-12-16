package ClassLoad;
/**
 * 测试自定义的FileSystemClassLoader
 * @author dell
 *
 */

public class TestFileSystem {
	public static void main(String[] args) throws Exception {
		FileSystemClassLoader loader=new FileSystemClassLoader("E:/javac");
		FileSystemClassLoader loader1=new FileSystemClassLoader("E:/javac");
		FileSystemClassLoader loader2=new FileSystemClassLoader("E:/javac");
		FileSystemClassLoader loader3=new FileSystemClassLoader("E:/workspace-java/注解_反射_类加载机制/bin");
		Class<?> c=loader.loadClass("Hi");
		Class<?> c1=loader1.loadClass("Hi");
		Class<?> c2=loader2.loadClass("Hi");
		Class<?> c3=loader.loadClass("java.lang.String");
		Class<?> c4=loader3.loadClass("ClassLoad.Demo01");
		System.out.println(c);
		//被两个加载器加载的同一个类，JVM不认为是同一个类
		System.out.println(c1.equals(c2));
		System.out.println(c3);
		System.out.println(c3.getClassLoader());
		System.out.println(c1.getClassLoader());
		System.out.println(c4.getClassLoader());
	}

}
