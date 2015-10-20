package pers.magee.emap.core.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;

public class HttpUtil {
	
	/**
	 * 通过java后台进行URL的模拟浏览器请求操作
	 * @param url 需要访问的URL链接
	 * @param str 传递到Session里的字符串内容
	 * @return 
	 */
	public static String requestPostByHttp(String url,String str){
		HttpURLConnection conn = null;
		BufferedReader br = null;
		OutputStream ops = null;
		ByteArrayInputStream bis = null;
		byte[] write = new byte[1024];
		try{
			URL postUrl = new URL(StringUtil.getUrlEncode(url));
			conn = (HttpURLConnection)postUrl.openConnection();  //此时的con只是为一个连接对象，待连接中
			conn.setDoOutput(true);	//设置连接输出流为true，默认为false{POST请求是以流的方式隐式的传递参数，比GET要安全}
			conn.setDoInput(true);	//设置连接输入流为true
			conn.setRequestMethod("POST");//设置请求方式为POST
			conn.setUseCaches(false);	//POST请求缓存设为false
			conn.setRequestProperty("Content-Type", "application/octet-stream");//设置请求头里面的各个属性，这里主要设置内容的类型
			
			//conn.setAllowUserInteraction(true);
		    //conn.setChunkedStreamingMode(0);
						
			conn.connect();//建立连接
			ops = conn.getOutputStream();//创建输入输出流，用于往连接里面输出携带的参数，内容为str
			bis = new ByteArrayInputStream(str.getBytes("UTF-8"));
			int rc = -1;
			while((rc = bis.read(write))!=-1){
				ops.write(write,0,rc);
			}
			ops.flush();//输出完成后刷新
			
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));//连接发起请求，处理服务器响应（从连接获取到输入流并包装为BufferedReader）
			String line=null;
			StringBuffer sb = new StringBuffer();//用来存储响应数据
			// 循环读取流,若不到结尾处
			while((line=br.readLine())!=null){
				sb.append(line);
			}
			
			//关闭相关的连接和流数据
			ops.close();
			br.close();
			bis.close();
			conn.disconnect();
			
			return sb.toString();
		}
		catch(Exception e){
			System.out.println("无法连接！");
		}

		return null;
	}
}
