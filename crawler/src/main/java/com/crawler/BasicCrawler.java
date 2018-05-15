package com.crawler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.Save;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class BasicCrawler extends WebCrawler {
	 
	private static final Logger LOGGER = LoggerFactory.getLogger(BasicCrawler.class);
	  // 正则匹配指定的后缀文件
	  private static final Pattern FILTERS = Pattern.compile(
	      ".*(\\.(css|js|bmp|gif|jpe?g" + "|png|tiff?|mid|mp2|mp3|mp4" + "|wav|avi|mov|mpeg|ram|m4v|pdf" +
	      "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
	 
	  private String[] myCrawlDomains; // 目标爬虫域名
	 
	  /**
	   * 爬虫实例启动前调用，一般是作用是初始化一些数据
	   */
	  @Override
	  public void onStart() {
	    myCrawlDomains = (String[]) myController.getCustomData(); // 获取自定义数据 目标爬虫域名  控制器类对象里会设置的
	  }
	 
	  /**
	   * 这个方法主要是决定哪些url我们需要抓取，返回true表示是我们需要的，返回false表示不是我们需要的Url
	   * 第一个参数referringPage封装了当前爬取的页面信息
	   * 第二个参数url封装了当前爬取的页面url信息
	   */
	  @Override
	  public boolean shouldVisit(Page referringPage, WebURL url) {
	    String href = url.getURL().toLowerCase(); // 得到小写的url
	     
	    // 正则匹配，过掉一些指定后缀的文件
	    if (FILTERS.matcher(href).matches()) {
	      return false;
	    }
	 
	    // 遍历目标域名 指定目标爬虫域名
	    for (String crawlDomain : myCrawlDomains) {
	      if (href.startsWith(crawlDomain)) {
	        return true;
	      }
	    }
	 
	    return false;
	  }
	 
	  /**
	   * 当我们爬到我们需要的页面，这个方法会被调用，我们可以尽情的处理这个页面
	   * page参数封装了所有页面信息
	   */
	  @Override
	  public void visit(Page page) {
		StringBuilder title=new StringBuilder("");
		StringBuilder content=new StringBuilder("=========================================").append("\r\n");
		
		String pathname = "D:/crawl/output/"+page.getWebURL().getDocid()+".txt";
		
		title.append("docId:"+page.getWebURL().getDocid()).append("\r\n")
		.append("url:"+page.getWebURL().getURL()).append("\r\n")
		.append("上级页面docId:"+page.getWebURL().getParentDocid()).append("\r\n");
		
		if (page.getParseData() instanceof HtmlParseData) { // 判断是否是html数据 
		      HtmlParseData htmlParseData = (HtmlParseData) page.getParseData(); // 强制类型转换，获取html数据对象
		
		      title.append("纯文本长度: " + htmlParseData.getText().length()).append("\r\n")
		      .append("html长度: " +htmlParseData.getHtml().length()).append("\r\n")
		      .append("输出链接个数: " + htmlParseData.getOutgoingUrls().size()).append("\r\n");
		      content.append(htmlParseData.getText()).append("\r\n")
		      .append("=========================================").append("\r\n");
		    
		    }
		
		Save se=new Save();
		
		se.save(pathname, title.toString());
		se.save(pathname, content.toString());
		
		
		
		
		
		
		
		
		}
		
	    
	    
	 
	   
	 

	  }
