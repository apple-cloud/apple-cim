/**
* 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ***************************************************************************************
 *                                                                                     *
 *                        Website : http://www.appleframework.com                           *
 *                                                                                     *
 ***************************************************************************************
 */
package com.appleframework.cim.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.appleframework.cim.sdk.server.model.Message;

public class MessageDispatcher {
	
	private static BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 20, TimeUnit.SECONDS, queue);
	
	final static String sendUrl = "http://%1$s:8080/cim-servier/cgi/message_send.api";

	public static void execute(final Message msg, final String ip) {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					httpPost(String.format(sendUrl, ip), msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static String httpPost(String url, Message msg) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mid", msg.getMid()));
		nvps.add(new BasicNameValuePair("extra", msg.getExtra()));
		nvps.add(new BasicNameValuePair("action", msg.getAction()));
		nvps.add(new BasicNameValuePair("title", msg.getTitle()));
		nvps.add(new BasicNameValuePair("content", msg.getContent()));
		nvps.add(new BasicNameValuePair("sender", msg.getSender()));
		nvps.add(new BasicNameValuePair("receiver", msg.getReceiver()));
		nvps.add(new BasicNameValuePair("timestamp", String.valueOf(msg.getTimestamp())));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response2 = httpclient.execute(httpPost);
		String data = null;
		try {
			System.out.println(response2.getStatusLine());
			HttpEntity entity2 = response2.getEntity();
			data = EntityUtils.toString(entity2);
		} finally {
			response2.close();
		}
		return data;
	}

}
