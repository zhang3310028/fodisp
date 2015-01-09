package com.celula.tmtool.reader;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;

public class BamReader {
	public static int MAXSEQ = 30000;
	public static int MAXSEQLEN = 300;
	public static int MAXFLOW = 500;
	public static int MAXQNAMELEN= 50;
	public static int MAXCIGARLEN =200;
	
	
	public interface CppReader extends Library{
		public static class Amplicon_GC {
			private Hashtable complementNucleotide = new Hashtable();
			public int maxSeq;
			public int maxSeqLen=MAXSEQLEN;
			public int minFlowValue=3;
			public int maxFlowValue=7;
            public int maxFlow;
            public int[] startFlow;
            public int[] startPos;
            public int[][] flowValue;
            public int[] hotspotFlow;
            public int[] hotspotBase;
            public int[][] baseFlow;
            public int[][] genomicCord;
            public char[] strand;
            public char[] flowOrder;
            public char[][] qname;
            public char[][] cigar;
            public char[][] seq;
            public char[][] qual;
            public Amplicon_GC()
            {
                complementNucleotide.put('C', 'G');
                complementNucleotide.put('G', 'C');
                complementNucleotide.put('A', 'T');
                complementNucleotide.put('T', 'A');
            }
            public void excludeEmpty(AMPLICON.ByReference Amp, int rcount)
            {
            	maxSeq = rcount;
                maxFlow = Amp.maxFlow;
                flowOrder = new char[maxFlow];
                //flowValue = new Int32[rcount][];
                flowValue = new int[rcount][Amp.maxFlow];
                hotspotFlow = new int[rcount];
                hotspotBase = new int[rcount];
                baseFlow = new int[rcount][];
                startFlow = new int[rcount];
                startPos = new int[rcount];
                genomicCord = new int[rcount][]; 
                qname = new char[rcount][];
                strand = new char[rcount];
                cigar = new char[rcount][];
                seq = new char[rcount][];
                qual = new char[rcount][];
                
                for (int i = 0; i < maxFlow; i++)
                {
                    flowOrder[i] = (char)Amp.flowOrder[i];
                }

                for (int i = 0; i < rcount; i++)
                {
                    startFlow[i] = Amp.startFlow[i];
                    startPos[i] = Amp.startPos[i];

                    //flowValue[i] = new Int32[maxFlow];
                    for (int j = 0; j < maxFlow; j++)
                    {
                        //flowValue[i][j] = Amp.flowValue[i * readBAM.MAXFLOW + j];
                        flowValue[i][j] = Amp.flowValue[i * BamReader.MAXFLOW + j];
                    }

                    qname[i] = new char[Amp.qnameLen[i]];
                    for (int j = 0; j < Amp.qnameLen[i]; j++)
                    {
                        qname[i][j] = (char)Amp.qname[i * BamReader.MAXQNAMELEN + j];
                    }

                    cigar[i] = new char[Amp.cigarLen[i]];
                    for (int j = 0; j < Amp.cigarLen[i]; j++)
                    {
                        cigar[i][j] = (char)Amp.cigar[i * BamReader.MAXCIGARLEN + j];
                    }

                    seq[i] = new char[Amp.seqLen[i]];
                    qual[i] = new char[Amp.seqLen[i]];
                    baseFlow[i] = new int[Amp.seqLen[i]];
                    genomicCord[i] = new int[Amp.seqLen[i]];
                    for (int j = 0; j < Amp.seqLen[i]; j++)
                    {
                        seq[i][j] = (char)Amp.seq[i * BamReader.MAXSEQLEN + j];
                        qual[i][j] = (char)Amp.qual[i * BamReader.MAXSEQLEN + j];
                        strand[i] = (char)Amp.strand[i];
                        //baseFlow[i][j] = Amp.baseFlow[i * readBAM.MAXSEQLEN + j];
                    }
                }
            
            }
            public void expandCigar()
            {
                for (int n = 0; n < startPos.length; n++)
                {
                    if (cigar[n][0]-'0' < 0 || cigar[n][0]-'9' > 0)  // Already expanded
                        return;

                    ArrayList<Character> al = new ArrayList<Character>();
                    int start = 0;
                    for (int i = 0; i < cigar[n].length; i++)
                    {
                        if (cigar[n][i]-'0'>= 0 && cigar[n][i]-'9' <= 0)
                            continue;
                        else
                        {
                            if (cigar[n][i]-'D' != 0)
                            {
                            	String num = new String(cigar[n], start, i - start);
                                for (int j = 0; j < Integer.parseInt(num); j++)
                                    al.add(cigar[n][i]);
                            }
                            start = i + 1;
                        }
                    }
                    Object[] array =al.toArray();
                    char[] cigarLine = new char[array.length];
                    for(int i = 0 ; i<array.length ; i++){
                    	if(array[i] instanceof Character){
                    		cigarLine[i] = (char)array[i];
                    	}
                    }
                    cigar[n]=cigarLine;
                }
            }
            
