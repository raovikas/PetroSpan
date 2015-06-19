package com.petro.span.client.resources;

import com.google.inject.Inject;

public class ResourcesLoader {

	@Inject
	ResourcesLoader(Resources resources){
		resources.style().ensureInjected();
	}

}
