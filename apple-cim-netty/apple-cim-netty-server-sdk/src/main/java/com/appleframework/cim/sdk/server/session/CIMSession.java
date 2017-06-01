/**
 * Copyright 2013-2023 Xia Jun(3979434@qq.com).
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
import java.net.SocketAddress;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

/**
 * Channel包装类,集群时 将此对象存入表中
 */

public class CIMSession extends AbstractCIMSession implements Serializable {

	private transient static final long serialVersionUID = 1L;

	private transient Channel session;

	public CIMSession(Channel session) {
		this.session = session;
		this.nid = session.id().asShortText();
	}

	public CIMSession() {

	}

	public void setChannel(Channel session) {
		this.session = session;
	}

	public Channel getSession() {
		return session;
	}

	public void setSession(Channel session) {
		this.session = session;
	}

	@Override
	public void setAttribute(String key, Object value) {
		if (session != null)
			session.attr(AttributeKey.valueOf(key)).set(value);
	}

	@Override
	public boolean containsAttribute(String key) {
		if (session != null)
			return session.hasAttr(AttributeKey.valueOf(key));
		return false;
	}

	@Override
	public Object getAttribute(String key) {
		if (session != null)
			return session.attr(AttributeKey.valueOf(key)).get();
		return null;
	}

	@Override
	public void removeAttribute(String key) {
		if (session != null)
			session.attr(AttributeKey.valueOf(key)).set(null);
	}

	@Override
	public SocketAddress getRemoteAddress() {
		if (session != null)
			return session.remoteAddress();
		return null;
	}

	@Override
	public boolean write(Object msg) {
		if (session != null) {
			return session.writeAndFlush(msg).awaitUninterruptibly(5000);
		}
		return false;
	}

	@Override
	public boolean isConnected() {
		if (session != null) {
			return session.isActive();
		}
		if (!isLocalhost()) {
			return status == STATUS_ENABLED;
		}
		return false;
	}

	@Override
	public void closeNow() {
		if (session != null)
			session.close();
	}

	@Override
	public void closeOnFlush() {
		if (session != null)
			session.close();
	}

	@Override
	public void setSession(Object session) {
		this.session = (Channel)session;
	}	
	
}
