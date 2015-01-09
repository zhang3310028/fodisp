package com.celula.tmtool.analyser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;
import org.rosuda.REngine.REXPInteger;
import org.rosuda.REngine.REXPVector;
import org.rosuda.REngine.REngineUIInterface;

import com.celula.tmtool.entity.Amplicon;
import com.celula.tmtool.entity.Hotspot;
import com.celula.tmtool.reader.BamReader.CppReader.Amplicon_GC;
import com.celula.tmtool.util.RengineUtil;
public class Analyser {
	static Analyser analyser = null;
	public static Analyser getInstance(){
		if(analyser==null){
			analyser = new Analyser();
		}
        return analyser;
	}
	public Analyser() {
		Rengine engine = RengineUtil.getRengine();
        engine.eval("library(Biostrings)");
        engine.eval("library(cluster)");
        
	}
	private static Hashtable<String,Amplicon> amplicons;
	private static Hashtable<String,Hotspot> hotspots;
	public void loadAmplicons(String bedFile) {
            ArrayList<Amplicon> al = new ArrayList<Amplicon>();
         
            File file = new File(bedFile);
            FileReader fileReader=null;
            BufferedReader bufferedReader = null;
			try {
				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);
	            String ampliconLine = bufferedReader.readLine();

	            while((ampliconLine = bufferedReader.readLine())!=null){
	            	String[] split = ampliconLine.split("\t");
	            	if (split.length < 8) continue;
	            	if(split[0].substring(0,3).equals("chr")){
	            		Amplicon amplicon = new Amplicon();
	            		amplicon.chromosome=split[0];
	            		amplicon.start = Integer.parseInt(split[1]);
	                    amplicon.end = Integer.parseInt(split[2]);
	                    amplicon.strand = split[5];
	                    amplicon.sequence = split[8];
	                    al.add(amplicon);
	            	}
	            }

	            setAmplicons(new Hashtable<String,Amplicon>());
	            for (int i = 0; i < al.size(); i++)
	            {
	                if(!getAmplicons().containsKey(Integer.toString(i)))
	                getAmplicons().put(Integer.toString(i), (Amplicon)al.get(i));
	            }
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					if(fileReader != null) fileReader.close();
					if(bufferedReader!= null) bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
            

            
		
	}
    public Hotspot [] findMatchedHotspot(Amplicon amplicon)
    {
        ArrayList<Hotspot> arrl = new ArrayList<Hotspot>();
        Set<Entry<String, Hotspot>> entrySet = hotspots.entrySet();
        Iterator<Entry<String, Hotspot>> iterator = entrySet.iterator();
        while(iterator.hasNext()){
        	Entry<String, Hotspot> next = iterator.next();
            Hotspot h = (Hotspot)next.getValue();
            if (h.chromosome.equals(amplicon.chromosome) && h.position >= amplicon.start && h.position <= amplicon.end)
                arrl.add(h);
        }
        Object[] array = (Object[])arrl.toArray();
        Hotspot[] array2 = new Hotspot[array.length]; 
        for(int i = 0 ; i < array2.length; i++){
        	array2[i] = (Hotspot)array[i];
        }
        
        return (Hotspot [])array2;
    }
	public Hashtable<String,Amplicon> getAmplicons() {
		return amplicons;
	}

	public void setAmplicons(Hashtable<String,Amplicon> amplicons) {
		Analyser.amplicons = amplicons;
	}

