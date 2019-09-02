package com.whty.example.socket.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class CustomStringEncoder extends ProtocolEncoderAdapter {

	public final static int DEFAULT_MAX_DATA_LENGTH = 2048;

	private final Charset charset;

	private int maxDataLength = DEFAULT_MAX_DATA_LENGTH;

	public CustomStringEncoder(Charset charset, int maxDataLength) {
		this.charset = charset;
		this.maxDataLength = maxDataLength;
	}

	public CustomStringEncoder(Charset charset) {
		this(charset, DEFAULT_MAX_DATA_LENGTH);
	}

	public void setMaxDataLength(int maxDataLength) {
		this.maxDataLength = maxDataLength;
	}

	public int getMaxDataLength() {
		return maxDataLength;
	}

	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		String value = (String) message;
		IoBuffer buffer = IoBuffer.allocate(value.length()).setAutoExpand(true);
		buffer.putString(value, charset.newEncoder());
		if (buffer.position() > maxDataLength) {
			throw new IllegalArgumentException("Data length: " + buffer.position());
		}
		buffer.flip();
		out.write(buffer);
	}
}