            public void getGenomicCord()
            {
                for (int i = 0; i < startPos.length; i++)
                {
                    if (cigar[i].length != seq[i].length)
                        continue;
                    int cord = startPos[i];
                    for (int j = 0; j < cigar[i].length; j++)
                    {
                        if (cigar[i][j]-'M' == 0)
                        {
                            genomicCord[i][j] = cord++;
                        }
                        //else if (cigar[i][j].CompareTo('I') == 0)
                        //{
                        //    genomicCord[i][j] = cord;
                        //}
                        else
                        {
                            genomicCord[i][j] = 0;
                        }
                    }
                }
            }
            
            public void minorStrandCovert()
            {
                for (int i = 0; i < strand.length; i++)
                {
                    if (strand[i]-'-' == 0)
                    {
                        char[] seq_R = new char[cigar[i].length];
                        char[] cigar_R = new char[cigar[i].length];
                        int[] genomicCord_R = new int[cigar[i].length];
                        for (int j = cigar[i].length - 1; j >= 0; j--)
                        {
                            seq_R[cigar[i].length - 1 - j] = (char)complementNucleotide.get(seq[i][j]);
                            cigar_R[cigar[i].length - 1 - j] = cigar[i][j];
                            genomicCord_R[cigar[i].length - 1 - j] = genomicCord[i][j];
                        }
                        seq[i] = seq_R;
                        cigar[i] = cigar_R;
                        genomicCord[i] = genomicCord_R;
                    }
                }
            }
            
