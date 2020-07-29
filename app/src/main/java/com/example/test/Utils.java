package com.example.test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Utils {

    public static InputStream getInputStream() {
        final String certificate = "-----BEGIN CERTIFICATE-----\n" +
                "MIIEIzCCAwugAwIBAgIUN0SU83eJ+trzVXHUfZC91Z8P7/kwDQYJKoZIhvcNAQEL\n" +
                "BQAwgYsxFjAUBgNVBAMMDVdlbGxDb25uZWN0ZWQxJDAiBgkqhkiG9w0BCQEWFWdh\n" +
                "YnJpZWxAZGVtZWNvLmNvbS5hcjELMAkGA1UEBhMCQVIxDTALBgNVBAgMBENBQkEx\n" +
                "FTATBgNVBAcMDEJ1ZW5vcyBBaXJlczEYMBYGA1UECgwPR2FicmllbCBEZSBNZWNv\n" +
                "MB4XDTIwMDcyMzIxNTcxM1oXDTIxMDcyMzIxNTcxM1owgYsxFjAUBgNVBAMMDVdl\n" +
                "bGxDb25uZWN0ZWQxJDAiBgkqhkiG9w0BCQEWFWdhYnJpZWxAZGVtZWNvLmNvbS5h\n" +
                "cjELMAkGA1UEBhMCQVIxDTALBgNVBAgMBENBQkExFTATBgNVBAcMDEJ1ZW5vcyBB\n" +
                "aXJlczEYMBYGA1UECgwPR2FicmllbCBEZSBNZWNvMIIBIjANBgkqhkiG9w0BAQEF\n" +
                "AAOCAQ8AMIIBCgKCAQEAzS9aNfaDYl4XVagX6K3YQx02s5MydYmGAD/E6F274xSr\n" +
                "84NA7h59VIOzZcK9Zj97O08zzFBErif26lrD55zA27hQOS+qUWNT9TKjuMbUJyiT\n" +
                "geHs2tOQGyQc/xBWOq/4QWMtJwZzjvmVFBKoTvH49M5G8szJGUUNO559STYQ/jwt\n" +
                "jH129/Oo7g4VV2TiKSu3sOY7Mv/KsnqnZQhZClq/7GIxyPGDOhVji0GcUDqbdw8e\n" +
                "Rb9AdsmO5SgZghYS+ODLH+1UN6MfXFRE4rDRqqarSEoyc8DSa8ULWDMMz6+fJBJi\n" +
                "V333qVN0Chega6kBkEVHI6AdSf89yQJ5SdJFE90ZGQIDAQABo30wezAdBgNVHQ4E\n" +
                "FgQUketdLJbAEqBXf8UKcxbnjSHKjJswDAYDVR0TAQH/BAIwADAeBgNVHREEFzAV\n" +
                "ghN3Y2FwaS5kZW1lY28uY29tLmFyMCwGCWCGSAGG+EIBDQQfFh1PcGVuU1NMIEdl\n" +
                "bmVyYXRlZCBDZXJ0aWZpY2F0ZTANBgkqhkiG9w0BAQsFAAOCAQEAUtPcEXsPOSt9\n" +
                "ld0z+FFvLvtdtWOBMw1hfiqWU0e/YZwk/PFG2fzmPd+cujVeeEDPJxA8WpV/ofy3\n" +
                "PivaAV+eQSZ9P3Bdf8Vawo71k0FF1+CyIVeWF/VJtzukkbmQAezLW6Bf4Dp8EfiC\n" +
                "rtNpfhD3wP2fEkpKukB4HmtlatfFpScNQX8g2CbSPx28ZpQ0UxBZZY1gRFsj8PhA\n" +
                "6fJaVoMdsItT7mV7nEo28apz1yJf0keBsSXDW3/mugvus7WMgzfYdP8S9sCtXnyc\n" +
                "AxrQ1vcXcbi/+ZGyAgEJ9e/LMuLcyw3pHTOtuxQB47hZUhmLmZRhLWLG2Llm1QWk\n" +
                "n/tiECUXpw==\n" +
                "-----END CERTIFICATE-----";
        return new ByteArrayInputStream(certificate.getBytes());
    }
}
