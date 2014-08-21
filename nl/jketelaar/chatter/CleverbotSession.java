package nl.jketelaar.chatter;

import java.util.LinkedHashMap;

public class CleverbotSession {
        private final LinkedHashMap<String, String> vars;
        private final String url;
        private int endIndex;

        public CleverbotSession(String url, int endIndex) {
            this.url = url;
            this.endIndex = endIndex;
            this.vars = new LinkedHashMap<String, String>();
            this.vars.put("start", "y");
            this.vars.put("icognoid", "wsf");
            this.vars.put("fno", "0");
            this.vars.put("sub", "Say");
            this.vars.put("islearning", "1");
            this.vars.put("cleanslate", "false");
        }

        private ChatterBotThought think(ChatterBotThought thought) throws Exception {
            this.vars.put("stimulus", thought.getText());

            String formData = Utils.parametersToWWWFormURLEncoded(this.vars);
            String formDataToDigest = formData.substring(9, this.endIndex);
            String formDataDigest = Utils.md5(formDataToDigest);
            this.vars.put("icognocheck", formDataDigest);

            String response = Utils.post(this.url, this.vars);

            String[] responseValues = response.split("\r");

            this.vars.put("sessionid", Utils.stringAtIndex(responseValues, 1));
            this.vars.put("logurl", Utils.stringAtIndex(responseValues, 2));
            this.vars.put("vText8", Utils.stringAtIndex(responseValues, 3));
            this.vars.put("vText7", Utils.stringAtIndex(responseValues, 4));
            this.vars.put("vText6", Utils.stringAtIndex(responseValues, 5));
            this.vars.put("vText5", Utils.stringAtIndex(responseValues, 6));
            this.vars.put("vText4", Utils.stringAtIndex(responseValues, 7));
            this.vars.put("vText3", Utils.stringAtIndex(responseValues, 8));
            this.vars.put("vText2", Utils.stringAtIndex(responseValues, 9));
            this.vars.put("prevref", Utils.stringAtIndex(responseValues, 10));

            this.vars.put("emotionalhistory", Utils.stringAtIndex(responseValues, 12));
            this.vars.put("ttsLocMP3", Utils.stringAtIndex(responseValues, 13));
            this.vars.put("ttsLocTXT", Utils.stringAtIndex(responseValues, 14));
            this.vars.put("ttsLocTXT3", Utils.stringAtIndex(responseValues, 15));
            this.vars.put("ttsText", Utils.stringAtIndex(responseValues, 16));
            this.vars.put("lineRef", Utils.stringAtIndex(responseValues, 17));
            this.vars.put("lineURL", Utils.stringAtIndex(responseValues, 18));
            this.vars.put("linePOST", Utils.stringAtIndex(responseValues, 19));
            this.vars.put("lineChoices", Utils.stringAtIndex(responseValues, 20));
            this.vars.put("lineChoicesAbbrev", Utils.stringAtIndex(responseValues, 21));
            this.vars.put("typingData", Utils.stringAtIndex(responseValues, 22));
            this.vars.put("divert", Utils.stringAtIndex(responseValues, 23));

            ChatterBotThought responseThought = new ChatterBotThought();

            responseThought.setText(Utils.stringAtIndex(responseValues, 16));

            return responseThought;
        }

        public String think(String text) throws Exception {
            ChatterBotThought thought = new ChatterBotThought();
            thought.setText(text);
            return think(thought).getText();
        }
    }