            public void getBaseFlow(int hotspotCord)
            {
                for (int i = 0; i < startFlow.length; i++)
                {
                    baseFlow[i] = new int[seq[i].length];
                    int start = startFlow[i];
                    for (int j = 0; j < seq[i].length; j++)
                    {
                        for (int k = start; k < flowValue[0].length; k++)
                        {
                            if (flowOrder[k]-seq[i][j] == 0)
                            {
                                //baseFlow[i][j] = flowValue[i, k];
                                baseFlow[i][j] = k;
                                start = k;
                                if (genomicCord[i][j] == hotspotCord)
                                {
                                    hotspotFlow[i] = k;
                                    hotspotBase[i] = j;
                                }
                                break;
                            }
                        }
                    }
                }
            }
            public void Dispose()
            {
                maxFlow = 0;
                strand = null;
                startFlow = null;
                startPos = null;
                flowValue = null;
                baseFlow = null;
                genomicCord = null;
                flowOrder = null;
                qname = null;
                cigar = null;
                seq = null;
                qual = null;
                hotspotFlow = null;
                hotspotBase = null;
                System.gc();
            }
		}
		public static class AMPLICON extends Structure{
			public static class ByReference extends AMPLICON implements Structure.ByReference{}
			public int maxSeq=MAXSEQ;
			public int maxSeqLen=MAXSEQLEN;
			public int maxFlow=MAXFLOW;
			public int minFlowValue=3;
			public int maxFlowValue=7;
			public int[] qnameLen=new int[MAXSEQ];
			public int[] cigarLen=new int[MAXSEQ];
			public int[] seqLen=new int[MAXSEQ];
			public int[] startFlow=new int[MAXSEQ];
			public int[] startPos=new int[MAXSEQ];
			public int[] flowValue=new int[MAXSEQ*MAXFLOW];//二维数组 
			public int[] baseFlow=new int[MAXSEQ*MAXSEQLEN];//二维数组 
			public byte[] flowOrder=new byte[MAXFLOW];
			public byte[] qname=new byte[MAXSEQ*MAXQNAMELEN];//二维数组 
			public byte[] strand=new byte[MAXSEQ];
			public byte[] cigar=new byte[MAXSEQ*MAXCIGARLEN];//二维数组 
			public byte[] seq=new byte[MAXSEQ*MAXSEQLEN];//二维数组 
			public byte[] qual=new byte[MAXSEQ*MAXSEQLEN];//二维数组 
			@Override
			protected List getFieldOrder() {
				return Arrays.asList(new String[] {
						"maxSeq", "maxSeqLen", "maxFlow", "minFlowValue" ,"maxFlowValue"
						,"qnameLen","cigarLen","seqLen","startFlow","startPos"
						,"flowValue","baseFlow","flowOrder", "qname","strand"
						,"cigar","seq","qual"});
			}
			public void excludeEmpty(int rcount){
				maxSeq=rcount;
				int[] qnameLen_new = Arrays.copyOf(qnameLen, rcount);
				int[] cigarLen_new = Arrays.copyOf(cigarLen, rcount);
				int[] seqLen_new = Arrays.copyOf(seqLen, rcount);
				int[] startFlow_new = Arrays.copyOf(startFlow, rcount);
				int[] startPos_new = Arrays.copyOf(startPos, rcount);
				int[] flowValue_new = Arrays.copyOf(flowValue, rcount*MAXFLOW);
				int[] baseFlow_new = Arrays.copyOf(baseFlow, rcount*MAXSEQLEN);
				byte[] flowOrder_new = Arrays.copyOf(flowOrder, rcount);
				byte[] qname_new = Arrays.copyOf(qname, rcount*MAXQNAMELEN);
				byte[] strand_new = Arrays.copyOf(strand, rcount);
				byte[] cigar_new = Arrays.copyOf(cigar, rcount*MAXCIGARLEN);
				byte[] seq_new = Arrays.copyOf(seq, rcount*MAXSEQLEN);
				byte[] qual_new = Arrays.copyOf(qual, rcount*MAXSEQLEN);
				
				qnameLen = qnameLen_new; 
				cigarLen = cigarLen_new; 
				seqLen = seqLen_new; 
				startFlow = startFlow_new; 
				startPos = startPos_new; 
				flowValue = flowValue_new; 
				baseFlow = baseFlow_new; 
				flowOrder = flowOrder_new; 
				qname = qname_new; 
				strand = strand_new; 
				cigar = cigar_new; 
				seq = seq_new; 
				qual = qual_new; 
			}
		}
		public static class AMPEXP extends Structure{
			public static class ByReference extends AMPEXP implements Structure.ByReference{}
			public int minCord;
			public int maxCord;
			public int[] seqLen=new int[MAXSEQ];
			public int[] baseFlow=new int[MAXSEQ*MAXSEQLEN];//二维数组 
			public int[] coordinate=new int[MAXSEQ*MAXSEQLEN];//二维数组 
			public byte[] cigar=new byte[MAXSEQ*MAXCIGARLEN];//二维数组 
			public byte[] seq=new byte[MAXSEQ*MAXSEQLEN];//二维数组 
			public byte[] qual=new byte[MAXSEQ*MAXSEQLEN];//二维数组 
			@Override
			protected List getFieldOrder() {
				return Arrays.asList(new String[] { "minCord", "maxCord", "seqLen", "baseFlow" ,"coordinate"
						,"cigar","seq","qual"});
			}
		}
		public static class ALIGNED extends Structure{
			public int alignedLen;
			public int minFlowValue;
			public int maxFlowValue;
			public int[] alignedCord=new int[MAXFLOW];
			public int[] flowValue=new int[MAXSEQ * MAXFLOW];
			public byte[] seq=new byte[MAXSEQ * MAXFLOW];
			@Override
			protected List getFieldOrder() {
				return Arrays.asList(new String[] { "alignedLen", "minFlowValue", "maxFlowValue", "alignedCord" ,"flowValue"
						,"seq"});
			}
		}
		public int queryReadsByGenomicCoordinate(byte[] filename, String chrom, int _beg, int _end, int nflow, int _maxSeq, int _maxSeqLen, AMPLICON.ByReference Amp);
		void matchBaseToFlowValue(AMPLICON.ByReference Amp, int rcount);
		int alignShortReads(AMPLICON.ByReference Amp, AMPEXP.ByReference AmpExpanded, int rcount, int isRemoveInsert);
		BamReader.CppReader instance = (BamReader.CppReader) Native.loadLibrary("bamUtill",BamReader.CppReader.class);

	}
	
	public CppReader.Amplicon_GC readsInAmplicon(String bamfile, String chromosome, int _beg, int _end, int nflow, int _maxSeq, int _maxSeqLen, int hotspotCord)
    {

        byte[] filename=null;
		try {
			filename = bamfile.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        CppReader.AMPLICON.ByReference ampRef =  new CppReader.AMPLICON.ByReference();
        int res = CppReader.instance.queryReadsByGenomicCoordinate(filename, chromosome, _beg, _end, nflow, _maxSeq, _maxSeqLen, ampRef);
        CppReader.Amplicon_GC currentAMP = new CppReader.Amplicon_GC();
        currentAMP.excludeEmpty(ampRef, res);
        currentAMP.expandCigar();
        currentAMP.getGenomicCord();
        currentAMP.minorStrandCovert();
        currentAMP.getBaseFlow(hotspotCord);
        ampRef = null;
        System.gc();
        return currentAMP;
    }
}


