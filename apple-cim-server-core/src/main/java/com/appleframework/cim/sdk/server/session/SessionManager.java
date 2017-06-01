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

import java.util.List;

/**
 * 客户端的 session管理接口 可自行实现此接口管理session
 */

public interface SessionManager {

	/**
	 * 添加新的session
	 */
	public void add(AbstractCIMSession session);

	/**
	 * 更新session
	 */
	public void update(AbstractCIMSession session);

	/**
	 * 
	 * @param account
	 *            客户端session的 key 一般可用 用户账号来对应session
	 * @return
	 */
	AbstractCIMSession get(String account);

	/**
	 * 获取所有session
	 * 
	 * @return
	 */
	public List<AbstractCIMSession> queryAll();

	/**
	 * 删除session
	 * 
	 * @param session
	 */
	public void remove(String account);

}
