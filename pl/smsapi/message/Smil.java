package pl.smsapi.message;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.smsapi.SmsapiException;
//import javax.activation.MimetypesFileTypeMap;
//import com.sun.xml.internal.messaging.saaj.util.Base64;

public final class Smil implements SmilInterface {

	String content;

	public static enum Region {

		IMAGE("Image", "img"),
		TEXT("Text", "text"),
		AUDIO("Audio", "audio"),
		VIDEO("Video", "video");
		private final String id;
		private final String tag;

		Region(String id, String tag) {
			this.id = id;
			this.tag = tag;
		}

		public String id() {
			return id;
		}

		public String tag() {
			return tag;
		}
	};

	public static enum ByteType {

		TXT("text"),
		JPG("image"),
		PNG("image"),
		GIF("image"),
		WAV("audio"),
		MP3("audio"),
		OGG("audio"),
		MP4("video"),
		AVI("video");
		private final String type;

		ByteType(String type) {
			this.type = type;
		}

		public String type() {

			String tmp = this.name().toLowerCase();

			if (tmp.equals("txt")) {
				tmp = "plain";
			} else if (tmp.equals("mp3")) {
				tmp = "mpeg";
			}

			return type + "/" + tmp;
		}
	};
	private HashMap<String, String> head = new HashMap<String, String>();
	private HashMap<String, String> body = new HashMap<String, String>();
	String width = "100%";
	String height = "100%";
	String dur;

	Smil() {
	}

	Smil(String content) {
		this.content = content;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getDur() {
		return dur;
	}

	public void setDur(String dur) {
		this.dur = dur;
	}

	@Override
	public void createElement(Region region, String src, String height, String width, String top, String left, String fit) {

		String item1 = regionTag(region, height, width, top, left, fit);

		head.put(region.id(), item1);

		String item2 = "<" + region.tag() + " src=\"" + src + "\" region=\"" + region.id() + "\" />";

		body.put(region.id(), item2);

	}

	@Override
	public void createElement(byte[] data, ByteType type, String height, String width, String top, String left, String fit) {

		//String encodeText = new String(Base64.encode(data));
		String encodeText = (String) Base64Coder.encodeLines(data);
		Region region = getRegionByByteType(type);

		String item1 = regionTag(region, height, width, top, left, fit);

		head.put(region.id(), item1);

		String item2 = "<" + region.tag() + " src=\"data:" + type.type() + ";base64," + encodeText + "\" region=\"" + region.id() + "\" />";

		body.put(region.id(), item2);

	}

	private Region getRegionByByteType(ByteType byteType) {

		Region region = null;

		switch (byteType) {
			case TXT:
				region = Region.TEXT;
				break;

			case JPG:
				region = Region.IMAGE;
				break;

			case PNG:
				region = Region.IMAGE;
				break;

			case GIF:
				region = Region.IMAGE;
				break;

			case WAV:
				region = Region.AUDIO;
				break;

			case MP3:
				region = Region.AUDIO;
				break;

			case OGG:
				region = Region.AUDIO;
				break;

			case MP4:
				region = Region.VIDEO;
				break;

			case AVI:
				region = Region.VIDEO;
				break;
		}

		return region;
	}

	@Override
	public void createElement(File file, String height, String width, String top, String left, String fit) {

		try {

			String fileName = file.getName();
			ByteType byteType;
			final long length = file.length();

			byte[] DataBa = new byte[(int) length];
			DataInputStream dataIs = new DataInputStream(new FileInputStream(file));
			dataIs.readFully(DataBa);

			try {

				fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
				byteType = ByteType.valueOf(fileName.toUpperCase());

				createElement(DataBa, byteType, width, height, left, top, fit);

			} catch (IllegalArgumentException ex) {
				throw new SmsapiException("Not exists byteType");
			} finally {
				dataIs.close();
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(Smil.class.getName()).log(Level.SEVERE, null, ex);
			throw new SmsapiException(ex.getMessage());
		} catch (IOException ex) {
			Logger.getLogger(Smil.class.getName()).log(Level.SEVERE, null, ex);
			throw new SmsapiException(ex.getMessage());
		}
	}

	private String regionTag(Region region, String height, String width, String top, String left, String fit) {

		String item1 = "<region id=\"" + region.id() + "\"";

		if (width != null && !width.isEmpty()) {
			item1 += " width=\"" + width + "\"";
		}

		if (height != null && !height.isEmpty()) {
			item1 += " height=\"" + height + "\"";
		}

		if (left != null && !left.isEmpty()) {
			item1 += " left=\"" + top + "\"";
		}

		if (top != null && !top.isEmpty()) {
			item1 += " top=\"" + top + "\"";
		}

		if (fit != null && !fit.isEmpty()) {
			item1 += " fit=\"" + fit + "\"";
		}

		item1 += "/>";

		return item1;
	}

	public final String render() {
		if (content != null && !content.isEmpty()) {
			return content;
		}

		String smil;

		smil = "<smil><head><layout><root-layout height=\"" + height + "\" width=\"" + width + "\"/>";

		for (String region : head.values()) {
			smil += region;
		}

		smil += "</layout></head><body>";

		smil += (dur == null) ? "<par>" : "<par dur=\"" + dur + "\">";

		for (String item : body.values()) {
			smil += item;
		}

		smil += "</par></body></smil>";

		return smil;
	}
}
