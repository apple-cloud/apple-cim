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
package com.appleframework.cim.sdk.server.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.appleframework.cim.sdk.server.constant.CIMConstant;
import com.appleframework.cim.sdk.server.model.proto.ReplyBodyProto;

/**
 * 请求应答对象
 *
 */
public class ReplyBody implements Serializable, Protobufable {

	private static final long serialVersionUID = 1L;

	/**
	 * 请求key
	 */
	private String key;

	/**
	 * 返回码
	 */
	private String code;

	/**
	 * 返回说明
	 */
	private String message;

	/**
	 * 返回数据集合
	 */
	private HashMap<String, String> data = new HashMap<String, String>();

	private long timestamp;

	public ReplyBody() {
		timestamp = System.currentTimeMillis();
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void put(String k, String v) {
		if (v != null && k != null) {
			data.put(k, v);
		}
	}

	public void putAll(Map<String, String> map) {
		data.putAll(map);
	}

	public String get(String k) {
		return data.get(k);
	}

	public void remove(String k) {
		data.remove(k);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Set<String> getKeySet() {
		return data.keySet();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("#ReplyBody#").append("\n");
		buffer.append("key:").append(this.getKey()).append("\n");
		buffer.append("timestamp:").append(timestamp).append("\n");
		buffer.append("code:").append(code).append("\n");

		if (!data.isEmpty()) {
			buffer.append("data{").append("\n");
			for (String key : getKeySet()) {
				buffer.append(key).append(":").append(this.get(key)).append("\n");
			}
			buffer.append("}");
		}

		return buffer.toString();
	}

	@Override
	public byte[] getByteArray() {
		ReplyBodyProto.Model.Builder builder = ReplyBodyProto.Model.newBuilder();
		builder.setCode(code);
		if (message != null) {
			builder.setMessage(message);
		}
		if (!data.isEmpty()) {
			builder.putAllData(data);
		}
		builder.setKey(key);
		builder.setTimestamp(timestamp);

		return builder.build().toByteArray();
	}

	@Override
	public byte getType() {
		// TODO Auto-generated method stub
		return CIMConstant.ProtobufType.REPLYBODY;
	}

}
