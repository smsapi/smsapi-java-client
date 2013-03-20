package pl.smsapi.message;

import java.io.File;

public interface SmilInterface {

	public void createElement(Smil.Region region, String src, String height, String width, String top, String left, String fit);

	public void createElement(byte[] data, Smil.ByteType type, String height, String width, String top, String left, String fit);

	public void createElement(File file, String height, String width, String top, String left, String fit);

	public String render();
}
