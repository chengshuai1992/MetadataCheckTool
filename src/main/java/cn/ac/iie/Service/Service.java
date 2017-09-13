package cn.ac.iie.Service;
import java.io.IOException;
import java.nio.file.Paths;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.DocValues;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.LeafReader;
import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.index.NumericDocValues;
import org.apache.lucene.search.DocValuesStats;
import org.apache.lucene.search.DocValuesStats.LongDocValuesStats;
import org.apache.lucene.search.DocValuesStats.NumericDocValuesStats;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Bits;

public class Service 

{  
    public static void main( String[] args ) throws IOException
    {    String s="m_insert_time";
         String ss="m_publish_time";
         long startTime = System.currentTimeMillis();
      //   System.out.println(startTime);
       	 max ms=new max(s);
       	 ms.max_min();	
       	 long endTime = System.currentTimeMillis();
       	 System.out.println(endTime-startTime+"ms");
    }  
}
class max{
	String s;
	max(String s){
		this.s=s;
	}
	public void max_min() throws IOException{
		  DirectoryReader reader =DirectoryReader.open(FSDirectory.open(Paths.get("E:/Java Program/in/index")));
	  	   int k=0;
	  	   LeafReader  atomicReader;
	  	 
	  	  int m=0;
	  	   long max=Long.MIN_VALUE,min=Long.MAX_VALUE;
	  	   for(int i=0;i<reader.leaves().size();i++){
	  		 m=m+1;
	  		 atomicReader= reader.leaves().get(i).reader();
	  		 Bits bits=atomicReader.getLiveDocs();
	  		 NumericDocValues str = DocValues.getNumeric((LeafReader)atomicReader,s); 		
	  		 for(int j=0;j<atomicReader.maxDoc();j++){
	  			   if(bits!=null &&bits.get(j)==false){
	  				   System.out.println(j+" "+str.get(j)+" "+i);
	  				   continue;
	  			   }
	  			 
	  			   if(str.get(j)<min){
	  				   min=str.get(j);  				   
	  			   }
	  			   if(str.get(j)>max){
	  				   max=str.get(j);			   
	  			   }
	  			k=k+1;
	  		   }
	  	   }
	  	   System.out.println(m+" "+k);
	  	   System.out.println("min:"+min+"\n"+"max:"+max);  
	  	   //---------------------------------------------
	  	   
	  	    	
	}
}

