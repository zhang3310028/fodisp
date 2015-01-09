expectHotspotFlowIndex<-function(amp.seq="",flowOrder="",hotspot.pos=-1,allele=c("C","G"),amp.start,amp.end,barcode="TCAGTAAGGAGAACGAT",strand=c("+","-")){
	require("Biostrings")
	if(strand[1]=="-"){
		amp.seq<-as.character(reverseComplement(DNAString(amp.seq)))
		allele<-as.character(reverseComplement(DNAStringSet(allele)))
		hotspot.pos<-amp.end-hotspot.pos+1+nchar(barcode)
	} else{
		hotspot.pos<-hotspot.pos-amp.start+1+nchar(barcode)
	}
	
	amp.seq<-paste(barcode,amp.seq,sep="")
	amp.seq<-strsplit(amp.seq,"",fixed=T)[[1]]
	amp.seq.allele1<-amp.seq
	amp.seq.allele2<-amp.seq
	amp.seq.allele1[hotspot.pos]<-allele[1]
	amp.seq.allele2[hotspot.pos]<-allele[2]
	flowOrder<-strsplit(flowOrder,"",fixed=T)[[1]]
	flow.index.allele1<-rep(-1,length(amp.seq.allele1))
	flow.index.allele2<-rep(-1,length(amp.seq.allele2))
	start.index<-1;
	for(i in 1:length(amp.seq.allele1)){
		for(j in start.index:length(flowOrder)){
			if(flowOrder[j] == amp.seq.allele1[i]){
				flow.index.allele1[i]<-j
				start.index<-j;
				break
			}
		}
	}
	start.index<-1;
	for(i in 1:length(amp.seq.allele2)){
		for(j in start.index:length(flowOrder)){
			if(flowOrder[j] == amp.seq.allele2[i]){
				flow.index.allele2[i]<-j
				start.index<-j;
				break
			}
		}
	}
	
	c(flow.index.allele1[hotspot.pos],flow.index.allele2[hotspot.pos])
}
