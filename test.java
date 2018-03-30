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
		//	webservice ws = new webservice("GetServiceTime",null,null);
		//	System.out.println("result:"+webservice.getOME("SignIn",null,null));
		//	System.out.println("result:"+webservice.getOME("GetServiceTime",null,null));
			//webservice ws2 = new webservice("GetAccClass",null,null);
		//	System.out.println("result:"+webservice.getOME("GetAccClass",null,null));
		//	System.out.println("10进制转为16进制:"+Decimal2HexConversion.decimalToHex(1101122255));
			/*ArrayList arrayList = new ArrayList();
			arrayList.add("1");
			arrayList.add("2");
			arrayList.add("3");
			arrayList.add("4");
			String[] a = {"1","2","3","4"};
			Collections.reverse(arrayList);
			System.out.println("集合反转:"+arrayList);
			reverseArray2(a);
			 for (int j = 0; j < a.length; j++) { 
			      System.out.print(a[j] + " "); 
			    } */
			OMElement result = webservice.getOME("GetEWallet",null,null);
			System.out.println("result:"+result);
			 a = webservice.getResults(result); 
			 for(int i=0;i<a.size();i++){
				 System.out.println("获得返回值:"+a.get(i));
			 }
			
		}
		
		
		 private static String[] reverseArray2(String[] Array) { 
			    String[] new_array = new String[Array.length]; 
			    for (int i = 0; i < Array.length; i++) { 
			      // 反转后数组的第一个元素等于源数组的最后一个元素： 
			      new_array[i] = Array[Array.length - i - 1]; 
			    } 
			    return new_array; 
			  } 
}