	public void loadHotSpot(String hotspotFile) {
        ArrayList<Hotspot> al = new ArrayList<Hotspot>();
        
        File file = new File(hotspotFile);
        FileReader fileReader=null;
        BufferedReader bufferedReader = null;
        try {
        	fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			String ampliconLine = bufferedReader.readLine();
			while((ampliconLine = bufferedReader.readLine())!=null){
				String[] split = ampliconLine.split("\t");
				if (split.length < 7) continue;
				if(split[0].substring(0,3).equals("chr")){
					String allele = split[6];
					Pattern pattern = Pattern.compile("REF=([^;]*);OBS=([^;]*);");
					Matcher matcher = pattern.matcher(allele);
					Hotspot hs = new Hotspot();
					if(matcher.find()){
						hs.refAllele = matcher.group(1);
						hs.altAllele = matcher.group(2);
					}
					hs.chromosome = split[0];
	                hs.position = Integer.parseInt(split[2]);
					al.add(hs);
				}
			}
        } catch (IOException e) {
			e.printStackTrace();
		}finally{
				try {
					if(fileReader != null) fileReader.close();
					if(bufferedReader!= null) bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
       
        setHotspots(new Hashtable());
        for (int i = 0; i < al.size(); i++)
        {
            if (!getHotspots().containsKey(Integer.toString(i)))
                getHotspots().put(Integer.toString(i), (Hotspot)al.get(i));
        }

	}

	public Hashtable<String,Hotspot> getHotspots() {
		return hotspots;
	}

	public void setHotspots(Hashtable<String,Hotspot> hotspots) {
		Analyser.hotspots = hotspots;
	}
	public int[] expectedHotpotFlowIndex(Amplicon amplicon,Hotspot hotspot,Amplicon_GC readsInAmplicon,String barcodeSeqence ,String adapterSequence) {
		Rengine engine = RengineUtil.getRengine();
		
		String flowOrder = new String(readsInAmplicon.flowOrder);
		
		engine.assign("amp.seq", amplicon.sequence);
		engine.assign("flowOrder", flowOrder);
        engine.assign("hotspot.pos", new int[]{hotspot.position});
        engine.assign("allele", new String[]{hotspot.refAllele,hotspot.altAllele});
        engine.assign("amp.start",  new int[]{amplicon.start});
        engine.assign("amp.end",  new int[]{amplicon.end});
        engine.assign("barcode",barcodeSeqence + adapterSequence);
        engine.assign("strand", amplicon.strand);
        String evalString = "source(\"expectedHotspotFlowIndex.R\")";
        engine.eval(evalString);
        evalString = "expectHotspotFlowIndex(amp.seq=amp.seq,flowOrder=flowOrder,hotspot.pos=hotspot.pos,allele=allele,amp.start=amp.start,amp.end=amp.end,barcode=barcode,strand=strand)";
        REXP eval = engine.eval(evalString,true);
        double[] asDoubleArray = eval.asDoubleArray();
        int[] allele_pos_R = new int[]{(int)asDoubleArray[0],(int)asDoubleArray[1]};
        
        int[] allele_pos = { allele_pos_R[0], allele_pos_R[1] };
        return allele_pos;
	}
	public int[] correctPolyclonalSignal(Amplicon_GC readsInAmplicon ,int[] hotspotFlow, int max_dist) {
		Rengine rengine = RengineUtil.getRengine();
		int [][] flowValue = readsInAmplicon.flowValue; 
		
		RengineUtil.assignAsRMatrix(rengine, flowValue, "mat");
		
		rengine.assign("max.dist", new int[]{max_dist});
		rengine.assign("hotspotFlow", hotspotFlow);
		rengine.eval("source(\"correctPolyclonalSignal.R\")");
		
//      rengine.eval("windows(width=12,height=8)");//
//		rengine.eval("par(mfrow=c(1,2))"); //
            try {
            	
				REXP eval = rengine.eval("cluster<-clusterFlowMatrix(mat,hotspotFlow,max.dist)");
				rengine.eval("hs<-mat[,hotspotFlow]");
//				
//				rengine.eval("plot(hs[,1],hs[,2],col=cluster,xlab=\"Euclidean Distance\",ylab=\"Euclidean Distance\")");
//				rengine.eval("JavaGD()");
//				rengine.eval("plot(1,1)");
				double[] doubleArray = eval.asDoubleArray();
				int[] intArray = new int[doubleArray.length];
				for (int i = 0; i < intArray.length; i++) {
					intArray[i] = (int) doubleArray[i];
				}
				return intArray;
			} catch (Exception e) {
				return new int[]{-1};
			}
   
	}
	public int[] getCorrectedAlleleCount(int[] clusters) {
		int[] groupCount = new int[4];
        for (int i = 0; i < clusters.length; i++)
            groupCount[clusters[i] - 1]++;
        groupCount[1] += groupCount[2];
        groupCount[3] += groupCount[2];
        int[] alleleCount = { groupCount[1], groupCount[3]};
        return alleleCount;
	}
	public void plotHeatmap(Amplicon_GC readsInAmplicon, int windowSize, int startFlow, boolean isCluster,int[] clusters,int[] allelePos) {
		
		Rengine rengine = RengineUtil.getRengine();
		rengine.eval("library(JavaGD)");
		rengine.eval("JavaGD()");
		//engine.Initialize();
		int length = readsInAmplicon.flowValue[0].length;
        if (startFlow > length)
            startFlow = length-1;
        if (startFlow + windowSize > length-1)
            windowSize = length -1 - startFlow;
        rengine.assign("windowSize", new int[]{windowSize});
        rengine.assign("startFlow", new int[]{startFlow});
        RengineUtil.assignAsRMatrix(rengine, readsInAmplicon.flowValue, "mat");
        rengine.assign("flowOrder", new String(readsInAmplicon.flowOrder));
        
        rengine.eval("flowOrder<-strsplit(flowOrder,\"\",fixed=T)[[1]]");
        rengine.eval("mat<-mat[,startFlow:(startFlow+windowSize)]");
        if (isCluster)
        {
            if (clusters.length != readsInAmplicon.flowValue.length)
                return;
            rengine.assign("cluster", clusters);
            rengine.eval("mat<-mat[order(cluster),]");
        }
        rengine.eval("source(\"plotHeatMap.R\")");
        rengine.eval("plotHeatMap(mat,startFlow=startFlow,flowOrder=flowOrder[startFlow:(startFlow+windowSize)])");

        for (int i = 0; i < allelePos.length; i++)
        {
            rengine.assign("hotspot_Flow", new int[]{allelePos[i] - startFlow + 1});
            rengine.eval("abline(v=hotspot_Flow,xpd=F,col=\"white\",lty=2)");
        }

        if (isCluster)
        {
            rengine.eval("abline(h=cumsum(table(cluster)),col=\"red\")");
        }
        System.gc();
	}
	

}
