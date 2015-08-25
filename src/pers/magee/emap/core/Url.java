package pers.magee.emap.core;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import pers.magee.emap.core.util.StringUtil;

public class Url implements Serializable {

	/**
	 * 定义一个Url类，用来存储Url相关数据
	 */
	private static final long serialVersionUID = 1L;

	private final String protocol; // 通信协议,存储是FTP还是HTTPS还是HTTP等信息
	private final String username; // 登录人员用户名
	private final String password; // 登录人员的秘密
	private final String host; // 访问的域名地址
	private final int port; // 访问的端口号
	private final String path; // 访问的路径
	private final Map<String, String> parameters; // url中存放的所有参数map数据，key是参数，value是对应的值
	// volatile表示该变量是线程可控的；transient表示该变量是不能序列化的，不能被持久化
	private volatile transient String ip; // 表示访问的IP地址
	private volatile transient String parameter; // 表示url中的所有参数字符串
	
	
	public static Url get(){
		return new Url(null,null,null,null,0,null,(Map)null);
	}

	private Url() {
		this.protocol = null;
		this.username = null;
		this.password = null;
		this.host = null;
		this.port = 0;
		this.path = null;
		this.parameters = null;
	}
	
	private Url(String protocol, String username, String password, String host, int port, String path,
			Map<String, String> parameters) {

		//默认创建Url时,username和password都为空，是允许的，用户名不为空密码为空也是允许的，但是用户名为空密码不为空则不允许
		if ((username == null || username.length() == 0)&&(password!=null&&password.length()!=0)) {
			throw new IllegalArgumentException("Invalid Url,password need username");
		}
		this.protocol = protocol;
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = (port < 0 ? 0 : port);
		this.path = path;
		
		if(parameters == null){
			parameters = new HashMap();
		}else{
			parameters = new HashMap(parameters);
		}
		//Collections.unmodifiableMap是创建一个不可修改的map对象，只读，如果调用put则抛出异常
		this.parameters = Collections.unmodifiableMap(parameters);
	}

	public String getProtocol() {
		return protocol;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getPath() {
		return path;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}
	/**
	 * 提供对Url进行参数添加的方法，同时并判断传递的参数是否是成对出现的 ，如果不是则返回异常信息
	 * @param parames 成对出现的参数的字符串数组
	 * @return 根据传递的参数情况，返回对应的Url，如果传递的参数是空或跟目前的Url参数属性没有变化则返回本身，如有变化则返回全新的Url对象
	 */
	public Url addParameters(String ...parames){
		if(parames == null || parames.length==0){
			return this;
		}
		if(parames.length % 2 !=0){
			throw new IllegalArgumentException("parames size can not be odd number");
		}
		Map<String,String> map = new HashMap();
		for(int i=0;i<(parames.length/2);i++){
			map.put(parames[(i*2)], parames[(i*2+1)]);
		}
		return addParameters(map);
	}
	/**
	 * 提供对Url参数添加的方法，
	 * @param parameMap 传递参数的map对象
	 * @return 根据传递的参数情况，返回对应的Url，如果传递的参数是空或跟目前的Url参数属性没有变化则返回本身，如有变化则返回全新的Url对象
	 */
	public Url addParameters(Map<String,String> parameMap){
		if(parameMap == null || parameMap.size()==0){
			return this;
		}
		boolean hasEqual = true;
		for(Map.Entry<String, String> entry : parameMap.entrySet()){
			String value = String.valueOf(getParameters().get(entry.getKey()));
			if((value==null&&entry.getValue()!=null)||!value.equals(entry.getValue())){
				hasEqual = false;
				break;
			}
		}
		if(hasEqual){
			return this;
		}
		return new Url(this.protocol,this.username,this.password,this.host,this.port,this.path,parameMap);
	}
	/**
	 * 通过传递的Url进行Url对象的创建
	 * @param url 传递的url字符串
	 * @return 返回创建的Url对象
	 */
	public static Url creatBy(String url){
		String protocol = null;
		String username = null;
		String password = null;
		String host = null;
		int port = 0;
		String path = null;
		Map<String,String> parameters = null;
		//举例 url="http://localhost:8080/WebService/dispatcher?app.key=com.test.example"
		//先对?后面的参数进行解析，并放到parameter对象中
		int index = url.indexOf("?");
		if(index>=0){
			String [] parames = url.substring(index+1).split("\\&");
			parameters = new HashMap();
			for(String para:parames){
				if(para.trim().length()>0){
					int p = para.trim().indexOf("=");
					if(p>=0){
						parameters.put(para.substring(0,p), para.substring(p+1));
					}else{
						parameters.put(para, para);//如果参数中没有查询到=字符串，则默认把字符串即当key值又当value值进行存放
					}
				}
			}
			url=url.substring(0, index);
		}
		//url截取完后变成了http://localhost:8080/WebService/dispatcher
		//通过对://进行查找进一步过滤url数据
		index = url.indexOf("://");
		if(index>=0){
			if(index==0){
				throw new IllegalArgumentException("url miss protocol,the url is :"+url+"\n");
			}
			protocol = url.substring(0,index);
			url = url.substring(index+3);
		}
		
		//url截取完后变成了localhost:8080/WebService/dispatcher
		//通过对/进行查找进一步过滤url数据
		index = url.indexOf("/");
		if(index>=0){
			path = url.substring(index+1);
			url = url.substring(0,index);
		}
		
		//url截取完后变成了localhost:8080
		//通过对:进行查找进一步过滤url数据
		index = url.indexOf(":");
		if((index>=0) && (index<url.length()-1)){
			port = StringUtil.getInt(url.substring(index+1));
			url = url.substring(0,index);
		}
		if(url.length()>0){
			host = url;
		}
		return new Url(protocol,username,password,host,port,path,parameters);
		
	}

}
