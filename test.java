package hello;
import hello.webservice;
import utils.Decimal2HexConversion;
import utils.XMLUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.axiom.om.OMElement;
public class test {
	static List<String> a = new ArrayList();
		public static void main(String[] args) {
			
			String[] key = {"ThirdType","Secret1","Secret2"};
			String[] value = {"1","1001","1001"};
		
			OMElement result = webservice.getOME("GetEWallet",null,null);
			System.out.println("result:"+result);
			 a = webservice.getResults(result); 
			 for(int i=0;i<a.size();i++){
				 System.out.println("获得返回值:"+a.get(i));
			 }
			
		}
		
		
}
