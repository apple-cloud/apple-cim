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
package com.appleframework.cim.sdk.server.constant;

/**
 * 常量
 */
public interface CIMConstant {

	// 消息头长度为3个字节，第一个字节为消息类型，第二，第三字节 转换int后为消息长度
	int DATA_HEADER_LENGTH = 3;

	public static interface ReturnCode {
		String CODE_200 = "200";
		String CODE_404 = "404";
		String CODE_403 = "403";
		String CODE_500 = "500";
	}

	String SESSION_KEY = "account";
	String HEARTBEAT_KEY = "heartbeat";
	String CLIENT_HEARTBEAT ="client_heartbeat";

	public static interface ProtobufType {
		byte C_H_RS    = 0;
		byte S_H_RQ    = 1;
		byte MESSAGE   = 2;
		byte SENTBODY  = 3;
		byte REPLYBODY = 4;
	}

	public static interface MessageAction {
		// 被其他设备登录挤下线消息
		String ACTION_999 = "999";
		// 被系统禁用消息
		String ACTION_444 = "444";
	}

}
