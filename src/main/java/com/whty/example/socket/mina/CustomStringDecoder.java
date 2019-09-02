package com.whty.example.socket.mina;

import java.nio.BufferUnderflowException;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

import org.apache.mina.core.buffer.BufferDataException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomStringDecoder extends CumulativeProtocolDecoder {

	public final static int DEFAULT_MAX_DATA_LENGTH = 2048;

	private final Charset charset;

	private int maxDataLength = DEFAULT_MAX_DATA_LENGTH;
	private static final Logger logger = LoggerFactory.getLogger(CustomStringDecoder.class);

	public CustomStringDecoder(Charset charset, int maxDataLength) {
		this.charset = charset;
		this.maxDataLength = maxDataLength;
	}

	public CustomStringDecoder(Charset charset) {
		this(charset, DEFAULT_MAX_DATA_LENGTH);
	}

	public void setMaxDataLength(int maxDataLength) {
		this.maxDataLength = maxDataLength;
	}

	public int getMaxDataLength() {
		return maxDataLength;
	}

	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		logger.debug("RECEIVED: {}", in);
		// String msg = in.getString(charset.newDecoder());
		String msg = getString(in, charset.newDecoder());
		out.write(msg);
		return true;

	}

	private String getString(IoBuffer in, CharsetDecoder decoder) throws CharacterCodingException {

		int fieldSize = in.limit();

		if (fieldSize == 0) {
			return "";
		}

		boolean utf16 = decoder.charset().name().startsWith("UTF-16");

		if (utf16 && (fieldSize & 1) != 0) {
			throw new BufferDataException("fieldSize is not even for a UTF-16 string.");
		}

		int oldLimit = in.limit();
		int end = in.position() + fieldSize;

		if (oldLimit < end) {
			throw new BufferUnderflowException();
		}

		in.limit(end);
		decoder.reset();

		int expectedLength = (int) (in.remaining() * decoder.averageCharsPerByte()) + 1;
		CharBuffer out = CharBuffer.allocate(expectedLength);
		for (;;) {
			CoderResult cr;
			if (in.hasRemaining()) {
				cr = decoder.decode(in.buf(), out, true);
			} else {
				cr = decoder.flush(out);
			}

			if (cr.isUnderflow()) {
				break;
			}

			if (cr.isOverflow()) {
				CharBuffer o = CharBuffer.allocate(out.capacity() + expectedLength);
				out.flip();
				o.put(out);
				out = o;
				continue;
			}

			cr.throwException();
		}

		in.limit(oldLimit);
		in.position(end);
		return out.flip().toString();
	}
}
