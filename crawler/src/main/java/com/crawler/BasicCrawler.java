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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.DuplicateRemoval;
import com.util.GetContent;
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
	 
	  
	   //爬虫实例启动前调用，初始化一些数据

	  @Override
	  public void onStart() {
	    myCrawlDomains = (String[]) myController.getCustomData(); // 获取自定义数据 目标爬虫域名  控制器类对象里会设置的
	  }
	 
	 
	   // shouldVisit决定哪些url我们需要抓取，返回true表示是我们需要的，返回false表示不是我们需要的Url
	   // 利用正则过滤掉错误的url
	   // 第一个参数referringPage封装了当前爬取的页面信息
	   // 第二个参数url封装了当前爬取的页面url信息
	
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
	 
	 
	   // 当爬到需要的页面，这个方法会被调用
	   // page参数封装了所有页面信息

	  @Override
	  public void visit(Page page) {
		
		Date date = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh");  
        String dateNowStr = sdf.format(date);
		String pathname = "D:/crawl/output/"+dateNowStr+"/"+page.getWebURL().getDomain()+"/";
		String filename=page.getWebURL().getDocid()+".txt";
		
		String pathnameforurl = "D:/crawl/url/";
		String filenameforurl=page.getWebURL().getDomain()+".txt";
		GetContent getcontent=new GetContent();
		Save se=new Save();
		
		se.save(pathname,filename,getcontent.getTitle(page).toString());
		se.save(pathname,filename,getcontent.getContent(page).toString());
		se.save(pathnameforurl,filenameforurl,getcontent.getUrl(page).toString());
		
		DuplicateRemoval duplicateremoval=new DuplicateRemoval();
		duplicateremoval.removerepeat(pathnameforurl+filenameforurl);
		
		
		}
	  


		
	    
	    
	 
	   
	 

	  }

