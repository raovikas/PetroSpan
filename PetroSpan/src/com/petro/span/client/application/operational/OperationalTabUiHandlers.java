package com.petro.span.client.application.operational;

import com.gwtplatform.mvp.client.UiHandlers;
import com.petro.span.client.application.common.filter.OperationalCommonFilter;

interface OperationalTabUiHandlers extends UiHandlers {

	void fireSelectionEvent(OperationalCommonFilter operationalFilter);
}