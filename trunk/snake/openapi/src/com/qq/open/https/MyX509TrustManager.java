package com.qq.open.https;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 自定义签名证书管理类 (接受任意来源证书)
 * 
 * @author jack
 * 
 */
public class MyX509TrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

	}

	public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}
