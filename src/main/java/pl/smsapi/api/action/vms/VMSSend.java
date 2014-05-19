package pl.smsapi.api.action.vms;

import org.json.JSONObject;
import pl.smsapi.api.action.AbstractSendAction;
import pl.smsapi.api.response.StatusResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class VMSSend extends AbstractSendAction<VMSSend, StatusResponse> {

    public static enum Lector {
        AGNIESZKA,
        EWA,
        JACEK,
        JAN,
        MAJA;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    public VMSSend() {
        setJson(true);
    }

    /**
     * Set local audio file.
     */
    public VMSSend setFile(File file) throws FileNotFoundException {
        files.put("file", new FileInputStream(file));
        return this;
    }

    /**
     * Set local audio filename.
     */
    public VMSSend setFile(String pathFile) throws FileNotFoundException {
        files.put("file", new FileInputStream(pathFile));
        return this;
    }

    /**
     * Set local audio stream.
     */
    public VMSSend setFile(InputStream inputStream) {
        files.put("file", inputStream);
        return this;
    }

    /**
     * Set text to voice synthesizer.
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
