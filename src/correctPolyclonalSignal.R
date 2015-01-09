clusterFlowMatrix<-function(mat,hotspotFlow,max.dist=80){
  hs<-mat[,hotspotFlow]
  eff1<-median(hs[hs[,1]>100,1])
  eff2<-median(hs[hs[,2]>100,2])
  if(!eff1%in%NA & !eff2%in%NA){
  	hs[,1]<-hs[,1]/(eff1/256)
  	hs[,2]<-hs[,2]/(eff2/256)
  }
  
  cr<-clara(hs,k=10,samples=nrow(hs))
  #cr<-clara(mat[,(min(hotspotFlow)-10):(max(hotspotFlow)+10)],k=10,samples=nrow(hs))
  centers<-matrix(c(256,0,128,128,0,256),byrow=T,nrow=3)
  #km<-kmeans(hs,centers=4)
  #dist<-apply(km$centers,1,function(x){
  #dist<-apply(centers,1,function(x){
  dist<-apply(cr$medoids,1,function(x){
  	#sqrt((hs[,1]-x[1])^2 + (hs[,2]-x[2])^2)
  	sqrt((centers[,1]-x[1])^2 + (centers[,2]-x[2])^2)
  	#sqrt((centers[,1]-x[11])^2 + (centers[,2]-x[length(x)-10])^2)
  })
  dist<-t(dist)
  cluster.center<-rep(1,10)
  for(i in 1:10){
  	if(min(dist[i,])<max.dist){
  		cluster.center[i]<-which.min(dist[i,])+1
  	}
  }
  cluster<-rep(1,nrow(hs))
  for(i in 1:10){
  	cluster[cr$clustering==i]<-cluster.center[i]
  }
  
  for(x in 1:3){
  	cluster.filter<-rep(x+1,sum(cluster==(x+1)))
  	dist<-sqrt((hs[cluster==(x+1),1]-centers[x,1])^2 + (hs[cluster==(x+1),2]-centers[x,2])^2)
  	cluster.filter[dist>max.dist]<-1
  	cluster[cluster==(x+1)]<-cluster.filter
  }
  
  #cluster<-apply(dist,2,function(x){
  #	which(x<max.dist)
  #})
  
  #cluster<-lapply(1:3,function(x){
  #	setdiff(cluster[[x]],unlist(cluster[setdiff(1:3,x)]))
  #})
  
  #col<-rep(1,nrow(hs))
  #for(i in 1:3){
  #	col[cluster[[i]]]<-i+1
  #}
#  par(mar=c(2,3,2,1))
#  par(mgp=c(1.5,0.5,0))
#  par(mai=c(0.5,0.5,0.5,0.5))
#  plot(hs[,1],hs[,2],col=cluster,xlab="Euclidean Distance",ylab="Euclidean Distance")
  cluster
}
