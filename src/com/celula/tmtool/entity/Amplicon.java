package com.celula.tmtool.entity;

import com.celula.tmtool.ui.TMTool.AmpliconBedTableModel;
import com.celula.tmtool.ui.TMTool.BamviewTableModel;

public class Amplicon {
	public String chromosome;
    public int start;
    public int end;
    public String sequence;
    public String strand;
    public String getChromosome() {
		return chromosome;
	}
	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getStrand() {
		return strand;
	}
	public void setStrand(String strand) {
		this.strand = strand;
	}
		
        public static Amplicon ListViewItem2Amplicon(AmpliconBedTableModel item,int row)
        {
            Amplicon amp = new Amplicon();
            amp.chromosome = (String)item.getValueAt(row, 1);
            amp.start = (int)item.getValueAt(row, 2);
            amp.end = (int)item.getValueAt(row, 3);
            amp.sequence = (String)item.getValueAt(row, 4);
            amp.strand = (String)item.getValueAt(row, 6);
            return amp;
        }
}
