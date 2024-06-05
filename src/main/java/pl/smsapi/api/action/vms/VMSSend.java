package pl.smsapi.api.action.vms;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractSendAction;
import pl.smsapi.api.response.StatusResponse;

import java.io.*;
import java.nio.file.Files;

public class VMSSend extends AbstractSendAction<VMSSend, StatusResponse> {

    public enum Lector {
        EWA,
        JACEK,
        JAN,
        MAJA;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    /**
     * @deprecated use {@link VMSSend(String, String)} or {@link VMSSend(String[], String)} or {@link VMSSend(String, File)}
     * or {@link VMSSend(String[], File)} instead
     */
    public VMSSend() {
        setJson(true);
    }

    public VMSSend(String to, String tts) {
        setJson(true);
        setTo(to);
        params.put("tts", tts);
    }

    public VMSSend(String[] to, String tts) {
        setJson(true);
        setTo(to);
        params.put("tts", tts);
    }

    public VMSSend(String to, File file) throws IOException {
        setJson(true);
        setTo(to);
        files.put("file", Files.newInputStream(file.toPath()));
    }

    public VMSSend(String[] to, File file) throws IOException {
        setJson(true);
        setTo(to);
        files.put("file", Files.newInputStream(file.toPath()));
    }

    public VMSSend(String[] to, InputStream file) {
        setJson(true);
        setTo(to);
        files.put("file", file);
    }

    /**
     * Set local audio file.
     *
     * @deprecated use {@link VMSSend(String, File)} or {@link VMSSend(String[], File)} instead
     *
     */
    public VMSSend setFile(File file) throws FileNotFoundException {
        files.put("file", new FileInputStream(file));
        return this;
    }

    /**
     * Set local audio filename.
     *
     * @deprecated use {@link VMSSend(String, File)} or {@link VMSSend(String[], File)} instead
     */
    public VMSSend setFile(String pathFile) throws FileNotFoundException {
        files.put("file", new FileInputStream(pathFile));
        return this;
    }

    /**
     * Set local audio stream.
     *
     * @deprecated use {@link VMSSend(String[], InputStream)} instead
     */
    public VMSSend setFile(InputStream inputStream) {
        files.put("file", inputStream);
        return this;
    }

    /**
     * Set text to voice synthesizer.
     *
     * @deprecated use {@link VMSSend(String, String)} or {@link VMSSend(String[], String)} instead
     */
    public VMSSend setTts(String tts) {
        params.put("tts", tts);
        return this;
    }

    /**
     * Set flag to not send messages on cell phone numbers.
     */
    public VMSSend setSkipGsm(boolean skipGsm) {

        if (skipGsm) {
            params.put("skip_gsm", "1");
        } else {
            params.remove("skip_gsm");
        }

        return this;
    }

    /**
     * Set lector name.
     */
    public VMSSend setTtsLector(Lector lector) {
        params.put("tts_lector", lector.toString());
        return this;
    }

    /**
     * Set the time in seconds where the connection have to be repeated in the case of not answer by receiver or reject this connection.
     */
    public VMSSend setInterval(Integer interval) {

        if (interval == null) {
            params.remove("interval");
        } else {
            params.put("interval", interval.toString());
        }
        return this;
    }


    /**
     * Set called number. Leaving the field blank causes the sending of the default number of callers.
     */
    public VMSSend setFrom(String from) {
        params.put("from", from);
        return this;
    }

    protected StatusResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new StatusResponse(jsonObject.getInt("count"), jsonObject.optJSONArray("list"));
    }

    @Override
    protected String endPoint() {
        return "vms.do";
    }
}
