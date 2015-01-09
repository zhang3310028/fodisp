package test;

import javax.swing.JFrame;

import org.rosuda.JRI.RMainLoopCallbacks;
import org.rosuda.JRI.Rengine;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPString;
import org.rosuda.REngine.JRI.JRIEngine;
import org.rosuda.javaGD.GDCanvas;
import org.rosuda.javaGD.GDInterface;

public class RTest {

	public static void main(String[] args) {
		testPlot();
		
	}
	
	@Deprecated
	private static void test1() {
		try {
			org.rosuda.REngine.REngine jriEngine = JRIEngine.createEngine();
			REXP rexp = new REXP();
			REXPString rexpString = new REXPString("R.version.string");
			REXP eval = jriEngine.eval(rexpString, null, true);
	        System.out.println(eval.asString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void test2() {
		try {
			System.out.println(System.getProperty("java.library.path"));  
	        System.loadLibrary("jri");
	        org.rosuda.JRI.Rengine jriEngine = new org.rosuda.JRI.Rengine(null, false, new TextConsole());
			org.rosuda.JRI.REXP eval = jriEngine.eval("R.version.string");
	        System.out.println(eval.asString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void test3() {
		try {
			System.out.println(System.getProperty("java.library.path"));  
	        System.loadLibrary("jri");
	        org.rosuda.JRI.Rengine jriEngine = new org.rosuda.JRI.Rengine(null, false, new TextConsole());
			org.rosuda.JRI.REXP eval = jriEngine.eval("R.version.string");
	        System.out.println(eval.asString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testPlot() {
		System.out.println(System.getProperty("java.library.path"));  
        System.loadLibrary("jri");
        org.rosuda.JRI.Rengine jriEngine = new org.rosuda.JRI.Rengine(null, false, new TextConsole());
        jriEngine.eval("library(JavaGD)");
//      jriEngine.eval("Sys.setenv('JAVAGD_CLASS_NAME'='test.RTest.MyJavaGD1')");
        jriEngine.eval("JavaGD()");
        jriEngine.eval("plot(1,1)");
        jriEngine.eval("JavaGD()");
        jriEngine.eval("plot(1,2)");
        //jriEngine.end();
	}
	static class TextConsole implements RMainLoopCallbacks{

		@Override
		public void rBusy(Rengine arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String rChooseFile(Rengine arg0, int arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void rFlushConsole(Rengine arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void rLoadHistory(Rengine arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String rReadConsole(Rengine arg0, String arg1, int arg2) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void rSaveHistory(Rengine arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void rShowMessage(Rengine arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void rWriteConsole(Rengine arg0, String arg1, int arg2) {
			System.out.println(arg1);
		}} 
	
		static class MyJavaGD1 extends GDInterface {
		public JFrame f;
		public void gdOpen(double w, double h) {
			f = new JFrame("JavaGD");
			c = new GDCanvas(w, h);
			f.add((GDCanvas) c);
			f.pack();
			f.setVisible(true);
			f.setTitle("Naked R plot");
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		}
}
