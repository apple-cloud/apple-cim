package com.appleframework.cim.sdk.client;

import com.appleframework.cim.sdk.client.CIMEventBroadcastReceiver;
import com.appleframework.cim.sdk.client.CIMEventListener;
import com.appleframework.cim.sdk.client.CIMPushManager;
import com.appleframework.cim.sdk.client.model.Message;
import com.appleframework.cim.sdk.client.model.ReplyBody;

public class CIMJavaClient implements CIMEventListener {

	public static String UID = "10000";

	public static void startup() {
		/**
		 * 第一步 设置运行时参数
		 */
		CIMPushManager.setClientVersion("2.0.0");// 客户端程序版本
		CIMPushManager.setDeviceModel("pc");// 设备型号名称
		// CIMPushManager.setAccount("10000");// IUID

		/**
		 * 第二步 设置全局的事件监听器
		 */
		CIMEventBroadcastReceiver.getInstance().setGlobalCIMEventListener(new CIMJavaClient());

		/**
		 * 第三步 连接到服务器
		 */
		CIMPushManager.connect("127.0.0.1", 9001);

	}

	@Override
	public void onConnectionClosed() {
		System.out.println("onConnectionClosed");
		/**
		 * 在此可以将事件分发到各个监听了CIMEventBroadcastReceiver的地方 第一步 连接到服务器
		 * 在需要监听事件的类调用CIMListenerManager.registerMessageListener(listener); 第二部
		 * 在此调用CIMListenerManager.notifyOnConnectionClosed()
		 */
	}

	@Override
	public void onConnectionFailed(Exception e) {
		System.out.println("onConnectionFailed");
		/**
		 * 在此可以将事件分发到各个监听了CIMEventBroadcastReceiver的地方 第一步 连接到服务器
		 * 在需要监听事件的类调用CIMListenerManager.registerMessageListener(listener); 第二部
		 * 在此调用CIMListenerManager.notifyOnConnectionFailed(e)
		 */
	}

	@Override
	public void onConnectionSuccessed(boolean hasAutoBind) {
		System.out.println("onConnectionSuccessed");
		if (!hasAutoBind) {
			CIMPushManager.bindAccount(UID);
		}
		/**
		 * 在此可以将事件分发到各个监听了CIMEventBroadcastReceiver的地方 第一步 连接到服务器
		 * 在需要监听事件的类调用CIMListenerManager.registerMessageListener(listener); 第二部
		 * 在此调用CIMListenerManager.notifyOnConnectionSuccessed(hasAutoBind)
		 */
	}

	@Override
	public void onMessageReceived(Message message) {
		System.out.println(message.toString());
		/**
		 * 在此可以将事件分发到各个监听了CIMEventBroadcastReceiver的地方 第一步 连接到服务器
		 * 在需要监听事件的类调用CIMListenerManager.registerMessageListener(listener); 第二部
		 * 在此调用CIMListenerManager.notifyOnMessageReceived(message)
		 */
	}

	@Override
	public void onReplyReceived(ReplyBody replybody) {
		System.out.println(replybody.toString());
		/**
		 * 在此可以将事件分发到各个监听了CIMEventBroadcastReceiver的地方 第一步 连接到服务器
		 * 在需要监听事件的类调用CIMListenerManager.registerMessageListener(listener); 第二部
		 * 在此调用CIMListenerManager.notifyOnReplyReceived(replybody)
		 */
	}

	public static void main(String[] a) {
		startup();
	}

	@Override
	public int getEventDispatchOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
