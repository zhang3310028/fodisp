package com.celula.tmtool.entity;

public class Hotspot {
    public String chromosome;
    public String getChromosome() {
		return chromosome;
	}
	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getRefAllele() {
		return refAllele;
	}
	public void setRefAllele(String refAllele) {
		this.refAllele = refAllele;
	}
	public String getAltAllele() {
		return altAllele;
	}
	public void setAltAllele(String altAllele) {
		this.altAllele = altAllele;
	}
	public int position;
    public String refAllele;
    public String altAllele;
//    public static Hotspot ListViewItem2Hotspot(ListViewItem item)
//    {
//        Hotspot hs = new Hotspot();
//        hs.chromosome = item.Text;
//        hs.position = Convert.ToInt32(item.SubItems[1].Text);
//        hs.refAllele = item.SubItems[2].Text;
//        hs.altAllele = item.SubItems[3].Text;
//        return hs;
//    }
}
