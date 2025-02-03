package com.example.User_Registration.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IpAndCountryLookup {
    private final RestTemplate restTemplate;

    public IpAndCountryLookup(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    public String getIpAddress(){
        String ipifyUrl = "https://api.ipify.org?format=json";
        IpifyResponse response = restTemplate.getForObject(ipifyUrl, IpifyResponse.class);
        return response != null ? response.getIp() : null;
    }
    public String getCountry(String ipAddress) {
        String ipApiUrl = "http://ip-api.com/json/" + ipAddress;
        IpApiResponse response = restTemplate.getForObject(ipApiUrl, IpApiResponse.class);
        return response != null ? response.getCountry() : null;
    }

    private static class IpifyResponse {
        private String ip;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
    }

    private static class IpApiResponse {
        private String country;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
}