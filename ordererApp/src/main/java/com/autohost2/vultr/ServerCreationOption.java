package com.autohost2.vultr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import com.autohost2.vultr.param.ConfigProperties;
import com.autohost2.vultr.param.Dcid;
import com.autohost2.vultr.param.Osid;
import com.autohost2.vultr.param.Vpsplanid;

/**
 * Permet de de construire les paramètres pour
 * la création d'une instance
 * dans un objet JSON
 * @author mehdi
 *
 */
@Component
public class ServerCreationOption {

    @Autowired
    private ConfigProperties configProperties;

    public ServerCreationOption() {
        super();
    }

    public ConfigProperties getConfigProperties() {
        return configProperties;
    }

    public void setConfigProperties(ConfigProperties configProperties) {
        this.configProperties = configProperties;
    }

    public ServerCreationOption(ConfigProperties configProperties) {
        super();
        this.configProperties = configProperties;
    }

    public JSONObject getPostOptionForCreation(Dcid dcid, Vpsplanid vpsplanid, Osid osid) throws JSONException {
        JSONObject hostJsonObject = new JSONObject();
        hostJsonObject.put("url", configProperties.getBaseurl() + "/server/create");
        hostJsonObject.put("DCID", dcid.toString());
        hostJsonObject.put("OSID", osid.toString());
        hostJsonObject.put("VPSPLANID", vpsplanid.toString());
        return hostJsonObject;
    }

    public String getServerState(String string) {
        return "/server/list?SUBID=" + string;

    }

    public JSONObject getPostOptionForCreation(String dcid, String vpsplanid, String osid) throws JSONException {
        // TODO Auto-generated method stub
        JSONObject hostJsonObject = new JSONObject();
        hostJsonObject.put("url", configProperties.getBaseurl() + "/server/create");
        hostJsonObject.put("DCID", dcid);
        hostJsonObject.put("OSID", osid);
        hostJsonObject.put("VPSPLANID", vpsplanid);
        return hostJsonObject;

    }

}
