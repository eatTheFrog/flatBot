package ru.eatthefrog.hatterBot.HTTPGetter;

import java.io.IOException;
import java.net.MalformedURLException;

public interface HTTPGetterable {
    public String doRequest(String url_string);
}
