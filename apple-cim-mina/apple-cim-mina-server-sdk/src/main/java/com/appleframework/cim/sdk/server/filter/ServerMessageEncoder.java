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
package com.appleframework.cim.sdk.server.filter;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.appleframework.cim.sdk.server.constant.CIMConstant;
import com.appleframework.cim.sdk.server.model.Protobufable;


/**
 * 服务端发送消息前编码
 */
public class ServerMessageEncoder extends ProtocolEncoderAdapter {

	protected final Logger logger = Logger.getLogger(ServerMessageEncoder.class);
	@Override
	public void encode(IoSession iosession, Object object, ProtocolEncoderOutput out) throws Exception {
		
		if(object instanceof Protobufable){
    		
    		Protobufable data = (Protobufable) object;
    		byte[] byteArray = data.getByteArray();
    		
        	IoBuffer buff = IoBuffer.allocate(byteArray.length + CIMConstant.DATA_HEADER_LENGTH).setAutoExpand(true);
        	
    		buff.put(createHeader(data.getType(),byteArray.length));
    		buff.put(byteArray);
    		
    		buff.flip();
			out.write(buff);
			
			//打印出收到的消息
			logger.info(data.toString());
 		}
	}
	
	/**
	 * 消息体最大为65535
	 * @param type
	 * @param length
	 * @return
	 */
	private byte[] createHeader(byte type,int length){
		byte[] header = new byte[CIMConstant.DATA_HEADER_LENGTH];
		header[0] = type;
		header[1] = (byte) (length & 0xff);
        header[2] = (byte) ((length >> 8) & 0xff);
		return header;
	}
	
	 

}
