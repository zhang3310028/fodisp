package com.celula.tmtool.util;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RMainLoopCallbacks;
import org.rosuda.JRI.Rengine;

public class RengineUtil {
	private static Rengine engine;
	static class TextConsole implements RMainLoopCallbacks{

		@Override
		public void rBusy(org.rosuda.JRI.Rengine arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String rChooseFile(org.rosuda.JRI.Rengine arg0, int arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void rFlushConsole(org.rosuda.JRI.Rengine arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void rLoadHistory(org.rosuda.JRI.Rengine arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String rReadConsole(org.rosuda.JRI.Rengine arg0, String arg1,
				int arg2) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void rSaveHistory(org.rosuda.JRI.Rengine arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void rShowMessage(org.rosuda.JRI.Rengine arg0, String arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void rWriteConsole(org.rosuda.JRI.Rengine arg0, String arg1,
				int arg2) {
			// TODO Auto-generated method stub
			
		}}
	public static Rengine getRengine(){
		if(engine==null){
			String env = System.getProperty("java.library.path");  
	        System.loadLibrary("jri"); 
	        engine = new Rengine(null, false, new TextConsole());
		}
		return engine;
	}
	public static void closeEngine(){
		engine.end();
		engine=null;
	}
	public static REXP assignAsRMatrix(Rengine rEngine, double[][] sourceArray, String nameToAssignOn) {
        if (sourceArray.length == 0) {
            return null;
        }

        rEngine.assign(nameToAssignOn, sourceArray[0]);
        REXP resultMatrix = rEngine.eval(nameToAssignOn + " <- matrix( " + nameToAssignOn + " ,nr=1)");
        for (int i = 1; i < sourceArray.length; i++) {
            rEngine.assign("temp", sourceArray[i]);
            resultMatrix = rEngine.eval(nameToAssignOn + " <- rbind(" + nameToAssignOn + ",matrix(temp,nr=1))");
        }

        return resultMatrix;
    }
	public static REXP assignAsRMatrix(Rengine rEngine, int[][] sourceArray, String nameToAssignOn) {
        if (sourceArray.length == 0) {
            return null;
        }

        rEngine.assign(nameToAssignOn, sourceArray[0]);
        REXP resultMatrix = rEngine.eval(nameToAssignOn + " <- matrix( " + nameToAssignOn + " ,nr=1)");
        for (int i = 1; i < sourceArray.length; i++) {
            rEngine.assign("temp", sourceArray[i]);
            resultMatrix = rEngine.eval(nameToAssignOn + " <- rbind(" + nameToAssignOn + ",matrix(temp,nr=1))");
        }

        return resultMatrix;
    }
	public static Rengine initEngine() {
		engine = new Rengine();
		return engine;
	}
}
