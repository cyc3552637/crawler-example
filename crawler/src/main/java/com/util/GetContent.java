package com.util;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;

public class GetContent {
	  public String getTitle(Page page){
		  StringBuilder title=new StringBuilder("");
		  title.append("docId:"+page.getWebURL().getDocid()).append("\r\n")
			.append("url:"+page.getWebURL().getURL()).append("\r\n")
			.append("上级页面docId:"+page.getWebURL().getParentDocid()).append("\r\n");
			
			if (page.getParseData() instanceof HtmlParseData) { // 判断是否是html数据 
			      HtmlParseData htmlParseData = (HtmlParseData) page.getParseData(); // 强制类型转换，获取html数据对象
			
			      title.append("纯文本长度: " + htmlParseData.getText().length()).append("\r\n")
			      .append("html长度: " +htmlParseData.getHtml().length()).append("\r\n")
			      .append("输出链接个数: " + htmlParseData.getOutgoingUrls().size()).append("\r\n");
		 		  
	  }
			return title.toString(); 
	  }
	  
	    public String getContent(Page page){
	    	StringBuilder content=new StringBuilder("=========================================").append("\r\n");
	    	if (page.getParseData() instanceof HtmlParseData) { // 判断是否是html数据 
			      HtmlParseData htmlParseData = (HtmlParseData) page.getParseData(); // 强制类型转换，获取html数据对象
			      content.append(htmlParseData.getText()).append("\r\n")
			      .append("=========================================").append("\r\n");
			
		  
	  }
	    	return content.toString();
	    }
	    
	    public String getUrl(Page page){
	    	StringBuilder htmlcontent=new StringBuilder();
	    	if (page.getParseData() instanceof HtmlParseData) { // 判断是否是html数据 
			      HtmlParseData htmlParseData = (HtmlParseData) page.getParseData(); // 强制类型转换，获取html数据对象
	               String url=htmlParseData.getOutgoingUrls().toString().replaceAll("\\[","").replaceAll("\\]","");
			      for(String result:url.split(",")){
			       if(result.substring(result.lastIndexOf(".")).equals(".html") || result.substring(result.lastIndexOf(".")).equals(".htm")){   		  
			    		  htmlcontent.append(result).append("\r\n");
			    	     }
			         
			      }
			
			    
			    }
			return htmlcontent.toString();
	    	
	    	
	    }
	    

}
