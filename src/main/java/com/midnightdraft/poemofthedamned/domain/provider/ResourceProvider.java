package com.midnightdraft.poemofthedamned.domain.provider;

import java.net.URL;

public interface ResourceProvider {

  String getPath(ResourceKey key);

  URL getUrl(ResourceKey key);
}
