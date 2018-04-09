package com.web.frame.webservice.ws;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;


public class webservice { 
	  private static String url = "http://114.115.178.10:1258/ThirdWebservice.asmx"; 
	  private static String namespace = "http://www.hzsun.com/";
	  //端点引用 指接口位置  
      private static EndpointReference targetEpr = new EndpointReference(url);  
      //有抽象OM工厂获取OM工厂，创建request SOAP包  
      private static OMFactory fac = OMAbstractFactory.getOMFactory();  
      
	  static String action  = "";  
      static String methodStr = "";  
      static String tns = "";  
      static String[] pars = null;  
      static String[] vals = null;  
      OMElement result = null;  
	
	
	
	public static OMElement getOME(String act,String[] pars1,String[] vals1){
		action  = namespace + act;  
        methodStr = act;  
        tns = act;  
        pars = pars1;  
        vals = vals1; 
		System.out.println("action:"+action);
		System.out.println("methodStr:"+methodStr);
		System.out.println("namespace:"+namespace);
		System.out.println("tns:"+tns);
		System.out.println("pars:"+pars);
		System.out.println("vals:"+vals);
		return getOMElement(action, methodStr, namespace, tns, pars, vals);
	}
	
	
	
	  
      
         
       public static OMElement getOMMethod(String methodStr,String namespace,String tns,String[] pars,String[] vals){  
           //创建命名空间  
           OMNamespace nms = fac.createOMNamespace(namespace, tns);  
           //创建OMElement方法 元素，并指定其在nms指代的名称空间中  
           OMElement method = fac.createOMElement(methodStr, nms);  
           //添加方法参数名和参数值  
           if(pars!=null){
        	   for(int i=0;i<pars.length;i++){  
                   //创建方法参数OMElement元素  
                   OMElement param = fac.createOMElement(pars[i],nms);  
                   //设置键值对 参数值  
                   param.setText(vals[i]);  
                   //讲方法元素 添加到method方法元素中  
                   method.addChild(param);  
               }  
           }
           
           return method;  
       }  
         
       
       
       public static Options getClientOptions(String action){  
           //创建request soap包 请求选项  
           Options options = new Options();  
           //设置options的soapAction   
           options.setAction(action);  
           //设置request soap包的端点引用(接口地址)  
           options.setTo(targetEpr);  
           //如果报错提示Content-Length，请求内容长度  
           options.setProperty(HTTPConstants.CHUNKED,"false");//把chunk关掉后，会自动加上Content-Length。  
           return options;  
 
       }  
 
 
       public static OMElement getOMElement(String action,String methodStr,String namespace,String tns,String[] pars,String[] vals){  
           OMElement result = null;  
           try {  
               ServiceClient client = new ServiceClient(); 
               addValidation(client);
               client.setOptions(getClientOptions(action));  
               result =  client.sendReceive(getOMMethod(methodStr,namespace,tns,pars,vals));  
           } catch (AxisFault e) {  
               // TODO Auto-generated catch block  
               e.printStackTrace();  
           }  
           return result;  
 
       }  
 
      
       
       
   
    //解析返回的结果
    public static List<String> getResults(OMElement element) {
        if (element == null) {
            return null;
        }
        System.out.println("IN: " + element); // 新增
        Iterator iterator = element.getChildElements();
        Iterator innerItr;
        List<String> list = new ArrayList<String>();
        OMElement result = null;
        while (iterator.hasNext()) {
            result = (OMElement) iterator.next();
         //   System.out.println("While: " + result); // 新增
            innerItr = result.getChildElements();
            System.out.println("返回值---" + result.getLocalName() + ": " + result.getText());  // 新增
           /* while (innerItr.hasNext()) {  // 新增
            		OMElement elem = (OMElement)innerItr.next();  // 新增
            		System.out.println("\tWhile: "+elem);  // 新增
            		System.out.println("\t\t" + elem.getLocalName() + ": " + elem.getText());  // 新增
            } // 新增
*/        }
        	return list;
    }
     
    //添加头部信息	
     public static void addValidation(ServiceClient serviceClient) {  
         OMFactory fac = OMAbstractFactory.getOMFactory();  
         OMNamespace omNs = fac.createOMNamespace(namespace,"SecurityHeader");  
         OMElement header = fac.createOMElement("SecurityHeader", omNs); 
         OMElement ThirdType = fac.createOMElement("ThirdType", omNs);  
         OMElement Secret1 = fac.createOMElement("Secret1", omNs); 
         OMElement Secret2 = fac.createOMElement("Secret2", omNs);  
      
         
         ThirdType.setText("306");
         Secret1.setText("2066558");
         Secret2.setText("7b1sxwFNuTrdEQO742pQHw==");
      
         
         header.addChild(ThirdType);  
         header.addChild(Secret1);
         header.addChild(Secret2);
         header.build();
         
         System.out.println("header:" + header.toString());  
         serviceClient.addHeader(header);  
      
     } 
} 
