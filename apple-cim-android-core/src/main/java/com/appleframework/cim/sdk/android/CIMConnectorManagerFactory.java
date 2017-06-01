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
package com.appleframework.cim.sdk.android;

import android.content.Context;
import android.util.Log;

/**
 * 连接服务端管理，cim核心处理类，管理连接，以及消息处理
 * 
 * @author 3979434@qq.com
 */
public class CIMConnectorManagerFactory {
	
	private static final  String TAG = CIMConnectorManagerFactory.class.getSimpleName();
	
	public static String connectorManagerClazz = "com.appleframework.cim.sdk.android.CIMConnectorManagerImpl";

	public static CIMConnectorManager manager;
	
	public static void setConnectorManagerClazz(String connectorManagerClazz) {
		CIMConnectorManagerFactory.connectorManagerClazz = connectorManagerClazz;
	}
	
	public synchronized static CIMConnectorManager getManager(Context ctx) {
		if (manager == null) {
			try {
				Class<?> clazz = Class.forName(connectorManagerClazz);
				manager = (CIMConnectorManager) clazz.newInstance();
			} catch (InstantiationException e) {
    	    	Log.e(TAG, e.getMessage());
			} catch (IllegalAccessException e) {
				Log.e(TAG, e.getMessage());
			} catch (ClassNotFoundException e) {
				Log.e(TAG, e.getMessage());
			}
		}
		return manager;
	}
}
