package daxigua.Util;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.ByteOrderMarkDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.crypto.spec.PSource;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


/**
 * 
 * @author Dyh
 *This is a util to get charset of file/stream and etc.
 */
public class CommUtil {
	
	public static String getFileEncoding(File file){
		
        /*

         * cpDetector是探测器，它把探测任务交给具体的探测实现类的实例完成。

         * cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法 加进来，如ParsingDetector、ByteOrderMarkDetector、JChardetFacade、ASCIIDetector、UnicodeDetector。

         * cpDetector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的字符集编码。cpDetector是基于统计学原理的，不保证完全正确。

         */

        CodepageDetectorProxy codepageDetector = CodepageDetectorProxy.getInstance();
        //ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于指示是否显示探测过程的详细信息，为false不显示。
        codepageDetector.add(new ParsingDetector(false));
        //JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码 测定。
        //所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以再多加几个探测器.
        //比如下面的ASCIIDetector、UnicodeDetector等。
        codepageDetector.add(JChardetFacade.getInstance());        
        codepageDetector.add(new ByteOrderMarkDetector());  
        codepageDetector.add(ASCIIDetector.getInstance());
        //ASCIIDetector用于ASCII编码测定
        codepageDetector.add(UnicodeDetector.getInstance());
        //UnicodeDetector用于Unicode家族编码的测定

        Charset charset = null;

        try {

            charset = codepageDetector.detectCodepage(file.toURI().toURL());

            if (charset != null){

                return charset.name();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
	}
	
	public static String getURLFileEncode(String str) {

		 

        /*

         * cpDetector是探测器，它把探测任务交给具体的探测实现类的实例完成。

         * cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法 加进来，如ParsingDetector、ByteOrderMarkDetector、JChardetFacade、ASCIIDetector、UnicodeDetector。

         * cpDetector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的字符集编码。cpDetector是基于统计学原理的，不保证完全正确。

         */

        CodepageDetectorProxy codepageDetector = CodepageDetectorProxy.getInstance();

        codepageDetector.add(new ParsingDetector(false));//ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于指示是否显示探测过程的详细信息，为false不显示。

        codepageDetector.add(JChardetFacade.getInstance());//JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。

        codepageDetector.add(ASCIIDetector.getInstance());//ASCIIDetector用于ASCII编码测定

        codepageDetector.add(UnicodeDetector.getInstance());//UnicodeDetector用于Unicode家族编码的测定

        Charset charset = null;

        try {
        	URL url = new URL(str);
            charset = codepageDetector.detectCodepage(url);

            if (charset != null){
            	System.out.println("charset->"+charset.name());
                return charset.name();
                
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }
	
	public static String getInputStreamEncode(InputStream inputStream) {

		 

        /*

         * cpDetector是探测器，它把探测任务交给具体的探测实现类的实例完成。

         * cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法 加进来，如ParsingDetector、ByteOrderMarkDetector、JChardetFacade、ASCIIDetector、UnicodeDetector。

         * cpDetector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的字符集编码。cpDetector是基于统计学原理的，不保证完全正确。

         */

        CodepageDetectorProxy codepageDetector = CodepageDetectorProxy.getInstance();

        codepageDetector.add(new ParsingDetector(false));//ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于指示是否显示探测过程的详细信息，为false不显示。

        codepageDetector.add(JChardetFacade.getInstance());//JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。

        codepageDetector.add(ASCIIDetector.getInstance());//ASCIIDetector用于ASCII编码测定

        codepageDetector.add(UnicodeDetector.getInstance());//UnicodeDetector用于Unicode家族编码的测定

        Charset charset = null;

        try {

            charset = codepageDetector.detectCodepage(inputStream, 0);

            if (charset != null){

                return charset.name();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }
	
	public static String getStringEncode(String stringValue) {

		 

        /*

         * cpDetector是探测器，它把探测任务交给具体的探测实现类的实例完成。

         * cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法 加进来，如ParsingDetector、ByteOrderMarkDetector、JChardetFacade、ASCIIDetector、UnicodeDetector。

         * cpDetector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的字符集编码。cpDetector是基于统计学原理的，不保证完全正确。

         */

        CodepageDetectorProxy codepageDetector = CodepageDetectorProxy.getInstance();

        codepageDetector.add(new ParsingDetector(false));//ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于指示是否显示探测过程的详细信息，为false不显示。

        codepageDetector.add(JChardetFacade.getInstance());//JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。

        codepageDetector.add(ASCIIDetector.getInstance());//ASCIIDetector用于ASCII编码测定

        codepageDetector.add(UnicodeDetector.getInstance());//UnicodeDetector用于Unicode家族编码的测定

        Charset charset = null;

        try {

            InputStream inputStream = new ByteArrayInputStream(stringValue.getBytes());

            charset = codepageDetector.detectCodepage(inputStream, 3);

            if (charset != null){

                return charset.name();

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }
	
	public static InputStream getInputStream(String str){
		InputStream inputStream = null;
		
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(6000);
		GetMethod getMethod = new GetMethod(str);
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 6000);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
				new DefaultHttpMethodRetryHandler());
		
		int statusCode;
		try {
			statusCode = client.executeMethod(getMethod);
			
			if (statusCode == HttpStatus.SC_OK){

				System.out.println("get->ok!");
				byte[] resp = getMethod.getResponseBody();
				inputStream = new ByteArrayInputStream(resp);
			}
				
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			getMethod.releaseConnection();
		}
		if (inputStream != null)
			System.out.println("inputstream is not null");
			
		else
			System.out.println("inputstream is null");
		
		return inputStream;
	}
	
	public static void saveTolocal(InputStream inputStream){
		String path = "D:\\url.txt";
		int ch = 0;
		byte[] bytes = new byte[3*1024];
		OutputStream out = null;
		try {
			out = new FileOutputStream(new File(path));
			 while((ch=inputStream.read(bytes))!= -1){
			    	out.write(bytes,0,ch);
			 }
			 out.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("saving is ok!");
			}
		}
	   
	   
	}

	public static void clientPost(String url) {
        //String httpUrl= urlprasUtil.getUrl_head(url);
        String httpUrl = "https://detail.tmall.com/item.htm";
        
        Map<String, String> params = urlprasUtil.getParams(url);
        List<List<String>> list = new ArrayList<List<String>>();
        
        Iterator<Entry<String, String>> i = params.entrySet().iterator();
        while (i.hasNext()) {
        	Entry<String, String> en = i.next();
			List<String> lists = new ArrayList<String>();
			lists.add(en.getKey());
			lists.add(en.getValue());
			list.add(lists);
		}
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(httpUrl);

        NameValuePair[] orderInfo = {new NameValuePair(list.get(0).get(0), list.get(0).get(1)),
        		new NameValuePair(list.get(1).get(0), list.get(1).get(1)),
        		//new NameValuePair(list.get(2).get(0), list.get(2).get(1))
        		};
        
        postMethod.setRequestBody(orderInfo);
        try {
				client.executeMethod(postMethod);
			
			//int i2 = postMethod.getStatusCode();
			//if (i2 == HttpStatus.SC_OK) {
				
				InputStream is = postMethod.getResponseBodyAsStream();
				System.out.println(is==null);
				saveTolocal(is);
				//}
			} catch (HttpException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			
			
	
	
	}





}



