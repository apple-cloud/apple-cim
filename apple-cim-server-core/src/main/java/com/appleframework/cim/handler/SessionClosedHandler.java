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
package com.appleframework.cim.handler;

import org.apache.log4j.Logger;

import com.appleframework.cim.sdk.server.constant.CIMConstant;
import com.appleframework.cim.sdk.server.handler.CIMRequestHandler;
import com.appleframework.cim.sdk.server.model.ReplyBody;
import com.appleframework.cim.sdk.server.model.SentBody;
import com.appleframework.cim.sdk.server.session.AbstractCIMSession;
import com.appleframework.cim.sdk.server.session.SessionManager;
import com.appleframework.cim.util.ContextHolder;

/**
 * 断开连接，清除session
 * 
 */
public class SessionClosedHandler implements CIMRequestHandler {

	protected final Logger logger = Logger.getLogger(SessionClosedHandler.class);

	public ReplyBody process(AbstractCIMSession ios, SentBody message) {
		Object account = ios.getAttribute(CIMConstant.SESSION_KEY);
		if (account == null) {
			return null;
		}
		SessionManager sessionManager = ContextHolder.getBean(SessionManager.class);
		ios.removeAttribute(CIMConstant.SESSION_KEY);
		sessionManager.remove(account.toString());
		return null;
	}

}
