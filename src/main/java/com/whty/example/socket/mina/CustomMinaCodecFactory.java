package com.whty.example.socket.mina;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class CustomMinaCodecFactory implements ProtocolCodecFactory {

	private final CustomStringEncoder encoder;

	private final CustomStringDecoder decoder;

	public CustomMinaCodecFactory(Charset charset) {
		encoder = new CustomStringEncoder(charset);
		decoder = new CustomStringDecoder(charset);
	}

	public CustomMinaCodecFactory() {
		this(Charset.defaultCharset());
	}

	public int getEncoderMaxDataLength() {
		return encoder.getMaxDataLength();
	}

	public void setEncoderMaxDataLength(int maxDataLength) {
		encoder.setMaxDataLength(maxDataLength);
	}

	public int getDecoderMaxDataLength() {
		return decoder.getMaxDataLength();
	}

	public void setDecoderMaxDataLength(int maxDataLength) {
		decoder.setMaxDataLength(maxDataLength);
	}

	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}

	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}
}
