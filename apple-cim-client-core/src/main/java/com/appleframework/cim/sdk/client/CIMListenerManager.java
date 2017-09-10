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
package com.appleframework.cim.sdk.client;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.log4j.Logger;

import com.appleframework.cim.sdk.client.model.Message;
import com.appleframework.cim.sdk.client.model.ReplyBody;

 
/**
 * CIM 消息监听器管理
 */
public class CIMListenerManager  {

	private static ArrayList<CIMEventListener> cimListeners = new ArrayList<CIMEventListener>();
	private static CIMMessageReceiveComparator comparator = new CIMMessageReceiveComparator();
	protected static final Logger logger = Logger.getLogger(CIMListenerManager.class);


	public static void registerMessageListener(CIMEventListener listener) {
		if (!cimListeners.contains(listener)) {
			cimListeners.add(listener);
			Collections.sort(cimListeners,comparator);
		}
	}

	
	public static void removeMessageListener(CIMEventListener listener) {
		for (int i = 0; i < cimListeners.size(); i++) {
			if (listener.getClass() == cimListeners.get(i).getClass()) {
				cimListeners.remove(i);
			}
		}
	}
	
	public static void notifyOnConnectionSuccessed(boolean antoBind) {
		for (CIMEventListener listener : cimListeners) {
			listener.onConnectionSuccessed(antoBind);
		}
	}
	
	public static void notifyOnMessageReceived(Message message) {
		for (CIMEventListener listener : cimListeners) {
			listener.onMessageReceived(message);
		}
	}
	
	public static void notifyOnConnectionClosed() {
		for (CIMEventListener listener : cimListeners) {
			listener.onConnectionClosed();
		}
	}
	
	
	public static void notifyOnReplyReceived(ReplyBody body) {
		for (CIMEventListener listener : cimListeners) {
			listener.onReplyReceived(body);
		}
	}
	
	public static void notifyOnConnectionFailed(Exception e) {
		for (CIMEventListener listener : cimListeners) {
			listener.onConnectionFailed(e);
		}
	}
	
	public static void destory() {
		cimListeners.clear();
	}
	
	public static void logListenersName() {
		for (CIMEventListener listener : cimListeners) {
			logger.debug("#######" + listener.getClass().getName() + "#######" );
		}
	}
	
	/**
	 * 消息接收activity的接收顺序排序，CIM_RECEIVE_ORDER倒序
	 */
   private static class CIMMessageReceiveComparator  implements Comparator<CIMEventListener>{
		@Override
		public int compare(CIMEventListener arg1, CIMEventListener arg2) {
			 
			int order1 = arg1.getEventDispatchOrder();
			int order2 = arg2.getEventDispatchOrder();
			return order2 - order1 ;
		}

	}

 
}
