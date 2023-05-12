package com.zheng.spider.htmlUnit;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.htmlunit.BrowserVersion;
import org.htmlunit.FailingHttpStatusCodeException;
import org.htmlunit.ProxyConfig;
import org.htmlunit.SilentCssErrorHandler;
import org.htmlunit.WebClient;
import org.htmlunit.html.DomNode;
import org.htmlunit.html.HtmlAnchor;
import org.htmlunit.html.HtmlButton;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlTextInput;

public class HtmlUnitExample {
	
    // define usage of firefox, chrome or Edge
    private static final WebClient webClient = new WebClient(BrowserVersion.CHROME);

    public static void main(String[] args) {
        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setTimeout(2000);
        webClient.getOptions().setUseInsecureSSL(true);
        // overcome problems in JavaScript
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);
        webClient.setCssErrorHandler(new SilentCssErrorHandler());
        
        /**使用代理
         * java -Dhttp.proxyHost=192.168.0.0 -Dhttp.proxyPort=4711 -jar myApp.ja
        final String proxyHost = System.getProperty("http.proxyHost");
        final String proxyPort = System.getProperty("http.proxyPort");
        ProxyConfig proxyConfig = new ProxyConfig(proxyHost, Integer.parseInt(proxyPort),null);
        webClient.getOptions().setProxyConfig(proxyConfig);
        ***/
        
        try {
            final HtmlPage page = webClient
                    .getPage("https://www.innoq.com/en/search/");
            final HtmlTextInput searchField = htmlElementById(page,
                    "q",
                    HtmlTextInput.class).get();
            // alternative to XPath: querySelector(".search-form__btn")
            final HtmlButton searchButton = htmlElementByXPath(page,
            		"//button[@class='search-form__btn' and @type='submit']",
            		HtmlButton.class).get();
            searchField.setValueAttribute("Scraping");

            final HtmlPage resultPage = searchButton.click();
            // in real life you may use LOGGER.debug()
            //  System.out.println("HTML source: " + resultPage.getWebResponse().getContentAsString());
            htmlElementsByCssClass(resultPage, ".search-result", HtmlAnchor.class).stream().forEach(System.out::println);
        } catch (FailingHttpStatusCodeException | IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> Optional<T> htmlElementByXPath(
           DomNode node,
           String xpath,
           Class<T> type) {
        return node.getByXPath(xpath).stream()
               .filter(o->type.isAssignableFrom(o.getClass()))
               .map(type::cast).findFirst();
    }

    public static <T> List<T> htmlElementsByCssClass(
    		DomNode node,
    		String cssClass,
    		Class<T> type) {
    	return node.querySelectorAll(cssClass).stream()
	        .filter(o->type.isAssignableFrom(o.getClass()))
	        .map(type::cast).collect(Collectors.toList());
    }

    public static <T> Optional<T> htmlElementById(
    		HtmlPage node,
    		String htmlTagId,
    		Class<T> type) {
    	return Optional.ofNullable(node.getElementById(htmlTagId)).map(type::cast);
    }
}