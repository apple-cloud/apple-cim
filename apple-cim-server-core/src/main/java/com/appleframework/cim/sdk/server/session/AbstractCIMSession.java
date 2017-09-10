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
package com.appleframework.cim.sdk.server.session;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import com.appleframework.cim.sdk.server.constant.CIMConstant;

/**
 * IoSession包装类,集群时 将此对象存入表中
 */

public abstract class AbstractCIMSession implements Serializable {

	private transient static final long serialVersionUID = 1L;
	
	public transient static String ID = "ID";
	public transient static String HOST = "HOST";
	public transient static final int STATUS_ENABLED = 0;
	public transient static final int STATUS_DISABLED = 1;
	public transient static final int APNS_ON = 1;
	public transient static final int APNS_OFF = 0;

	public transient static String CHANNEL_IOS = "ios";
	public transient static String CHANNEL_ANDROID = "android";
	public transient static String CHANNEL_WINDOWS = "windows";
	public transient static String CHANNEL_WP = "wp";

	protected String gid;// session全局ID
	protected String nid;// session在本台服务器上的ID
	protected String deviceId;// 客户端ID (设备号码+应用包名),ios为devicetoken
	protected String host;// session绑定的服务器IP
	protected String account;// session绑定的账号
	protected String channel;// 终端设备类型
	protected String deviceModel;// 终端设备型号
	protected String clientVersion;// 终端应用版本
	protected String systemVersion;// 终端系统版本
	protected String packageName;// 终端应用包名
	protected Long bindTime;// 登录时间
	protected Long heartbeat;// 心跳时间
	protected String longitude;// 经度
	protected String latitude;// 维度
	protected String location;// 位置
	protected int apnsAble;// apns推送状态
	protected int status;// 状态
	protected String clientIp;
	
	public String getAccount() {
		if(null == account)
			account = (String)getAttribute(CIMConstant.SESSION_KEY);
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
		setAttribute(CIMConstant.SESSION_KEY, account);
	}

	public String getLongitude() {
		if(null == longitude)
			longitude = (String)getAttribute("longitude");
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		if(null == latitude)
			latitude = (String)getAttribute("latitude");
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLocation() {
		if(null == location)
			location = (String)getAttribute("location");
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getGid() {
		if(null == gid)
			gid = (String)getAttribute("gid");
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
		setAttribute("gid", gid);
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getDeviceId() {
		if(null == deviceId)
			deviceId = (String)getAttribute("deviceId");
		return deviceId;
	}

	public String getChannel() {
		if(null == channel)
			channel = (String)getAttribute("channel");
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
		setAttribute("channel", channel);
	}

	public String getDeviceModel() {
		if(null == deviceModel)
			deviceModel = (String)getAttribute("deviceModel");
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
		setAttribute("deviceModel", deviceModel);
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
		setAttribute("deviceId", deviceId);
	}

	public String getHost() {
		if(null == host)
			host = (String)getAttribute("host");
		return host;
	}

	public Long getBindTime() {
		return bindTime;
	}

	public void setBindTime(Long bindTime) {
		this.bindTime = bindTime;
		setAttribute("bindTime", bindTime);
	}

	public String getClientVersion() {
		if(null == clientVersion)
			clientVersion = (String)getAttribute("clientVersion");
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
		setAttribute("clientVersion", clientVersion);
	}

	public String getSystemVersion() {
		if(null == systemVersion)
			systemVersion = (String)getAttribute("systemVersion");
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
		setAttribute("systemVersion", systemVersion);
	}

	public Long getHeartbeat() {
		if(null == heartbeat)
			heartbeat = (Long)getAttribute(CIMConstant.HEARTBEAT_KEY);
		return heartbeat;
	}

	public void setHeartbeat(Long heartbeat) {
		this.heartbeat = heartbeat;
		setAttribute(CIMConstant.HEARTBEAT_KEY, heartbeat);
	}

	public void setHost(String host) {
		this.host = host;
		setAttribute("host", host);
	}

	public int getApnsAble() {
		return apnsAble;
	}

	public void setApnsAble(int apnsAble) {
		this.apnsAble = apnsAble;
		setAttribute("apnsAble", apnsAble);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		setAttribute("status", status);
	}

	public String getClientIp() {
		if(null == clientIp)
			clientIp = (String)getAttribute("clientIp");
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
		setAttribute("clientIp", clientIp);
	}

	public abstract void setAttribute(String key, Object value);

	public abstract boolean containsAttribute(String key);

	public abstract Object getAttribute(String key);

	public abstract void removeAttribute(String key);

	public abstract SocketAddress getRemoteAddress();

	public abstract boolean write(Object msg);

	public abstract boolean isConnected();

	public boolean isLocalhost() {
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			return ip.equals(host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return false;
	}

	public abstract void closeNow();

	public abstract void closeOnFlush();
	
	public abstract void setSession(Object session);
	
	public abstract String getRemoteIp();

	public void setPackageName(String packageName) {
		this.packageName = packageName;
		setAttribute("packageName", packageName);
	}

	public String getPackageName() {
		if(null == packageName)
			packageName = (String)getAttribute("packageName");
		return packageName;
	}

	public int hashCode() {
		return (deviceId + nid + host).hashCode();
	}

	public boolean equals(Object o) {
		if (o instanceof AbstractCIMSession) {
			return hashCode() == o.hashCode();
		}
		return false;
	}

	public boolean fromOtherDevice(Object o) {
		if (o instanceof AbstractCIMSession) {
			AbstractCIMSession t = (AbstractCIMSession) o;
			if (t.deviceId != null && deviceId != null) {
				return !t.deviceId.equals(deviceId);
			}
		}
		return false;
	}

	public boolean fromCurrentDevice(Object o) {
		return !fromOtherDevice(o);
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		buffer.append("\"").append("gid").append("\":").append("\"").append(gid).append("\"").append(",");
		buffer.append("\"").append("nid").append("\":").append(nid).append(",");
		buffer.append("\"").append("deviceId").append("\":").append("\"").append(deviceId).append("\"").append(",");
		buffer.append("\"").append("host").append("\":").append("\"").append(host).append("\"").append(",");
		buffer.append("\"").append("account").append("\":").append("\"").append(account).append("\"").append(",");
		buffer.append("\"").append("channel").append("\":").append("\"").append(channel).append("\"").append(",");
		buffer.append("\"").append("deviceModel").append("\":").append("\"").append(deviceModel).append("\"").append(",");
		buffer.append("\"").append("status").append("\":").append(status).append(",");
		buffer.append("\"").append("apnsAble").append("\":").append(apnsAble).append(",");
		buffer.append("\"").append("bindTime").append("\":").append(bindTime).append(",");
		buffer.append("\"").append("heartbeat").append("\":").append(heartbeat);
		buffer.append("}");
		return buffer.toString();

	}

}
